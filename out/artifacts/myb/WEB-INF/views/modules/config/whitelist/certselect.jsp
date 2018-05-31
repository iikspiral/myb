<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>个人用户列表</title>
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
         	            $("input[id=select]").each(function() {  
         	                $(this).attr("checked", true);  
         	            });  
         	        } else {  
         	            $("input[id=select]").each(function() {  
         	                $(this).attr("checked", false);  
         	            });  
         	        }  
				// ----------
         	      selecValue();
         	 // ----------
            });
          // ------------------------------------------------
             $("input:checkbox[id=select]").click(function(){
           	  selecValue();
             });
          // ------------------------------------------------
           var boxObj = $("input:checkbox[name='iddd']");  //获取所有的复选框
           /* var expresslist = '${certSn}'; //用el表达式获取在控制层存放的复选框的值为字符串类型 */
    	   var expresslist = $("#certSn").val(); //
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
	 });       
//	--------------分页反选控制------------------------------
function selecValue(){
	//隐藏域选中值
	var corporationId = $("#certSn").val();
	var corpId="";
	var checkVal ="";
	var noChekVal="";
	if(corporationId != null && corporationId != ""){
		  	//当页选中值
	        $("input[id=select]").each(function() {  
	            if ($(this).attr("checked")) {  
	            	checkVal += $(this).val() + ",";
	            }else{
	            	noChekVal += $(this).val() + ",";
	            } 
	        }); 
	}else{
		//当页选中值
	        $("input[id=select]").each(function() {  
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
	$("#certSn").val(checkVal);
		
	
}
//	--------------------------------------------
	  
	 function saveCor(){
		var value = $("#certSn").val();
 		/* alert(value); */
			window.parent.reloadcert(value); 
			window.parent.document.getElementById('certSn').value=value;
			window.parent.window.jBox.close(); 
		}
</script>
</head>
<body>
	<div id="inputBox">
	
	<form:form id="searchForm" modelAttribute="certselectVo"
		 method="post" action="${ctx}/white/formCert" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input id="certSn" name="certSn" type="hidden" value="${certselectVo.certSn}"/>
	  <label>证书主题：</label>
		<form:input path="certSubject" id="certSubject" htmlEscape="false" maxlength="600"  
			class="input-medium" />	
		&nbsp;
	 <%--  <label>证书序列号：</label>
		<form:input path="certSn" id="certSn" htmlEscape="false" maxlength="600"  
			class="input-medium" />	 --%>
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" 
			value="查询" /> 
		&nbsp;
		<input id="btnCancel" class="btn btn-primary" type="button"	value="刷新" 
		onclick="javascript:window.location.href='${ctx}/white/formCert'" />
		<%-- <input id="certselectId" name="certselectId" type="hidden" value="${certselectVo.certselectId}"/> --%>
	</form:form>
	 
		<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;"><input  type="checkbox" id="checkAll" onclick="checkAll(this)"/></th>
				<th style="text-align: center;">证书主题</th>
				<th style="text-align: center;">证书序列号</th>
				<th style="text-align: center;">证书类型</th>	
				<th style="text-align: center;">证书状态</th>
				<th style="text-align: center;">有效期</th>
				<th style="text-align: center;">生效时间</th>
				<th style="text-align: center;">失效时间</th>
				
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(page.list) <= 0}">
				<tr><td colspan="9" style="text-align: center;">无数据</td></tr>
			</c:if>
			<c:if test="${fn:length(page.list) > 0}">
				<c:forEach items="${page.list}" var="obj" varStatus="status">
					 <tr>
						<td style="text-align: center;">${page.pageSize*(page.pageNo-1)+status.count}</td>
						<td style="text-align: center;"> <input name="iddd"  type="checkbox" id="select" value=${obj.certSn}> </td>
						<td class="tip" title="${obj.certSubject}">${obj.certSubject}</td>
						<td style="text-align: center;">${obj.certSn}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.certType, 'cert_type', '')}</td>
						<td style="text-align: center;">${fns:getDictLabel(obj.certStatus, 'cert_status', '')}</td>
						<td style="text-align: center;">${obj.certValidity}</td>
						<td style="text-align: center;">${fn:substring(obj.notBefore,0,4)}-${fn:substring(obj.notBefore,4,6)}-${fn:substring(obj.notBefore,6,8)} ${fn:substring(obj.notBefore,8,10)}:${fn:substring(obj.notBefore,10,12)}:${fn:substring(obj.notBefore,12,14)}</td>
						<td style="text-align: center;">${fn:substring(obj.notAfter,0,4)}-${fn:substring(obj.notAfter,4,6)}-${fn:substring(obj.notAfter,6,8)} ${fn:substring(obj.notAfter,8,10)}:${fn:substring(obj.notAfter,10,12)}:${fn:substring(obj.notAfter,12,14)}</td>
						
					</tr> 
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<!-- ------------------------------ -->
	 <input type="hidden" id="aaa" name="aaa">
	</div>
</body>
</html>