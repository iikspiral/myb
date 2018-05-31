package com.sxca.myb.modules.config.facewhite.vo;

import java.io.Serializable;

/**
 * @author lihuilong
 * @date : 2017年6月12日 上午11:53:05
 */
public class FacewhiteVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idCardNum;

	private String userName;

	private String isEffect;

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

	public String getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}

}
