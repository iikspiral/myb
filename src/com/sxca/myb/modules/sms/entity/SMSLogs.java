package com.sxca.myb.modules.sms.entity;

import com.sxca.myb.common.persistence.DataEntity;

import java.util.Date;

public class SMSLogs extends DataEntity<SMSLogs>{
	//Fields
	private String id;  //ID
	private String templateName;  //模板名称
	private String phoneNum;  //手机号
	private String content;  //短信内容
	private String sendState;  //发送状态
	private Date sendTime;  //发送时间


	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
}
