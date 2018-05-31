<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>查看详情</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" type="text/css" media="all" href="${ctxStatic}/myb/css/State_style.css" />
</head>
<body>
    <!--页面开始-->
    <div class="state_body">
        <div class="state_top">
            <div class="state_top_ico">
                <img src="${ctxStatic}/myb/image/Error_ico.gif" />
            </div>
            <div class="state_top_font">
                <div class="state_top_prompt">
                    <img src="${ctxStatic}/myb/image/Error_font.gif" />
                </div>
                <div class="state_top_explain">错误码：${errCode}</div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="error_bottom">
            <b>错误信息：</b>${errMsg}
        </div>
    </div>
    <!--页面结束-->
</body>
</html>
