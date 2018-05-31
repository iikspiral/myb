package com.sxca.myb.modules.cert.service;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.common.utils.PropertiesLoader;
import com.sxca.myb.modules.cert.dao.CertapplyBusinessDao;
import com.sxca.myb.modules.cert.entity.CertapplyBusinessInfo;
import com.sxca.myb.modules.cert.vo.CertVo;
import com.sxca.myb.modules.config.corporcode.entity.CorporationRequestCode;
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;
import com.sxca.myb.modules.idinfo.dao.CorporationInfoDao;
import com.sxca.myb.modules.idinfo.dao.CorporationUserInfoDao;
import com.sxca.myb.modules.idinfo.dao.UserInfoDao;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.pro.dao.ProjectInfoDao;
import com.sxca.myb.modules.pro.entity.ProjectInfo;

/**
 * @author ggw 
 * @date : 2017年5月25日 下午2:49:35
 */
@Service
@Transactional(readOnly = true)
public class CertapplyBusinessService extends CrudService<CertapplyBusinessDao,CertapplyBusinessInfo>{
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private CorporationUserInfoDao corporationUserInfoDao;
	
	@Autowired
	private ProjectInfoDao projectInfoDao;
	
	@Autowired
	private CorporationInfoDao corporationInfoDao;
	
	
	public List<CertapplyBusinessInfo>findbyList (CertapplyBusinessInfo certapplyBusinessInfo){
		List<CertapplyBusinessInfo> list =dao.findbyList(certapplyBusinessInfo);
		return list;
	}
	
	
	public String findByWhitListId(Object userOrCorpWhitelist,String certType,String certSubject) {
		
		CertapplyBusinessInfo serch = new CertapplyBusinessInfo();
		serch.setCertSubject(certSubject);
		serch.setApplyType(ApplyConstants.OPTTYPE_ADD);
		
		if(ApplyConstants.USERTYPE_USERINFO.equals(certType)){
			WhiteList whiteList = (WhiteList)userOrCorpWhitelist;
			serch.setProjectId(whiteList.getProjectInfoId());
		}
		if(ApplyConstants.USERTYPE_CORPORATION_INFO.equals(certType)){
			CorporationRequestCode requestCode = (CorporationRequestCode)userOrCorpWhitelist;
			serch.setProjectId(requestCode.getProjectId());
		}
		
		List<CertapplyBusinessInfo> certapplyBusinessInfos = dao.findList(serch);
		String applyResult = null;
		String id = null;
		if(certapplyBusinessInfos!=null&&certapplyBusinessInfos.size()>0) {
			applyResult = certapplyBusinessInfos.get(0).getApplyResult();
			if(ApplyConstants.BUSINESS_FAIL.equals(applyResult)) {
				id = certapplyBusinessInfos.get(0).getId();
			}
		}
		return id;
	}
	
	@Transactional(readOnly = false)
	public String saveCertapplyBusiness(String certType, String deviceNum, String deviceType,Object userOrCorpWhitelist,String certSubject) {
		CertapplyBusinessInfo certapplyBusinessInfo = new CertapplyBusinessInfo();
		certapplyBusinessInfo.setDeviceType(deviceType);
		certapplyBusinessInfo.setDeviceNum(deviceNum);
		certapplyBusinessInfo.setApplyResult(ApplyConstants.BUSINESS_FAIL);
		//个人用户
		if(ApplyConstants.USERTYPE_USERINFO.equals(certType)){
			//个人白名单信息
			WhiteList whiteList = (WhiteList)userOrCorpWhitelist;
			certapplyBusinessInfo.setProjectId(whiteList.getProjectInfoId());
			certapplyBusinessInfo.setUserId(whiteList.getUserInfoId());
			certapplyBusinessInfo.setUserType(ApplyConstants.USERTYPE_USERINFO);
			certapplyBusinessInfo.setApplyType(ApplyConstants.OPTTYPE_ADD);
			certapplyBusinessInfo.setPhoneNum(userInfoDao.get(whiteList.getUserInfoId()).getPhonenum());
			certapplyBusinessInfo.setCertSubject(certSubject);
		}
		//企业用户
		if(ApplyConstants.USERTYPE_CORPORATION_INFO.equals(certType)){
			//企业白名单信息
			CorporationRequestCode requestCode = (CorporationRequestCode)userOrCorpWhitelist;
			certapplyBusinessInfo.setProjectId(requestCode.getProjectId());
			certapplyBusinessInfo.setUserId(requestCode.getCorporationId());
			certapplyBusinessInfo.setUserType(ApplyConstants.USERTYPE_CORPORATION_INFO);
			certapplyBusinessInfo.setApplyType(ApplyConstants.OPTTYPE_ADD);
			certapplyBusinessInfo.setCorpUserId(requestCode.getCorporUserId());
			certapplyBusinessInfo.setPhoneNum(corporationUserInfoDao.get(requestCode.getCorporUserId()).getPhoneNum());
			certapplyBusinessInfo.setCertSubject(certSubject);
		}
		String id = super.insert1(certapplyBusinessInfo);
		return id;
		
	}
	
