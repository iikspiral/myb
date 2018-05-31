package com.sxca.myb.modules.idinfo.entity;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * 用户扩展域信息
 * 
 * @author lihuilong
 * @date : 2017年4月10日 下午2:13:09
 */
public class ExtraInfo extends DataEntity<ExtraInfo> {

	private static final long serialVersionUID = 1L;

	private String userInfoType;// 用户类型

	private String extraMean;// 代表含义

	private String dataType;// 字段类型 单行 0 字典 1

	private String attribute;// 对应属性

	private String isRequired;// 是否必填 1 是 0 否

	private String defaultValue;// 字段类型为单行，需要添加的默认值

	private String dicValue;// 字段类型为字典,字典类型

	private String checkValue;// 字段类型为单行，需要的校验类型,应存储正则

	private Integer filedLength;// 字段校验长度

	public String getUserInfoType() {
		return userInfoType;
	}

	public void setUserInfoType(String userInfoType) {
		this.userInfoType = userInfoType;
	}

	public String getExtraMean() {
		return extraMean;
	}

	public void setExtraMean(String extraMean) {
		this.extraMean = extraMean;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDicValue() {
		return dicValue;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public Integer getFiledLength() {
		return filedLength;
	}

	public void setFiledLength(Integer filedLength) {
		this.filedLength = filedLength;
	}

}
