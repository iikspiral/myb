package com.sxca.myb.modules.config.corporcode.entity;

import com.sxca.myb.common.persistence.DataEntity;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;

/**
 * @author lihuilong
 * @date : 2017年4月14日 上午9:16:26
 */
public class CorporationRequestCode extends DataEntity<CorporationRequestCode> {

	private static final long serialVersionUID = 1L;

	private String corporationId;// 企业信息id

	private String projectId;// 项目信息id

	private String status;// 是否生效( 0 是 1 否)

	private String type;// 业务类型(新申请  ，变更 )

	private String applicant;// 申请人
	
	private Integer optTime;//操作时间
	
	private String certSn;//证书序列号
	
	private String corporUserId;//企业用户Id
	
	private String  corporUserRelaId;//企业与企业用户中间表id
	
	private String isFace;//是否人脸
	
	private String searchCertSubject;//变更前证书主题

	private String certSubject;//新制证书或变更后的证书主题

	private CorporationUserInfo corporationUserInfo;
	
	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Integer getOptTime() {
		return optTime;
	}

	public void setOptTime(Integer optTime) {
		this.optTime = optTime;
	}

	public String getCertSn() {
		return certSn;
	}

	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}

	public String getCorporUserId() {
		return corporUserId;
	}

	public void setCorporUserId(String corporUserId) {
		this.corporUserId = corporUserId;
	}

	public String getCorporUserRelaId() {
		return corporUserRelaId;
	}

	public void setCorporUserRelaId(String corporUserRelaId) {
		this.corporUserRelaId = corporUserRelaId;
	}

	public String getIsFace() {
		return isFace;
	}

	public void setIsFace(String isFace) {
		this.isFace = isFace;
	}

	public CorporationUserInfo getCorporationUserInfo() {
		return corporationUserInfo;
	}

	public void setCorporationUserInfo(CorporationUserInfo corporationUserInfo) {
		this.corporationUserInfo = corporationUserInfo;
	}

	public String getCertSubject() {
		return certSubject;
	}

	public void setCertSubject(String certSubject) {
		this.certSubject = certSubject;
	}

	public String getSearchCertSubject() {
		return searchCertSubject;
	}

	public void setSearchCertSubject(String searchCertSubject) {
		this.searchCertSubject = searchCertSubject;
	}
}
