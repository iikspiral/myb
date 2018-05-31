<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>对象表单</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
function isIdCardNo(num){
	var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	var re = new RegExp(isIDCard1);
	if (re.test(num)) {
        return true;
    }else{
       return false;
    }
}

$(document).ready(function() {
	
	// 扩展属性校验
    jQuery.validator.addMethod("extraReq", function(value, element) { 
      var type = element.name+"zz";
      var zz = $("#"+type).val();
      if(zz != null && zz != ""){
	      var re = new RegExp(zz);
	      return this.optional(element) || (re.test(value));      
      }else{
    	  return true;
      }
    }, "格式错误,请重新输入");   
	
    jQuery.validator.addMethod("isIdCardNo", function (value, element){
        return this.optional(element) || isIdCardNo(value);
     },"请正确输入您的身份证号码");
    
    
   
	$("#inputForm")
	.validate(
	{
			rules: {
				useridcardnum:{
					remote: "${ctx}/userinfo/checkUnique?id="+$("#id").val(),
					isIdCardNo : true
				},
				extra1 : {
					extraReq :true
				},
				extra2 : {
					extraReq :true					
				},
				extra3 : {
					extraReq :true			
				},
				extra4 : {
					extraReq :true	
				},
				extra5 : {
					extraReq :true
				},
				extra6 : {
					extraReq :true
				},
				extra7 : {
					extraReq :true
				},
				extra8 : {
					extraReq :true
				},
				extra9 : {
					extraReq :true
				},
				extra10 : {
					extraReq :true
				}
			},
			messages: {
				useridcardnum:{
					remote: "证件号码已存在"
				},
				extra1 : {
				},
				extra2 : {
				},
				extra3 : {
				},
				extra4 : {
				},
				extra5 : {
				},
				extra6 : {
				},
				extra7 : {
				},
				extra8 : {
				},
				extra9 : {
				},
				extra10 : {
				}
			},
			submitHandler : function(form) {
				    $.ajax({ 
						url : "${ctx}/userinfo/updateCorpname?idCard="+$("#useridcardnum").val()+"&name="+encodeURI(encodeURI($("#username").val()))+"&id="+$("#id").val(), 
						type : "post", 
						success : function(result) { 
							if(result){
								loading('正在提交，请稍等...');
								form.submit();
							}else{
								alert("【密钥宝服务平台】提示：\r\n姓名不同,请确保与企业用户中身份证号相同的用户姓名一致.\r\n谢谢！");
							}
						} 
				     }); 
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")
						|| element.parent().is(
								".input-append")) {
					error.appendTo(element.parent()
							.parent());
				} else {
					error.insertAfter(element);
				}
			}

		});
	
}); 
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/userinfo/list?clickType=2">个人用户列表</a></li>
		<li class="active"><a href="${ctx}/userinfo/form?id=${userInfo.id}">个人用户${not empty userInfo.id?'编辑':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="userInfo"
		action="${ctx}/userinfo/save" method="post" class="form-horizontal">
		<form:hidden path="id" id="id" />
		<form:hidden path="raUserId" id="raUserId" />
		<form:hidden path="userState" id="userState" />
		<form:hidden path="userType" id="userType" />
		<form:hidden path="idtype" id="idtype" />
		<input type="hidden" name="extra1zz" id ="extra1zz" value = "${extraInfo1.checkValue}"/>
		<input type="hidden" name="extra2zz" id ="extra2zz" value = "${extraInfo2.checkValue}"/>
		<input type="hidden" name="extra3zz" id ="extra3zz" value = "${extraInfo3.checkValue}"/>
		<input type="hidden" name="extra4zz" id ="extra4zz" value = "${extraInfo4.checkValue}"/>
		<input type="hidden" name="extra5zz" id ="extra5zz" value = "${extraInfo5.checkValue}"/>
		<input type="hidden" name="extra6zz" id ="extra6zz" value = "${extraInfo6.checkValue}"/>
		<input type="hidden" name="extra7zz" id ="extra7zz" value = "${extraInfo7.checkValue}"/>
		<input type="hidden" name="extra8zz" id ="extra8zz" value = "${extraInfo8.checkValue}"/>
		<input type="hidden" name="extra9zz" id ="extra9zz" value = "${extraInfo9.checkValue}"/>
		<input type="hidden" name="extra10zz" id ="extra10zz" value = "${extraInfo10.checkValue}"/>
		<sys:message content="${message}" />
		<div class="control-group">
			<div class="control-group">
				<label class="control-label">用户名:</label>
				<div class="controls">
					<form:input id="username" path="username" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">手机号:</label>
				<div class="controls">
					<form:input id="phonenum" path="phonenum" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">联系地址:</label>
				<div class="controls">
					<form:input id="usercontactaddr" path="usercontactaddr" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>			
			<div class="control-group">
				<label class="control-label">身份证号码:</label>
				<div class="controls">
					<form:input id="useridcardnum" path="useridcardnum" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>			
			<div class="control-group">
				<label class="control-label">单位名称:</label>
				<div class="controls">
					<form:input id="org" path="org" htmlEscape="false" rows="3" cssStyle="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<c:if test="${not empty extraInfo1 }">
			<div class="control-group">
				<label class="control-label">${extraInfo1.extraMean}:</label>
				<div class="controls">
					<c:if test="${extraInfo1.dataType=='0' }">
						<c:if test="${extraInfo1.isRequired=='0' }">
							<form:input id="extra1" path="extra1" htmlEscape="false" rows="3" cssStyle="width:270px" 
								minlength="0" maxlength="${extraInfo1.filedLength}" 
								class="required input-xlarge"
								/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo1.isRequired=='1' }">
							<form:input id="extra1" path="extra1" htmlEscape="false" rows="3" cssStyle="width:270px" 
								minlength="0" maxlength="${extraInfo1.filedLength}" 
								/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo1.dataType=='1' }">
						<c:if test="${extraInfo1.isRequired=='0' }">
							<form:select id="extra1" path="extra1" cssStyle="width:283px" class="required">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo1.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo1.isRequired=='1' }">
							<form:select id="extra1" path="extra1" cssStyle="width:283px">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo1.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
						</c:if>
					</c:if>			
				</div>	
			</div>		
			</c:if>	
			<c:if test="${not empty extraInfo2 }">
			<div class="control-group">
				<label class="control-label">${extraInfo2.extraMean}:</label>
				<div class="controls">
					<c:if test="${extraInfo2.dataType=='0' }">
						<c:if test="${extraInfo2.isRequired=='0' }">
							<form:input id="extra2" path="extra2" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo2.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo2.isRequired=='1' }">
							<form:input id="extra2" path="extra2" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo2.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo2.dataType=='1' }">
						<c:if test="${extraInfo2.isRequired=='0' }">
							<form:select id="extra2" path="extra2" cssStyle="width:283px" class="required">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo2.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo2.isRequired=='1' }">
							<form:select id="extra2" path="extra2" cssStyle="width:283px">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo2.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
						</c:if>
					</c:if>			
				</div>		
			</div>		
			</c:if>				
			<c:if test="${not empty extraInfo3 }">
			<div class="control-group">
				<label class="control-label">${extraInfo3.extraMean}:</label>
				<div class="controls">
					<c:if test="${extraInfo3.dataType=='0' }">
						<c:if test="${extraInfo3.isRequired=='0' }">
							<form:input id="extra3" path="extra3" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo3.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo3.isRequired=='1' }">
							<form:input id="extra3" path="extra3" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo3.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo3.dataType=='1' }">
						<c:if test="${extraInfo3.isRequired=='0' }">
							<form:select id="extra3" path="extra3" cssStyle="width:283px" class="required">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo3.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo3.isRequired=='1' }">
							<form:select id="extra3" path="extra3" cssStyle="width:283px">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo3.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
						</c:if>
					</c:if>			
				</div>
			</div>				
			</c:if>				
			<c:if test="${not empty extraInfo4 }">
			<div class="control-group">
				<label class="control-label">${extraInfo4.extraMean}:</label>
				<div class="controls">
					<c:if test="${extraInfo4.dataType=='0' }">
						<c:if test="${extraInfo4.isRequired=='0' }">
							<form:input id="extra4" path="extra4" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo4.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo4.isRequired=='1' }">
							<form:input id="extra4" path="extra4" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo4.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo4.dataType=='1' }">
						<c:if test="${extraInfo4.isRequired=='0' }">
							<form:select id="extra4" path="extra4" cssStyle="width:283px" class="required">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo4.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo4.isRequired=='1' }">
							<form:select id="extra4" path="extra4" cssStyle="width:283px">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo4.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
						</c:if>
					</c:if>			
				</div>	
			</div>			
			</c:if>				
			<c:if test="${not empty extraInfo5 }">
			<div class="control-group">
				<label class="control-label">${extraInfo5.extraMean}:</label>
				<div class="controls">
					<c:if test="${extraInfo5.dataType=='0' }">
						<c:if test="${extraInfo5.isRequired=='0' }">
							<form:input id="extra5" path="extra5" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo5.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo5.isRequired=='1' }">
							<form:input id="extra5" path="extra5" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo5.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo5.dataType=='1' }">
						<c:if test="${extraInfo5.isRequired=='0' }">
							<form:select id="extra5" path="extra5" cssStyle="width:283px" class="required">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo5.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo5.isRequired=='1' }">
							<form:select id="extra5" path="extra5" cssStyle="width:283px">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo5.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
						</c:if>
					</c:if>			
				</div>	
			</div>			
			</c:if>			
			<c:if test="${not empty extraInfo6 }">
			<div class="control-group">
				<label class="control-label">${extraInfo6.extraMean}:</label>
				<div class="controls">
					<c:if test="${extraInfo6.dataType=='0' }">
						<c:if test="${extraInfo6.isRequired=='0' }">
							<form:input id="extra6" path="extra6" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo6.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo6.isRequired=='1' }">
							<form:input id="extra6" path="extra6" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo6.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo6.dataType=='1' }">
						<c:if test="${extraInfo6.isRequired=='0' }">
							<form:select id="extra6" path="extra6" cssStyle="width:283px" class="required">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo6.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo6.isRequired=='1' }">
							<form:select id="extra6" path="extra6" cssStyle="width:283px">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo6.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
						</c:if>
					</c:if>			
				</div>
			</div>				
			</c:if>				
			<c:if test="${not empty extraInfo7 }">
			<div class="control-group">
				<label class="control-label">${extraInfo7.extraMean}:</label>
				<div class="controls">
					<c:if test="${extraInfo7.dataType=='0' }">
						<c:if test="${extraInfo7.isRequired=='0' }">
							<form:input id="extra7" path="extra7" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo7.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo7.isRequired=='1' }">
							<form:input id="extra7" path="extra7" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo7.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo7.dataType=='1' }">
						<c:if test="${extraInfo7.isRequired=='0' }">
							<form:select id="extra7" path="extra7" cssStyle="width:283px" class="required">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo7.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo7.isRequired=='1' }">
							<form:select id="extra7" path="extra7" cssStyle="width:283px">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo7.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
						</c:if>
					</c:if>			
				</div>
			</div>				
			</c:if>				
			<c:if test="${not empty extraInfo8 }">
			<div class="control-group">
				<label class="control-label">${extraInfo8.extraMean}:</label>
				<div class="controls">
					<c:if test="${extraInfo8.dataType=='0' }">
						<c:if test="${extraInfo8.isRequired=='0' }">
							<form:input id="extra8" path="extra8" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo8.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo8.isRequired=='1' }">
							<form:input id="extra8" path="extra8" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo8.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo8.dataType=='1' }">
						<c:if test="${extraInfo8.isRequired=='0' }">
							<form:select id="extra8" path="extra8" cssStyle="width:283px" class="required">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo8.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo8.isRequired=='1' }">
							<form:select id="extra8" path="extra8" cssStyle="width:283px">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo8.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
						</c:if>
					</c:if>			
				</div>	
			</div>			
			</c:if>			
			<c:if test="${not empty extraInfo9 }">
			<div class="control-group">
				<label class="control-label">${extraInfo9.extraMean}:</label>
				<div class="controls">
					<c:if test="${extraInfo9.dataType=='0' }">
						<c:if test="${extraInfo9.isRequired=='0' }">
							<form:input id="extra9" path="extra9" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo9.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo9.isRequired=='1' }">
							<form:input id="extra9" path="extra9" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo9.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo9.dataType=='1' }">
						<c:if test="${extraInfo9.isRequired=='0' }">
							<form:select id="extra9" path="extra9" cssStyle="width:283px" class="required">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo9.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo9.isRequired=='1' }">
							<form:select id="extra9" path="extra9" cssStyle="width:283px">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo9.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
						</c:if>
					</c:if>			
				</div>	
			</div>			
			</c:if>				
			<c:if test="${not empty extraInfo10 }">
			<div class="control-group">
				<label class="control-label">${extraInfo10.extraMean}:</label>
				<div class="controls">
					<c:if test="${extraInfo10.dataType=='0' }">
						<c:if test="${extraInfo10.isRequired=='0' }">
							<form:input id="extra10" path="extra10" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo10.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo10.isRequired=='1' }">
							<form:input id="extra10" path="extra10" htmlEscape="false" rows="3" cssStyle="width:270px"
								minlength="0" maxlength="${extraInfo10.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo10.dataType=='1' }">
						<c:if test="${extraInfo10.isRequired=='0' }">
							<form:select id="extra10" path="extra10" cssStyle="width:283px" class="required">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo10.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo10.isRequired=='1' }">
							<form:select id="extra10" path="extra10" cssStyle="width:283px">
								<form:option value="" label="请选择" />
								<form:options items="${fns:getDictList(extraInfo10.dicValue) }" itemLabel="label" itemValue="value"/>
							</form:select>
						</c:if>
					</c:if>			
				</div>
			</div>				
			</c:if>				
			<div class="control-group">
				<label class="control-label">备注:</label>
				<div class="controls">
					<form:textarea id="remarks" htmlEscape="true" path="remarks" rows="4" maxlength="4000" class="input-xxlarge"/>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<input type="submit" value="保存" class="btn btn-primary">&nbsp;&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button"	value="返 回" onclick="window.location.href='${ctx}/userinfo/list'" />
		</div>
	</form:form>
</body>
</html>
