package com.sxca.myb.modules.config.logman.entity;

import com.sxca.myb.common.persistence.DataEntity;

import java.util.Date;



/**  
* <p>Title: CertAppLog</p>  
* <p>Description: </p>  
* <p>Company: 吉大正元</p>   
* @author Yophy.W 
* @date 上午8:28:49  
*/ 
public class CertAppLog extends DataEntity<CertAppLog> {

	private static final long serialVersionUID = 1L;
	private String certSn;
	private String systemFlag;
	private String operationName;
	private Date operationDate;
	private String  busDes;
	private String optType;
//	-----------------------------
	private String systemNameeng;
//	-----------------------------
	public String getCertSn() {
		return certSn;
	}
	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}
	public String getSystemFlag() {
		return systemFlag;
	}
	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	public String getSystemNameeng() {
		return systemNameeng;
	}
	public void setSystemNameeng(String systemNameeng) {
		this.systemNameeng = systemNameeng;
	}
	public String getBusDes() {
		return busDes;
	}
	public void setBusDes(String busDes) {
		this.busDes = busDes;
	}
	public String getOptType() {
		return optType;
	}
	public void setOptType(String optType) {
		this.optType = optType;
	}


	
}