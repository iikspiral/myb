
/**  2013-11-06 author guoxudong  **/
var TIMEOUT = 60000;
var rootPath=$("#bossRootPath").val();
var iCloudPath = $("#iCloudPath").val();
var eattStr = "type=\"text\" class=\"text\" onblur = \"onblurGo(this)\"";
var eatthid= "type=\"text\" class=\"text hidden\" onblur = \"onblurGo(this)\"";
var tipVal1= 0;
var tipVal2= 0;
var idtype  = "<select id=\"paperworkType\" class=\"select w100\"><option value=1>身份证</option><option value=2>护照</option><option value=4>军官证</option></select>";
var idtypehid  = "<select id=\"paperworkType\" class=\"class=\"select w100\" hidden\"><option value=1>身份证</option><option value=2>护照</option><option value=4>军官证</option></select>\"";
var cueStr = "无法判断来源，请到&nbsp;<a href=\"http://i.jcloud.com/user/info/authorization\" style=\"font-size: 15px;color: orange;font-weight: bolder;\">这里选择对应平台</a>&nbsp;注册！";
function init(){
	
	
	$("#upgradeEnterpriseBtnSub").click(function (e){
		upgradeEnterpriseBtnSubFun();
	});
	$("#updataCertifideFwBtnSub").click(function (e){
		updateEnterpriBtnSubFun();
	});
	
	//协议弹框
	$("#agreements_fw_p").click(function (e){
		getAgreementsFun(2,"agreements_fw_p");
		$('#addOperator').jqmShow();
	});
	
	$("#agreementsReg").click(function(e){
		getAgreementsFun(1,"agreementsReg");
		$('#addOperator').jqmShow();
	});
	
	$("#agreements_fw").click(function(e){
		getAgreementsFun(2,"agreements_fw");
		$('#addOperator').jqmShow();
	});
	$("#agreements_h").click(function(e){
		getAgreementsFun(2,"agreements_h");
		$('#addOperator').jqmShow();
	});
	$("#viewKey").click(function(e){
		viewKeyFun();
	});
	
	$("#descShowBtn").click(function (e){
		$('#addOperator').jqmHide();
	});
	
	$("#keyCloseBtn").click(function(e){
		$('#addOperator').jqmHide();
	});
	
	$('#addOperator').jqm({
		w:550,
		closeClass: 'jqmClose',
		overlayClass: 'whiteOverlay',
		retrieveTop:function(){
			return 20;
		},
		movable: true
	});	
	
	
	
	/**  注册开发者   **/
	$("#checkbox_reg").click(function(e){
		checkboxFun("checkbox_reg","regSubmitBtn");
	});
	$("#regSubmitBtn").click(function(e){
		regSubmitBtnFun();
	});
	
	
	
	/**  认证开发者 云鼎  **/
	$("#hosting").click(function(e){
		checkboxFun("hosting","hostingBtn");
	});
	$("#hostingBtn").click(function(e){
		hostingBtnFun();
	});
	
	
	
	/**  认证开发者 服务商 **/
	$("#certifideFw").click(function(e){
		checkboxFun("certifideFw","certifideFwBtn");
	});
	$("#certifideFwBtn").click(function(e){
		certifideFwBtnFun();
	});
	
	/**  认证开发者 个人 **/
	$("#personalFw").click(function(e){
		checkboxFun("personalFw","personalFwBtn");
	});
	$("#personalFwBtn").click(function(e){
		personalFwBtnFun();
	});
	
	
	
	$("#enterpriseId").click(function (e){
		if(!$("#enterpriseId").hasClass("curr")){
			$("#enterpriseId").addClass("curr");
		}
		if($("#personalId").hasClass("curr")){
			$("#personalId").removeClass("curr");
		}
		if($("#enterprise").hasClass("hidden")){
			$("#enterprise").removeClass("hidden");
		}
		if(!$("#personal").hasClass("hidden")){
			$("#personal").addClass("hidden");
		}
	});
	$("#personalId").click(function (e){
		if(!$("#personalId").hasClass("curr")){
			$("#personalId").addClass("curr");
		}
		if($("#enterpriseId").hasClass("curr")){
			$("#enterpriseId").removeClass("curr");
		}
		
		if($("#personal").hasClass("hidden")){
			$("#personal").removeClass("hidden");
		}
		if(!$("#enterprise").hasClass("hidden")){
			$("#enterprise").addClass("hidden");
		}
	});
	
	
	$("#regBtn").click(function (e){
		if(!$("#regBtn").hasClass("curr")){
			$("#regBtn").addClass("curr");
		}
		if($("#certifiedBtn").hasClass("curr")){
			$("#certifiedBtn").removeClass("curr");
		}
		if($("#reg").hasClass("hidden")){
			$("#reg").removeClass("hidden");
		}
		if(!$("#certified").hasClass("hidden")){
			$("#certified").addClass("hidden");
		}
	});
	
	$("#certifiedBtn").click(function (e){
		if(!$("#certifiedBtn").hasClass("curr")){
			$("#certifiedBtn").addClass("curr");
		}
		if($("#regBtn").hasClass("curr")){
			$("#regBtn").removeClass("curr");
		}
		if($("#certified").hasClass("hidden")){
			$("#certified").removeClass("hidden");
		}
		if(!$("#reg").hasClass("hidden")){
			$("#reg").addClass("hidden");
		}
	});
	
	//inviteBtn
	/**  邀请码 **/
	$("#inviteBtn").click(function(e){
		inviteBtnFun();
	});
	
	//regUpdata
	$("#regUpdata").click(function(e){
		regUpdataFun();
	});
	
	
	$("#updataSub").click(function(e){
		updataSubFun();
	});
	
	$("#updatePersonalFwBtn").click(function(e){
		updatePersonalFwBtnFun();
	});
	
	
	$("#updatePersonalFwBtnSub").click(function(e){
		updatePersonalFwBtnSubFun();
	});
	
	
	$("#updataCertifideFwBtn").click(function(e){
		updataCertifideFwBtnFun();
	});
	
	//升级成企业
	$("#upgradeEnterprise").click(function(e){
		upgradeEnterpriseFun();
	});
	
	//企业基本信息展开收缩
	$("#qyjb").click(function(e){
		openContraction(this.id);
	});
	//企业执照信息展开收缩
	$("#qyzhizhao").click(function(e){
		openContraction(this.id);
	});
	//企业税务信息展开收缩
	$("#qysw").click(function(e){
		openContraction(this.id);
	});
	
	//企业开户信息展开收缩
	$("#qykhh").click(function(e){
		openContraction(this.id);
	});
	
	
}

function initClouseBtn(){
	$("#agreements_h_arg").click(function (e){
		trueCheck("hosting","hostingBtn");
		$('#addOperator').jqmHide();
	});
	$("#agreements_fw_arg").click(function (e){
		trueCheck("certifideFw","certifideFwBtn");
		$('#addOperator').jqmHide();
	});
	$("#agreementsReg_arg").click(function (e){
		trueCheck("checkbox_reg","regSubmitBtn");
		$('#addOperator').jqmHide();
	});
	$("#agreements_fw_p_arg").click(function (e){
		trueCheck("personalFw","personalFwBtn");
		$('#addOperator').jqmHide();
	});
	
	$("#closeAuditInfoBtn").click(function (e){
		$('#addOperator').jqmHide();
	});
}


function trueCheck(checkId,sbtnId){
	if (!$("#"+checkId).attr("checked")){
		!$("#"+checkId).attr("checked",true);
	}
	if($("#"+sbtnId).hasClass("disabled")){
		$("#"+sbtnId).removeClass("disabled");
	}
}

function updataCertifideFwBtnFun(){
	ediHidden("spName");
	ediHidden("spComContactName");
	ediHidden("spBizLicenseNum");
	ediHidden("spBizLicenseScannedCopies");
	ediHidden("spOrganizationCode");
	ediHidden("spOrganizationScannedCopies");
	ediHidden("spTaxRegistrationNumber");
	ediHidden("spTaxRegistrationScannedCopies");
	ediHidden("spAccountLicenseNum");
	ediHidden("spAccountLicenseScannedCopies");
	ediHidden("spServicePhone");
	ediHidden("spBankAccountName");
	ediHidden("spCompanyBankAccount");
	ediHidden("spBankLocation");
	ediHidden("spServiceEmail");
	copiesHidden("spTaxRegistrationScannedCopies");
	copiesHidden("spBizLicenseScannedCopies");
	copiesHidden("spOrganizationScannedCopies");
	copiesHidden("spAccountLicenseScannedCopies");
	
	if(!$("#updataCertifideFwBtn").hasClass("hidden")){
		$("#updataCertifideFwBtn").addClass("hidden");
		$("#updataCertifideFwBtnSub").removeClass("hidden");
	}
}

function copiesHidden(fname){
	if(!$("#"+fname+"dev").hasClass("hidden")){
		$("#"+fname+"dev").addClass("hidden");
		$("#"+fname+"_css").removeClass("hidden");
	}
}

function ediHidden(fname){
	if(!$("#"+fname+"_s").hasClass("hidden")){
		$("#"+fname+"_s").addClass("hidden");
		$("#"+fname).removeClass("hidden");
	}
}

function regUpdataFun(){
	if($("#email").hasClass("hidden")){
		$("#email").removeClass("hidden");
		$("#email_s").addClass("hidden");
	}
	if($("#phone").hasClass("hidden")){
		$("#phone").removeClass("hidden");
		$("#phone_s").addClass("hidden");
	}
	if($("#phoneVerify").hasClass("hidden")){
		$("#phoneVerify").removeClass("hidden");
		$("#phoneVerifyCodeId").removeClass("hidden");
		$("#phoneVerifydev_tr").removeClass("hidden");
	}
	if(!$("#key_tr").hasClass("hidden")){
		$("#key_tr").addClass("hidden");
	}
	if(!$("#regUpdataId").hasClass("hidden")){
		$("#regUpdataId").addClass("hidden");
		$("#updataSubId").removeClass("hidden");
	}
	$("#regEdiId").addClass("tab2");
	$("#phone_tr_tdId").css("width"," 680px");
}

