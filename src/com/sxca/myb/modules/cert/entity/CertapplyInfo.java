package com.sxca.myb.modules.cert.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.sxca.myb.common.persistence.DataEntity;
import com.sxca.myb.modules.pro.entity.ProjectInfo;

/**
 * 证书申请信息表
 * 
 * @author lihuilong
 * @date : 2017年4月17日 下午5:38:36
 */
public class CertapplyInfo extends DataEntity<CertapplyInfo> {

	private static final long serialVersionUID = 1L;

	private String certId;// 证书id

	private String certSn;// ra证书id

	private String projectId;// 项目id

	private String subjectUppercase;// 证书主题大写

	private String certSubject;// 证书主题

	private String commonName;// CN值

	private String ctmlName;// 证书模板

	private String optType;// 申请类型，1-新制申请、2-注销申请、3-冻结申请、4-解冻申请、5-更新申请、6-授权码更新、7-证书自主更新、11-证书自主申请

	private String isAdmin;// 是否是管理员证书，1-不是、2-是

	private String reqStatus;// 申请状态，1-未审核、2-已审核、3-审核未通过、4-CA拒绝申请、5-已审核未执行

	private Integer certValidity;// 有效期

	private BigDecimal notBefore;// 生效时间

	private BigDecimal notAfter;// 失效时间

	private String userInfoId;// 用户id

	private String certType;// corporation_info 企业用户类型 userinfo 个人用户类型

	private String applicantUppercase;// 申请人大写

	private String applicant;// 申请人

	private BigDecimal optTime;// 申请时间

	private String refuseReason;// 拒绝原因

	private String exclusiveFlag;//

	private String organId;// 组织机构id

	private Boolean isRetainKey;// 是否保留原有密钥

	private String oldCertSn;// 原证书id

	private String doubleCertSn;// 加密证书序列号

	private String oldDoubleCertSn;// 原加密证书序列号

	private String signServer;//

	private String revokeReason;// 注销类型 修改字段

	private String revokeName;// 注销类型名称

	private String corpUserId;// 企业用户id
	
	private String reqSn;//证书申请唯一标识
	
	private String deviceType;//设备类型
	
	private String deviceNum;//设备号
	
	private String  revokeDesc; //新增字段
	
	private String  applyBusinessId; //证书业务ID
//	-------------查询字段------------------
	private Date beforeMin;
	private BigDecimal beforeMin1;
	private Date beforeMax;
	private BigDecimal beforeMax1;
//	-------------------------------
	private String projectName;
	private ProjectInfo projectInfo;
	private String certCtmlName;
	/*private String  projectInfo;*/
//	-------------------------------
	private String userName;
//	-------------------------------

	public CertapplyInfo() {

	}

	public CertapplyInfo(String certSn, String certSubject, BigDecimal notBefore, BigDecimal notAfter, String ctmlName, Integer certValidity) {
		this.certSn = certSn;
		this.certSubject = certSubject;
		this.notBefore = notBefore;
		this.notAfter = notAfter;
		this.ctmlName = ctmlName;
		this.certValidity = certValidity;
	}


	
	
