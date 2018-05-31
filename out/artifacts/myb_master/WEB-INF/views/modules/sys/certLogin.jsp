<%@ page contentType="text/html;charset=UTF-8" import="java.security.cert.X509Certificate"%>
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
</head>
<body>
<div class="container" style=" ">
    <div class="Con_left">
        <div class="Con_L_text">Copyright @ 2012-2016 密钥宝服务平台 - Powered By 山西CA V1.0</div>
    </div>
    <div class="Con_right">
        <div class="loading">
            <div class="Pass_key Position">
                <div class="Titie_head">登录</div>
                <div class="loading_done loading_box" >
                    <div class="loading_Pic">
                        <img src="${ctxStatic}/myb/image/Key/done_loading.png" alt="">
                    </div>
                    <p class="loading_test">检测到数字证书，请点击按钮登录</p>
                    <input class="BTN" type="button" value="立即登录">
                </div>
                <div class="loading_undone loading_box" style="display: none">
                    <div class="loading_Pic">
                        <img src="${ctxStatic}/myb/image/Key/undone_loading.png" alt="">
                    </div>
                    <p class="loading_test loading_untest">未检测到数字证书，请插入KEY</p>
                    <input class="BTN BTN_undone" type="button" value="立即登录">
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('.BTN').click(function () {
            $('.loading_undone').show();
            $('.loading_done').hide();
        })
    })
</script>
</body>
</html>