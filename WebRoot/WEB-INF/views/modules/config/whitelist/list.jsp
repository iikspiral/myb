<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>终端版本</title>
<meta name="decorator" content="default" />
<style type="text/css">
.input-medium {
    width: 187px;
}
</style>
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		 $("#searchForm").submit(); 
		return false;
	}
	
	
	function cancelAdmin(id) {
		var submit = function (v, h, f) {
		    if (v == true) {
		    	window.location.href="${ctx}/white/delete?id="+id;
		    }
		    return true;
		};
		$.jBox.warning("您当前操作要删除该条白名单信息，请您确认？", "提示", submit, { buttons: { '是': true, '否': false} });
	}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/white/list?clickType=2">白名单列表</a></li>
		<li><a href="${ctx}/white/form">白名单添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="whiteList"
		action="${ctx}/white/list?clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<!-- ----------------------------------- -->
		<div>
			<label>用户名称：</label>
			<form:select id ="userInfoId" path="userInfoName" cssStyle="width:200px" value="${whiteListVo.userInfoName}" >
				<form:option value="" label="请选择用户" />
				<form:options items="${fns:getUserInfoList()}" itemLabel="username" itemValue="username"/>
			</form:select>	
			 &nbsp;
			<label>证书主题：</label>
			<form:input path="searchCertSubject" htmlEscape="false" maxlength="600" value="${whiteListVo.searchCertSubject}"
				class="input-medium" />
			&nbsp;
			 <label >业务类型：</label>
			<form:select id="optType" path="optType" cssStyle="width:100px"  value="${whiteListVo.optType}" >
				<form:option value="" label="请选择" />	
				<form:options items="${fns:getDictList('opt_type') }" itemLabel="label" itemValue="value"/>
			</form:select>
		</div>
		<div style="margin-top: 5px;">
			<label>项目名称：</label>
			<form:select id="projectId" path="projectName" cssStyle="width:200px"  class="required" value="${whiteListVo.projectName}">
				<form:option value="" label="请选择" />
				<form:options items="${fns:getProjectInfoList()}" itemLabel="projectName" itemValue="projectName" />
			</form:select>
			<label style="margin-left: 18px;" >手机号码：</label>
			<form:input path="userPhoneNum" htmlEscape="false" maxlength="600"  value="${whiteListVo.userPhoneNum}"
				class="input-medium" />
			&nbsp; 
			<label>是否生效：</label>
				<form:select path="isUsed" cssStyle="width:100px"  value="${whiteListVo.isUsed}">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('is_required')}" itemLabel="label" itemValue="value"/>
				</form:select>
			<span style="margin-left: 12px;">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
			&nbsp;
			  <input id="btnCancel" class="btn btn-primary" type="button"	value="刷新" 
			onclick="javascript:window.location.href='${ctx}/white/list'" />  
			</span>
		</div>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">用户名称</th>
				<th style="text-align: center;">项目名称</th>
				<th style="text-align: center;">证书主题</th>
				<th style="text-align: center;">证书序列号</th>
				<th style="text-align: center;">业务类型</th>
				<th style="text-align: center;">是否生效</th>
				<th style="text-align: center;">手机号</th>
				<th style="text-align: center;">创建时间</th>
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="10" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="width:1%;text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="width:8%;text-align: center;">${obj.userInfoName}</td>
						<td style="width:10%;text-align: center;">${obj.projectName}</td>
						<td style="width:20%;text-align: center;" class="tip" title="${obj.searchCertSubject}"><c:if test="${empty obj.searchCertSubject}">---</c:if>${obj.searchCertSubject}</td>
						<td style="width:10%;text-align: center;" class="tip" title="${obj.certSn}"><c:if test="${empty obj.certSn}">---</c:if>${obj.certSn}</td>
						<td style="width:5%;text-align: center;">${fns:getDictLabel(obj.optType, 'opt_type', '无')}</td>
						<td style="width:1%;text-align: center;">${fns:getDictLabel(obj.isUsed, 'is_required', '无')}</td>
						<td style="width:5%;text-align: center;">${obj.userPhoneNum}</td>
						<td style="width:10%;text-align: center;"><fmt:formatDate value="${obj.createDate}" type="both" dateStyle="medium" timeStyle="medium" /></td>
						<td style="width:7%;text-align: center;">
							<c:choose>
		        				<c:when test="${obj.isUsed=='0'}">
		               			<a href="${ctx}/white/editor?id=${obj.id}">编辑</a> &nbsp;&nbsp;
		               			<a href="javascript:void(0);" onclick="cancelAdmin('${obj.id}')">删除</a>&nbsp;&nbsp; 
		        				 </c:when>
		 						<c:otherwise>
		   						<a href="javascript:void(0);" onclick="cancelAdmin('${obj.id}')">删除</a>&nbsp;&nbsp; 
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