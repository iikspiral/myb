<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>对象表单</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
function changeShow() {
	var value = $('#dataType option:selected').val();
	if(value==0 && value != "") {
		$('#div_02').show();
		$('#div_03').show();
		$('#div_04').show();
		$('#div_01').hide();
	}else if(value==1 && value != ""){
		$('#div_02').hide();
		$('#div_03').hide();
		$('#div_04').show();
		$('#div_01').show();
	}
}
function checkUnique(){
	var userInfoType = $("#userInfoType").val();
	var attribute = $("#attribute").val();
	var id = $("#id").val();
	if(userInfoType != "" && userInfoType != null && attribute != "" && attribute != null){
	     $.ajax({ 
				url : "${ctx}/extrainfo/checkUnique?userInfoType="+userInfoType+"&attribute="+attribute+"&id="+id, 
				type : "post", 
				success : function(result) { 
					if(result){
						$("#inputForm").submit();
					}else{
						alert("该用户类型下对应属性已存在,请重新选择!!!");
					}
				} 
		     }); 
	}
}
$(document).ready(function() {
	changeShow();
	$("#inputForm")
	.validate(
	{
			rules: {
				userInfoType:{
					required: true
				},
				extraMean:{
					required: true
				},
				dataType:{
					required: true
				},
				dicValue:{
					required: true
				},
				checkValue:{
					required: true
				},
				filedLength:{
					required: true,
					digits:true,
					min:0
				},
				attribute:{
					required: true
				}
			},
			messages: {
				userInfoType:{
				},
				extraMean:{
				},
				dataType:{
				},
				dicValue:{
				},
				checkValue:{
				},
				filedLength:{
				},
				attribute:{
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
		<li><a href="${ctx}/extrainfo/list?clickType=2">用户扩展域列表</a></li>
		<li class="active"><a href="${ctx}/extrainfo/form?id=${extraInfo.id}">用户扩展域${not empty extraInfo.id?'编辑':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="extraInfo"
		action="${ctx}/extrainfo/save" method="post" class="form-horizontal">
		<form:hidden path="id" id="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<div class="control-group">
				<label class="control-label">用户类型:</label>
				<div class="controls">
					<form:select id="userInfoType" path="userInfoType" cssStyle="width:283px">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('user_info_type') }" itemLabel="label" itemValue="value"/>
					</form:select>						
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">代表含义:</label>
				<div class="controls">
					<form:input path="extraMean" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">字段类型:</label>
				<div class="controls">
					<form:select id="dataType" path="dataType" cssStyle="width:283px" onchange="changeShow()">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('field_type') }" itemLabel="label" itemValue="value"/>
					</form:select>	
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group" style="display: none" id="div_01">
				<label class="control-label">字典类型:</label>
				<div class="controls">
					<form:select id="dicValue" path="dicValue" cssStyle="width:283px">
						<form:option value="" label="请选择" />
						<form:options items="${typeList}" itemLabel="description" itemValue="type"/>
					</form:select>
				</div>
			</div>			
			<div class="control-group" style="display: none" id="div_02">
				<label class="control-label">默认值:</label>
				<div class="controls">
					<form:input path="defaultValue" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="input-xlarge"/>
				</div>
			</div>
			<div class="control-group" style="display: none" id="div_03">
				<label class="control-label">验证:</label>
				<div class="controls">
					<form:select id="checkValue" path="checkValue" cssStyle="width:283px">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('check_type') }" itemLabel="label" itemValue="value"/>
					</form:select>	
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>		
			<div class="control-group" style="display: none" id="div_04">
				<label class="control-label">长度:</label>
				<div class="controls">
					<form:input path="filedLength" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
				</div>
			</div>
								
			<div class="control-group">
				<label class="control-label">对应属性:</label>
				<div class="controls">
					<form:select id="attribute" path="attribute" cssStyle="width:283px">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('extra_type') }" itemLabel="label" itemValue="value"/>
					</form:select>						
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>			
			<div class="control-group">
				<label class="control-label">是否必填:</label>
				<div class="controls">
					<form:radiobuttons path="isRequired" items="${fns:getDictList('is_required')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
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
			<input type="button" value="保存" onclick="checkUnique()" class="btn btn-primary">&nbsp;&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button"	value="返 回" onclick="window.location.href='${ctx}/extrainfo/list'" />
		</div>
	</form:form>
</body>
</html>
