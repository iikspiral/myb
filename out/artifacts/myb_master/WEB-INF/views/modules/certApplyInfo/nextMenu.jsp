<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查看详情</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" media="all" href="${ctxStatic}/myb/css/State_style.css"/>
<script>
	function operateApply(){
		var reqId='${reqId}';
		window.location.href="${ctx}/certApplyInfo/RevokeHoldAudit?reqId="+reqId;
	}
	function applySearch(){
		window.location.href="${ctx}/certApplyInfo/list";
	}
	function certSearch(){
		window.location.href="${ctx}/certInfo/list";
	}
</script>
</head>
<body>
	<div class="state_body">
	  <div class="state_top">
	    <div class="state_top_ico"><img src="${ctxStatic}/myb/image/mark_ico.gif" /></div>
	    <div class="state_top_font">
	    	<div class="state_top_prompt"><img src="${ctxStatic}/myb/image/mark_font.gif" /></div>
	    	<div class="state_top_explain">
	    		<c:if test="${operate==1}">
				 	[提交申请操作成功]
				 </c:if>
				 <c:if test="${operate==2}">
				 	[修改申请操作成功]
				 </c:if>
				 <c:if test="${operate==3}">
				 	[审核操作成功]
				 </c:if>
	    	</div>
	    </div>
	    <div class="clear"></div>
	  </div>
	  <div class="success_bottom">
	    <ul>
		     <li><b>您还可以继续：</b></li>
			 <c:if test="${operate==1||operate==2||pass==false}">
				 <li><a href="javascript:operateApply();">操作此申请</a></li>
			 </c:if>
			 <li><a href="javascript:applySearch();">申请查询</a></li>
			 <c:if test="${operate==3}">
			 	<li><a href="javascript:certSearch();">证书查询</a></li>
			 </c:if>
	    </ul>
  	 </div> 
	</div>
</body>
</body>
</html>
