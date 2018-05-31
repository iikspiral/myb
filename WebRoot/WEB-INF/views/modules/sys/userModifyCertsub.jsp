<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>修改证书</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#oldcertsub").focus();
			$("#inputForm").validate({
				rules: {
				},
				messages: {
					confirmNewCertsub: {equalTo: "输入与上面相同的证书"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li class="active"><a href="${ctx}/sys/user/modifyCertsub">修改证书</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/modifyCertsub" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">旧证书:</label>
			<div class="controls">
				<input id="oldcertsub" name="oldcertsub" type="text" value="" maxlength="50" minlength="3" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">新证书:</label>
			<div class="controls">
				<input id="newcertsub" name="newcertsub" type="text" value="" maxlength="50" minlength="3" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认新证书:</label>
			<div class="controls">
				<input id="confirmNewCertsub" name="confirmNewCertsub" type="text" value="" maxlength="50" minlength="3" class="required" equalTo="#newcertsub"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>