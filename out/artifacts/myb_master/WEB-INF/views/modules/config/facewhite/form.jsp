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
	$("#inputForm")
	.validate(
	{
			rules: {
				idCardNum:{
					remote: "${ctx}/facewhite/checkUnique?id="+$("#id").val(),
					isIdCardNo : true
				}
			},
			messages: {
				idCardNum:{
					remote: "身份证号已存在"
				}
			},
			submitHandler : function(form) {
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/facewhite/list?clickType=2">人脸白名单列表</a></li>
		<li class="active"><a href="${ctx}/facewhite/form?id=${facewhite.id}">人脸白名单${not empty facewhite.id?'编辑':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="facewhite"
		action="${ctx}/facewhite/save" method="post" class="form-horizontal">
		<form:hidden path="id" id="id" />
		<form:hidden path="isEffect" id="isEffect" />
		<sys:message content="${message}" />
		<div class="control-group">
			<div class="control-group">
				<label class="control-label">身份证号:</label>
				<div class="controls">
					<form:input path="idCardNum" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">联系人姓名:</label>
				<div class="controls">
					<form:input path="userName" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="input-xlarge"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">联系人电话:</label>
				<div class="controls">
					<form:input path="userPhone" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="input-xlarge"/>
				</div>
			</div>						
		</div>
		<div class="form-actions">
			<input type="submit" value="保存" class="btn btn-primary">&nbsp;&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button"	value="返 回" onclick="window.location.href='${ctx}/facewhite/list'" />
		</div>
	</form:form>
</body>
</html>
