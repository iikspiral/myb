/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.certCtml.entity;

import com.sxca.myb.common.persistence.DataEntity;


/**
 * 区域Entity
 * @author ThinkGem
 * @version 20170410
 */
public class CertCtmlSelfExt extends DataEntity<CertCtmlSelfExt> {

	private static final long serialVersionUID = 1L;
	private String Id;
	private String certCtmlId;
	private String selfExtName;
	private String selfExtOid;

	public CertCtmlSelfExt(){
		super();
	}

	@Override
	public String getId() {
		return Id;
	}

	@Override
	public void setId(String id) {
		Id = id;
	}

	public String getCertCtmlId() {
		return certCtmlId;
	}

	public void setCertCtmlId(String certCtmlId) {
		this.certCtmlId = certCtmlId;
	}

	public String getSelfExtName() {
		return selfExtName;
	}

	public void setSelfExtName(String selfExtName) {
		this.selfExtName = selfExtName;
	}

	public String getSelfExtOid() {
		return selfExtOid;
	}

	public void setSelfExtOid(String selfExtOid) {
		this.selfExtOid = selfExtOid;
	}
}