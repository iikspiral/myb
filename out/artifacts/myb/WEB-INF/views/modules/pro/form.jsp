<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>对象表单</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	$("#inputForm")
	.validate(
	{
			rules: {
				projectNum:{
					remote: "${ctx}/projectinfo/checkUniquePronum?id="+$("#id").val()
				},
				certValidity:{
					digits:true,
					min:0
				},
				allowUpdateDay:{
					digits:true,
					min:0
				},
				makeCertRules:{
					remote:"${ctx}/projectinfo/checkMakecertRules?id="+$("#id").val()
				}
			},
			messages: {
				projectNum:{
					remote: "项目编号已存在"
				},
				certValidity:"只能输入非负整数或整数",
				allowUpdateDay:"只能输入非负整数或整数",
				makeCertRules:"制证规则不能修改"
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
function checkUnique(){
	var isDefaultProject = $("input[name='isDefaultProject']:checked").val();
	if(isDefaultProject == "0"){
	    $.ajax({ 
			url : "${ctx}/projectinfo/checkUnique?isDefaultProject="+isDefaultProject+"&id="+$("#id").val(), 
			type : "post", 
			success : function(result) { 
				if(result){
					$("#inputForm").submit();
				}else{
					alert("默认项目已存在!!!");
					 $(":radio[name='isDefaultProject'][value='1']").prop("checked", "checked")
				}
			} 
	     }); 
	}else{
		$("#inputForm").submit();
	}
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/projectinfo/list?clickType=2">项目列表</a></li>
		<li class="active"><a href="${ctx}/projectinfo/form?id=${projectInfo.id}">项目${not empty projectInfo.id?'编辑':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="projectInfo"
		action="${ctx}/projectinfo/save" method="post" class="form-horizontal">
		<form:hidden path="id" id="id" />
		<form:hidden path="isHoldKey" id="isHoldKey" />
		<sys:message content="${message}" />
		<div class="control-group">
			<div class="control-group">
				<label class="control-label">项目编号:</label>
				<div class="controls">
					<form:input path="projectNum" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">项目名称:</label>
				<div class="controls">
					<form:input path="projectName" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>			
			<div class="control-group">
				<label class="control-label">证书模板:</label>
				<div class="controls">
					<form:select path="certTemplate" cssStyle="width:282px" class="required">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getCertctmlList()}" itemLabel="certCtmlName" itemValue="id"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>				
			<div class="control-group">
				<label class="control-label">制证规则:</label>
				<div class="controls">
					<form:textarea id="makeCertRules" htmlEscape="true" path="makeCertRules" rows="2"  class="input-xxlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>				
			<div class="control-group">
				<label class="control-label">自定义扩展域规则:</label>
				<div class="controls">
					<form:textarea id="selfExtRules" htmlEscape="selfExtRules" path="selfExtRules" rows="2"  class="input-xxlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>				
			<div class="control-group">
				<label class="control-label">标准扩展域规则:</label>
				<div class="controls">
					<form:textarea id="standardExtRules" htmlEscape="standardExtRules" path="standardExtRules" rows="2"  class="input-xxlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>				
			<div class="control-group">
				<label class="control-label">证书有效期(天):</label>
				<div class="controls">
					<form:input path="certValidity" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否默认项目:</label>
				<div class="controls">
					<form:radiobuttons path="isDefaultProject" items="${fns:getDictList('is_required')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				</div>
			</div>				
			<div class="control-group">
				<label class="control-label">允许更新条件(天):</label>
				<div class="controls">
					<form:input path="allowUpdateDay" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>			
		</div>
		<div class="form-actions">
			<input type="button" value="保存" onclick="checkUnique()" class="btn btn-primary">&nbsp;&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button"	value="返 回" onclick="window.location.href='${ctx}/projectinfo/list'" />
		</div>
	</form:form>
</body>
</html>
