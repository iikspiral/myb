<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
		
			function cancelAdmin(id) {
				var submit = function (v, h, f) {
				    if (v == 'yes') {
				        $.jBox.tip('已保存。', 'success');
				        window.location.href="${ctx}/smsTemplate/del?id="+id;
				    }
				    if (v == 'no') {
				        $.jBox.tip('没保存。');
				    }
				    if (v == 'cancel') {
				        $.jBox.tip('已取消。');
				    }
				    return true;
				};
				$.jBox.warning("您当前操作要删除该条信息，请您确认？", "提示", submit);
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/smsTemplate/temList">短信模板列表</a></li>
		<li><a href="${ctx}/smsTemplate/temForm">短信模板添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="smsTemplate" action="${ctx}/smsTemplate/temList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>模板名称：</label><form:input path="templateName" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;&nbsp;<label>模板类型 ：</label>
		<form:select path="templateType" htmlEscape="false" maxlength="50" class="input-medium">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getDictList('smsTemplate_type') }" itemLabel="label" itemValue="value"/>
		</form:select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		&nbsp;<input class="btn btn-primary" type="reset" value="清空"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<td width="20%">模板ID</td>
			<td width="20%">模板名称</td>
			<td width="15%">模板类型</td>
			<td width="15%">短信签名</td>
			<td width="5%">操作</td>
		</tr>
		<c:forEach items="${page.list}" var="smsTemplate">
			<tr>
				<td><a href="${ctx}/smsTemplate/temForm?id=${smsTemplate.id}">${smsTemplate.templateId}</a></td>
				<td>${smsTemplate.templateName}</td>
				<td>${fns:getDictLabel(smsTemplate.templateType, 'smsTemplate_type', '')}</td>
				<td>${smsTemplate.smsSignature}</td>
				<td>
					<a href="${ctx}/smsTemplate/temForm?id=${smsTemplate.id}" >编辑</a>&nbsp;
					<a href="javascript:void(0);" onclick="cancelAdmin('${smsTemplate.id}')">删除</a>&nbsp;
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>