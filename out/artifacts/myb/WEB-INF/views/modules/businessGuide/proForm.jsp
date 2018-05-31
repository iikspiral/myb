<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#isHasProSelect").change(function(){
				var val = $('input[name=isHasPro]:checked').val();
				if(val==0){ //已有项目
					$("#isHasPro").removeClass("display");
					$("#addOrEditPro").addClass("display");
					deletePro();
				}else if(val==1){ //新增项目
					$("#isHasPro").addClass("display");
					$("#addOrEditPro").removeClass("display");
				}
			});
			$("#projectSelect").change(function(){
				var pro =$('#projectSelect option:selected');
				var proId =pro.val();
				var proName =pro.text();
				$("#projectInfoId").val(proId);
				$("#projectId").val(proId);
				$(".pro-Name").val(proName);
				getWhitelistByProjectId();
			});
		});
	</script>

	<div class="control-group">
		<label class="control-label">是否已有项目:</label>
		<div class="controls" id="isHasProSelect">
			<c:forEach items="${fns:getDictList('is_required')}" var="var">
				<c:if test="${var.value==0}">
					<input name="isHasPro" type="radio" value="${var.value}" class="required" checked="checked"/>${var.label}
				</c:if>
				<c:if test="${var.value!=0}">
					<input name="isHasPro" type="radio" value="${var.value}" class="required"/>${var.label}
				</c:if>
			</c:forEach>
		</div>
	</div>
	<div id="isHasPro" >
		<div class="control-group">
			<label class="control-label">项目:</label>
			<div class="controls">
				<select name="projectSelect" style="width:282px" class="required" id="projectSelect">
					<option value="">请选择</option>
					<c:forEach items="${fns:getProjectInfoList()}" var="pro">
						<option value="${pro.id}">${pro.projectName}</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	</div>


<div id="addOrEditPro" class="display control-group">
	<input type="hidden" name="newProId" id="newProId">
	<div class="control-group">
		<div class="control-group">
			<label class="control-label">项目编号:</label>
			<div class="controls">
				<input name="projectNum" htmlEscape="false" rows="3" cssStyle="width:270px"
					   minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称:</label>
			<div class="controls">
				<input name="projectName" htmlEscape="false" rows="3" cssStyle="width:270px"
					   minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书模板:</label>
			<div class="controls">
				<select name="certTemplate" style="width:275px" class="required">
					<option value="">请选择</option>
					<c:forEach items="${fns:getCertctmlList()}" var="certCtml">
						<option value="${certCtml.id}">${certCtml.certCtmlName}</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">制证规则:</label>
			<div class="controls">
				<textarea name="makeCertRules" htmlEscape="false" rows="2" class="required input-xxlarge"></textarea>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">自定义扩展域规则:</label>
			<div class="controls">
				<textarea name="selfExtRules" htmlEscape="false" rows="2" class="required input-xxlarge"></textarea>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准扩展域规则:</label>
			<div class="controls">
				<textarea name="standardExtRules" htmlEscape="false" rows="2" class="required input-xxlarge"></textarea>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书有效期(天):</label>
			<div class="controls">
				<input name="certValidity" htmlEscape="false" rows="3" cssStyle="width:270px"
					   minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否默认项目:</label>
			<div class="controls">
				<c:forEach items="${fns:getDictList('is_required')}" var="var">
					<c:if test="${var.value==0}">
						<input name="isDefaultProject" type="radio" value="${var.value}" class="required" checked/>${var.label}
					</c:if>
					<c:if test="${var.value!=0}">
						<input name="isDefaultProject" type="radio" value="${var.value}" class="required"/>${var.label}
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书默认跟新时长(天):</label>
			<div class="controls">
				<input name="allowUpdateDay" htmlEscape="false" rows="3" cssStyle="width:270px"
					   minlength="0" maxlength="600" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	</div>
</div>
