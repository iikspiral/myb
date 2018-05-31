<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>抽取比例列表</title>
<script type="text/javascript">

</script>
</head>
<body>
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
				<th style="text-align: center;">经办人有效证件类型</th>
				<th style="text-align: center;">经办人有效证件号码</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty corporList || corporList.size() <= 0}">
				<tr><td colspan="10" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${corporList.size() > 0}">
				<c:forEach items="${corporList}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${status.count}</td>
						<td style="text-align: center;">${obj.corpname}</td>
						<td style="text-align: center;">${obj.legalpersonname}</td>
						<%-- <td style="text-align: center;">${obj.corpidtype}</td> --%>
						<td style="text-align: center;">${fns:getDictLabel(obj.corpidtype, 'corpid_type', '无')}</td>
						<td style="text-align: center;">${obj.corpidcardnum}</td>
						<td style="text-align: center;">${obj.agentname}</td>
						<td style="text-align: center;">${obj.agentphone}</td>
						<td style="text-align: center;">${obj.agentcontactaddr}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.agentidtype, 'certifiy_type', '无')}</td>
						<td style="text-align: center;">${obj.agentidcardnum}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>