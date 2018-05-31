package com.sxca.myb.modules.config.basearea.util;

import org.apache.commons.lang3.StringUtils;

import com.sxca.myb.common.utils.SpringContextHolder;
import com.sxca.myb.modules.config.basearea.dao.BaseAreaDao;
import com.sxca.myb.modules.config.basearea.entity.BaseArea;

/**
 * @author lihuilong 
 * @date : 2017年6月30日 上午9:40:28
 */
public class AreaUtil {
	
	
	private static BaseAreaDao baseAreaDao = SpringContextHolder
			.getBean(BaseAreaDao.class);

	
	public static String getAreaname(String baseAreaid){
		if(StringUtils.isNotBlank(baseAreaid)){
			BaseArea baseArea = new BaseArea();
			baseArea.setBaseAreaid(baseAreaid);
			BaseArea entity = baseAreaDao.getAreabyAreaid(baseArea);
			return entity.getName();
		}else{
			return "无";
		}
	}
}
