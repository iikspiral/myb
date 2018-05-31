<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript">

$(function() {
	var uploadUrl = '${ctx}/uploadAPK?folderName=apk;JSESSIONID=${sessionid}';
    $('#apkFileID').uploadify({
        //指定swf文件
        'swf': '${ctxStatic}/uploadify/uploadify.swf',
        //后台处理的页面
        'uploader': uploadUrl,
        //按钮显示的文字
        'buttonText': 'APK文件上传',
        //允许上传的文件后缀
        'fileTypeExts': '*.apk',
        //选择文件后自动上传
        'auto': true,
        //设置为true将允许多文件上传
        'multi': false,
        'fileSizeLimit':2*1024*1024,
        'onUploadSuccess': function (file, data,response) {
        	var par = data.split(";");
        	var url = par[0];
        	var id = par[1];
            $('#' + file.id).find('.data').html(' 上传完毕');
            $('#fileName').remove();
            $('#apkFileDiv').append('<span id="fileName">miyaobao.apk<a href="javascript:delAttachment();">删除</a><input type="hidden" name="fileName" value="miyaobao.apk"><input type="hidden" id="name" name="name" value="'+id+'"><input type="hidden" id="url" name="url" class="url" value="'+url+'"></span><br>');
        	var oldVersionNu = $('#version').val();
        	if(oldVersionNu=="") {
        		$('#version').val("1.0");
        	}
        }
    });
});

function delAttachment() {
	$('#fileName').remove();
}

$(document).ready(function() {
	$("#inputForm")
		.validate(
		{
				rules: {
				},
				messages: {
				},
				submitHandler : function(form) {
					var url = $("#url").val();
					if(url==undefined||url==""){
						$.jBox.tip('【密钥宝服务平台】提示:请您上传APK文件!');
						return;
					}
				 	var oldName = $('#oldName').val();
					var name = $('#name').val();
					if(oldName!=name&&oldName!="") {
						var oldVersionNu = $('#version').val();
						var newVersionNu = getNewVersionNu(oldVersionNu);
						$('#version').val(newVersionNu);
					}
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer : "#messageBox",
				errorPlacement : function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")
							|| element.is(":radio")
							|| element.parent().is(
									".input-append")) {
						error.appendTo(element.parent()
								.parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
});


function getNewVersionNu(oldVersionNu) {
	if(oldVersionNu=="") {
		return "1.0";
	}else {
		var number = oldVersionNu.substring(0,oldVersionNu.indexOf("."));
		var newNumber = parseInt(number) + 1;
		var newVersionNu = newNumber.toString()+".0";
		return newVersionNu;
	}
}

</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/apk/list">APK管理</a></li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="apkVersion" action="${ctx}/apk/save" method="post" class="form-horizontal">
		<form:hidden path="id" id="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">版本号:</label>
			<div class="controls">
				<form:input path="version" id="version" htmlEscape="false" rows="3"
					minlength="0" maxlength="600" class="input-xlarge" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			
		<div class="control-group">
			<label class="control-label">上传APK文件:</label>
			<div class="controls" id="apkFileDiv">
				<input type="file" name="apkFileID" id="apkFileID" />
				<input  id="oldName" type="hidden" value="${apkVersion.name }">
				<c:if test="${not empty apkVersion.url }">
					<span id="fileName">
						miyaobao.apk
						<a href="javascript:delAttachment();">删除</a>
						<input  type="hidden" name="fileName" value="miyaobao.apk">
						<input  id="name" type="hidden" name="name" value="${apkVersion.name }">
						<input  id="url" type="hidden" name="url" class="url" value="${apkVersion.url }">
					</span>
					<br>
				</c:if>
			</div>
		</div>
			
		<div class="form-actions">
			<input type="submit" value="保存" class="btn btn-primary">
		</div>
	</form:form>
</body>
</html>
