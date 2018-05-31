var saveAlterPasswd = saveAlterPasswd || {};

saveAlterPasswd.alterPasswd = {
		init : function(){
			//console.log("alter password init()...");
			var self = this;
			$("#pwdSubmit").click(function() { 
				//self.doAlterPasswd();
			});
		},
		doAlterPasswd : function(){
			var oldPassword = $('#alter_old_password').val();
			var newPassword = $('#pwdpas').val();
			var newPassword1 = $('#newpassword1').val();
			var usrid = $('#userNameSafe').text();
			//console.log(usrid+'     ----------------!');
			var submitBtn = $('#pwdSubmit');
			var _kjax1_ = $.des.getDes(oldPassword,'kingdom');
			var _kjax_ = $.des.getDes(newPassword,'kingdom');
			//console.log('-----enctrpted key is : '+_kjax_);
			var data={};
			data.oldPassword =_kjax1_; 
			data.newPassword =_kjax_; 
			data.usrId =usrid; 
			//---------------------------------------------------------------
			var options = {
					type:'post',//可选get
					url : "/mall/safeAlterPwd!alterPasswd.do?ajax=yes",
					data:data,//  
					dataType : "text",
					success : function(data) {
						console.log("Kifp.Login.login.success() data=" + data);
						var results = data.toString().split(",");
						//console.log("results.length="+results.length);
						
						if ( results.length > 0) {
							console.log("results[0]="+results[0]);
							if ( results[0] == 1 ) {
								//console.log("location to index.html...");
								alert('alter password success!'); 					
							} else {
								alert("info: "+results[1]);
							}
						}
						//window.href="/edit_project.html?id="+project_id;
					},
					error : function() {
						//console.log("safe_pwd alter pass .error() called... "); 
					}
				};
			//---------------------------------------------------------------
			$.ajax(options);
		}
};


$(function() {
	saveAlterPasswd.alterPasswd.init();
});

			