$.extend({
 
	

	bex: {
		 kifpaction:"/mall",
		 
		  doBex : function(bexid,bexParamsObject,callback) {
	             $.post(
	             $.bex.kifpaction+"/bex!testBex.do?ajax=yes",
	            { 
		            bexid: $.trim(bexid),
		            bexparams: JSON.stringify(   bexParamsObject  )  
	            },
	            function(data) {
	            			if ( typeof callback == "function")	  {
	            			   callback(data);
	            			}
				},
	            "json"
	            );  
		  },
		
		doKifpBex: function(kifpbexid, kifpbexparamsobject,callback) {
			$.post(
					$.bex.kifpaction+"/bex!kifpBex.do?ajax=yes",
		            { 
			            bexid: $.trim(kifpbexid),
			            bexparams: JSON.stringify(   kifpbexparamsobject  )  
		            },
		            function(data) {
		            			if ( typeof callback == "function")	  {
		            			   callback(data);
		            			}
					},
		            "json"
		            ); 
		} 
		  
		  
	}
});