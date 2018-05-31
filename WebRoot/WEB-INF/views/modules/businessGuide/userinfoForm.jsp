<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#isHasUserSelect").change(function(){
			var val = $('input[name=isHasUser11]:checked').val();
			var userId ="";
			if(val==0){ //已有个人
				$("#isHasUser").removeClass("display");
				$("#addOrEditUser").addClass("display");
			}else if(val==1){ //新增个人
				$("#isHasUser").addClass("display");
				$("#addOrEditUser").removeClass("display");
			}
		});
		$("#isHasUser").change(function(){
			var val = $('#userSelect option:selected').val();
			reload(val);
		});
	});
</script>
<div class="control-group">
	<label class="control-label">是否已有个人:</label>
	<div class="controls" id="isHasUserSelect">
		<c:forEach items="${fns:getDictList('is_required')}" var="var">
			<c:if test="${var.value==0}">
				<input name="isHasUser11" type="radio" value="${var.value}" checked/>${var.label}
			</c:if>
			<c:if test="${var.value!=0}">
				<input name="isHasUser11" type="radio" value="${var.value}"/>${var.label}
			</c:if>
		</c:forEach>
	</div>
</div>
<div id="isHasUser" >
	<div class="control-group">
		<label class="control-label">选择个人:</label>
		<div class="controls">
			<select name="userSelect" style="width:282px" id="userSelect">
				<option value="">请选择</option>
				<c:forEach items="${userInfos}" var="user">
					<option value="${user.id}">${user.username}</option>
				</c:forEach>
			</select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
