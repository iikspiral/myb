package com.sxca.myb.modules.idinfo.dao;

import java.util.List;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.idinfo.entity.UserInfo;

/**
 * @author Yophy.W
 * @date : 2017年4月11日 上午11:20:11
 */
@MyBatisDao
public interface UserInfoDao extends CrudDao<UserInfo>{

	public List<UserInfo> getUserInfoList();
	
	public List<UserInfo> fingByids(List<String> userids);
	
	public UserInfo findUserByCertSn(String certs);
	
	public List<UserInfo> findProUserlist(UserInfo userInfo);
}
