<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>企业申请码</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/problem/getProblem?id=${problem.id}">反馈详情</a></li>
	</ul><br/>
<form:form id="inputForm" modelAttribute="problem" action="" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">反馈问题详情:</label>
			<div class="controls">
				<label class="lbl">${problem.feedbackProblem}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系方式:</label>
			<div class="controls">
				<label class="lbl">${problem.contact}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评分:</label>
			<div class="controls">
				<label class="lbl">${problem.score}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片详情:</label>
			<c:if test="${fn:length(problem.picList) > 0}">
			<div class="controls">
				<c:forEach items="${problem.picList}" var="obj" varStatus="status">
						<img style="padding: 3px; border: 0px currentColor; max-height: 550px; max-width: 380px; _height: 550px;" height="550" width="380" src="data:image/jpeg;base64,${obj}">
				</c:forEach>
			</div>
			</c:if>
		</div>
		<div style="text-align: center; width: 90%;margin-top: 30px;">
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> 
</form:form>	
</body>
</html>