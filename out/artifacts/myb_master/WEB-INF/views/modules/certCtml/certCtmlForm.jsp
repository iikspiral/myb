<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模板管理</title>
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

	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/certCtml/">模板列表</a></li>
		<li class="active"><a href="${ctx}/extrainfo/form?id=${extraInfo.id}">模板详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="certCtml" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="prompt_tit magin_bottom">
			<span>基础信息</span>
		</div>
		<div class="control-group">
			<label class="control-label">模板名称:</label>
			<div class="controls">
					${certCtml.certCtmlName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模板类型:</label>
			<div class="controls">
					${fns:getDictLabel(certCtml.certCtmlType, 'cert_ctml_type', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模板状态:</label>
			<div class="controls">
					${fns:getDictLabel(certCtml.certCtmlStatus, 'cert_ctml_status', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模板baseDN:</label>
			<div class="controls">
					${certCtml.certCtmlBasedn}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核策略:</label>
			<div class="controls">
					${fns:getDictLabel(certCtml.certCtmlAudit, 'ctml_audit_type', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密钥类型:</label>
			<div class="controls">
					${certCtml.secretType}
			</div>
		</div>
		<div class="prompt_tit magin_bottom">
			<span>标注扩展域信息</span>
		</div>
		<c:if test="${fn:length(standardExtList) <= 0}">
			<div class="control-group">
				<div class="controls">
					无标准扩展域数据
				</div>
			</div>
		</c:if>
		<c:if test="${fn:length(standardExtList) > 0}">
			<table id="contentTable"
				   class="table table-bordered" style="width: 50%">
				<thead>
				<tr>
					<th style="text-align: center;">标准扩展域名称</th>
					<th style="text-align: center;">标准扩展域子项目名称</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${standardExtList}" var="standardExt" varStatus="status">
					<tr>
						<td style="text-align: center;">${standardExt.extName}</td>
						<td style="text-align: center;">${standardExt.extChildName}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</c:if>
		<div class="prompt_tit magin_bottom">
			<span>自定义扩展域信息</span>
		</div>
		<c:if test="${fn:length(selfExtList) <= 0}">
			<div class="control-group">
				<div class="controls">
					无自定义扩展域数据
				</div>
			</div>
		</c:if>
		<c:if test="${fn:length(selfExtList) > 0}">
			<table id="contentTable"
				   class="table table-bordered" style="width: 50%">
				<thead>
				<tr>
					<th style="text-align: center;">自定义扩展域名称</th>
					<th style="text-align: center;">自定义扩展域oid</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${selfExtList}" var="selfExt" varStatus="status">
					<tr>
						<td style="text-align: center;">${selfExt.selfExtName}</td>
						<td style="text-align: center;">${selfExt.selfExtOid}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</c:if>
		<div class="form-actions">
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>