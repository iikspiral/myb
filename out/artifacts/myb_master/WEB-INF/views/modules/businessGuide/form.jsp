<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>业务向导</title>
    <meta name="decorator" content="default" />
    <style type="text/css">
		/*.guide li{*/
			/*float:left;*/
			/*list-style:none;*/
			/*width: 200px;*/
			/*height: 200px;*/
			/*font-size: 5;*/
		/*}*/
	</style>
	<script type="text/javascript">
		var step = 1;
		var val;
		$(document).ready(function() {
			$("#step2").change(function(){
				val = $('input[name=userType]:checked').val();
				if(val=='userinfo'){
					$("#userinfo").html('<a href="${ctx}/userinfo/form" id="step3" disabled>个人信息增加</a>');
					$("#whitelist").html('<a href="${ctx}/white/form" id="step4" disabled>个人白名单配置</a>');
				}else if(val=='corporation_info'){
					$("#userinfo").html('<a href="${ctx}/corporationInfo/form" id="step3" disabled>企业信息增加</a><br><span><font color="red"> (并维护企业使用人信息)</font> </span>');
					$("#whitelist").html('<a href="${ctx}/corporcode/form" id="step4" disabled>企业白名单配置</a>');
				}
			});
//			$("#back").click(function(){
//				$("#step"+step).attr("disabled","");
//				step--;
//				$("#step"+step).removeAttrs("disabled");
//				if(step<4){
//					$('#next').removeAttrs("disabled");
//				}
//				if(step==1){
//					$('#back').attr("disabled","");
//				}
//			});
			$("#next").click(function(){
				$("#step"+step).attr("disabled","");
				step++;
				$("#step"+step).removeAttrs("disabled");
				if(step < 4 && step > 1){
//					$('#back').removeAttrs("disabled","");
					$('#next').removeAttrs("disabled");
				}
				if(step==4){
					$('#next').attr("disabled","");
					$('#finish').removeAttrs("disabled");
				}
			});
		});
	</script>
</head>
<body>
	<%--<ul class="guide">--%>
		<%--<li>项目新增</li>--%>
		<%--<li>用户新增</li>--%>
		<%--<li>白名单配置</li>--%>
	<%--</ul>--%>
	<%--如果项目存在进行下一步--%>
	<table style="width: 800px;height: 300px" border="1">
		<thead>
			<tr style="height: 20%">
				<td colspan="4" style="text-align:center">业务向导</td>
			</tr>
		</thead>
		<tbody>
			<tr style="height: 5%">
				<td>第一步:新增项目</td>
				<td>第二步:选择用户类型</td>
				<td>第三步:新增用户</td>
				<td>第四步:白名单配置</td>
			</tr>
			<tr>
				<td><a href="${ctx}/projectinfo/form" id="step1">新增项目</a><br>
					<span><font color="red"> (如果项目存在,则进行下一步)</font> </span></td>
				<td>
					<div id="step2" disabled>
						<input type="radio" name="userType" value="userinfo">个人用户
						<input type="radio" name="userType" value="corporation_info">企业用户
					</div>
				</td>
				<td id="userinfo"><a href="${ctx}/userinfo/form" id="step3" disabled>个人信息增加</a></td>
				<td id="whitelist"><a href="${ctx}/white/form" id="step4" disabled>个人白名单配置</a></td>
			</tr>
		</tbody>
		<tfoot>
			<tr style="height: 20%">
				<td colspan="4" style="text-align:center">
					<%--<button id="back" disabled>上一步</button>--%>
					<button id="next">下一步</button>
					<button id="finish" onclick="alert('finish!')" disabled>完成</button>
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>