]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信平台管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}

		$(document).ready(function() {
			$("#btnTest").click(function(){
				window.location.href="${ctx}/smsPlatform/sendTest";
			});
		});

		function cancelAdmin(id) {
			var submit = function (v, h, f) {
			    if (v == 'yes') {
			        $.jBox.tip('已保存。', 'success');
			        window.location.href="${ctx}/smsPlatform/del?id="+id;
			    }
			    if (v == 'no') {
			        $.jBox.tip('没保存。');
			    }
			    if (v == 'cancel') {
			        $.jBox.tip('已取消。');
			    }
			    return true;
			};
			$.jBox.warning("您当前操作要删除该条信息，请您确认？", "提示", submit);
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/smsPlatform/list">短信平台列表</a></li>
		<li><a href="${ctx}/smsPlatform/form">短信平台添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="smsPlatform" action="${ctx}/smsPlatform/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>短信平台名称：</label><form:input path="smsName" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;&nbsp;<label>状态 ：</label>
		<form:select path="state" htmlEscape="false" maxlength="50" class="input-medium">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getDictList('smsPlatform_status') }" itemLabel="label" itemValue="value"/>
		</form:select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		&nbsp;<input class="btn btn-primary" type="reset" value="清空"/>
		&nbsp;<input id="btnTest" class="btn btn-primary" type="button" value="验证码测试"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<td width="20%">短信平台名称</td>
			<td width="20%">短信平台编码</td>
			<td width="15%">状态</td>
			<td width="15%">修改日期</td>
			<td width="5%">操作</td>
		</tr>
		<c:forEach items="${page.list}" var="smsPlatform">
			<tr>
				<td><a href="${ctx}/smsPlatform/form?id=${smsPlatform.id}">${smsPlatform.smsName}</a></td>
				<td>${smsPlatform.smsCode}</td>
				<td>${fns:getDictLabel(smsPlatform.state, 'smsPlatform_status', '')}</td>
				<td><fmt:formatDate value="${smsPlatform.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<a href="${ctx}/smsPlatform/form?id=${smsPlatform.id}" >编辑</a>&nbsp;
					<a href="javascript:void(0);" onclick="cancelAdmin('${smsPlatform.id}')">删除</a>&nbsp;
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>