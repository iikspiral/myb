<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信平台明细</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/smsTemplate/temList">短信平台列表</a></li>
		<li class="active"><a href="${ctx}/smsTemplate/temForm?id=${smsTemplate.id}">短信平台${not empty smsTemplate.id?'编辑':'添加'}</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="smsTemplate" action="${ctx}/smsTemplate/temSave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">模板ID:</label>
			<div class="controls">
				<form:input path="templateId" htmlEscape="false" rows="3" cssStyle="width:270px"
							minlength="0" maxlength="600" class="required input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模板名称:</label>
			<div class="controls">
				<form:input path="templateName" htmlEscape="false" rows="3" cssStyle="width:270px"
							minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模板类型:</label>
			<div class="controls">
				<form:select path="templateType" htmlEscape="false" maxlength="50" class="input-medium">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('smsTemplate_type') }" itemLabel="label" itemValue="value"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短信签名:</label>
			<div class="controls">
				<form:input path="smsSignature" htmlEscape="false" rows="3" cssStyle="width:270px"
							minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短信内容:</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="3" cssStyle="width:270px"
							minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>