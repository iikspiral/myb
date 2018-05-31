/**
 * @author Glan.duanyj
 * @date 2014-05-12
 * @project rest_demo
 */
package com.sxca.myb.common.ucpaas.restDemo.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appBill")
public class AppBill{
	/**
	 * 
	 */
	private java.lang.String appId;
	private java.lang.String date;
	private String downUrl;
	private String token;
	public java.lang.String getAppId() {
		return appId;
	}
	public void setAppId(java.lang.String appId2) {
		this.appId = appId2;
	}
	public java.lang.String getDate() {
		return date;
	}
	public void setDate(java.lang.String date2) {
		this.date = date2;
	}
	public String getDownUrl() {
		return downUrl;
	}
	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
