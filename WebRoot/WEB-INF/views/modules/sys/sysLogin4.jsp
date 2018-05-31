<%@ page contentType="text/html;charset=UTF-8" import="java.security.cert.X509Certificate"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,IE=11,IE=10,IE=9,IE=8">
    <title>秘钥管家登录</title>
    <link rel="stylesheet" href="${ctxStatic}/sts/css/index.css">
    <link rel="stylesheet" href="${ctxStatic}/sts/css/common.css">
    <script src="${ctxStatic}/sts/js/jquery-1.8.3.js"></script>
    <link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<style type="text/css">
	      html,body,table{background-color:#f5f5f5;width:100%;text-align:center;}.form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}
      .form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;
        	-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      .header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
      .header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
		.alert {
		    position: relative;
		    width: 300px;
		    margin: 0 auto;
		}		
		.alert {
		    padding: 13px 35px 8px 14px;
		    margin-b ottom: 20px;
		    text-shadow: 0 1px 0 rgba(255,255,255,0.5);
		    background-color: #f1ceab;
		    border: 1px solid #efb99e;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    border-radius: 4px;
		}
		.alert-danger, .alert-error {
		    color: #bd4247;
		    background-color: #f2bdb1;
		    border-color: #f0a5a4;
		}
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
                          //dn = cert.getSubjectDN().toString();
                          dn = cert.getSubjectX500Principal().getName();
                  }
      } catch(Exception e){
      	errmsg="Exception=" + e.getMessage();
      }
      %>
</head>
<body>
	<div class="header">
		<div id="messageBox" style="display:none;" class="alert alert-error ${empty message ? 'hide' : ''}">
			<label id="loginError" class="error">${message}</label>
		</div>
	</div>

    <div class="container" style=" background: url(${ctxStatic}/sts/image/bg.png) top left no-repeat;">
        <div class="Con_left">
            <div class="Con_L_text">Copyright @ 2012-2016 密钥管家服务平台 - Powered By 山西CA V1.0</div>
        </div>
        <div class="Con_right">
            <div id="icon_change"></div>
            <div class="loading">
                <div class="Pass_word Position">
                <!-- -------------------------------------------- --> 
         	   <form id="loginForm"  action="${ctx}/login" method="post">
                    <div class="Titie_head">账号登录</div>
                    <div class="user">
                        <div class="icon_image">
                            <img src="${ctxStatic}/sts/image/Word/user.png" alt="">
                        </div>
                        <input class="Pass_test username"  id="username" name="username" type="text" value="请输入账号" onfocus="this.value=''" onblur="if(this.value==''){this.value='请输入账号'}">
                    </div>
                    
                    <div class="pwd">
                        <div class="icon_image">
                            <img src="${ctxStatic}/sts/image/Word/pwd.png" alt="">
                        </div>
                        <input  id="password" name="password" class="Pass_test password" type="password" value="请输入密码" onfocus="this.value=''" onblur="if(this.value==''){this.value='请输入密码'}">
                    </div>
                    
                    <input class="BTN" type="submit" value="立即登录">
			</form>
			  
                </div>
                <!-- -------------------------------------------- -->
                <div class="Pass_key Position Display">
                <form id="certloginForm"  action="${ctx}/login" method="post">
                    <div class="Titie_head">证书登录</div>
                    <div class="loading_done loading_box" >
                        <div class="loading_Pic">
                            <img src="${ctxStatic}/sts/image/Key/done_loading.png" alt="">
                        </div>
                        <p class="loading_test">检测到数字证书，请点击按钮登录</p>
                        <input type="hidden" name="username" id="certsubject">
                        <input class="BTN" type="button" onclick="loginbycert();" value="立即登录">
                    </div>
                    <div class="loading_undone loading_box" style="display: none">
                        <div class="loading_Pic">
                            <img src="${ctxStatic}/sts/image/Key/undone_loading.png" alt="">
                        </div>
                        <p class="loading_test loading_untest">未检测到数字证书，请插入KEY</p>
                        
                        <input class="BTN BTN_undone" type="button" onclick="loginbycert();" value="立即登录">
                    </div>
				</form>
                </div>
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
                   $(this).css({background:"url(${ctxStatic}/sts/image/Key/icon_Pass_key.png)"})
               }else{
                   $('.Pass_word').fadeIn(200,function () {
                       $('.Pass_key').fadeOut(200);
                   });
                   $('.Pass_key').addClass('Display');
                   $(this).css({background:"url(${ctxStatic}/sts/image/Word/icon_Pass_word.png)"})
               }
           })
           $('.BTN').click(function () {
//                $('.loading_undone').show();
//                $('.loading_done').hide();
        	   $('.loading_undone').show();
               $('.loading_done').hide();
           })
           //文本框

       })
       
       $(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
       
       
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