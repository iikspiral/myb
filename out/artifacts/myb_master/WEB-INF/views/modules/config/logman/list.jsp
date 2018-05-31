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
		<li class="active"><a href="${ctx}/logman/list?clickType=2">保存证书日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="certAppLog"
		action="${ctx}/logman/list?&clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>证书序列号：</label>
		<form:input path="certSn" htmlEscape="false" maxlength="600"
			class="input-medium" />
		<%-- <label>应用标识：</label>
		<form:input path="systemFlag" htmlEscape="false" maxlength="600"
			class="input-medium" /> --%>
		<label>操作名称：</label>
		<form:input path="operationName" htmlEscape="false" maxlength="600"
			class="input-medium" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" 
			value="查询" />
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">证书序列号</th>
				<th style="text-align: center;">操作名称</th>
				<th style="text-align: center;">系统名称</th>
				<th style="text-align: center;">业务描述</th>
				<th style="text-align: center;">操作时间</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="6" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;">${obj.certSn}</td>
						<td style="text-align: center;">${obj.operationName}</td>
						<td style="text-align: center;">${obj.systemFlag}</td>
						<td style="text-align: center;">${obj.busDes}</td>
						<td style="text-align: center;"><fmt:formatDate value="${obj.operationDate}" type="both"  dateStyle="medium" timeStyle="medium"/></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>