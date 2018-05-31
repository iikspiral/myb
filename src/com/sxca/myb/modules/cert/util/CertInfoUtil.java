package com.sxca.myb.modules.cert.util;

import java.util.List;

import com.sxca.myb.common.utils.SpringContextHolder;
import com.sxca.myb.modules.cert.dao.CertInfoDao;
import com.sxca.myb.modules.cert.entity.CertInfo;


/**
 * @author Yophw
 *
 */
public class CertInfoUtil {
	
	private static CertInfoDao certInfoDao = SpringContextHolder
			.getBean(CertInfoDao.class);
	/**
	 * 获取所有项目
	 * @return
	 */
	
	public static List<CertInfo> getCertInfoList(String certType) {
		List<CertInfo> list =  certInfoDao.getCertInfoList(certType);
		return list;
	}
	
	public static String getCertSubject(String certSn){
		CertInfo certInfo =  certInfoDao.getByCertSn(certSn);
		if(certInfo != null){
			return certInfo.getCertSubject();
		}else{
			return "";
		}
	}
}