function updatePersonalFwBtnFun(){
	if(!$("#name_s").hasClass("hidden")){
		$("#name_s").addClass("hidden");
		$("#name").removeClass("hidden");
	}
	if(!$("#paperworkType_s").hasClass("hidden")){
		$("#paperworkType_s").addClass("hidden");
		$("#paperworkType").removeClass("hidden");
	}
	if(!$("#idCardCode_s").hasClass("hidden")){
		$("#idCardCode_s").addClass("hidden");
		$("#idCardCode").removeClass("hidden");
	}
	if(!$("#spPersonalServicePhone_s").hasClass("hidden")){
		$("#spPersonalServicePhone_s").addClass("hidden");
		$("#spPersonalServicePhone").removeClass("hidden");
	}
	if(!$("#spPersonalMail_s").hasClass("hidden")){
		$("#spPersonalMail_s").addClass("hidden");
		$("#spPersonalMail").removeClass("hidden");
	}
	if(!$("#updatePersonalFwBtn").hasClass("hidden")){
		$("#updatePersonalFwBtn").addClass("hidden");
		$("#updatePersonalFwBtnSub").removeClass("hidden");
	}
	
}

function onblurGo(a){
	var fid = a.id;
	var val = a.value;
	if(val){
		var str = "<input id=\""+fid+"\" name=\""+fid+"\" value=\""+val+"\"/>";
		$("#"+fid+"_hdev").html(str);
	}
	
}

function initInputVal(fid,val){
	var str = "<input id=\""+fid+"\" name=\""+fid+"\" value=\""+val+"\"/>";
	$("#"+fid+"_hdev").html(str);
}


//获取协议内容
function getAgreementsFun(type,bId){
	$.ajax({
		url : 'getAgreementsFun',
		type : 'GET',
		data : {
			type : type
		},
		dataType : 'json',
		timeout : TIMEOUT,
		error : function(d) {
			return;
		}, 
		success : function(d) {
			$("#titleId").html("协议内容");
			$("#agreementsTxt").html(d.msg);
			var agrBtn="<a id=\""+bId+"_arg\" class=\"btn_common\">确定并同意</a>";
			$("#closeBtn").html(agrBtn);
			initClouseBtn();
		}
	});
}

function viewKeyFun(){
	$.ajax({
		url : iCloudPath+'center/getUserKeyFun?_='+(new Date()).valueOf(),
		type : 'GET',
		dataType : 'json',
		timeout : TIMEOUT,
		error : function(d) {
			return;
		}, 
		success : function(d) {
			$('#addOperator').jqmShow();
			 if(d.code==1){
        		 var a_htm=d.accessKey;
        		 $("#accessKeyId").html(a_htm);
        		 var s_htm=d.secretKey;
        		 $("#secretKeyId").html(s_htm);
        		 return;
        	}
		}
	});
}

function inviteBtnFun(){
	var inviteCode = $("#inviteCode").val();
	var platform = $("#platform").val();
	if(inviteCode.length!=13){
		$.fn.utility.tipsv1.simple ("提示","failure","邀请码错误。",2000);
		return;
	}
	$.ajax({
		url : 'inviteCodeFun',
		type : 'POST',
		data : {
			platform : platform,
			inviteCode:inviteCode
		},
		dataType : 'json',
		timeout : TIMEOUT,
		error : function(d) {
			$.fn.utility.tipsv1.simple ("提示","failure","填写邀请码失败。",2000);
			return;
		}, 
		success : function(d) {
			if(1==d.code){
				$.fn.utility.tipsv1.simple ("提示","success",d.msg,2000);
				setTimeout(function(){window.location.href = ""+d.platform+"";},2000);
				return;
			}
			$.fn.utility.tipsv1.simple ("提示","failure",d.msg,2000);
			return;
		}
	});
}

function checkboxFun(checkId,bid){
	if ($("#"+checkId).attr("checked")) { //判断是否选中  
		$("#"+bid).removeClass("disabled");
    }else{
    	$("#"+bid).addClass("disabled");
    }
}



/**
 * 编辑  注册用户信息 updata
 * 2013-11-06
 * author guoxudong
 **/
function updataSubFun(){
	if(checkEP()) return;
	  $("#ediReg").ajaxSubmit({
	        url:iCloudPath+"user/info/saveEdiInfoReg",
	        type : 'POST',
	        dataType:"json",
	    	timeout:TIMEOUT,
	    	error:function(d) {
	 				return;
	    	},
	        success: function(d) {
	    		if(1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","success",d.msg,2000);
	    			if(d.platform){
	    				setTimeout(function(){window.location.href = ""+d.platform+"";},2000);
	    			}
	    			return;
	    		}else{
	    			$.fn.utility.tipsv1.simple ("提示","failure",d.msg,2000);
	    			return;
	    		}
            }
	   });
}

/**
 * 编辑 认证个人 用户信息 updata
 * 2013-11-06
 * author guoxudong
 **/
function updatePersonalFwBtnSubFun(){
	  $("#fw_personal_form").ajaxSubmit({
	        url:iCloudPath+"user/info/updataPersonalFun",
	        type : 'POST',
	        dataType:"json",
	    	timeout:TIMEOUT,
	    	error:function(d) {
	 				return;
	    	},
	        success: function(d) {
	    		if(1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","success",d.msg,2000);
	    			window.location.reload();
	    			return;
	    		}else{
	    			$.fn.utility.tipsv1.simple ("提示","failure",d.msg,2000);
	    			return;
	    		}
            }
	   });
}

/**
 * 编辑 认证企业 用户信息 updata
 * 2013-11-06
 * author guoxudong
 **/
function updateEnterpriBtnSubFun(){
	  $("#fw_enterprise_form").ajaxSubmit({
	        url:iCloudPath+"user/info/upgradeEnterpriseFun",
	        type : 'POST',
	        dataType:"json",
	    	timeout:TIMEOUT,
	    	error:function(d) {
	 				return;
	    	},
	        success: function(d) {
	    		if(1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","success",d.msg,2000);
	    			window.location.reload();
	    			return;
	    		}else{
	    			$.fn.utility.tipsv1.simple ("提示","failure",d.msg,2000);
	    			return;
	    		}
            }
	   });
}

/**
 * 注册用户提交
 * 2013-11-06
 * author guoxudong
 **/
function regSubmitBtnFun(){
	if($("#regSubmitBtn").hasClass("disabled")) return;
	if(checkEP()) return;
	  $("#regDevform").ajaxSubmit({
	        url:iCloudPath+"user/info/createRegDev",
	        type : 'POST',
	        dataType:"json",
	    	timeout:TIMEOUT,
	    	error:function(d) {
	 				return;
	    	},
	        success: function(d) {
	    		if(1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","success",d.msg,2000);
	    			if(d.platform){
	    				setTimeout(function(){window.location.href = ""+d.platform+"";},2000);
	    			}
	    			return;
	    		}else if(-1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","failure",cueStr,5000);
	    			return;
	    		}else{
	    			$.fn.utility.tipsv1.simple ("提示","failure",d.msg,2000);
	    			return;
	    		}
            }
	   });
}

/**
 * 认证用户 云鼎 提交
 * 2013-11-06
 * author guoxudong
 **/
function hostingBtnFun(){
	if($("#hostingBtn").hasClass("disabled")) return;
	if(checkEP()) return;
	  $("#hosting_form").ajaxSubmit({
	        url:iCloudPath+"user/info/createHosting",
	        type : 'POST',
	        dataType:"json",
	    	timeout:TIMEOUT,
	    	error:function(d) {
	 				return;
	    	},
	        success: function(d) {
	    		if(1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","success",d.msg,2000);
	    			if(d.platform){
	    				setTimeout(function(){window.location.href = ""+d.platform+"";},2000);
	    			}
	    			return;
	    		}else if(-1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","failure",cueStr,2000);
	    			return;
	    		}else{
	    			$.fn.utility.tipsv1.simple ("提示","failure",d.msg,2000);
	    			return;
	    		}
            }
	   });
}

/**
 * 认证用户 有平台 来源  企业 提交
 * 2013-11-06
 * author guoxudong
 **/
function certifideFwBtnFun(){
	if($("#certifideFwBtn").hasClass("disabled")) return;
	if(checkEP()) return;
	  $("#fw_enterprise_form").ajaxSubmit({
	        url:iCloudPath+"user/info/createCategoryEnterpri",
	        type : 'POST',
	        dataType:"json",
	    	timeout:TIMEOUT,
	    	error:function(d) {
	 				return;
	    	},
	        success: function(d) {
	    		if(1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","success",d.msg,2000);
	    			if(d.platform){
	    				setTimeout(function(){window.location.href = ""+d.platform+"";},2000);
	    			}
	    			return;
	    		}else if(-1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","failure",cueStr,5000);
	    			return;
	    		}else{
	    			$.fn.utility.tipsv1.simple ("提示","failure",d.msg,2000);
	    			return;
	    		}
            }
	   });
}


/**
 * 认证用户 有平台 来源  个人 提交
 * 2013-11-06
 * author guoxudong
 **/
function personalFwBtnFun(){
	if($("#personalFwBtn").hasClass("disabled")) return;
	if(checkEP()) return;
	  $("#fw_personal_form").ajaxSubmit({
	        url:iCloudPath+"user/info/createCategoryPersonal",
	        type : 'POST',
	        dataType:"json",
	    	timeout:TIMEOUT,
	    	error:function(d) {
	 				return;
	    	},
	        success: function(d) {
	    		if(1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","success",d.msg,2000);
	    			if(d.platform){
	    				window.location.href = ""+d.platform+"";
	    			}
	    			return;
	    		}else if(-1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","failure",cueStr,5000);
	    			return;
	    		}else{
	    			$.fn.utility.tipsv1.simple ("提示","failure",d.msg,2000);
	    			return;
	    		}
            }
	   });
}

/**
 *获取手机校验码
 * 2013-11-06
 * author guoxudong
 **/
function getPhoenVerifyCode(){
	var flag=false;
	if($("#phoneVerifyCodeId").hasClass("disabled")) return;
	var phoneNum =  $.trim($("#phone").val());
	if(phoneNum.length<=0){
		 $("input[name='phone']").each(function(index){
			  if((this.value).length>0){
				  phoneNum=this.value;
			  }
		});
		 if(phoneNum.length>0){
			 flag=true;
		 }
	}
	
	if(phoneNum.length==0){ 
		 $.fn.utility.tipsv1.simple ("提示","failure","手机号不能为空。",2000);
		    return false; 
		   }
	 if(!phoneNum.match(/^1[3|4|5|7|8][0-9]\d{8}$/)){ 
		 $.fn.utility.tipsv1.simple ("提示","failure","请填写正确手机号码。",2000);
		    return false; 
		   } 
	
	if(flag){
		getPhoneValidationWait(1);
		$("[name='phoneVerifyCodeId']").addClass("disabled");
	}else{
		getPhoneValidationWait(0);
		$("#phoneVerifyCodeId").addClass("disabled");
	}
	
	$.ajax({
        url: iCloudPath+'center/getPhoneVerifyCodeFun?_='+(new Date()).valueOf(),
        type: 'POST',
        data: {
        	phoneNum:phoneNum
        	},
        dataType: 'json',
        timeout: TIMEOUT,
        error: function(){
        },
        success: function(json){
        }
    });
}

