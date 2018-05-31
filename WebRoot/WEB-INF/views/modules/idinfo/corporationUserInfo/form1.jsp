<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>对象表单</title>
<meta name="decorator" content="default" />
<script type="text/javascript">	 

		function isIdCardNo(num){
			var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
			var re = new RegExp(isIDCard1);
			if (re.test(num)) {
		        return true;
		    }else{
		       return false;
		    }
		}




		$(document).ready(function() {
			
			jQuery.validator.addMethod("isIdCardNo", function (value, element){
		        return this.optional(element) || isIdCardNo(value);
		     },"请正确输入您的身份证号码");
			
			$("#inputForm")
			.validate(
			{
					rules: {
						userName:{
							required: true
						},
						idCard:{
							required: true,
							remote: "${ctx}/corpUserInfo/checkUnique?oldIdCard=" + encodeURIComponent('${corporationUserInfo.idCard}'),
							isIdCardNo : true
						},
						phoneNum:{
							required: true
						}
					},
					messages: {
						userName: {
						},
						idCard:{
							remote: "身份证号已存在"
						},
						phoneNum:{
						}
					},
					submitHandler : function(form) {
						var userName = $('#userName').val();
						var idCard = $('#idCard').val();
						var id = $('#id').val();
						if(id=="") {
							$.ajax({
								url : "${ctx}/corpUserInfo/checkUserName?userName="+encodeURIComponent(userName)+"&idCard="+idCard,
								type : "post",
								success : function(result) {
									if(result=="false") {
										var message ="【密钥宝服务平台】提示：\r\n姓名不同,请确保与个人用户中身份证号相同的用户姓名一致.\r\n谢谢！"
										alert(message);
										return;
									}else {
										loading('正在提交，请稍等...');
										form.submit();
									}
								}
							});
						}else {
							loading('正在提交，请稍等...');
							form.submit();
						}
					},
					errorContainer : "#messageBox",
					errorPlacement : function(error, element) {
						$("#messageBox").text("输入有误，请先更正。");
						if (element.is(":checkbox")
								|| element.is(":radio")
								|| element.parent().is(
										".input-append")) {
							error.appendTo(element.parent()
									.parent());
						} else {
							error.insertAfter(element);
						}
					}

				});
			
		});
		
		function saveCor(){
	 		$("#inputForm").submit();
		}
</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="corporationUserInfo"
		action="${ctx}/corpUserInfo/save1" method="post" class="form-horizontal">
		<form:hidden path="id" id="id"/>
		<input id="corporationId" name="corporationId" type="hidden" value="${corporationId}"/>
		<sys:message content="${message}" />
		<div class="control-group">
			<div class="control-group">
				<label class="control-label">用户名:</label>
				<div class="controls">
					<form:input path="userName" htmlEscape="false" rows="3"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">身份证号:</label>
				<div class="controls">
					<input id="oldIdCard" name="oldIdCard" type="hidden" value="${corporationUserInfo.idCard}">
					<form:input path="idCard" htmlEscape="false" rows="3"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">手机号:</label>
				<div class="controls">
					<form:input path="phoneNum" htmlEscape="false" rows="3"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注:</label>
				<div class="controls">
					<form:textarea id="remarks" htmlEscape="true" path="remarks" rows="4" maxlength="4000" class="input-xxlarge"/>
				</div>
			</div>
		</div>
		<!-- <div class="form-actions"> -->
		<div style="text-align: center; width: 90%;margin-top: 30px;">
			<input type="submit" value="保存" class="btn btn-primary">&nbsp;
			<%-- <a href="${ctx}/corpUserInfo/list" id="btnCancel" class="btn btn-primary">返 回</a> --%>
			<%-- <input id="btnCancel" class="btn btn-primary" type="button"	value="返 回" onclick="window.location.href='${ctx}/corpUserInfo/list'" /> --%>
			<input id="btnCancel" class="btn btn-primary" type="button"	value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>
