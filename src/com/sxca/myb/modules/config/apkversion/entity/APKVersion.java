package com.sxca.myb.modules.config.apkversion.entity;

import com.sxca.myb.common.persistence.DataEntity;

public class APKVersion extends DataEntity<APKVersion>{

	private static final long serialVersionUID = 1L;
	
	private String version;
	
	private String url;
	
	private String fileName;
	
	private String name;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
