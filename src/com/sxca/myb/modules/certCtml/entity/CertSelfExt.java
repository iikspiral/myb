package com.sxca.myb.modules.certCtml.entity;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * 证书自定义扩展域
 * 
 * @author lihuilong
 * @date : 2017年5月5日 下午2:13:33
 */
public class CertSelfExt extends DataEntity<CertSelfExt> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String regSn;// 证书申请唯一标示

	private String certId;// 证书id

	private String extName;// 自定义扩展域名称

	private String extValue;// 自定义扩展域值

	private String othernameId;// 其他名称oid
	
	private String certSn;//证书序列号

	public String getRegSn() {
		return regSn;
	}

	public void setRegSn(String regSn) {
		this.regSn = regSn;
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

	public String getExtValue() {
		return extValue;
	}

	public void setExtValue(String extValue) {
		this.extValue = extValue;
	}

	public String getOthernameId() {
		return othernameId;
	}

	public void setOthernameId(String othernameId) {
		this.othernameId = othernameId;
	}

	public String getCertSn() {
		return certSn;
	}

	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}

	
}
