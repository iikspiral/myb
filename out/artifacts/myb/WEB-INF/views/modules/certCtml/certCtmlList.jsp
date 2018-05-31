]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
		$(document).ready(function() {
			$("#btnSynch").click(function(){
				$.ajax({
					type:'POST',
					url:'${ctx}/certCtml/synchCtml',
					success:function(data){
						if(data){
							alert("模板同步成功！");
						}else{
							alert("模板同步失败！");
						}
						window.location.href="${ctx}/certCtml/";
					}
				});
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/certCtml/">模板列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="certCtml" action="${ctx}/certCtml/list/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称：</label><form:input path="certCtmlName" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		&nbsp;<input id="btnSynch" class="btn btn-primary" type="button" value="模板同步"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>模板名称</th>
			<th>模板类型</th>
			<th>模板状态</th>
			<th>模板basedn</th>
			<th>审核策略</th>
			<th>密钥类型</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="certCtml">
			<tr>
				<td><a href="${ctx}/certCtml/form?id=${certCtml.id}">${certCtml.certCtmlName}</a></td>
				<td>${fns:getDictLabel(certCtml.certCtmlType, 'cert_ctml_type', '')}</td>
				<td>${fns:getDictLabel(certCtml.certCtmlStatus, 'cert_ctml_status', '')}</td>
				<td>${certCtml.certCtmlBasedn}</td>
				<td>${fns:getDictLabel(certCtml.certCtmlAudit, 'ctml_audit_type', '')}</td>
				<td>${certCtml.secretType}</td>
				<td>
					<a href="${ctx}/certCtml/form?id=${certCtml.id}" >查看</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>