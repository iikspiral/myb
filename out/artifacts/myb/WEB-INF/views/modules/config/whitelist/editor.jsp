<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript">


$(document).ready(function() {
	
	var value =$("#optType").val();
	/* alert(value); */
	/* var value = $('#optType option:selected') .val(); */
	if(value==0) {
		$('#user').show();
		$('#certsn').hide();
	}else {
		$('#user').hide();
		$('#certsn').show();
	}

	
	
	
	$("#inputForm")
		.validate(
		{
				rules: {
					optType:{
						required: true
					},
					projectInfoId:{
						required: true
					},
					isUsed:{
						required: true
					},
					loginType:{
						required: true
					},
					userInfoId:{
						required: true
					},
					certSn:{
						required: true
					}
				},
				messages: {
					optType: {
					},
					projectInfoId:{
					},
					isUsed:{
					},
					loginType:{
					},
					userInfoId:{
					},
					certSn:{
					}
				},
				submitHandler : function(form) {
					var optType = $("#optType").val();
					var projectInfoId = $("#projectInfoId").val();
					var userInfoId = $("#userInfoId").val();
					var remarks = $("#remarks").val();
					var certSubject = $("#certSubject").val();
					var certSn = $("#certSn").val();
					var id = $("#id").val();
					 $.ajax({ 
							url : "${ctx}/white/decide?userInfoId="+userInfoId+"&projectInfoId="+projectInfoId+"&optType="+optType+"&id="+id+"&remarks="+remarks+"&certSn="+certSn+"&certSubject="+certSubject, 
							type : "post", 
							success : function(result) { 
								if(result) {
									loading('正在提交，请稍等...');
									form.submit();
								}else if(optType== '0'){
									$.jBox.tip("该项目下用户已存在或者存在证书,请重新选择!!!");
									return;
								}else if(optType=='1'){
									$.jBox.tip("该证书已存在,请重新选择!!!");
									return;
								}
							} 
					     }); 
					
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
	<form:form id="inputForm" modelAttribute="whiteList"
		action="${ctx}/white/save?type=1" method="post" class="form-horizontal">
			<form:hidden path="id" id="id" />
			<form:hidden path="isUsed" id="isUsed" />
			<%-- <form:hidden path="certSubject" id="certSubject" /> --%>
			<sys:message content="${message}" />
			
			<!-- <div class="control-group"> -->
			<div class="control-group">
				<label class="control-label">业务类型:</label>
				<div class="controls">
				<form:select id="optType" path="optType" cssStyle="width:284px" disabled="true" >
					<form:options items="${fns:getDictList('opt_type') }" itemLabel="label" itemValue="value"/>
				</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">项目选择:</label>
				<div class="controls">
					<form:select id="projectInfoId" path="projectInfoId" cssStyle="width:284px" value="${projectName}">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getProjectInfoList()}" itemLabel="projectName" itemValue="id"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div id="user" class="control-group">
				<label class="control-label">用户修改:</label>
				<div class="controls">
				<form:select id ="userInfoId" path="userInfoId" cssStyle="width:284px" value="${userInfoName}">
						<form:option value="" label="请选择用户" />
						<form:options items="${fns:getUserInfoList()}" itemLabel="username" itemValue="id"/>
				</form:select>	
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div id="certsn" style="display: none" class="control-group" >
				<label class="control-label">证书修改:</label>
				<div class="controls">
				<form:select id="certSn" path="certSn" cssStyle="width:284px"  >
						<form:option value="" label="请选择证书" />
						<form:options items="${fns:getCertInfoList('userinfo')}" itemLabel="certSubject" itemValue="certSn"/>
				</form:select>	
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">申请证书主题:</label>
				<div class="controls">
					<form:input path="certSubject" htmlEscape="false" class="input-xlarge" readonly="true"/>
				</div>
			</div>

			<div class="form-actions">
				<input type="submit" value="保存" class="btn btn-primary">&nbsp;
				<input id="btnCancel" class="btn btn-primary" type="button"	value="返 回" onclick="javascript:window.location.href='${ctx}/white/list?clickType=2'"  />
			</div>
		</form:form>
	</div>
</body>
</html>
