package com.sxca.myb.modules.config.corporcode.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.modules.idinfo.dao.CorporationInfoDao;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.mobile.util.GetCertSubjectUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.cert.dao.CertInfoDao;
import com.sxca.myb.modules.cert.dao.CertapplyInfoDao;
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.config.corporcode.dao.CorporcodeDao;
import com.sxca.myb.modules.config.corporcode.entity.CorporationRequestCode;
import com.sxca.myb.modules.config.corporcode.util.Utils;
import com.sxca.myb.modules.config.whitelist.dao.WhiteListDao;
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;
import com.sxca.myb.modules.idinfo.dao.CorporationnUserRelationDao;
import com.sxca.myb.modules.idinfo.entity.CorporationUserRelation;
import com.sxca.myb.modules.idinfo.service.CorporationUserInfoService;
import com.sxca.myb.modules.pro.dao.ProjectInfoDao;
import com.sxca.myb.modules.pro.entity.ProjectInfo;
import com.sxca.myb.modules.sys.entity.User;
import com.sxca.myb.modules.sys.utils.UserUtils;

/**
 * @author lihuilong
 * @date : 2017年4月14日 上午9:25:01
 */
@Service
@Transactional(readOnly = true)
public class CorporcodeService extends
		CrudService<CorporcodeDao, CorporationRequestCode> {

	@Autowired
	private CorporcodeDao corporcodeDao;

	@Autowired
	private CertapplyInfoDao certapplyInfoDao;

	@Autowired
	private CorporationnUserRelationDao corporationnUserRelationDao;

	@Autowired
	private ProjectInfoDao projectInfoDao;

	@Autowired
	private CorporationInfoDao corporationInfoDao;

	@Autowired
	private CertInfoDao certInfoDao;

	@Autowired
	private WhiteListDao whiteListDao;
	
	@Autowired
	private CorporationUserInfoService corporationUserInfoService;
	
	
	//注销删除
	public void revokeDelete(String certSn){
		//企业白名单
		CorporationRequestCode corporationRequestCode = new CorporationRequestCode();
		corporationRequestCode.setCertSn(certSn);
		corporationRequestCode.setStatus("0");
		List<CorporationRequestCode> corporList = dao.findList(corporationRequestCode);
		if(corporList != null && corporList.size() > 0){
			dao.delete(corporList.get(0).getId());
		}
		
		//个人白名单
		WhiteList whiteList = new WhiteList();
		whiteList.setCertSn(certSn);
		whiteList.setIsUsed("0");
		List<WhiteList> white = whiteListDao.findList(whiteList);
		if(white != null && white.size() > 0){
			dao.delete(white.get(0).getId());
		}
	}

	@Transactional(readOnly = false)
	public boolean saveCorpor(CorporationRequestCode entity) {
		// 获取项目id判断是否人脸识别
		ProjectInfo projectInfo = projectInfoDao.get(entity.getProjectId());
		// 判断是否为新制
		if ("0".equals(entity.getType())) {
			// 获取企业与企业用户中间表id
			String corporUserRelaId = entity.getCorporUserRelaId();
			List<String> result = Arrays.asList(corporUserRelaId.split(","));
			// 通过ids 来获取企业与企业用户中间表信息
			List<CorporationUserRelation> corPorUserRelalist = corporationnUserRelationDao
					.fingByids(result);

			if (corPorUserRelalist != null && corPorUserRelalist.size() > 0) {

				for (int i = 0; i < corPorUserRelalist.size(); i++) {
					CorporationUserRelation corporationUserRelation = corPorUserRelalist.get(i);

					CorporationInfo corporationInfo = corporationInfoDao.get(corporationUserRelation.getCorporationId());
					CorporationUserInfo corporationUserInfo = corporationUserInfoService.get(corporationUserRelation.getCorporationUserId());
					String rdmNum = corporationUserInfo.getRdmNum();
					
					CorporationRequestCode requestCode = new CorporationRequestCode();
					User user = UserUtils.getUser();
					requestCode.setStatus("0");
					requestCode.setOptTime(Integer.parseInt(Utils.date2String(new Date(), "yyyyMMdd")));
					requestCode.setApplicant(user.getName());
					requestCode.setCorporUserRelaId(corporationUserRelation.getId());// 中间表id
					requestCode.setCorporationId(corporationUserRelation.getCorporationId());// 企业id
					requestCode.setCorporUserId(corporationUserRelation.getCorporationUserId());// 企业用户id
					requestCode.setProjectId(entity.getProjectId());
					//根据项目信息和企业信息生成新制证书的证书主题
					String certSubject = GetCertSubjectUtil.getSubject(projectInfo, corporationInfo, ApplyConstants.USERTYPE_CORPORATION_INFO,rdmNum);
					requestCode.setCertSubject(certSubject);
					requestCode.setType("0");
					requestCode.preInsert();
					dao.insert(requestCode);
				}

			} else {
				return false;
			}
			// 判断是否为变更
		} else if ("1".equals(entity.getType())) {
			// 获取证书序列号集合
			String cerSn = entity.getCertSn();
			List<String> result = Arrays.asList(cerSn.split(","));
			for (int i = 0; i < result.size(); i++) {
				// 创建对象
				CorporationRequestCode requestCode = new CorporationRequestCode();
				User user = UserUtils.getUser();
				requestCode.setStatus("0");
				requestCode.setOptTime(Integer.parseInt(Utils.date2String(
						new Date(), "yyyyMMdd")));
				requestCode.setApplicant(user.getName());
				requestCode.setCertSn(result.get(i));
				// 判断是否为变更，如果为变更，需从申请信息表中查询相关用户id 插入到表中
				CertapplyInfo certapplyInfo = new CertapplyInfo();
				certapplyInfo.setCertSn(result.get(i));
				List<CertapplyInfo> certApplyList = certapplyInfoDao
						.findList(certapplyInfo);
				if (certApplyList != null && certApplyList.size() > 0) {
					requestCode.setCorporationId(certApplyList.get(0).getUserInfoId());
					requestCode.setCorporUserId(certApplyList.get(0).getCorpUserId());
					//获取企业用户
					CorporationInfo corporationInfo = corporationInfoDao.get(certApplyList.get(0).getUserInfoId());
					CorporationUserInfo corporationUserInfo = corporationUserInfoService.get(certApplyList.get(0).getCorpUserId());
					String rdmNum = corporationUserInfo.getRdmNum();
					//根据项目信息和企业信息生成 证书变更后的证书主题
					String certSubject = GetCertSubjectUtil.getSubject(projectInfo, corporationInfo, ApplyConstants.USERTYPE_CORPORATION_INFO,rdmNum);
					requestCode.setCertSubject(certSubject);
					// 判断项目是否为空，如果为空，去证书申请表中通过证书序列号查询项目id
					if (StringUtils.isBlank(entity.getProjectId())) {
						requestCode.setProjectId(certApplyList.get(0)
								.getProjectId());
					} else {
						requestCode.setProjectId(entity.getProjectId());
					}
				}
				requestCode.setType("1");
				requestCode.preInsert();
				dao.insert(requestCode);
			}
			return true;
		}
		return true;

	}

	// 获取已新制的ids
	public String fingCheckIds(String projectId) {

		List<CorporationRequestCode> list = dao.findUseCorpor(projectId);
		if (list != null && list.size() > 0) {
			String ids = "";
			for (int i = 0; i < list.size(); i++) {
				ids += list.get(i).getCorporUserRelaId() + ",";
			}
			return ids;
		} else {
			return null;
		}
	}

	// 获取已变更的certs
	public String fingCheckCerts() {
		CorporationRequestCode entity = new CorporationRequestCode();
		List<CorporationRequestCode> list = dao.findCertCorpor(entity);
		if (list != null && list.size() > 0) {
			String ids = "";
			for (int i = 0; i < list.size(); i++) {
				ids += list.get(i).getCertSn() + ",";
			}
			return ids;
		} else {
			return null;
		}
	}

	public List<CorporationRequestCode> getWaitcorporWhitelist(
			CorporationRequestCode entity) {
		return dao.getWaitcorporWhitelist(entity);
	}
	public List<CorporationRequestCode> getNotInvalidCorpcode(CorporationRequestCode entity){
			return dao.getNotInvalidCorpcode(entity);
	}
	public CorporationRequestCode getByCorUserId(String corUserId) {
		List<CorporationRequestCode> corporationRequestCodes = dao
				.getByCorUserId(corUserId);
		CorporationRequestCode corporationRequestCode = null;
		if (corporationRequestCodes != null
				&& corporationRequestCodes.size() > 0) {
			corporationRequestCode = corporationRequestCodes.get(0);
		}
		return corporationRequestCode;
	}

	// 获取企业证书 企业用户与企业用户中间表ids，通过正在使用的证书来进行获取
	public String getCorpuseridsByProid(String projectId) {
		
		List<CertInfo> certList = new ArrayList<CertInfo>();
		

		String ids = "";
		//查询使用中
		CertInfo entity = new CertInfo();

		entity.setCertType("corporation_info");
		CertapplyInfo certApplyInfo = new CertapplyInfo();
		certApplyInfo.setProjectId(projectId);
		entity.setCertapplyInfo(certApplyInfo);

		List<CertInfo> certInfoList = certInfoDao.findListBySP(entity);
		
		if(certInfoList != null && certInfoList.size() > 0){
			for(CertInfo certInfoOld : certInfoList){
				certList.add(certInfoOld);
			}
		}

		if (certList != null && certList.size() > 0) {
			for (CertInfo certInfo : certList) {
				// 通过企业id 跟 企业用户id 去企业与企业用户中间表中去查
				CorporationUserRelation corporationUserEntity = new CorporationUserRelation();

				corporationUserEntity.setCorporationId(certInfo
						.getCertapplyInfo().getUserInfoId());
				corporationUserEntity.setCorporationUserId(certInfo
						.getCertapplyInfo().getCorpUserId());

				CorporationUserRelation corporationUserRelation = corporationnUserRelationDao
						.getByCorUserIdAndCorId(corporationUserEntity);

				if (corporationUserRelation != null) {
					ids += corporationUserRelation.getId() + ",";
				}

			}
			if (StringUtils.isNotBlank(ids)) {
				return ids;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 白名单信息过期作废 lihuilong
	 * 
	 * @param userType
	 *            用户类型
	 * @param id
	 *            白名单id
	 */
	public void updateWhiteListstatus(String userType, String id) {
		if (StringUtils.isNotBlank(userType) && StringUtils.isNotBlank(id)) {
			if ("userinfo".equals(userType)) {
				WhiteList whiteList = whiteListDao.get(id);
				whiteList.setIsUsed("1");
				whiteListDao.update(whiteList);
			} else if ("corporation_info".equals(userType)) {
				CorporationRequestCode corporationRequestCode = dao.get(id);
				corporationRequestCode.setStatus("1");
				dao.update(corporationRequestCode);
			}
		}
	}
	
		public List<CertInfo> getStatus(String projectId,String corporationUserId) {
			
			CertInfo entity = new CertInfo();

			entity.setCertType("corporation_info");
			CertapplyInfo certApplyInfo = new CertapplyInfo();
			certApplyInfo.setProjectId(projectId);
			certApplyInfo.setCorpUserId(corporationUserId);
			entity.setCertapplyInfo(certApplyInfo);

			List<CertInfo> certInfoList = certInfoDao.findListBySP(entity);
			return certInfoList;
			
		}

}
