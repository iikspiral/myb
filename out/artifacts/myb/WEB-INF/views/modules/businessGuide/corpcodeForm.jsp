<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%--<script type="text/javascript">--%>
	<%--$(document).ready(function() {--%>
		<%--$("#typeCheck").click(function(){--%>
			<%--var projectId = $("#projectInfoId").val();--%>
			<%--var type = $("#type").val();--%>
			<%--if(type == '0'){//新申请，选择企业--%>
				<%--$.jBox.open("iframe:${ctx}/corporcode/corList?corporationId="+$("#corporUserRelaId").val()+"&projectId="+projectId, "企业选择", 900, 400, {           //如果是修改，传个ID就行了--%>
					<%--buttons: {"确定": "ok", "关闭": true},submit: function (v, h, f) {--%>
						<%--if (v == "ok") {--%>
							<%--var iframeName = h.children(0).attr("name");--%>
							<%--var iframeHtml = window.frames[iframeName];               //获取子窗口的句柄--%>
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
		 <input type="hidden" name="corporUserRelaId" id="corporUserRelaId" />
		 <input type="hidden" name="projectId" id="projectId" />
		<table style="width: 100%;">
			<tr>
				<td style="width: 30%;margin-top:8px;padding-bottom:8px;border-bottom:1px dotted #dddddd;">
					<div id="projectName">
						<label class="control-label">项目选择:</label>
						<div class="controls">
							<input id="proName" style="width:200px" class="pro-Name" type="text" value="" readonly>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td style="width: 30%;margin-top:2px;padding-bottom:8px; border-bottom:1px dotted #dddddd;">
					<div>
						<label class="control-label">业务类型:</label>
						<div class="controls">
							<select name="type" style="width:200px" class="required" id="type">
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
			</tr>
		</table>
		<div id="corpList">
			<table id="contentTable"
				   class="table table-striped table-bordered table-condensed">
				<thead>
				<tr>
					<th style="text-align: center;">序号</th>
					<th style="text-align: center;">企业名称</th>
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
					<tr>
						<td colspan="7" style="text-align: center;">无数据</td>
					</tr>
				</tbody>
			</table>
		</div>