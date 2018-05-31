var Kifpadmin = Kifpadmin || {};

Kifpadmin.priorityapply = {
		
	init : function() {
		var self = this;
		$("#btn_referee").click(function() {
			if ( $("#checkbox_tjr").attr("checked")  != "checked") {
				$.kd.kdMsg("申请前请仔细阅读并勾选同意");
				return ;
		}
			self.identityapply(12);
		});
		$("#btn_investor").click(function() {
			/*if ( $("#checkbox_fqr").attr("checked")  != "checked") {
				$.kd.kdMsg("申请前请仔细阅读并勾选同意");
				return ;
			}*/
			self.identityapply(11);
		});
		$("#btn_leader").click(function() {
			self.identityapply(10);
		});
		$("#btn_limitedcrowdfunding").click(function() {
			if ( $("#checkboxTypeTwo").attr("checked")  != "checked") {
					$.kd.kdMsg("申请前请仔细阅读并勾选同意");
					return ;
			}
			self.transpriorityapply(20);
		});
		$("#btn_limitlesscrowdfunding").click(function() {
			if ( $("#checkboxTypeOne").attr("checked")  != "checked") {
				$.kd.kdMsg("申请前请仔细阅读并勾选同意");
				return ;
		}
			self.transpriorityapply(21);
		});
	},
	identityapply:function(type){
		$("#btn_referee").attr("disabled", "disabled");
		$("#btn_investor").attr("disabled", "disabled");
		$("#btn_leader").attr("disabled", "disabled");
    	$.post(
    			"/mall/applyAttachmentUpload!identityapplyWorkFlow.do?ajax=yes"	,
    	{type:type},
    	function(data) {
				     
				var result = data.result;
				var message = data.message;
				if ( $.type(result) != "undefined")
				{
					data.flag =  parseInt(result);
				}
				if ( $.type(message) != "undefined")
				{
					data.msg =  message;
				}
    		if ( data.flag == 1 ) {
    			$.kd.kdAlert(data.msg, function() {
    				window.location.href = "/apply_usertype.html?type=friendlist&id=137&_="+new Date().getTime();
				},"身份资质申请");
				//alert("ok: "+data.message);
			}else{
				
				if (  data.msg == "err_goto_myproject_safe"  
					||  data.msg == "未获取到当前用户的customerno(请确保操作前已正确绑定银行卡)"
					) {
					$.kd.kdAlert("用户需先实名认证后才能进行相应操作,点击确定后将跳转至认证中心", function() {       				
    				window.location.href = "/account_certification_center.html?type=safe&_="+new Date().getTime();
					},"身份资质申请");
				}
				else if (  data.msg == "未查询到当前用户的风险评测结果"   ) {
					$.kd.kdAlert("用户需先风险评测后才能进行相应操作,点击确定后将跳转至认证中心", function() {       				
	    					window.location.href = "/account_certification_center.html?type=safe&_="+new Date().getTime();
						},"身份资质申请");
				}
				else {
					$.kd.kdAlert(data.msg, function() {
						window.location.href = "/apply_usertype.html?type=friendlist&id=137&_="+new Date().getTime();  
					},"身份资质申请");
				}
				
			}
    		//$("#btn_referee").attr("disabled", false);
        	//$("#btn_investor").attr("disabled", false);
        	//$("#btn_leader").attr("disabled", false);
    	},
    	"json"
    	);
    	

	} ,
	transpriorityapply:function(type){
			$("#btn_limitedcrowdfunding").attr("disabled", "disabled");
			$("#btn_limitlesscrowdfunding").attr("disabled", "disabled");
	    	$.post(
	    			"/mall/newpriorityapply!transpriorityapplyWorkFlow.do?ajax=yes"	,
	    	{type:type},
	    	function(data) {
				var result = data.result;
				var message = data.message;
				if ( $.type(result) != "undefined")
				{
					data.flag =  parseInt(result);
				}
				if ( $.type(message) != "undefined")
				{
					data.msg =  message;
				}
	    		if ( data.flag == 1 ) {
	    			$.kd.kdAlert(data.msg, function() {
	    				window.location.href = "apply_transaction_rights.html?type=friendapply&id=137&_="+new Date().getTime();
					},"交易权限申请");
					//alert("ok: "+data.message);
				}else{
					if (  data.msg == "err_goto_myproject_safe"  
						||  data.msg == "未获取到当前用户的customerno(请确保操作前已正确绑定银行卡)"
						) {
						$.kd.kdAlert("用户需先实名认证后才能进行相应操作,点击确定后将跳转至认证中心", function() {       				
	    				window.location.href = "/account_certification_center.html?type=safe&_="+new Date().getTime();
						},"交易权限申请");
					}
					else if (  data.msg == "未查询到当前用户的风险评测结果"   ) {
						$.kd.kdAlert(data.msg, function() {       				
		    					window.location.href = "/account_certification_center.html?type=safe&_="+new Date().getTime();
							},"交易权限申请");
					}
					else { 
						$.kd.kdAlert(data.msg, function() {
							window.location.href = "apply_transaction_rights.html?type=friendapply&id=137&_="+new Date().getTime();  
						},"交易权限申请");
					}
					
				}
	    		//$("#btn_limitedcrowdfunding").attr("disabled", false);
		    	//$("#btn_limitlesscrowdfunding").attr("disabled", false);
	    	},
	    	"json"
	    	);		
	    
		} 
};
	


$(function() {
	Kifpadmin.priorityapply.init();
});