package com.sxca.myb.modules.idinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.jit.pki.ra.user.response.UserInfoInsertResponse;
import cn.com.jit.pki.ra.user.response.UserInfoUpdateResponse;

import com.sxca.myb.common.BTK.btkinterface.BTK;
import com.sxca.myb.common.BTK.conversion.BussinessToBTKImpl;
import com.sxca.myb.common.BTK.entity.CertVOBTK;
import com.sxca.myb.common.BTK.entity.CorporationBTK;
import com.sxca.myb.common.BTK.entity.UserBTK;
import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.modules.idinfo.dao.CorporationInfoDao;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;

/**
 * @author lihuilong 
 * @date : 2017年4月12日 上午11:29:30
 */
@Service
@Transactional(readOnly = true)
public class CorporationInfoService  extends CrudService<CorporationInfoDao,CorporationInfo>{
	@Autowired
	private BTK btk;
	
	/**
	 * 用户同步保存到ra
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveAndRasave(CorporationInfo corporationInfo) {
		try {
			String message = "网络异常";
			String errCode = "000";
			if(StringUtils.isNotBlank(corporationInfo.getId())){
				int a = dao.update(corporationInfo);
				if(a == 1){
					return true;
				}else{
					return false;
				}
//				if(StringUtils.isNotBlank(corporationInfo.getRaUserId())){
//					//userInfo表转btk实体
//					BussinessToBTKImpl a = new BussinessToBTKImpl();
//					CorporationBTK  corporationBTK = (CorporationBTK) a.mapToEntityBTK(corporationInfo,new CorporationBTK());
//					corporationBTK.setCorpcountry("CN");
//					corporationBTK.setCorporationId(corporationInfo.getRaUserId());
//					CertVOBTK certVOBTK = new CertVOBTK();
//					certVOBTK.setCorporationBTK(corporationBTK);
//					UserInfoUpdateResponse userInfoUpdateResponse = btk.userInfoUpdate("corporation_info",certVOBTK);
//					//返回值
//					if(userInfoUpdateResponse != null && userInfoUpdateResponse.getUserId() != null){
//						corporationInfo.setRaUserId(userInfoUpdateResponse.getUserId());
//						dao.update(corporationInfo);
//						return true;
//					}else{
//						if (userInfoUpdateResponse != null) {
//							message = userInfoUpdateResponse.getMsg();
//							errCode = userInfoUpdateResponse.getErr();
//						}
//						logger.warn("企业用户：【{}】 企业用户更新失败！错误码：{}，错误原因：{}。",
//								corporationInfo.getCorpname(), errCode, message);
//						return false;
//					}
//				}else{
//					return false;
//				}
			}else{
				//userInfo表转btk实体
				BussinessToBTKImpl a = new BussinessToBTKImpl();
				CorporationBTK  corporationBTK = (CorporationBTK) a.mapToEntityBTK(corporationInfo,new CorporationBTK());
				corporationBTK.setCorpcountry("CN");
				CertVOBTK certVOBTK = new CertVOBTK();
				certVOBTK.setCorporationBTK(corporationBTK);
				UserInfoInsertResponse userInfoInsertResponse = btk.userInfoInsert("corporation_info",certVOBTK);
				//返回值
				if(userInfoInsertResponse != null && userInfoInsertResponse.getUserinfoId() != null){
					corporationInfo.setRaUserId(userInfoInsertResponse.getUserinfoId());
				}else{
					if (userInfoInsertResponse != null) {
						message = userInfoInsertResponse.getMsg();
						errCode = userInfoInsertResponse.getErr();
					}
					logger.warn("企业用户：【{}】 企业用户新增失败！错误码：{}，错误原因：{}。",
							corporationInfo.getCorpname(), errCode, message);
					return false;
				}
				corporationInfo.preInsert();
				dao.insert(corporationInfo);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<CorporationInfo> fingByids(List<String> result) {
		List<CorporationInfo> list = dao.fingByids(result);
		return list;
	}
	
	public List<CorporationInfo> findProUserlist(CorporationInfo corporationInfo){
		List<CorporationInfo> list = dao.findProUserlist(corporationInfo);
		return list;
	};
}
