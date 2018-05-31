<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript">

function isIP(num){
	var isIDIP=/^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}$/;
	var re = new RegExp(isIDIP);
	if (re.test(num)) {
        return true;
    }else{
       return false;
    }
}




$(document).ready(function() {
	
	jQuery.validator.addMethod("isIP", function (value, element){
        return this.optional(element) || isIP(value);
     },"请输入正确的IP地址");
	
	$("#inputForm")
		.validate(
		{
				rules: {
					deviceNameeng:{
						required: true,
						minlength:3,
						maxlength:50
					},
					deviceIp:{
						/* required: true, */
						isIP : true
						/* minlength:9,
						maxlength:50 */
					},
					devicePort:{
						required: true,
						digits: true,
						minlength:2,
						maxlength:50
						
					}
				},
				messages: {
					deviceNameeng: {
						minlength:"输入设备名称太短"
					},
					deviceIp:{
						minlength:"输入设备IP地址不符合要求",
					},
					devicePort:{
						minlength:"输入设备端口不合法",
							digits:"只能输入端口号"
					}
				},
				submitHandler : function(form) {
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
});

</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/device/list?clickType=2">设备列表</a></li>
		<li class="active"><a href="${ctx}/device/form">设备添加</a></li>
	</ul>
	
	
	<form:form id="inputForm" modelAttribute="deviceInfo"
		action="${ctx}/device/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}" />
		<div class="control-group">
			<div class="control-group">
				<label class="control-label">设备名称:</label>
				<div class="controls">
					<form:input path="deviceNameeng" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600"  class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">设备 IP:</label>
				<div class="controls">
					<form:input path="deviceIp" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">设备端口:</label>
				<div class="controls">
					<form:input path="devicePort" htmlEscape="false" rows="3" cssStyle="width:270px"
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
			<input type="submit" value="保存" class="btn btn-primary">&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button"	value="返 回" 
			onclick="javascript:window.location.href='${ctx}/device/list?id=${id}'"/>
		</div>
	</form:form>
	
	
</body>
</html>
