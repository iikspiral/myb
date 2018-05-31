<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>短信发送测试</title>
		<meta name="decorator" content="default"/>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#smsPlatform").change(function(){
					var type=$('#smsPlatform option:selected').val();
					$('#code').val(type);
				});
			});
			$(document).ready(function() {
				$("#regitBtn").click(function(){
					var formv=$("#inputForm").serialize();
					$.ajax({
						type:'POST',
						url:'${ctx}/smsPlatform/regedit',
						cache:false,
						data:formv,
						success:function(data){
							if (data=='true'){
								$("#msg").html('验证码正确');
								setTimeout("window.location.href = '${ctx}/smsPlatform/sendTest'","2000")
							}else{
								$("#msg").html(data);
							}
						}
					});
				})
			});
			$(document).ready(function() {
				$("#yzmBtn").click(function(){
					$.ajax({
						type:'POST',
						url:'${ctx}/smsPlatform/getYzm',
						data: {"phone":$("#phone").val(),"code":$("#code").val()},
						success:function(data){
							var time=60;
							if (data=="true"){
								$("#yzmBtn").hide();
								var times= setInterval(function() {
									$("#msg").html(time+'秒后可重新获取验证码');
									time--;
									if (time==0){
										window.clearInterval(times);
										$("#yzmBtn").show();
										$("#msg").html('');
									}
								}, 1000);
							}else{
								$("#msg").html('验证码获取失败，请重试！');
							}
						}
					});
				});
			});
			$(document).ready(function() {
				$("#yzm").focus(function(){
					$("#msg").html('');
				});
			});
		</script>
	</head>
	<body>
	<form id="inputForm" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<input type="hidden" name="code" id="code" value=""/>
		<div class="control-group">
			<label class="control-label">短信平台:</label>
			<div class="controls">
				<select id="smsPlatform" style="width:200px" class="required">
					<option value="">请选择</option>
					<c:forEach items="${listSMS}" var="sms">
						<option value="${sms.smsCode}">${sms.smsName}</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号:</label>
			<div class="controls">
				<input id="phone" class="required input-medium"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">验证码:</label>
			<div class="controls">
				<input id="yzm" name="mobileYz" class="required input-medium"/>
				<input id="yzmBtn" class="btn btn-primary" type="button"  value="获 取" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
			<div id="msg" class="controls" style="color:red"></div>
		</div>
		<div class="form-actions">
			<input id="regitBtn" class="btn btn-primary" type="button"  value="提 交"/>&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	</body>
</html>
