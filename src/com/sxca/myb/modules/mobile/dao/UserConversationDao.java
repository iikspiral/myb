package com.sxca.myb.modules.mobile.dao;

import java.util.List;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.mobile.entity.UserConversation;

/**
 * @author ggw
 * @date : 2017-05-04
 */
@MyBatisDao
public interface UserConversationDao extends CrudDao<UserConversation>{
	
	public List<UserConversation> findListByPhoneNum(String phoneNum);
	
	public void deleteByPhonenum(String phoneNum);

}
