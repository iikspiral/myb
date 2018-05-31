package com.sxca.myb.modules.idinfo.utils;

import java.util.List;

import com.sxca.myb.common.utils.SpringContextHolder;
import com.sxca.myb.modules.idinfo.dao.UserInfoDao;
import com.sxca.myb.modules.idinfo.entity.UserInfo;


/**
 * @author Yophw
 *
 */
public class UserInfoUtil {
	
	private static UserInfoDao userInfoDao = SpringContextHolder
			.getBean(UserInfoDao.class);
	/**
	 * 获取所有项目
	 * @return
	 */
	
	public static List<UserInfo> getUserInfoList() {
		List<UserInfo> list =  userInfoDao.getUserInfoList();
		return list;
	}
}
