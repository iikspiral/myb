package com.sxca.myb.modules.config.usertypedetail.entity;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * @author lihuilong
 * @date : 2017年6月21日 下午3:32:16
 */
public class UserTypeDetail extends DataEntity<UserTypeDetail> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userType;

	private String displayName;

	private String failName;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFailName() {
		return failName;
	}

	public void setFailName(String failName) {
		this.failName = failName;
	}

}
