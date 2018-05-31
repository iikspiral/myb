package com.sxca.myb.modules.mobile.entity;

import com.sxca.myb.common.persistence.DataEntity;


/**  
* 人脸信息类
* @author glq  	2017-05-11
*/ 
public class FaceInfo extends DataEntity<FaceInfo> {

	private static final long serialVersionUID = 1L;
	private String name;		//姓名
	private String phoneNum;		//手机号
	private String idcard;		//身份证号
	private String picString;		//人脸图片字符串

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPicString() {
		return picString;
	}

	public void setPicString(String picString) {
		this.picString = picString;
	}
}