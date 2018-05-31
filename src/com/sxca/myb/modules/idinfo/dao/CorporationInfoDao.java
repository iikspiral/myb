package com.sxca.myb.modules.idinfo.dao;

import java.util.List;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;

/**
 * @author lihuilong 
 * @date : 2017年4月12日 上午11:27:41
 */
@MyBatisDao
public interface CorporationInfoDao extends CrudDao<CorporationInfo> {

	
	public List<CorporationInfo> fingByids(List strList);
	//获取企业用户相关联信息
	public List<CorporationInfo> findProUserlist(CorporationInfo corporationInfo);

	public List<CorporationInfo> fingByCorUserinfo(CorporationUserInfo corporationUserInfo);
}
