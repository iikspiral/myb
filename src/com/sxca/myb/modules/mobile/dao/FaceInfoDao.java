package com.sxca.myb.modules.mobile.dao;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.mobile.entity.FaceInfo;

/**
 * 人脸信息Dao
 * @author glq 		2017-05-11
 */
@MyBatisDao
public interface FaceInfoDao extends CrudDao<FaceInfo> {

}
