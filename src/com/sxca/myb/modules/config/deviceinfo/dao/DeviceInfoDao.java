package com.sxca.myb.modules.config.deviceinfo.dao;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.config.deviceinfo.entity.DeviceInfo;

@MyBatisDao
public interface DeviceInfoDao extends CrudDao<DeviceInfo> {
	

}
