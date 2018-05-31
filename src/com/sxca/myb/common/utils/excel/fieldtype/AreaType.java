/**
 * Copyright &copy; 2016-2016 <a href="http://www.sxca.com.cn">山西CA</a> All rights reserved.
 */
package com.sxca.myb.common.utils.excel.fieldtype;

import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.modules.sys.entity.Area;
import com.sxca.myb.modules.sys.utils.UserUtils;

/**
 * 字段类型转换
 * @author prophet
 * @version 2013-03-10
 */
public class AreaType {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		for (Area e : UserUtils.getAreaList()){
			if (StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((Area)val).getName() != null){
			return ((Area)val).getName();
		}
		return "";
	}
}
