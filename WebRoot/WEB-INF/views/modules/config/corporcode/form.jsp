<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>企业申请码</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function typeCheck(){
		var type = $("#type").val();
		if(type=="") {
			$.jBox.tip('【密钥宝服务平台】提示您：请选择类型!');
			return;
		}
		if(type == '0'){//新申请，选择企业
			var projectId = $("#projectId").val();
			if(projectId == "" || projectId == null){
				$.jBox.tip('【密钥宝服务平台】提示您：请选择项目!');
				return;
			}
			$.jBox.open("iframe:${ctx}/corporcode/corList?corporationId="+$("#corporUserRelaId").val()+"&projectId="+projectId, "企业选择", 900, 400, {           //如果是修改，传个ID就行了

                buttons: {"确定": "ok", "关闭": true},submit: function (v, h, f) {

                    if (v == "ok") {

                        var iframeName = h.children(0).attr("name");

                        var iframeHtml = window.frames[iframeName];               //获取子窗口的句柄

                        iframeHtml.saveCor();

                        return false;

                    }

                },

                loaded: function (h) {

                    $(".jbox-content", document).css("overflow-y", "hidden");

                }

            });
		}else if(type == '1'){
			var projectId = $("#projectId").val();
			$.jBox.open("iframe:${ctx}/corporcode/certList?corporationId="+$("#certSn").val()+"&projectId="+projectId, "证书选择", 900, 400,{           //如果是修改，传个ID就行了

                buttons: {"确定": "ok", "关闭": true},submit: function (v, h, f) {

                    if (v == "ok") {

                        var iframeName = h.children(0).attr("name");

                        var iframeHtml = window.frames[iframeName];               //获取子窗口的句柄

                        iframeHtml.saveCor();

                        return false;

                    }

                },

                loaded: function (h) {

                    $(".jbox-content", document).css("overflow-y", "hidden");

                }

            });		
		}
	}
	
	function reload(corId){
		$("#div2").hide();
		$("#div1").show();
		$("#corporUserRelaId").val(corId);
		$("#certSn").val("");
		$("#div1").load('${ctx}/corporcode/corporlist?corporationId='+corId);
		
	}
	
	function reload1(certNum){
		$("#div1").hide();
		$("#div2").show();
		$("#certSn").val(certNum);
		$("#corporUserRelaId").val("");
		$("#div2").load('${ctx}/corporcode/certSelectlist?certSn='+certNum);
	}
	function save(){
		var type = $("#type").val();
		if(type == null || type == ""){
			$.jBox.tip('【密钥宝服务平台】提示您：请选择业务类型！');
			return;
		}
		if(type == "1"){
			var certSn = $("#certSn").val();
			if(certSn == "" || certSn == null){
				$.jBox.tip('【密钥宝服务平台】提示您：请选择证书！');
				return;
			}
		}else if(type == "0"){
			var  corId = $("#corporUserRelaId").val();
			if(corId == "" || corId == null){
				$.jBox.tip('【密钥宝服务平台】提示您：请选择用户！');
				return;
			}
			var projectId = $("#projectId").val();
			if(projectId == "" || projectId == null){
				$.jBox.tip('【密钥宝服务平台】提示您：请选择项目！');
				return;
			}
		}
		$("#inputForm").submit();
	}
	function typeSelect(){
		$("#certSn").val("");
		$("#corporUserRelaId").val("");
		$("#div2").hide();
		$("#div1").show();
		var  corporUserRelaId = $("#corporUserRelaId").val();
		$("#div1").load('${ctx}/corporcode/corporlist?corporationId='+corporUserRelaId);
	}
	$(document).ready(function() {
		var type = $("#type").val();
		if(type == "1"){
			var certSn = $("#certSn").val();
			$("#div1").hide();
			
		}else if(type == "0"){
			var  corId = $("#corporUserRelaId").val();
			$("#div2").hide();
			$("#div1").load('${ctx}/corporcode/corporlist?corporationId='+corporUserRelaId);
			
		}else{
			$("#div1").load('${ctx}/corporcode/corporlist');
		}
		$("#inputForm")
		.validate(
		{
				rules: {
				},
				messages: {
				},
				submitHandler : function(form) {
					/* loading('正在提交，请稍等...'); */
					/* form.submit(); */
					var certSn = $("#certSn").val();
					var projectInfoId = $("#projectId").val();
					//获取表单提交按钮
					 var btnSubmit = document.getElementById("button2");
					 $.ajax({ 
							url : "${ctx}/corporcode/Judgement?userInfoId="+$("#corporUserRelaId").val()+"&projectInfoId="+projectInfoId+"&certSn="+certSn, 
							type : "post", 
							success : function(result) { 
								if(result=="OK") {
									form.submit();
									btnSubmit.disabled= "disabled";
								}else{
									form.submit();
									btnSubmit.disabled= "disabled";
									$.jBox.warning("存在变更【项目名称】的证书信息，请您确认是否要保存？", "【企业白名单提示】", submit, { buttons: { '是': true, '否': false} });
								}
							} 
					     });  
					
					
					
					
					
				},
				errorContainer : "#messageBox",
				errorPlacement : function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")
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
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/corporcode/list?clickType=2">企业白名单列表</a></li>
		<li class="active"><a href="${ctx}/corporcode/form">企业白名单添加</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="corporationRequestCode"
		action="${ctx}/corporcode/save" method="post" class="form-horizontal">
		<form:hidden path="id" id="id" />
		<form:hidden path="corporUserRelaId" id="corporUserRelaId"/>
		<form:hidden path="certSn" id="certSn" />
		<%-- <form:hidden path="corporationId" id="ids" /> --%>
		<sys:message content="${message}" />
				<table style="width: 100%;">
			<tr>
				<td style="width: 33%;margin-top:8px;padding-bottom:8px;border-bottom:1px dotted #dddddd;">
					<div>
						<label class="control-label">项目名称:</label>
						<div class="controls">
						<form:select id="projectId" path="projectId" cssStyle="width:200px"  onchange="typeSelect()">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getProjectInfoList()}" itemLabel="projectName" itemValue="id" />
						</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td style="width: 33%;margin-top:8px;padding-bottom:8px;border-bottom:1px dotted #dddddd;">
					<div>
						<label class="control-label">业务类型:</label>
						<div class="controls">
						<form:select id="type" path="type" cssStyle="width:200px" class="required" onchange="typeSelect()">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getDictList('opt_type') }" itemLabel="label" itemValue="value" class="required"/>
						</form:select>	
						<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td style="width: 33%;margin-top:8px;padding-bottom:8px;border-bottom:1px dotted #dddddd;">
					<div>
						<div class="controls" style="margin-left: 30px;" >
							<input id="button1" onclick="typeCheck()" class="btn btn-primary" type="button" value="选择" />
							<input id="button2" onclick="save()" class="btn btn-primary" type="button" value="保存" />
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form:form>
		<div id="div1">
		</div>
		<div id="div2">
		</div>
	</body>
</html>