/**
 * 手机验证码倒计时
 * 
 * getPhoneValidationWait
 * 2013-11-06
 * author guoxudong
 * 
 * */
function getPhoneValidationWait(num){
	function jump(count) {
        window.setTimeout(function(){
            count--;
            if(count > 0) {
            	if(1==num){
            		$('#waitId_tr_p').removeClass("hidden");
            		$('#spanId_p').text(count);
            	}else{
            		$('#waitId_tr').removeClass("hidden");
               	 	$('#spanId').text(count);
            	}
                jump(count);
            } else {
                if(1==num){
                	$('#waitId_tr_p').addClass("hidden");
                	$("[name='phoneVerifyCodeId']").removeClass("disabled");
                }else{
                	$("#phoneVerifyCodeId").removeClass("disabled");
                	$('#waitId_tr').addClass("hidden");
                }
            }
        }, 1000);
    }
    jump(60);
}


/**
 * 初始化基本信息
 * 
 * initBasicInfo
 * 2013-11-06
 * author guoxudong
 * 
 * */
function initBasicInfo(){
	
	$.ajax({
        url: 'getRegDevInfo?_='+(new Date()).valueOf(),
        type: 'GET',
        dataType: 'json',
        timeout: TIMEOUT,
        error: function(){
        },
        success: function(d){
        	if(1==d.code){
        		var jdname = "";
        		var mail_htm = "<input id=\"email\"  name=\"email\" "+eattStr+" "+validate(30,6,"email")+" >";
        		var phone_htm = "<input id=\"phone\" name=\"phone\" "+eattStr+" "+validate(11,11,"phone")+">";
        			phone_htm +="<a class=\"phone\" id=\"phoneVerifyCodeId\" name=\"phoneVerifyCodeId\" onclick=\"getPhoenVerifyCode();\" href=\"javascript:void(0);\">获取手机验证码</a>";
        		if(d.u.jname){
        			jdname = "<strong>"+d.u.jname+"</strong>";
        		}
        		if(d.u.nickName){
        			var nickname = "<strong>"+d.u.nickName+"</strong>";
        			$("#nickNamedev").html(nickname);
        			$("#nickNamedev_p").html(nickname);
        			if($("#nickId").hasClass("hidden")){
        				$("#nickId").removeClass("hidden");
        			}
        		} 
        		if(d.email){
        			mail_htm = "<span id=\"email_s\">"+d.email+"</span>";
        			mail_htm += "<input id=\"email\" name=\"email\" "+eatthid+" value=\""+d.email+"\" "+validate(30,6,"email")+">";
        			//mail_htm += "<input id=\"email\" name=\"email\" "+eatthid+" value=\"\" "+validate(30,6,"email")+">";
        			createInput("email",d.email);
        		}else if(d.u.email){
        			mail_htm = "<input id=\"email\" name=\"email\" "+eattStr+" value=\""+d.u.email+"\" "+validate(30,6,"email")+">";
        			//mail_htm = "<input id=\"email\" name=\"email\" "+eattStr+" value=\"\" "+validate(30,6,"email")+">";
        			createInput("email",d.u.email);
        		}
        		if(d.phone){
        			phone_htm = "<span id=\"phone_s\">"+d.phone+"</span>";
        			phone_htm += "<input id=\"phone\" name=\"phone\" "+eatthid+" value=\""+d.phone+"\" "+validate(11,11,"phone")+">" +
        					"<a class=\"phone hidden\" id=\"phoneVerifyCodeId\" name=\"phoneVerifyCodeId\" onclick=\"getPhoenVerifyCode();\" href=\"javascript:void(0);\">获取手机验证码</a>";
        	//		phone_htm += "<input id=\"phone\" name=\"phone\" "+eatthid+" value=\"\" "+validate(11,11,"phone")+">" +
//					"<a class=\"phone hidden\" id=\"phoneVerifyCodeId\" name=\"phoneVerifyCodeId\" onclick=\"getPhoenVerifyCode();\" href=\"javascript:void(0);\">获取手机验证码</a>";
        			createInput("phone",d.phone);
        		}else if(d.u.phone){
        			phone_htm = "<input id=\"phone\" name=\"phone\" "+eattStr+" value=\""+d.u.phone+"\" "+validate(11,11,"phone")+">" +
        					"<a class=\"phone hidden\" id=\"phoneVerifyCodeId\" name=\"phoneVerifyCodeId\" onclick=\"getPhoenVerifyCode();\" href=\"javascript:void(0);\">获取手机验证码</a>";
//        			phone_htm = "<input id=\"phone\" name=\"phone\" "+eattStr+" value=\"\" "+validate(11,11,"phone")+">" +
//					"<a class=\"phone hidden\" id=\"phoneVerifyCodeId\" name=\"phoneVerifyCodeId\" onclick=\"getPhoenVerifyCode();\" href=\"javascript:void(0);\">获取手机验证码</a>";
        			createInput("phone",d.u.phone);
        		}
        		var phoneVerify_htm = "<input id=\"phoneVerify\" name=\"phoneVerify\" "+eatthid+" "+validate(6,6,"verifyPhoneCode")+">";
    			$("#phoneVerifydev").html(phoneVerify_htm);
        		
    			$("#jdNamedev").html(jdname);
        		$("#jdNamedev_p").html(jdname);
        		$("#emaildev").html(mail_htm);
        		$("#emaildev_p").html(mail_htm);
        		$("#phonedev").html(phone_htm);
        		$("#phonedev_p").html(phone_htm);
        		
    			if(!d.phone||!d.email){
        			$("#phoneVerifyCodeId").removeClass("hidden");
        			$("[name='phoneVerifyCodeId']").removeClass("hidden");
        			$("#phoneVerify").removeClass("hidden");
        		}else{
        			$("#agreements_tr").addClass("hidden");
        			$("#regsub_tr").addClass("hidden");
        			$("#phoneVerifydev_tr").addClass("hidden");
        			$("#phoneVerifydev_tr_p").addClass("hidden");
        			$("#waitId_tr").addClass("hidden");
        		}
        		
        		
        		/*绑定校验事件*/
        		if($("#tip").length<1){
        			$().common.valid.init($("#container"));
        		}
        		$('#jqmLoading').jqmHide();
        	}
        }
    });
}

/**
 * 初始化 注册开发者 注册页面
 * 
 * initRegDev
 * 2013-11-06
 * author guoxudong
 * 
 * */
function initRegDev(audit){
	
	$.ajax({
        url: 'getRegDevInfo?_='+(new Date()).valueOf(),
        type: 'GET',
        dataType: 'json',
        timeout: TIMEOUT,
        error: function(){
        },
        success: function(d){
        	if(1==d.code){
        		var jdname = "";
        		var mail_htm = "<input id=\"email\" name=\"email\" "+eattStr+" "+validate(30,6,"email")+" >";
        		var phone_htm = "<input id=\"phone\" name=\"phone\" "+eattStr+" "+validate(11,11,"phone")+">";
        			phone_htm +="<a class=\"phone\" id=\"phoneVerifyCodeId\" name=\"phoneVerifyCodeId\" onclick=\"getPhoenVerifyCode();\" href=\"javascript:void(0);\">获取手机验证码</a>";
    			if(d.u.jname){
        			jdname = "<strong>"+d.u.jname+"</strong>";
        		}
        		if(d.u.nickName){
        			var nickname = "<strong>"+d.u.nickName+"</strong>";
        			$("#nickNamedev").html(nickname);
        			
        			if($("#nickId").hasClass("hidden")){
        				$("#nickId").removeClass("hidden");
        			}
        		} 
        		if(d.email){
        			mail_htm = "<span id=\"email_s\">"+d.email+"</span>";
        			mail_htm += "<input id=\"email\" name=\"email\" "+eatthid+" value=\""+d.email+"\" "+validate(30,6,"email")+">";
        		}else if(d.u.email){
        			mail_htm = "<input id=\"email\"  name=\"email\" value=\""+d.u.email+"\" "+eattStr+" "+validate(30,6,"email")+" >";
        		}
        		if(d.phone){
        			phone_htm = "<span id=\"phone_s\">"+d.phone+"</span>";
        			phone_htm += "<input id=\"phone\" name=\"phone\" "+eatthid+" value=\""+d.phone+"\" "+validate(11,11,"phone")+">" +
        							"<a class=\"phone hidden\" id=\"phoneVerifyCodeId\" name=\"phoneVerifyCodeId\" onclick=\"getPhoenVerifyCode();\" href=\"javascript:void(0);\">获取手机验证码</a>";
        		}else if(d.u.phone){
        			phone_htm = "<input id=\"phone\" name=\"phone\" "+eattStr+" value=\""+d.u.phone+"\" "+validate(11,11,"phone")+">" +
									"<a class=\"phone hidden\" id=\"phoneVerifyCodeId\" name=\"phoneVerifyCodeId\" onclick=\"getPhoenVerifyCode();\" href=\"javascript:void(0);\">获取手机验证码</a>";
        		}
        		var phoneVerify_htm = "<input id=\"phoneVerify\" name=\"phoneVerify\" "+eattStr+" "+validate(6,6,"verifyPhoneCode")+">";
    			$("#phoneVerifydev").html(phoneVerify_htm);
    			$("#jdNamedev").html(jdname);
        		$("#emaildev").html(mail_htm);
        		$("#phonedev").html(phone_htm);
        		
    			if(!d.phone||!d.email){
        			if($("#phoneVerifyCodeId").hasClass("hidden")){
        				$("#phoneVerifyCodeId").removeClass("hidden");
        			}
        			if($("#phoneVerify").hasClass("hidden")){
        				$("#phoneVerify").removeClass("hidden");
        				$("#phoneVerifydev_tr").removeClass("hidden");
        			}
        		}else{
//        			$("#agreements_tr").addClass("hidden");
//        			$("#regsub_tr").addClass("hidden");
//        			$("#phoneVerifydev_tr").addClass("hidden");
//        			$("#phoneVerifydev_tr_p").addClass("hidden");
//        			$("#waitId_tr").addClass("hidden");
        			
        			$("#agreements_tr").addClass("hidden");
        			$("#regsub_tr").addClass("hidden");
        			$("#phoneVerifydev_tr").addClass("hidden");
        			$("#waitId_tr").addClass("hidden");
        		}
        		
        		
        		/*绑定校验事件*/
        		if($("#tip").length<1){
        			$().common.valid.init($("#container"));
        		}
        		$('#jqmLoading').jqmHide();
        	}
        	if(1==audit){
        		$("#email").removeClass("hidden");
    			$("#email_s").addClass("hidden");
    			$("#phone").removeClass("hidden");
    			$("#phone_s").addClass("hidden");
    			$("#phoneVerify").removeClass("hidden");
    			$("#phoneVerifyCodeId").removeClass("hidden");
    			$("#phoneVerifydev_tr").removeClass("hidden");
    			$("#regsub_tr").removeClass("hidden");
    			$("#regSubmitBtn").removeClass("disabled");
        	}
        }
    });
}

