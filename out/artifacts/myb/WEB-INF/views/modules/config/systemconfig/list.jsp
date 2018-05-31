<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript">

 function checkUnique(){
	var loginType = $("input[name='loginType']:checked").val();
	if(loginType == "1"){
	    $.ajax({ 
			url : "${ctx}/systemconfig/checkUnique?loginType="+loginType+"&id="+$("#id").val(), 
			type : "post", 
			success : function(result) { 
				if(result){
					$("#inputForm").submit();
				}else{
		        	$(":radio[name='loginType'][value='0']").prop("checked", "checked")
		        	$.jBox.tip('请确认您的用户已绑定【证书】后重试！'); 
				        
				}
			} 
	     }); 
	}else{
		$("#inputForm").submit();
	}
	 
}
$(document).ready(function() {
	
	$("#inputForm")
		.validate(
		{
				rules: {
					systemName:{
						required: true
					},
					copyRightInfo:{
						required: true
					},
					phone:{
						required: true
					},
					loginType:{
						required: true
					},
					conversationDate:{
						digits:true,
						min:0
					}
				},
				messages: {
					systemName: {
					},
					copyRightInfo:{
					},
					phone:{
					},
					loginType:{
					},
					conversationDate:"时间只能输入非负整数或整数"
				},
				submitHandler : function(form) {
					loading('正在提交，请稍等...');
					form.submit();
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

</script>
</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="systemConfig"
		action="${ctx}/systemconfig/save" method="post" class="form-horizontal">
			<form:hidden path="id" id="id" />
			<sys:message content="${message}" />
			
			<div class="control-group">
			<div class="control-group">
				<label class="control-label">系统名称:</label>
				<div class="controls">
					<form:input path="systemName" htmlEscape="false" rows="3"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">版权信息:</label>
				<div class="controls">
					<form:input path="copyRightInfo" id="copyRightInfo" htmlEscape="false" rows="3"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">客服电话:</label>
				<div class="controls">
					<form:input path="phone" id="phone" htmlEscape="false" rows="3"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">登录方式:</label>
				<div class="controls">
				<form:radiobuttons path="loginType" items="${fns:getDictList('login_Type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">启用默认项目:</label>
				<div class="controls">
				<form:radiobuttons path="isuseDefaultProject" items="${fns:getDictList('isuse_DefaultProject')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否开启人脸识别:</label>
				<div class="controls">
				<form:radiobuttons path="isFace" items="${fns:getDictList('is_usedface')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				</div>
			</div>	
			<div class="control-group">
				<label class="control-label">手机会话时间(小时):</label>
				<div class="controls">
					<form:input path="conversationDate" id="conversationDate" htmlEscape="false" rows="3"
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
		<div class="form-actions">
		<aclass="btn btn-success" type="button" onclick="checkUnique()" class="btn btn-primary" rel="popover" title="系统配置请谨慎保存，【证书登录】请先绑定证书！" 
		data-content="">
		<iclass=" icon-white icon-share">
		</i> 保存</a>
		</div>
		</form:form>
	</div>
</body>
</html>
