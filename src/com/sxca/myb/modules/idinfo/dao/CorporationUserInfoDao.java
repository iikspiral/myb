package com.sxca.myb.modules.idinfo.dao;

import java.util.List;
import java.util.Map;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;

@MyBatisDao
public interface CorporationUserInfoDao extends CrudDao<CorporationUserInfo>{

	public CorporationUserInfo getByIdCard(String idCard);
	
	public List<CorporationUserInfo> findListNotUsers(Map<String, Object> map);
	
}