function validate(max,min,type){
	return "validate=\"true\"  max=\""+max+"\" min=\""+min+"\" dataType=\""+type+"\"";
}

/**
 * 初始化云鼎注册页面
 * 
 * initHosting
 * 2013-11-06
 * author guoxudong
 * 
 * */
function initHosting(audit){
	
	$.ajax({
        url: 'getCertificationInfo?_='+(new Date()).valueOf(),
        type: 'GET',
        dataType: 'json',
        timeout: TIMEOUT,
        error: function(){
        },
        success: function(d){
        	if(1==d.code){
        		
        		//业务信息
        		var spName_htm = "<input id=\"spName\" name=\"spName\" "+eattStr+" "+validate(30,1,"name")+">";
        		var spComContactName_htm = "<input id=\"spComContactName\" name=\"spComContactName\" "+eattStr+" "+validate(30,1,"string")+">";	//企业联系人姓名
        		var spBizLicenseNum_htm = "<input id=\"spBizLicenseNum\" name=\"spBizLicenseNum\" "+eattStr+" "+validate(15,15,"validBusCode")+">";	//营业执照号
//        		var spBizLicenseScannedCopies_htm = scan_spb;	//营业执照号扫描件
        		var spOrganizationCode_htm = "<input id=\"spOrganizationCode\" name=\"spOrganizationCode\" "+eattStr+" "+validate(10,10,"organization")+">";	//组织机构代码
//        		var spOrganizationScannedCopies_htm = scan_spo;	//组织机构代码扫描件
        		var spTaxRegistrationNumber_htm = "<input id=\"spTaxRegistrationNumber\" name=\"spTaxRegistrationNumber\" "+eattStr+" "+validate(15,15,"registration")+">";	//税务登记号
//        		var spTaxRegistrationScannedCopies_htm = scan_spt;	//税务登记号扫描件
        		
        		if(d.spName){
        			spName_htm = "<span id=\"spName_s\">"+d.spName+"</span>";
        			initInputVal("spName",d.spName);
        			spName_htm += "<input id=\"spName\" name=\"spName\" "+eatthid+" value=\""+d.spName+"\" "+validate(30,1,"string")+">";
        			
        		}
        		if(d.spComContactName){
        			spComContactName_htm = "<span id=\"spComContactName_s\">"+d.spComContactName+"</span>";
        			initInputVal("spComContactName",d.spComContactName);
        			spComContactName_htm += "<input id=\"spComContactName\" name=\"spComContactName\" "+eatthid+" value=\""+d.spComContactName+"\" "+validate(30,1,"string")+">";
        		}
        		if(d.spBizLicenseNum){
        			spBizLicenseNum_htm = "<span id=\"spBizLicenseNum_s\">"+d.spBizLicenseNum+"</span>";
        			initInputVal("spBizLicenseNum",d.spBizLicenseNum);
        			spBizLicenseNum_htm += "<input id=\"spBizLicenseNum\" name=\"spBizLicenseNum\" "+eatthid+" value=\""+d.spBizLicenseNum+"\" "+validate(30,1,"validBusCode")+">";
        		}
        		if(d.spBizLicenseScannedCopies){
        			var spBizLicenseScannedCopies_htm = "<a class=\"\" href=\""+iCloudPath+"center/showImg?imge="+d.spBizLicenseScannedCopies+"\" target=\"_blank\">" +
						  							"<img src=\""+iCloudPath+"fileUpload/downloadFiles?fileKey="+d.spBizLicenseScannedCopies+"\" alt=\"\" width=\"122\" height=\"92\" /></a>";
        			$("#spBizLicenseScannedCopiesdev").html(spBizLicenseScannedCopies_htm);
        			initInputVal("spBizLicenseScannedCopies",d.spBizLicenseScannedCopies);
        			$("#spBizLicenseScannedCopies_css").addClass("hidden");
        			
        		}
        		if(d.spOrganizationCode){
        			spOrganizationCode_htm = "<span id=\"spOrganizationCode_s\">"+d.spOrganizationCode+"</span>";
        			initInputVal("spOrganizationCode",d.spOrganizationCode);
        			spOrganizationCode_htm += "<input id=\"spOrganizationCode\" name=\"spOrganizationCode\" "+eatthid+" value=\""+d.spOrganizationCode+"\" "+validate(30,1,"organization")+">";
        		}
        		if(d.spOrganizationScannedCopies){
        			var spOrganizationScannedCopies_htm = "<a class=\"\" href=\""+iCloudPath+"center/showImg?imge="+d.spOrganizationScannedCopies+"\" target=\"_blank\">" +
  	 			  									  "<img src=\""+iCloudPath+"fileUpload/downloadFiles?fileKey="+d.spOrganizationScannedCopies+"\" alt=\"\" width=\"122\" height=\"92\" /></a>";
        			
        			$("#spOrganizationScannedCopiesdev").html(spOrganizationScannedCopies_htm);
        			initInputVal("spOrganizationScannedCopies",d.spOrganizationScannedCopies);
        			$("#spOrganizationScannedCopies_css").addClass("hidden");
        		}
        		if(d.spTaxRegistrationNumber){
        			spTaxRegistrationNumber_htm = "<span id=\"spTaxRegistrationNumber_s\">"+d.spTaxRegistrationNumber+"</span>";
        			initInputVal("spTaxRegistrationNumber",d.spTaxRegistrationNumber);
        			spTaxRegistrationNumber_htm += "<input id=\"spTaxRegistrationNumber\" name=\"spTaxRegistrationNumber\" "+eatthid+" value=\""+d.spTaxRegistrationNumber+"\" "+validate(30,1,"registration")+">";
        		}
        		
        		
        		
        		if(d.spTaxRegistrationScannedCopies){
        			var spTaxRegistrationScannedCopies_htm = "<a class=\"\" href=\""+iCloudPath+"center/showImg?imge="+d.spTaxRegistrationScannedCopies+"\" target=\"_blank\">" +
														 "<img src=\""+iCloudPath+"fileUpload/downloadFiles?fileKey="+d.spTaxRegistrationScannedCopies+"\" alt=\"\" width=\"122\" height=\"92\" /></a>";
        			
        			$("#spTaxRegistrationScannedCopiesdev").html(spTaxRegistrationScannedCopies_htm);
        			initInputVal("spTaxRegistrationScannedCopies",d.spTaxRegistrationScannedCopies);
        			$("#spTaxRegistrationScannedCopies_css").addClass("hidden");
        		}
        		
        	
        		
        		if(!d.spTaxRegistrationScannedCopies&&!d.spOrganizationScannedCopies){
        			var phoneVerify_htm = "<input id=\"phoneVerify\" name=\"phoneVerify\" "+eattStr+" "+validate(6,6,"verifyPhoneCode")+">";
        			$("#phoneVerifydev").html(phoneVerify_htm);
        		}else{
        			$("#agreements_tr_h").addClass("hidden");
        			$("#hostingsub_tr").addClass("hidden");
        			$("#phoneVerifydev_tr").addClass("hidden");
        			$("#waitId_tr").addClass("hidden");
        		}
        		$("#spNamedev").html(spName_htm);
        		$("#spComContactNamedev").html(spComContactName_htm);
        		$("#spBizLicenseNumdev").html(spBizLicenseNum_htm);
        		
        		$("#spOrganizationCodedev").html(spOrganizationCode_htm);
        		
        		$("#spTaxRegistrationNumberdev").html(spTaxRegistrationNumber_htm);
        		/*绑定校验事件*/
        		if($("#tip").length<1){
        			$().common.valid.init($("#container"));
        		}
        		$('#jqmLoading').jqmHide();
        		if(1==audit){
        			iniUpdata(audit);
        		}
        	}
        	
        }
    });
}

function slectedFun(){
	$('#spBankLocationProvince').change(function(){
		var val = $(this).children('option:selected').val();
		createInput("spBankLocationProvince",val);

	});
	$('#spBankLocationCity').change(function(){ 
		var val = $(this).children('option:selected').val();
		createInput("spBankLocationCity",val);
	});

	$('#spBankLocationDistrict').change(function(){ 
		var val = $(this).children('option:selected').val();
		createInput("spBankLocationDistrict",val);
	});
	
	$('#paperworkType').change(function(){ 
		var val = $(this).children('option:selected').val();
		createInput("paperworkType",val);
	});
	
}

function createInput(sid,val){
	if(sid && val){
		var str = "<input id=\""+sid+"\" class=\"hidden\" name=\""+sid+"\" value=\""+val+"\"/>";
		$("#"+sid+"_hdev").html(str);
	}
}

/**
 * 初始化  服务市场 ，众包服务市场，模板市场 注册页面
 * 
 * initCertifiedFw_e
 * 2013-11-06
 * author guoxudong
 * 
 * */