	public String findByWhitListIdOther(UserInfo userInfo,String certSubject) {
		CertapplyBusinessInfo serch = new CertapplyBusinessInfo();
		serch.setCertSubject(certSubject);
		serch.setApplyType(ApplyConstants.OPTTYPE_ADD);
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setIsDefaultProject(ApplyConstants.ISDEAFULT_PRO_YES);
		List<ProjectInfo> projectInfos = projectInfoDao.findList(projectInfo);
		serch.setProjectId(projectInfos.get(0).getId());
		List<CertapplyBusinessInfo> certapplyBusinessInfos = dao.findList(serch);
		String applyResult = null;
		String id = null;
		if(certapplyBusinessInfos!=null&&certapplyBusinessInfos.size()>0) {
			applyResult = certapplyBusinessInfos.get(0).getApplyResult();
			if(ApplyConstants.BUSINESS_FAIL.equals(applyResult)) {
				id = certapplyBusinessInfos.get(0).getId();
			}
		}
		return id;
	}
	
	@Transactional(readOnly = false)
	public String saveCertapplyBusinessOther(String deviceNum, String deviceType, UserInfo userInfo,String certSubject) {
		CertapplyBusinessInfo certapplyBusinessInfo = new CertapplyBusinessInfo();
		certapplyBusinessInfo.setDeviceType(deviceType);
		certapplyBusinessInfo.setDeviceNum(deviceNum);
		certapplyBusinessInfo.setApplyResult(ApplyConstants.BUSINESS_FAIL);
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setIsDefaultProject(ApplyConstants.ISDEAFULT_PRO_YES);
		List<ProjectInfo> projectInfos = projectInfoDao.findList(projectInfo);
		certapplyBusinessInfo.setProjectId(projectInfos.get(0).getId());
		certapplyBusinessInfo.setUserId(userInfo.getId());
		certapplyBusinessInfo.setUserType(ApplyConstants.USERTYPE_USERINFO);
		certapplyBusinessInfo.setApplyType(ApplyConstants.OPTTYPE_ADD);
		certapplyBusinessInfo.setPhoneNum(userInfo.getPhonenum());
		certapplyBusinessInfo.setCertSubject(certSubject);
		String id = super.insert1(certapplyBusinessInfo);
		return id;
	}
	
	public String findByCertSn(String certSn,String optType) {
		CertapplyBusinessInfo serch = new CertapplyBusinessInfo();
		serch.setCertSn(certSn);
		serch.setApplyType(optType);
		List<CertapplyBusinessInfo> certapplyBusinessInfos = dao.findList(serch);
		String applyResult = null;
		String id = null;
		if(certapplyBusinessInfos!=null&&certapplyBusinessInfos.size()>0) {
			applyResult = certapplyBusinessInfos.get(0).getApplyResult();
			if(ApplyConstants.BUSINESS_FAIL.equals(applyResult)) {
				id = certapplyBusinessInfos.get(0).getId();
			}
		}
		return id;
	}
	@Transactional(readOnly = false)
	public String saveCertapplyBusiness(String deviceNum, String deviceType, String optType, CertVo certVo) {
		
		CertapplyBusinessInfo certapplyBusinessInfo = new CertapplyBusinessInfo();
		certapplyBusinessInfo.setDeviceType(deviceType);
		certapplyBusinessInfo.setDeviceNum(deviceNum);
		certapplyBusinessInfo.setApplyResult(ApplyConstants.BUSINESS_FAIL);
		certapplyBusinessInfo.setProjectId(certVo.getCertapplyInfo().getProjectId());
		certapplyBusinessInfo.setUserId(certVo.getCertapplyInfo().getUserInfoId());
		certapplyBusinessInfo.setApplyType(optType);
		if (ApplyConstants.USERTYPE_USERINFO.equals(certVo.getCertInfo().getCertType())) {
			certapplyBusinessInfo.setUserType(ApplyConstants.USERTYPE_USERINFO);
			certapplyBusinessInfo.setPhoneNum(userInfoDao.get(certVo.getCertapplyInfo().getUserInfoId()).getPhonenum());
		}else{
			certapplyBusinessInfo.setUserType(ApplyConstants.USERTYPE_CORPORATION_INFO);
			certapplyBusinessInfo.setCorpUserId(certVo.getCertapplyInfo().getCorpUserId());
			certapplyBusinessInfo.setPhoneNum(corporationUserInfoDao.get(certVo.getCertapplyInfo().getCorpUserId()).getPhoneNum());
		}
		certapplyBusinessInfo.setCertSubject(certVo.getCertInfo().getCertSubject());
		certapplyBusinessInfo.setCertSn(certVo.getCertInfo().getCertSn());
		String id = super.insert1(certapplyBusinessInfo);
		return id;
	}
}
