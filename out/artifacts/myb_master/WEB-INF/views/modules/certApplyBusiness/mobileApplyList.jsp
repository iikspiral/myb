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
	
</script>
</head>
<body>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">项目名称</th>
				<th style="text-align: center;">证书主题</th>
				<th style="text-align: center;">证书序列号</th>
				<th style="text-align: center;">用户类型</th>
				<th style="text-align: center;">手机号</th>
				<th style="text-align: center;">申请类型</th>
				<th style="text-align: center;">申请结果</th>
				<th style="text-align: center;">设备类型</th>
				<th style="text-align: center;">设备号</th>
				<th style="text-align: center;">创建时间</th>
			    <th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="12" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;">${obj.projectName}</td>
						<td class="tip" title="${obj.certSubject}">${obj.certSubject}</td>
						<td style="text-align: center;">${obj.certSn}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.userType, 'cert_type', '')}</td>
						<td style="text-align: center;"><a href="${ctx}/certApplyBusiness/applyform?id=${obj.id}">${obj.phoneNum}</a></td>
						<td style="text-align: center;">${fns:getDictLabel(obj.applyType, 'opt_type', fns:getDictLabel(obj.applyType, 'opt_type_other', ''))}</td>
						<td class="tip" title="${obj.applyResult}">${obj.applyResult}</td>
						<td style="text-align: center;">${obj.deviceType}</td>
						<td style="text-align: center;">${obj.deviceNum}</td>
						<td style="text-align: center;"><fmt:formatDate value="${obj.createDate}" type="both"  dateStyle="medium" timeStyle="medium"/></td>
						<td style="text-align: center;">
							<a href="${ctx}/mobileApplyInfo/form?id=${obj.id}">查看</a> &nbsp;&nbsp;
						</td>  
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>