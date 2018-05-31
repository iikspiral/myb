package com.sxca.myb.modules.mobile.entity;

import com.sxca.myb.common.persistence.DataEntity;


/**
 * 移动端申请信息Entity
 * @author glq
 * @version 2017-05-04
 */
public class MobileApplyInfo extends DataEntity<MobileApplyInfo> {

	private static final long serialVersionUID = 1L;
	private String deviceType;
	private String deviceNum;
	private String applyType;
	private String phoneNum;
	private String certSubject;
	private String certSn;
	private String userId;
	private String projectId;
	private String cancelReason;
	private String applyResult;
	private String userType;
	private String  applyBusinessId; //证书业务ID
	private String errorMessage;//错误描述

//	----------------------
	private String oldCertSn;
	private String projectName;
	private String corpUserId;
//	----------------------
	private String userName;


	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCertSubject() {
		return certSubject;
	}

	public void setCertSubject(String certSubject) {
		this.certSubject = certSubject;
	}

	public String getCertSn() {
		return certSn;
	}

	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getApplyResult() {
		return applyResult;
	}

	public void setApplyResult(String applyResult) {
		this.applyResult = applyResult;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getOldCertSn() {
		return oldCertSn;
	}

	public void setOldCertSn(String oldCertSn) {
		this.oldCertSn = oldCertSn;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCorpUserId() {
		return corpUserId;
	}

	public void setCorpUserId(String corpUserId) {
		this.corpUserId = corpUserId;
	}

	public String getApplyBusinessId() {
		return applyBusinessId;
	}

	public void setApplyBusinessId(String applyBusinessId) {
		this.applyBusinessId = applyBusinessId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	


	
}