<%@ page contentType="text/html;charset=UTF-8" import="java.security.cert.X509Certificate"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="blank"/>
	<script type="text/javascript" src="${ctxStatic}/login/js/jquery.SuperSlide.2.1.1.js"></script>
	<link href="${ctxStatic}/login/css/login.css" rel="stylesheet" type="text/css">
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
	<script type="text/javascript">
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
</head>
<body>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<div class="header">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div>
	<h1 class="form-signin-heading">${fns:getConfig('productName')}</h1>
	<div class="form-signin">
		<dl class="sort_brand_blue" style="margin:0 auto">
			<dt><a href="#">账号密码登陆</a><a href="#">数字证书登录</a></dt>
			<dd class="login_form">
				<form id="loginForm"  action="${ctx}/login" method="post">
					<label class="input-label" for="username">登录名</label>
					<input type="text" id="username" name="username" class="input-block-level required">
					<label class="input-label" for="password">密码</label>
					<input type="password" id="password" name="password" class="input-block-level required">
					<c:if test="${isValidateCodeLogin}">
						<div class="validateCode">
							<label class="input-label mid" for="validateCode">验证码</label>
							<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
						</div>
					</c:if>
					<%--<label for="mobile" title="手机登录"><input type="checkbox" id="mobileLogin" name="mobileLogin" ${mobileLogin ? 'checked' : ''}/></label> --%>
					<input class="btn btn-large btn-primary" type="submit" value="登 录"/>&nbsp;&nbsp;
					<label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）</label>
					<div id="themeSwitch" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
						<ul class="dropdown-menu">
						  <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
						</ul>
						<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
					</div>
				</form>
			</dd>
			<dd class="login_form">
				<form id="certloginForm"  action="${ctx}/login" method="post">
					<table align="center">
						<tr>
							<td align="center">
								<img src="${ctxStatic}/login/images/login_013.gif">
							</td>
						</tr>
						<tr>
							<td align="center">
								<span class="Success_tips">请插入数字证书进行登录！</span>
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="hidden" name="username" id="certsubject">
								<input class="btn btn-large btn-primary" type="button" onclick="loginbycert();" value="证 书 登 录"/>
							</td>
						</tr>
					</table>
				</form>
			</dd>
		</dl>
		<script type="text/javascript">
         //书签切换，只切换targetCell
         jQuery(".sort_brand_blue").slide({titCell: "dt a", targetCell: "dd", delayTime: 0});
    </script>
	</div>
	
	<div class="footer">
		Copyright &copy; 2012-${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="http://www.sxca.com.cn/" target="_blank">山西CA</a> ${fns:getConfig('version')} 
	</div>
	<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
</body>
</html>