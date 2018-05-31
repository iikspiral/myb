<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查看详情</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		body {
			font-family: "Helvetica Neue",Helvetica,Microsoft Yahei,Hiragino Sans GB,WenQuanYi Micro Hei,sans-serif;
		}
		.prompt_box {
			border: 1px solid #faebcc;
			background: #fcf8e3;
			padding: 10px 20px 10px 20px;
		}
		.prompt_tit {
			border-bottom: 2px solid #ccc;
			height: 20px;
			margin-top: 20px;
		}
		.prompt_tit span {
			color: #2fa4e7;
			border-bottom: 2px solid #2fa4e7;
			line-height: 20px;
			padding: 30px 10px 2px 10px;
		}
		.magin_bottom {
			margin-bottom: 8px;
		}
		.form-horizontal .control-label {
   			 padding-top: 0px;
		}
	</style>
	<script type="text/javascript">
		//注销
		$(document).ready(function() {
			$("#btnRevoke").click(function(){
				var certSn = $("#certSn").val();
				window.location.href="${ctx}/certApplyInfo/revokeHoldApply?optType=2&certSn="+certSn;
			});
		});
		//冻结
		$(document).ready(function() {
			$("#btnHold").click(function(){
				var certSn = $("#certSn").val();
				window.location.href="${ctx}/certApplyInfo/revokeHoldApply?optType=3&certSn="+certSn;
			});
		});
		//解冻
		$(document).ready(function() {
			$("#btnUnhold").click(function(){
				var certSn = $("#certSn").val();
				window.location.href="${ctx}/certApplyInfo/revokeHoldApply?optType=4&certSn="+certSn;
			});
		});
		//同步
		$(document).ready(function() {
			$("#btnSync").click(function(){
				$.ajax({
					url:'${ctx}/certApplyInfo/syncCertInfo',
					cache: false,
					data: {certSn : $("#certSn").val(),isRecovery : $("#isRecovery").val()},
					success:function(data){
						if(data){
							alert("状态同步成功！");
							window.location.href="${ctx}/certInfo/form?id=${certInfo.id}";
						}else{
							alert("状态同步失败!");
						}
					}
				});
			});
		});
	</script>
