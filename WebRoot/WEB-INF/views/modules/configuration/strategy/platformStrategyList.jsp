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
	
	$(document).ready(function() {
		$("#btnInput").click(function(){
			$.jBox("iframe:${ctx}/strategy/form",{title:"平台策略新增",width:400,height:300, buttons:{"关闭":true}});
		});
	});
	
</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="platformStrategy"
		action="${ctx}/strategy/platformStrategyList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>策略：</label>
		<form:select path="strategy" cssStyle="width:220px">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getDictList('strategy_type') }" itemLabel="label" itemValue="value"/>
		</form:select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" 
			value="查询" />
		&nbsp;<input id="btnInput" class="btn btn-primary" type="button" 
			value="新增" />
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">策略名称</th>
				<th style="text-align: center;">描述</th>
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
						<td style="text-align: center;">${fns:getDictLabel(obj.strategy, 'strategy_type', '无')}</td>
						<td style="text-align: center;">${obj.remarks}</td>
						<td style="text-align: center;">
							<a href="${ctx}/strategy/delete?id=${obj.id}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>