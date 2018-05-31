<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>注销冻结操作</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").click(function(){
				var optType = ${optType=='2'}?'注销':${optType=='3'}?'冻结':'解冻';
				$.ajax({
					url:'${ctx}/certApplyInfo/saveRevokeHoldApply',
					cache: false,
					data: $("#inputForm").serialize(),
					success:function(data){
						var array = data.split(":");
						if(array[0]=='false'){
							alert(optType+"失败！失败原因:" + array[1]);
							window.location.href="${ctx}/certInfo/list";
						}else if(array[0]=='true'){
							if(array[1]==''){
								alert(optType+"成功！");
								window.location.href="${ctx}/certInfo/list";
							}else{
								auditConfirm(optType+"申请成功，是否进行审核？", "${ctx}/certApplyInfo/RevokeHoldAudit?reqId=" + array[1], "${ctx}/certInfo/list");
							}
						}
					}
				});
			});
		});
		// 证书申请审核确认对话框
		function auditConfirm(mess, href, closed){
			var jBoxConfig = {};
			jBoxConfig.defaults = {
				showClose:false
			};
			top.$.jBox.setDefaults(jBoxConfig);
			top.$.jBox.confirm(mess,'系统提示',function(v,h,f){
				 if(v=='ok'){
					 location = href;
				 }else if(v=='cancel'){
					 location = closed;
				 }
			},{buttonsFocus:1, closed:function(){
				closeTip();
			}});
			top.$('.jbox-body .jbox-icon').css('top','55px');
			return false;
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/certInfo/list">证书查询列表</a></li>
		<li><a href="${ctx}/certInfo/form?id=${certVo.certInfo.id}">查询详情</a></li>
		<li class="active"><a href="">${optType=='2'?'注销':'冻结'}</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="certVo" method="post" action="${ctx}/certApplyInfo/saveRevokeHoldApply" class="form-horizontal">
		<sys:message content="${message}"/>
		<input name="optType" type="hidden" value="${optType}"/>
		<form:hidden path="certapplyInfo.certSn"/>
		<br>
		<div class="control-group">
			<label class="control-label">证书序列号:</label>
			<div class="controls">
				${certVo.certInfo.certSn}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书主题:</label>
			<div class="controls">
			${certVo.certInfo.certSubject}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">CN:</label>
			<div class="controls">
			${certVo.certInfo.commonName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书模板:</label>
			<div class="controls">
			${certVo.certInfo.ctmlName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">授权码:</label>
			<div class="controls">
			${certVo.certInfo.authCode}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生效时间:</label>
			<div class="controls">
					${fn:substring(certVo.certInfo.notBefore,0,4)}-${fn:substring(certVo.certInfo.notBefore,4,6)}-${fn:substring(certVo.certInfo.notBefore,6,8)}
					${fn:substring(certVo.certInfo.notBefore,8,10)}:${fn:substring(certVo.certInfo.notBefore,10,12)}:${fn:substring(certVo.certInfo.notBefore,12,14)}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">失效时间:</label>
			<div class="controls">
					${fn:substring(certVo.certInfo.notAfter,0,4)}-${fn:substring(certVo.certInfo.notAfter,4,6)}-${fn:substring(certVo.certInfo.notAfter,6,8)}
					${fn:substring(certVo.certInfo.notAfter,8,10)}:${fn:substring(certVo.certInfo.notAfter,10,12)}:${fn:substring(certVo.certInfo.notAfter,12,14)}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效期:</label>
			<div class="controls">
					${certVo.certInfo.certValidity}天
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书状态:</label>
			<div class="controls">
					${fns:getDictLabel(certVo.certInfo.certStatus, 'cert_status', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书类型:</label>
			<div class="controls">
			${fns:getDictLabel(certVo.certInfo.certType, 'cert_type', '')}
			</div>
		</div>
		<c:if test="${optType=='2'}">
			<div class="control-group">
				<label class="control-label">注销原因:</label>
				<div class="controls">
					<form:select path="certapplyInfo.revokeReason" cssStyle="width:270px" class="required">
						<c:forEach items="${fns:getDictList('revoke_type') }" var="revoke" varStatus="i">
							<c:if test="${revoke.value=='0'}">
								<form:option value="${revoke.value}" selected="selected">${revoke.label}</form:option>
							</c:if>
							<c:if test="${revoke.value!='0'}">
								<form:option value="${revoke.value}" >${revoke.label}</form:option>
							</c:if>
						</c:forEach>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">注销描述:</label>
				<div class="controls">
					<form:textarea path="certapplyInfo.revokeDesc" rows="3"></form:textarea>
				</div>
			</div>
		</c:if>
		<c:if test="${optType=='3'}">
			<div class="control-group">
				<label class="control-label">冻结描述:</label>
				<div class="controls">
					<form:textarea path="certapplyInfo.revokeDesc" rows="3"></form:textarea>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="提 交"/>&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>