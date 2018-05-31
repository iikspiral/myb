<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户扩展域列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function deleteMsg(id) {
		var submit = function (v, h, f) {
		    if (v == true) {
    		   window.location.href="${ctx}/extrainfo/delete?id="+id;
		    }
		    return true;
		};
		$.jBox.warning("是否删除该条信息？", "提示", submit, { buttons: { '是': true, '否': false} });
	}	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/extrainfo/list?clickType=2">用户扩展域列表</a></li>
		<li><a href="${ctx}/extrainfo/form">用户扩展域添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="extraInfo"
		action="${ctx}/extrainfo/list?clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>用户类型：</label>
		<form:select path="userInfoType" cssStyle="width:213px" value="${extraInfoVo.userInfoType}">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getDictList('user_info_type') }" itemLabel="label" itemValue="value"/>
		</form:select>
		<label>字段类型：</label>
		<form:select path="dataType" cssStyle="width:213px" value="${extraInfoVo.dataType}">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getDictList('field_type') }" itemLabel="label" itemValue="value"/>
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
				<th style="text-align: center;">用户类型</th>
				<th style="text-align: center;">代表含义</th>
				<th style="text-align: center;">数据类型</th>
				<th style="text-align: center;">对应属性</th>
				<th style="text-align: center;">是否必填</th>
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
						<td style="text-align: center;">${fns:getDictLabel(obj.userInfoType, 'user_info_type', '无')}</td>
						<td style="text-align: center;">${obj.extraMean}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.dataType, 'field_type', '无')}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.attribute, 'extra_type', '无')}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.isRequired, 'is_required', '无')}</td>
						<td style="text-align: center;">
							<a href="${ctx}/extrainfo/form?id=${obj.id}">编辑</a> &nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="deleteMsg('${obj.id}')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>