package com.sxca.myb.modules.mobile.entity;

import java.util.Date;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * 移动会话Entity
 * @author ggw
 * @version 2017-05-04
 */
public class UserConversation extends DataEntity<UserConversation>{

	private static final long serialVersionUID = 1L;
	
	private String phoneNum;
	
	private String deviceNum;
	
	private Date conversationDate;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public Date getConversationDate() {
		return conversationDate;
	}

	public void setConversationDate(Date conversationDate) {
		this.conversationDate = conversationDate;
	}
	
	
}
