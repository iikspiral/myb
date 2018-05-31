<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>短信发送测试</title>
		<meta name="decorator" content="default"/>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#inputForm").validate({
					submitHandler: function(form){
						loading('正在提交，请稍等...');
						form.submit();
					},
					errorContainer: "#messageBox",
					errorPlacement: function(error, element) {
						$("#messageBox").text("输入有误，请先更正。");
						if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
							error.appendTo(element.parent().parent());
						} else {
							error.insertAfter(element);
						}
					}
				});
			});
		</script>
	</head>
	<body>
	<form method="POST" class="form-horizontal" enctype="multipart/form-data" action="${ctx}/smsPlatform/importInfo" >
		<div class="control-group">
			<label class="control-label">批量发送:</label>
			<div class="controls">
				<div class="msg">
					注意:请下载模板并保持模版原样填写有效数据后导入
				</div>
				<div>
					导入:&nbsp;
					<input type="file" name="file" />
					&emsp;
					<input type="submit" id="impBtn" class="importBtn" value="导&nbsp;入" />
					&emsp;
					<input type="button" value="模板下载" onClick="window.location='${ctx}/smsPlatform/downFile'; "/>
				</div>
			</div>
		</div>
	</form>
	<form id="inputForm" method="post" class="form-horizontal" action="${ctx}/smsPlatform/sendAll">
		<sys:message content="${message}"/>
		<input type="hidden" name="code" id="code" value=""/>
		<div class="control-group">
			<label class="control-label">短信平台:</label>
			<div class="controls">
				<select id="smsPlatform" name="smsCode" style="width:200px" class="required">
					<option value="">请选择</option>
					<c:forEach items="${listSMS}" var="sms">
						<option value="${sms.smsCode}">${sms.smsName}</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短信模板:</label>
			<div class="controls">
				<select id="templateName" name="templateName" style="width:200px" class="required">
					<option value="">请选择</option>
					<c:forEach var="tem" items="${temList}">
						<option value="${tem.templateId}">${tem.templateName}</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">待发号码:</label>
			<div class="controls">
				<textarea style="height: 130px;width:500px;" class="required input-medium" name="phoneNum" id="phoneNum">${mobile}</textarea>
				<span class="help-inline"><font color="red">*</font> </span><br>
				<span class="help-inline"><font color="red"> 输入多个手机号时，手机号间用英文逗号分割</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短信内容:</label>
			<div class="controls">
				<textarea name="content" style="width:500px;" class="required input-medium"></textarea>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
			<div id="msg" class="controls" style="color:red"></div>
		</div>
		<div class="form-actions">
			<input id="subBtn" class="btn btn-primary" type="submit"  value="提 交"/>&nbsp;
		</div>
	</form>
	</body>
</html>
