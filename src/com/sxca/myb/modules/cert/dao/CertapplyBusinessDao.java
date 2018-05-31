package com.sxca.myb.modules.cert.dao;

import java.util.List;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.cert.entity.CertapplyBusinessInfo;


/**
 * @author ggw 
 * @date : 2017年5月25日 下午2:48:02
 */
@MyBatisDao
public interface CertapplyBusinessDao extends CrudDao<CertapplyBusinessInfo>{
	
	public CertapplyBusinessInfo findByCertSubject();
	
	public List<CertapplyBusinessInfo>findbyList (CertapplyBusinessInfo certapplyBusinessInfo);

}
