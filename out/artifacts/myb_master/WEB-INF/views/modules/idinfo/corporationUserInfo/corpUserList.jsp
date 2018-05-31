<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>企业用户列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	$(document).ready(function() {
		$("#add").click(function(){
		   window.location.href="${ctx}/corpUserInfo/form" 
		})
	})
	function deleteMsg(id) {
		var submit = function (v, h, f) {
		    if (v == true) {
    		   window.location.href="${ctx}/corpUserInfo/delete?id="+id;
		    }
		    return true;
		};
		$.jBox.warning("是否删除该条信息？", "提示", submit, { buttons: { '是': true, '否': false} });
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/corporationInfo/list">企业信息列表</a></li>
		<li><a href="${ctx}/corporationInfo/form">企业信息添加</a></li>
		<li class="active"><a href="${ctx}/corpUserInfo/list">企业用户信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="corporationUserInfo"
		action="${ctx}/corpUserInfo/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>用户名称：</label>
		<form:input path="userName" htmlEscape="false" maxlength="600"  
			class="input-medium" />	
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" 
			value="查询" />
		&nbsp;<input id="add" class="btn btn-primary" type="button"  
			value="新增" />
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">用户名称</th>
				<th style="text-align: center;">用户身份证号</th>
				<th style="text-align: center;">用户手机号</th>
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="5" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;">${obj.userName}</td>
						<td style="text-align: center;">${obj.idCard}</td>
						<td style="text-align: center;">${obj.phoneNum}</td>
						<td style="text-align: center;">
							<a href="${ctx}/corpUserInfo/form?id=${obj.id}">编辑</a> &nbsp;&nbsp;
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