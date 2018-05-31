package com.sxca.myb.modules.certCtml.entity;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * 证书标准扩展域
 * @author lihuilong 
 * @date : 2017年5月5日 下午2:14:47
 */
public class CertStandardExt extends DataEntity<CertStandardExt> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String reqSn;//证书申请唯一标示
	
	private String certId;//证书id
	
	private String extName;//标准扩展域名称
	
	private String childName;//标准扩展域子项目名称
	
	private String othernameOid;//其他名称oid
	
	private String extValue;//扩展域值
	
	private String certSn;//证书序列号

	public String getReqSn() {
		return reqSn;
	}

	public void setReqSn(String reqSn) {
		this.reqSn = reqSn;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getOthernameOid() {
		return othernameOid;
	}

	public void setOthernameOid(String othernameOid) {
		this.othernameOid = othernameOid;
	}

	public String getExtValue() {
		return extValue;
	}

	public void setExtValue(String extValue) {
		this.extValue = extValue;
	}

	public String getCertSn() {
		return certSn;
	}

	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}
	
	

}
