var Kifp = Kifp || {};

Kifp.Head = {
		
	init : function() {
		//console.log("exec kifp.head.init()");
		var self = this;
		
		$("#custlogout").click(function() { 
			self.logout();
		});
		
	},
    logout:function() {
	zhejiang_confrim("提示","确定要退出吗？", function() {
		$.post("mall/newlogin!newlogout.do",
    			{},
    			function(data) {
    				var result = $.parseJSON(data); 
    				
    				if ( result.result == 1) {
    					window.location.href = "/index.html?_="+new Date().getTime();
    				} else {
    					rdmAlert(result.message);
    				}   					
    			}
    		);
	});    	
    } 
 	
};


$(function() {
	Kifp.Head.init();
});

