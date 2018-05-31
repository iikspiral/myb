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
						required: true,
						remote: "${ctx}/strategy/checkUnique?timestamp=" + new Date().getTime()
					}
				},
				messages: {
					strategy: {
						remote: "策略已存在",
					} 
				},
				submitHandler : function(form) {
					var param = $("#inputForm").serialize(); 
				     $.ajax({ 
						url : "${ctx}/strategy/save", 
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
		<form:form id="inputForm" modelAttribute="platformStrategy" 
			class="form-search" style="padding-left:20px;"><br/><br>
			<label>策略:</label>
			<form:select id="strategy" path="strategy" cssStyle="width:220px" >
				<form:option value="" label="请选择" />
				<form:options items="${fns:getDictList('strategy_type') }" itemLabel="label" itemValue="value"/>
			</form:select><br><br>
			<label>描述:</label>
			<form:textarea id="remarks" path="remarks" htmlEscape="true"/><br><br>
			<div style="padding-left:145px;">
				<input type="submit" value="保存" class="btn btn-primary">
			</div>
		</form:form>
	</div>
</body>
</html>
