package com.sxca.myb.modules.config.facewhite.entity;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * 人脸白名单
 * 
 * @author lihuilong
 * @date : 2017年5月2日 下午5:47:37
 */
public class Facewhite extends DataEntity<Facewhite> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idCardNum;// 身份证号码

	private String userName;// 联系人姓名

	private String userPhone;// 联系人电话

	private String isEffect;// 是否生效

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}



}
