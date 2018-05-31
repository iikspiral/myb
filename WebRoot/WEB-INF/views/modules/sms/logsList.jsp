]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/smsLogs/logsList">短信日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="smsLogs" action="${ctx}/smsLogs/logsList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>手机号：</label><form:input path="phoneNum" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		&nbsp;<input class="btn btn-primary" type="reset" value="清空"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<td width="20%">手机号</td>
			<td width="20%">模板名称</td>
			<td width="15%">发送状态</td>
			<td width="15%">发送时间</td>
			<td width="5%">编辑</td>
		</tr>
		<c:forEach items="${page.list}" var="smsLogs">
			<tr>
				<td><a href="${ctx}/smsLogs/logsForm?id=${smsLogs.id}">${smsLogs.phoneNum}</a></td>
				<td>${smsLogs.templateName}</td>
				<td>${smsLogs.sendState}</td>
				<td><fmt:formatDate value="${smsLogs.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<a href="${ctx}/smsLogs/logsForm?id=${smsLogs.id}" >查看</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>