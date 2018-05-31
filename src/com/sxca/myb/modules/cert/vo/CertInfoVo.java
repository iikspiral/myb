package com.sxca.myb.modules.cert.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.pro.entity.ProjectInfo;


/**  
* <p>Title: CertInfoVo</p>  
* <p>Description: </p>  
* <p>Company: 吉大正元</p>   
* @author wyf 
* @date 下午2:59:01  
*/ 
public class CertInfoVo {
	
	private String certSn;// Ra中证书id

	private String subjectUppercase;// 证书主题大写

	private String certSubject;// 证书主题

	private String commonName;// CN

	private String ctmlName;// 证书模板

	private String authCode;// 授权码

	private String isAdmin;// 是否是管理员证书，1-不是、2-是

	private String certStatus;// 证书状态，Use-使用中、Undown-未下载、Revoke-注销、Hold-冻结、UndownRevoke未下载注销

	private Integer certValidity;// 有效期

	private Date notBefore;// 生效时间

	private Date notAfter;// 失效时间

	private String userinfoId;// 用户id

	private String certType;// 证书类型 个人用户 userinfo  企业用户 corporation_info

	private String applicantUppercase;// 审核人大写

	private String applicant;// 审核人

	private BigDecimal optTime;// 审核时间

	private String organId;// 组织机构id

	private String isRetainKey;// 是否保留原有密钥

	private String oldCertSn;// 原证书id
	
	private String doubleCertSn;// 加密证书序列号

	private String oldDoubleCertSn;// 原加密证书序列号

	private String isWaiting;//

	private String signServer;//

	private List<String> certList;
	
	private CertapplyInfo certapplyInfo;
	
//	------------查询字段------------------
	private Date beforeMin;
	private BigDecimal beforeMin1;
	private Date beforeMax;
	private BigDecimal beforeMax1;
//	------------查询字段------------------
	private Date afterMin;
	private Date afterMax;
	private BigDecimal afterMin1;
	private BigDecimal afterMax1;
//	------------------------------
	private ProjectInfo projectInfo;
//	------------------------------
	private String projectName;
	private String certTemplate;
//	------------------------------

	public String getCertSn() {
		return certSn;
	}

	public void setCertSn(String certSn) {
		this.certSn = certSn;
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

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(String certStatus) {
		this.certStatus = certStatus;
	}

	public Integer getCertValidity() {
		return certValidity;
	}

	public void setCertValidity(Integer certValidity) {
		this.certValidity = certValidity;
	}


	public Date getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(Date notBefore) {
		this.notBefore = notBefore;
	}

	public Date getNotAfter() {
		return notAfter;
	}

	public void setNotAfter(Date notAfter) {
		this.notAfter = notAfter;
	}

	public String getUserinfoId() {
		return userinfoId;
	}

	public void setUserinfoId(String userinfoId) {
		this.userinfoId = userinfoId;
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

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getIsRetainKey() {
		return isRetainKey;
	}

	public void setIsRetainKey(String isRetainKey) {
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

	public String getIsWaiting() {
		return isWaiting;
	}

	public void setIsWaiting(String isWaiting) {
		this.isWaiting = isWaiting;
	}

	public String getSignServer() {
		return signServer;
	}

	public void setSignServer(String signServer) {
		this.signServer = signServer;
	}

	public List<String> getCertList() {
		return certList;
	}

	public void setCertList(List<String> certList) {
		this.certList = certList;
	}

	public CertapplyInfo getCertapplyInfo() {
		return certapplyInfo;
	}

	public void setCertapplyInfo(CertapplyInfo certapplyInfo) {
		this.certapplyInfo = certapplyInfo;
	}

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

	public Date getAfterMin() {
		return afterMin;
	}

	public void setAfterMin(Date afterMin) {
		this.afterMin = afterMin;
	}

	public Date getAfterMax() {
		return afterMax;
	}

	public void setAfterMax(Date afterMax) {
		this.afterMax = afterMax;
	}

	public BigDecimal getAfterMin1() {
		return afterMin1;
	}

	public void setAfterMin1(BigDecimal afterMin1) {
		this.afterMin1 = afterMin1;
	}

	public BigDecimal getAfterMax1() {
		return afterMax1;
	}

	public void setAfterMax1(BigDecimal afterMax1) {
		this.afterMax1 = afterMax1;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCertTemplate() {
		return certTemplate;
	}

	public void setCertTemplate(String certTemplate) {
		this.certTemplate = certTemplate;
	}
	
	
	
}
