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
	function cancelAdmin(id) {
		var submit = function (v, h, f) {
		    if (v == true) {
		    	
		    	$.ajax({
		    		url : "${ctx}/projectinfo/checkProisInwhite?id="+id,
		    		type : "post",
		    		success : function(result){
		    			if(result){
		    				window.location.href="${ctx}/projectinfo/delete?id="+id;
		    			}else{
		    				alert("证书已被白名单使用,无法删除！");
		    			}
		    		}
		    	});
		    }
		    return true;
		};
		$.jBox.warning("确定要删除该条信息？", "提示", submit, { buttons: { '是': true, '否': false} });
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/projectinfo/list?clickType=2">项目列表</a></li>
		<li><a href="${ctx}/projectinfo/form">项目添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="projectInfo"
		action="${ctx}/projectinfo/list?clickType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>项目名称：</label>
		<form:input path="projectName" htmlEscape="false" maxlength="600"   value="${projectInfoVo.projectName}"
			class="input-medium" />	
		<label>证书模板：</label>
		<form:select path="certTemplate" cssStyle="width:213px" value="${projectInfoVo.certTemplate}">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getCertctmlList()}" itemLabel="certCtmlName" itemValue="id"/>
		</form:select>
		<label>是否默认项目：</label>
		<form:select path="isDefaultProject" cssStyle="width:75px" value="${projectInfoVo.isDefaultProject}">
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
				<th style="text-align: center;">项目编号</th>
				<th style="text-align: center;">项目名称</th>
				<th style="text-align: center;">证书模板</th>
				<th style="text-align: center;">制证规则</th>
				<th style="text-align: center;">证书有限期</th>
				<th style="text-align: center;">是否默认项目</th>
				<th style="text-align: center;">允许更新条件(天)</th>
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
						<td class="tip" title="${obj.projectNum}">${obj.projectNum}</td>
						<td class="tip" title="${obj.projectName}">${obj.projectName}</td>
						<td class="tip" title="${fns:getCertname(obj.certTemplate)}">${fns:getCertname(obj.certTemplate)}</td>
						<td class="tip" title="${obj.makeCertRules}">${obj.makeCertRules}</td>
						<td style="text-align: center;">${obj.certValidity}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.isDefaultProject, 'is_required', '无')}</td>
						<td style="text-align: center;">${obj.allowUpdateDay}</td>
						<td style="text-align: center;">
							<span style="margin-left: 2px;"><a href="${ctx}/projectinfo/form?id=${obj.id}">编辑</a></span> 
							<a href="javascript:void(0);" onclick="cancelAdmin('${obj.id}')">删除</a>&nbsp;
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>