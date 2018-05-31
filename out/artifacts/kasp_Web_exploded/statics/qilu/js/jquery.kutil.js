$.extend({


	
	kutil:{
		
	pageReload:function(uri,paramobj){
		var param = {};
		if( $.type(paramobj) == "undefined") {
			  param._ = new Date().getTime();				
		}
		else{				
			param=paramobj;
			param._ = new Date().getTime();	
		}

		 var gohtml ="<form id='form_go_html' name='form_go_html' action='"+uri+"' method='post'>";
		 for(key in param) {
		 gohtml += "<input type='hidden' name='"+key+"' value='"+param[key]+"' />";
		 }
		 //gohtml += "<input type='submit' name='submit_go_html' />"
		 gohtml += "</form>"
		  $("body").append(gohtml);
		 // document.getElementById("form_go_html").submit()
		 $("#form_go_html").submit();
	}
	
	,page_to:function(page_to_url,page_to_params_obj){
	  var page_from = window.location.href;
	  var page_to = page_to_url;
	  var indatas = JSON.stringify( page_to_params_obj) ;
	  
	  var uri ="ssologin.html";
	  var param = {};
	  param.page_from = page_from;
	  param.page_to = page_to;
	  param.indatas = indatas;
	  
	     var gohtml ="<form id='form_go_html' name='form_go_html' action='"+uri+"' method='post' >";
	     for(key in param) {
	     gohtml += "<input type='hidden' name='"+key+"' value='"+param[key]+"' />";
	     }
	     // gohtml += "<input type='submit' name='submit_go_html' />"
	     gohtml += "</form>"
	      $("body").append(gohtml);
	     // document.getElementById("form_go_html").submit()
	     $("#form_go_html").submit();
	
	}
	,newTabPage:function(uri,paramobj){
		var param = {};
		if( $.type(paramobj) == "undefined") {
			  param._ = new Date().getTime();				
		}
		else{				
			param=paramobj;
			param._ = new Date().getTime();	
		}

		 var gohtml ="<form id='form_go_html' name='form_go_html' action='"+uri+"' method='post'  target='_blank'>";
		 for(key in param) {
		 gohtml += "<input type='hidden' name='"+key+"' value='"+param[key]+"' />";
		 }
		 //gohtml += "<input type='submit' name='submit_go_html' />"
		 gohtml += "</form>"
		  $("body").append(gohtml);
		 // document.getElementById("form_go_html").submit()
		 $("#form_go_html").submit();
	}
	,formatMoney:function(s,n) {
		if ( !n) { n=2;} 
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + ""; 
		var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1]; 
		t = ""; 
		for (i = 0; i < l.length; i++) { 
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : ""); 
		} 
		return t.split("").reverse().join("") + "." + r; 
	
	}
	,moneyNumber:function(s) {
		return parseFloat(s.replace(/[^\d\.-]/g, "")); 
	}
	} 
});