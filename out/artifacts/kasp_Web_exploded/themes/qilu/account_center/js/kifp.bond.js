var Kifp = Kifp || {} ;

var $myAddBorrowForm;


Kifp.Bond = {	
		submiting: false,
		init : function() {
			$myAddBorrowForm = $("#addBorrowForm").kdValidform();
			//console.log("kifp.bond.init...");
			var self = this;	 	
			$("#addBorrowBtn").click(function() { 
				var id, msg ;
				id= "project_title";		msg = "项目名称不能为空!"         ; if(!self.notNullValidate(id,msg))return;
				id= "project_purpose"; 		msg = "项目类别不能为空!"         ; if(!self.notNullValidate(id,msg))return;
				id= "project_com_info"; 	msg = "项目公司基本情况不能为空!" ; if(!self.notNullValidate(id,msg))return;
				id= "project_info"	;		msg = "项目简介不能为空!"         ; if(!self.notNullValidate(id,msg))return;
				id= "withdrawAmount"; 		msg = "资金募集总额不能为空!"     ; if(!self.notNullValidate(id,msg))return;
				id= "annualrate"	;		msg = "预期年化收益率不能为空!"   ; if(!self.notNullValidate(id,msg))return;
				id= "deadline";				msg = "融资期限不能为空!"         ; if(!self.notNullValidate(id,msg))return;
				id= "paymentmode"; 			msg = "还款方式不能为空!"         ; if(!self.notNullValidate(id,msg))return;				
				id= "raiseterm"; 			msg = "募集周期不能为空!"           ; if(!self.notNullValidate(id,msg))return;
				id= "lest_amount"; 			msg = "资金认购起点不能为空!"     ; if(!self.notNullValidate(id,msg))return;
				id= "min_per_amount"; 		msg = "最小认购单位不能为空!"     ; if(!self.notNullValidate(id,msg))return; 
				id= "publisherinfo"; 		msg = "发行人基本情况不能为空!"     ; if(!self.notNullValidate(id,msg))return; 
				id= "assets"; 				msg = "发行人财务状况不能为空!"     ; if(!self.notNullValidate(id,msg))return; 
				id= "moneypurposes"; 		msg = "募集资金用途不能为空!"     ; if(!self.notNullValidate(id,msg))return; 
				id= "lvl"; 					msg = "偿债保证机制不能为空!"     ; if(!self.notNullValidate(id,msg))return;
                id= "project_org"; 			msg = "发行机构不能为空!"     ; if(!self.notNullValidate(id,msg))return;
                id= "project_cat"; 			msg = "项目类别不能为空!"     ; if(!self.notNullValidate(id,msg))return;				
				
				self.addBorrow();
				return false;
			});			 					
		},
		notNullValidate : function(id,msg){
			if($.kd.kdValidateNull(id, msg)){	$('#' + id).focus();	return false;	}
			else return true;
		},
		addBorrow : function() {
			
		var raiseterm = $("#raiseterm").val();
		if ( raiseterm > 90) {
			$.kd.kdMsg("募集周期不能大于90天");
			$("#raiseterm").focus();
			return false;
		}
		var withdrawAmount = $("#withdrawAmount").val();
		if ( withdrawAmount <= 0 ) {
			$.kd.kdMsg("资金募集总额不能为零");
			$("#withdrawAmount").focus();
			return false;
		}
		var lest_amount = $("#lest_amount").val();
		if ( lest_amount <= 0 ) {
			$.kd.kdMsg("资金认购起点不能为零");
			$("#lest_amount").focus();
			return false;
		}
		var min_per_amount = $("#min_per_amount").val();
		if ( min_per_amount <= 0 ) {
			$.kd.kdMsg("最小认购单位不能为零");
			$("#min_per_amount").focus();
			return false;
		}
		var invest_upper_limit = $("#invest_upper_limit").val();
		if ( invest_upper_limit <= 0 ) {
			$.kd.kdMsg("投资者认购上限不能为零");
			$("#invest_upper_limit").focus();
			return false;
		}
		
		$("#addBorrowBtn").val("提交中...");
		
		/*if (Kifp.Bond.submiting) {
			return;
		} else {
			Kifp.Bond.submiting = true;
			var s = setTimeout(function() { Kifp.Bond.submiting = false;},6000);
		}*/
		 
			var options = {
				url : "mall/bond!addBorrow.do?ajax=yes",
				dataType : "text",
				success : function(data) {
					$('#addBorrowBtn').attr("disabled", false);
					$("#addBorrowBtn").val("提交");
					var result = $.trim(data);
					
					if ( result.indexOf("1,") == 0 ) {
						$.kd.kdConfirm(result.substr(result.indexOf("1,") + 2), function() {
							window.location.reload(); 
						});
					} else {
						$.kd.kdAlert("保存成功！", function() {
							window.location.href = "/myproject.html?type=bondlist";
						});
					}
				},
				error : function() {
					$('#addBorrowBtn').attr("disabled", false);
					$("#addBorrowBtn").val("提交");
					Kifp.Bond.submiting = false;
					//clearTimeout(s);
					$.kd.kdAlert("addBorrow failed");
				}
			}; 
			if($myAddBorrowForm.check()){
				$('#addBorrowBtn').attr("disabled", true);
				$("#addBorrowForm").ajaxSubmit(options);
			}
		} 
		
	};


	$(function() {
		Kifp.Bond.init();
	});