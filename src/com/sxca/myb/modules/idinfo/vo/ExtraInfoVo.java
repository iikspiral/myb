package com.sxca.myb.modules.idinfo.vo;

import java.io.Serializable;

/**
 * @author lihuilong
 * @date : 2017年6月12日 上午10:05:35
 */
public class ExtraInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userInfoType;

	private String dataType;

	public String getUserInfoType() {
		return userInfoType;
	}

	public void setUserInfoType(String userInfoType) {
		this.userInfoType = userInfoType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
