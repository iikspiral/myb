package com.sxca.myb.modules.idinfo.entity;

import java.util.List;

import com.sxca.myb.common.persistence.DataEntity;
/**
 * 企业用户信息
* @author lihuilong
* @date : 2017年4月10日 下午2:14:29
 */
public class CorporationInfo extends DataEntity<CorporationInfo> {

	private static final long serialVersionUID = 1L;

	private String corpname;// 企业名称  ra  corpname   corpName

	private String legalpersonname;// 法人代表 ra legalpersonname   legalPersonName

	private String corpidtype;// 企业有效证件类型代码  ra corpidtype   corpIdcardType
 
	private String corpidcardnum;// 企业有效证件号码  ra corpidcardnum   corpIdcardNum

	private String agentname;// 经办人姓名  ra agentname   agentName
 
	private String agentphone;// 经办人联系电话 ra agentphone    agentPhone

	private String agentcontactaddr;// 经办人联系地址 ra agentcontactaddr   agentContactAddr

	private String agentidtype;// 经办人有效证件类型  ra agentidtype  agentIdcardType

	private String agentidcardnum;// 经办人有效证件号码  ra agentidcardnum  agentIdcardNum
	
	private String raUserId;//ra用户id
	
	private String userState;//用户状态

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
	
	private CorporationUserInfo corporationUserInfo;//企业用户
	
	private CorporationUserRelation corporationUserRelation;//企业与企业用户信息中间表
	
	private String corpUserHe;//
	
	private List<String> strList;
	
	private String projectName;

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

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getLegalpersonname() {
		return legalpersonname;
	}

	public void setLegalpersonname(String legalpersonname) {
		this.legalpersonname = legalpersonname;
	}

	public String getCorpidtype() {
		return corpidtype;
	}

	public void setCorpidtype(String corpidtype) {
		this.corpidtype = corpidtype;
	}

	public String getCorpidcardnum() {
		return corpidcardnum;
	}

	public void setCorpidcardnum(String corpidcardnum) {
		this.corpidcardnum = corpidcardnum;
	}

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public String getAgentphone() {
		return agentphone;
	}

	public void setAgentphone(String agentphone) {
		this.agentphone = agentphone;
	}

	public String getAgentcontactaddr() {
		return agentcontactaddr;
	}

	public void setAgentcontactaddr(String agentcontactaddr) {
		this.agentcontactaddr = agentcontactaddr;
	}

	public String getAgentidtype() {
		return agentidtype;
	}

	public void setAgentidtype(String agentidtype) {
		this.agentidtype = agentidtype;
	}

	public String getAgentidcardnum() {
		return agentidcardnum;
	}

	public void setAgentidcardnum(String agentidcardnum) {
		this.agentidcardnum = agentidcardnum;
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

	public CorporationUserInfo getCorporationUserInfo() {
		return corporationUserInfo;
	}

	public void setCorporationUserInfo(CorporationUserInfo corporationUserInfo) {
		this.corporationUserInfo = corporationUserInfo;
	}

	public CorporationUserRelation getCorporationUserRelation() {
		return corporationUserRelation;
	}

	public void setCorporationUserRelation(
			CorporationUserRelation corporationUserRelation) {
		this.corporationUserRelation = corporationUserRelation;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public String getCorpUserHe() {
		return corpUserHe;
	}

	public void setCorpUserHe(String corpUserHe) {
		this.corpUserHe = corpUserHe;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
}
