package com.sxca.myb.modules.config.appsystem.entity;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * @author Yophw 系统配置实体
 *
 */
public class ApplicationSystem extends DataEntity<ApplicationSystem> {

	private static final long serialVersionUID = 1L;
	
	private String systemNameeng;  //系统名称英文
	private String systemName;//系统名称
	private String systemFlag;//应用标识
	private String province;//省
	private String city;//市
	private String country;//县
	//	-------------------------------------------------
	public String getSystemNameeng() {
		return systemNameeng;
	}
	public void setSystemNameeng(String systemNameeng) {
		this.systemNameeng = systemNameeng;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemFlag() {
		return systemFlag;
	}
	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