function initCertifiedFw_e(audit){
	
	$.ajax({
        url: 'getCertifiedFwInfo?_='+(new Date()).valueOf(),
        type: 'GET',
        dataType: 'json',
        timeout: TIMEOUT,
        error: function(){
        },
        success: function(d){

        	if(1==d.code){
        		
        		//业务信息
        		var spName_htm = "<input id=\"spName\" name=\"spName\" "+eattStr+" "+validate(30,1,"string")+">";	//服务商名称
        		var spComContactName_htm = "<input id=\"spComContactName\" name=\"spComContactName\" "+eattStr+" "+validate(30,1,"string")+">";	//企业联系人姓名
        		var spBizLicenseNum_htm = "<input id=\"spBizLicenseNum\" name=\"spBizLicenseNum\" "+eattStr+" "+validate(15,15,"validBusCode")+">";	//营业执照号
        		//var spBizLicenseScannedCopies_htm = scan_spb;	//营业执照号扫描件
        		var spOrganizationCode_htm = "<input id=\"spOrganizationCode\" name=\"spOrganizationCode\" "+eattStr+" "+validate(10,10,"organization")+">";	//组织机构代码
        		//var spOrganizationScannedCopies_htm = scan_spo;	//组织机构代码扫描件
        		var spTaxRegistrationNumber_htm = "<input id=\"spTaxRegistrationNumber\" name=\"spTaxRegistrationNumber\" "+eattStr+" "+validate(15,15,"registration")+">";	//税务登记号//        		//var spTaxRegistrationScannedCopies_htm = scan_spt;	//税务登记号扫描件
        		var spServicePhone_htm = "<input id=\"spServicePhone\" name=\"spServicePhone\" "+eattStr+" "+validate(13,5,"serviceTel")+">"; 	//客服电话
        		var spBankAccountName_htm = "<input id=\"spBankAccountName\" name=\"spBankAccountName\" "+eattStr+" "+validate(50,1,"string")+">";	//银行开户名
        		var spCompanyBankAccount_htm = "<input id=\"spCompanyBankAccount\" name=\"spCompanyBankAccount\" "+eattStr+" "+validate(50,1,"string")+">";	//公司银行账号
        		var spAccountLicenseNum_htm = "<input id=\"spAccountLicenseNum\" name=\"spAccountLicenseNum\" "+eattStr+" "+validate(14,14,"permit")+">";	//税务登记号
        		
        		var spBankLocation_htm = "<select id=\"spBankLocationProvince\" class=\"select w100\" ></select>";	//开户行所在地
        			spBankLocation_htm += "<select id=\"spBankLocationCity\" class=\"select w100\" ></select>";
        			spBankLocation_htm += "<select id=\"spBankLocationDistrict\" class=\"select w100\" ></select>";
        		var spServiceEmail_htm = "<input id=\"spServiceEmail\" name=\"spServiceEmail\" "+eattStr+" "+validate(50,6,"email")+">";	//客服邮箱
        		
        		//20140520
        		var spLegalName_htm = "<input id=\"spLegalName\" name=\"spLegalName\" "+eattStr+" "+validate(20,1,"string")+">";	//法人身份证号
        		var spLegalIdCard_htm = "<input id=\"spLegalIdCard\" name=\"spLegalIdCard\" "+eattStr+" "+validate(18,15,"idcard")+">";	//法人身份证号
        		var spBusinessLicenseLocation_htm = "<input id=\"spBusinessLicenseLocation\" name=\"spBusinessLicenseLocation\" "+eattStr+" "+validate(50,1,"string")+">";	//营业执照所在地
        		var spValidBusinessLicense_htm = "<input id=\"spValidBusinessLicense\" name=\"spValidBusinessLicense\" "+eattStr+" "+validate(50,1,"spValidBusinessLicenseVerify")+">" +
        				"<br><br><span>如：自 2014年1月1日 至 2015年1月1日</span>";	//营业执照有效期
        		var spTaxTyp_htm = "<input id=\"spTaxTyp\" name=\"spTaxTyp\" "+eattStr+" "+validate(50,1,"string")+">" +
        				"<br><br><span>如：一般纳税人</span>";	//纳税类型
        		var spTaxTypeCode_htm = "<input id=\"spTaxTypeCode\" name=\"spTaxTypeCode\" "+eattStr+" "+validate(20,1,"string")+">" +
        				"<br><br><span>如：3%</span>";	//纳税类型代码
        		var spEnterpriseType_htm = "<input id=\"spEnterpriseType\" name=\"spEnterpriseType\" "+eattStr+" "+validate(20,1,"string")+">";	//企业类型
        		var spRegisteredCapital_htm = "<input id=\"spRegisteredCapital\" name=\"spRegisteredCapital\" "+eattStr+" "+validate(20,1,"string")+">";	//注册资金
        		var spBusinessScope_htm = "<input id=\"spBusinessScope\" name=\"spBusinessScope\" "+eattStr+" "+validate(50,1,"string")+">";	//经营范围
        		var spBankBranchName_htm = "<input id=\"spBankBranchName\" name=\"spBankBranchName\" "+eattStr+" "+validate(50,1,"string")+">";	//开会支行名称
        		var spBankBranchNum_htm = "<input id=\"spBankBranchNum\" name=\"spBankBranchNum\" "+eattStr+" "+validate(12,12,"int")+">";	//开户行支行联行号
        		var spCompanyAddress_htm = "<input id=\"spCompanyAddress\" name=\"spCompanyAddress\" "+eattStr+" "+validate(50,1,"string")+">";	//公司地址
        		
        		if(d.spLegalName){
        			spLegalName_htm = "<span id=\"spLegalName_s\">"+d.spLegalName+"</span>";
        			spLegalName_htm += "<input id=\"spLegalName\" name=\"spLegalName\" "+eatthid+" value=\""+d.spLegalName+"\" "+validate(18,15,"string")+">";
        			initInputVal("spLegalName",d.spLegalName);
        		}
        		if(d.spLegalIdCard){
        			spLegalIdCard_htm = "<span id=\"spLegalIdCard_s\">"+d.spLegalIdCard+"</span>";
        			spLegalIdCard_htm += "<input id=\"spLegalIdCard\" name=\"spLegalIdCard\" "+eatthid+" value=\""+d.spLegalIdCard+"\" "+validate(18,15,"idcard")+">";
        			initInputVal("spLegalIdCard",d.spLegalIdCard);
        		}
        		if(d.spBusinessLicenseLocation){
        			spBusinessLicenseLocation_htm = "<span id=\"spBusinessLicenseLocation_s\">"+d.spBusinessLicenseLocation+"</span>";
        			spBusinessLicenseLocation_htm += "<input id=\"spBusinessLicenseLocation\" name=\"spBusinessLicenseLocation\" "+eatthid+" value=\""+d.spBusinessLicenseLocation+"\" "+validate(50,1,"string")+">";
        			initInputVal("spBusinessLicenseLocation",d.spBusinessLicenseLocation);
        		}
        		if(d.spValidBusinessLicense){
        			spValidBusinessLicense_htm = "<span id=\"spValidBusinessLicense_s\">"+d.spValidBusinessLicense+"</span>";
        			spValidBusinessLicense_htm += "<input id=\"spValidBusinessLicense\" name=\"spValidBusinessLicense\" "+eatthid+" value=\""+d.spValidBusinessLicense+"\" "+validate(50,1,"string")+">";
        			initInputVal("spValidBusinessLicense",d.spValidBusinessLicense);
        		}
        		if(d.spTaxTyp){
        			spTaxTyp_htm = "<span id=\"spTaxTyp_s\">"+d.spTaxTyp+"</span>";
        			spTaxTyp_htm += "<input id=\"spTaxTyp\" name=\"spTaxTyp\" "+eatthid+" value=\""+d.spTaxTyp+"\" "+validate(20,1,"string")+">";
        			initInputVal("spTaxTyp",d.spTaxTyp);
        		}
        		
        		if(d.spTaxTypeCode){
        			spTaxTypeCode_htm = "<span id=\"spTaxTypeCode_s\">"+d.spTaxTypeCode+"</span>";
        			spTaxTypeCode_htm += "<input id=\"spTaxTypeCode\" name=\"spTaxTypeCode\" "+eatthid+" value=\""+d.spTaxTypeCode+"\" "+validate(20,1,"string")+">";
        			initInputVal("spTaxTypeCode",d.spTaxTypeCode);
        		}
        		
        		if(d.spEnterpriseType){
        			spEnterpriseType_htm = "<span id=\"spEnterpriseType_s\">"+d.spEnterpriseType+"</span>";
        			spEnterpriseType_htm += "<input id=\"spEnterpriseType\" name=\"spEnterpriseType\" "+eatthid+" value=\""+d.spEnterpriseType+"\" "+validate(20,1,"string")+">";
        			initInputVal("spEnterpriseType",d.spEnterpriseType);
        		}
        		
        		if(d.spRegisteredCapital){
        			spRegisteredCapital_htm = "<span id=\"spRegisteredCapital_s\">"+d.spRegisteredCapital+"</span>";
        			spRegisteredCapital_htm += "<input id=\"spRegisteredCapital\" name=\"spRegisteredCapital\" "+eatthid+" value=\""+d.spRegisteredCapital+"\" "+validate(50,1,"string")+">";
        			initInputVal("spRegisteredCapital",d.spRegisteredCapital);
        		}
        		
        		if(d.spBusinessScope){
        			spBusinessScope_htm = "<span id=\"spBusinessScope_s\">"+d.spBusinessScope+"</span>";
        			spBusinessScope_htm += "<input id=\"spBusinessScope\" name=\"spBusinessScope\" "+eatthid+" value=\""+d.spBusinessScope+"\" "+validate(50,1,"string")+">";
        			initInputVal("spBusinessScope",d.spBusinessScope);
        		}
        		
        		if(d.spBankBranchName){
        			spBankBranchName_htm = "<span id=\"spBankBranchName_s\">"+d.spBankBranchName+"</span>";
        			spBankBranchName_htm += "<input id=\"spBankBranchName\" name=\"spBankBranchName\" "+eatthid+" value=\""+d.spBankBranchName+"\" "+validate(50,1,"string")+">";
        			initInputVal("spBankBranchName",d.spBankBranchName);
        		}
        		if(d.spBankBranchNum){
        			spBankBranchNum_htm = "<span id=\"spBankBranchNum_s\">"+d.spBankBranchNum+"</span>";
        			spBankBranchNum_htm += "<input id=\"spBankBranchNum\" name=\"spBankBranchNum\" "+eatthid+" value=\""+d.spBankBranchNum+"\" "+validate(12,12,"int")+">";
        			initInputVal("spBankBranchNum",d.spBankBranchNum);
        		}
        		if(d.spCompanyAddress){
        			spCompanyAddress_htm = "<span id=\"spCompanyAddress_s\">"+d.spCompanyAddress+"</span>";
        			spCompanyAddress_htm += "<input id=\"spCompanyAddress\" name=\"spCompanyAddress\" "+eatthid+" value=\""+d.spCompanyAddress+"\" "+validate(50,1,"string")+">";
        			initInputVal("spCompanyAddress",d.spCompanyAddress);
        		}
        		
        		if(d.spCNASAuthScanning){
        			var spCNASAuthScanning_htm = "<a class=\"\" href=\""+iCloudPath+"center/showImg?imge="+d.spCNASAuthScanning+"\" target=\"_blank\">" +
        		  							"<img src=\""+iCloudPath+"fileUpload/downloadFiles?fileKey="+d.spCNASAuthScanning+"\" alt=\"\" width=\"122\" height=\"92\" /></a>";
        			initInputVal("spCNASAuthScanning",d.spCNASAuthScanning);
        			$("#spCNASAuthScanningdev").html(spCNASAuthScanning_htm);
        			$("#spCNASAuthScanning_css").addClass("hidden");
        		}
        		
        		if(d.spCMAAutScanning){
        			var spCMAAutScanning_htm = "<a class=\"\" href=\""+iCloudPath+"center/showImg?imge="+d.spCMAAutScanning+"\" target=\"_blank\">" +
        		  							"<img src=\""+iCloudPath+"fileUpload/downloadFiles?fileKey="+d.spCMAAutScanning+"\" alt=\"\" width=\"122\" height=\"92\" /></a>";
        			initInputVal("spCMAAutScanning",d.spCMAAutScanning);
        			$("#spCMAAutScanningdev").html(spCMAAutScanning_htm);
        			$("#spCMAAutScanning_css").addClass("hidden");
        		}
        		// 20140520   end
        		
        		if(d.spName){
        			spName_htm = "<span id=\"spName_s\">"+d.spName+"</span>";
        			spName_htm += "<input id=\"spName\" name=\"spName\" "+eatthid+" value=\""+d.spName+"\" "+validate(30,1,"string")+">";
        			initInputVal("spName",d.spName);
        		}
        		if(d.spComContactName){
        			spComContactName_htm = "<span id=\"spComContactName_s\">"+d.spComContactName+"</span>";
        			initInputVal("spComContactName",d.spComContactName);
        			spComContactName_htm += "<input id=\"spComContactName\" name=\"spComContactName\" "+eatthid+" value=\""+d.spComContactName+"\" "+validate(30,1,"string")+">";
        		}
        		if(d.spBizLicenseNum){
        			spBizLicenseNum_htm = "<span id=\"spBizLicenseNum_s\">"+d.spBizLicenseNum+"</span>";
        			initInputVal("spBizLicenseNum",d.spBizLicenseNum);
        			spBizLicenseNum_htm += "<input id=\"spBizLicenseNum\" name=\"spBizLicenseNum\" "+eatthid+" value=\""+d.spBizLicenseNum+"\" "+validate(30,1,"validBusCode")+">";
        		}
        		if(d.spBizLicenseScannedCopies){
        			var spBizLicenseScannedCopies_htm = "<a class=\"\" href=\""+iCloudPath+"center/showImg?imge="+d.spBizLicenseScannedCopies+"\" target=\"_blank\">" +
        		  							"<img src=\""+iCloudPath+"fileUpload/downloadFiles?fileKey="+d.spBizLicenseScannedCopies+"\" alt=\"\" width=\"122\" height=\"92\" /></a>";
        			initInputVal("spBizLicenseScannedCopies",d.spBizLicenseScannedCopies);
        			$("#spBizLicenseScannedCopiesdev").html(spBizLicenseScannedCopies_htm);
        			$("#spBizLicenseScannedCopies_css").addClass("hidden");
        		}

        		
        		if(d.spOrganizationCode){
        			spOrganizationCode_htm = "<span id=\"spOrganizationCode_s\">"+d.spOrganizationCode+"</span>";
        			initInputVal("spOrganizationCode",d.spOrganizationCode);
        			spOrganizationCode_htm += "<input id=\"spOrganizationCode\" name=\"spOrganizationCode\" "+eatthid+" value=\""+d.spOrganizationCode+"\" "+validate(30,1,"organization")+">";
        		}
        		if(d.spOrganizationScannedCopies){
        			var spOrganizationScannedCopies_htm = "<a class=\"\" href=\""+iCloudPath+"center/showImg?imge="+d.spOrganizationScannedCopies+"\" target=\"_blank\">" +
        											  "<img src=\""+iCloudPath+"fileUpload/downloadFiles?fileKey="+d.spOrganizationScannedCopies+"\" alt=\"\" width=\"122\" height=\"92\" /></a>";
        			initInputVal("spOrganizationScannedCopies",d.spOrganizationScannedCopies);
            		$("#spOrganizationScannedCopiesdev").html(spOrganizationScannedCopies_htm);
            		$("#spOrganizationScannedCopies_css").addClass("hidden");
        		}

        		
        		if(d.spTaxRegistrationNumber){
        			spTaxRegistrationNumber_htm = "<span id=\"spTaxRegistrationNumber_s\">"+d.spTaxRegistrationNumber+"</span>";
        			initInputVal("spTaxRegistrationNumber",d.spTaxRegistrationNumber);
        			spTaxRegistrationNumber_htm += "<input id=\"spTaxRegistrationNumber\" name=\"spTaxRegistrationNumber\" "+eatthid+" value=\""+d.spTaxRegistrationNumber+"\" "+validate(30,1,"registration")+">";
        		}
        		if(d.spTaxRegistrationScannedCopies){
        			var spTaxRegistrationScannedCopies_htm = "<a class=\"\" href=\""+iCloudPath+"center/showImg?imge="+d.spTaxRegistrationScannedCopies+"\" target=\"_blank\">" +
        										 "<img src=\""+iCloudPath+"fileUpload/downloadFiles?fileKey="+d.spTaxRegistrationScannedCopies+"\" alt=\"\" width=\"122\" height=\"92\" /></a>";
        			initInputVal("spTaxRegistrationScannedCopies",d.spTaxRegistrationScannedCopies);
        			$("#spTaxRegistrationScannedCopiesdev").html(spTaxRegistrationScannedCopies_htm);
        			$("#spTaxRegistrationScannedCopies_css").addClass("hidden");
        		}
        		
        		if(d.spAccountLicenseNum){
        			spAccountLicenseNum_htm = "<span id=\"spAccountLicenseNum_s\">"+d.spAccountLicenseNum+"</span>";
        			spAccountLicenseNum_htm += "<input id=\"spAccountLicenseNum\" name=\"spAccountLicenseNum\" "+eatthid+" value=\""+d.spAccountLicenseNum+"\" "+validate(14,14,"permit")+">";
        			initInputVal("spAccountLicenseNum",d.spAccountLicenseNum);
        		}
        		
        		if(d.spAccountLicenseScannedCopies){
        			var spAccountLicenseScannedCopies_htm = "<a class=\"\" href=\""+iCloudPath+"center/showImg?imge="+d.spAccountLicenseScannedCopies+"\" target=\"_blank\">" +
														 "<img src=\""+iCloudPath+"fileUpload/downloadFiles?fileKey="+d.spAccountLicenseScannedCopies+"\" alt=\"\" width=\"122\" height=\"92\" /></a>";
        			initInputVal("spAccountLicenseScannedCopies",d.spAccountLicenseScannedCopies);
        			$("#spAccountLicenseScannedCopiesdev").html(spAccountLicenseScannedCopies_htm);
        			$("#spAccountLicenseScannedCopies_css").addClass("hidden");
        		}
        		
        		if(d.spServicePhone){
        			spServicePhone_htm = "<span id=\"spServicePhone_s\">"+d.spServicePhone+"</span>";
        			initInputVal("spServicePhone",d.spServicePhone);
        			spServicePhone_htm += "<input id=\"spServicePhone\" name=\"spServicePhone\" "+eatthid+" value=\""+d.spServicePhone+"\" "+validate(30,5,"serviceTel")+">";
        		}

        		if(d.spBankAccountName){
        			spBankAccountName_htm = "<span id=\"spBankAccountName_s\">"+d.spBankAccountName+"</span>";
        			initInputVal("spBankAccountName",d.spBankAccountName);
        			spBankAccountName_htm += "<input id=\"spBankAccountName\" name=\"spBankAccountName\" "+eatthid+" value=\""+d.spBankAccountName+"\" "+validate(30,1,"string")+">";

        		}
        		
        		if(d.spCompanyBankAccount){
        			spCompanyBankAccount_htm = "<span id=\"spCompanyBankAccount_s\">"+d.spCompanyBankAccount+"</span>";
        			initInputVal("spCompanyBankAccount",d.spCompanyBankAccount);
        			spCompanyBankAccount_htm += "<input id=\"spCompanyBankAccount\" name=\"spCompanyBankAccount\" "+eatthid+" value=\""+d.spCompanyBankAccount+"\" "+validate(30,1,"string")+">";
        		}
        		if(d.spBankLocationProvince){
        			spBankLocation_htm = "<span id=\"spBankLocation_s\">"+d.spBankLocationProvince+""+d.spBankLocationCity+""+d.spBankLocationDistrict+"</span>";
        			initInputVal("spBankLocationProvince",d.spBankLocationProvince);
        			initInputVal("spBankLocationCity",d.spBankLocationCity);
        			initInputVal("spBankLocationDistrict",d.spBankLocationDistrict);
        			spBankLocation_htm += "<div id=\"spBankLocation\" class=\"hidden\"><select id=\"spBankLocationProvince\" class=\"select w100\" ></select>";	//开户行所在地
        			spBankLocation_htm += "<select id=\"spBankLocationCity\" class=\"select w100\" ></select>";
        			spBankLocation_htm += "<select id=\"spBankLocationDistrict\" class=\"select w100\" ></select></div>";
        		}
        		if(d.spServiceEmail){
        			spServiceEmail_htm = "<span id=\"spServiceEmail_s\">"+d.spServiceEmail+"</span>";
        			initInputVal("spServiceEmail",d.spServiceEmail);
        			spServiceEmail_htm += "<input id=\"spServiceEmail\" name=\"spServiceEmail\" "+eatthid+" value=\""+d.spServiceEmail+"\" "+validate(30,1,"string")+">";
        		}
        		
        		//var phoneVerify_htm = "<input id=\"phoneVerify\" name=\"phoneVerify\" "+eatthid+" "+validate(6,6,"verifyPhoneCode")+">";
    			//$("#phoneVerifydev").html(phoneVerify_htm);
    			if(!d.spServiceEmail ||!d.spCompanyBankAccount||!d.spServicePhone ||!d.spLegalName){
        			$("#agreements_tr").removeClass("hidden");
        		}else{
        			$("#agreements_tr").addClass("hidden");
        			$("#agreements_tr_e").addClass("hidden");
        			$("#certifiledsub_tr").addClass("hidden");
        			$("#phoneVerifydev_tr").addClass("hidden");
        			$("#waitId_tr").addClass("hidden");
        		}
        		$("#spNamedev").html(spName_htm);
        		$("#spComContactNamedev").html(spComContactName_htm);
        		$("#spBizLicenseNumdev").html(spBizLicenseNum_htm);
        		
        		$("#spOrganizationCodedev").html(spOrganizationCode_htm);
        		
        		$("#spTaxRegistrationNumberdev").html(spTaxRegistrationNumber_htm);
        		
        		$("#spServicePhonedev").html(spServicePhone_htm);
        		$("#spBankAccountNamedev").html(spBankAccountName_htm);
        		$("#spCompanyBankAccountdev").html(spCompanyBankAccount_htm);
        		$("#spAccountLicenseNumdev").html(spAccountLicenseNum_htm);
        		$("#spBankLocationdev").html(spBankLocation_htm);
        		$("#spServiceEmaildev").html(spServiceEmail_htm);
        		
        		//20140520  ->
        		$("#spLegalNamedev").html(spLegalName_htm);
        		$("#spLegalIdCarddev").html(spLegalIdCard_htm);
        		$("#spBusinessLicenseLocationdev").html(spBusinessLicenseLocation_htm);
        		$("#spValidBusinessLicensedev").html(spValidBusinessLicense_htm);
        		$("#spTaxTypdev").html(spTaxTyp_htm);
        		$("#spTaxTypeCodedev").html(spTaxTypeCode_htm);
        		$("#spEnterpriseTypedev").html(spEnterpriseType_htm);
        		$("#spRegisteredCapitaldev").html(spRegisteredCapital_htm);
        		$("#spBusinessScopedev").html(spBusinessScope_htm);
        		$("#spBankBranchNamedev").html(spBankBranchName_htm);
        		$("#spBankBranchNumdev").html(spBankBranchNum_htm);
        		$("#spCompanyAddressdev").html(spCompanyAddress_htm);
        		//20140520  <-
        		
        		_init_area(5);
        		slectedFun();
        		
        		tipVal1 = 1;
        		if(1==audit){
        			updataCertifideFwBtnFun();
        			$("#certifiledsub_tr").removeClass("hidden");
        			$("#certifideFwBtn").removeClass("disabled");
        		}
        		if(!$("#updataCertifideFwBtnSub").hasClass("hidden")){
        			$("#updataCertifideFwBtn").addClass("hidden");
        		}
        	}
        }
    });
	
}

