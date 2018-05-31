package com.sxca.myb.modules.pro.vo;

import java.io.Serializable;

/**
 * @author lihuilong 
 * @date : 2017年6月12日 上午8:57:56
 */
public class ProjectInfoVo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String projectName;
	
	private String certTemplate;
	
	private String isDefaultProject;
	
	private String isHoldKey;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCertTemplate() {
		return certTemplate;
	}

	public void setCertTemplate(String certTemplate) {
		this.certTemplate = certTemplate;
	}

	public String getIsDefaultProject() {
		return isDefaultProject;
	}

	public void setIsDefaultProject(String isDefaultProject) {
		this.isDefaultProject = isDefaultProject;
	}

	public String getIsHoldKey() {
		return isHoldKey;
	}

	public void setIsHoldKey(String isHoldKey) {
		this.isHoldKey = isHoldKey;
	}
	
	

}
