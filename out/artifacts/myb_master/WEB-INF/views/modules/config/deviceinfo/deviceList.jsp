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
		<li class="active"><a href="${ctx}/device/list?clickType=2">设备列表</a></li>
		<li><a href="${ctx}/device/form">设备添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="deviceInfo"
		action="${ctx}/device/list?&clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>设备名称：</label>
		<form:input path="deviceNameeng" htmlEscape="false" maxlength="600" value="${deviceInfoVo.deviceNameeng}"
			class="input-medium" />
		<label>设备ip：</label>
		<form:input path="deviceIp" htmlEscape="false" maxlength="600" value="${deviceInfoVo.deviceIp}"
			class="input-medium" />
		<label>设备端口：</label>
		<form:input path="devicePort" htmlEscape="false" maxlength="600" value="${deviceInfoVo.devicePort}"
			class="input-medium" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" 
			value="查询" />
		&nbsp;
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">设备名称</th>
				<th style="text-align: center;">设备ip</th>
				<th style="text-align: center;">设备端口</th>
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
						<td style="text-align: center;">${obj.deviceNameeng}</td>
						<td class="tip" title="${obj.deviceIp}">${obj.deviceIp}</td>
						<td style="text-align: center;">${obj.devicePort}</td>
						<td style="text-align: center;"><fmt:formatDate value="${obj.updateDate}" type="both"  dateStyle="medium" timeStyle="medium"/></td>
						<td style="text-align: center;">
							<a href="${ctx}/device/form?id=${obj.id}">编辑</a> &nbsp;&nbsp;
							<a href="${ctx}/device/delete?id=${obj.id}" onclick="return confirmx('确认要删除该条设备管理配置吗？', this.href)">删除</a>&nbsp;&nbsp;
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>