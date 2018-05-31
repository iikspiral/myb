var Kifp = Kifp || {} ;
var $myaddTransferForm;
Kifp.Transfer = {	
		submiting: false,
		init : function() {
			$myaddTransferForm = $("#addTransferForm").kdValidform();
			var self = this;	 	
			$("#addTransferBtn").click(function() { 
				var id, msg ;
				//id= "reprice";		msg = "转让价格不能为空!"         ; if(!self.notNullValidate(id,msg))return;
				self.addTransfer();
				return false;
			});			 					
		},
		notNullValidate : function(id,msg){
			if($.kd.kdValidateNull(id, msg)){	$('#' + id).focus();	return false;	}
			else return true;
		},	
		addTransfer : function() {				    
			var options = {
				url : "mall/v2transfer!addhbBondTransfer.do?ajax=yes",
				dataType : "text",
				success : function(data) {
					$('#addTransferBtn').attr("disabled", false);
					var result = $.trim(data);
					//var json = eval('('+data+')');
					//var response = data.responseText;
					var obj = eval(result);					
					if (obj[0].success) {
						$.kd.kdAlert(obj[0].msg  , function(){
							window.location.href = "/product_transfer.html";
						});
					} else {
						$.kd.kdMsg(obj[0].msg);
					}
				},
				error : function() {
					$('#addTransferBtn').attr("disabled", false);
					$("#addTransferBtn").val("提交");
					Kifp.Transfer.submiting = false;

					$.kd.kdAlert("addTransfer failed");
				}
			}; 
			if($myaddTransferForm.check()){
				var rawpas = $("#tradepass").val();
				var _tradepwd_ = $.des.getDes(rawpas,'kingdom');
				$("#tradepass").val(_tradepwd_);
				$('#addTransferBtn').attr("disabled", true);
				
				 
				$("#addTransferForm").ajaxSubmit(options);
			}
		} 
		
	};

	$(function() {
		Kifp.Transfer.init();
	});