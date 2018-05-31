<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	
	var type =$("#type").val();
	if(type == "0"){
		$("#div1").show();
		$("#div2").hide();
	}else{
		$("#div2").show();
		$("#div1").hide();	
	}
	
	$("#inputForm")
		.validate(
		{
				rules: {
				},
				messages: {
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

function save(){
	var id =$("#id").val();
	var projectId = $("#projectId").val();
	var type = $("#type").val();
	var corporUserRelaId = $("#corporUserRelaId").val();
	var certSn = $("#certSn").val();
	if(type == "0"){
		if(projectId == "" || projectId == null){
			alert("请选择项目!");
			return;
		}
		if(corporUserRelaId == "" || corporUserRelaId == null){
			alert("请选择企业!");
			return;
		}
	    $.ajax({ 
			url : "${ctx}/corporcode/checkUniqueCorpuser?id="+id+"&corporUserRelaId="+corporUserRelaId+"&projectId="+projectId, 
			type : "post", 
			success : function(result) { 
				if(result){
					$("#inputForm").submit();
				}else{
					 alert("该项目下企业用户已存在或者存在证书,请重新选择!!!");
					 return;
				}
			} 
	     }); 
	}else if(type == "1"){
		if(certSn == "" || certSn == null){
			alert("请选择证书");
			return;
		}
		$.ajax({
			url : "${ctx}/corporcode/checkUniqueCert?id="+id+"&certSn="+certSn, 
			type : "post",
			success : function(result){
				if(result){
					$("#inputForm").submit();
				}else{
					alert("该证书已存在,请重新选择!!!");
					return;
				}
			}
		});
	}
}
</script>
</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="corporationRequestCode"
		action="${ctx}/corporcode/updateSave" method="post" class="form-horizontal">
			<form:hidden path="id" id="id" />
			<form:hidden path="status" id="status" />
			<form:hidden path="type" id="type" />
			<form:hidden path="applicant" id="applicant" />
			<form:hidden path="optTime" id="optTime" />
			<sys:message content="${message}" />
			
			<div class="control-group">
				<label class="control-label">业务类型:</label>
				<div class="controls">
				<form:select id="type" path="type" cssStyle="width:284px" disabled="true">
								<form:options items="${fns:getDictList('opt_type') }" itemLabel="label" itemValue="value"/>
				</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">项目名称:</label>
				<div class="controls">
				<form:select id="projectId" path="projectId" cssStyle="width:284px">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getProjectInfoList()}" itemLabel="projectName" itemValue="id" />
				</form:select>
				</div>
			</div>		
			<div class="control-group" id="div1">
				<label class="control-label">企业名称:</label>
				<div class="controls">
				<form:select id="corporUserRelaId" path="corporUserRelaId" cssStyle="width:284px">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getCorporUserHelist()}" itemLabel="corpUserHe" itemValue="corporationUserRelation.id"/>
				</form:select>
				</div>
			</div>
			<div class="control-group" id ="div2">
				<label class="control-label">证书名称:</label>
				<div class="controls">
				<form:select id="certSn" path="certSn" cssStyle="width:284px">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getCertInfoList('corporation_info') }" itemLabel="certSubject" itemValue="certSn"/>
				</form:select>
				</div>
			</div>
			<div class="form-actions">
				<input type="button" onclick="save()" value="保存" class="btn btn-primary">&nbsp;
				<input id="btnCancel" class="btn btn-primary" type="button"	value="返 回" onclick="history.go(-1)" />
			</div>
<!-- 			------------------ -->
			
		</form:form>
	</div>
</body>
</html>
