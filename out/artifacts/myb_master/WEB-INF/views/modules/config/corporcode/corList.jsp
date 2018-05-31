<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>企业申请码</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	//全选
	 $(function(){
              //点击全选的判断
              $("#checkAll").click(function(){
          	        if ($(this).attr("checked")) {  
          	            $("input[name=id]").each(function() {  
          	                $(this).attr("checked", true);  
          	            });  
          	        } else {  
          	            $("input[name=id]").each(function() {  
          	                $(this).attr("checked", false);  
          	            });  
          	        }  
          	      selecValue();
             });
              $("input:checkbox[name='id']").click(function(){
            	  selecValue();
              });
      	    var boxObj = $("input:checkbox[name='id']");  //获取所有的复选框
    	    var corporationId = $("#corporationId").val(); //用el表达式获取在控制层存放的复选框的值为字符串类型
    	    var express = corporationId.split(','); //去掉它们之间的分割符“，” 
    	    for(i=0;i<boxObj.length;i++){
    	       for(j=0;j<express.length;j++){            
    	           if(boxObj[i].value == express[j])  //如果值与修改前的值相等
    	           {
    	               boxObj[i].checked= true;
    	               break;
    	           }
    	       }
    	    } 
	 });
	function selecValue(){
		//隐藏域选中值
		var corporationId = $("#corporationId").val();
		var corpId="";
		var checkVal ="";
		var noChekVal="";
		if(corporationId != null && corporationId != ""){
 		  	//当页选中值
 	        $("input[name=id]").each(function() {  
 	            if ($(this).attr("checked")) {  
 	            	checkVal += $(this).val() + ",";
 	            }else{
 	            	noChekVal += $(this).val() + ",";
 	            } 
 	        }); 
		}else{
 		  	//当页选中值
 	        $("input[name=id]").each(function() {  
 	            if ($(this).attr("checked")) {  
 	            	checkVal += $(this).val() + ",";
 	            }else{
 	            	noChekVal += $(this).val() + ",";
 	            }  
 	        }); 
		}
		if(corporationId != null && corporationId != ""){
			if(noChekVal != ""){
				var express1 = corporationId.split(','); 
				for(j=0;j<express1.length;j++){
					if(noChekVal.indexOf(express1[j]) == -1){
						corpId += express1[j] + ",";
					}
				}
			}else{
				corpId = corporationId;
			}
			if(checkVal != ""){
			    var express = corpId.split(','); 
			       for(i=0;i<corpId.length;i++){
			    	   if(checkVal.indexOf(express[i]) == -1 && typeof(express[i])!="undefined"){
			    		   checkVal += express[i] + ",";
			    	   }
			       }
			}else{
				checkVal = corpId;
			}
		}
		$("#corporationId").val(checkVal);
	}
	function saveCor(){
		var corporationId = $("#corporationId").val();
		if(corporationId == ""){
			alert("请选择证书！！！");
			return;
		}else{
			window.parent.reload(corporationId); 
			window.parent.window.jBox.close(); 
		}
	}
</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="corporcodeVo"
		action="${ctx}/corporcode/corList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input id="corporationId" name="corporationId" type="hidden" value="${corporcodeVo.corporationId}"/>
		<input id="projectId" name="projectId" type="hidden" value="${corporcodeVo.projectId}"/>
		<label>企业名称：</label>
		<form:input path="corpname" htmlEscape="false" maxlength="600"  
			class="input-medium" />	
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" 
			value="查询" />
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;"><input type="checkbox" id="checkAll" /></th>
				<th style="text-align: center;">企业名称</th>
				<th style="text-align: center;">企业用户</th>
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
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="12" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;"><input  name="id" type="checkbox" value=${obj.corporationUserRelation.id}></td>
						<td style="text-align: center;" class="tip" title="${obj.corpname}">${obj.corpname}</td>
						<td style="text-align: center;">${obj.corporationUserInfo.userName}</td>
						<td style="text-align: center;">${obj.legalpersonname}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.corpidtype, 'corpid_type', '无')}</td>
						<td style="text-align: center;">${obj.corpidcardnum}</td>
						<td style="text-align: center;">${obj.agentname}</td>
						<td style="text-align: center;">${obj.agentphone}</td>
						<td style="text-align: center;" class="tip" title="${obj.agentcontactaddr}">${obj.agentcontactaddr}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.agentidtype, 'certifiy_type', '无')}</td>
						<td style="text-align: center;">${obj.agentidcardnum}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
		
</body>
</html>