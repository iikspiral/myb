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
					strategy:{
						required: true
					},
					webserviceType:{
						required: true,
// 						remote: "${ctx}/certsource/checkUnique?strategy="+${certSource.strategy}+"&timestamp=" + new Date().getTime()
					},
					address:{
						required: true
					}
				},
				messages: {
					strategy: {
					},
					webserviceType:{
// 						remote: "记录已存在",
					},
					address:{
					}
				},
				submitHandler : function(form) {
					var param = $("#inputForm").serialize(); 
				     $.ajax({ 
						url : "${ctx}/certsource/save", 
						type : "post", 
						data: param, 
						success : function(result) { 
							if(result=="OK") {
								parent.location.reload();
							}
						} 
				     }); 
				}
			});
});

</script>
</head>
<body>
	<div id="inputBox">
		<form:form id="inputForm" modelAttribute="certSource" 
			class="form-search" style="padding-left:20px;"><br/><br>
			<form:hidden path="id" />
			<label>策略:</label>
			<form:select id="strategy" path="strategy" cssStyle="width:281px" >
				<form:option value="" label="请选择" />
				<form:option value="3" label="生产平台" />
				<%-- <form:options items="${fns:getDictList('strategy_type') }" itemLabel="label" itemValue="value"/> --%>
			</form:select><br><br>
			<label>类型:</label>
			<form:select id="webserviceType" path="webserviceType" cssStyle="width:281px" >
				<form:option value="" label="请选择" />
				<form:options items="${fns:getDictList('webservice_type') }" itemLabel="label" itemValue="value"/>
			</form:select><br><br>
			<label>地址:</label>
			<form:input path="address" htmlEscape="false" rows="3"
						minlength="0" maxlength="600" class="required input-xlarge"/><br><br>
			<label>方法:</label>
			<form:input path="method" htmlEscape="false" rows="3"
						minlength="0" maxlength="600" class="required input-xlarge"/><br><br>			
			<div style="padding-left:5px;">
				<label>描述:</label>
				<form:textarea id="remarks" path="remarks" htmlEscape="true" cssStyle ="width:266px"/><br><br>
			</div>
			
			<div style="padding-left:170px;">
				<input type="submit" value="保存" class="btn btn-primary">
			</div>
		</form:form>
	</div>
</body>
</html>
