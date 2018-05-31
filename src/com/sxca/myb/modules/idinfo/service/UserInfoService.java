package com.sxca.myb.modules.idinfo.service;

import java.util.List;

import com.sxca.myb.common.config.Global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.jit.pki.ra.user.response.UserInfoInsertResponse;
import cn.com.jit.pki.ra.user.response.UserInfoUpdateResponse;

import com.sxca.myb.common.BTK.btkinterface.BTK;
import com.sxca.myb.common.BTK.conversion.BussinessToBTKImpl;
import com.sxca.myb.common.BTK.entity.CertVOBTK;
import com.sxca.myb.common.BTK.entity.UserBTK;
import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.modules.idinfo.dao.UserInfoDao;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.mobile.dao.UserConversationDao;

/**
 * @author lihuilong 
 * @date : 2017年4月11日 上午11:23:33
 */
@Service
@Transactional(readOnly = true)
public class UserInfoService extends CrudService<UserInfoDao,UserInfo>{

	@Autowired
	private BTK btk;
	@Autowired
	private UserConversationDao userConversationDao;
	
	/**
	 * 用户同步保存到ra
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveAndRasave(UserInfo userInfo) {
		try {
			String message = "网络异常";
			String errCode = "000";
			if(StringUtils.isNotBlank(userInfo.getId())){
				int a = dao.update(userInfo);
				if(a == 1){
					return true;
				}else{
					return false;
				}
//				if(StringUtils.isNotBlank(userInfo.getRaUserId())){
//					//userInfo表转btk实体
//					BussinessToBTKImpl a = new BussinessToBTKImpl();
//					UserBTK  userBTK = (UserBTK) a.mapToEntityBTK(userInfo,new UserBTK());
//					userBTK.setOrganId(Global.getConfig("organId"));
//					userBTK.setCertType("userinfo");
//					userBTK.setUserInfoId(userInfo.getRaUserId());
//					userBTK.setUsercountry("CN");
//					CertVOBTK certVOBTK = new CertVOBTK();
//					certVOBTK.setUserBTK(userBTK);	
//					UserInfoUpdateResponse userInfoUpdateResponse = btk.userInfoUpdate("userinfo",certVOBTK);
//					//返回值
//					if(userInfoUpdateResponse != null && userInfoUpdateResponse.getUserId() != null){
//						userInfo.setRaUserId(userInfoUpdateResponse.getUserId());
//						
//						//判断手机号是否修改，如果修改，删除 user_conversation 表中，该用户的会话信息
//						UserInfo entity = dao.get(userInfo.getId());
//						if(!entity.getPhonenum().equals(userInfo.getPhonenum())){
//							userConversationDao.deleteByPhonenum(entity.getPhonenum());
//						}
//						
//						return true;
//					}else{
//						if (userInfoUpdateResponse != null) {
//							message = userInfoUpdateResponse.getMsg();
//							errCode = userInfoUpdateResponse.getErr();
//						}
//						logger.warn("个人用户：【{}】 个人用户更新失败！错误码：{}，错误原因：{}。",
//								userInfo.getUsername(), errCode, message);
//						return false;
//					}
//				}else{
//					return false;
//				}
			}else{
				//userInfo表转btk实体
				BussinessToBTKImpl a = new BussinessToBTKImpl();
				UserBTK  userBTK = (UserBTK) a.mapToEntityBTK(userInfo,new UserBTK());
				userBTK.setOrganId(Global.getConfig("organId"));
				userBTK.setCertType("userinfo");
				userBTK.setUsercountry("CN");
				CertVOBTK certVOBTK = new CertVOBTK();
				certVOBTK.setUserBTK(userBTK);	
				UserInfoInsertResponse userInfoInsertResponse = btk.userInfoInsert("userinfo",certVOBTK);
				//返回值
				if(userInfoInsertResponse != null && userInfoInsertResponse.getUserinfoId() != null){
					userInfo.setRaUserId(userInfoInsertResponse.getUserinfoId());
				}else{
					if (userInfoInsertResponse != null) {
						message = userInfoInsertResponse.getMsg();
						errCode = userInfoInsertResponse.getErr();
					}
					logger.warn("个人用户：【{}】 个人用户新增失败！错误码：{}，错误原因：{}。",
							userInfo.getUsername(), errCode, message);
					return false;
				}
				userInfo.preInsert();
				dao.insert(userInfo);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	/**author:Yophy.w 关联用户表和证书表方法
	 * @param certs
	 * @return
	 */
	public UserInfo findUserByCertSn (String certs){
		 return dao.findUserByCertSn(certs);
	 }
	
	/**author:Yophy.w 优化查询通过sql来完成后端迭代，提升效率
	 * @param certs
	 * @return
	 */
	public List<UserInfo> findUserids (List<String> userids){
		List<UserInfo> list = dao.fingByids(userids);
		 return list;
	 }
	
	
	/**author:Yophy.w
	 * @param corporationInfo
	 * @return
	 */
	public List<UserInfo> findProUserlist(UserInfo userInfo){
		List<UserInfo> list = dao.findProUserlist(userInfo);
		return list;
	};
}
