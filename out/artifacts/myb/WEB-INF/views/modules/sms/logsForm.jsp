<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信日志明细</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/smsLogs/logsList">短信日志列表</a></li>
		<li class="active"><a href="${ctx}/smsLogs/logsForm?id=${smsPlatform.id}">短信日志查看</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="smsLogs" action="${ctx}/smsLogs/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">手机号:</label>
			<div class="controls">
					${smsLogs.phoneNum}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模板名称:</label>
			<div class="controls">
					${smsLogs.templateName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短信内容:</label>
			<div class="controls">
					${smsLogs.content}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送状态:</label>
			<div class="controls">
					${smsLogs.sendState}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送时间:</label>
			<div class="controls">
				<fmt:formatDate value="${smsLogs.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="form-actions">
			<%--<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;--%>
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>