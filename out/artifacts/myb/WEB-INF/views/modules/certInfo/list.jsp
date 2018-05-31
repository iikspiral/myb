<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>抽取比例列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	
	/* $(document).ready(function() {
		var value = $(certInfo.certApplyInfo.projectId) .val();
		alert(value);
	}  */
	$(document).ready(function() {
		$("#btnSyncToday").click(function(){
			$.ajax({
				url:'${ctx}/certApplyInfo/syncCertInfoToday',
				cache: false,
				success:function(data){
					if(data=='true'){
						alert("状态同步成功！");
						window.location.href="${ctx}/certInfo/list";
					} else if(data=='null'){
						alert("没有需要同步的数据");
					} else {
						alert("状态同步失败!");
					}
				}
			});
		});
	});
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/certInfo/list?clickType=2">证书查询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="certInfo"
		action="${ctx}/certInfo/list?&clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<div>	
			<label>证书主题：</label>
			<form:input path="certSubject" htmlEscape="false" maxlength="600" width="200px"/>
			<label>项目名称：</label>
			 <form:select path="projectName" id="projectName" cssStyle="width:200px">
				<form:option value="" label="请选择" />
					<form:options items="${fns:getProjectInfoList()}" itemLabel="projectName" itemValue="id"/>
			</form:select> 
			 
			<label for="itemTypeName">生效时间：</label>
			<input type="text" value="<fmt:formatDate value="${certInfo.beforeMin}" pattern="yyyy-MM-dd HH:mm:ss"/>"  id="beforeMin" name="beforeMin" class="Wdate" onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />&nbsp;
			<label for="itemTypeName">到</label>
			<input type="text" value="<fmt:formatDate value="${certInfo.beforeMax}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="beforeMax" name="beforeMax" class="Wdate" onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
	 	</div>	
		<div style="margin-top: 5px;">
			<label style="margin-left: -3px;">证书序列号：</label>
			<form:input path="certSn" htmlEscape="false" maxlength="600" width="200px"/>
			<label>证书状态：</label>
			<form:select id="certStatus" path="certStatus" cssStyle="width:200px"   >
				<form:option value="" label="请选择" />
				<form:options items="${fns:getDictList('cert_status') }" itemLabel="label" itemValue="value"/>
			</form:select>
			<label for="itemTypeName">失效时间：</label>
			<input type="text" value="<fmt:formatDate value="${certInfo.afterMin}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="afterMin" name="afterMin" class="Wdate" onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />&nbsp;
			<label for="itemTypeName">到</label>
			<input type="text" value="<fmt:formatDate value="${certInfo.afterMax}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="afterMax" name="afterMax" class="Wdate" onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
		</div>
		<div style="margin-top: 5px;">
		    <label style="margin-left: 10px;">用户名称：</label>
			<form:input path="userName" htmlEscape="false" maxlength="600" width="200px"/>
			<span style="margin-left: 80px;">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
			<input id="btnSyncToday" class="btn btn-info" type="button" value="状态同步"/>
			</span>
		</div>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">项目名称</th>
				<th style="text-align: center;">证书主题</th>
				<th style="text-align: center;">证书序列号</th>
				<th style="text-align: center;">证书状态</th>
				<th style="text-align: center;">用户类型</th>
				<th style="text-align: center;">用户名称</th>
				<th style="text-align: center;">模板名称</th>
				<th style="text-align: center;">生效时间</th>
				<th style="text-align: center;">失效时间</th>
				<th style="text-align: center;">有效时间（天）</th>
				<!-- <th style="text-align: center;">创建时间</th> -->
			    <th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="12" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;">${obj.projectName}</td>
						<td class="tip" title="${obj.certSubject}">${obj.certSubject}</td>
						<td style="text-align: center;">${obj.certSn}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.certStatus, 'cert_status', '')}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.certType, 'cert_type', '')}</td>
						<td style="text-align: center;">${obj.userName}</td>
						<td style="text-align: center;">${obj.ctmlName}</td>
						<td style="text-align: center;">${fn:substring(obj.notBefore,0,4)}-${fn:substring(obj.notBefore,4,6)}-${fn:substring(obj.notBefore,6,8)} ${fn:substring(obj.notBefore,8,10)}:${fn:substring(obj.notBefore,10,12)}:${fn:substring(obj.notBefore,12,14)}</td>
						<td style="text-align: center;">${fn:substring(obj.notAfter,0,4)}-${fn:substring(obj.notAfter,4,6)}-${fn:substring(obj.notAfter,6,8)} ${fn:substring(obj.notAfter,8,10)}:${fn:substring(obj.notAfter,10,12)}:${fn:substring(obj.notAfter,12,14)}</td>
						<td style="text-align: center;">${obj.certValidity}</td>
						<%-- <td style="text-align: center;"><fmt:formatDate value="${obj.createDate}" type="both"  dateStyle="medium" timeStyle="medium"/></td> --%>
						<td style="text-align: center;">
							<a href="${ctx}/certInfo/form?id=${obj.id}">查看</a> 
						</td> 
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
 	<div class="pagination">${page}</div> 
</body>
</html>