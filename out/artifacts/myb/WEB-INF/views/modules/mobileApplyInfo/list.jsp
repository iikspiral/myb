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
		<li class="active"><a href="${ctx}/mobileApplyInfo/list?clickType=2">证书查询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mobileApplyInfo"
		action="${ctx}/mobileApplyInfo/list?&clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<div>	
			<label>证书主题：</label>
			<form:input path="certSubject" htmlEscape="false" maxlength="600" width="205px"/>
			<label style="margin-left: 21px;">手机号：</label>
			<form:input path="phoneNum" htmlEscape="false" maxlength="600" width="205px"/>
			<label style="margin-left: 23px;">申请类型：</label>
			<form:select id="applyType" path="applyType" cssStyle="width:200px"   >
				<form:option value="" label="请选择" />
				<form:options items="${fns:getDictList('opt_type') }" itemLabel="label" itemValue="value"/> 
				<form:options items="${fns:getDictList('opt_type_other') }" itemLabel="label" itemValue="value"/> 
			</form:select>
		</div>
		<div style="margin-top: 5px;">
			<label style="margin-left: -4px;">证书序列号：</label>
			<form:input path="certSn" htmlEscape="false" maxlength="600" width="205px"/>
			 <label style="margin-left: 10px;">用户名称：</label>
			<form:input path="userName" htmlEscape="false" maxlength="600" width="205px"/> 
			<label>项目名称：</label>
			<form:select path="projectName" id="projectName" cssStyle="width:200px">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getProjectInfoList()}" itemLabel="projectName" itemValue="id"/>
			</form:select> 
			&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" 
				value="查询" />
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
				<th style="width:7%;text-align: center;">手机号</th>
				<th style="width:7%;text-align: center;">申请类型</th>
				<th style="width:7%;text-align: center;">申请结果</th>
				<th style="width:5%;text-align: center;">错误结果</th>
				<th style="width:10%;text-align: center;">创建时间</th>
			    <th style="width:2%;text-align: center;">操作</th>
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
						<td style="text-align: center;">${fns:getDictLabel(obj.userType, 'cert_type', '')}</td>
						<td style="text-align: center;">${obj.userName}</td>
						<td style="text-align: center;"><a href="${ctx}/mobileApplyInfo/form?id=${obj.id}">${obj.phoneNum}</a></td>
						<td style="text-align: center;">${fns:getDictLabel(obj.applyType, 'opt_type', fns:getDictLabel(obj.applyType, 'opt_type_other', ''))}</td>
						<td class="tip" title="${obj.applyResult}">${obj.applyResult}</td>
						<td class="tip" title="${obj.errorMessage}">${obj.errorMessage}</td>
						<td style="text-align: center;"><fmt:formatDate value="${obj.createDate}" type="both"  dateStyle="medium" timeStyle="medium"/></td>
						<td style="text-align: center;">
							<a href="${ctx}/mobileApplyInfo/form?id=${obj.id}">查看</a> 
						</td>  
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>