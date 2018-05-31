package com.sxca.myb.modules.pro.entity;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * 项目实体
 * 
 * @author lihuilong
 * @date : 2017年4月10日 下午3:52:51
 */
public class ProjectInfo extends DataEntity<ProjectInfo> {

	private static final long serialVersionUID = 1L;

	private String projectNum;// 项目编号

	private String projectName;// 项目名称

	private String certTemplate;// 证书模板

	private String makeCertRules;// 制证规则

	private String selfExtRules;// 自定义扩展域规则

	private String standardExtRules;// 标准扩展域规则

	private String isuseFace;// 是否启用人脸识别

	private Integer certValidity;// 证书有效期

	private String isDefaultProject;// 是否默认项目

	private String isHoldKey;// 是否保持密钥

	private Integer allowUpdateDay;// 允许更新条件(天)

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

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

	public String getMakeCertRules() {
		return makeCertRules;
	}

	public void setMakeCertRules(String makeCertRules) {
		this.makeCertRules = makeCertRules;
	}

	public String getSelfExtRules() {
		return selfExtRules;
	}

	public void setSelfExtRules(String selfExtRules) {
		this.selfExtRules = selfExtRules;
	}

	public String getIsuseFace() {
		return isuseFace;
	}

	public void setIsuseFace(String isuseFace) {
		this.isuseFace = isuseFace;
	}

	public Integer getCertValidity() {
		return certValidity;
	}

	public void setCertValidity(Integer certValidity) {
		this.certValidity = certValidity;
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

	public Integer getAllowUpdateDay() {
		return allowUpdateDay;
	}

	public void setAllowUpdateDay(Integer allowUpdateDay) {
		this.allowUpdateDay = allowUpdateDay;
	}

	public String getStandardExtRules() {
		return standardExtRules;
	}

	public void setStandardExtRules(String standardExtRules) {
		this.standardExtRules = standardExtRules;
	}

}
