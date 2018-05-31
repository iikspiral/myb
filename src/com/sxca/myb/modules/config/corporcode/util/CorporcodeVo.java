package com.sxca.myb.modules.config.corporcode.util;
/**
 * @author lihuilong 
 * @date : 2017年4月14日 下午2:32:27
 */
public class CorporcodeVo {
	
	private Integer pageNo;
	
	private Integer pageSize;
	
	private String corporationId;
	
	private String projectId;
	
	private String type;
	
	private String corpname;
	
	private String certSubject;
	
	
	
	

	public String getCertSubject() {
		return certSubject;
	}

	public void setCertSubject(String certSubject) {
		this.certSubject = certSubject;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
