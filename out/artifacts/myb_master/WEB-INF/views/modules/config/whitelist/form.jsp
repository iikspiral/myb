<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
<meta name="decorator" content="default" />
<style type="text/css">
.form-horizontal {
    margin: -23px 10px;
}
</style>

<script type="text/javascript">
	 function changeShow() {
		var value = $('#optType option:selected') .val();
		if(value==0) {
			$('#userlisted1').show();
			$('#certlisted').hide();
			$('#certlisted1').hide();
			$("#certSn").val("");

		}else {
			$('#userlisted1').hide();
			$('#certlisted1').show();
			$('#userlisted').hide();
			$("#ids").val("");
		}
	} 
	

	$(document).ready(function() {
		$("#selected").click(function(){
			var type = $("#optType").val();
			var projectId = $("#projectInfoId").val();
			if(projectId == "" || projectId == null){
				$.jBox.tip('请先选择项目');
				return;
			}
			if(type == '0'){
				$.jBox.open("iframe:${ctx}/white/formUser?userInfoId="+$("#ids").val()+"&projectId="+projectId, "白名单用户添加", 900, 400, {  //如果是修改，传个ID就行了	
					buttons: {"确定": "ok", "关闭": true},
	                submit: function (v, h, f) {

	                    if (v == "ok") {

	                        var iframeName = h.children(0).attr("name");

	                        var iframeHtml = window.frames[iframeName];   //获取子窗口的句柄

	                        iframeHtml.saveCor();

	                        return false;

	                    }

	                },
	                loaded: function (h) {

	                    $(".jbox-content", document).css("overflow-y", "hidden");

	                }

	            });
			}else if(type == '1'){
				$.jBox.open("iframe:${ctx}/white/formCert?certSn="+$("#certSn").val(), "白名单证书选择", 900, 400, {  //如果是修改，传个ID就行了	
					buttons: {"确定": "ok", "关闭": true},
	                submit: function (v, h, f) {

	                    if (v == "ok") {

	                        var iframeName = h.children(0).attr("name");

	                        var iframeHtml = window.frames[iframeName];   //获取子窗口的句柄

	                        iframeHtml.saveCor();

	                        return false;

	                    }

	                },
	                loaded: function (h) {

	                    $(".jbox-content", document).css("overflow-y", "hidden");

	                }

				});
			}
		}),

		$("#inputForm").validate({
			rules: {
				optType:{
					required: true
				},
				userInfoId:{
					required: true
				}
			},
			messages: {
				optType: {
				},
				userInfoId:{
				}
				
			},
			submitHandler : function(form) {
			 	var ids = $("#ids").val();
				var certSn = $("#certSn").val();
				var optType = $("#optType").val();
				var projectInfoId = $("#projectInfoId").val();
				if(optType==0 &&projectInfoId=="" ||projectInfoId==null ||projectInfoId==undefined ){
					$.jBox.tip('【密钥宝服务平台】提示您：请按照操作顺序从选择项目开始！');
					return
				}else if(ids =="" && certSn ==""){
						 $.jBox.tip('【密钥宝服务平台】提示您：无效操作请重新选择证书或用户！');
						 return;
			 	}
				var projectInfoId = $("#projectInfoId").val();
				var certSn = $("#certSn").val();
			 	var btnSubmit = document.getElementById("save");
			    $.ajax({ 
					url : "${ctx}/white/Judgement?userInfoId="+$("#ids").val()+"&projectInfoId="+projectInfoId+"&certSn="+certSn, 
					type : "post", 
					success : function(result) { 
						if(result=="OK") {
							 form.submit();
							 btnSubmit.disabled= "disabled";
						}else{
							form.submit();
							btnSubmit.disabled= "disabled";
							$.jBox.warning("存在变更【项目】的证书信息，请您确认是否要保存？", "【个人白名单提示】", submit, { buttons: { '是': true, '否': false} });
						}
					} 
			    });  
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
	
	/*- --控制最下面的列表显示与隐藏在选择用户id后自动触发--  */
	function reload(corId){
		if(corId!=null) {
			$('#userlisted').show();
			$('#userlisted1').hide();
		}else {
			$('#userlisted1').hide();
			$('#userlisted').show();
		}
		$("#ids").val(corId);
		$("#userlisted").load('${ctx}/white/listUser?userInfoId='+corId);
	 
	}

//-------------------------------------------------------
function typeSelect(){
		$("#certSn").val("");
		$("#ids").val("");
	}
//-------------------------------------------------------

function reloadcert(corId){

	if(corId!=null) {
		$('#certlisted').show();
		$('#userlisted').hide();
		$('#userlisted1').hide();
		$('#certlisted1').hide();
	}else {
		$('#userlisted1').hide();
		$('#userlisted').hide();
		$('#certlisted').hide();
	}
	$("#certSn").val(corId);
	$("#certlisted").load('${ctx}/white/listCert?certSn='+corId);
	 
}
//-------------------------------------------------------
//-------------------------------------------------------
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/white/list?clickType=2">白名单列表</a></li>
		<li class="active"><a href="${ctx}/white/form?id=${WhiteList.id}">白名单${not empty WhiteList.id?'编辑':'添加'}</a></li>
	</ul>
	<br />
		<!-- ----------------------------- -->
		<form:form id="inputForm" modelAttribute="whiteList"
		action="${ctx}/white/save" method="post" class="form-horizontal">
			<form:hidden path="id" id="id" />
			<form:hidden path="userInfoId" id="ids" />
			<form:hidden path="certSn" id="certSn" />
			<sys:message content="${message}" />
		<!-- ----------------------------- -->
		<!-- <input type="hidden" id="ids"  path="userInfoId"> -->
		<!-- ----------------------------- -->
		<table style="width: 100%;">
			<tr>
				<td style="width: 30%;margin-top:8px;padding-bottom:8px;border-bottom:1px dotted #dddddd;">
					 <div id="projectName">
						<label class="control-label" >项目选择:</label>
						<div class="controls" >
							<form:select path="projectInfoId" id="projectInfoId" cssStyle="width:200px" onchange="typeSelect()">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getProjectInfoList()}" itemLabel="projectName" itemValue="id"/>
							</form:select>	
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td> 
				<td style="width: 30%;margin-top:2px;padding-bottom:8px; border-bottom:1px dotted #dddddd;">
					<div >
						<label class="control-label" >业务类型:</label>
						<div class="controls">
							<form:select id="optType" path="optType" cssStyle="width:200px"  value="${optType}" onchange="changeShow()" >
								<form:options items="${fns:getDictList('opt_type') }" itemLabel="label" itemValue="value"/>
							</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			 	
					<td  style="width: 27%; padding-top:8px;padding-bottom:11px; border-bottom:1px dotted #dddddd;" >
					<div>
						<input id="selected"  class="btn btn-primary" type="button" value="选择" />
						&nbsp;
						<input  id="save" type="submit" value="保存" class="btn btn-primary">
						&nbsp;
						<input id="btnCancel" class="btn btn-primary" type="button"	
						value="返回" onclick="javascript:window.location.href='${ctx}/white/list?clickType=2&id=${userInfo.id}'" /> 
					</div>
					</td>
			</tr>
			 <tr>
				<td style="width: 1%;padding-top:8px; padding-bottom:8px;border-bottom:1px dotted #dddddd;">
				<div  style="display: none">
				<label class="control-label">是否生效:</label>
						<div class="controls">
							<form:select id="isUsed" path="isUsed" cssStyle="width:200px"  value="${isUsed}" >
								<form:options items="${fns:getDictList('is_used') }" itemLabel="label" itemValue="value"/>
							</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
						</div>
				</div>
				</td>
				
				 <td style="width: 1%;padding-top:8px; padding-bottom:8px;border-bottom:1px dotted #dddddd;">
				<div  style="display: none">
				<label class="control-label">是否启用人脸:</label>
						<div class="controls">
							<form:select id="isUseface" path="isuseFace" cssStyle="width:200px"  value="${isuseFace}" >
								<form:options items="${fns:getDictList('is_usedface') }" itemLabel="label" itemValue="value"/>
							</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
						</div>
				</div>
				</td> 
				
			</tr> 
			
		</table>
		</form:form>
		<div id="userlisted">
		</div>
		
		<div id="certlisted">
		</div>
	<!-- -----下面这个就是个样子------- -->
	 <div id="userlisted1">
		<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">用户名</th>
				<th style="text-align: center;">电话号码</th>
				<th style="text-align: center;">联系地址</th>
				<th style="text-align: center;">证件号码</th>
				<th style="text-align: center;">单位名称</th>
			</tr>
		</thead>
		<tbody>
				<tr><td colspan="7" style="text-align: center;">无数据</td></tr>
		</tbody>
	</table>
	</div>
	
	 <div id="certlisted1" style="display: none">
		<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">项目</th>
				<th style="text-align: center;">证书主题</th>
				<th style="text-align: center;">证书序列号</th>
				<th style="text-align: center;">证书模板</th>
				<th style="text-align: center;">证书状态</th>
				<th style="text-align: center;">证书类型</th>
				<th style="text-align: center;">证书有效期</th>
				<th style="text-align: center;">生效时间</th>
				<th style="text-align: center;">失效时间</th>
			</tr>
		</thead>
		<tbody>
				<tr><td colspan="10" style="text-align: center;">无数据</td></tr>
		</tbody>
	</table>
	</div>
	 
</body>
</html>
