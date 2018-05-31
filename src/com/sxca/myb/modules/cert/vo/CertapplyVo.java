package com.sxca.myb.modules.cert.vo;

import java.util.List;

import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.certCtml.entity.CertSelfExt;
import com.sxca.myb.modules.certCtml.entity.CertStandardExt;

/**
 * 证书申请实体 
 * @author lihuilong 
 * @date : 2017年5月5日 下午5:03:47
 */
public class CertapplyVo {
	
	private CertapplyInfo certapplyInfo;//证书申请
	
	private List<CertSelfExt> certSelfExtList;//证书自定义扩展域
	
	private List<CertStandardExt> certStandardExtList;//证书标准扩展与

	public CertapplyInfo getCertapplyInfo() {
		return certapplyInfo;
	}

	public void setCertapplyInfo(CertapplyInfo certapplyInfo) {
		this.certapplyInfo = certapplyInfo;
	}

	public List<CertSelfExt> getCertSelfExtList() {
		return certSelfExtList;
	}

	public void setCertSelfExtList(List<CertSelfExt> certSelfExtList) {
		this.certSelfExtList = certSelfExtList;
	}

	public List<CertStandardExt> getCertStandardExtList() {
		return certStandardExtList;
	}

	public void setCertStandardExtList(List<CertStandardExt> certStandardExtList) {
		this.certStandardExtList = certStandardExtList;
	}
	
	
	
	
	

}
