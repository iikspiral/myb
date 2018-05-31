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
</head>
<body>
	 <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mobileApplyInfo/form?id=${mobileApplyInfo.id}">查询详情</a></li>
	</ul> 
	<form:form id="inputForm" modelAttribute="mobileApplyInfo" method="post" class="form-horizontal">
		   <form:hidden path="id"/> 
		<sys:message content="${message}"/>
		<div class="prompt_tit magin_bottom">
			<span>证书信息</span>
		</div>
		<c:if test="${fn:length(certlist) <= 0}">
			<div class="control-group">
				<div class="controls">
					无证书信息
				</div>
			</div>
		</c:if>
		<c:if test="${fn:length(certlist) > 0}">
		<div class="control-group">
			<label class="control-label">证书序列号:</label>
			<div class="controls">
			${cert.certSn}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书主题:</label>
			<div class="controls">
			${cert.certSubject}
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">CN:</label>
			<div class="controls">
			${cert.commonName}
			</div>
		</div> 
		 <div class="control-group">
			<label class="control-label">证书模板:</label>
			<div class="controls">
			${cert.ctmlName}
			</div>
		</div> 
		 <div class="control-group">
			<label class="control-label">证书状态:</label>
			<div class="controls"> 
			${fns:getDictLabel(cert.certStatus, 'cert_status', '无')}
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">有效期:</label>
			<div class="controls">
			${cert.certValidity}
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">生效期:</label>
			<div class="controls">
			<c:if test="${empty cert.notBefore}">
					无生效期
			</c:if>
			<c:if test="${not empty cert.notBefore}">
			${fn:substring(cert.notBefore,0,4)}-${fn:substring(cert.notBefore,4,6)}-${fn:substring(cert.notBefore,6,8)} ${fn:substring(cert.notBefore,8,10)}:${fn:substring(cert.notBefore,10,12)}:${fn:substring(cert.notBefore,12,14)}
			</c:if>
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">失效期:</label>
			<div class="controls">
			<c:if test="${empty cert.notAfter}">
					无失效期
			</c:if>
			<c:if test="${not empty cert.notAfter}">
			${fn:substring(cert.notAfter,0,4)}-${fn:substring(cert.notAfter,4,6)}-${fn:substring(cert.notAfter,6,8)} ${fn:substring(cert.notAfter,8,10)}:${fn:substring(cert.notAfter,10,12)}:${fn:substring(cert.notAfter,12,14)}
			</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书类型:</label>
			<div class="controls">
			${fns:getDictLabel(cert.certType, 'user_info_type', '无')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核时间:</label>
			<div class="controls">
			<c:if test="${empty cert.optTime}">
					无审核时间
			</c:if>
			<c:if test="${not empty cert.optTime}">
			${fn:substring(cert.optTime,0,4)}-${fn:substring(cert.optTime,4,6)}-${fn:substring(cert.optTime,6,8)} ${fn:substring(cert.optTime,8,10)}:${fn:substring(cert.optTime,10,12)}:${fn:substring(cert.optTime,12,14)}
			</c:if>
			</div>
		</div>
		</c:if>
<!-- 		------------------------------------ -->
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
	<!-- ------------------------- -->
		<c:if test="${fn:length(userList) > 0}">
	 	 <div class="prompt_tit magin_bottom">
			<span>手机申请个人用户信息</span>
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
	  		<div class="prompt_tit magin_bottom">
			<span>手机申请企业信息</span>
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
		<!-------------------------------------- -->
		<c:if test="${fn:length(coruserList) > 0}">
		<div class="prompt_tit magin_bottom">
			<span>手机申请企业用户信息</span>
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