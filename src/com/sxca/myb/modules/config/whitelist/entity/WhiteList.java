package com.sxca.myb.modules.config.whitelist.entity;

import com.sxca.myb.common.persistence.DataEntity;
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.pro.entity.ProjectInfo;

/**  
* <p>Title: WhiteList</p>  
* <p>Description: 个人白名单实体 </p>  
* <p>Company: 吉大正元</p>   
* @author wyf 
* @date 2017年6月7日下午4:42:14  
* @version V1.0 
*/ 
public class WhiteList extends DataEntity<WhiteList> {

	private static final long serialVersionUID = 1L;
	
	private String userInfoId;  //个人用户id
	private String projectInfoId;//项目id
	private String isUsed;//是否生效
	private String isuseFace;//是否启用人脸识别
	private String optType;//业务类型
	private String certSn;//业务类型
	private UserInfo userInfo; // 个人用户信息实体
	private ProjectInfo projectInfo; // 项目实体
	private CertInfo certInfo; // 申请信息信息实体
	private String projectName;
	private String userInfoName;
	
//	--------------------------------------------------
	private String searchCertSubject;//变更前证书主题
	private String certSubject;//新制证书或变更后的证书主题

	private String userPhoneNum;
	//	-------------------------------------------------

	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
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
	public String getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}
	public String getProjectInfoId() {
		return projectInfoId;
	}
	public void setProjectInfoId(String projectInfoId) {
		this.projectInfoId = projectInfoId;
	}
	
	
	public String getIsuseFace() {
		return isuseFace;
	}
	public void setIsuseFace(String isuseFace) {
		this.isuseFace = isuseFace;
	}
	public String getOptType() {
		return optType;
	}
	public void setOptType(String optType) {
		this.optType = optType;
	}
	public String getCertSn() {
		return certSn;
	}
	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	
	public CertInfo getCertInfo() {
		return certInfo;
	}
	public void setCertInfo(CertInfo certInfo) {
		this.certInfo = certInfo;
	}
	public WhiteList() {
		userInfo = new UserInfo();
	}
	public String getCertSubject() {
		return certSubject;
	}
	public void setCertSubject(String certSubject) {
		this.certSubject = certSubject;
	}
//	---------------------------------------------
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public String getSearchCertSubject() {
		return searchCertSubject;
	}

	public void setSearchCertSubject(String searchCertSubject) {
		this.searchCertSubject = searchCertSubject;
	}
}
