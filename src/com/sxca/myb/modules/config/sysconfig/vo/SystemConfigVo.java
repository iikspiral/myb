package com.sxca.myb.modules.config.sysconfig.vo;

import java.io.Serializable;
public class SystemConfigVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String systemName;  //系统名称
	private String copyRightInfo;//版权信息
	private String phone;//联系方式
	private String loginType;//登录方式(用户名密码，证书)
	private String isuseDefaultProject;//是否启用默认项目
//	-------------------------------------------------
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getCopyRightInfo() {
		return copyRightInfo;
	}
	public void setCopyRightInfo(String copyRightInfo) {
		this.copyRightInfo = copyRightInfo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getIsuseDefaultProject() {
		return isuseDefaultProject;
	}
	public void setIsuseDefaultProject(String isuseDefaultProject) {
		this.isuseDefaultProject = isuseDefaultProject;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

}
