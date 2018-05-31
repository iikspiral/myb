<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>抽取比例列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/application/list?clickType=2">系统列表</a></li>
		<li><a href="${ctx}/application/form">系统添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="applicationSystem"
		action="${ctx}/application/list?&clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>系统名称：</label>
		<form:input path="systemName" htmlEscape="false" maxlength="600" value="${applicationSystemVo.systemName}"
			class="input-medium" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" 
			value="查询" />
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">省</th>
				<th style="text-align: center;">市</th>
				<th style="text-align: center;">县</th>
				<th style="text-align: center;">系统名称</th>
				<th style="text-align: center;">应用标识</th>
				<th style="text-align: center;">更新时间</th>
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="9" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;">${fns:getAreaname(obj.province)}</td>
						<td style="text-align: center;">${fns:getAreaname(obj.city)}</td>
						<td style="text-align: center;">${fns:getAreaname(obj.country)}</td>
						<td style="text-align: center;">${obj.systemName}</td>
						<td style="text-align: center;">${obj.systemFlag}</td>
						<td style="text-align: center;"><fmt:formatDate value="${obj.updateDate}" type="both"  dateStyle="medium" timeStyle="medium"/></td>
						<td style="text-align: center;">
							<a href="${ctx}/application/form?id=${obj.id}">编辑</a> &nbsp;&nbsp;
							<a href="${ctx}/application/delete?id=${obj.id}" onclick="return confirmx('确认要删除该条应用系统配置吗？', this.href)">删除</a>&nbsp;&nbsp;
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>