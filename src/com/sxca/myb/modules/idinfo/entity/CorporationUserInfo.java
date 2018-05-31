package com.sxca.myb.modules.idinfo.entity;

import java.util.List;

import com.sxca.myb.common.persistence.DataEntity;

public class CorporationUserInfo extends DataEntity<CorporationUserInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String idCard;
	
	private String phoneNum;
	
	private List userid;
	
	private String rdmNum;
	
	public String getRdmNum() {
		return rdmNum;
	}

	public void setRdmNum(String rdmNum) {
		this.rdmNum = rdmNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public List getUserid() {
		return userid;
	}

	public void setUserid(List userid) {
		this.userid = userid;
	}


}
