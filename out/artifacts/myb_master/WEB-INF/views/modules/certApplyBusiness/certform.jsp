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
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="certInfo" method="post" class="form-horizontal">
	 	<form:hidden path="id"/>
	 	<form:hidden path="certSn"/>
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
		<c:if test="${fn:length(userList) > 0}">
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
					<th style="text-align: center;">用户类型</th>
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
						<td style="text-align: center;">${fns:getDictLabel(user.userType, 'user_type', '')}</td>
					</tr>
				</tbody>
			</table>
		</c:if>
		<c:if test="${fn:length(corpoList) > 0}">
		<div id=user>
	  		<div class="prompt_tit magin_bottom">
				<span>企业信息</span>
			</div> 
			<table id="contentTable"
				   class="table table-bordered" style="width: 100%">
				<thead>
				<tr>
					<th style="text-align: center;">企业名称</th>
					<th style="text-align: center;">法人代表</th>
					<th style="text-align: center;">企业有效证件类型</th>
					<th style="text-align: center;">企业有效证件号码</th>
					<th style="text-align: center;">经办人姓名</th>
					<th style="text-align: center;">经办人联系电话</th>
					<th style="text-align: center;">经办人联系地址</th>
					<th style="text-align: center;">经办人有效证件类型</th>
					<th style="text-align: center;">经办人有效证件号码</th>
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
						<td style="text-align: center;">${fns:getDictLabel(corpo.agentidtype,'certifiy_type','') }</td>
						<td style="text-align: center;">${corpo.agentidcardnum}</td>
					</tr>
				</tbody>
			</table>
		</c:if>
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
		 <div style="text-align: center; width: 90%;margin-top: 30px;">
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> 
	</form:form>
</body>
</html>