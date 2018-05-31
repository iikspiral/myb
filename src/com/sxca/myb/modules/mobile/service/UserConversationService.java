package com.sxca.myb.modules.mobile.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.mobile.dao.UserConversationDao;
import com.sxca.myb.modules.mobile.entity.UserConversation;

/**
 * @author ggw
 * @date : 2017-05-04
 */
@Service
public class UserConversationService extends CrudService<UserConversationDao, UserConversation>{

	
	public List<UserConversation> findListByPhoneNum(String phoneNum) {
		return dao.findListByPhoneNum(phoneNum);
	}

	@Transactional(readOnly = false)
	public void createOrUpdate(String phoneNum, String deviceNum) {
		//根据手机号查询会话信息
		List<UserConversation> userConversations = dao.findListByPhoneNum(phoneNum);
		UserConversation userConversation = null;
		if(userConversations != null && userConversations.size() > 0){//会话信息存在则进行更新
			userConversation = userConversations.get(0);
			userConversation.setConversationDate(new Date());
			super.update(userConversation);
		}else{//会话信息不存在则保存
			userConversation = new UserConversation();
			userConversation.setPhoneNum(phoneNum);
			userConversation.setDeviceNum(deviceNum);
			userConversation.setConversationDate(new Date());
			super.insert(userConversation);
		}
	}
	@Transactional(readOnly = false)
	public void deleteByPhonenum(String phoneNum){
		dao.deleteByPhonenum(phoneNum);
	}
	
	@Transactional(readOnly = false)
	public void updateOrAddiserconversation(String deviceNum, String phoneNum) {

		List<UserConversation> userConversationList = dao
				.findListByPhoneNum(phoneNum);

		if (userConversationList != null && userConversationList.size() > 0) {
			UserConversation userConversation = userConversationList.get(0);
			userConversation.setConversationDate(new Date());
			userConversation.setDeviceNum(deviceNum);
			dao.update(userConversation);
		} else {
			UserConversation userConversation = new UserConversation();
			userConversation.setDeviceNum(deviceNum);
			userConversation.setConversationDate(new Date());
			userConversation.setPhoneNum(phoneNum);
			userConversation.preInsert();
			dao.insert(userConversation);
		}

	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		dao.delete(id);
	}
}
