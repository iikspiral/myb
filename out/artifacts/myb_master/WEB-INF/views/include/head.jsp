<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="http://ggzyzt.com/"/>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<!-- tip气泡 -->
<link rel="stylesheet" href="${ctxStatic}/tip/css/tip-yellowsimple.css" type="text/css"/>
<script src="${ctxStatic}/tip/js/jquery.poshytip.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'cerulean'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="${ctxStatic}/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctxStatic}/uploadify/uploadify.css" type="text/css"/>
<script type="text/javascript" src="${ctxStatic}/uploadify/jquery.uploadify.min.js"></script>
<%-- <script type="text/javascript" src="${ctxStatic}/uploadify/ajaxfileupload.js"></script> --%>
<link href="${ctxStatic}/common/myb.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/common/myb.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';</script>
<script type="text/javascript">
$.validator.methods.weburl = function( value, element ) {
	return this.optional( element ) || /[a-zA-z]+:\/\/[^\s]*/.test( value );
};
jQuery.validator.addMethod("isIdCardNo",function(value, element) {
	return this.optional(element) || checkIdcard(value);
},"请输入正确的身份证号码(15-18位)");
</script>
<script type="text/javascript">	 
$(function() {
		function flashChecker() {  
		    var hasFlash = 0;         //是否安装了flash  
		    var flashVersion = 0; //flash版本  
		    var isIE = /*@cc_on!@*/0;      //是否IE浏览器  
		
	        if (isIE) {  
	        	try {
	        		var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');  
	 	            if (swf) {  
	 	                hasFlash = 1;  
	 	                VSwf = swf.GetVariable("$version");  
	 	                flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);  
	 	            }
				} catch (e) {
					hasFlash = 0;
					flashVersion = 0;
					return { f: hasFlash, v: flashVersion };
				}
	             
	        } else {
	        	try {
	        		 if (navigator.userAgent.indexOf("Firefox")>0 || navigator.userAgent.indexOf("Chrome")>0) {  
	 	                var swf = navigator.plugins["Shockwave Flash"];  
	 	                if (swf) {  
	 	                    hasFlash = 1;  
	 	                    var words = swf.description.split(" ");  
	 	                    for (var i = 0; i < words.length; ++i) {  
	 	                        if (isNaN(parseInt(words[i]))) continue;  
	 	                        flashVersion = parseInt(words[i]);  
	 	                    }  
	 	                }  
	 	            }
				} catch (e) {
					hasFlash = 0;
					flashVersion = 0;
					return { f: hasFlash, v: flashVersion };
				}
	             
	        }  
	        return { f: hasFlash, v: flashVersion };  
	    }  
		
		var fls = flashChecker();  
		var s = "";  
		if(fls.f){
			//alert("您安装了flash,当前flash版本为: " + fls.v + ".x");
		}else{
// 			alert("您没有安装flash,请安装flash插件");
		}
	
});
</script>
