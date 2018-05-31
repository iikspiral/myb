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
		$("#selectUser").click(function(){
			$.jBox.open(
					"iframe:${ctx}/corpUserRel/corpUserList?corporationId=${corporationUserRelation.corporationId}","企业用户信息",900, 450,{
						buttons: {"确定": "ok", "关闭": true},
							submit: function (v, h, f) {

			                    if (v == "ok") {

			                        var iframeName = h.children(0).attr("name");

			                        var iframeHtml = window.frames[iframeName];   //获取子窗口的句柄

			                        iframeHtml.saveCor();

			                        return false;

			                    }

			                },
			                loaded: function (h) {

			                    $(".jbox-content", document).css("overflow-y", "hidden");

			                } 
							
							
							
							
					});
		});
	});
	
	function setAdmin(id) {
		$.ajax({ 
			url : "${ctx}/corpUserRel/setAdmin?id="+id, 
			type : "post", 
			success : function(result) { 
				if(result=="OK") {
					//window.parent.window.jBox.close();
					location.reload();
				}
			} 
	     }); 
	}
	
	function cancelAdmin(id) {
		$.ajax({ 
			url : "${ctx}/corpUserRel/cancelAdmin?id="+id, 
			type : "post", 
			success : function(result) { 
				if(result=="OK") {
					//window.parent.window.jBox.close();
					location.reload();
				}
			} 
	     }); 
	}
	
	
	function reloadCorpUser(userId){
		var corporationId = $("#corporationId").val();
		window.location.href="${ctx}/corpUserRel/save?userId="+userId+"&corporationId="+corporationId;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/corpUserRel/listByCorpId?corporationId=${corporationUserRelation.corporationId}">企业用户列表</a></li>
		<%-- <li><a href="${ctx}/corpUserRel/corpUserList?corporationId=${corporationUserRelation.corporationId}">企业用户添加</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="corporationUserRelation" 
		action="${ctx}/corpUserRel/listByCorpId" method="post" class="breadcrumb form-search">
		<input id="corporationId" name="corporationId" type="hidden" value="${corporationUserRelation.corporationId}" />
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>用户名称：</label>
		<form:input path="userName" htmlEscape="false" maxlength="600"  
			class="input-medium" />	
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" 
			value="查询" />
		&nbsp;<input id="selectUser" class="btn btn-primary" type="button" 
			value="选择企业用户"/>
		&nbsp;<input id="btnCancel" class="btn btn-primary" type="button"	
			value="返回" onclick="javascript:window.location.href='${ctx}/corporationInfo/list'" />
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
				<th style="text-align: center;">是否管理员</th>
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="6" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;">${obj.userName}</td>
						<td style="text-align: center;">${obj.idCard}</td>
						<td style="text-align: center;">${obj.phoneNum}</td>
						<td style="text-align: center;">
							<c:choose>
								<c:when test="${obj.isAdmin=='0'}"> 
									是
								</c:when>
								<c:otherwise> 
									否
								</c:otherwise>
							</c:choose>
						</td>
						<td style="text-align: center;">
							<a href="${ctx}/corpUserRel/delete?id=${obj.id}">删除</a>
							<c:choose>
								<c:when test="${hasAdmin}"> 
									<c:if test="${obj.isAdmin=='0'}">
										<a href="javascript:void(0);" onclick="cancelAdmin('${obj.id}')">取消管理员身份</a>
									</c:if>
								</c:when>
								<c:otherwise> 
									<a href="javascript:void(0);" onclick="setAdmin('${obj.id}')">设置为管理员</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>