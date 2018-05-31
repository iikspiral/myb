<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>注销冻结审核操作</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var val = $('input[name=auditStatus]:checked').val();
			reasonHideOrShow(val)
			$("input[name=auditStatus]").click(function(){
				val = $('input[name=auditStatus]:checked').val();
				reasonHideOrShow(val)
			});
		});
		function reasonHideOrShow(val) {
			if(val==0){
				$("#reason").hide();
			}else{
				$("#reason").show();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/certInfo/list">证书查询列表</a></li>
		<li><a href="${ctx}/certInfo/form?id=${certVo.certInfo.id}">查询详情</a></li>
		<li class="active"><a href="javascript:void(0);">审核</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="certVo" method="post" action="${ctx}/certApplyInfo/saveRevokeHoldAudit" class="form-horizontal">
		<sys:message content="${message}"/>
		<form:hidden path="certapplyInfo.id"/>
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
			<label class="control-label">申请状态:</label>
			<div class="controls">
					${fns:getDictLabel(certVo.certapplyInfo.reqStatus, 'req_status', '')}
			</div>
		</div>
		<c:if test="${certVo.certapplyInfo.optType=='2'}">
			<div class="control-group">
				<label class="control-label">注销原因:</label>
				<div class="controls">
						${certVo.certapplyInfo.revokeName}
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">注销描述:</label>
				<div class="controls">
						${certVo.certapplyInfo.revokeDesc}
				</div>
			</div>
		</c:if>
		<c:if test="${certVo.certapplyInfo.optType=='3'}">
			<div class="control-group">
				<label class="control-label">冻结描述:</label>
				<div class="controls">
						${certVo.certapplyInfo.revokeDesc}
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">操作人:</label>
			<div class="controls">
					${certVo.certapplyInfo.applicant}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作时间:</label>
			<div class="controls">
					${fn:substring(certVo.certapplyInfo.optTime,0,4)}-${fn:substring(certVo.certapplyInfo.optTime,4,6)}-${fn:substring(certVo.certapplyInfo.optTime,6,8)}
					${fn:substring(certVo.certapplyInfo.optTime,8,10)}:${fn:substring(certVo.certapplyInfo.optTime,10,12)}:${fn:substring(certVo.certapplyInfo.optTime,12,14)}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核:</label>
			<div class="controls">
				<input type="radio" name="auditStatus" value="0" checked="checked"/>通过
				<input type="radio" name="auditStatus" value="1" />拒绝
			</div>
		</div>
		<div class="control-group" id="reason">
			<label class="control-label">拒绝原因:</label>
			<div class="controls">
				<form:textarea path="certapplyInfo.refuseReason" rows="3"></form:textarea>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="审 核"/>&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>