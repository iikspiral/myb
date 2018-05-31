package com.sxca.myb.modules.mobile.entity;

import com.sxca.myb.common.BTK.entity.CertBase;

/**
 * 证书下载
 * 
 * @author glq
 * @date : 2017-05-05
 */
public class CertMake extends CertBase{

	private String authCode;    //授权码
	private String refCode;     //参考码
	private String p10;         //P10申请书
	private String doubleP10;  //doubleP10申请书
	private String isRetainKey; //是否保持原有密钥
	private Boolean isCheckAuthcode;//是否检查授权码
	private long notBefore; //延期下载生效日期
	private long notAftere; //延期下载失效日期
	private Integer validaty;//延期下载有效期

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getP10() {
		return p10;
	}

	public void setP10(String p10) {
		this.p10 = p10;
	}

	public String getDoubleP10() {
		return doubleP10;
	}

	public void setDoubleP10(String doubleP10) {
		this.doubleP10 = doubleP10;
	}

	public String getIsRetainKey() {
		return isRetainKey;
	}

	public void setIsRetainKey(String isRetainKey) {
		this.isRetainKey = isRetainKey;
	}

	public Boolean getCheckAuthcode() {
		return isCheckAuthcode;
	}

	public void setCheckAuthcode(Boolean checkAuthcode) {
		isCheckAuthcode = checkAuthcode;
	}

	public long getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(long notBefore) {
		this.notBefore = notBefore;
	}

	public long getNotAftere() {
		return notAftere;
	}

	public void setNotAftere(long notAftere) {
		this.notAftere = notAftere;
	}

	public Integer getValidaty() {
		return validaty;
	}

	public void setValidaty(Integer validaty) {
		this.validaty = validaty;
	}
}