/**
 * 初始化认证开发者 个人 注册页面
 * 
 * initCertifiedFw_p
 * 2013-11-06
 * author guoxudong
 * 
 * */
function initCertifiedFw_p(audit){
	
	$.ajax({
        url: 'getCertifiedDevInfo?_='+(new Date()).valueOf(),
        type: 'GET',
        dataType: 'json',
        timeout: TIMEOUT,
        error: function(){
        },
        success: function(d){

        	if(1==d.code){
        		//业务信息
    			var name_htm = "<input id=\"name\" name=\"name\" "+eattStr+" "+validate(30,1,"string")+" >";
    			var paperworkType_htm = idtype;
    				createInput("paperworkType",1);
    			var idCardCode_htm = "<input id=\"idCardCode\" name=\"idCardCode\" "+eattStr+" "+validate(18,6,"string")+" >";
        		var spPersonalServicePhone_htm = "<input id=\"spPersonalServicePhone\" name=\"spPersonalServicePhone\" "+eattStr+" "+validate(13,11,"serviceTel")+" >";
        		var spPersonalMail_htm = "<input id=\"spPersonalMail\" name=\"spPersonalMail\" "+eattStr+" "+validate(30,6,"email")+" >";
        		
        		//20140520
        		var spPersonalPayeeName_htm = "<input id=\"spPersonalPayeeName\" name=\"spPersonalPayeeName\" "+eattStr+" "+validate(30,1,"string")+" >";
        		var spPersonalPayeePhone_htm = "<input id=\"spPersonalPayeePhone\" name=\"spPersonalPayeePhone\" "+eattStr+" "+validate(13,11,"serviceTel")+" >";
        		var spPersonalBankName_htm = "<input id=\"spPersonalBankName\" name=\"spPersonalBankName\" "+eattStr+" "+validate(30,1,"string")+" >";
        		var spPersonalBankAccount_htm = "<input id=\"spPersonalBankAccount\" name=\"spPersonalBankAccount\" "+eattStr+" "+validate(30,1,"int")+" >";
        		
        		if(d.name){
        			name_htm = "<span id=\"name_s\" >"+d.name+"</span>";
        			name_htm += "<input id=\"name\" name=\"name\" "+eatthid+" value=\""+d.name+"\" "+validate(30,1,"string")+">";
        		}
        		if(d.paperworkType){
        			paperworkType_htm = "<span id=\"paperworkType_s\">"+getIdCardTxt(d.paperworkType)+"</span>";
        			paperworkType_htm += "<select id=\"paperworkType\" class=\"select w100 hidden\">" +
        					"<option value=1>身份证</option>" +
        					"<option value=2>护照</option>" +
        					"<option value=4>军官证</option>" +
        					"</select>";
        		}
        		
        		if(d.idCardCode){
        			idCardCode_htm = "<span id=\"idCardCode_s\">"+d.idCardCode+"</span>";
        			idCardCode_htm += "<input id=\"idCardCode\" name=\"idCardCode\" "+eatthid+" value=\""+d.idCardCode+"\" "+validate(18,6,"string")+">";
        		}
        		
        		if(d.spPersonalServicePhone){
        			spPersonalServicePhone_htm = "<span id=\"spPersonalServicePhone_s\">"+d.spPersonalServicePhone+"</span>";
        			spPersonalServicePhone_htm += "<input id=\"spPersonalServicePhone\" name=\"spPersonalServicePhone\" "+eatthid+" value=\""+d.spPersonalServicePhone+"\" "+validate(13,11,"serviceTel")+">";
        		}
        		
        		if(d.spPersonalMail){
        			spPersonalMail_htm = "<span id=\"spPersonalMail_s\">"+d.spPersonalMail+"</span>";
        			spPersonalMail_htm += "<input id=\"spPersonalMail\" name=\"spPersonalMail\" "+eatthid+" value=\""+d.spPersonalMail+"\" "+validate(30,6,"email")+">";
        		}
        		
        		if(d.spPersonalPayeeName){
        			spPersonalPayeeName_htm = "<span id=\"spPersonalPayeeName_s\">"+d.spPersonalPayeeName+"</span>";
        			spPersonalPayeeName_htm += "<input id=\"spPersonalPayeeName\" name=\"spPersonalPayeeName\" "+eatthid+" value=\""+d.spPersonalPayeeName+"\" "+validate(30,1,"string")+">";
        		}
        		if(d.spPersonalPayeePhone){
        			spPersonalPayeePhone_htm = "<span id=\"spPersonalPayeePhone_s\">"+d.spPersonalPayeePhone+"</span>";
        			spPersonalPayeePhone_htm += "<input id=\"spPersonalPayeePhone\" name=\"spPersonalPayeePhone\" "+eatthid+" value=\""+d.spPersonalPayeePhone+"\" "+validate(13,11,"servicetel")+">";
        		}
        		if(d.spPersonalBankName){
        			spPersonalBankName_htm = "<span id=\"spPersonalBankName_s\">"+d.spPersonalBankName+"</span>";
        			spPersonalBankName_htm += "<input id=\"spPersonalBankName\" name=\"spPersonalBankName\" "+eatthid+" value=\""+d.spPersonalBankName+"\" "+validate(50,1,"string")+">";
        		}
        		if(d.spPersonalBankAccount){
        			spPersonalBankAccount_htm = "<span id=\"spPersonalBankAccount_s\">"+d.spPersonalBankAccount+"</span>";
        			spPersonalBankAccount_htm += "<input id=\"spPersonalBankAccount\" name=\"spPersonalBankAccount\" "+eatthid+" value=\""+d.spPersonalBankAccount+"\" "+validate(50,6,"string")+">";
        		}
        		
        		
        		if(!d.spPersonalServicePhone || !d.spPersonalMail || !d.spPersonalBankAccount){
        			var phoneVerify_htm = "<input id=\"phoneVerify\" name=\"phoneVerify\" "+eattStr+" "+validate(6,6,"verifyPhoneCode")+">";
        			$("#phoneVerifydev_p").html(phoneVerify_htm);
        		}else{
        			$("#agreements_tr_p").addClass("hidden");
        			$("#regsub_tr_p").addClass("hidden");
        			$("#phoneVerifydev_tr_p").addClass("hidden");
        			$("#waitId_tr_p").addClass("hidden");
        		}
        		$("#namedev").html(name_htm);
        		$("#paperworkTypedev").html(paperworkType_htm);
        		
        		$("#idCardCodedev").html(idCardCode_htm);
        		$("#spPersonalServicePhonedev").html(spPersonalServicePhone_htm);
        		$("#spPersonalMaildev").html(spPersonalMail_htm);
        		
        		//20140520
        		$("#spPersonalPayeeNamedev").html(spPersonalPayeeName_htm);
        		$("#spPersonalPayeePhonedev").html(spPersonalPayeePhone_htm);
        		$("#spPersonalBankNamedev").html(spPersonalBankName_htm);
        		$("#spPersonalBankAccountdev").html(spPersonalBankAccount_htm);
        		
        		
        		slectedFun();
        		tipVal2 = 1;
        		
        		if(1==audit){
        			updatePersonalFwBtnFun();
        			$("#regsub_tr_p").removeClass("hidden");
        			$("#personalFwBtn").removeClass("disabled");
        		}
        	}
        }
    });
}
function tipValFun(){
	function tipValjump(count) {
        window.setTimeout(function(){
            count--;
            if(count > 0) {
            	if(tipVal1 ==1 && tipVal2 ==1){
            		if($("#tip").length > 0){
            			$("a[id='tip']").remove();
            			$().common.valid.init($("#container"));
            		}
                	$('#jqmLoading').jqmHide();
                	return;
            	}
            	tipValjump(count);
            }
        }, 200);
    }
	tipValjump(60);
}


