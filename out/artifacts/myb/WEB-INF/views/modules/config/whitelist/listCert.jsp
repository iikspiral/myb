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
				<th style="text-align: center;">项目</th>
				<th style="text-align: center;">证书主题</th>
				<th style="text-align: center;">证书序列号</th>
				<th style="text-align: center;">证书模板</th>
				<!-- <th style="text-align: center;">授权码</th> -->
				<th style="text-align: center;">证书状态</th>
				<th style="text-align: center;">证书类型</th>
				<th style="text-align: center;">证书有效期</th>
				<th style="text-align: center;">生效时间</th>
				<th style="text-align: center;">失效时间</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty certInfos || certInfos.size() <= 0}">
				<tr><td colspan="7" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${certInfos.size() > 0}">
				<c:forEach items="${certInfos}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${status.count}</td>
						<td style="text-align: center;">${obj.projectName}</td>
						<td style="text-align: center;">${obj.certSubject}</td>
						<td style="text-align: center;">${obj.certSn}</td>
						<td style="text-align: center;">${obj.ctmlName}</td>
						<%-- <td style="text-align: center;">${obj.authCode}</td> --%>
						<%-- <td style="text-align: center;">${obj.certStatus}</td> --%>
						<td style="text-align: center;">${fns:getDictLabel(obj.certStatus, 'cert_status', '')}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.certType, 'cert_type', '')}</td>
						<td style="text-align: center;">${obj.certValidity}</td>
						<td style="text-align: center;">${fn:substring(obj.notBefore,0,4)}-${fn:substring(obj.notBefore,4,6)}-${fn:substring(obj.notBefore,6,8)} ${fn:substring(obj.notBefore,8,10)}:${fn:substring(obj.notBefore,10,12)}:${fn:substring(obj.notBefore,12,14)}</td>
						<td style="text-align: center;">${fn:substring(obj.notAfter,0,4)}-${fn:substring(obj.notAfter,4,6)}-${fn:substring(obj.notAfter,6,8)} ${fn:substring(obj.notAfter,8,10)}:${fn:substring(obj.notAfter,10,12)}:${fn:substring(obj.notAfter,12,14)}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<input type="hidden" id="ids" value="userInfoId">
	</table>
</body>
</html>