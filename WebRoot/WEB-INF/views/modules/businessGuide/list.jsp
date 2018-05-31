<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务向导</title>
	<meta name="decorator" content="default" />
	<style type="text/css">
		.display{
			display: none;
		}
		.form-horizontal .control-label {
			float: left;
			width: 40%;
			padding-top: 5px;
			text-align: right;
		}
	</style>
	<script type="text/javascript">
		var step = 1;
		var usertype = "userinfo";
		var newOption = "";
		$(document).ready(function() {
			// 扩展属性校验
			jQuery.validator.addMethod("extraReq", function(value, element) {
				var type = element.name+"zz";
				var zz = $("#"+type).val();
				if(zz != null && zz != ""){
					var re = new RegExp(zz);
					return this.optional(element) || (re.test(value));
				}else{
					return true;
				}
			}, "格式错误,请重新输入");

			jQuery.validator.addMethod("isIdCardNo", function (value, element){
				return this.optional(element) || isIdCardNo(value);
			},"请正确输入您的身份证号码");

			$("#inputForm").validate({
				rules: {
					projectNum: {
						remote: "${ctx}/projectinfo/checkUniquePronum?id=" + $("#newProId").val()
					},
					certValidity: {
						digits: true,
						min: 0
					},
					allowUpdateDay: {
						digits: true,
						min: 0
					},
					makeCertRules: {
						remote: "${ctx}/projectinfo/checkMakecertRules?id=" + $("#newProId").val()
					},
					useridcardnum:{
						remote: "${ctx}/userinfo/checkUnique?id="+$("#userinfoid").val(),
						isIdCardNo : true
					},
					extra1 : {
						extraReq :true
					},
					extra2 : {
						extraReq :true
					},
					extra3 : {
						extraReq :true
					},
					extra4 : {
						extraReq :true
					},
					extra5 : {
						extraReq :true
					},
					extra6 : {
						extraReq :true
					},
					extra7 : {
						extraReq :true
					},
					extra8 : {
						extraReq :true
					},
					extra9 : {
						extraReq :true
					},
					extra10 : {
						extraReq :true
					}
				},
				messages: {
					projectNum: {
						remote: "项目编号已存在"
					},
					certValidity: "只能输入非负整数或整数",
					allowUpdateDay: "只能输入非负整数或整数",
					makeCertRules: "制证规则不能为空",
					useridcardnum:{
						remote: "证件号码已存在"
					},
				},
				submitHandler: function () {
					submitForm();
				},
				errorContainer: "#messageBox",
				errorPlacement: function (error, element) {
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
		$(document).ready(function() {
			//用户类型选择
			$("#usertypeSelect").click(function(){
				usertype = $('input[name=userTypeGuide]:checked').val();
			});
			//点击上一步
			$("#back").click(function(){
				back();
			});
			//点击下一步
			$("#next").click(function(){
				switch(step) {
					case 1:projectinfoSave();
						break;
					case 2:usertypeSave();
						break;
					case 3:userinfoSave();
						break;
					case 4:isSureCorpUser();
						break;
					default:
						$.jBox.tip('【密钥宝服务平台】提示：数据保存失败!');
				}
			});
			//点击完成
			$("#finish").click(function(){
				var submit = function (v, h, f) {
					if (v == true) {
						submitForm();
					}
					return true;
				};
				$.jBox.warning("是否确认提交【白名单配置】", "【白名单】", submit, { buttons: { '是': true, '否': false} });
			});
		});
		//提交表单 不同步骤 不同用户类型 提交路径不同
		function submitForm() {
			var btnNum = $("#stepBtn").children().length;
			var formv = $("#inputForm").serialize();
			var url;
			if (step == 1) {
				url = "${ctx}/businessGuide/projectinfoSave";
			} else if (step == 3) {
				if(usertype=='userinfo'){
					url = "${ctx}/businessGuide/userinfoSave";
				}else if(usertype=='corporation_info'){
					url = "${ctx}/businessGuide/corporationinfoSave";
				}
			} else if (step == btnNum) {
				if(usertype=='userinfo'){
					url = "${ctx}/businessGuide/whitelistSave";
				}else if(usertype=='corporation_info'){
					url = "${ctx}/businessGuide/corporcodeSave";
				}
			}
			$.ajax({
				type: 'POST',
				url: url,
				cache: false,
				data: formv,
				success: function (data) {
					var array = data.split(":");
					if(array[0]=='true'){
						if (step == 1) {
							$.jBox.tip('【密钥宝服务平台】提示：保存项目信息完成!');
							$("#projectInfoId").val(array[1]);
							$("#newProId").val(array[1]);
							$("#projectId").val(array[1]);
							$("#proName").val(array[2]);

						} else if (step == 3) {
							if(usertype=='userinfo'){
								$.jBox.tip('【密钥宝服务平台】提示：保存个人用户完成!');
								$("#userId").val(array[1]);
								reload(array[1]);//添加完用户 加载到白名单选择
							}else if(usertype=='corporation_info'){
								$.jBox.tip('【密钥宝服务平台】提示：保存企业用户完成!');
								$("#corporationId").val(array[1]);
							}
						}else if (step == btnNum) {
							$.jBox.tip('【密钥宝服务平台】提示：业务向导配置完成,可在白名单中查看!');
						}
						next();
					}else{
						$.jBox.tip('【密钥宝服务平台】提示：数据保存失败!');
					}
				}
			});
		}
		//检查项目信息
		function checkUnique(){
			var isDefaultProject = $("input[name='isDefaultProject']:checked").val();
			if(isDefaultProject == "0"){
				$.ajax({
					url : "${ctx}/projectinfo/checkUnique?isDefaultProject="+isDefaultProject+"&id="+$("#newProId").val(),
					type : "post",
					success : function(result) {
						if(result){
							$("#inputForm").submit();
						}else{
							$.jBox.tip('【密钥宝服务平台】提示：默认项目已存在!');
							$(":radio[name='isDefaultProject'][value='1']").prop("checked", "checked")
						}
					}
				});
			}else{
				$("#inputForm").submit();
			}
		}
		//第三步完成进入第四步
		function userinfoSave(){
			if(usertype=='userinfo'){
				var val = $('input[name=isHasUser11]:checked').val();
				var userId ="";
				if(val==0){ //已有个人
					userId = $('#userSelect option:selected').val();
					if(userId==null||userId==""){
						$.jBox.tip('【密钥宝服务平台】提示：请正确选择个人信息!');
						return;
					}else{
						next();
					}
				}else if(val==1){ //新增个人
					var idCard = $("#useridcardnum").val();
					var name = $("#username").val();
					var userinfoid = $("#userinfoid").val();
					if((idCard!=null&&idCard!='')&&(name!=null&&name!='')){
						$.ajax({
							url : "${ctx}/userinfo/updateCorpname?idCard="+idCard+"&name="+encodeURI(encodeURI(name))+"&id="+userinfoid,
							type : "post",
							success : function(result) {
								if(result){
									$("#inputForm").submit();
								}else{
									$.jBox.tip('【密钥宝服务平台】提示：请确保与企业用户中身份证号相同的用户姓名一致!');
								}
							}
						});
					}else{
						$("#inputForm").submit();
					}
				}
			}else if(usertype=='corporation_info'){
				var val = $('input[name=isHasCorp11]:checked').val();
				var corpId ="";
				if(val==0){ //已有企业
					corpId = $('#corpSelect option:selected').val();
					if(corpId==null||corpId==""){
						$.jBox.tip('【密钥宝服务平台】提示：请正确选择企业信息!');
						return;
					}else{
						next();
					}
				}else if(val==1){ //新增企业
					$("#inputForm").submit();
				}
			}
		}
		//确认企业是否选择企业使用人
		function isSureCorpUser() {
			var rows = $("#corpUserTable").find("tbody").find("tr").length;
			var text = $("#corpUserTable").find("tbody").find("tr").find("td").text();
			if(rows==1 && text=="无数据"){
				$.jBox.tip('【密钥宝服务平台】提示：未选择企业使用用户,请先完善此步!');
				return;
			}else{
				var checkVal = "";
				$("input[name=corpUserRelaID]").each(function() {
					if ($(this).attr("checked")) {
						checkVal += $(this).val() + ",";
					}
				});
				if(checkVal != ""){
					var submit = function (v, h, f) {
						if (v == true) {
							reload(checkVal);
							next();
						}
						return true;
					};
					$.jBox.warning("确认列表中是否已选中加入白名单的企业使用用户", "【企业使用用户】", submit, { buttons: { '是': true, '否': false} });
				}else{
					$.jBox.tip('【密钥宝服务平台】提示：未选中加入白名单的企业使用用户!');
					return;
				}

			}
		}
		//项目信息保存
		function projectinfoSave(){
			var val = $('input[name=isHasPro]:checked').val();
			if(val==0){ //已有项目
				var proId =$('#projectSelect option:selected').val();
				if(proId != "" && proId != undefined){
					next();
				}else{
					$.jBox.tip('【密钥宝服务平台】提示：请选择项目!');
					return;
				}
			}else if(val==1){ //新增项目
				checkUnique();
			}
		}
		function usertypeSave() {
			if(usertype != "" && usertype != undefined){
				if(usertype=='userinfo'){
					$("#stepBtn").append("<button id='step4'>第四步:白名单配置</button>");
				}else if(usertype=='corporation_info'){
					$("#stepBtn").append("<button id='step4'>第四步:企业用户添加</button><button id='step5'>第五步:白名单配置</button>");
				}
				next();
			}else{
				$.jBox.tip('【密钥宝服务平台】提示：请正确选择用户类型!');
			}
		}
		function back(){
			var btnNum = $("#stepBtn").children().length;
			$("#step" + step + "Form").addClass("display");
			step--;
			$("#step" + step + "Form").removeClass("display");
			if(step == 3){
				if(usertype=='userinfo'){
					$("#userinfo").removeClass("display");
					$("#corporation_info").addClass("display");
				}else if(usertype=='corporation_info'){
					$("#corporation_info").removeClass("display");
					$("#userinfo").addClass("display");
				}
			}
			if(step == btnNum-1){
				$('#finish').attr("disabled","");
			}
			if(step == 2){
				if(usertype=='userinfo'){
					$("#step4").remove();
				}else if(usertype=='corporation_info'){
					$("#step4").remove();
					$("#step5").remove();
				}
			}
			if (step < btnNum && step > 1) {
				$('#back').removeAttrs("disabled");
				$('#next').removeAttrs("disabled");
			}
			if(step==1){
				$('#back').attr("disabled","");
			}
		}
		function next(){
			var btnNum = $("#stepBtn").children().length;
			if(step < btnNum){
				$("#step" + step + "Form").addClass("display");
				step++;
				$("#step" + step + "Form").removeClass("display");
				if(step == 3){
					if(usertype=='userinfo'){
						$("#userinfo").removeClass("display");
						$("#corporation_info").addClass("display");
					}else if(usertype=='corporation_info'){
						$("#corporation_info").removeClass("display");
						$("#userinfo").addClass("display");
					}
				}
				if (step < btnNum && step > 1) {
					$('#back').removeAttrs("disabled","");
					$('#next').removeAttrs("disabled");
				}
				if (step == 4) {
					if(usertype=='userinfo'){
						$("#whitelist").removeClass("display");
						$("#corpUser").addClass("display");
					}else if(usertype=='corporation_info'){
						$("#corpUser").removeClass("display");
						$("#whitelist").addClass("display");
					}
				}
				if (step == btnNum) {
					$('#next').attr("disabled", "");
					$('#finish').removeAttrs("disabled");
				}
			}else{
				setTimeout("window.location.href='${ctx}/businessGuide/list'",1000);
			}
		}
		function isIdCardNo(num){
			var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
			var re = new RegExp(isIDCard1);
			if (re.test(num)) {
				return true;
			}else{
				return false;
			}
		}
		function reload(corId){
			if(usertype=='userinfo'){
				$("#ids").val(corId);
				$("#userList").load('${ctx}/white/listUser?userInfoId='+corId);
			}else if(usertype=='corporation_info'){
				$("#corporUserRelaId").val(corId);
				$("#corpList").empty();
				$("#corpList").load('${ctx}/corporcode/corporlist?corporationId='+corId);
			}
		}
		function deletePro() {
			var newProId = $("#newProId").val();
			if(newProId != "" && newProId != undefined){
				$.ajax({
					url: '${ctx}/businessGuide/projectinfoDelete?id='+newProId,
					type: 'POST',
					cache: false,
					success: function (data) {
						if(data=='true'){
							$("#newProId").val("");
							return;
						}
					}
				});
			}else{
				return;
			}
		}
		//判断当前项目 已存在的白名单
		function getWhitelistByProjectId(){
			var projectInfoId = $("#projectInfoId").val();
			if(projectInfoId != "" && projectInfoId != null){
				$.ajax({
					url :'${ctx}/businessGuide/getWhitelistByProjectId?projectId='+projectInfoId,
					type :'post',
					cache: false,
					success : function(result) {
						$("#userSelect").append(newOption);
						newOption = "";
						if(result != "null"){
							var userIdArray = result.split(",");
							for(var i=0;i<userIdArray.length;i++){
								$("#userSelect option").each(function() {
									var getVal = $(this).val();
									if(userIdArray[i]==getVal){
										$("#userSelect option[value="+getVal+"]").remove();
										var getText = $(this).text();
										newOption += "<option value="+getVal+">"+getText+"</option>";
										return true;
									}
								});
							}
						}else{
							return;
						}
					}
				});
			}
		}
	</script>
</head>
<body>
	<div id="stepBtn" style="height: 15%;text-align:center">
		<button id="step1">第一步:选择或新增项目</button>
		<button id="step2">第二步:选择用户类型</button>
		<button id="step3">第三步:新增用户</button>
	</div>
	<div style="height: 80%">
		<form id="inputForm" method="post" class="form-horizontal">
			<input id="projectInfoId" name="projectInfoId" type="hidden" value=""/>
			<input id="userId" type="hidden" name="userId" value=""/>
			<input id="corporationId" type="hidden" name="corporationId" value=""/>
			<input id="idOk" type="hidden" value="false"/>
			<sys:message content="${message}" />
			<div class="control-group">
			</div>
			<div id="step1Form">
				<jsp:include page="proForm.jsp"></jsp:include>
			</div>
			<div id="step2Form" class="display">
				<div class="control-group">
					<label class="control-label">用户类型:</label>
					<div class="controls" id="usertypeSelect">
						<c:forEach items="${fns:getDictList('user_info_type')}" var="var">
							<c:if test="${var.value=='userinfo'}">
								<input name="userTypeGuide" type="radio" value="${var.value}" class="required" checked/>${var.label}
							</c:if>
							<c:if test="${var.value!='userinfo'}">
								<input name="userTypeGuide" type="radio" value="${var.value}" class="required"/>${var.label}
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
			<div id="step3Form" class="display">
				<div id="userinfo" class="display">
					<jsp:include page="userinfoForm.jsp"></jsp:include>
				</div>
				<div id="corporation_info" class="display">
					<jsp:include page="corporationinfoForm.jsp"></jsp:include>
				</div>
			</div>
			<div id="step4Form" class="display">
				<div id="whitelist" class="display">
					<jsp:include page="whitelistForm.jsp"></jsp:include>
				</div>
				<div id="corpUser" class="display">
					<jsp:include page="corpUserList.jsp"></jsp:include>
				</div>
			</div>
			<div id="step5Form" class="display">
				<jsp:include page="corpcodeForm.jsp"></jsp:include>
			</div>
		</form>
	</div>
	<div style="height: 5%;text-align:center">
		<button id="back" disabled>上一步</button>&nbsp;&nbsp;
		<button id="next">下一步</button>&nbsp;&nbsp;
		<button id="finish" disabled>完成</button>
	</div>
</body>
</html>