function getIdCardTxt(tid){
	 var arr = []; 
	 arr[1]="身份证";
	 arr[2]="护照";
	 arr[3]="港澳通行证";
	 arr[4]="军官证";
	 arr[5]="回乡证";
	 arr[6]="台胞证";
	 arr[7]="国际海员证";
	 arr[8]="其他";
	 return arr[tid];
}




function iniauthor(){
	$.ajax({
        url: 'getAuthorizationFun?_='+(new Date()).valueOf(),
        type: 'GET',
        dataType: 'json',
        timeout: TIMEOUT,
        error: function(){
        },
        success: function(d){
        	
        	if(1==d.reject){
        		$("#rejectId").removeClass("hidden");
        		$("#rejectId_t").removeClass("hidden");
        		var re = d.rejectObj;
        		for(var i=0;i<re.length;i++){
        			$("#bh_"+re[i]).removeClass("hidden");
        		}
        	}
        	if(1==d.nAudit){
        		$("#nAuditId").removeClass("hidden");
        		$("#nAuditId_t").removeClass("hidden");
        		var nau = d.nAuditObj;
        		for(var i=0;i<nau.length;i++){
        			$("#dsh_"+nau[i]).removeClass("hidden");
        		}
        	}
        	if(1==d.nApply){
        		$("#nApplyId").removeClass("hidden");
        		$("#nApplyId_t").removeClass("hidden");
        		var nap = d.nApplyObj;
        		for(var i=0;i<nap.length;i++){
        			$("#wsq_"+nap[i]).removeClass("hidden");
        		}
        		
        	}
        	if(1==d.approved){
        		$("#approvedId").removeClass("hidden");
        		$("#approvedId_t").removeClass("hidden");
        		var ap = d.approvedObj;
        		for(var i=0;i<ap.length;i++){
        			$("#approved_"+ap[i]).removeClass("hidden");
        		}
        	}
        	if(1==d.approved || 1==d.nAudit){
        		$("#approved_jshop").removeClass("hidden");
        		$("#nApplyId_jshop").addClass("hidden");
        	}
        	if(0==d.approved){
        		$("#approvedId_t").addClass("hidden");
        	}
        	if(0==d.nAudit && 0==d.nApply && 0==d.reject){
        		$("#noserviceId").addClass("hidden");
        	}
        	$('#jqmLoading').jqmHide();
        }
    });
}


