package com.sxca.myb.modules.idinfo.entity;

import java.util.List;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * 个人用户信息
 * 
 * @author lihuilong
 * @date : 2017年4月10日 下午2:13:21
 */
public class UserInfo extends DataEntity<UserInfo> {

	private static final long serialVersionUID = 1L;

	private String username;// 用户名 ra username userInfoName

	private String phonenum;// 电话号码 ra phonenum userPhoneNum

	private String usercontactaddr;// 联系地址 ra usercontactaddr userContactAddr

	private String idtype;// 证件类型 ra idtype idcardType

	private String useridcardnum;// 证件号码 ra useridcardnum userInfoIdcardNum

	private String org;// 单位名称 ra org  userOrg

	private String raUserId;// ra用户id

	private String userState;// 用户状态

	private String extra1;// 扩展域1

	private String extra2;// 扩展域2

	private String extra3;// 扩展域3

	private String extra4;// 扩展域4

	private String extra5;// 扩展域5

	private String extra6;// 扩展域6

	private String extra7;// 扩展域7

	private String extra8;// 扩展域8

	private String extra9;// 扩展域9

	private String extra10;// 扩展域10
	
	private String userType;//用户类型(0:正常，1:散户)
	
	private String isFace;//是否人脸认证(0:是,1:否)

	private String remarks;
//--------------------------------
	private List<String> strList; //查询添加字段 wyf
//	----------------------------
	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

	public String getExtra3() {
		return extra3;
	}

	public void setExtra3(String extra3) {
		this.extra3 = extra3;
	}

	public String getExtra4() {
		return extra4;
	}

	public void setExtra4(String extra4) {
		this.extra4 = extra4;
	}

	public String getExtra5() {
		return extra5;
	}

	public void setExtra5(String extra5) {
		this.extra5 = extra5;
	}

	public String getExtra6() {
		return extra6;
	}

	public void setExtra6(String extra6) {
		this.extra6 = extra6;
	}

	public String getExtra7() {
		return extra7;
	}

	public void setExtra7(String extra7) {
		this.extra7 = extra7;
	}

	public String getExtra8() {
		return extra8;
	}

	public void setExtra8(String extra8) {
		this.extra8 = extra8;
	}

	public String getExtra9() {
		return extra9;
	}

	public void setExtra9(String extra9) {
		this.extra9 = extra9;
	}

	public String getExtra10() {
		return extra10;
	}

	public void setExtra10(String extra10) {
		this.extra10 = extra10;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRaUserId() {
		return raUserId;
	}

	public void setRaUserId(String raUserId) {
		this.raUserId = raUserId;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getUsercontactaddr() {
		return usercontactaddr;
	}

	public void setUsercontactaddr(String usercontactaddr) {
		this.usercontactaddr = usercontactaddr;
	}

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	public String getUseridcardnum() {
		return useridcardnum;
	}

	public void setUseridcardnum(String useridcardnum) {
		this.useridcardnum = useridcardnum;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIsFace() {
		return isFace;
	}

	public void setIsFace(String isFace) {
		this.isFace = isFace;
	}

	@Override
	public String getRemarks() {
		return remarks;
	}

	@Override
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	//	--------------------------------
	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
//	-------------------------------------
	
	
	
}
