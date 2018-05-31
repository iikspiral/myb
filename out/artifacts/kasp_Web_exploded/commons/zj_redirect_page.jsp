<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> </title> 
	</head>
	<body>
			<form id="onlinepayForm" name="onlinepayForm" action="${action}" method="post" style="display:none">
				<!-- 中金参数  -->	
					<input	type="hidden" 		name="txName" 		value="${txName}"/>
					<input	type="hidden" 		name="message" 		value="${message}"/>
					<input	type="hidden" 		name="txCode" 		value="${txCode}"/>
					<input	type="hidden" 		name="signature" 	value="${signature}"/>
			</form>
	</body>
	<script type="text/javascript">
		
			document.getElementById("onlinepayForm").submit();
  	</script>
</html>



