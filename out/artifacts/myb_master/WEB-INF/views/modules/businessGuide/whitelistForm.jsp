<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%--<script type="text/javascript">--%>
	<%--$(document).ready(function() {--%>
		<%--/*-弹出选择用户窗口- */--%>
		<%--$("#selected").click(function(){--%>
			<%--var type = $("#optType").val();--%>
			<%--var projectId = $("#projectInfoId").val();--%>
			<%--if(type == '0'){--%>
				<%--$.jBox.open("iframe:${ctx}/white/formUser?userInfoId="+$("#ids").val()+"&projectId="+projectId, "白名单用户添加", 900, 400, {--%>
					<%--buttons: {"确定": "ok", "关闭": true},--%>
	                <%--submit: function (v, h, f) {--%>
	                    <%--if (v == "ok") {--%>
	                        <%--var iframeName = h.children(0).attr("name");--%>
	                        <%--var iframeHtml = window.frames[iframeName];--%>
	                        <%--iframeHtml.saveCor();--%>
	                        <%--return false;--%>
	                    <%--}--%>
	                <%--},--%>
	                <%--loaded: function (h) {--%>
	                    <%--$(".jbox-content", document).css("overflow-y", "hidden");--%>
	                <%--}--%>
	            <%--});--%>
			<%--}--%>
		<%--});--%>
	<%--});--%>
<%--</script>--%>
<input type="hidden" name="whitelistid" id="whitelistid"/>
<input type="hidden" name="userInfoIds" id="ids"/>
<sys:message content="${message}"/>
<table style="width: 100%;">
	<tr>
		<td style="width: 30%;margin-top:8px;padding-bottom:8px;border-bottom:1px dotted #dddddd;">
			<div id="projectName">
				<label class="control-label">项目选择:</label>
				<div class="controls">
					<input id="proName" style="width:200px" class="pro-Name" type="text" readonly>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</td>
		<td style="width: 30%;margin-top:2px;padding-bottom:8px; border-bottom:1px dotted #dddddd;">
			<div>
				<label class="control-label">业务类型:</label>
				<div class="controls">
					<select name="optType" style="width:200px" class="required" id="optType">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('opt_type') }" var="dict">
							<c:if test="${dict.value==0}">
								<option value="${dict.value}" selected>${dict.label}</option>
							</c:if>
						</c:forEach>
					</select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</td>
		<%--<td style="width: 27%; padding-top:8px;padding-bottom:11px; border-bottom:1px dotted #dddddd;">--%>
			<%--<input id="selected" class="btn btn-primary" type="button" value="选择"/>--%>
		<%--</td>--%>
	</tr>
</table>
<div id="userList">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th style="text-align: center;">序号</th>
			<th style="text-align: center;">用户名</th>
			<th style="text-align: center;">电话号码</th>
			<th style="text-align: center;">联系地址</th>
			<th style="text-align: center;">证件号码</th>
			<th style="text-align: center;">单位名称</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td colspan="7" style="text-align: center;">无数据</td>
		</tr>
		</tbody>
	</table>
</div>
