package com.sxca.myb.modules.cert.entity;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * 证书申请业务信息表
 * 
 * @author ggw
 * @date : 2017年5月25日 下午2:46:36
 */
public class CertapplyBusinessInfo extends DataEntity<CertapplyBusinessInfo>{

	private static final long serialVersionUID = 1L;
	
	private String deviceType;         //设备类型
	private String deviceNum;          //设备号 
	private String applyType;          //申请类型
	private String phoneNum;           //手机号
	private String certSubject;        //证书主题
	private String certSn;             //证书序列号
	private String userType;           //用户类型
	private String userId;             //用户ID
	private String corpUserId;         //企业用户ID
	private String projectId;          //项目ID
	private String applyResult;        //申请结果
//	-----------------------------------------
	private String projectName;
	private String userName;
	
//	-----------------------------------------
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCorpUserId() {
		return corpUserId;
	}
	public void setCorpUserId(String corpUserId) {
		this.corpUserId = corpUserId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getApplyResult() {
		return applyResult;
	}
	public void setApplyResult(String applyResult) {
		this.applyResult = applyResult;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
//	-------------------------------------------------
	         
	
}
