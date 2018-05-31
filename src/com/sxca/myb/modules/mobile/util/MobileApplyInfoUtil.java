package com.sxca.myb.modules.mobile.util;

import java.util.List;

import com.sxca.myb.common.utils.SpringContextHolder;
import com.sxca.myb.modules.mobile.dao.MobileApplyInfoDao;
import com.sxca.myb.modules.mobile.entity.MobileApplyInfo;


/**  
* <p>Title: MobileApplyInfoUtil</p>  
* <p>Description: </p>  
* <p>Company: 吉大正元</p>   
* @author wyf 
* @date 2017年5月18日下午3:52:56  
* @version V1.0 
*/ 
public class MobileApplyInfoUtil {
	
	private static MobileApplyInfoDao mobileApplyInfo = SpringContextHolder
			.getBean(MobileApplyInfoDao.class);
	/**
	 * 获取所有手机相关信息
	 * @return
	 */
	
	public static List<MobileApplyInfo> getMobileApplyInfoList() {
		List<MobileApplyInfo> list =  mobileApplyInfo.findList(new MobileApplyInfo());
		return list;
	}
	
}
