<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面报错</title>
<link rel="stylesheet" href="${staticserver}/common/css/ibase.css">
<link rel="stylesheet" href="${staticserver}/common/css/common.css">    
<link rel="stylesheet" href="${staticserver}/common/css/new_user.css">
<link rel="stylesheet" type="text/css" href="${staticserver}/common/css/5istyle.css">
<link rel="stylesheet" type="text/css" href="${staticserver}/common/css/kd.ui.css">
<link rel="stylesheet" type="text/css" href="${staticserver}/common/css/kd.ui.plugin.css">
</head>
<body>
 
  
<div align="center">
	<div class="kd-outer-box" style="height: 100px">
		<div class="kd-outer-box-title">
			<h>页面报错</h>
		 <div>
        <div>
        <!-- [/commons/error.jsp] -->
         	   页面报错了！
        </div>
        
        <div>
       系统将会在 5 秒后自动跳回, 或者您手动<a href="index.html">点击此处</a>返回产品页  
        </div>
  
  </div>
</div>
  <script type="text/javascript">
  window.onload = function() {
  		setTimeout(function() {
  				window.location.href="${staticserver}/../order_payment.html";
  },5000);
   }
  </script>
</body>
</html>



