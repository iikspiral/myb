var Kifpadmin = Kifpadmin || {};

Kifpadmin.projectflow = {
		
	init : function() {
		var self = this;
		$(".btn_beginwarmup").click(function() {
			//$("#project_id").value();
			var project_id = $(this).attr("data-value");
			var title = $(this).attr("data-value-title");
			var status = $(this).attr("data-value-status");
			
			if ( $(this).attr("disabled") == "disabled" ) {
				return ;
			}
		   $(this).attr("disabled", "disabled");
			self.stockrightflow('00',project_id,title,status);
		});
		$(".btn_beginfinancing").click(function() {
			//$("#project_id").value();
			var project_id = $(this).attr("data-value");
			var title = $(this).attr("data-value-title");
			var status = $(this).attr("data-value-status");
			
			if ( $(this).attr("disabled") == "disabled" ) {
				return ;
			}
			
			$(this).attr("disabled", "disabled");
			self.stockrightflow('00',project_id,title,status);
		});
		$(".btn_endfinancing").click(function() {
			//$("#project_id").value();
			var project_id = $(this).attr("data-value");
			var title = $(this).attr("data-value-title");
			var status = $(this).attr("data-value-status");
			
			if ( $(this).attr("disabled") == "disabled" ) {
				return ;
			}
			
			$(this).attr("disabled", "disabled");
			self.stockrightflow('00',project_id,title,status);
		});
		
		$(".btn_modifyinfo").click(function() {
			//$("#project_id").value();
			var project_id = $(this).attr("data-value");
			
			if ( $(this).attr("disabled") == "disabled" ) {
				return ;
			}
			
			$(this).attr("disabled", "disabled");
			window.location.href = "/edit_project.html?_="+new Date().getTime()+"&id="+project_id;
		});
		
		
		$(".btn_beginflow").click(function() {
			//$("#project_id").value();
			var project_id = $(this).attr("data-value");
			var title = $(this).attr("data-value-title");
			var status = $(this).attr("data-value-status");
			
			if ( $(this).attr("disabled") == "disabled" ) {
				return ;
			}
			
			$(this).attr("disabled", "disabled");
			self.bondrightflow('01',project_id,title,status);
		});
		
	},
	stockrightflow:function(type,project_id,title,status){
    	$.post(
    			"/mall/newmyprojectflow!stockrightWorkFlow.do?ajax=yes"	,
    	{type:type,
    	 project_id:project_id,
    	 title:title,
    	 status:status
    	 },
    	function(data) {
    		if ( data.result == 1 ) {   			 
    				$.kd.kdAlert(data.msg, function() {        				
        				window.location.href = "/product_equity.html?type=equitylist&_="+new Date().getTime();
    				},"股权项目");      			
			}else{
				if (  data.msg == "err_goto_myproject_safe" ) {
					$.kd.kdAlert("用户需先实名认证后才能进行相应操作,点击确定后将跳转至认证中心", function() {       				
    				window.location.href = "/account_certification_center?type=safe&_="+new Date().getTime();
					},"股权项目");
				} 
				else if (  data.msg == "err_goto_project_basic" ) {
					$.kd.kdAlert("请先设置预热周期和募集周期", function() {       				
    				window.location.href = "/edit_project.html?_="+new Date().getTime()+"&id="+project_id;
					},"股权项目");
				} 
				
				else {
				$.kd.kdAlert(data.msg, function() {
    				window.location.href = "/product_equity.html?type=equitylist&_="+new Date().getTime();
					},"股权项目");
			}
	    		
			}
    	},
    	"json"
    	);		

	} ,
	bondrightflow:function(type,project_id,title,status){
    	$.post(
			"/mall/hbmyprojectflow!productrightWorkFlow.do?ajax=yes"	,
			{type:type, project_id:project_id,title:title, status:status},
    	function(data) {
    		if ( data.result == 1 ) {
        			$.kd.kdAlert(data.message, function() {
        				window.location.href = "/product_bonds.html?type=bondlist&_="+new Date().getTime();
    				},"项目");
			}else{
				if (  data.message == "err_goto_myproject_safe" ) {
					$.kd.kdAlert("用户需先实名认证后才能进行相应操作,点击确定后将跳转至认证中心", function() {       				
    				window.location.href = "/account_certification_center.html?type=safe&_="+new Date().getTime();
					},"项目");
				} else {
					$.kd.kdAlert(data.message, function() {
	    				window.location.href = "/product_bonds.html?type=bondlist&_="+new Date().getTime();
					},"项目");	
				}
	    			
			}
    	},
    	"json"
    	);		

	} 
	
};
	


$(function() {
	Kifpadmin.projectflow.init();
});