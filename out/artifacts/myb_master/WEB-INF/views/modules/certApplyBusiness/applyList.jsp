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
				<!-- <th style="text-align: center;">申请时间</th> -->
				<th style="text-align: center;">项目名称</th>
				<th style="text-align: center;">证书主题</th>
				<th style="text-align: center;">证书序列号</th>
				<th style="text-align: center;">申请类型</th>
				<th style="text-align: center;">申请状态</th>
				<th style="text-align: center;">模板名称</th>
				<th style="text-align: center;">生效时间</th>
				<th style="text-align: center;">有效时间（天）</th>
				<th style="text-align: center;">创建时间</th>
				<!-- <th style="text-align: center;">特殊说明</th> -->
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
						<td style="text-align: center;">${obj.projectName}</td>
						<td style="text-align: center;"><a href="${ctx}/certApplyBusiness/applyform?id=${obj.id}">${obj.certSubject}</a></td>
						<td class="tip" title="${obj.certSn}">${obj.certSn}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.optType, 'ra_opt_type', '无')}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.reqStatus, 'req_status', '无')}</td>
						<td class="tip" title="${obj.ctmlName}">${obj.ctmlName}</td>
						<td style="text-align: center;">${fn:substring(obj.notBefore,0,4)}-${fn:substring(obj.notBefore,4,6)}-${fn:substring(obj.notBefore,6,8)} ${fn:substring(obj.notBefore,8,10)}:${fn:substring(obj.notBefore,10,12)}:${fn:substring(obj.notBefore,12,14)}</td>
						<td style="text-align: center;">${obj.certValidity}</td>
						<td style="text-align: center;"><fmt:formatDate value="${obj.createDate}" type="both"  dateStyle="medium" timeStyle="medium"/></td>
						 <td style="text-align: center;">
							<a href="${ctx}/certApplyBusiness/applyform?id=${obj.id}">查看</a> &nbsp;&nbsp;
						</td>  
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>