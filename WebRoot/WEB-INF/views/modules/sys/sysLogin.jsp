<%@ page contentType="text/html;charset=UTF-8" import="java.security.cert.X509Certificate"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,IE=11,IE=10,IE=9,IE=8">
    <title>秘钥管家登录</title>
    <link rel="stylesheet" href="${ctxStatic}/sts/css/index.css">
    <link rel="stylesheet" href="${ctxStatic}/sts/css/common.css">
    <script src="${ctxStatic}/myb/js/jquery-1.11.3.min.js"></script>
    <script src="${ctxStatic}/myb/js/loginValidate.js"></script>
    <style type="text/css">
		#app {font-size:11px;color:#999;}
		#app:hover {font-size:13px;}
		#appDemo {font-size:11px;color:#999;}
		#appDemo:hover {font-size:13px;}
	</style>
     <%
      String dn ="";
	  String errmsg="";
      java.security.cert.X509Certificate[] certs=null;
      try{
          certs=(X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate");
          if (certs == null) {
          	errmsg="No certificates";
          } else if (certs.length == 0) {
          	errmsg="Certificates length is 0";
          } else {
                  java.security.cert.X509Certificate cert = certs[0];
                  dn = cert.getSubjectX500Principal().getName();
          }
          System.out.println(errmsg+"::"+dn);
      } catch(Exception e){
	  		errmsg="Exception=" + e.getMessage();
      }
      %>
</head>
<body>
    <div class="container" style=" background: url(${ctxStatic}/myb/image/bg1.png) top left no-repeat;">
        <div class="Con_left">
            <!-- <div class="Con_L_text">Copyright @ 2012-2016 密钥宝服务平台 - Powered By 山西CA V1.0</div> -->
            <div class="Con_L_text">${fns:getCopyRight()}</div>
        </div>
        <div class="Con_right">
          <!-- <div id="icon_change" style="display: block;"></div>  -->
            <div class="loading">
                <!-- -------------------------------------------- --> 
	         	   <div class="Pass_key Position ">
	                <form id="certloginForm"  action="${ctx}/login" method="post">
	                    <div class="Titie_head">证书登录</div>
	                    <div class="loading_done loading_box" >
	                         <div class="loading_Pic">
	                            <img src="${ctxStatic}/myb/image/Key/done_loading.png" alt="">
	                        </div> 
	       					<c:if test="${empty zsmessage}">
		                        <div class="loading_test">
		       						 请插入数字证书
	                       	    </div>
                   			</c:if>
            	     		<c:if test="${not empty zsmessage}">
		                    	<div class="loading_error" >
		                   				${zsmessage}
		                    	</div>
                   			</c:if>                 
                   			<!-- ------------------------  -->
                   			
	                        <input type="hidden" name="username" id="certsubject">
	                        <input class="BTN" type="button" onclick="loginbycert();" value="立即登录">
	                        <div style="margin:5px 0 0 0;padding:2px 0 0 0;text-align:center;">
		                    	<a href="${ctxf}/qrcode/createQRcode" target="_blank" id="app">app下载</a>&nbsp;&nbsp;
	                       		<a href="${ctxf}/qrcode/createQRcode" target="_blank" id="appDemo">appDemo下载</a>
	                    	</div>
	                    </div>
					</form>
                </div> 
                <!-- -------------------------------------------- -->
                 
                <!-- -------------------------------------------- -->  
            </div>
        </div>
    </div>
<script>
        $(function () {
    	   
           $('#icon_change').click(function () {
                //判断display属性
               if($('.Pass_key').hasClass('Display')){
                   $('.Pass_key').fadeIn(200,function () {
                       $('.Pass_word').fadeOut(200);
                       $(this).removeClass('Display')
                   });
                   $(this).css({background:"url(${ctxStatic}/sts/image/Key/icon_Pass_key.png)"});
                   document.cookie="name=Pass_key";
                   tabclick();
               }else{
                   $('.Pass_word').fadeIn(200,function () {
                       $('.Pass_key').fadeOut(200);
                   });
                   $('.Pass_key').addClass('Display');
                   $(this).css({background:"url(${ctxStatic}/sts/image/Word/icon_Pass_word.png)"});
                   document.cookie="name=Pass_word";
                   tabclick();
               }
           })
            $('.BTN').click(function () {                
        	   $('.loading_undone').show();
               $('.loading_done').hide();
        	   $('.loading_undone').show();
               $('.loading_done').hide();
           }) 
           //文本框

       }) 
       
        function loginbycert(){
			document.execCommand('ClearAuthenticationCache');
			var urlhref = window.location.href;//完整的地址栏地址
			var protocol = window.location.protocol;
			urlhref = urlhref.replace(protocol,"https:");
			urlhref = urlhref.replace('${httpport}','${httpsport}');
			window.location.href = urlhref;
		} 
       
    // 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
		
		window.onload = function() { 
			document.getElementById("certsubject").value = '<%=dn%>';
			var protocol=window.location.protocol;
			if(protocol=="https:"){
				var pri='${principal}';
				var fail='${certloinfail}';
				var msg='${message}';
				if($('#certsubject').val()!=null&&$('#certsubject').val()!=''){
					if(pri!=null&&pri!=''){
						$('#certloginForm').submit();
					}else{
						if(fail=="0"){
							if(msg!='认证失败，请稍后再试！'&&msg!='用户不存在'){
								window.opener=null;
								window.close();
							}
						}else{
							$('#certloginForm').submit();
						}
					}
			    }else{
			    	window.opener=null;
					window.close();
			    }
			}
		};
</script>
</body>
</html>