</head>
<body>
	 <ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/certInfo/list">证书查询列表</a></li> --%>
		<li class="active"><a href="${ctx}/certInfo/form?id=${certInfo.id}">查询详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="certInfo" method="post" class="form-horizontal">
	 	<form:hidden path="id"/>
	 	<form:hidden path="certSn"/>
	 	<form:hidden path="isRecovery"/>
	 	<sys:message content="${message}"/>
		<div class="prompt_tit magin_bottom">
			<span>证书信息</span>
		</div>
		<div class="control-group">
			<label class="control-label">证书序列号:</label>
			<div class="controls">
			${certInfo.certSn}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书主题:</label>
			<div class="controls">
			${certInfo.certSubject}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书主题大写:</label>
			<div class="controls">
			${certInfo.subjectUppercase}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">CN:</label>
			<div class="controls">
			${certInfo.commonName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书模板:</label>
			<div class="controls">
			${certInfo.ctmlName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">授权码:</label>
			<div class="controls">
			${certInfo.authCode}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书状态:</label>
			<div class="controls">
					${fns:getDictLabel(certInfo.certStatus, 'cert_status', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书类型:</label>
			<div class="controls">
			${fns:getDictLabel(certInfo.certType, 'cert_type', '')}
			</div>
		</div>
		<!-- 	------------------------------	---- -->
		 <div class="prompt_tit magin_bottom">
			<span>项目信息</span>
		</div> 
		<div class="control-group">
			<label class="control-label">项目编号:</label>
			<div class="controls">
			 ${project.projectNum} 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称:</label>
			<div class="controls">
			${project.projectName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">制证规则:</label>
			<div class="controls">
			${project.makeCertRules}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">自定义扩展域规则:</label>
			<div class="controls">
			${project.selfExtRules}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准扩展域规则:</label>
			<div class="controls">
			${project.standardExtRules}
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">是否启用人脸识别:</label>
			<div class="controls">
			${fns:getDictLabel(project.isuseFace, 'is_usedface', '')}
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">是否默认项目:</label>
			<div class="controls">
			${project.isDefaultProject}
			 ${fns:getDictLabel(project.isDefaultProject, 'isuse_DefaultProject', '')}
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">是否保持密钥:</label>
			<div class="controls">
			${fns:getDictLabel(project.isHoldKey, 'is_holdkey', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">允许更新条件(天):</label>
			<div class="controls">
			${project.allowUpdateDay}
			</div>
		</div>

	<!-- ------------------------- -->
	
	<!-- --------------------	------ -->
		<%-- <c:if test="${fn:length(userList) <= 0}">
			<div class="control-group">
				<div class="controls">
					无个人用户信息
				</div>
			</div>
		</c:if> --%>
		<c:if test="${fn:length(userList) > 0}">
		<!--  <div id=user> -->
	  		<div class="prompt_tit magin_bottom">
			<span>个人用户信息</span>
		</div> 
		
			<table id="contentTable"
				   class="table table-bordered" style="width: 100%">
				<thead>
				<tr>
					<th style="text-align: center;">用户姓名</th>
					<th style="text-align: center;">电话号码</th>
					<th style="text-align: center;">联系地址</th>
					<th style="text-align: center;">证件类型</th>
					<th style="text-align: center;">证件号码</th>
					<th style="text-align: center;">单位名称</th>
					<!-- <th style="text-align: center;">用户状态</th> -->
					<th style="text-align: center;">用户类型</th>
					<!-- <th style="text-align: center;">是否人脸认证：</th> -->
				</tr>
				</thead>
				<tbody>
					<tr>
						<td style="text-align: center;">${user.username}</td>
						<td style="text-align: center;">${user.phonenum}</td>
						<td style="text-align: center;">${user.usercontactaddr}</td>
						<td style="text-align: center;">${fns:getDictLabel(user.idtype,'certifiy_type','') }</td>
						<td style="text-align: center;">${user.useridcardnum}</td>
						<td style="text-align: center;">${user.org}</td>
						<%-- <td style="text-align: center;">${user.userState}</td> --%>
						<td style="text-align: center;">${fns:getDictLabel(user.userType, 'user_type', '')}</td>
						<%-- <td style="text-align: center;">${fns:getDictLabel(user.isFace, 'is_usedface', '')}</td> --%>
					</tr>
				</tbody>
			</table>
		</c:if>
		<!-- ---------------		--------------------- -->
		 
		<%-- <c:if test="${fn:length(corpoList) <= 0}">
			<div class="control-group">
				<div class="controls">
					无企业信息
				</div>
			</div>
		</c:if> --%>
		<c:if test="${fn:length(corpoList) > 0}">
		<div id=user>
	  		<div class="prompt_tit magin_bottom">
				<span>企业信息</span>
			</div> 
			<table id="contentTable"
				   class="table table-bordered" style="width: 100%">
				<thead>
				<tr>
					<th style="width:160px; text-align:center;">企业名称</th>
					<th style="text-align: center;">法人代表</th>
					<th style="text-align: center;">企业有效证件类型</th>
					<th style="text-align: center;">企业有效证件号码</th>
					<th style="text-align: center;">经办人姓名</th>
					<th style="text-align: center;">经办人联系电话</th>
					<th style="text-align: center;">经办人联系地址</th>
					<th style="text-align: center;">经办人有效证件类型</th>
					<th style="text-align: center;">经办人有效证件号码</th>
					<!-- <th style="text-align: center;">用户状态</th> -->
				</tr>
				</thead>
				<tbody>
					<tr>
						<td style="text-align: center;">${corpo.corpname}</td>
						<td style="text-align: center;">${corpo.legalpersonname}</td>
						<td style="text-align: center;">${fns:getDictLabel(corpo.corpidtype, 'corpid_type', '无')}</td>
						<td style="text-align: center;">${corpo.corpidcardnum}</td>
						<td style="text-align: center;">${corpo.agentname}</td>
						<td style="text-align: center;">${corpo.agentphone}</td>
						<td style="text-align: center;">${corpo.agentcontactaddr}</td>
						<%-- <td style="text-align: center;">${corpo.agentidtype}</td> --%>
						<td style="text-align: center;">${fns:getDictLabel(corpo.agentidtype,'certifiy_type','') }</td>
						<td style="text-align: center;">${corpo.agentidcardnum}</td>
						<%-- <td style="text-align: center;">${corpo.userState}</td> --%>
					</tr>
				</tbody>
			</table>
		</c:if>
		<!-------------------------------------- -->
		
		<%-- <c:if test="${fn:length(corpoUserList) <= 0}">
			<div class="control-group">
				<div class="controls">
					无企业用户信息
				</div>
			</div>
		</c:if --%>
		<c:if test="${fn:length(corpoUserList) > 0}">
		<div class="prompt_tit magin_bottom">
			<span>企业用户信息</span>
		</div> 
			<table id="contentTable"
				   class="table table-bordered" style="width: 50%">
				<thead>
				<tr>
					<th style="text-align: center;">企业用户姓名</th>
					<th style="text-align: center;">企业用户身份证号</th>
					<th style="text-align: center;">企业用户手机号</th>
				</tr>
				</thead>
				<tbody>
					<tr>
						<td style="text-align: center;">${corUser.userName}</td>
						<td style="text-align: center;">${corUser.idCard}</td>
						<td style="text-align: center;">${corUser.phoneNum}</td>
					</tr>
				</tbody>
			</table>
		</c:if>

		<div id="opt" style="text-align: center; width: 90%;margin-top: 30px;">
			 <c:if test="${certInfo.certStatus == 'Use' || certInfo.certStatus == 'Undown' }">
				 <input id="btnRevoke" class="btn btn-inverse" type="button" value="注 销">&nbsp;&nbsp;
			 </c:if>
			<c:if test="${certInfo.certStatus == 'Use'}">
				<input id="btnHold" class="btn btn-warning" type="button" value="冻 结"/>&nbsp;&nbsp;
			</c:if>
			 <c:if test="${certInfo.certStatus == 'Hold'}">
				 <input id="btnUnhold" class="btn btn-danger" type="button" value="解 冻"/>&nbsp;&nbsp;
			 </c:if>
			 <c:if test="${certInfo.certStatus != 'Revoke' or certInfo.certStatus != 'UndownRevoke'}">
				 <input id="btnSync" class="btn btn-info" type="button" value="状态同步"/>&nbsp;&nbsp;
			 </c:if>
			 <input type="button" class="btn btn-primary" value="返&nbsp;&nbsp;回" onclick="history.go(-1)"/>&nbsp;&nbsp;
	 	</div>
	</form:form>
</body>
</html>