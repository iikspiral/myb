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
				<th style="text-align: center;">用户名</th>
				<th style="text-align: center;">电话号码</th>
				<th style="text-align: center;">联系地址</th>
				<th style="text-align: center;">证件号码</th>
				<th style="text-align: center;">单位名称</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty userInfos || userInfos.size() <= 0}">
				<tr><td colspan="6" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${userInfos.size() > 0}">
				<c:forEach items="${userInfos}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${status.count}</td>
						<td style="text-align: center;">${obj.username}</td>
						<td style="text-align: center;">${obj.phonenum}</td>
						<td style="text-align: center;">${obj.usercontactaddr}</td>
						<%-- <td style="text-align: center;">${obj.idCardType}</td> --%>
						<td style="text-align: center;">${obj.useridcardnum}</td>
						<td style="text-align: center;">${obj.org}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<input type="hidden" id="ids" value="userInfoId">
	</table>
</body>
</html>