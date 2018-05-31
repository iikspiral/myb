package com.sxca.myb.modules.cert.dao;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;

import java.util.List;

/**
 * @author lihuilong 
 * @date : 2017年4月17日 下午6:02:02
 */
@MyBatisDao
public interface CertapplyInfoDao extends CrudDao<CertapplyInfo>{
	
	public  List<CertapplyInfo> findByCertSn (String certSn);

	public List<CertapplyInfo> findApplyByCertSn (String certSn);
	
	public List<CertapplyInfo> findbyList(CertapplyInfo certapplyInfo);

	public void deleteData(CertapplyInfo certapplyInfo);

}
