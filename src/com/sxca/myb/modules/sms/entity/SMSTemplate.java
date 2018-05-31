package com.sxca.myb.modules.sms.entity;

import com.sxca.myb.common.persistence.DataEntity;

public class SMSTemplate extends DataEntity<SMSTemplate> {
	//Fields
	private String id;  //Id
	private String templateType;  //模板类型
	private String templateName;  //模板名称
	private String smsSignature;  //短信签名
	private String content;  //短信内容
	private String templateId;  //模板ID

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getSmsSignature() {
		return smsSignature;
	}

	public void setSmsSignature(String smsSignature) {
		this.smsSignature = smsSignature;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
}
