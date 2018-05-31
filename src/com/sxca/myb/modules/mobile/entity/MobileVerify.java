package com.sxca.myb.modules.mobile.entity;

import com.sxca.myb.common.persistence.DataEntity;

import java.util.Date;


/**
 * 移动端验证码Entity
 * @author glq
 * @version 2017-04-27
 */
public class MobileVerify extends DataEntity<MobileVerify> {

	private static final long serialVersionUID = 1L;
	private String deviceType;
	private String deviceNum;
	private String phoneNum;
	private String verifyCode;
	private Date sendDate;

	public MobileVerify(){
		super();
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
}