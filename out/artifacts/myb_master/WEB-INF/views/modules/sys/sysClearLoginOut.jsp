<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>用户退出</title>
	<script type="text/javascript">
		window.onload=function(){
			var urlhref = window.location.href;//完整的地址栏地址
			var protocol = window.location.protocol;
			urlhref = urlhref.replace(protocol,"http:");
			urlhref = urlhref.replace('${httpsport}','${httpport}');
			window.location.href = urlhref;
		}
	</script>
</head>
<body>
</body>
</html>