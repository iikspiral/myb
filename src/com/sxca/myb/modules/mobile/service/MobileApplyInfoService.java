package com.sxca.myb.modules.mobile.service;

import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.cert.vo.CertVo;
import com.sxca.myb.modules.config.corporcode.entity.CorporationRequestCode;
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;
import com.sxca.myb.modules.idinfo.dao.CorporationUserInfoDao;
import com.sxca.myb.modules.idinfo.dao.UserInfoDao;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.mobile.dao.MobileApplyInfoDao;
import com.sxca.myb.modules.mobile.entity.MobileApplyInfo;
import com.sxca.myb.modules.pro.dao.ProjectInfoDao;
import com.sxca.myb.modules.pro.entity.ProjectInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author glq
 * @date : 2017-05-04
 */
@Service
public class MobileApplyInfoService extends CrudService<MobileApplyInfoDao, MobileApplyInfo> {
	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private CorporationUserInfoDao corporationUserInfoDao;

	@Autowired
	private ProjectInfoDao projectInfoDao;
	/**
	 * 新制和变更业务配置在白名单中,保存移动端申请信息
	 * @param certType  用户类型:个人用户userinfo,企业用户corporation_info
	 * @param deviceNum  设备号
	 * @param deviceType  设备类型
	 * @param userOrCorpWhitelist  白名单信息:个人用户WhiteList,企业用户CorporationRequestCode
     * @return
     */
	@Transactional(readOnly = false)
	public String saveMobileApplyInfo(String certType, String deviceNum, String deviceType,Object userOrCorpWhitelist,String certSubject,String applyBusinessId){
		MobileApplyInfo mobileApplyInfo = new MobileApplyInfo();
		mobileApplyInfo.setDeviceNum(deviceNum);
		mobileApplyInfo.setDeviceType(deviceType);
		mobileApplyInfo.setApplyBusinessId(applyBusinessId);
		mobileApplyInfo.setApplyResult("待申请");
		mobileApplyInfo.setErrorMessage("无");
		//个人用户
		if(ApplyConstants.USERTYPE_USERINFO.equals(certType)){
			//个人白名单信息
			WhiteList whiteList = (WhiteList)userOrCorpWhitelist;
			mobileApplyInfo.setProjectId(whiteList.getProjectInfoId());
			mobileApplyInfo.setUserId(whiteList.getUserInfoId());
			mobileApplyInfo.setUserType(ApplyConstants.USERTYPE_USERINFO);
			mobileApplyInfo.setApplyType(ApplyConstants.OPTTYPE_ADD);
			mobileApplyInfo.setPhoneNum(userInfoDao.get(whiteList.getUserInfoId()).getPhonenum());
			if (ApplyConstants.OPTTYPE_ALTER.equals(whiteList.getOptType())) {//变更
				mobileApplyInfo.setOldCertSn(whiteList.getCertSn());
			}
			if (ApplyConstants.OPTTYPE_ADD.equals(whiteList.getOptType())) {//新制
				mobileApplyInfo.setCertSubject(certSubject);
			}
		}
		//企业用户
		if(ApplyConstants.USERTYPE_CORPORATION_INFO.equals(certType)){
			//企业白名单信息
			CorporationRequestCode requestCode = (CorporationRequestCode)userOrCorpWhitelist;
			mobileApplyInfo.setProjectId(requestCode.getProjectId());
			mobileApplyInfo.setUserId(requestCode.getCorporationId());
			mobileApplyInfo.setUserType(ApplyConstants.USERTYPE_CORPORATION_INFO);
			mobileApplyInfo.setApplyType(ApplyConstants.OPTTYPE_ADD);
			mobileApplyInfo.setCorpUserId(requestCode.getCorporUserId());
			mobileApplyInfo.setPhoneNum(corporationUserInfoDao.get(requestCode.getCorporUserId()).getPhoneNum());
			if (ApplyConstants.OPTTYPE_ALTER.equals(requestCode.getType())) {//变更
				mobileApplyInfo.setOldCertSn(requestCode.getCertSn());
			}
			if (ApplyConstants.OPTTYPE_ADD.equals(requestCode.getType())) {//新制
				mobileApplyInfo.setCertSubject(certSubject);
			}
		}
		String id = super.insert1(mobileApplyInfo);
		return id;
	}
	/**
	 * 个人用户和企业用户在做其它操作(除新制,变更)保存移动端申请信息
	 * @param deviceNum 	设备号
	 * @param deviceType	设备类型
	 * @param optType		操作类型
	 * @param certVo		操作证书VO
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveMobileApplyInfo(String deviceNum, String deviceType, String optType, CertVo certVo,String cancelReason,String applyBusinessId,String applyResult){
		MobileApplyInfo mobileApplyInfo = new MobileApplyInfo();
		mobileApplyInfo.setDeviceNum(deviceNum);
		mobileApplyInfo.setDeviceType(deviceType);
		mobileApplyInfo.setApplyBusinessId(applyBusinessId);
		mobileApplyInfo.setApplyResult("待申请");
		mobileApplyInfo.setErrorMessage("无");
		mobileApplyInfo.setProjectId(certVo.getCertapplyInfo().getProjectId());
		mobileApplyInfo.setUserId(certVo.getCertapplyInfo().getUserInfoId());
		mobileApplyInfo.setApplyType(optType);
		mobileApplyInfo.setCertSubject(certVo.getCertInfo().getCertSubject());
		if(ApplyConstants.OPTTYPE_OTHER_REVOKE.equals(optType)) {
			mobileApplyInfo.setCancelReason(cancelReason);
		}
		mobileApplyInfo.setOldCertSn(certVo.getCertInfo().getCertSn());
		if (ApplyConstants.USERTYPE_USERINFO.equals(certVo.getCertInfo().getCertType())) {
			mobileApplyInfo.setUserType(ApplyConstants.USERTYPE_USERINFO);
			mobileApplyInfo.setPhoneNum(userInfoDao.get(certVo.getCertapplyInfo().getUserInfoId()).getPhonenum());
		}else{
			mobileApplyInfo.setUserType(ApplyConstants.USERTYPE_CORPORATION_INFO);
			mobileApplyInfo.setCorpUserId(certVo.getCertapplyInfo().getCorpUserId());
			mobileApplyInfo.setPhoneNum(corporationUserInfoDao.get(certVo.getCertapplyInfo().getCorpUserId()).getPhoneNum());
		}
		String id = super.insert1(mobileApplyInfo);
		return id;
	}
	/**
	 * 散户保存移动端申请信息
	 * @param deviceNum 	设备号
	 * @param deviceType	设备类型
	 * @param userInfo		散户用户信息
	 * @param optType		操作类型
	 * @param certSn		操作证书序列号
     * @return
     */
	@Transactional(readOnly = false)
	public String saveMobileApplyInfoOther(String deviceNum, String deviceType, UserInfo userInfo, String optType, String certSn,String certSubject,String applyBusinessId){
		MobileApplyInfo mobileApplyInfo = new MobileApplyInfo();
		mobileApplyInfo.setDeviceNum(deviceNum);
		mobileApplyInfo.setDeviceType(deviceType);
		mobileApplyInfo.setApplyBusinessId(applyBusinessId);
		mobileApplyInfo.setApplyResult("待申请");
		mobileApplyInfo.setErrorMessage("无");
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setIsDefaultProject(ApplyConstants.ISDEAFULT_PRO_YES);
		List<ProjectInfo> projectInfos = projectInfoDao.findList(projectInfo);
		mobileApplyInfo.setProjectId(projectInfos.get(0).getId());
		mobileApplyInfo.setUserId(userInfo.getId());
		mobileApplyInfo.setUserType(ApplyConstants.USERTYPE_USERINFO);
		mobileApplyInfo.setApplyType(optType);
		mobileApplyInfo.setPhoneNum(userInfo.getPhonenum());
		if ("0".equals(optType)) {//新制
			mobileApplyInfo.setCertSubject(certSubject);
		}else{
			mobileApplyInfo.setOldCertSn(certSn);
		}
		String id = super.insert1(mobileApplyInfo);
		return id;
	}
	@Transactional(readOnly = false)
	public String saveUpdateapplyEntiy(String deviceNum,String deviceType,String applyBusinessId,String optType,String subject,String oldCertSn,CertVo certVo){
		MobileApplyInfo mobileApplyInfo = new MobileApplyInfo();
		mobileApplyInfo.setDeviceType(deviceType);
		mobileApplyInfo.setDeviceNum(deviceNum);
		mobileApplyInfo.setApplyBusinessId(applyBusinessId);
		mobileApplyInfo.setApplyType(optType);
		mobileApplyInfo.setCertSubject(subject);
		mobileApplyInfo.setOldCertSn(oldCertSn);
		mobileApplyInfo.setApplyResult("待申请");
		mobileApplyInfo.setErrorMessage("无");
		
		CertapplyInfo certapplyInfo = certVo.getCertapplyInfo();
		mobileApplyInfo.setUserId(certapplyInfo.getUserInfoId());
		
		if ("userinfo".equals(certapplyInfo.getCertType())) {
			UserInfo userInfo = userInfoDao.get(certapplyInfo.getUserInfoId());
			mobileApplyInfo.setPhoneNum(userInfo.getPhonenum());
			mobileApplyInfo.setUserType("userinfo");
		} else if ("corporation_info".equals(certapplyInfo.getCertType())) {
			CorporationUserInfo corporationUserInfo = corporationUserInfoDao.get(certapplyInfo.getCorpUserId());
			mobileApplyInfo.setPhoneNum(corporationUserInfo.getPhoneNum());
			mobileApplyInfo.setCorpUserId(certapplyInfo.getCorpUserId());
			mobileApplyInfo.setUserType("corporation_info");
		}
		
		mobileApplyInfo.setProjectId(certapplyInfo.getProjectId());
		
		
		String id = super.insert1(mobileApplyInfo);
		return id;	
	}
	
	
	
	public List<MobileApplyInfo> findbyList(MobileApplyInfo mobileApplyInfo) {
		List<MobileApplyInfo> list = dao.findbyList(mobileApplyInfo);
		return list;
	}
	
	public String getApplyType(String certSn) {
		String applyType = null;
		MobileApplyInfo mobileApplyInfo = new MobileApplyInfo();
		mobileApplyInfo.setCertSn(certSn);
		List<MobileApplyInfo> list = dao.findbyOldCertSn(mobileApplyInfo);
		if(list != null && list.size() > 0) {
			MobileApplyInfo applyInfo = list.get(0);
			applyType = applyInfo.getApplyType();
		}
		return applyType;
	}
}
