String.prototype.trim= function()  { return this.replace(/(^\s*)|(\s*$)/g, "");  };  
function font(s){	return "<font color='red'>"+s+"</font>"; }
function validateEmpty(){
	var username=$("#username");
	if($.trim(username.val())==""||$.trim(username.val())=="null"){
		$("#loginMessage").html(font("×用户名不能为空"));
		username.focus();
		return false;
	}
	
	var password=$("#password");
	if($.trim(password.val())==""||$.trim(password.val())=="null"){
		$("#loginMessage").html(font("×密码不能为空"));
		password.focus();
		return false;
	}
	      
}