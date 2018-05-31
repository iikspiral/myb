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
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/certApplyInfo/list?clickType=2">证书查询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="certapplyInfo"
		action="${ctx}/certApplyInfo/list?&clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<div>	
			<label style="margin-left: 12px;">证书主题：</label>
			<form:input path="certSubject" htmlEscape="false" maxlength="600" width="205px"/>
			<label >申请类型：</label>
			<form:select id="optType" path="optType" cssStyle="width:200px"   >
				<form:option value="" label="请选择" />
				<form:options items="${fns:getDictList('ra_opt_type') }" itemLabel="label" itemValue="value"/>
			</form:select>
			
			<label style="margin-left: 24px;" for="itemTypeName">生效时间：</label>
			<input type="text"  value="<fmt:formatDate value="${certapplyInfo.beforeMin}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="beforeMin" name="beforeMin" class="Wdate" onClick="WdatePicker({isShowClear:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />&nbsp;
			<label for="itemTypeName">到</label>
			<input type="text" value="<fmt:formatDate value="${certapplyInfo.beforeMax}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="beforeMax" name="beforeMax" class="Wdate" onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
		</div>
		<div style="margin-top: 5px;">
			<label style="margin-left: -1px;">证书序列号：</label>
			<form:input path="certSn" htmlEscape="false" maxlength="600" width="205px"/>&nbsp;&nbsp;&nbsp;
			<label style="margin-left: -2px;">申请状态：</label>
			<form:select id="reqStatus" path="reqStatus" cssStyle="width:200px"   >
				<form:option value="" label="请选择" />
				<form:options items="${fns:getDictList('req_status') }" itemLabel="label" itemValue="value"/>
			</form:select>
			<label style="margin-left: 24px;">项目名称：</label>
			<form:select path="projectName" id="projectName" cssStyle="width:200px">
				<form:option value="" label="请选择" />
				<form:options items="${fns:getProjectInfoList()}" itemLabel="projectName" itemValue="id"/>
			</form:select> 
		</div>
		<div style="margin-top: 5px;">
		 <label style="margin-left: 12px;">用户名称：</label>
			<form:input path="userName" htmlEscape="false" maxlength="600" width="205px"/> 
			<input style="margin-left: 80px;" id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		</div>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:1%;text-align: center;">序号</th>
				<th style="width:10%;text-align: center;">项目名称</th>
				<th style="width:20%;text-align: center;">证书主题</th>
				<th style="width:10%;text-align: center;">证书序列号</th>
				<th style="width:5%;text-align: center;">用户类型</th>
				<th style="width:5%;text-align: center;">用户名称</th>
				<th style="width:13%;text-align: center;">模板名称</th>
				<th style="width:5%;text-align: center;">申请状态</th>
				<th style="width:3%;text-align: center;">申请类型</th>
				<th style="width:7%;text-align: center;">生效时间</th>
				<th style="width:5%;text-align: center;">有效时间（天）</th>
				<th style="width:7%;text-align: center;">创建时间</th>
				<th style="width:2%;text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="13" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;">${obj.projectName}</td>
						<td class="tip" title="${obj.certSubject}">${obj.certSubject}</td>
						<td style="text-align: center;">${obj.certSn}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.certType, 'cert_type', '')}</td>
						<td style="text-align: center;">${obj.userName}</td>
						<td style="text-align: center;">${obj.ctmlName}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.reqStatus, 'req_status', '无')}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.optType, 'ra_opt_type', '无')}</td>
						<td style="text-align: center;">${fn:substring(obj.notBefore,0,4)}-${fn:substring(obj.notBefore,4,6)}-${fn:substring(obj.notBefore,6,8)} ${fn:substring(obj.notBefore,8,10)}:${fn:substring(obj.notBefore,10,12)}:${fn:substring(obj.notBefore,12,14)}</td>
						<td style="text-align: center;">${obj.certValidity}</td>
						<td style="text-align: center;"><fmt:formatDate value="${obj.createDate}" type="both"  dateStyle="medium" timeStyle="medium"/></td>
						<td style="text-align: center;">
							<a href="${ctx}/certApplyInfo/form?id=${obj.id}">查看</a> 
						</td>  
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>