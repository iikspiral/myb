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
public class CertCtmlStandardExt extends DataEntity<CertCtmlStandardExt> {

	private static final long serialVersionUID = 1L;
	private String Id;
	private String certCtmlId;
	private String extName;
	private String extChildName;

	public CertCtmlStandardExt(){
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

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public String getExtChildName() {
		return extChildName;
	}

	public void setExtChildName(String extChildName) {
		this.extChildName = extChildName;
	}
}