/**
 *  授权 页面
 * 
 * getAuditInfoFun
 * 2013-11-06
 * author guoxudong
 * 
 * */
function getAuditInfoFun(categorysCode){
	$.ajax({
        url: 'getAuditInfoFun?_='+(new Date()).valueOf(),
        type: 'GET',
        data: {
        	categorysCode:categorysCode
        	},
        dataType: 'json',
        timeout: TIMEOUT,
        error: function(){
        },
        success: function(d){
        	if(2==d.stutas){
        		$("#descShowId").html(d.desc);
        		
        		$('#addOperator').jqm({
        			w:550,
        			closeClass: 'jqmClose',
        			overlayClass: 'whiteOverlay',
        			retrieveTop:function(){
        				return 20;
        			},
        			movable: true
        		});
        		$('#addOperator').jqmShow();
        	}
        }
    });
}

function inisu(){
	$.ajax({
        url: 'getAuthorizationFun?_='+(new Date()).valueOf(),
        type: 'GET',
        dataType: 'json',
        timeout: TIMEOUT,
        error: function(){
        },
        success: function(d){
        
        	if(1==d.approved){
        		var ap = d.approvedObj;
        		for(var i=0;i<ap.length;i++){
        			$("#"+ap[i]).removeClass("hidden");
        		}
        	}else if(0==d.approved){
        		$("#showCategoryId").addClass("hidden");
        	}
        }
    });
}

function iniUpdata(audit){
	if(1==audit){
			ediHidden("spName");
			ediHidden("spComContactName");
			ediHidden("spBizLicenseNum");
			ediHidden("spBizLicenseScannedCopies");
			ediHidden("spOrganizationCode");
			ediHidden("spOrganizationScannedCopies");
			ediHidden("spTaxRegistrationNumber");
			ediHidden("spTaxRegistrationScannedCopies");
			ediHidden("spAccountLicenseNum");
			ediHidden("spAccountLicenseScannedCopies");
			ediHidden("spServicePhone");
			ediHidden("spBankAccountName");
			ediHidden("spCompanyBankAccount");
			ediHidden("spBankLocation");
			ediHidden("spServiceEmail");
			copiesHidden("spTaxRegistrationScannedCopies");
			copiesHidden("spBizLicenseScannedCopies");
			copiesHidden("spOrganizationScannedCopies");
			copiesHidden("spAccountLicenseScannedCopies");
			$("#hostingsub_tr").removeClass("hidden");
			$("#hostingBtn").removeClass("disabled");
	}
}
function ediBtnStutas(p,e){
	if(1==p){
		$("#updatePersonalFwBtnSub").addClass("hidden");
	}
	if(1==e){
		$("#updataCertifideFwBtnSub").addClass("hidden");
	}
}

function initPageType(audit){
	if(undefined!=audit){
		if(1==audit.AUTH_ENTERPRISE_DEV){
			$("#personalLiId").addClass("hidden");
		}else if(1==audit.AUTH_INDIVIDUAL_DEV){
			$("#enterpriseLiId").addClass("hidden");
			$("#enterprise").addClass("hidden");
			$("#personal").removeClass("hidden");
			$("#personalId").addClass("curr");
			$("#btnLiId").removeClass("hidden");
		}
	}
}

function upgradeEnterpriseFun(){
	$("#personal").addClass("hidden");
	$("#personalLiId").addClass("hidden");
	$("#btnLiId").addClass("hidden");
	$("#enterpriseLiId").removeClass("hidden");
	$("#enterprise").removeClass("hidden");
}

/**
 * 认证用户 有平台 来源  企业 提交
 * 2013-11-06
 * author guoxudong
 **/
function upgradeEnterpriseBtnSubFun(){
	  $("#upgrade_enterprise_form").ajaxSubmit({
	        url:iCloudPath+"user/info/upgradeEnterpriseFun",
	        type : 'POST',
	        dataType:"json",
	    	timeout:TIMEOUT,
	    	error:function(d) {
	 				return;
	    	},
	        success: function(d) {
	    		if(1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","success",d.msg,2000);
	    			 location=location;
	    			return;
	    		}else if(-1==d.code){
	    			$.fn.utility.tipsv1.simple ("提示","failure",cueStr,5000);
	    			return;
	    		}else{
	    			$.fn.utility.tipsv1.simple ("提示","failure",d.msg,2000);
	    			return;
	    		}
            }
	   });
}

/**
 * 获取待审核信息
 * 2013-11-06
 * author  guoxudong
 **/
function showAuditInfoFun(conditionId){
	$.ajax({
		url : 'showAuditInfoFun',
		type : 'GET',
		data : {
			conditionId : conditionId
		},
		dataType : 'json',
		timeout : TIMEOUT,
		error : function(d) {
			return;
		}, 
		success : function(d) {
			$("#titleId").html("待审核信息");
			var agrBtn="<a id=\"closeAuditInfoBtn\" class=\"btn_common\">确定</a>";
			$("#closeBtn").html(agrBtn);
			$("#agreementsTxt").html(initshowAuditInfoFun(d));
			$('#addOperator').jqmShow();
			initClouseBtn();
		}
	});
	
}

function initshowAuditInfoFun(d){
	
	var htmlStr = "<div style=\"margin-top: 30px;margin-left: 50px;\">";
	if(1==d.code){
		//业务信息
		if(d.name)htmlStr += structuralTags("姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名","name",d.name);
		
		if(d.paperworkType)htmlStr += structuralTags("证件类型","paperworkType",getIdCardTxt(d.paperworkType));
		
		if(d.idCardCode)htmlStr += structuralTags("证件号码","idCardCode",d.idCardCode);
		
		if(d.spPersonalServicePhone)htmlStr += structuralTags("客服电话","spPersonalServicePhone",d.spPersonalServicePhone);
		
		if(d.spPersonalMail)htmlStr += structuralTags("客服邮箱","spPersonalMail",d.spPersonalMail);
	}
	htmlStr += "</div>";
	return htmlStr;
}

function structuralTags(n,k,v){
	var str = "<div><lable>"+n+"&nbsp;:&nbsp;</lable><span id=\""+k+"\">"+v+"</span></div>";
	return str;
}

function identifyingSources(obj,type){
	if(2==type && (1==obj.AUTH_ENTERPRISE_DEV || 1==obj.AUTH_INDIVIDUAL_DEV)){
		return;
	}else if(1==type && 1==obj.REG_DEV){
		return;
	}
	
	var plaform = $("#platform").val();
	if (typeof(plaform) == "undefined"
			|| "" ==plaform
			|| "" ==null
			|| "" =="null"){
		var str= "请根据需求&nbsp;<a href=\"http://i.jcloud.com/user/info/authorization\" style=\"font-size: 15px;color: orange;font-weight: bolder;\">选择对应平台</a>&nbsp;进行注册！";
		$.fn.utility.tipsv1.simple ("提示","failure",str,0);
		$("#isShowCloseId").css("display","none");
		$("#failureShowId").css("display","none");
		return;
	}
}

function checkEP(){
	var stutas=false;
	$("a[name='tip']").each(function(){
	       var a = $(this).css('display');
	       if('inline' ==a){
	    	   stutas=true;
	       }
	});
	return stutas;
}

//展开、折叠
function openContraction(tid){
	var flag = $("tr[name='"+tid+"xx']").hasClass("hidden");
	if(flag){
		$("tr[name='"+tid+"xx']").removeClass("hidden");
		$("#"+tid).html("-");
	}else{
		$("tr[name='"+tid+"xx']").addClass("hidden");
		$("#"+tid).html("+");
	}
	
}

function errorPositioning(){
	return;
}