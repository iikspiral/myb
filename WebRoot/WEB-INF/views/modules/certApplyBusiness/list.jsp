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
	function mobileApply(id) {
		$.jBox("iframe:${ctx}/certApplyBusiness/mobileApply?id="+id,{
			title:"手机申请详情显示",
			width:1100, 
			height:500,
			top:'5%',
			persistent:true,
			buttons:{"关闭":true}});
		}
	
	function cert(id) {
			$.jBox("iframe:${ctx}/certApplyBusiness/cert?id="+id,{
				title:"证书详情显示",
				width:1100, 
				height:500,
				top:'5%',
				persistent:true,
				buttons:{"关闭":true}
			});
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/certApplyBusiness/list?clickType=2">证书查询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="certapplyBusinessInfo"
		action="${ctx}/certApplyBusiness/list?&clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<div >	
			<label>证书主题：</label>
			<form:input path="certSubject" htmlEscape="false" maxlength="600" width="205px"/>
			<label style="margin-left: 22px;" >手机号：</label>
			<form:input path="phoneNum" htmlEscape="false" maxlength="600" width="205px"/>
			<label >申请结果：</label>
			<form:select id="applyResult" path="applyResult" cssStyle="width:200px"   >
				<form:option value="" label="请选择" />
				<form:options items="${fns:getDictList('apply_result') }" itemLabel="label" itemValue="value"/>
			</form:select>
		</div>
		<div style="margin-top: 5px;">
			<label style="margin-left: -4px;">证书序列号：</label>
			<form:input path="certSn" htmlEscape="false" maxlength="600" width="205px"/>
			 <label style="margin-left: 10px;">用户名称：</label>
			<form:input path="userName" htmlEscape="false" maxlength="600" width="205px"/> 
			 <label>项目名称：</label>
			 <form:select path="projectName" id="projectName" cssStyle="width:200px">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getProjectInfoList()}" itemLabel="projectName" itemValue="id"/>
			</form:select> 
			&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" 
				value="查询" />
		</div>	
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:1%;text-align: center;">序号</th>
				<th style="width:10%;text-align: center;">项目名称</th>
				<th style="width:20%;text-align: center;">证书主题</th>
				<th style="width:10%;text-align: center;">证书序列号</th>
				<th style="width:5%;text-align: center;">用户类型</th>
				<th style="width:5%;text-align: center;">用户名称</th>
				<th style="width:5%;text-align: center;">申请类型</th>
				<th style="width:5%;text-align: center;">申请结果</th>
				<th style="width:5%;text-align: center;">手机号</th>
				<th style="width:10%;text-align: center;">申请时间</th>
			    <th style="width:7%;text-align: center;">操作</th>
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
						<td style="text-align: center;">${obj.projectName}</td>
						<td class="tip" title="${obj.certSubject}">${obj.certSubject}</td>
						<td style="text-align: center;">${obj.certSn}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.userType, 'cert_type', '')}</td>
						<td style="text-align: center;">${obj.userName}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.applyType, 'opt_type', fns:getDictLabel(obj.applyType, 'opt_type_other', ''))}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.applyResult, 'apply_result', '')}</td>
						<td style="text-align: center;">${obj.phoneNum}</td>
						<td style="text-align: center;"><fmt:formatDate value="${obj.createDate}" type="both"  dateStyle="medium" timeStyle="medium"/></td>
						<td style="text-align: center;" >
							<span style="margin-left: 17px;"><a href="javascript:void(0);" onclick="mobileApply('${obj.id}')">申请详情</a></span>&emsp;
							<span style="margin-left: 17px;"><a href="javascript:void(0);" onclick="cert('${obj.id}')">证书详情</a></span>&emsp;
						</td>  
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>