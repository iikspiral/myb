package com.sxca.myb.modules.config.sysconfig.dao;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.config.sysconfig.entity.SystemConfig;

@MyBatisDao
public interface SystemConfigDao extends CrudDao<SystemConfig> {
	

}
