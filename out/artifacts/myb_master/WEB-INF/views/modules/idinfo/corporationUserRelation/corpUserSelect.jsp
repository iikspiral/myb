<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>企业用户列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
 	$(function(){
	    $("#checkAll").click(function(){
 	        if ($(this).attr("checked")) {
 	            $("input[id=select]").each(function() {
 	                $(this).attr("checked", true);
 	            });
 	        } else {
 	            $("input[id=select]").each(function() {
 	                $(this).attr("checked", false);
 	            });
 	        }
 	       	selecValue();
	   	});
        $("input:checkbox[id='select']").click(function(){
      	  	selecValue();
        });
        var boxObj = $("input:checkbox[id='select']");  //获取所有的复选框
   	    var expresslist = $("#userInfoId").val(); //
   	    var express = expresslist.split(','); //去掉它们之间的分割符“，”
   	    for(i=0;i<boxObj.length;i++){
   	       for(j=0;j<express.length;j++){
   	           if(boxObj[i].value == express[j])  //如果值与修改前的值相等
   	           {
   	               boxObj[i].checked= true;
   	               break;
   	           }
   	       }
   	    }

   	  	$("#add").click(function(){
   	  		var corporationId = $("#corporationId").val();
   	  		window.location.href="${ctx}/corpUserInfo/form1?corporationId=" + corporationId;
  		});
	 });


	function selecValue(){
		var checkVal ="";
        $("input[id=select]").each(function() {
            if ($(this).attr("checked")) {
            	checkVal += $(this).val() + ",";
            }
        });
		$("#userInfoId").val(checkVal);


	}

 	function saveCor(){
		var value = $("#userInfoId").val();
		if(value!="") {
			window.parent.reloadCorpUser(value);
			window.parent.window.jBox.close();
		}else {
			$.jBox.tip('【密钥宝服务平台】提示您：当前点击确认 尚未选取任何用户，默认为您【全选】！',
					'success',
					{ closed: function () {
						$("input[id=checkAll]").attr("checked", true);
						$("input[id=select]").each(function() {
		 	                $(this).attr("checked", true);
		 	            });
						selecValue();
						} });
		}

	}


	
	
</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="corporationUserInfo" action="${ctx}/corpUserRel/corpUserList"
	method="post" class="breadcrumb form-search"> 
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input id="userInfoId" type="hidden"/>
		<input id="corporationId" name="corporationId" type="hidden" value="${corporationId}"/>
		<label>用户身份证号：</label>
		<form:input path="idCard" id="idCard" htmlEscape="false" maxlength="600"  
			class="input-medium" />	
		&nbsp; 
		 <label>用户手机号：</label>
		 <form:input path="phoneNum" id="phoneNum" htmlEscape="false" maxlength="600"  
			class="input-medium" />	  
		&nbsp;
		
		<input id="btnSubmit" class="btn btn-primary" type="submit" 
			value="查询" /> 
		<input id="add" class="btn btn-primary" type="button" 
			value="新增" /> 
		
	</form:form> 
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;"><input type="checkbox" id="checkAll" onclick="checkAll(this)"/></th>
				<th style="text-align: center;">用户名称</th>
				<th style="text-align: center;">用户身份证号</th>
				<th style="text-align: center;">用户手机号</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="5" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;"> <input name="corUser" type="checkbox" id="select" value=${obj.id} > </td>
						<td style="text-align: center;">${obj.userName}</td>
						<td style="text-align: center;">${obj.idCard}</td>
						<td style="text-align: center;">${obj.phoneNum}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>	