	public CertapplyInfo(String id, String certId, String certSn,
			String projectId, String subjectUppercase, String certSubject,
			String commonName, String ctmlName, String optType, String isAdmin,
			String reqStatus, Integer certValidity, BigDecimal notBefore,
			BigDecimal notAfter, String userInfoId, String certType,
			String applicantUppercase, String applicant, BigDecimal optTime,
			String refuseReason, String exclusiveFlag, String organId,
			Boolean isRetainKey, String oldCertSn, String doubleCertSn,
			String oldDoubleCertSn, String signServer, String revokeReason,
			String revokeName, String corpUserId, String reqSn,
			String deviceType, String deviceNum, String revokeDesc) {
		super(id);
		this.certId = certId;
		this.certSn = certSn;
		this.projectId = projectId;
		this.subjectUppercase = subjectUppercase;
		this.certSubject = certSubject;
		this.commonName = commonName;
		this.ctmlName = ctmlName;
		this.optType = optType;
		this.isAdmin = isAdmin;
		this.reqStatus = reqStatus;
		this.certValidity = certValidity;
		this.notBefore = notBefore;
		this.notAfter = notAfter;
		this.userInfoId = userInfoId;
		this.certType = certType;
		this.applicantUppercase = applicantUppercase;
		this.applicant = applicant;
		this.optTime = optTime;
		this.refuseReason = refuseReason;
		this.exclusiveFlag = exclusiveFlag;
		this.organId = organId;
		this.isRetainKey = isRetainKey;
		this.oldCertSn = oldCertSn;
		this.doubleCertSn = doubleCertSn;
		this.oldDoubleCertSn = oldDoubleCertSn;
		this.signServer = signServer;
		this.revokeReason = revokeReason;
		this.revokeName = revokeName;
		this.corpUserId = corpUserId;
		this.reqSn = reqSn;
		this.deviceType = deviceType;
		this.deviceNum = deviceNum;
		this.revokeDesc = revokeDesc;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String getCertSn() {
		return certSn;
	}

	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getSubjectUppercase() {
		return subjectUppercase;
	}

	public void setSubjectUppercase(String subjectUppercase) {
		this.subjectUppercase = subjectUppercase;
	}

	public String getCertSubject() {
		return certSubject;
	}

	public void setCertSubject(String certSubject) {
		this.certSubject = certSubject;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getCtmlName() {
		return ctmlName;
	}

	public void setCtmlName(String ctmlName) {
		this.ctmlName = ctmlName;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public Integer getCertValidity() {
		return certValidity;
	}

	public void setCertValidity(Integer certValidity) {
		this.certValidity = certValidity;
	}

	public BigDecimal getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(BigDecimal notBefore) {
		this.notBefore = notBefore;
	}

	public BigDecimal getNotAfter() {
		return notAfter;
	}

	public void setNotAfter(BigDecimal notAfter) {
		this.notAfter = notAfter;
	}

	public String getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getApplicantUppercase() {
		return applicantUppercase;
	}

	public void setApplicantUppercase(String applicantUppercase) {
		this.applicantUppercase = applicantUppercase;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public BigDecimal getOptTime() {
		return optTime;
	}

	public void setOptTime(BigDecimal optTime) {
		this.optTime = optTime;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getExclusiveFlag() {
		return exclusiveFlag;
	}

	public void setExclusiveFlag(String exclusiveFlag) {
		this.exclusiveFlag = exclusiveFlag;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}


	public Boolean getIsRetainKey() {
		return isRetainKey;
	}

	public void setIsRetainKey(Boolean isRetainKey) {
		this.isRetainKey = isRetainKey;
	}

	public String getOldCertSn() {
		return oldCertSn;
	}

	public void setOldCertSn(String oldCertSn) {
		this.oldCertSn = oldCertSn;
	}

	public String getDoubleCertSn() {
		return doubleCertSn;
	}

	public void setDoubleCertSn(String doubleCertSn) {
		this.doubleCertSn = doubleCertSn;
	}

	public String getOldDoubleCertSn() {
		return oldDoubleCertSn;
	}

	public void setOldDoubleCertSn(String oldDoubleCertSn) {
		this.oldDoubleCertSn = oldDoubleCertSn;
	}

	public String getSignServer() {
		return signServer;
	}

	public void setSignServer(String signServer) {
		this.signServer = signServer;
	}


	public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	public String getRevokeName() {
		return revokeName;
	}

	public void setRevokeName(String revokeName) {
		this.revokeName = revokeName;
	}

	public String getCorpUserId() {
		return corpUserId;
	}

	public void setCorpUserId(String corpUserId) {
		this.corpUserId = corpUserId;
	}

	public String getReqSn() {
		return reqSn;
	}

	public void setReqSn(String reqSn) {
		this.reqSn = reqSn;
	}

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
//	---------------------------

	public Date getBeforeMin() {
		return beforeMin;
	}

	public void setBeforeMin(Date beforeMin) {
		this.beforeMin = beforeMin;
	}

	public BigDecimal getBeforeMin1() {
		return beforeMin1;
	}

	public void setBeforeMin1(BigDecimal beforeMin1) {
		this.beforeMin1 = beforeMin1;
	}

	public Date getBeforeMax() {
		return beforeMax;
	}

	public void setBeforeMax(Date beforeMax) {
		this.beforeMax = beforeMax;
	}

	public BigDecimal getBeforeMax1() {
		return beforeMax1;
	}

	public void setBeforeMax1(BigDecimal beforeMax1) {
		this.beforeMax1 = beforeMax1;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	
	public String getCertCtmlName() {
		return certCtmlName;
	}

	public void setCertCtmlName(String certCtmlName) {
		this.certCtmlName = certCtmlName;
	}

	public String getRevokeDesc() {
		return revokeDesc;
	}

	public void setRevokeDesc(String revokeDesc) {
		this.revokeDesc = revokeDesc;
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
	
	

}
