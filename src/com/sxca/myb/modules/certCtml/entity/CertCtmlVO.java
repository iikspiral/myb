package com.sxca.myb.modules.certCtml.entity;

import java.util.List;

/**
 * 证书模板vo
 * @author glq 		2017-05-10
 *
 */
public class CertCtmlVO {
	private CertCtml ctml;//证书模板实体
	private List<CertCtmlSelfExt> selfExt;//证书模板自定义扩展域
	private List<CertCtmlStandardExt> standardExt;//证书模板标准扩展域
	private List<List<CertCtmlStandardExt>> standardExtList;//证书模板标准扩展域集合

	public CertCtml getCtml() {
		return ctml;
	}
	public void setCtml(CertCtml ctml) {
		this.ctml = ctml;
	}

	public List<CertCtmlSelfExt> getSelfExt() {
		return selfExt;
	}

	public void setSelfExt(List<CertCtmlSelfExt> selfExt) {
		this.selfExt = selfExt;
	}

	public List<CertCtmlStandardExt> getStandardExt() {
		return standardExt;
	}

	public void setStandardExt(List<CertCtmlStandardExt> standardExt) {
		this.standardExt = standardExt;
	}

	public List<List<CertCtmlStandardExt>> getStandardExtList() {
		return standardExtList;
	}

	public void setStandardExtList(List<List<CertCtmlStandardExt>> standardExtList) {
		this.standardExtList = standardExtList;
	}
}
