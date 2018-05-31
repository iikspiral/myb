package com.sxca.myb.modules.idinfo.utils;

import java.util.List;

import com.sxca.myb.common.utils.SpringContextHolder;
import com.sxca.myb.modules.idinfo.dao.CorporationInfoDao;
import com.sxca.myb.modules.idinfo.dao.CorporationUserInfoDao;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.pro.entity.ProjectInfo;

/**
 * @author lihuilong 
 * @date : 2017年4月18日 上午11:05:00
 */
public class CorporationUtil {

	
	private static CorporationInfoDao corporationInfoDao = SpringContextHolder
			.getBean(CorporationInfoDao.class);
	
	private static CorporationUserInfoDao corporationUserInfoDao = SpringContextHolder
			.getBean(CorporationUserInfoDao.class);
	
	/**
	 * 获取单个项目名称
	 * @return
	 */
	public static String getCorporName(String id){
		
		CorporationInfo corporationInfo = corporationInfoDao.get(id);
		if(corporationInfo != null){
			return corporationInfo.getCorpname();
		}else{
			return "";
		}
		
	}
	
	
	/**
	 * 获取所有企业用户信息
	 * @return
	 */
	public static List<CorporationInfo> getCorporList() {
		List<CorporationInfo> list =  corporationInfoDao.findList(new CorporationInfo());
		return list;
	}
	
	public static List<CorporationUserInfo> getCorporUserList(){
		
		List<CorporationUserInfo> list = corporationUserInfoDao.findList(new CorporationUserInfo());
		return list;
		
	}
	
	public static String getCorpUsername(String id){
		
		CorporationUserInfo corporationUserInfo = corporationUserInfoDao.get(id);

		if(corporationUserInfo != null){
			return corporationUserInfo.getUserName();
		}else{
			return "";
		}
		
	}
	
	public static List<CorporationInfo> getCorporUserHelist(){
		
		List<CorporationInfo> list = corporationInfoDao.findProUserlist(new CorporationInfo());
		if(list != null && list.size() > 0){
			for(int i = 0;i<list.size();i++){
				CorporationInfo corporationInfo = list.get(i);
				corporationInfo.setCorpUserHe(corporationInfo.getCorpname()+"("+corporationInfo.getCorporationUserInfo().getUserName()+")");
			}
			return list;
		}else{
			return null;
		}
		
		
	}
	
	
	
	
}