</div>
<div id="addOrEditUser" class="display">
		<input type="hidden" name="userinfoid" id="userinfoid" />
		<input type="hidden" name="raUserId" id="raUserId" />
		<input type="hidden" name="userState" id="userState" />
		<input type="hidden" name="userType" id="userType" />
		<input type="hidden" name="idtype" id="idtype" value="01"/>
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
		<div class="control-group">
			<div class="control-group">
				<label class="control-label">用户名:</label>
				<div class="controls">
					<input id="username" name="username" htmlEscape="false" rows="3" style="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">手机号:</label>
				<div class="controls">
					<input id="phonenum" name="phonenum" htmlEscape="false" rows="3" style="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">联系地址:</label>
				<div class="controls">
					<input id="usercontactaddr" name="usercontactaddr" htmlEscape="false" rows="3" style="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>			
			<div class="control-group">
				<label class="control-label">身份证号码:</label>
				<div class="controls">
					<input id="useridcardnum" name="useridcardnum" htmlEscape="false" rows="3" style="width:270px"
						minlength="0" maxlength="600" class="required input-xlarge"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>			
			<div class="control-group">
				<label class="control-label">单位名称:</label>
				<div class="controls">
					<input id="org" name="org" htmlEscape="false" rows="3" style="width:270px"
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
							<input id="extra1" name="extra1" htmlEscape="false" rows="3" style="width:270px" 
								minlength="0" maxlength="${extraInfo1.filedLength}" 
								class="required input-xlarge"
								/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo1.isRequired=='1' }">
							<input id="extra1" name="extra1" htmlEscape="false" rows="3" style="width:270px" 
								minlength="0" maxlength="${extraInfo1.filedLength}" 
								/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo1.dataType=='1' }">
						<c:if test="${extraInfo1.isRequired=='0' }">
							<select id="extra1" name="extra1" style="width:283px" class="required">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo1.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo1.isRequired=='1' }">
							<select id="extra1" name="extra1" style="width:283px">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo1.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
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
							<input id="extra2" name="extra2" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo2.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo2.isRequired=='1' }">
							<input id="extra2" name="extra2" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo2.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo2.dataType=='1' }">
						<c:if test="${extraInfo2.isRequired=='0' }">
							<select id="extra2" name="extra2" style="width:283px" class="required">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo2.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo2.isRequired=='1' }">
							<select id="extra2" name="extra2" style="width:283px">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo2.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
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
							<input id="extra3" name="extra3" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo3.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo3.isRequired=='1' }">
							<input id="extra3" name="extra3" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo3.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo3.dataType=='1' }">
						<c:if test="${extraInfo3.isRequired=='0' }">
							<select id="extra3" name="extra3" style="width:283px" class="required">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo3.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo3.isRequired=='1' }">
							<select id="extra3" name="extra3" style="width:283px">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo3.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
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
							<input id="extra4" name="extra4" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo4.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo4.isRequired=='1' }">
							<input id="extra4" name="extra4" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo4.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo4.dataType=='1' }">
						<c:if test="${extraInfo4.isRequired=='0' }">
							<select id="extra4" name="extra4" style="width:283px" class="required">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo4.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo4.isRequired=='1' }">
							<select id="extra4" name="extra4" style="width:283px">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo4.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
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
							<input id="extra5" name="extra5" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo5.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo5.isRequired=='1' }">
							<input id="extra5" name="extra5" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo5.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo5.dataType=='1' }">
						<c:if test="${extraInfo5.isRequired=='0' }">
							<select id="extra5" name="extra5" style="width:283px" class="required">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo5.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo5.isRequired=='1' }">
							<select id="extra5" name="extra5" style="width:283px">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo5.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
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
							<input id="extra6" name="extra6" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo6.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo6.isRequired=='1' }">
							<input id="extra6" name="extra6" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo6.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo6.dataType=='1' }">
						<c:if test="${extraInfo6.isRequired=='0' }">
							<select id="extra6" name="extra6" style="width:283px" class="required">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo6.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo6.isRequired=='1' }">
							<select id="extra6" name="extra6" style="width:283px">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo6.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
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
							<input id="extra7" name="extra7" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo7.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo7.isRequired=='1' }">
							<input id="extra7" name="extra7" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo7.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo7.dataType=='1' }">
						<c:if test="${extraInfo7.isRequired=='0' }">
							<select id="extra7" name="extra7" style="width:283px" class="required">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo7.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo7.isRequired=='1' }">
							<select id="extra7" name="extra7" style="width:283px">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo7.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
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
							<input id="extra8" name="extra8" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo8.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo8.isRequired=='1' }">
							<input id="extra8" name="extra8" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo8.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo8.dataType=='1' }">
						<c:if test="${extraInfo8.isRequired=='0' }">
							<select id="extra8" name="extra8" style="width:283px" class="required">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo8.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo8.isRequired=='1' }">
							<select id="extra8" name="extra8" style="width:283px">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo8.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
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
							<input id="extra9" name="extra9" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo9.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo9.isRequired=='1' }">
							<input id="extra9" name="extra9" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo9.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo9.dataType=='1' }">
						<c:if test="${extraInfo9.isRequired=='0' }">
							<select id="extra9" name="extra9" style="width:283px" class="required">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo9.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo9.isRequired=='1' }">
							<select id="extra9" name="extra9" style="width:283px">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo9.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
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
							<input id="extra10" name="extra10" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo10.filedLength}" class="required input-xlarge"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo10.isRequired=='1' }">
							<input id="extra10" name="extra10" htmlEscape="false" rows="3" style="width:270px"
								minlength="0" maxlength="${extraInfo10.filedLength}"/>
						</c:if>
					</c:if>
					<c:if test="${extraInfo10.dataType=='1' }">
						<c:if test="${extraInfo10.isRequired=='0' }">
							<select id="extra10" name="extra10" style="width:283px" class="required">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo10.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
							<span class="help-inline"><font color="red">*</font> </span>
						</c:if>
						<c:if test="${extraInfo10.isRequired=='1' }">
							<select id="extra10" name="extra10" style="width:283px">
								<option value="">请选择</option>
								<c:forEach items="${fns:getDictList(extraInfo10.dicValue) }" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
						</c:if>
					</c:if>			
				</div>
			</div>				
			</c:if>				
			<div class="control-group">
				<label class="control-label">备注:</label>
				<div class="controls">
					<textarea id="remarks" htmlEscape="true" name="remarks" rows="4" maxlength="4000" class="input-xxlarge"></textarea>
				</div>
			</div>
		</div>
</div>