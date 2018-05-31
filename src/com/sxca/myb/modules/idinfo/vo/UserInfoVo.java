package com.sxca.myb.modules.idinfo.vo;

import java.io.Serializable;

/**
 * @author lihuilong
 * @date : 2017年6月12日 上午9:27:25
 */
public class UserInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;

	private String usercontactaddr;

	private String org;

	private String idtype;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsercontactaddr() {
		return usercontactaddr;
	}

	public void setUsercontactaddr(String usercontactaddr) {
		this.usercontactaddr = usercontactaddr;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

}
