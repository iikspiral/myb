package com.sxca.myb.modules.config.corporcode.vo;

import java.io.Serializable;

/**
 * @author lihuilong
 * @date : 2017年6月12日 上午10:52:52
 */
public class CorporationRequestCodeVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String projectId;

	private String corporationId;

	private String corporUserId;

	private String certSubject;

	private String type;

	private String status;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getCorporUserId() {
		return corporUserId;
	}

	public void setCorporUserId(String corporUserId) {
		this.corporUserId = corporUserId;
	}

	public String getCertSubject() {
		return certSubject;
	}

	public void setCertSubject(String certSubject) {
		this.certSubject = certSubject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
