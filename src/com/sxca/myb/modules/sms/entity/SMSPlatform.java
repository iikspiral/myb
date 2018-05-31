package com.sxca.myb.modules.sms.entity;

import com.sxca.myb.common.persistence.DataEntity;

public class SMSPlatform extends DataEntity<SMSPlatform> {
	// Fields

	private String id; //ID
	private String smsName; //平台名称
	private String smsCode; //平台编码
	private String accountId; //开发者ID
	private String tokenId; //开发者token
	private String appId; //应用ID
	private String state; //状态

	// Constructors

	/** default constructor */
	public SMSPlatform() {
	}

	public String getSmsName() {
		return smsName;
	}

	public void setSmsName(String smsName) {
		this.smsName = smsName;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
