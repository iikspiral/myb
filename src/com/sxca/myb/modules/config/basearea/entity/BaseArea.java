package com.sxca.myb.modules.config.basearea.entity;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * 
 * @author lihuilong
 * @date : 2017年6月29日 下午2:38:32
 */
public class BaseArea extends DataEntity<BaseArea> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String baseAreaid;// 地址ID

	private String name;// 地区名字

	private String parentid;// 上级路径Id

	private String vieworder;// 顺序

	public String getBaseAreaid() {
		return baseAreaid;
	}

	public void setBaseAreaid(String baseAreaid) {
		this.baseAreaid = baseAreaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getVieworder() {
		return vieworder;
	}

	public void setVieworder(String vieworder) {
		this.vieworder = vieworder;
	}

}
