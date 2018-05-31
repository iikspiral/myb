<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目列表</title>
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
		<li class="active"><a href="${ctx}/facewhite/list?clickType=2">人脸白名单列表</a></li>
		<li><a href="${ctx}/facewhite/form">人脸白名单添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="facewhite"
		action="${ctx}/facewhite/list?clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>身份证号码：</label>
		<form:input path="idCardNum" htmlEscape="false" maxlength="600"  value="${facewhiteVo.idCardNum}"
			class="input-medium" />	
		<label>联系人：</label>
		<form:input path="userName" htmlEscape="false" maxlength="600" value="${facewhiteVo.userName}" 
			class="input-medium" />	
		<label>是否生效：</label>
		<form:select path="isEffect" cssStyle="width:75px" value="${facewhiteVo.isEffect}">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getDictList('is_required')}" itemLabel="label" itemValue="value"/>
		</form:select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" 
			value="查询" />
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">身份证号码</th>
				<th style="text-align: center;">联系人</th>
				<th style="text-align: center;">联系电话</th>
				<th style="text-align: center;">是否生效</th>
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="13" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;">${obj.idCardNum}</td>
						<td style="text-align: center;">${obj.userName}</td>
						<td style="text-align: center;">${obj.userPhone}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.isEffect, 'is_required', '无')}</td>
						<td style="text-align: center;">
							<a href="${ctx}/facewhite/form?id=${obj.id}">编辑</a> &nbsp;&nbsp;
							<a href="${ctx}/facewhite/delete?id=${obj.id}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>