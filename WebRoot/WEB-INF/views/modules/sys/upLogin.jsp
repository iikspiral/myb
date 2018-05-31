<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,IE=11,IE=10,IE=9,IE=8">
    <title>秘钥管家登录</title>
    <link rel="stylesheet" href="${ctxStatic}/myb/css/index.css">
    <link rel="stylesheet" href="${ctxStatic}/myb/css/common.css">
    <script src="${ctxStatic}/myb/js/jquery-1.8.3.js"></script>
    <script src="${ctxStatic}/myb/js/loginValidate.js"></script>
    <style type="text/css">
		#app {font-size:11px;color:#999;}
		#app:hover {font-size:13px;}
		#appDemo {font-size:11px;color:#999;}
		#appDemo:hover {font-size:13px;}
	</style>
</head>
<body>
    <div class="container" style=" background: url(${ctxStatic}/myb/image/bg1.png) top left no-repeat;">
        <div class="Con_left">
            <!-- <div class="Con_L_text">Copyright @ 2012-2016 密钥宝服务平台 - Powered By 山西CA V1.0</div> -->
            <div class="Con_L_text">${fns:getCopyRight()}</div>
        </div>
        <div class="Con_right">
            <div class="loading">
                <div class="Pass_word Position">
                	<form id="loginForm"  action="${ctx}/login" method="post">
	                    <div class="Titie_head">登录</div>
	                    <div class="user">
	                        <div class="icon_image icon_image_user"></div>
	                        <input type="text" class="Pass_test username"  id="username" name="username" autocomplete="off" >
	                    </div>
	                    <div class="pwd">
	                        <div class="icon_image icon_image_pass"></div>
	                        <input type="password" id="password" name="password" class="Pass_test password" >
	                    </div>
	                    <c:if test="${empty message}">
                   			<div id="msg1" class="loadingMassage_word_kong" >
                   			</div>
                		</c:if>
                   		<c:if test="${not empty message}">
			                 <div id="msg1" class="loadingMassage_word" >
		                   				${message}
		                     </div>
                   		</c:if> 
	                    <input class="BTN" type="submit" onclick="return validateEmpty()" value="立即登录"><br>
	                    <div style="margin:5px 0 0 0;padding:2px 0 0 0;text-align:center;">
	                    	<a href="${ctxf}/qrcode/createQRcode?type=app" target="_blank" id="app">app下载</a>&nbsp;&nbsp;
                       		<a href="${ctxf}/qrcode/createQRcode?type=appDemo" target="_blank" id="appDemo">appDemo下载</a>
	                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>