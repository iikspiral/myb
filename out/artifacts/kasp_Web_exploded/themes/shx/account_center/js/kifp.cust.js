var Kifp = Kifp || {} ;
var $addCustForm ;

Kifp.Cust = {		
		init : function() {
			$addCustForm=$("#addCustForm").kdValidform();
			var self = this;	 	
			$("#addCustBtn").bind("click", function() { 
				self.addCust();
				return false;
			});			 					
		},
		 
		addCust : function() {
			var ids = [];
			$("#listcontainer span").each(function() {
				ids.push($(this).attr("rel"));
			});
			if(ids.length == 0) {
				$.kd.kdAlert("关注行业不能为空！");
			    return;
			}
			$("#attention_info").val(ids.join(","));		 
			var options = {
				url :"mall/newcust!addCust.do?ajax=yes",
				dataType : "text",
				success : function(data) {
					var result = $.parseJSON(data);
					
					if ( result.flag=="1" ) {
						$.kd.kdAlert(result.msg, function() {
							window.location.reload();
						});
					} else {
						$.kd.kdAlert(result.msg);
					}
				},
				error : function() {
					$.kd.kdAlert("addCust failed");
				}
			}; 
			if($addCustForm.check()){
				$("#addCustForm").ajaxSubmit(options);
			}
		} 
		
	};


	$(function() {
		Kifp.Cust.init();
	});