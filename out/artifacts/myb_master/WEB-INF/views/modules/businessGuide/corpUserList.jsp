<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#selectCorpUser").unbind('click').click(function () {
			var val = $('input[name=isHasCorp11]:checked').val();
			var corpId = "";
			if(val==0) { //已有项目
				corpId = $('#corpSelect option:selected').val();
			}else{
				corpId = $('#corporationId').val();
			}
			$.jBox.open("iframe:${ctx}/corpUserRel/corpUserList?corporationId="+corpId, "企业用户信息", 900, 450, {
				buttons: {"确定": "ok", "关闭": true},
				submit: function (v, h, f) {
					if (v == "ok") {
						var iframeName = h.children(0).attr("name");
						var iframeHtml = window.frames[iframeName];
						iframeHtml.saveCor();
						return false;
					}
				},
				loaded: function (h) {
					$(".jbox-content", document).css("overflow-y", "hidden");
				}
			});
		});
	});
	function reloadCorpUser(userId){
		var val = $('input[name=isHasCorp11]:checked').val();
		var projectId = $('#projectInfoId').val();
		var corpId = "";
		if(val==0) { //已有项目
			corpId = $('#corpSelect option:selected').val();
		}else{
			corpId = $('#corporationId').val();
		}
		$("#corporationUserInfo").empty();
		$("#corporationUserInfo").load('${ctx}/businessGuide/listByCorpId?corporationId='+corpId+'&userId='+userId+'&projectId'+projectId);
	}
	function setAdmin(id) {
		$.ajax({
			url : "${ctx}/corpUserRel/setAdmin?id="+id,
			type : "post",
			success : function(result) {
				if(result=="OK") {
					reloadCorpUser("");
				}
			}
	     });
	}
	function cancelAdmin(id) {
		$.ajax({
			url : "${ctx}/corpUserRel/cancelAdmin?id="+id,
			type : "post",
			success : function(result) {
				if(result=="OK") {
					reloadCorpUser("");
				}
			}
	     });
	}
	function deleteRel(id) {
		$.ajax({
			url : "${ctx}/businessGuide/deleteCorpUserRel?id="+id,
			type : "post",
			success : function(result) {
				var array = result.split(":");
				if(array[0]=='true'){
					reloadCorpUser("");
				}else{
					$.jBox.tip('【密钥宝服务平台】提示：'+ array[1]);
				}
			}
		});
	}
</script>
<div id="corporationUserInfo">
	<input id="selectCorpUser" class="btn btn-primary" type="button" value="选择企业用户"/>
	<table id="corpUserTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th colspan="7" style="text-align: center;font-size: large">企业人员列表</th></tr>
		<tr>
			<th style="text-align: center;">序号</th>
			<th style="text-align: center;">ID</th>
			<th style="text-align: center;">用户名称</th>
			<th style="text-align: center;">用户身份证号</th>
			<th style="text-align: center;">用户手机号</th>
			<th style="text-align: center;">是否管理员</th>
			<th style="text-align: center;">操作</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(corporationUserRelations) <= 0}">
			<tr><td colspan="7" style="text-align: center;">无数据</td></tr>
		</c:if>
		<c:if test="${fn:length(corporationUserRelations) > 0}">
			<c:forEach items="${corporationUserRelations}" var="obj" varStatus="status">
				<tr>
					<td style="text-align: center;">${status.index}</td>
					<td style="text-align: center;">
						<c:if test="${not empty obj.isChoose && obj.isChoose == 'false'}"><input  name="corpUserRelaID" type="checkbox" value=${obj.id}></c:if>
					</td>
					<td style="text-align: center;">${obj.userName}</td>
					<td style="text-align: center;">${obj.idCard}</td>
					<td style="text-align: center;">${obj.phoneNum}</td>
					<td style="text-align: center;">
						<c:choose>
							<c:when test="${obj.isAdmin=='0'}">
								是
							</c:when>
							<c:otherwise>
								否
							</c:otherwise>
						</c:choose>
					</td>
					<td style="text-align: center;">
						<a href="javascript:void(0);" onclick="deleteRel('${obj.id}')">删除</a>
						<c:choose>
							<c:when test="${hasAdmin}">
								<c:if test="${obj.isAdmin=='0'}">
									<a href="javascript:void(0);" onclick="cancelAdmin('${obj.id}')">取消管理员身份</a>
								</c:if>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" onclick="setAdmin('${obj.id}')">设置为管理员</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
</div>

