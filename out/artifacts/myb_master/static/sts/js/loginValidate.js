String.prototype.trim= function()  { return this.replace(/(^\s*)|(\s*$)/g, "");  };  
function font(s){	return "<font color='red'>"+s+"</font>"; }
function validateEmpty(){
	var username=$("#username");
	if($.trim(username.val())==""||$.trim(username.val())=="null"){
		msgshow();
		$("#msg1").html("用户名不能为空");
		username.focus();
		return false;
	}
	
	var password=$("#password");
	if($.trim(password.val())==""||$.trim(password.val())=="null"){
		msgshow();
		$("#msg1").html("密码不能为空");
		password.focus();
		return false;
	}
	      
}
function msgshow(){
	$("#msg1").removeClass("loginMessage loadingMassage_word");
	$("#msg1").removeClass("loginMessage loadingMassage_word_kong");
	$("#msg1").addClass("loginMessage loadingMassage_word");
}
function tabclick(){
    $(".loginMessage").html("");
	$("#msg1").removeClass("loginMessage loadingMassage_word");
	$("#msg1").removeClass("loginMessage loadingMassage_word_kong");
	$("#msg1").addClass("loginMessage loadingMassage_word_kong");
}