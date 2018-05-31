<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信平台明细</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/smsPlatform/list">短信平台列表</a></li>
		<li class="active"><a href="${ctx}/smsPlatform/form?id=${smsPlatform.id}">短信平台${not empty smsPlatform.id?'编辑':'添加'}</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="smsPlatform" action="${ctx}/smsPlatform/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">短信平台名称:</label>
			<div class="controls">
				<form:input path="smsName" htmlEscape="false" rows="3" cssStyle="width:270px"
							minlength="0" maxlength="600" class="required input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短信平台编码:</label>
			<div class="controls">
				<form:input path="smsCode" htmlEscape="false" rows="3" cssStyle="width:270px"
							minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开发者ID:</label>
			<div class="controls">
				<form:input path="accountId" htmlEscape="false" rows="3" cssStyle="width:270px"
							minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开发者token:</label>
			<div class="controls">
				<form:input path="tokenId" htmlEscape="false" rows="3" cssStyle="width:270px"
							minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应用ID:</label>
			<div class="controls">
				<form:input path="appId" htmlEscape="false" rows="3" cssStyle="width:270px"
							minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="state" cssStyle="width:270px" class="required">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('smsPlatform_status') }" itemLabel="label" itemValue="value"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${not empty smsPlatform.id}">
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<fmt:formatDate value="${smsPlatform.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>