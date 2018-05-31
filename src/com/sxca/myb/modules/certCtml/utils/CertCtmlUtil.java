package com.sxca.myb.modules.certCtml.utils;

import java.util.List;

import com.sxca.myb.common.utils.SpringContextHolder;
import com.sxca.myb.modules.certCtml.dao.CertCtmlDao;
import com.sxca.myb.modules.certCtml.entity.CertCtml;

/**
 * @author lihuilong 
 * @date : 2017年4月20日 上午10:00:48
 */
public class CertCtmlUtil {
	
	private static CertCtmlDao certCtmlDao = SpringContextHolder
			.getBean(CertCtmlDao.class);
	
	/**
	 * 获取所有模板信息
	 * @return
	 */
	public static List<CertCtml> getCertctmlList() {
		List<CertCtml> list =  certCtmlDao.findList(new CertCtml());
		return list;
	}
	
	/**
	 * 获取模板名称
	 * @param id
	 * @return
	 */
	public static String getCertname(String id){
		
		CertCtml certCtml = certCtmlDao.get(id);
		if(certCtml != null){
			return certCtml.getCertCtmlName();
		}else{
			return "";
		}
	}

}
