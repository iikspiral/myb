/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.certCtml.entity;

import com.sxca.myb.common.persistence.DataEntity;


/**
 * 证书模板
 * @author glq
 * @version 20170410
 */
public class CertCtml extends DataEntity<CertCtml> {

	private static final long serialVersionUID = 1L;
	private String id;
	private String certCtmlName;//模版名称
	private String certCtmlType;//模版单双
	private String certCtmlStatus;//模版状态(使用中，未使用)
	private String certCtmlBasedn;//模版BaseDN
	private String certCtmlAudit;//模版审核状态(N:手动,Y:自动)
	private String secretType;
	private String ctmlKeyGenPlace;//密钥位置

	public CertCtml(){
		super();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getCertCtmlName() {
		return certCtmlName;
	}

	public void setCertCtmlName(String certCtmlName) {
		this.certCtmlName = certCtmlName;
	}

	public String getCertCtmlType() {
		return certCtmlType;
	}

	public void setCertCtmlType(String certCtmlType) {
		this.certCtmlType = certCtmlType;
	}

	public String getCertCtmlStatus() {
		return certCtmlStatus;
	}

	public void setCertCtmlStatus(String certCtmlStatus) {
		this.certCtmlStatus = certCtmlStatus;
	}

	public String getCertCtmlBasedn() {
		return certCtmlBasedn;
	}

	public void setCertCtmlBasedn(String certCtmlBasedn) {
		this.certCtmlBasedn = certCtmlBasedn;
	}

	public String getCertCtmlAudit() {
		return certCtmlAudit;
	}

	public void setCertCtmlAudit(String certCtmlAudit) {
		this.certCtmlAudit = certCtmlAudit;
	}

	public String getSecretType() {
		return secretType;
	}

	public void setSecretType(String secretType) {
		this.secretType = secretType;
	}

	public String getCtmlKeyGenPlace() {
		return ctmlKeyGenPlace;
	}

	public void setCtmlKeyGenPlace(String ctmlKeyGenPlace) {
		this.ctmlKeyGenPlace = ctmlKeyGenPlace;
	}
	
	
}