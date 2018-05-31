package com.sxca.myb.modules.config.whitelist.vo;

import java.io.Serializable;
public class WhiteListVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String projectName;  //项目名
	private String userInfoName;//用户名
	private String userPhoneNum;//电话号码
	private String userInfoId;//用户id
	private String certSn;//证书序列号
	private String isUsed;//是否生效
	private String optType;//业务类型
	private String searchCertSubject;//查询用证书主题？
//	-------------------------------------------------
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUserInfoName() {
		return userInfoName;
	}
	public void setUserInfoName(String userInfoName) {
		this.userInfoName = userInfoName;
	}
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}
	public String getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}
	public String getCertSn() {
		return certSn;
	}
	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	public String getOptType() {
		return optType;
	}
	public void setOptType(String optType) {
		this.optType = optType;
	}
	public String getSearchCertSubject() {
		return searchCertSubject;
	}
	public void setSearchCertSubject(String searchCertSubject) {
		this.searchCertSubject = searchCertSubject;
	}
	

	

}
