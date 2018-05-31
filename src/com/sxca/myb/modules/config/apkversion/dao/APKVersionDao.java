package com.sxca.myb.modules.config.apkversion.dao;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.config.apkversion.entity.APKVersion;

@MyBatisDao
public interface APKVersionDao extends CrudDao<APKVersion>{

}
