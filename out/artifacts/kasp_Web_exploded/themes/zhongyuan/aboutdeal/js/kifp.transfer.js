var Kifp = Kifp || {} ;

var $myaddTransferForm;
Kifp.Transfer = {	
		submiting: false,
		init : function() {
			$myaddTransferForm = $("#addTransferForm").kdValidform();
			//console.log("kifp.bond.init...");
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
		/* var reprice = $("#reprice").val();
		if ( reprice < 0) {
			$.kd.kdMsg("转让价格不能为空！");
			$("#reprice").focus();
			return false;
		} */
			var reprice = $("#reprice").val();
			var positive = $("#limitpricepo").val();
			var minus = $("#limitpricemin").val();
			if ( reprice - positive >0) {
				$.kd.kdMsg("转让价格不能大于"+ Number( positive).toFixed(2));
				$("#reprice").focus();
				return false;
			}
			if ( reprice - minus <0 ) {
				$.kd.kdMsg("转让价格不能小于" + Number( minus).toFixed(2));
				$("#reprice").focus();
				return false;
			}
		
			var options = {
				url : "mall/newtransfer!addTransfer.do?ajax=yes",
				dataType : "text",
				success : function(data) {
					$('#addTransferBtn').attr("disabled", false);
					var result = $.trim(data);
					
					if ( result.indexOf("1,") == 0 ) {
						$.kd.kdAlert(result.substr(result.indexOf("1,") + 2), function() {
							//window.location.reload();// 
							window.location.href = "/transfer.html";
						});
					} else {
						$.kd.kdMsg(result.substr(result.indexOf("0,") + 2));
					}
				},
				error : function() {
					//$.kd.kdAlert("addTransfer failed");					
					$('#addTransferBtn').attr("disabled", false);
					$("#addTransferBtn").val("提交");
					Kifp.Transfer.submiting = false;
					//clearTimeout(s);
					$.kd.kdAlert("addTransfer failed");
				}
			}; 
			//$("#addTransferForm").ajaxSubmit(options);
			//console.log("ajaxSubmit...");
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