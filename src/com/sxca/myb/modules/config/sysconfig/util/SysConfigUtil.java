package com.sxca.myb.modules.config.sysconfig.util;

import java.util.List;

import com.sxca.myb.common.utils.CacheUtils;
import com.sxca.myb.common.utils.SpringContextHolder;
import com.sxca.myb.modules.config.sysconfig.dao.SystemConfigDao;
import com.sxca.myb.modules.config.sysconfig.entity.SystemConfig;


public class SysConfigUtil {
	
	public static final String CACHE_LOGINTYPE = "loginType";
	
	public static final String CACHE_SYSTEMNAME = "systemName";
	
	public static final String CACHE_COPYRIGHT = "copyRightInfo";
	
	private static SystemConfigDao systemConfigDao = SpringContextHolder
			.getBean(SystemConfigDao.class);
	
	public static String getLoginType() {
		String loginType = (String) CacheUtils.get(CACHE_LOGINTYPE);
		if(loginType == null||"".equals(loginType)) {
			List<SystemConfig> systemConfigs = systemConfigDao.findList(new SystemConfig());
			if(systemConfigs!=null&&systemConfigs.size()>0) {
				SystemConfig systemConfig = systemConfigs.get(0);
				loginType = systemConfig.getLoginType();
				CacheUtils.put(CACHE_LOGINTYPE, loginType);
			}
		}
		return loginType;
	}
	
	public static String getSystemName() {
		String systemName = (String) CacheUtils.get(CACHE_SYSTEMNAME);
		if(systemName == null||"".equals(systemName)) {
			List<SystemConfig> systemConfigs = systemConfigDao.findList(new SystemConfig());
			if(systemConfigs!=null&&systemConfigs.size()>0) {
				SystemConfig systemConfig = systemConfigs.get(0);
				systemName = systemConfig.getSystemName();
				CacheUtils.put(CACHE_SYSTEMNAME, systemName);
			}
		}
		return systemName;
	}
	
	public static String getCopyRight() {
		String copyRightInfo = (String) CacheUtils.get(CACHE_COPYRIGHT);
		if(copyRightInfo == null||"".equals(copyRightInfo)) {
			List<SystemConfig> systemConfigs = systemConfigDao.findList(new SystemConfig());
			if(systemConfigs!=null&&systemConfigs.size()>0) {
				SystemConfig systemConfig = systemConfigs.get(0);
				copyRightInfo = systemConfig.getCopyRightInfo();
				CacheUtils.put(CACHE_COPYRIGHT, copyRightInfo);
			}
		}
		return copyRightInfo;
	}

}
