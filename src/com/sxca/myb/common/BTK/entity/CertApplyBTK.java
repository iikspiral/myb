package com.sxca.myb.common.BTK.entity;

import java.math.BigDecimal;

/**
 * Created by admin on 2017/3/23. BTK证书申请信息封装类
 */
public class CertApplyBTK extends CertBaseBTK {
	private String certSn; // 证书序列号
	private String reqSn; // 证书申请ID
	private String certSubject; // 证书主题
	private String ctmlName; // 证书模版名称
	private String isAdmin; // 是否管理员证书："1",否；"2"，是
	private String certType; // 证书用户类型
	private BigDecimal notBefore; // 生效时间
	private BigDecimal notAfter; // 失效时间
	private Integer certValidity; // 有效期
	private String userInfoId; // 证书用户ID
	private String organId; // 组织机构ID
	private Boolean isUpdateDN; // 是否变更证书主题
	private Boolean notNow; // 是否延迟申请
	private Integer delayDays; // 延迟天数
	private String holdDesc; // 证书冻结描述
	private String revokeReason; // 证书注销原因
	private String revokeDesc; // 证书注销描述
	private String extendValidApplyType; // 证书延期申请是否自动审核
	private Boolean isRetainKey1;

	public CertApplyBTK() {

	}
	//证书跟新申请对应构造函数
	public CertApplyBTK(String certSn, String certSubject, String ctmlName, BigDecimal notBefore, BigDecimal notAfter, Integer certValidity,boolean isRetainKey1) {
		this.certSn = certSn;
		this.certSubject = certSubject;
		this.ctmlName = ctmlName;
		this.notBefore = notBefore;
		this.notAfter = notAfter;
		this.certValidity = certValidity;
		this.isRetainKey1 = isRetainKey1;
	}

	public CertApplyBTK(String certSn, String reqSn, String certSubject,
						String ctmlName, String isAdmin, String certType,
						BigDecimal notBefore, BigDecimal notAfter,
						Integer certValidity, String userInfoId, String organId,
						Boolean isUpdateDN, Boolean notNow, Integer delayDays,
						String holdDesc, String revokeReason, String revokeDesc,
						String extendValidApplyType) {
		this.certSn = certSn;
		this.reqSn = reqSn;
		this.certSubject = certSubject;
		this.ctmlName = ctmlName;
		this.isAdmin = isAdmin;
		this.certType = certType;
		this.notBefore = notBefore;
		this.notAfter = notAfter;
		this.certValidity = certValidity;
		this.userInfoId = userInfoId;
		this.organId = organId;
		this.isUpdateDN = isUpdateDN;
		this.notNow = notNow;
		this.delayDays = delayDays;
		this.holdDesc = holdDesc;
		this.revokeReason = revokeReason;
		this.revokeDesc = revokeDesc;
		this.extendValidApplyType = extendValidApplyType;
	}


	public String getCertSn() {
		return certSn;
	}

	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}

	public String getReqSn() {
		return reqSn;
	}

	public void setReqSn(String reqSn) {
		this.reqSn = reqSn;
	}

	public String getCtmlName() {
		return ctmlName;
	}

	public void setCtmlName(String ctmlName) {
		this.ctmlName = ctmlName;
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

	public String getCertSubject() {
		return certSubject;
	}

	public void setCertSubject(String certSubject) {
		this.certSubject = certSubject;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public Integer getCertValidity() {
		return certValidity;
	}

	public void setCertValidity(Integer certValidity) {
		this.certValidity = certValidity;
	}

	public String getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public Boolean getUpdateDN() {
		return isUpdateDN;
	}

	public void setUpdateDN(Boolean updateDN) {
		isUpdateDN = updateDN;
	}

	public Boolean getNotNow() {
		return notNow;
	}

	public void setNotNow(Boolean notNow) {
		this.notNow = notNow;
	}

	public Integer getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(Integer delayDays) {
		this.delayDays = delayDays;
	}

	public String getHoldDesc() {
		return holdDesc;
	}

	public void setHoldDesc(String holdDesc) {
		this.holdDesc = holdDesc;
	}

	public String getRevokeReason() {
		return revokeReason;
	}
	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}
	public String getRevokeDesc() {
		return revokeDesc;
	}

	public void setRevokeDesc(String revokeDesc) {
		this.revokeDesc = revokeDesc;
	}

	public String getExtendValidApplyType() {
		return extendValidApplyType;
	}

	public void setExtendValidApplyType(String extendValidApplyType) {
		this.extendValidApplyType = extendValidApplyType;
	}
	public Boolean getIsRetainKey1() {
		return isRetainKey1;
	}
	public void setIsRetainKey1(Boolean isRetainKey1) {
		this.isRetainKey1 = isRetainKey1;
	}


	
	
}
