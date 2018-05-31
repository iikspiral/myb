<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript">

$(document).ready(function() {
	
	$("#inputForm")
		.validate(
		{
				rules: {
					systemName:{
						required: true,
						maxlength:50,
						remote: "${ctx}/application/checkUnique?id="+$("#id").val()
					},
					systemFlag:{
						required: true,
						minlength:1,
						maxlength:128
					}
				},
				messages: {
					systemName:{
						minlength:"输入系统名称太短",
						remote:"系统名称已存在"
					},
					systemFlag:{
						minlength:"输入应用标识太短"
					}
				},
				submitHandler : function(form) {
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
});

function prorhChange(){
	$("#country").empty();
	
	var parentid = $('#province option:selected').val();
	$.ajax({
		url : "${ctx}/application/rhChange?parentid="+parentid,
		type : "post",
		success : function(result){
			$("#city").empty();
			$("#city").append(result); 
		}
	});
	
}

function cityChange(){
	
	var parentid = $('#city option:selected').val();
	$.ajax({
		url : "${ctx}/application/rhChange?parentid="+parentid,
		type : "post",
		success : function(result){
			$("#country").empty();
			$("#country").append(result); 
		}
	});
	
}

function countryChange(){
	var Num = MathRand();
	var province = $('#province option:selected').val();
	var city = $('#city option:selected').val();
	var country = $('#country option:selected').val();
	if(country != null && country != ""){
		$("#systemFlag").val(province+city+country+Num);
	}else{
		$("#systemFlag").val("");
	}
}
function MathRand() { 
	var Num=""; 
	for(var i=0;i<6;i++) 
	{ 
	Num+=Math.floor(Math.random()*10); 
	} 
	return Num;
} 
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/application/list?clickType=2">系统列表</a></li>
		<li class="active"><a href="${ctx}/application/form">系统添加</a></li>
	</ul>
	
	
	<form:form id="inputForm" modelAttribute="applicationSystem"
		action="${ctx}/application/save" method="post" class="form-horizontal">
		<form:hidden path="id" id ="id"/>
		<sys:message content="${message}" />
		<div class="control-group">
			<div class="control-group">
				<label class="control-label">省:</label>
				<div class="controls">
					<form:select id="province" path="province" cssStyle="width:282px" onchange="prorhChange()" class="required">
						<form:option value="" label="请选择" />
						<form:options items="${areaList}" itemLabel="name" itemValue="baseAreaid"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>	
						<div class="control-group">
				<label class="control-label">市:</label>
				<div class="controls">
					<form:select id="city" path="city" cssStyle="width:282px" onchange="cityChange()" class="required">
						<form:option value="" label="请选择" />
						<form:options items="${cityArealist}" itemLabel="name" itemValue="baseAreaid"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>	
			<div class="control-group">
				<label class="control-label">县:</label>
				<div class="controls">
					<form:select id="country" path="country" cssStyle="width:282px" onchange="countryChange()" class="required">
						<form:option value="" label="请选择" />
						<form:options items="${countryArealist}" itemLabel="name" itemValue="baseAreaid"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>	
			<div class="control-group">
				<label class="control-label">系统名称:</label>
				<div class="controls">
					<form:input path="systemName" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">应用标识:</label>
				<div class="controls">
					<form:input id="systemFlag" path="systemFlag" htmlEscape="false" rows="3" cssStyle="width:270px" readonly="true"
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
			onclick="javascript:window.location.href='${ctx}/application/list?id=${id}'"/>
		</div>
	</form:form>
	
	
</body>
</html>
