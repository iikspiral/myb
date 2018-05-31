package com.sxca.myb.modules.cert.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.cert.entity.CertInfo;

/**
 * @author Yophy.w 
 * @date : 2017年4月17日 下午3:27:07
 */
@MyBatisDao
public interface CertInfoDao extends CrudDao<CertInfo>{

	public List<CertInfo> getCertInfoList(@Param("certType")String certType);
	
	public List<CertInfo> fingByids(List<String> strList);
	
	public List<CertInfo> fingByCertSn(List<String> strList);

	public List<CertInfo> getCertInfoListByUserIds(List<String> strList);
	
	public List<CertInfo> getCertByCorIds(List<String> strList);

	public CertInfo getByCertSn(String cerSn);
	
	public CertInfo getByCertSnAndCertStatus(String cerSn);
	
	public CertInfo getByOldCertSn(String cerSn);
	
	public List<CertInfo> findbyList(CertInfo certInfo);
	
	public CertInfo getbyid(String id);
	
	public List<CertInfo> getNodownCertinfo(CertInfo certInfo);
	
	public List<CertInfo> findListBySP(CertInfo certInfo);
	
}
