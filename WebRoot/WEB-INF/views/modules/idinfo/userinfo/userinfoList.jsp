<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>个人用户列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	function cancelAdmin(id) {
    	$.ajax({
    		url : "${ctx}/userinfo/whiteIs?id="+id, 
			type : "post", 
			success : function(result) { 
				if(result=="OK") {
					 $.jBox.tip('该用户已被个人白名单引用,无法删除!');
					 return false;
				}else{
					return confirmx('确认要删除吗？', "${ctx}/userinfo/delete?id="+id);
				}
			} 
    	});
	}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/userinfo/list?clickType=2">个人用户列表</a></li>
		<li><a href="${ctx}/userinfo/form">个人用户添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="userInfo"
		action="${ctx}/userinfo/list?clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>用户名：</label>
		<form:input path="username" htmlEscape="false" maxlength="600"  value="${userInfoVo.username}"
			class="input-medium" />	
		<label>联系地址：</label>
		<form:input path="usercontactaddr" htmlEscape="false" maxlength="600" value="${userInfoVo.usercontactaddr}"  
			class="input-medium" />	
		<label>单位名称：</label>
		<form:input path="org" htmlEscape="false" maxlength="600"  value="${userInfoVo.org}" 
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
				<th style="text-align: center;">用户名</th>
				<th style="text-align: center;">电话号码</th>
				<th style="text-align: center;">联系地址</th>
				<th style="text-align: center;">证件类型</th>
				<th style="text-align: center;">证件号码</th>
				<th style="text-align: center;">单位名称</th>
				<th style="text-align: center;">用户类型</th>
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
						<td style="text-align: center;">${obj.username}</td>
						<td style="text-align: center;">${obj.phonenum}</td>
						<td class="tip" title="${obj.usercontactaddr}">${obj.usercontactaddr}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.idtype, 'certifiy_type', '无')}</td>
						<td style="text-align: center;">${obj.useridcardnum}</td>
					    <td style="text-align: center;">${obj.org}</td> 
						<td style="text-align: center;">${fns:getDictLabel(obj.userType, 'user_type', '无')}</td>
						<td style="text-align: center;">
							<a href="${ctx}/userinfo/form?id=${obj.id}">编辑</a> &nbsp;&nbsp;
							<%-- <a href="${ctx}/userinfo/delete?id=${obj.id}">删除</a> --%>
							<%-- <a href="javascript:void(0);" onclick="cancelAdmin('${obj.id}')">删除</a> --%>
							<a href="javascript:void(0);" onclick="cancelAdmin('${obj.id}')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>