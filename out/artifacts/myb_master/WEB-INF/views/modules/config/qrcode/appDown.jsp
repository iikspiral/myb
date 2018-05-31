<%--<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>密钥宝</title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .app-bg{
            position: fixed;
            left: 0px;
            top: 0px;
            bottom: 0px;
            right: 0px;
            z-index: -999;;

            margin: 0;
            padding: 0;

            display: -moz-box;
            display: -webkit-box;
            display: box;

            -moz-box-orient: horizontal;
            -webkit-box-orient: horizontal;
            box-orient:horizontal;

            -moz-box-pack: center;
            -webkit-box-pack: center;
            box-pack: center;

            -moz-box-align: center;
            -webkit-box-align: center;
            box-align: center;
            background: url("${ctxStatic}/images/app-bg.jpg") no-repeat center center;
            background-size: cover;

            height: 100%;
            width: 100%;

        }
        .app-0{
            width:816px;
            height:696px;
            position: fixed;
            top:50%;
            left:50%;
            margin-top: -348px;
            margin-left: -408px;
            background: url("${ctxStatic}/images/app-1.png") top center no-repeat;
        }
        .app-scan{
            width:220px;
            height: 220px;
            position: relative;
            top: 106px;
            left:504px;
            /*background: #000;*/
        }
    </style>
</head>
<body>
<div class="app-0">
    <div class="app-scan">
        <img src="../../res/mobilefiles/imag.png" width="220px" height="220px">
    </div>
</div>
<div class="app-bg"></div>
</body>
</html>