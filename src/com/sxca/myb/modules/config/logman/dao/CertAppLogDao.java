package com.sxca.myb.modules.config.logman.dao;

import java.util.List;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.config.logman.entity.CertAppLog;

/**
 * @author Yophw
 *@date : 2017年5月5日 下午3:24:04
 */
@MyBatisDao
public interface CertAppLogDao extends CrudDao<CertAppLog>{
	
	public List<CertAppLog> getCertAppLogs(CertAppLog CertAppLog);

}
