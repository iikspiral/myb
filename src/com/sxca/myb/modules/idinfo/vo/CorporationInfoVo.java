package com.sxca.myb.modules.idinfo.vo;

import java.io.Serializable;

/**
 * @author lihuilong
 * @date : 2017年6月12日 上午9:50:49
 */
public class CorporationInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String corpname;

	private String legalpersonname;

	private String agentname;

	private String agentcontactaddr;

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

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public String getAgentcontactaddr() {
		return agentcontactaddr;
	}

	public void setAgentcontactaddr(String agentcontactaddr) {
		this.agentcontactaddr = agentcontactaddr;
	}

}
