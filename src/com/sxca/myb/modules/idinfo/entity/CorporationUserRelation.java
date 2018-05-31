package com.sxca.myb.modules.idinfo.entity;

import java.util.List;

import com.sxca.myb.common.persistence.DataEntity;

public class CorporationUserRelation extends DataEntity<CorporationUserRelation>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String corporationId;
	
	private String corporationUserId;
	
	private String isAdmin;
	
	private String userName;
	
	private String idCard;
	
	private String phoneNum;

	private String isChoose;
//	-----------------------------------------
	
//	-----------------------------------------

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getCorporationUserId() {
		return corporationUserId;
	}

	public void setCorporationUserId(String corporationUserId) {
		this.corporationUserId = corporationUserId;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
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

	public String getIsChoose() {
		return isChoose;
	}

	public void setIsChoose(String isChoose) {
		this.isChoose = isChoose;
	}
}
