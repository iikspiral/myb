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
	function deleteMsg(id) {
		var submit = function (v, h, f) {
		    if (v == true) {
		    	$.ajax({
		    		url : "${ctx}/corporationInfo/whiteIs?id="+id, 
					type : "post", 
					success : function(result) { 
						if(result=="OK") {
							 $.jBox.tip('【密钥宝服务平台】提示您：该用户已被企业白名单引用,无法删除！');
							/*  window.parent.location.reload();
							loading('正在提交，请稍等...'); */
						}else{
							window.location.href="${ctx}/corporationInfo/delete?id="+id;
						}
					} 
		    	});
    		  /*  window.location.href="${ctx}/corporationInfo/delete?id="+id; */
		    }
		    return true;
		};
		$.jBox.warning("是否删除该条信息？", "提示", submit, { buttons: { '是': true, '否': false} });
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/corporationInfo/list?clickType=2">企业信息列表</a></li>
		<li><a href="${ctx}/corporationInfo/form">企业信息添加</a></li>
		<li><a href="${ctx}/corpUserInfo/list">企业用户信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="corporationInfo"
		action="${ctx}/corporationInfo/list?clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>企业名称：</label>
		<form:input path="corpname" htmlEscape="false" maxlength="600"  value="${corporationInfoVo.corpname}"
			class="input-medium" />	
		<label>法人代表：</label>
		<form:input path="legalpersonname" htmlEscape="false" maxlength="600"  value="${corporationInfoVo.legalpersonname}"
			class="input-medium" />
		<label>经办人姓名：</label>
		<form:input path="agentname" htmlEscape="false" maxlength="600"  value="${legalpersonname.agentname}"
			class="input-medium" />		
		<label>经办人联系地址：</label>
		<form:input path="agentcontactaddr" htmlEscape="false" maxlength="600"  value="${legalpersonname.agentcontactaddr}"
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
				<th style="text-align: center;">企业名称</th>
				<th style="text-align: center;">法人代表</th>
				<th style="text-align: center;">企业有效证件类型</th>
				<th style="text-align: center;">企业有效证件号码</th>
				<th style="text-align: center;">经办人姓名</th>
				<th style="text-align: center;">经办人联系电话</th>
				<th style="text-align: center;">经办人联系地址</th>
				<!-- <th style="text-align: center;">经办人有效证件类型</th>
				<th style="text-align: center;">经办人有效证件号码</th> -->
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="11" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="width:200px;text-align: center;">${obj.corpname}</td>
						<td style="text-align: center;">${obj.legalpersonname}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.corpidtype,'corpid_type', '')}</td>
						<td style="text-align: center;">${obj.corpidcardnum}</td>
						<td style="text-align: center;">${obj.agentname}</td>
						<td style="text-align: center;">${obj.agentphone}</td>
						<td class="tip" title="${obj.agentcontactaddr}">${obj.agentcontactaddr}</td>
						<%-- <td style="text-align: center;">${fns:getDictLabel(obj.agentidtype, 'certifiy_type', '无')}</td>
						<td style="text-align: center;">${obj.agentidcardnum}</td> --%>
						<td style="text-align: center;">
							<span style="margin-left: 10px;">
							<a href="${ctx}/corporationInfo/form?id=${obj.id}">编辑</a>
							<a href="javascript:void(0);" onclick="deleteMsg('${obj.id}')">删除</a>
							</span>
							<a href="${ctx}/corpUserRel/listByCorpId?corporationId=${obj.id}">查看企业用户</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>