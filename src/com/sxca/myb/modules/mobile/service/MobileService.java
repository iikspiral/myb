package com.sxca.myb.modules.mobile.service;

import cn.com.jit.pki.ra.cert.response.addapply.CertRevokeApplyAddResponse;
import cn.com.jit.pki.ra.cert.response.addapply.CertUpdateApplyAddResponse;
import cn.com.jit.pki.ra.cert.response.auditapply.CertRevokeApplyAuditResponse;
import cn.com.jit.pki.ra.cert.response.auditapply.CertUpdateApplyAuditResponse;
import cn.com.jit.pki.ra.cert.response.certmake.CertMakeResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sxca.myb.common.BTK.btkinterface.BTK;
import com.sxca.myb.common.BTK.entity.CertApplyBTK;
import com.sxca.myb.common.BTK.entity.CertMakeBTK;
import com.sxca.myb.common.config.Global;
import com.sxca.myb.common.utils.DateUtil;
import com.sxca.myb.common.utils.DateUtils;
import com.sxca.myb.modules.cert.dao.CertInfoDao;
import com.sxca.myb.modules.cert.dao.CertapplyInfoDao;
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.entity.CertapplyBusinessInfo;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.cert.vo.CertVo;
import com.sxca.myb.modules.cert.vo.CertapplyVo;
import com.sxca.myb.modules.certCtml.dao.CertCtmlDao;
import com.sxca.myb.modules.certCtml.dao.CertCtmlSelfExtDao;
import com.sxca.myb.modules.certCtml.dao.CertCtmlStandardExtDao;
import com.sxca.myb.modules.certCtml.dao.CertSelfExtDao;
import com.sxca.myb.modules.certCtml.dao.CertStandardExtDao;
import com.sxca.myb.modules.certCtml.entity.*;

import cn.com.jit.pki.ra.cert.response.addapply.CertApplyAddResponse;
import cn.com.jit.pki.ra.cert.response.addapply.CertAuthCodeUpdateApplyAddResponse;
import cn.com.jit.pki.ra.cert.response.auditapply.CertApplyAuditResponse;
import cn.com.jit.pki.ra.cert.response.auditapply.CertAuthCodeUpdateApplyAuditResponse;
import cn.com.jit.pki.ra.cert.response.deleteapply.DeleteApplyResponse;
import cn.com.jit.pki.ra.cert.response.query.CertSingleQueryResponse;
import cn.com.jit.pki.ra.vo.RACertInfo;

import com.sxca.myb.common.BTK.conversion.BussinessToBTKImpl;
import com.sxca.myb.common.BTK.entity.*;
import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.modules.cert.service.CertInfoService;
import com.sxca.myb.modules.cert.service.CertapplyBusinessService;
import com.sxca.myb.modules.certCtml.service.CertCtmlService;
import com.sxca.myb.modules.certCtml.service.CertSelfExtService;
import com.sxca.myb.modules.certCtml.service.CertStandardExtService;
import com.sxca.myb.modules.config.corporcode.dao.CorporcodeDao;
import com.sxca.myb.modules.config.corporcode.entity.CorporationRequestCode;
import com.sxca.myb.modules.config.corporcode.service.CorporcodeService;
import com.sxca.myb.modules.config.facewhite.dao.FacewhiteDao;
import com.sxca.myb.modules.config.facewhite.entity.Facewhite;
import com.sxca.myb.modules.config.logman.entity.CertAppLog;
import com.sxca.myb.modules.config.logman.service.CertAppLogService;
import com.sxca.myb.modules.config.sysconfig.dao.SystemConfigDao;
import com.sxca.myb.modules.config.sysconfig.entity.SystemConfig;
import com.sxca.myb.modules.config.whitelist.dao.WhiteListDao;
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;
import com.sxca.myb.modules.idinfo.dao.CorporationInfoDao;
import com.sxca.myb.modules.idinfo.dao.CorporationUserInfoDao;
import com.sxca.myb.modules.idinfo.dao.CorporationnUserRelationDao;
import com.sxca.myb.modules.idinfo.dao.UserInfoDao;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.idinfo.service.UserInfoService;
import com.sxca.myb.modules.mobile.dao.FaceInfoDao;
import com.sxca.myb.modules.mobile.dao.MobileApplyInfoDao;
import com.sxca.myb.modules.mobile.dao.MobileVerifyDao;
import com.sxca.myb.modules.mobile.entity.MobileApplyInfo;
import com.sxca.myb.modules.mobile.entity.MobileVerify;
import com.sxca.myb.modules.mobile.entity.UserConversation;
import com.sxca.myb.modules.mobile.entity.CertMake;
import com.sxca.myb.modules.pro.dao.ProjectInfoDao;
import com.sxca.myb.modules.pro.entity.ProjectInfo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.modules.mobile.dao.MobileDao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * @author lihuilong
 * @date : 2017年4月24日 下午3:25:05
 */
@Service
public class MobileService implements MobileDao {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MobileVerifyDao mobileVerifyDao;

	@Autowired
	private WhiteListDao whiteListDao;

	@Autowired
	private CorporcodeDao corporcodeDao;

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private CorporationUserInfoDao corporationUserInfoDao;

	@Autowired
	private SystemConfigDao systemConfigDao;

	@Autowired
	private FacewhiteDao facewhiteDao;

	@Autowired
	private ProjectInfoDao projectInfoDao;

	@Autowired
	private CorporationInfoDao corporationInfoDao;

	@Autowired
	private CertInfoDao certInfoDao;

	@Autowired
	private CertapplyInfoDao certapplyInfoDao;

	@Autowired
	private CertSelfExtDao certSelfExtDao;

	@Autowired
	private CertStandardExtDao certStandardExtDao;

	@Autowired
	private BTK btk;

	@Autowired
	private CertCtmlDao certCtmlDao;

	@Autowired
	private MobileApplyInfoDao mobileApplyInfoDao;

	@Autowired
	private CertInfoService certInfoService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private CertCtmlService certCtmlService;

	@Autowired
	private FaceInfoDao faceInfoDao;

	@Autowired
	private CorporcodeService corporcodeService;
	
	@Autowired
	private CertapplyBusinessService certapplyBusinessService;
	
	@Autowired
	private CertCtmlSelfExtDao certCtmlSelfExtDao;
	
	@Autowired
	private CertCtmlStandardExtDao certCtmlStandardExtDao;
	
	@Autowired
	private CertAppLogService certAppLogService;
	
	@Autowired
	private CertSelfExtService certSelfExtService;
	
	@Autowired
	private CertStandardExtService certStandardExtService;
	
	@Autowired
	private UserConversationService userConversationService;
	
	@Autowired
	private CorporationnUserRelationDao corporationnUserRelationDao;

	/**
	 * service层验证验证码是否通过
	 * 
	 * glq 2017-04-27
	 * 
	 * @param phoneNum
	 * @param verify
	 * @return
	 */
	public boolean validateVerify(String phoneNum, String verify) {
		MobileVerify search = new MobileVerify();
		search.setPhoneNum(phoneNum);
		List<MobileVerify> list = mobileVerifyDao.findList(search);
		if (list == null || list.size() <= 0) {
			return false;
		}
		MobileVerify verifyCode = list.get(0);
		String realVerifyCode = verifyCode.getVerifyCode();
		Date sendDate = verifyCode.getSendDate();
		if (StringUtils.isNotBlank(verify)
				&& StringUtils.isNotBlank(realVerifyCode)
				&& verify.equals(realVerifyCode)) {
			Long realVerifyTime = sendDate.getTime() + 5 * 60 * 1000;
			if (realVerifyTime != null && realVerifyTime > new Date().getTime()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * lihuilong 身份验证接口（新制待申请）
	 * 
	 * @param certType
	 *            证书类型 userinfo 个人用户 corporation_info 企业用户
	 * @param idCard
	 *            身份证号码
	 * @param whiteListId
	 *            白名单id
	 * @param phoneNum
	 *            电话号码
	 * @return
	 */
	@Override
	public Map<String, Object> validateIdentityNew(String certType,
			String idCard, String whiteListId, String phoneNum) {
		Map<String, Object> map = Maps.newHashMap();
		// certType 个热用户 userinfo 企业用户 corporation_info
		if (certType.equals(ApplyConstants.USERTYPE_USERINFO)) {
			// 个人白名单中，通过whiteListId来进行查询
			WhiteList whiteList = whiteListDao.get(whiteListId);
			if (whiteList != null) {
				if (idCard.equals(whiteList.getUserInfo().getUseridcardnum())
						&& phoneNum.equals(whiteList.getUserInfo()
								.getPhonenum())) {
					map.put("flag", "0");
					map.put("message", "身份认证成功!");
					return map;
				} else {
					map.put("flag", "1");
					map.put("message", "身份认证失败!");
					return map;
				}
			} else {
				map.put("flag", "1");
				map.put("message", "身份认证失败!");
				return map;
			}
		} else if (certType.equals(ApplyConstants.USERTYPE_CORPORATION_INFO)) {
			// 企业白名单,通过whiteListId来进行查询
			CorporationRequestCode corporationRequestCode = corporcodeDao
					.getCorporuserByid(whiteListId);
			if (corporationRequestCode != null) {
				if (idCard.equals(corporationRequestCode
						.getCorporationUserInfo().getIdCard())
						&& phoneNum.equals(corporationRequestCode
								.getCorporationUserInfo().getPhoneNum())) {
					map.put("flag", "0");
					map.put("message", "身份认证成功!");
					return map;
				} else {
					map.put("flag", "1");
					map.put("message", "身份认证失败!");
					return map;
				}
			} else {
				map.put("flag", "1");
				map.put("message", "身份认证失败!");
				return map;
			}
		} else {
			map.put("flag", "1");
			map.put("message", "身份认证失败!");
			return map;
		}

	}

	/**
	 * 是否可申请证书接口 lihuilong
	 * 
	 * @param phoneNum
	 *            手机号码
	 * @param idCard
	 *            身份证号码
	 * @return
	 */
	@Override
	public Map<String, Object> getIsMobileApply(String name, String phoneNum,
			String idCard, String deviceNum) {
		Map<String, Object> map = Maps.newHashMap();
		boolean flag = false;
		// 先去系统配置，查看是否存在配置信息
		List<SystemConfig> systemConfig = systemConfigDao.findList(new SystemConfig());
		// 去个人用户 查询是否存在该人
		UserInfo userInfo = new UserInfo();
		userInfo.setUseridcardnum(idCard);
		List<UserInfo> userInfoList = userInfoDao.findList(userInfo);
		if (userInfoList.size() > 0 && userInfoDao != null) {
			if (name.equals(userInfoList.get(0).getUsername())) {
				flag = true;
			} else {
				map.put("flag", "1");
				map.put("message", "校验失败：姓名跟系统中名称不同,请联系管理员进行修改!");
				map.put("isTurn", "1");
				return map;
			}
		}
		// 企业用户表中查询是否存在该人
		CorporationUserInfo corporationUserInfo = new CorporationUserInfo();
		corporationUserInfo.setIdCard(idCard);
		List<CorporationUserInfo> corporUserinfoList = corporationUserInfoDao
				.findList(corporationUserInfo);
		if (corporUserinfoList != null && corporUserinfoList.size() > 0) {
			if (name.equals(corporUserinfoList.get(0).getUserName())) {
				flag = true;
			} else {
				map.put("flag", "1");
				map.put("isFace", "");
				map.put("message", "校验失败：姓名跟系统中名称不同,请联系管理员进行修改!");
				map.put("isTurn", "1");
				return map;
			}
		}
		// 如果从个人或者企业用户中能够查询出相关信息
		if (flag) {
			if (systemConfig != null && systemConfig.size() > 0) {
				SystemConfig sysEntity = systemConfig.get(0);
				// 去平台系统配置中查询是否开启人脸识别
				if ("1".equals(sysEntity.getIsFace())) {
					map.put("flag", "0");
					map.put("isFace", "1");
					map.put("message", "校验通过");
					map.put("isTurn", "1");
					// 如果用户存在或者公共项目开启且不用人脸识别保存会话信息或者更新会话信息。到会话信息查找，不存在保存，存在更新
					userConversationService.updateOrAddiserconversation(deviceNum, phoneNum);

					return map;
				} else {// 如果平台配置开启人脸识别，根据身份证号，去人脸白名单查下，看是否存在，如存在，不进行人脸
					Facewhite facewhite = new Facewhite();
					facewhite.setIdCardNum(idCard);
					facewhite.setIsEffect("0");
					List<Facewhite> faceWhitelist = facewhiteDao
							.findList(facewhite);
					if (faceWhitelist != null && faceWhitelist.size() > 0) {
						map.put("flag", "0");
						map.put("isFace", "1");
						map.put("message", "校验通过");
						map.put("isTurn", "1");

						// 如果用户存在或者公共项目开启且不用人脸识别保存会话信息或者更新会话信息。到会话信息查找，不存在保存，存在更新
						userConversationService.updateOrAddiserconversation(deviceNum, phoneNum);

						return map;
					} else {
						map.put("flag", "0");
						map.put("isFace", "0");
						map.put("message", "校验通过");
						map.put("isTurn", "1");
						return map;
					}
				}

			} else {
				map.put("flag", "1");
				map.put("isFace", "");
				//map.put("message", "平台系统配置未配置,验证失败!");
				map.put("message", "系统错误!");
				map.put("isTurn", "1");
				return map;
			}

		} else {
			if (systemConfig != null && systemConfig.size() > 0) {
				SystemConfig sysEntity = systemConfig.get(0);
				// 个人与企业用户都没找到相关信息，需要去系统配置去查下是否开启公共项目
				if ("0".equals(sysEntity.getIsuseDefaultProject())) {

					if ("1".equals(sysEntity.getIsFace())) {
						map.put("flag", "0");
						map.put("isFace", "1");
						map.put("message", "校验通过!");
						map.put("isTurn", "0");

						// 如果用户存在或者公共项目开启且不用人脸识别保存会话信息或者更新会话信息。到会话信息查找，不存在保存，存在更新
						userConversationService.updateOrAddiserconversation(deviceNum, phoneNum);

						return map;
					} else {// 如果平台配置开启人脸识别，根据身份证号，去人脸白名单查下，看是否存在，如存在，不进行人脸
						Facewhite facewhite = new Facewhite();
						facewhite.setIdCardNum(idCard);
						facewhite.setIsEffect("0");
						List<Facewhite> faceWhitelist = facewhiteDao
								.findList(facewhite);
						if (faceWhitelist != null && faceWhitelist.size() > 0) {
							map.put("flag", "0");
							map.put("isFace", "1");
							map.put("message", "校验通过!");
							map.put("isTurn", "0");

							// 如果用户存在或者公共项目开启且不用人脸识别保存会话信息或者更新会话信息。到会话信息查找，不存在保存，存在更新
							userConversationService.updateOrAddiserconversation(deviceNum, phoneNum);

							return map;
						} else {
							map.put("flag", "0");
							map.put("isFace", "0");
							map.put("message", "校验通过!");
							map.put("isTurn", "0");
							return map;
						}
					}

				} else {
					map.put("flag", "1");
					map.put("isFace", "");
					map.put("message", "您无权限使用,如有疑问请拨打电话联系"+sysEntity.getPhone()+"");
					map.put("isTurn", "1");
					return map;
				}

			} else {
				map.put("flag", "1");
				map.put("isFace", "");
				map.put("message", "平台系统配置未配置,验证失败!");
				map.put("isTurn", "1");
				return map;
			}
		}
	}


	/**
	 * lihuilong 获取当前用户待申请证书
	 * 
	 * @param phoneNum
	 *            手机号码
	 * @param idCard
	 *            身份证号码
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getWaitApplyCert(String phoneNum,
			String idCard) {
		List<Map<String, Object>> list = Lists.newArrayList();
		// 去个人白名单去查，手机号 idCard 去匹配查询
		WhiteList whiteList = new WhiteList();
		UserInfo userInfo = new UserInfo();
		userInfo.setPhonenum(phoneNum);
		userInfo.setUseridcardnum(idCard);
		whiteList.setUserInfo(userInfo);
		whiteList.setOptType("0");// 证书新制类型为0

		List<WhiteList> white = whiteListDao.findWaitwhiteList(whiteList);
		if (white != null && white.size() > 0) {//
			for (int i = 0; i < white.size(); i++) {
				WhiteList entity = white.get(i);
				ProjectInfo projectInfo = projectInfoDao.get(entity.getProjectInfoId());
				CertCtml certCtml = certCtmlDao.get(projectInfo.getCertTemplate());
				Map<String, Object> map = Maps.newHashMap();
				map.put("whiteId", entity.getId());
				map.put("certType", ApplyConstants.USERTYPE_USERINFO);
				map.put("subject",entity.getCertSubject());
				map.put("secretType",certCtml.getSecretType());
				if (StringUtils.isNotBlank((String) map.get("subject"))) {
					list.add(map);
				}
			}
		}
		// 去企业白名单中去查，手机号、身份证号去匹配
		CorporationRequestCode corporationRequestCode = new CorporationRequestCode();
		corporationRequestCode.setType("0");
		CorporationUserInfo corporationUserInfo = new CorporationUserInfo();
		corporationUserInfo.setPhoneNum(phoneNum);
		corporationUserInfo.setIdCard(idCard);
		corporationRequestCode.setCorporationUserInfo(corporationUserInfo);

		List<CorporationRequestCode> corporationList = corporcodeDao
				.getWaitcorporWhitelist(corporationRequestCode);

		if (corporationList != null && corporationList.size() > 0) {
			for (int j = 0; j < corporationList.size(); j++) {
				CorporationRequestCode corporationRequest = corporationList
						.get(j);
				ProjectInfo projectInfo = projectInfoDao.get(corporationRequest.getProjectId());
				CertCtml certCtml = certCtmlDao.get(projectInfo.getCertTemplate());
				Map<String, Object> map = Maps.newHashMap();
				map.put("whiteId", corporationRequest.getId());
				map.put("certType", ApplyConstants.USERTYPE_CORPORATION_INFO);
				map.put("subject",corporationRequest.getCertSubject());
				map.put("secretType",certCtml.getSecretType());
				if (StringUtils.isNotBlank((String) map.get("subject"))) {
					list.add(map);
				}
			}
		}
		return list;
	}


	/**
	 * 生成验证码
	 */
	public String generateVerifyCode() {
		boolean ishas = true;// 判断是否存在的变量
		Random rm = new Random(); // 生成随机数对象
		double pross = 0;
		Integer codelength = 7;
		String fixLenthString = "";
		String verifyCode = "";
		while (ishas) {
			pross = (1 + rm.nextDouble()) * Math.pow(10, codelength - 1);// 获取随机数
			fixLenthString = String.valueOf(pross);// 将随机数转换成字符串
			verifyCode = fixLenthString.substring(1, codelength);// 根据字符串生成
			List<MobileVerify> lists = mobileVerifyDao
					.unquieVerifyCode(verifyCode);
			if (lists != null && lists.size() > 0) {
				ishas = true;
			} else {
				ishas = false;
			}
		}
		return verifyCode;
	}

	/**
	 * 获取当前用户可变更证书 lihuilong
	 * 
	 * @param phoneNum
	 * @param idCard
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getChangeCert(String phoneNum,
			String idCard) {

		List<Map<String, Object>> list = Lists.newArrayList();

		//个人白名单查询------------开始
		WhiteList whiteList = new WhiteList();
		UserInfo userInfo = new UserInfo();
		userInfo.setPhonenum(phoneNum);
		userInfo.setUseridcardnum(idCard);
		whiteList.setOptType(ApplyConstants.OPTTYPE_ALTER);
		whiteList.setUserInfo(userInfo);

		List<WhiteList> white = whiteListDao.findWaitwhiteList(whiteList);
		if (white != null && white.size() > 0) {//
			for (int i = 0; i < white.size(); i++) {
				WhiteList entity = white.get(i);
				if (StringUtils.isNotBlank(entity.getCertSn())) {
					CertInfo certInfo = certInfoDao.get(entity.getCertSn());
					if (certInfo != null) {
						ProjectInfo projectInfo = projectInfoDao.get(entity.getProjectInfoId());
						CertCtml certCtml = certCtmlDao.get(projectInfo.getCertTemplate());
						Map<String, Object> map = Maps.newHashMap();
						map.put("certSn", certInfo.getCertSn());
						map.put("subject", entity.getCertSubject() );
						map.put("secretType",certCtml.getSecretType());
						list.add(map);
					}

				}
			}
		}
		//个人白名单查询------------结束

		//企业白名单查询------------开始
		CorporationRequestCode corporationRequestCode = new CorporationRequestCode();

		CorporationUserInfo corporationUserInfo = new CorporationUserInfo();
		corporationUserInfo.setPhoneNum(phoneNum);
		corporationUserInfo.setIdCard(idCard);

		corporationRequestCode.setType(ApplyConstants.OPTTYPE_ALTER);
		corporationRequestCode.setCorporationUserInfo(corporationUserInfo);

		List<CorporationRequestCode> corporationList = corporcodeDao
				.getWaitcorporWhitelist(corporationRequestCode);
		if (corporationList != null && corporationList.size() > 0) {
			for (int j = 0; j < corporationList.size(); j++) {
				CorporationRequestCode corporationRequest = corporationList
						.get(j);
				if (StringUtils.isNotBlank(corporationRequest.getCertSn())) {
					CertInfo certInfo = certInfoDao.get(corporationRequest
							.getCertSn());
					if (certInfo != null) {
						ProjectInfo projectInfo = projectInfoDao.get(corporationRequest.getProjectId());
						CertCtml certCtml = certCtmlDao.get(projectInfo.getCertTemplate());
						Map<String, Object> map = Maps.newHashMap();
						map.put("certSn", certInfo.getCertSn());
						map.put("subject", corporationRequest.getCertSubject());
						map.put("secretType",certCtml.getSecretType());
						list.add(map);
					}
				}
			}

		}
		//企业白名单查询------------结束
		return list;
	}

	/**
	 * 保存证书申请实体 lihuilong
	 * 
	 * @param certapplyVo
	 *            证书申请实体
	 * @param certId
	 *            证书id
	 * @param certSn
	 *            ra证书序列号
	 * @param reqSn
	 *            证书申请唯一标识
	 */
	public void saveCertapplyVo(CertapplyVo certapplyVo, String certId,
			String certSn, String reqSn) {

		CertapplyInfo certapplyInfo = certapplyVo.getCertapplyInfo();

		// 保存证书申请实体
		certapplyInfo.setCertId(certId);
		certapplyInfo.setCertSn(certSn);
		certapplyInfo.setReqSn(reqSn);
		certapplyInfo.setReqStatus("2");
		certapplyInfo.preInsert();
		certapplyInfoDao.insert(certapplyInfo);

		// 证书自定义扩展域信息
		List<CertSelfExt> selfList = certapplyVo.getCertSelfExtList();
		if (selfList != null && selfList.size() > 0) {
			for (CertSelfExt self : selfList) {
				self.setCertId(certId);
				self.setRegSn(reqSn);
				self.setCertSn(certSn);
				self.preInsert();
				certSelfExtDao.insert(self);
			}
		}

		// 证书标准扩展域信息
		List<CertStandardExt> standList = certapplyVo.getCertStandardExtList();
		if (standList != null && standList.size() > 0) {
			for (CertStandardExt ext : standList) {
				ext.setCertId(certId);
				ext.setReqSn(reqSn);
				ext.setCertSn(certSn);
				ext.preInsert();
				certStandardExtDao.insert(ext);
			}
		}

	}

	/**
	 * 证书跟新申请下载接口 lihuilong
	 * @param certVo
	 * @param mobileApplyInfoId
	 * @param applyBusinessId
	 * @return
	 * @throws ParseException
	 */
	@Override
	public Map<String, Object> updateMobileCert(CertVo certVo,String mobileApplyInfoId,String applyBusinessId,String isRecovery,CertInfo certInfoOld){
		// 获取移动端申请信息
		MobileApplyInfo mobileApplyInfo = mobileApplyInfoDao.get(mobileApplyInfoId);
		//证书申请实体
		CertapplyVo certapplyVo = new CertapplyVo();
		//证书申请对象
		CertapplyInfo certapplyInfo = certVo.getCertapplyInfo();
		//待跟新证书对象
		CertInfo certEntity = certVo.getCertInfo();
		
		if (certEntity != null && certapplyInfo != null) {
			// 获取项目id
			String projectId = certapplyInfo.getProjectId();
			
			if (StringUtils.isNotBlank(projectId)) {
				ProjectInfo projectInfo = projectInfoDao.get(projectId);
				boolean isRetainKey = false;
				if("0".equals(projectInfo.getIsHoldKey())){
					isRetainKey = true;
				}
					//证书申请对象
					String date = DateUtil.date2String(new Date(),"yyyyMMddHHmmss");
					certapplyInfo.setOptTime(new BigDecimal(Double.parseDouble(date + "000")));
					certapplyInfo.setId(null);
					certapplyInfo.setApplyBusinessId(applyBusinessId);
					certapplyInfo.setOptType(ApplyConstants.RA_OPTTYPE_UPDATE);
					certapplyInfo.setReqStatus("1");
					certapplyInfo.setDoubleCertSn(null);
					certapplyInfo.setOldCertSn(certEntity.getCertSn());
					certapplyInfo.setOldDoubleCertSn(certEntity.getDoubleCertSn());
					
					certapplyVo.setCertapplyInfo(certapplyInfo);

					// 证书自定义扩展域信息
					List<CertSelfExt> selfList = certVo.getCertSelfExtList();
					if (selfList != null && selfList.size() > 0) {
						for (CertSelfExt self : selfList) {
							self.setCertId(null);
							self.setRegSn(null);
							self.setCertSn(null);
							self.setId(null);
						}
					}
					certapplyVo.setCertSelfExtList(selfList);

					// 证书标准扩展域信息
					List<CertStandardExt> standList = certVo
							.getCertStandardExtList();
					if (standList != null && standList.size() > 0) {
						for (CertStandardExt ext : standList) {
							ext.setCertId(null);
							ext.setReqSn(null);
							ext.setCertSn(null);
							ext.setId(null);
						}
					}
					certapplyVo.setCertStandardExtList(standList);

					//BTK证书申请信息封装类------装载
					Integer certValidity = certUpdateValidity(certVo,certInfoOld);
					CertApplyBTK certApplyBTK = new CertApplyBTK(
							certEntity.getCertSn(),
							certEntity.getCertSubject(),
							certEntity.getCtmlName(), new BigDecimal(1),
							new BigDecimal(0), certValidity, isRetainKey);
					
					
					// btk接口 证书跟新申请
					CertUpdateApplyAddResponse response = btk.addCertUpdateOnlineApply(certApplyBTK);
					String message = "网络异常";
					String errCode = "000";
					if (response != null) {
						if (!response.getErr().equals("0")) {
							Map<String, Object> map = new HashMap<>();
							map.put("flag", "1");
							map.put("message", "证书更新申请失败！");

							mobileApplyInfo.setApplyResult("证书更新申请失败");
							mobileApplyInfo.setErrorMessage("错误码:"+response.getErr()+",错误原因:"+response.getMsg());
							mobileApplyInfoDao.update(mobileApplyInfo);
							logger.warn("证书更新：{} 证书更新申请失败！错误码：{}，错误原因：{}。",
									mobileApplyInfo.getCertSubject(),response.getErr(), response.getMsg());
							return map;
						}

						// 通过 项目id 取到模板id 通过模板id取到 模板 来判断模板是否是 自动审核
						String certTemplate = projectInfo.getCertTemplate();
						CertCtml certCtml = certCtmlDao.get(certTemplate);

						// Y为自动审核,N为手动审核
						if ("Y".equals(certCtml.getCertCtmlAudit())) {
							//证书跟新后，注销旧的证书实体，保存新的证书对象----------开始
							String newCertSn = saveUpdatecertVo(certEntity,response.getRefcode(),response.getReqSN(),certapplyVo,isRecovery);
							//证书跟新后，注销旧的证书实体，保存新的证书对象----------结束
							
							mobileApplyInfo.setApplyResult("申请、自动审核成功待下载");
							mobileApplyInfo.setErrorMessage("无");
							mobileApplyInfoDao.update(mobileApplyInfo);

							Map<String, Object> map = new HashMap<>();
							map.put("flag", "0");
							map.put("newCertSn", newCertSn);
							return map;
						} else if ("N".equals(certCtml.getCertCtmlAudit())) {
							// 手动审核 ，需要再去调用btk 手动审核---------开始
							CertUpdateApplyAuditResponse certUpdateApplyAuditResponse = btk.auditCertUpdateOnlineApply(response.getReqSN(), true, "");
							// 手动审核 ，需要再去调用btk 手动审核---------结束

							if (certUpdateApplyAuditResponse != null
									&& certUpdateApplyAuditResponse.getErr()
											.equals("0")) {

								//证书跟新后，注销旧的证书实体，保存新的证书对象----------开始
								String newCertSn = saveUpdatecertVo(certEntity,certUpdateApplyAuditResponse.getRefcode(),certUpdateApplyAuditResponse.getReqSN(),certapplyVo,isRecovery);
								//证书跟新后，注销旧的证书实体，保存新的证书对象----------结束
								
								mobileApplyInfo.setApplyResult("申请、自动审核成功待下载");
								mobileApplyInfo.setErrorMessage("无");
								mobileApplyInfoDao.update(mobileApplyInfo);
								
								Map<String, Object> map = new HashMap<>();
								map.put("flag", "0");
								map.put("newCertSn", newCertSn);
								return map;

							} else {
								Map<String, Object> map = new HashMap<>();
								map.put("flag", "1");
								map.put("message", "证书审核失败！");

								if (certUpdateApplyAuditResponse != null) {
									message = certUpdateApplyAuditResponse.getMsg();
									errCode = certUpdateApplyAuditResponse.getErr();
								}
								mobileApplyInfo.setApplyResult("证书审核失败！");
								mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
								mobileApplyInfoDao.update(mobileApplyInfo);
								logger.warn("证书更新：{} 证书申请审核失败！错误码：{}，错误原因：{}。",
										certapplyInfo.getCertSubject(),errCode, message);
								return map;
							}

						}

					} else {
						return null;
					}
			} else {
				Map<String, Object> map = new HashMap<>();
				map.put("flag", "1");
				map.put("message", "项目信息查询失败！");
				return map;
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("flag", "1");
			map.put("message", "证书相关信息查询失败！");
			return map;
		}
		return null;
	}
	
	
	/**
	 * 跟新证书信息 lihuilong
	 */
	public void updateCertinfoBycertsn(String certSn) {
		// 根据证书序列号去RA查询证书信息
		CertSingleQueryResponse certSingleQueryResponse = btk
				.certSingleQuery(certSn);

		CertInfo certInfo = certInfoDao.get(certSn);
		if (certSingleQueryResponse != null
				&& "0".equals(certSingleQueryResponse.getErr())) {
			RACertInfo raCertInfo = certSingleQueryResponse.getRaCertInfo();
			certInfo.setCertStatus(raCertInfo.getCertInfo().getCertStatus());
			certInfo.setNotBefore(new BigDecimal(raCertInfo.getCertInfo()
					.getNotBefore()));
			certInfo.setNotAfter(new BigDecimal(raCertInfo.getCertInfo()
					.getNotAfter()));
			certInfo.setCertValidity(raCertInfo.getCertInfo().getValidity());
			certInfo.setOptTime(new BigDecimal(raCertInfo.getOptTime()));
			certInfo.setCertStatusMyb("Undown");
			certInfoDao.update(certInfo);
		}

	}

	/**
	 * lihuilong
	 * 
	 * @param certSn
	 * @return
	 */
	public CertVo getcertVoBycertsn(String certSn) {

		CertVo certVo = new CertVo();
		// 获取证书信息
		CertInfo certInfo = certInfoDao.getByCertSn(certSn);

		certVo.setCertInfo(certInfo);

		// 获取证书自定义扩展域
		CertSelfExt certSelfExt = new CertSelfExt();
		certSelfExt.setCertSn(certSn);
		List<CertSelfExt> certSelfExtList = certSelfExtDao.findList(certSelfExt);
		if (certSelfExtList != null && certSelfExtList.size() > 0) {
			certVo.setCertSelfExtList(certSelfExtList);
		}
		// 获取证书标准扩展域
		CertStandardExt certStandardExt = new CertStandardExt();
		certStandardExt.setCertId(certSn);
		List<CertStandardExt> certStandardExtList = certStandardExtDao.findList(certStandardExt);
		if (certStandardExtList != null && certStandardExtList.size() > 0) {
			certVo.setCertStandardExtList(certStandardExtList);
		}

		// 获取最近证书申请信息
		CertapplyInfo certapplyInfo = new CertapplyInfo();
		certapplyInfo.setCertSn(certSn);
		List<CertapplyInfo> certApplyInfolist = certapplyInfoDao.findList(certapplyInfo);

		if (certApplyInfolist != null && certApplyInfolist.size() > 0) {
			CertapplyInfo certapply = certApplyInfolist.get(0);
			certVo.setCertapplyInfo(certapply);
		}

		return certVo;
	}

	/**
	 * 证书注销接口 lihuilong
	 * 
	 * @param certSn
	 *            证书序列号
	 * @param cancelReason
	 *            注销原因
	 * @param deviceType
	 *            设备类型
	 * @param deviceNum
	 *            设备号
	 * @return
	 */
	@Override
	public Map<String, Object> cancelMobileCert(CertVo certVo,String mobileApplyInfoId,String applyBusinessId,String cancelReason,String cancelDes) {
		String isRecovery = "0";
		
		// 获取移动端申请信息
		MobileApplyInfo mobileApplyInfo = mobileApplyInfoDao.get(mobileApplyInfoId);

		CertapplyBusinessInfo certapplyBusinessInfo = certapplyBusinessService.get(applyBusinessId);
		

		// 证书申请
		CertapplyInfo certapplyInfo = certVo.getCertapplyInfo();
		certapplyInfo.setId(null);
		certapplyInfo.setApplyBusinessId(applyBusinessId);
		certapplyInfo.setOptType(ApplyConstants.RA_OPTTYPE_REVOKE);
		certapplyInfo.setRevokeReason(cancelReason);
		certapplyInfo.setRevokeDesc(cancelDes);


		// 证书注销对应实体
		CertApplyBTK certApplyBTK = new CertApplyBTK();

		certApplyBTK.setCertSn(certVo.getCertInfo().getCertSn());
		certApplyBTK.setRevokeReason("0");
		certApplyBTK.setRevokeDesc(cancelReason);
		certApplyBTK.setNotNow(false);
		certApplyBTK.setDelayDays(0);

		// 证书注销申请 btk接口
		CertRevokeApplyAddResponse certRevokeApplyAddResponse = btk
				.certRevokeApplyAdd(certApplyBTK);
		String message = "网络异常";
		String errCode = "000";
		if (certRevokeApplyAddResponse != null
				&& certRevokeApplyAddResponse.getErr().equals("0")) {

			String reqSn = certRevokeApplyAddResponse.getReqSN();

			String projectId = certapplyInfo.getProjectId();

			ProjectInfo projectInfo = projectInfoDao.get(projectId);

			mobileApplyInfo.setProjectId(projectId);
			mobileApplyInfo.setProjectName(projectInfo.getProjectName());

			if (projectInfo != null) {
				// 通过 项目id 取到模板id 通过模板id取到 模板 来判断模板是否是 自动审核
				String certTemplate = projectInfo.getCertTemplate();
				CertCtml certCtml = certCtmlDao.get(certTemplate);

				if ("Y".equals(certCtml.getCertCtmlAudit())) {
					// 自动审核后保存证书申请信息
					certapplyInfo.setReqStatus("2");
					certapplyInfo.setReqSn(reqSn);
					certapplyInfo.preInsert();
					certapplyInfoDao.insert(certapplyInfo);
					// 关联修改相关证书实体状态
					CertInfo certInfo = certVo.getCertInfo();
					certInfo.setCertStatus("Revoke");
					certInfo.setCertStatusMyb("Revoke");
					certInfo.setIsRecovery(isRecovery);
					certInfoDao.update(certInfo);
					
					certapplyBusinessInfo.setApplyResult(ApplyConstants.BUSINESS_OK);
					certapplyBusinessService.update(certapplyBusinessInfo);
					
					Map<String, Object> map = new HashMap<>();
					map.put("flag", "0");
					map.put("message", "证书注销成功！");

					mobileApplyInfo.setApplyResult("证书注销成功！");
					mobileApplyInfo.setErrorMessage("无");
					mobileApplyInfo.setCertSn(certVo.getCertInfo().getCertSn());
					mobileApplyInfoDao.update(mobileApplyInfo);

					return map;
				} else if ("N".equals(certCtml.getCertCtmlAudit())) {
					// 需要手动审核
					certapplyInfo.setReqStatus("1");
					certapplyInfo.setReqSn(reqSn);
					certapplyInfo.preInsert();
					certapplyInfoDao.insert(certapplyInfo);

					// 调用注销审核接口
					CertRevokeApplyAuditResponse certRevokeApplyAuditResponse = btk
							.certRevokeApplyAudit(reqSn, true, null);

					if (certRevokeApplyAuditResponse != null
							&& certRevokeApplyAuditResponse.getErr()
									.equals("0")) {// 审核通过
						// 取到证书申请实体 修改对应状态
						CertapplyInfo certapplyInfoNew = certapplyInfoDao
								.get(certapplyInfo.getId());
						certapplyInfoNew.setReqStatus("2");
						certapplyInfoDao.update(certapplyInfoNew);

						// 关联修改相关证书实体状态
						CertInfo certInfo = certVo.getCertInfo();
						certInfo.setCertStatus("Revoke");
						certInfo.setCertStatusMyb("Revoke");
						certInfo.setIsRecovery(isRecovery);
						certInfoDao.update(certInfo);
						
						certapplyBusinessInfo.setApplyResult(ApplyConstants.BUSINESS_OK);
						certapplyBusinessService.update(certapplyBusinessInfo);

						Map<String, Object> map = new HashMap<>();
						map.put("flag", "0");
						map.put("message", "证书注销成功！");

						mobileApplyInfo.setApplyResult("证书注销成功！");
						mobileApplyInfo.setErrorMessage("无");
						mobileApplyInfo.setCertSn(certVo.getCertInfo().getCertSn());
						mobileApplyInfoDao.update(mobileApplyInfo);

						return map;

					} else {
						Map<String, Object> map = new HashMap<>();
						map.put("flag", "1");
						map.put("message", "注销审核失败！");

						if (certRevokeApplyAddResponse != null) {
							message = certRevokeApplyAddResponse.getMsg();
							errCode = certRevokeApplyAddResponse.getErr();
						}
						mobileApplyInfo.setApplyResult("注销审核失败！");
						mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
						mobileApplyInfoDao.update(mobileApplyInfo);
						logger.warn("证书注销：{} 注销审核失败！错误码：{}，错误原因：{}。",
								certapplyInfo.getCertSubject(),errCode, message);
						return map;
					}
				}
				return null;
			} else {
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("flag", "1");
				map.put("message", "项目未找到！");
				return map;
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("flag", "1");
			map.put("message", "证书注销申请失败！");
			if (certRevokeApplyAddResponse != null) {
				message = certRevokeApplyAddResponse.getMsg();
				errCode = certRevokeApplyAddResponse.getErr();
			}
			mobileApplyInfo.setApplyResult("证书注销申请失败！");
			mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
			mobileApplyInfoDao.update(mobileApplyInfo);
			logger.warn("证书注销：{} 证书注销申请失败！错误码：{}，错误原因：{}。",
					certapplyInfo.getCertSubject(),errCode, message);
			return map;
		}
	}
	
	
	/**
	 * 证书注销接口 ggw
	 * @return
	 */
	public Boolean cancelMobileCert(CertVo certVo,String applyBusinessId,String isRecovery,String mobileApplyInfoId) {

		Boolean result = false;
		MobileApplyInfo mobileApplyInfo = mobileApplyInfoDao.get(mobileApplyInfoId);
		// 证书申请
		CertapplyInfo certapplyInfo = certVo.getCertapplyInfo();
		certapplyInfo.setApplyBusinessId(applyBusinessId);
		certapplyInfo.setId(null);
		certapplyInfo.setOptType(ApplyConstants.RA_OPTTYPE_REVOKE);
		certapplyInfo.setRevokeReason("0");
		if("1".equals(isRecovery)) {
			certapplyInfo.setRevokeDesc("异常证书注销");
		}else {
			certapplyInfo.setRevokeDesc("变更证书注销");
		}
		

		// 证书注销对应实体
		CertApplyBTK certApplyBTK = new CertApplyBTK();

		certApplyBTK.setCertSn(certVo.getCertInfo().getCertSn());
		certApplyBTK.setRevokeReason("0");
		certApplyBTK.setRevokeDesc("");
		certApplyBTK.setNotNow(false);
		certApplyBTK.setDelayDays(0);

		// 证书注销申请 btk接口
		CertRevokeApplyAddResponse certRevokeApplyAddResponse = btk
				.certRevokeApplyAdd(certApplyBTK);
		String message = "网络异常";
		String errCode = "000";
		if (certRevokeApplyAddResponse != null
				&& certRevokeApplyAddResponse.getErr().equals("0")) {

			String reqSn = certRevokeApplyAddResponse.getReqSN();

			String projectId = certapplyInfo.getProjectId();

			ProjectInfo projectInfo = projectInfoDao.get(projectId);

			if (projectInfo != null) {
				// 通过 项目id 取到模板id 通过模板id取到 模板 来判断模板是否是 自动审核
				String certTemplate = projectInfo.getCertTemplate();
				CertCtml certCtml = certCtmlDao.get(certTemplate);

				if ("Y".equals(certCtml.getCertCtmlAudit())) {
					// 自动审核后保存证书申请信息
					certapplyInfo.setReqStatus("2");
					certapplyInfo.setReqSn(reqSn);
					certapplyInfo.preInsert();
					certapplyInfoDao.insert(certapplyInfo);
					// 关联修改相关证书实体状态
					CertInfo certInfo = certVo.getCertInfo();
					certInfo.setCertStatus("Revoke");
					certInfo.setCertStatusMyb("Revoke");
					certInfo.setIsRecovery(isRecovery);
					certInfoDao.update(certInfo);
					result = true;
				} else if ("N".equals(certCtml.getCertCtmlAudit())) {
					// 需要手动审核
					certapplyInfo.setReqStatus("1");
					certapplyInfo.setReqSn(reqSn);
					certapplyInfo.preInsert();
					certapplyInfoDao.insert(certapplyInfo);

					// 调用注销审核接口
					CertRevokeApplyAuditResponse certRevokeApplyAuditResponse = btk
							.certRevokeApplyAudit(reqSn, true, null);

					if (certRevokeApplyAuditResponse != null
							&& certRevokeApplyAuditResponse.getErr()
									.equals("0")) {// 审核通过
						// 取到证书申请实体 修改对应状态
						CertapplyInfo certapplyInfoNew = certapplyInfoDao
								.get(certapplyInfo.getId());
						certapplyInfoNew.setReqStatus("2");
						certapplyInfoDao.update(certapplyInfoNew);

						// 关联修改相关证书实体状态
						CertInfo certInfo = certVo.getCertInfo();
						certInfo.setCertStatus("Revoke");
						certInfo.setCertStatusMyb("Revoke");
						certInfo.setIsRecovery(isRecovery);
						certInfoDao.update(certInfo);
						result = true;
					} else {
						if (certRevokeApplyAuditResponse != null) {
							message = certRevokeApplyAuditResponse.getMsg();
							errCode = certRevokeApplyAuditResponse.getErr();
						}
						if("1".equals(isRecovery)) {
							mobileApplyInfo.setApplyResult("异常证书注销失败!");
						}else {
							mobileApplyInfo.setApplyResult("变更证书注销失败!");
						}
						mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
						mobileApplyInfoDao.update(mobileApplyInfo);
						logger.warn("证书注销：{} 证书注销审核失败！错误码：{}，错误原因：{}。",
								certapplyInfo.getCertSubject(),errCode, message);
					}
				}
			} 
		}else {
			if (certRevokeApplyAddResponse != null) {
				message = certRevokeApplyAddResponse.getMsg();
				errCode = certRevokeApplyAddResponse.getErr();
			}
			if("1".equals(isRecovery)) {
				mobileApplyInfo.setApplyResult("异常证书注销失败!");
			}else {
				mobileApplyInfo.setApplyResult("变更证书注销失败!");
			}
			mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
			mobileApplyInfoDao.update(mobileApplyInfo);
			logger.warn("证书注销：{} 证书注销申请失败！错误码：{}，错误原因：{}。",
					certapplyInfo.getCertSubject(),errCode, message);
		}
		return result;
	}

	/**
	 * service层验证用户身份信息（其他） glq 2017-04-27
	 * 
	 * @param idCard
	 * @param certSn
	 * @param phoneNum
	 * @return
	 */
	public boolean validateIdentity(String idCard, String certSn,
			String phoneNum) {
		CertInfo certinfo = certInfoDao.getByCertSn(certSn); // 根据证书序列号获取证书信息
		String certType = certinfo.getCertType(); // 证书类型
													// 个人用户userinfo企业用户corporation_info
		if (StringUtils.isNotBlank(certType)) {
			if (ApplyConstants.USERTYPE_USERINFO.equals(certType)) { // 个人用户
				// 根据RA用户Id查询用户
				UserInfo userInfo = new UserInfo();
				userInfo.setRaUserId(certinfo.getUserinfoId());
				List<UserInfo> userInfos = userInfoDao.findList(userInfo);
				if (userInfos != null && userInfos.size() > 0) {
					if (idCard.equals(userInfos.get(0).getUseridcardnum())
							&& phoneNum.equals(userInfos.get(0).getPhonenum())) {
						return true;
					}
				}
			} else if (ApplyConstants.USERTYPE_CORPORATION_INFO.equals(certType)) {
				CertapplyInfo certapplyInfo = new CertapplyInfo();
				certapplyInfo.setCertType(certType);
				certapplyInfo.setCertSn(certSn);
				List<CertapplyInfo> applyInfoList = certapplyInfoDao.findList(certapplyInfo);
				if (applyInfoList != null && applyInfoList.size() > 0) {
					if (StringUtils.isNotBlank(applyInfoList.get(0).getCorpUserId())) {
						String isAdmin = corporationnUserRelationDao.getIsAdmin(applyInfoList.get(0).getUserInfoId(), applyInfoList.get(0).getCorpUserId());
						String adminCorpUserId = null;
						if("0".equals(isAdmin)) {
							adminCorpUserId = applyInfoList.get(0).getCorpUserId();
						}else {
							adminCorpUserId = corporationnUserRelationDao.getAdminCorpUserId(applyInfoList.get(0).getUserInfoId());
						}
						CorporationUserInfo corporationUserInfo = corporationUserInfoDao.get(adminCorpUserId);
						if (idCard.equals(corporationUserInfo.getIdCard())&& phoneNum.equals(corporationUserInfo.getPhoneNum())) {
							return true;
						}
						
					}
				}
			}
		}
		return false;
	}

	/**
	 * 获取当前用户已有证书 glq 2017-05-02
	 * 
	 * @param phoneNum
	 * @param idCard
	 * @return
	 */
	public List<Map<String, String>> getCert(String phoneNum, String idCard) {
		List<String> certSnList = new ArrayList<>();
		List<Map<String, String>> certInfoList = new ArrayList<>();
		// 根据信息获取个人用户
		UserInfo userInfo = new UserInfo();
		userInfo.setPhonenum(phoneNum);
		userInfo.setUseridcardnum(idCard);
		List<UserInfo> userInfos = userInfoDao.findList(userInfo);
		if (userInfos != null && userInfos.size() > 0) {
			CertapplyInfo certapplyInfo = new CertapplyInfo();
			certapplyInfo.setUserInfoId(userInfos.get(0).getId());
			List<CertapplyInfo> certapplyInfos = certapplyInfoDao
					.findList(certapplyInfo);
			if (certapplyInfos != null && certapplyInfos.size() > 0) {
				for (int i = 0; i < certapplyInfos.size(); i++) {
					if (StringUtils.isBlank(certapplyInfos.get(i).getCertSn())) {
						continue;
					}
					boolean isExist = false;
					if (certSnList != null && certSnList.size() > 0) {
						for(int j = 0; j < certSnList.size(); j++) {
							if (certapplyInfos.get(i).getCertSn().equals(certSnList.get(j))) {
								isExist = true;
								break;
							}
						}
					}
					if (!isExist) {
						certSnList.add(certapplyInfos.get(i).getCertSn());
					}
				}
			}
		}

		// 根据信息获取企业用户
		CorporationUserInfo corporationUserInfo = new CorporationUserInfo();
		corporationUserInfo.setIdCard(idCard);
		corporationUserInfo.setPhoneNum(phoneNum);
		List<CorporationUserInfo> corporationUserInfos = corporationUserInfoDao
				.findList(corporationUserInfo);
		if (corporationUserInfos != null && corporationUserInfos.size() > 0) {
			CertapplyInfo certapplyInfo = new CertapplyInfo();
			certapplyInfo.setCorpUserId(corporationUserInfos.get(0).getId());
			List<CertapplyInfo> certapplyInfos = certapplyInfoDao
					.findList(certapplyInfo);
			if (certapplyInfos != null && certapplyInfos.size() > 0) {
				for (int i = 0; i < certapplyInfos.size(); i++) {
					if (StringUtils.isBlank(certapplyInfos.get(i).getCertSn())) {
						continue;
					}
					boolean isExist = false;
					if (certSnList != null && certSnList.size() > 0) {
						for(int j = 0; j < certSnList.size(); j++) {
							if (certapplyInfos.get(i).getCertSn().equals(certSnList.get(j))) {
								isExist = true;
								break;
							}
						}
					}
					if (!isExist) {
						certSnList.add(certapplyInfos.get(i).getCertSn());
					}
				}
			}
		} 
		// 根据证书序列号获取使用中的证书
		if(certSnList.size() > 0){
			List<CertInfo> certinfoLists = certInfoDao.fingByCertSn(certSnList);
			if (certinfoLists != null && certinfoLists.size() > 0) {
				for (CertInfo cert : certinfoLists) {
					if ("Revoke".equals(cert.getCertStatus())
							|| "UndownRevoke".equals(cert.getCertStatus())) {// 证书状态是"注销"或者是"未下载注销"则继续
						continue;
					} else {// 证书状态是"未下载"、"使用中"、"冻结" 则进行RA数据同步
						cert = certInfoService.createOrUpdateCert(cert.getCertSn(),cert.getIsRecovery());
						if (!"Use".equals(cert.getCertStatus())&&!"Undown".equals(cert.getCertStatus())) {
							continue;
						}
						Map<String, String> certinfoMap = new HashMap<>();
						CertCtml certCtml = new CertCtml();
						certCtml.setCertCtmlName(cert.getCtmlName());
						String secretType = null;
						List<CertCtml> certCtmls= certCtmlDao.findList(certCtml);
						if(certCtmls!=null&&certCtmls.size()>0) {
							secretType = certCtmls.get(0).getSecretType();
						}
						certinfoMap.put("certSn", cert.getCertSn());
						certinfoMap.put("certSubject", cert.getCertSubject());
						certinfoMap.put("notBefore", cert.getNotBefore().toString());
						certinfoMap.put("notAfter", cert.getNotAfter().toString());
						certinfoMap.put("certType", cert.getCertType());
						certinfoMap.put("secretType", secretType);
						certInfoList.add(certinfoMap);
					}
				}
			}
		}
		return certInfoList;

	}

	/**
	 * 通过企业id获取企业所有证书
	 * 
	 * @param corIds
	 *            企业id集合
	 * @return
	 */
	public List<Map<String, String>> getCertByCorIds(List<String> corIds) {
		// 根据RA用户ID获取使用中的证书
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<CertInfo> certinfoLists = certInfoDao.getCertByCorIds(corIds);
		if (certinfoLists != null && certinfoLists.size() > 0) {
			for (CertInfo cert : certinfoLists) {

				// 去RA查询证书信息,比对证书状态是否一致,不一致的话进行更新(状态同步)
				CertSingleQueryResponse certSingleQueryResponse = btk
						.certSingleQuery(cert.getCertSn());
				RACertInfo raCertInfo = certSingleQueryResponse.getRaCertInfo();
				if (!raCertInfo.getCertInfo().getCertStatus()
						.equals(cert.getCertStatus())) {
					cert.setCertStatus(raCertInfo.getCertInfo().getCertStatus());
					cert.setCertValidity(raCertInfo.getCertInfo().getValidity());
					cert.setNotBefore(new BigDecimal(raCertInfo.getCertInfo()
							.getNotBefore()));
					cert.setNotAfter(new BigDecimal(raCertInfo.getCertInfo()
							.getNotAfter()));
					certInfoDao.update(cert);
				}
				if (!"Use".equals(cert.getCertStatus())) {
					continue;
				}
				Map<String, String> certinfoMap = new HashMap<>();
				//certinfoMap.put(cert.getCertSn(), cert.getCertSubject());

				CertCtml certCtml = new CertCtml();
				certCtml.setCertCtmlName(cert.getCertTemplate());
				String secretType = null;
				List<CertCtml> certCtmls= certCtmlDao.findList(certCtml);
				if(certCtmls!=null&&certCtmls.size()>0) {
					secretType = certCtmls.get(0).getSecretType();
				}
				certinfoMap.put("secretType", secretType);
				certinfoMap.put("certSn", cert.getCertSn());
				certinfoMap.put("certSubject", cert.getCertSubject());
				certinfoMap.put("notBefore", cert.getNotBefore().toString());
				certinfoMap.put("notAfter", cert.getNotAfter().toString());
				
				list.add(certinfoMap);
			}
		}
		return list;

	}

	/**
	 * 移动端进行证书申请 glq 2017-05-04
	 * 
	 * @param certType
	 *            证书类型:个人用户userinfo,企业用户corporation_info
	 * @param userOrCorpWhitelist
	 *            白名单信息:正常个人用户WhiteList,散户:UserInfo,企业用户CorporationRequestCode
	 * @param userType
	 *            个人用户用户类型 正常:0, 散户:1
	 * @param mobileApplyInfoId
	 *            移动端申请信息Id
	 * @return
	 */
	public String mobileAddApply(String certType, Object userOrCorpWhitelist,String userType, String mobileApplyInfoId, String deviceType,String deviceNum,String applyBusinessId) {
		
		Boolean result = true;
		String certSn = null;
		String isRecovery = "0";
		
		CertapplyBusinessInfo certapplyBusinessInfo = certapplyBusinessService.get(applyBusinessId);
		MobileApplyInfo mobileApplyInfo = mobileApplyInfoDao.get(mobileApplyInfoId);

		BussinessToBTKImpl bussToBtk = new BussinessToBTKImpl();
		CertapplyVo certapplyVo = new CertapplyVo();
		CertVOBTK certVOBTK = new CertVOBTK();
		
		CertapplyInfo certapplyInfo = new CertapplyInfo();
		CertInfo certInfoNodown = new CertInfo();
		
		if (ApplyConstants.USERINFO_NORMAL.equals(userType)) {
			// 获取证书未下载状态下的最新的一条证书信息
			certInfoNodown = getNodownCertinfo(mobileApplyInfo.getCertSubject(),userOrCorpWhitelist,certType);
			
		}else{
			certInfoNodown = getSanhuNodownCertinfo(mobileApplyInfo.getCertSubject());
		}
		
		// 如果不为空，需要注销原有证书
		if (certInfoNodown != null) {
			isRecovery = "1";
			CertVo certVo = getcertVoBycertsn(certInfoNodown.getCertSn());
			result = cancelMobileCert(certVo,applyBusinessId,isRecovery,mobileApplyInfoId);
		}
		
		if(result) {
			String projectId = null;
			String userInfoId = null;
			UserInfo userinfo = null;
			CorporationRequestCode requestCode = null;
			// 个人用户
			if (ApplyConstants.USERTYPE_USERINFO.equals(certType)) {
				if (ApplyConstants.USERINFO_NORMAL.equals(userType)) {// 正常个人用户
					WhiteList whiteList = (WhiteList) userOrCorpWhitelist;
					projectId = whiteList.getProjectInfoId();
					userInfoId = whiteList.getUserInfoId();
					// 封装证书申请个人用户信息
					userinfo = userInfoDao.get(userInfoId);
				} else {// 散户
					userinfo = (UserInfo) userOrCorpWhitelist;
					userInfoId = userinfo.getId();
					ProjectInfo projectInfo = new ProjectInfo();
					projectInfo.setIsDefaultProject(ApplyConstants.ISDEAFULT_PRO_YES);
					List<ProjectInfo> projectInfos = projectInfoDao.findList(projectInfo);
					projectId = projectInfos.get(0).getId();
				}
				// 封装证书申请个人用户信息
				UserBTK userBTK = (UserBTK) bussToBtk.mapToEntityBTK(userinfo,new UserBTK());
				certVOBTK.setUserBTK(userBTK);
				// 封装证书申请信息
				certapplyInfo.setCertType(ApplyConstants.USERTYPE_USERINFO);
				certapplyInfo.setUserInfoId(userinfo.getRaUserId());
			}

			// 企业用户
			if (ApplyConstants.USERTYPE_CORPORATION_INFO.equals(certType)) {
				requestCode = (CorporationRequestCode) userOrCorpWhitelist;
				projectId = requestCode.getProjectId();
				userInfoId = requestCode.getCorporationId();
				// 封装证书申请企业用户信息
				CorporationInfo corporationInfo = corporationInfoDao.get(userInfoId);
				CorporationBTK corporationBTK = (CorporationBTK) bussToBtk.mapToEntityBTK(corporationInfo, new CorporationBTK());
				corporationBTK.setCorpcountry("CN");
				certVOBTK.setCorporationBTK(corporationBTK);
				// 封装证书申请信息
				certapplyInfo.setCertType(ApplyConstants.USERTYPE_CORPORATION_INFO);
				certapplyInfo.setUserInfoId(corporationInfo.getRaUserId());
				certapplyInfo.setCorpUserId(requestCode.getCorporUserId());
			}

			// 封装证书申请信息
			ProjectInfo projectInfo = projectInfoDao.get(projectId);
			CertCtml certCtml = certCtmlDao.get(projectInfo.getCertTemplate());
			certapplyInfo.setApplyBusinessId(applyBusinessId);
			certapplyInfo.setCtmlName(certCtml.getCertCtmlName());
			certapplyInfo.setCertValidity(projectInfo.getCertValidity());
			certapplyInfo.setCertSubject(mobileApplyInfo.getCertSubject());
			certapplyInfo.setProjectId(projectId);
			certapplyInfo.setIsAdmin("1");
			certapplyInfo.setOptType(ApplyConstants.RA_OPTTYPE_ADD);
			certapplyInfo.setNotBefore(new BigDecimal(1));
			certapplyInfo.setNotAfter(new BigDecimal(0));
			certapplyInfo.setOrganId(Global.getConfig("organId"));
			certapplyInfo.setSubjectUppercase(mobileApplyInfo.getCertSubject().toUpperCase());
			if (certapplyInfo.getCertSubject().indexOf("CN=") != -1) {
				String certsubject = certapplyInfo.getCertSubject();
				String subname = certsubject.substring(certsubject.indexOf("CN=") + 3);
				String commName = subname.substring(0, subname.indexOf(","));
				certapplyInfo.setCommonName(commName);
			} else {
				certapplyInfo.setCommonName(certapplyInfo.getCertSubject());
			}
			certapplyInfo.setApplicant(mobileApplyInfo.getCertSubject());
			certapplyInfo.setApplicantUppercase(mobileApplyInfo.getCertSubject().toUpperCase());
			String date = DateUtils.formatPayTime(new Date());
			certapplyInfo.setOptTime(new BigDecimal(Double.parseDouble(date + "000")));
			certapplyVo.setCertapplyInfo(certapplyInfo);

			// 封装证书申请扩展域信息
			certapplyVo = sealedExtList(projectId, certType, userInfoId,certapplyVo);

			// 证书申请信息业务实体转换为BTK实体
			CertApplyBTK certApplyBTK = (CertApplyBTK) bussToBtk.mapToEntityBTK(certapplyInfo, new CertApplyBTK());
			certVOBTK.setCertApplyBTK(certApplyBTK);
			
			// 证书申请证书自定义扩展域实体转换为BTK实体
			List<CertSelfExtBTK> certSelfExtBTKs = certSelfextChange(certapplyVo);
			certVOBTK.setCertSelfExtBTKList(certSelfExtBTKs);
			
			// 证书申请证书标准扩展域实体转换为BTK实体
			List<CertStandardExtBTK> certStandardExtBTKs = certStandextChange(certapplyVo);
			certVOBTK.setCertStandardExtBTKList(certStandardExtBTKs);
			
			// 证书申请
			CertApplyAddResponse certApplyAddResponse = btk.certApplyAdd(certVOBTK);
			String message = "网络异常";
			String errCode = "000";
			
			if (certApplyAddResponse != null&& "0".equals(certApplyAddResponse.getErr())) {// 证书申请成功
				
				if ("Y".equals(certCtml.getCertCtmlAudit())) {// 如果模板是自动审核,则审核完成后进行证书申请信息与证书信息的保存,并更新移动端申请信息
					
					certapplyInfo.setUserInfoId(userInfoId);
					// 保存证书申请信息
					saveCertapplyVo(certapplyVo, "",certApplyAddResponse.getRefcode(),certApplyAddResponse.getReqSN());
					// 保存证书信息
					certInfoService.createOrUpdateCert(certApplyAddResponse.getRefcode(),isRecovery);
					// 更新移动端申请信息
					mobileApplyInfo.setApplyResult("申请、自动审核成功待下载");
					mobileApplyInfo.setErrorMessage("无");
					mobileApplyInfo.setCertSn(certApplyAddResponse.getRefcode());
					mobileApplyInfoDao.update(mobileApplyInfo);
					// 更新移动端业务申请信息
					certapplyBusinessInfo.setCertSn(certApplyAddResponse.getRefcode());
					certapplyBusinessService.update(certapplyBusinessInfo);
					certSn = certApplyAddResponse.getRefcode();
					
				} else {
					
					CertApplyAuditResponse certApplyAuditResponse = btk.certApplyAudit(certApplyAddResponse.getReqSN(), true,"");
					if (certApplyAuditResponse != null&& "0".equals(certApplyAuditResponse.getErr())) {// 审核成功保存证书申请信息与证书信息,并更新移动端申请信息
						certapplyInfo.setUserInfoId(userInfoId);
						// 保存证书申请信息
						saveCertapplyVo(certapplyVo, "",certApplyAuditResponse.getRefcode(),certApplyAddResponse.getReqSN());
						// 保存证书信息
						certInfoService.createOrUpdateCert(certApplyAuditResponse.getRefcode(),isRecovery);
						// 更新移动端申请信息
						mobileApplyInfo.setApplyResult("申请、自动审核成功待下载");
						mobileApplyInfo.setErrorMessage("无");
						mobileApplyInfo.setCertSn(certApplyAuditResponse.getRefcode());
						mobileApplyInfoDao.update(mobileApplyInfo);
						// 更新移动端业务申请信息
						certapplyBusinessInfo.setCertSn(certApplyAuditResponse.getRefcode());
						certapplyBusinessService.update(certapplyBusinessInfo);
						certSn = certApplyAuditResponse.getRefcode();
						
					} else {// 如果审核失败,则删除申请,并更新移动端申请信息
						// 删除RA上申请信息
						DeleteApplyResponse deleteApplyResponse = btk.deleteApply(certApplyAddResponse.getReqSN());
						if (deleteApplyResponse != null&& "0".equals(deleteApplyResponse.getErr())) {
							message = certApplyAuditResponse.getMsg();
							errCode = certApplyAddResponse.getErr();
						}
						mobileApplyInfo.setApplyResult("证书审核失败");
						mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
						mobileApplyInfoDao.update(mobileApplyInfo);
						logger.warn("证书新制：{} 审核证书失败！错误码：{}，错误原因：{}。",
								certapplyInfo.getCertSubject(),errCode, message);
					}
				}
			} else {// 申请失败,更新移动端申请信息
				if (certApplyAddResponse != null) {
					message = certApplyAddResponse.getMsg();
					errCode = certApplyAddResponse.getErr();
				}
				mobileApplyInfo.setApplyResult("证书申请失败");
				mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
				mobileApplyInfoDao.update(mobileApplyInfo);
				logger.warn("证书新制：{} 申请证书失败！错误码：{}，错误原因：{}。",
						mobileApplyInfo.getCertSubject(), errCode, message);
			}
		}
		return certSn;
	}


	/**
	 * 封装证书申请扩展域信息 glq 2017-05-10
	 * 
	 * @param projectId
	 *            项目ID
	 * @param certType
	 *            证书类型
	 * @param userInfoId
	 *            用户ID
	 * @param certapplyVo
	 *            证书申请实体Vo
	 * @return
	 */
	public CertapplyVo sealedExtList(String projectId, String certType,
			String userInfoId, CertapplyVo certapplyVo) {
		//证书自定义扩展域
		List<CertSelfExt> certSelfExtList = certSelfExtService.getCertSelftextList(projectId,userInfoId,certType);
		certapplyVo.setCertSelfExtList(certSelfExtList);
		
		//
		List<CertStandardExt> certStandardExt = certStandardExtService.getCertStandtextList(projectId,userInfoId,certType);
		certapplyVo.setCertStandardExtList(certStandardExt);
		return certapplyVo;
	}
	
	public List<CertSelfExtBTK> certSelfextChange(CertapplyVo certapplyVo) {
		List<CertSelfExtBTK> certSelfExtBTKList = new ArrayList<CertSelfExtBTK>();
		if (certapplyVo != null) {
			if (certapplyVo.getCertSelfExtList() != null) {
				for (CertSelfExt certSelfExt : certapplyVo.getCertSelfExtList()) {
					CertSelfExtBTK certSelfExtBTK = new CertSelfExtBTK(
							certSelfExt.getRegSn(), certSelfExt.getCertSn(),
							certSelfExt.getOthernameId(),
							certSelfExt.getExtName(), certSelfExt.getExtValue());
					certSelfExtBTKList.add(certSelfExtBTK);
				}
				return certSelfExtBTKList;
			} else {
				return certSelfExtBTKList;
			}
		} else {
			return null;
		}
	}

	public List<CertStandardExtBTK> certStandextChange(CertapplyVo certapplyVo) {
		List<CertStandardExtBTK> certSelfExtBTKList = new ArrayList<CertStandardExtBTK>();
		if (certapplyVo != null) {
			if (certapplyVo.getCertStandardExtList() != null) {
				for (CertStandardExt certStandardExt : certapplyVo
						.getCertStandardExtList()) {
					CertStandardExtBTK certStandardExtBTK = new CertStandardExtBTK(
							certStandardExt.getReqSn(),
							certStandardExt.getCertSn(),
							certStandardExt.getExtName(),
							certStandardExt.getChildName(),
							certStandardExt.getOthernameOid(),
							certStandardExt.getExtValue());
					certSelfExtBTKList.add(certStandardExtBTK);
				}
				return certSelfExtBTKList;
			} else {
				return certSelfExtBTKList;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 证书申请证书扩展域实体转换为BTK实体 glq 2017-05-10
	 * 
	 * @param certapplyVo
	 *            证书申请实体VO
	 * @param object
	 *            需要转换的扩展域BTK实体对象 自定义:CertSelfExtBTK,标准:CertStandardExtBTK
	 * @return
	 */
	public Object busExtToBTK(CertapplyVo certapplyVo, Object object) {
		if (certapplyVo == null || object == null) {
			return null;
		}
		BussinessToBTKImpl bussToBtk = new BussinessToBTKImpl();
		String className = object.getClass().getName();
		className = className.substring(className.lastIndexOf("."));
		List<Object> certExtBTKs = new ArrayList<>();
		if (className.equals("CertSelfExtBTK")) {
			List<CertSelfExt> certSelfExts = certapplyVo.getCertSelfExtList();
			for (CertSelfExt certSelfExt : certSelfExts) {
				CertSelfExtBTK certSelfExtBTK = (CertSelfExtBTK) bussToBtk
						.mapToEntityBTK(certSelfExt, new CertSelfExtBTK());
				certExtBTKs.add(certSelfExtBTK);
			}

		}
		if (className.equals("CertStandardExtBTK")) {
			List<CertStandardExt> certStandardExts = certapplyVo
					.getCertStandardExtList();
			for (CertStandardExt certStandardExt : certStandardExts) {
				CertStandardExtBTK certSelfExtBTK = (CertStandardExtBTK) bussToBtk
						.mapToEntityBTK(certStandardExt,
								new CertStandardExtBTK());
				certExtBTKs.add(certSelfExtBTK);
			}
		}
		return certExtBTKs;
	}

	/**
	 * 移动端进行证书更新申请 glq 2017-05-09
	 * 
	 * @param certVo
	 *            证书Vo信息
	 * @param mobileApplyInfoId
	 *            移动端申请信息Id
	 * @return
	 */
	public String mobileUpdateApply(String certSn,CertVo certVo, String mobileApplyInfoId,String applyBusinessId) {
		
		String isRecovery = "0";
		
		MobileApplyInfo mobileApplyInfo = mobileApplyInfoDao.get(mobileApplyInfoId);
		
		CertapplyVo certapplyVo = new CertapplyVo();
		CertapplyInfo certapplyInfo = certVo.getCertapplyInfo();
		CertInfo certInfo = certVo.getCertInfo();
		CertInfo certEntity = null;
		
		if (certInfo != null && certapplyInfo != null) {
			
			CertInfo info = new CertInfo();
			info.setOldCertSn(certSn);
			CertInfo certInfoNodown = getNewCertinfoByOldCertSn(info);
			// 如果不为空，说明
			if (certInfoNodown != null) {
				certEntity = certInfoNodown;
				isRecovery = "1";
			} else {
				certEntity = certInfo;
			}
			
			//mobileApplyInfo.setOldCertSn(certEntity.getCertSn());
			// 获取项目id
			String projectId = certapplyInfo.getProjectId();
			if (StringUtils.isNotBlank(projectId)) {
				ProjectInfo projectInfo = projectInfoDao.get(projectId);
				boolean isRetainKey = false;
				if("0".equals(projectInfo.getIsHoldKey())){
					isRetainKey = true;
				}
				String date = DateUtil.date2String(new Date(), "yyyyMMddHHmmss");
				certapplyInfo.setOptTime(new BigDecimal(Double.parseDouble(date + "000")));
				certapplyInfo.setId(null);
				certapplyInfo.setApplyBusinessId(applyBusinessId);
				certapplyInfo.setOptType(ApplyConstants.RA_OPTTYPE_UPDATE);
				certapplyInfo.setReqStatus("1");
				certapplyInfo.setCertSn(null);
				certapplyInfo.setDoubleCertSn(null);
				certapplyInfo.setOldCertSn(certEntity.getCertSn());
				certapplyInfo.setOldDoubleCertSn(certEntity.getDoubleCertSn());

				certapplyVo.setCertapplyInfo(certapplyInfo);

				// 证书自定义扩展域信息
				List<CertSelfExt> selfList = certVo.getCertSelfExtList();
				if (selfList != null && selfList.size() > 0) {
					for (CertSelfExt self : selfList) {
						self.setCertId(null);
						self.setRegSn(null);
						self.setCertSn(null);
						self.setId(null);
					}
				}
				certapplyVo.setCertSelfExtList(selfList);

				// 证书标准扩展域信息
				List<CertStandardExt> standList = certVo.getCertStandardExtList();
				if (standList != null && standList.size() > 0) {
					for (CertStandardExt ext : standList) {
						ext.setCertId(null);
						ext.setReqSn(null);
						ext.setId(null);
						ext.setCertSn(null);
					}
				}
				certapplyVo.setCertStandardExtList(standList);

				CertCtml certCtml = certCtmlDao.get(projectInfo.getCertTemplate());

				CertapplyInfo applyInfo = new CertapplyInfo(
						certEntity.getCertSn(), certEntity.getCertSubject(),
						new BigDecimal(1), new BigDecimal(0),
						certCtml.getCertCtmlName(), certEntity.getCertValidity());

				Integer certValidity = certValitity(certVo);
				CertApplyBTK certApplyBTK = new CertApplyBTK(
						applyInfo.getCertSn(),
						applyInfo.getCertSubject(),
						applyInfo.getCtmlName(), new BigDecimal(1),
						new BigDecimal(0), certValidity, isRetainKey);
				
				// btk接口 证书更新申请
				CertUpdateApplyAddResponse certUpdateApplyAddResponse = btk.addCertUpdateOnlineApply(certApplyBTK);
				String message = "网络异常";
				String errCode = "000";
				
				if (certUpdateApplyAddResponse != null && "0".equals(certUpdateApplyAddResponse.getErr())) {// 证书申请成功
					
					if ("Y".equals(certCtml.getCertCtmlAudit())) {// 如果模板是自动审核,则审核完成后进行证书申请信息与证书信息的保存,并更新移动端申请信息
						
						// 审核成功后修改旧证书状态为Revoke
						certEntity.setCertStatus("Revoke");
						certEntity.setCertStatusMyb("Revoke");
						certEntity.setIsRecovery(isRecovery);
						certInfoDao.update(certEntity);
						// 保存证书申请信息
						saveCertapplyVo(certapplyVo, "",certUpdateApplyAddResponse.getRefcode(),certUpdateApplyAddResponse.getReqSN());
						// 保存证书信息
						certInfoService.createOrUpdateCert(certUpdateApplyAddResponse.getRefcode(),isRecovery);
						// 更新移动端申请信息
						mobileApplyInfo.setApplyResult("申请、自动审核成功待下载");
						mobileApplyInfo.setErrorMessage("无");
						mobileApplyInfo.setCertSn(certUpdateApplyAddResponse.getRefcode());
						mobileApplyInfoDao.update(mobileApplyInfo);
						return certUpdateApplyAddResponse.getRefcode();
					
					} else {
						
						CertUpdateApplyAuditResponse auditCertUpdateOnlineApply = btk.auditCertUpdateOnlineApply(certUpdateApplyAddResponse.getReqSN(),true, "");
						if (auditCertUpdateOnlineApply != null && "0".equals(auditCertUpdateOnlineApply.getErr())) {// 审核成功保存证书申请信息与证书信息,并更新移动端申请信息
							// 审核成功后修改旧证书状态为Revoke
							certEntity.setCertStatus("Revoke");
							certEntity.setCertStatusMyb("Revoke");
							certEntity.setIsRecovery(isRecovery);
							certInfoDao.update(certEntity);
							// 保存证书申请信息
							saveCertapplyVo(certapplyVo, "",auditCertUpdateOnlineApply.getRefcode(),certUpdateApplyAddResponse.getReqSN());
							// 保存证书信息
							certInfoService.createOrUpdateCert(auditCertUpdateOnlineApply.getRefcode(),isRecovery);
							// 更新移动端申请信息
							mobileApplyInfo.setApplyResult("申请、自动审核成功待下载");
							mobileApplyInfo.setErrorMessage("无");
							mobileApplyInfo.setCertSn(auditCertUpdateOnlineApply.getRefcode());
							mobileApplyInfoDao.update(mobileApplyInfo);
							return auditCertUpdateOnlineApply.getRefcode();
						
						} else {// 如果审核失败,则删除申请,并更新移动端申请信息
							if (auditCertUpdateOnlineApply != null) {
								// 删除RA上申请信息
								DeleteApplyResponse deleteApplyResponse = btk.deleteApply(auditCertUpdateOnlineApply.getReqSN());
								if ("0".equals(deleteApplyResponse.getErr())) {
									message = auditCertUpdateOnlineApply.getMsg();
									errCode = auditCertUpdateOnlineApply.getErr();
								}
							}
							mobileApplyInfo.setApplyResult("证书审核失败");
							mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
							mobileApplyInfoDao.update(mobileApplyInfo);
							logger.warn("证书补发：{} 更新审核失败！错误码：{}，错误原因：{}。",mobileApplyInfo.getCertSubject(),errCode, message);
						}
					}
				} else {// 申请失败,更新移动端申请信息
					if (certUpdateApplyAddResponse != null) {
						message = certUpdateApplyAddResponse.getMsg();
						errCode = certUpdateApplyAddResponse.getErr();
					}
					mobileApplyInfo.setApplyResult("证书申请失败");
					mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
					mobileApplyInfoDao.update(mobileApplyInfo);
					logger.warn("证书补发：{} 更新申请失败！错误码：{}，错误原因：{}。",mobileApplyInfo.getCertSubject(),errCode, message);
				}
			}
		}
		return null;
	}

	/**
	 * 证书下载 glq 2017-05-04
	 * 
	 * @param certSN
	 *            证书序列号
	 * @param p10
	 *            证书下载p10码
	 * @param mobileApplyInfoId
	 *            移动端申请信息id
	 * @return
	 */
	public String mobileCertDown(String certSN, String p10,String mobileApplyInfoId) {
		
		if (StringUtils.isNotBlank(certSN) && StringUtils.isNotBlank(p10)) {
			// 获取证书信息
			CertInfo certinfo = certInfoDao.getByCertSn(certSN);

			if (certinfo != null) {
				// 封装证书下载信息
				CertMake certMake = new CertMake();
				certMake.setAuthCode(certinfo.getAuthCode());
				certMake.setRefCode(certSN);
				certMake.setP10(p10);
				certMake.setCheckAuthcode(false);
				// 通过BTK进行下载
				BussinessToBTKImpl bussToBtk = new BussinessToBTKImpl();
				CertMakeBTK certMakeBTK = (CertMakeBTK) bussToBtk.mapToEntityBTK(certMake, new CertMakeBTK());
				CertMakeResponse certMakeResponse = btk.downloadCert(certMakeBTK);

				if (certMakeResponse != null&& certMakeResponse.getErr().equals("0")) {
					// 下载成功,修改证书信息
					CertInfo certInfo = certInfoService.createOrUpdateCert(certSN,certinfo.getIsRecovery());
					// 获取证书申请信息
					List<CertapplyInfo> certapplyInfos = certapplyInfoDao.findApplyByCertSn(certSN);
					
					if (certapplyInfos != null && certapplyInfos.size() > 0) {
						certapplyInfos.get(0).setCertId(certInfo.getId());
						certapplyInfos.get(0).setNotBefore(certInfo.getNotBefore());
						certapplyInfos.get(0).setNotAfter(certInfo.getNotAfter());
						certapplyInfos.get(0).setCertValidity(certInfo.getCertValidity());
						certapplyInfoDao.update(certapplyInfos.get(0));
					}
					if (StringUtils.isNotBlank(mobileApplyInfoId)) {
						// 获取移动端申请信息
						MobileApplyInfo mobileApplyInfo = mobileApplyInfoDao.get(mobileApplyInfoId);
						// 更新移动端申请信息
						mobileApplyInfo.setApplyResult("RA下载证书成功,手机未下载");
						mobileApplyInfo.setErrorMessage("无");
						mobileApplyInfo.setCertSn(certSN);
						mobileApplyInfoDao.update(mobileApplyInfo);
					}
					return certMakeResponse.getP7b();
				} else {
					if (StringUtils.isNotBlank(mobileApplyInfoId)) {
						String message = "网络异常";
						String errCode = "000";
						// 获取移动端申请信息
						MobileApplyInfo mobileApplyInfo = mobileApplyInfoDao.get(mobileApplyInfoId);
						// 下载失败更新移动端申请信息
						if(certMakeResponse != null){
							message =certMakeResponse.getMsg();
							errCode = certMakeResponse.getErr();
						}
						mobileApplyInfo.setApplyResult("RA下载证书失败");
						mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
						mobileApplyInfo.setCertSn(certSN);
						mobileApplyInfoDao.update(mobileApplyInfo);
						logger.warn("证书下载：{} 下载证书失败！错误码：{}，错误原因：{}。",mobileApplyInfo.getCertSubject(), errCode, message);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 判断当前散户是否存在 存在则判断是否有使用中证书或未审核的证书申请,不存在则保存用户信息 glq 2017-05-08
	 * 
	 * @param phoneNum
	 * @param idCard
	 * @return
	 */
	public Map<String, Object> getCertOther(String phoneNum, String name,
			String idCard, String projectId) {
		Map<String, Object> result = new HashMap<>();
		// 根据信息获取个人用户
		UserInfo userInfo = new UserInfo();
		userInfo.setUseridcardnum(idCard);
		List<UserInfo> userInfos = userInfoDao.findList(userInfo);
		if (userInfos == null || userInfos.size() <= 0) {
			userInfo.setPhonenum(phoneNum);
			userInfo.setUsername(name);
			userInfo.setIdtype("01");// 默认身份证
			userInfo.setUserType("1");// 散户
			boolean isSaveSucc = userInfoService.saveAndRasave(userInfo);
			if (!isSaveSucc) {
				result.put("flag", "1");
				result.put("message", "用户信息保存失败");
				return result;
			}
			List<UserInfo> otherUserInfos = userInfoDao.findList(userInfo);
			if (otherUserInfos != null && otherUserInfos.size() > 0) {
				result.put("flag", "0");
				result.put("userId", otherUserInfos.get(0).getId());
				return result;
			}
		} else {
			// 判断散户信息,并判断默认项目下是否有使用中证书或者未审核的证书申请-------开始
			if (!phoneNum.equals(userInfos.get(0).getPhonenum())
					|| !name.equals(userInfos.get(0).getUsername())) {
				result.put("flag", "1");
				result.put("message", "用户信息与系统中的不符,请先修改用户信息");
				return result;
			}
			// 获取该用户在默认项目下的申请信息
			CertapplyInfo certapplyInfo = new CertapplyInfo();
			certapplyInfo.setUserInfoId(userInfos.get(0).getId());
			certapplyInfo.setProjectId(projectId);
			List<CertapplyInfo> certapplyInfos = certapplyInfoDao
					.findList(certapplyInfo);

			// 如果有申请信息则遍历申请信息,并进行相关判断
			for (int i = 0; i < certapplyInfos.size(); i++) {
				if (StringUtils.isBlank(certapplyInfos.get(i).getCertSn())) {
					// 如果存在未审核的证书申请信息,则返回
					if (StringUtils.isNotBlank(certapplyInfos.get(i)
							.getReqStatus())
							&& "1".equals(certapplyInfos.get(i).getReqStatus())) {
						result.put("flag", "1");
						result.put("message", "存在未审核证书申请");
						return result;
					}
				} else {// 判断证书状态是否是使用中、未下载或者冻结
					CertInfo certInfo = certInfoDao.getByCertSn(certapplyInfos
							.get(i).getCertSn());
					if ("Revoke".equals(certInfo.getCertStatus())
							|| "UndownRevoke".equals(certInfo.getCertStatus())) {// 证书状态是"注销"或者是"未下载注销"
																					// 则继续
						continue;
					} else {// 证书状态是"未下载"、"使用中"、"冻结" 则进行RA数据同步
						CertSingleQueryResponse certSingleQueryResponse = btk
								.certSingleQuery(certInfo.getCertSn());
						RACertInfo raCertInfo = certSingleQueryResponse
								.getRaCertInfo();
						if (!raCertInfo.getCertInfo().getCertStatus()
								.equals(certInfo.getCertStatus())) {
							certInfo.setCertStatus(raCertInfo.getCertInfo()
									.getCertStatus());
							certInfo.setCertValidity(raCertInfo.getCertInfo()
									.getValidity());
							certInfo.setNotBefore(new BigDecimal(raCertInfo
									.getCertInfo().getNotBefore()));
							certInfo.setNotAfter(new BigDecimal(raCertInfo
									.getCertInfo().getNotAfter()));
							certInfoDao.update(certInfo);
						}
						if (("Use".equals(certInfo.getCertStatus()) || "Undown"
								.equals(certInfo.getCertStatus()))
								&& "Undown".equals(certInfo.getCertStatusMyb())) {
							result.put("flag", "0");
							result.put("userId", userInfos.get(0).getId());
							result.put("subjectOrSubjectRule",
									certInfo.getCertSubject());
							return result;
						}
						if ("Hold".equals(certInfo.getCertStatus())
								|| "Use".equals(certInfo.getCertStatusMyb())) {
							result.put("flag", "1");
							result.put("message", "存在使用中或者冻结的证书");
							return result;
						}
					}
				}
			}
			// 如果没有申请信息 、不存在使用中证书 、不存在未审核证书申请 则返回
			result.put("flag", "0");
			result.put("userId", userInfos.get(0).getId());
			return result;
			// 判断散户信息,并判断默认项目下是否有使用中证书或者未审核的证书申请-------结束
		}
		return null;
	}

	/**
	 * 判断当前RA证书授权码是否过期,如果过期则进行授权码更新,并更新证书信息 glq 2017-05-04
	 * @param certinfo
	 * @return
	 */
	private CertInfo verifyRACode(CertInfo certinfo) {
		String opt = certinfo.getOptTime().longValue() + "";
		opt = opt.substring(0, 4) + "-" + opt.substring(4, 6) + "-"
				+ opt.substring(6, 8) + " " + opt.substring(8, 10) + ":"
				+ opt.substring(10, 12) + ":" + opt.substring(12, 14);
		Date date = DateUtils.string2Date(opt, "yyyy-MM-dd HH:mm:ss");
		date = new Date(date.getTime() + 30 * 24 * 60 * 60 * 1000);
		long beforetime = new Date().getTime();
		long aftertime = date.getTime();
		long time = aftertime - beforetime;
		if (time < 0) {
			CertAuthCodeUpdateApplyAddResponse addResponse = btk
					.certAuthCodeUpdateApplyAdd(certinfo.getCertSn());
			if (addResponse != null && "0".equals(addResponse.getErr())) {
				CertAuthCodeUpdateApplyAuditResponse auditResponse = btk
						.certAuthCodeUpdateApplyAudit(addResponse.getReqSN(),
								true, "");
				if (auditResponse != null && "0".equals(auditResponse.getErr())) {
					certinfo.setAuthCode(auditResponse.getAuthcode());
					certInfoDao.update(certinfo);
					return certinfo;
				}
			}
		}
		return certinfo;
	}

	/**
	 * 证书申请回调更新证书本地状态 根据证书序列号获取到证书信息及最新申请信息,修改"本地证书状态"为"Use"
	 * 如果证书申请为证书新制或变更,即配置在白名单中的证书申请,则修改白名单的"是否有效"状态为"否" glq 2017-05-19
	 * 
	 * @param certSN
	 *            证书序列号
	 * @return
	 */
	@Transactional
	public boolean updateCertCallBack(String certSN,String oldCertSn,String applyType) {
		Boolean result = false;
		try {
			CertVo certVo = this.getcertVoBycertsn(certSN);
			CertInfo certInfo = certVo.getCertInfo();
			
			if (certInfo != null) {
				/*ggw*/
				CertapplyBusinessInfo certapplyBusinessInfo = null;
				CertapplyBusinessInfo certapplyBusinessInfoserch = new CertapplyBusinessInfo();
				if(oldCertSn!=null&&!"".equals(oldCertSn)) {
					certapplyBusinessInfoserch.setApplyType(applyType);
					certapplyBusinessInfoserch.setCertSn(oldCertSn);
				}else {
					certapplyBusinessInfoserch.setApplyType(applyType);
					certapplyBusinessInfoserch.setCertSn(certSN);
				}
				List<CertapplyBusinessInfo> certapplyBusinessInfos = certapplyBusinessService.findList(certapplyBusinessInfoserch);
				if(certapplyBusinessInfos!=null&&certapplyBusinessInfos.size()>0) {
					certapplyBusinessInfo = certapplyBusinessInfos.get(0);
					certapplyBusinessInfo.setApplyResult(ApplyConstants.BUSINESS_OK);
					certapplyBusinessService.update(certapplyBusinessInfo);
				}
				
				
				MobileApplyInfo applyInfo = new MobileApplyInfo();
				applyInfo.setCertSn(certSN);
				List<MobileApplyInfo> mobileApplyInfos = mobileApplyInfoDao.findbyCertSn(applyInfo);
				MobileApplyInfo mobileApplyInfo;
				if(mobileApplyInfos!=null&&mobileApplyInfos.size()>0) {
					mobileApplyInfo = mobileApplyInfos.get(0);
					mobileApplyInfo.setApplyResult("证书下载成功");
					mobileApplyInfoDao.update(mobileApplyInfo);
				}
				/*ggw*/
				certInfo.setCertStatusMyb("Use");
				certInfo.preUpdate();
				certInfoDao.update(certInfo);
				CertapplyInfo certapplyInfo = certVo.getCertapplyInfo();
				if (certapplyInfo != null) {
					if (ApplyConstants.OPTTYPE_ADD.equals(applyType)|| ApplyConstants.OPTTYPE_ALTER.equals(applyType)) {
						String certType = certapplyInfo.getCertType();
						String whiteOrCorporcodeId = null;
						if (ApplyConstants.USERTYPE_USERINFO.equals(certType)) {
							WhiteList whiteList = new WhiteList();
							whiteList.setUserInfoId(certapplyInfo.getUserInfoId());
							whiteList.setProjectInfoId(certapplyInfo.getProjectId());
							whiteList.setOptType(applyType);
							List<WhiteList> whiteLists = whiteListDao.findWaitwhiteList(whiteList);
							if (whiteLists != null && whiteLists.size() > 0) {
								whiteOrCorporcodeId = whiteLists.get(0).getId();
							}
						} else {
							CorporationRequestCode corporationRequestCode = new CorporationRequestCode();
							corporationRequestCode.setCorporationId(certapplyInfo.getUserInfoId());
							corporationRequestCode.setCorporUserId(certapplyInfo.getCorpUserId());
							corporationRequestCode.setProjectId(certapplyInfo.getProjectId());
							corporationRequestCode.setType(applyType);
							corporationRequestCode.setStatus("0");
							List<CorporationRequestCode> requestCodes = corporcodeDao.findList(corporationRequestCode);
							if (requestCodes != null && requestCodes.size() > 0) {
								whiteOrCorporcodeId = requestCodes.get(0).getId();
							}
						}
						// 白名单，设为失效
						corporcodeService.updateWhiteListstatus(certType,
								whiteOrCorporcodeId);
					}
				}
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
		
	}

	/**
	 * 证书变更业务
	 * 判断白名单所在项目下是否有与新证书主题certSubject相关的使用中证书  glq 2017-06-22
	 * @param certSn
	 * @param certSubject
     * @return
     */
	public boolean isHasCert(String certType, String certSn, String certSubject){
		String  projectId = null;
		if (ApplyConstants.USERTYPE_USERINFO.equals(certType)) {// 个人白名单去查
			// 白名单查询类
			WhiteList whiteList = new WhiteList();
			whiteList.setCertSn(certSn);
			whiteList.setOptType(ApplyConstants.OPTTYPE_ALTER);// 变更类型为1
			List<WhiteList> whitelistList = whiteListDao.findList(whiteList);
			if (whitelistList != null && whitelistList.size() > 0) {
				WhiteList whiteEntity = whitelistList.get(0);
				// 取到项目id
				projectId = whiteEntity.getProjectInfoId();
			}
		} else if (ApplyConstants.USERTYPE_CORPORATION_INFO.equals(certType)) {// 企业白名单去查
			// 企业白名单查询类
			CorporationRequestCode corporationRequestCode = new CorporationRequestCode();
			corporationRequestCode.setType(ApplyConstants.OPTTYPE_ALTER);
			corporationRequestCode.setCertSn(certSn);
			List<CorporationRequestCode> corporRequestList = corporcodeDao.findList(corporationRequestCode);
			if (corporRequestList != null && corporRequestList.size() > 0) {
				CorporationRequestCode entity = corporRequestList.get(0);
				projectId = entity.getProjectId();
			}
		}
		CertInfo certInfo = new CertInfo();
		certInfo.setCertSubject(certSubject);
		certInfo.setCertStatusMyb("Use");
		certInfo.setCertStatus("Use");
		CertapplyInfo applyInfo = new CertapplyInfo();
		applyInfo.setProjectId(projectId);
		certInfo.setCertapplyInfo(applyInfo);
		List<CertInfo> certInfos = certInfoDao.findbyList(certInfo);
		if(certInfos != null && certInfos.size() > 0 ){
			return true;
		}
		return false;
	}

	/**
	 * 证书变更 需 证书变更申请下载接口 lihuilong
	 * 
	 * @param certType
	 *            证书类型
	 * @param certSn
	 *            证书序列号
	 * @param p10
	 *            证书下载p10码
	 * @param deviceType
	 *            设备类型
	 * @param deviceNum
	 *            设备类型编码
	 * @return
	 */
	@Override
	public Map<String, Object> changeMobileCert(String certType, String certSn,String deviceType, String deviceNum,String certSubject,String applyBusinessId,String mobileApplyInfoId) {
		// 获取变更下 存在证书，切证书oldcertsn 是该序列号的，查找出最新的一条证书序列号
		String certSnNew = getChangeNewCertinfo(certSn);

		// 获取移动端申请信息
		MobileApplyInfo mobileApplyInfo = mobileApplyInfoDao.get(mobileApplyInfoId);
		
		String isRecovery = "0";

		BussinessToBTKImpl bussToBtk = new BussinessToBTKImpl();
		CertVOBTK certVOBTK = new CertVOBTK();
		String projectId = null;
		String userId = null;
		String id = null;// 白名单id
		CertapplyInfo certapplyInfo = new CertapplyInfo();

		// 通过证书序列号 以及证书类型 去对应的白名单中去查询相关的变更信息
		if ("userinfo".equals(certType)) {// 个人白名单去查
			// 白名单查询类
			WhiteList whiteList = new WhiteList();
			whiteList.setCertSn(certSn);
			whiteList.setOptType(ApplyConstants.OPTTYPE_ALTER);// 变更类型为1

			List<WhiteList> whitelistList = whiteListDao.findList(whiteList);

			if (whitelistList != null && whitelistList.size() > 0) {
				WhiteList whiteEntity = whitelistList.get(0);
				id = whiteEntity.getId();
				// 取到项目id
				projectId = whiteEntity.getProjectInfoId();
				// 封装证书申请个人用户信息
				UserInfo userinfo = userInfoDao
						.get(whiteEntity.getUserInfoId());
				userId = userinfo.getId();
				UserBTK userBTK = (UserBTK) bussToBtk.mapToEntityBTK(userinfo,
						new UserBTK());
				certVOBTK.setUserBTK(userBTK);
				// 封装证书申请信息
				certapplyInfo.setCertType(ApplyConstants.USERTYPE_USERINFO);
				certapplyInfo.setUserInfoId(userinfo.getRaUserId());
			} else {
				Map<String, Object> map = new HashMap<>();
				map.put("flag", "1");
				map.put("message", "白名单查询失败！");

				mobileApplyInfo.setApplyResult("白名单查询失败!");
				mobileApplyInfo.setErrorMessage("无");
				mobileApplyInfo.preInsert();
				mobileApplyInfoDao.insert(mobileApplyInfo);
				return map;
			}

		} else if ("corporation_info".equals(certType)) {// 企业白名单去查
			// 企业白名单查询类
			CorporationRequestCode corporationRequestCode = new CorporationRequestCode();
			corporationRequestCode.setType(ApplyConstants.OPTTYPE_ALTER);
			corporationRequestCode.setCertSn(certSn);

			List<CorporationRequestCode> corporRequestList = corporcodeDao
					.findList(corporationRequestCode);
			if (corporRequestList != null && corporRequestList.size() > 0) {
				CorporationRequestCode entity = corporRequestList.get(0);
				id = entity.getId();
				projectId = entity.getProjectId();
				// 封装证书申请企业用户信息
				CorporationInfo corporationInfo = corporationInfoDao.get(entity
						.getCorporationId());
				userId = corporationInfo.getId();
				CorporationBTK corporationBTK = (CorporationBTK) bussToBtk
						.mapToEntityBTK(corporationInfo, new CorporationBTK());
				corporationBTK.setCorpcountry("CN");
				certVOBTK.setCorporationBTK(corporationBTK);
				// 封装证书申请信息
				certapplyInfo.setCertType(ApplyConstants.USERTYPE_CORPORATION_INFO);
				certapplyInfo.setUserInfoId(corporationInfo.getRaUserId());
				certapplyInfo.setCorpUserId(entity.getCorporUserId());
			} else {
				Map<String, Object> map = new HashMap<>();
				map.put("flag", "1");
				map.put("message", "白名单查询失败！");

				mobileApplyInfo.setApplyResult("白名单查询失败!");
				mobileApplyInfo.setErrorMessage("无");
				mobileApplyInfo.preInsert();
				mobileApplyInfoDao.insert(mobileApplyInfo);
				return map;
			}
		}

		// 先去注销证书
		Boolean result = true;
		CertVo certVoNew = getcertVoBycertsn(certSnNew);
		if("Undown".equals(certVoNew.getCertInfo().getCertStatusMyb())) {
			isRecovery = "1";
		}
		//证书注销--开始
		result = cancelMobileCert(certVoNew,applyBusinessId,isRecovery,mobileApplyInfoId);
		//证书注销--结束
		
		if (result) {

			certapplyInfo.setDeviceNum(deviceNum);
			certapplyInfo.setDeviceType(deviceType);
			certapplyInfo.setOldCertSn(certSnNew);

			mobileApplyInfo.setCertSubject(certSubject);

			// 封装证书申请扩展域信息
			CertapplyVo certapplyVo = sealedExtList(projectId, certType,userId, new CertapplyVo());

			// 证书申请证书自定义扩展域实体转换为BTK实体
			List<CertSelfExtBTK> certSelfExtBTKs = certSelfextChange(certapplyVo);
			certVOBTK.setCertSelfExtBTKList(certSelfExtBTKs);
			// 证书申请证书标准扩展域实体转换为BTK实体
			List<CertStandardExtBTK> certStandardExtBTKs = certStandextChange(certapplyVo);
			certVOBTK.setCertStandardExtBTKList(certStandardExtBTKs);

			// 封装证书申请信息
			ProjectInfo projectInfo = projectInfoDao.get(projectId);
			mobileApplyInfo.setProjectId(projectId);
			mobileApplyInfo.setProjectName(projectInfo.getProjectName());

			// 证书模板信息
			CertCtml certCtml = certCtmlDao.get(projectInfo.getCertTemplate());
			certapplyInfo.setApplyBusinessId(applyBusinessId);
			certapplyInfo.setCtmlName(certCtml.getCertCtmlName());
			certapplyInfo.setCertValidity(projectInfo.getCertValidity());
			certapplyInfo.setCertSubject(certSubject);
			certapplyInfo.setIsAdmin("1");
			certapplyInfo.setNotBefore(new BigDecimal(1));
			certapplyInfo.setNotAfter(new BigDecimal(0));
			certapplyInfo.setProjectId(projectId);
			certapplyInfo.setOptType(ApplyConstants.RA_OPTTYPE_ADD);
			certapplyInfo.setOrganId(Global.getConfig("organId"));
			certapplyInfo.setSubjectUppercase(certSubject.toUpperCase());
			if (certapplyInfo.getCertSubject().indexOf("CN=") != -1) {
				String certsubject = certapplyInfo.getCertSubject();
				String subname = certsubject.substring(certsubject
						.indexOf("CN=") + 3);
				String commName = subname.substring(0, subname.indexOf(","));
				certapplyInfo.setCommonName(commName);
			} else {
				certapplyInfo.setCommonName(certapplyInfo.getCertSubject());
			}
			certapplyInfo.setApplicant(certSubject);
			certapplyInfo.setApplicantUppercase(certSubject.toUpperCase());
			String date = DateUtils.formatPayTime(new Date());
			certapplyInfo.setOptTime(new BigDecimal(Double.parseDouble(date
					+ "000")));
			certapplyVo.setCertapplyInfo(certapplyInfo);
			// 证书申请信息业务实体 转换为BTK实体
			CertApplyBTK certApplyBTK = (CertApplyBTK) bussToBtk
					.mapToEntityBTK(certapplyInfo, new CertApplyBTK());
			certVOBTK.setCertApplyBTK(certApplyBTK);
			String message = "网络异常";
			String errCode = "000";
			// 证书申请
			CertApplyAddResponse certApplyAddResponse = btk
					.certApplyAdd(certVOBTK);
			if (certApplyAddResponse != null
					&& "0".equals(certApplyAddResponse.getErr())) {
				if ("Y".equals(certCtml.getCertCtmlAudit())) {// 如果模板是自动审核,则审核完成后进行证书申请信息与证书信息的保存,并更新移动端申请信息

					// 通过证书序列号去ra查找
					CertInfo certInfoNew = certInfoService.getCertBycertsnFromra(certApplyAddResponse
							.getRefcode());
					certInfoNew.setIsRecovery(isRecovery);
					certInfoNew.setOldCertSn(certSnNew);
					certInfoNew.setCertStatusMyb("Undown");
					// 保存新证书实体
					certInfoNew.preInsert();
					certInfoDao.insert(certInfoNew);

					// 保存证书申请信息
					saveCertapplyVo(certapplyVo, certInfoNew.getId(),
							certApplyAddResponse.getRefcode(),
							certApplyAddResponse.getReqSN());
					
					mobileApplyInfo.setApplyResult("审请,自动审核成功待下载");
					mobileApplyInfo.setErrorMessage("无");
					mobileApplyInfoDao.update(mobileApplyInfo);
					
					Map<String, Object> map = new HashMap<>();
					map.put("flag", "0");
					map.put("newCertSn", certInfoNew.getCertSn());
					return map;

				} else if ("N".equals(certCtml.getCertCtmlAudit())) {

					CertApplyAuditResponse certApplyAuditResponse = btk
							.certApplyAudit(certApplyAddResponse.getReqSN(),
									true, null);

					if (certApplyAuditResponse != null
							&& "0".equals(certApplyAuditResponse.getErr())) {
						// 通过证书序列号去ra查找
						CertInfo certInfoNew = certInfoService.getCertBycertsnFromra(certApplyAuditResponse
								.getRefcode());
						certInfoNew.setIsRecovery(isRecovery);
						certInfoNew.setOldCertSn(certSnNew);
						certInfoNew.setCertStatusMyb("Undown");
						// 保存新证书实体
						certInfoNew.preInsert();
						certInfoDao.insert(certInfoNew);

						certapplyInfo.setUserInfoId(userId);
						certapplyInfo.setNotAfter(certInfoNew.getNotAfter());
						certapplyInfo.setNotBefore(certInfoNew.getNotBefore());

						// 保存证书申请信息
						saveCertapplyVo(certapplyVo, certInfoNew.getId(),
								certApplyAuditResponse.getRefcode(),
								certApplyAuditResponse.getReqSN());
						
						mobileApplyInfo.setApplyResult("审请,自动审核成功待下载");
						mobileApplyInfo.setErrorMessage("无");
						mobileApplyInfoDao.update(mobileApplyInfo);
						
						Map<String, Object> map = new HashMap<>();
						map.put("flag", "0");
						map.put("newCertSn", certInfoNew.getCertSn());
						return map;


					} else {
						Map<String, Object> map = new HashMap<>();
						map.put("flag", "1");
						map.put("message", "证书变更审核失败！");
						
						if(certApplyAuditResponse != null){
							message =certApplyAuditResponse.getMsg();
							errCode = certApplyAuditResponse.getErr();
						}
						mobileApplyInfo.setApplyResult("证书审核失败！");
						mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
						mobileApplyInfoDao.update(mobileApplyInfo);
						logger.warn("证书变更：{} 证书审核失败！错误码：{}，错误原因：{}。",
								mobileApplyInfo.getCertSubject(), errCode, message);
						return map;
					}
				}
			} else {
				Map<String, Object> map = new HashMap<>();
				map.put("flag", "1");
				map.put("message", "证书申请失败！");

				if(certApplyAddResponse != null){
					message =certApplyAddResponse.getMsg();
					errCode = certApplyAddResponse.getErr();
				}
				mobileApplyInfo.setApplyResult("证书申请失败！");
				mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
				mobileApplyInfoDao.update(mobileApplyInfo);
				logger.warn("证书变更：{} 证书申请失败！错误码：{}，错误原因：{}。",
						mobileApplyInfo.getCertSubject(), errCode, message);
				return map;
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("flag", "1");
			map.put("message", "变更申请下载失败!");
			return map;
		}

		return null;
	}

	/***
	 * lhl 修改手机号接口
	 * @param idCard
	 * @param oldPhoneNum
	 * @param
	 */
	public Map<String, Object> changePhoneNum(String name, String idCard,String oldPhoneNum, String newPhoneNum, String deviceNum) {

		boolean flag = false;
		// 个人用户信息查询，修改----------开始
		// 先从个人用户中去查询匹配的用户,通过身份证号码去查询
		UserInfo userInfo = new UserInfo();
		userInfo.setUseridcardnum(idCard);
		userInfo.setPhonenum(oldPhoneNum);

		List<UserInfo> userInfoList = userInfoDao.findList(userInfo);

		if (userInfoList != null && userInfoList.size() > 0) {
			flag = true;
			for (UserInfo userEntity : userInfoList) {
				userEntity.setPhonenum(newPhoneNum);
				userEntity.setUsername(name);
				// 修改保存新的手机号信息
				if (!userInfoService.saveAndRasave(userEntity)) {
					Map<String, Object> map = new HashMap<>();
					map.put("flag", "1");
					map.put("message", "手机号修改失败！");
					return map;
				}
			}
		}
		// 个人用户信息查询，修改----------结束

		// 企业用户信息查询，修改----------开始
		// 去企业用户信息表中去查询匹配用户信息
		CorporationUserInfo corporationUserInfo = new CorporationUserInfo();

		corporationUserInfo.setIdCard(idCard);
		corporationUserInfo.setPhoneNum(oldPhoneNum);

		List<CorporationUserInfo> corporUserList = corporationUserInfoDao.findList(corporationUserInfo);

		if (corporUserList != null && corporUserList.size() > 0) {
			flag = true;
			for (CorporationUserInfo corporUser : corporUserList) {
				corporUser.setPhoneNum(newPhoneNum);
				corporUser.setUserName(name);
				corporationUserInfoDao.update(corporUser);
			}
		} 
		// 企业用户信息查询，修改----------结束
		
		if (flag) {
			//手机号修改成功后，去修改会话信息---------开始
			Map<String, Object> map = new HashMap<>();
			map.put("flag", "0");
			// 通过旧手机去会话中查询信息
			List<UserConversation> userConversationList = userConversationService
					.findListByPhoneNum(oldPhoneNum);

			if (userConversationList != null && userConversationList.size() > 0) {
				UserConversation userConversation = userConversationList.get(0);
				// 删除旧的会话信息，创建一条新的会话-------------开始
				userConversationService.delete(userConversation.getId());
				UserConversation entity = new UserConversation();
				entity.setDeviceNum(deviceNum);
				entity.setPhoneNum(newPhoneNum);
				entity.setConversationDate(new Date());
				entity.preInsert();
				userConversationService.insert(entity);
				// 删除旧的会话信息，创建一条新的会话-------------结束
				map.put("message", "手机号修改成功！");
			} else {
				map.put("message", "手机号修改成功,会话信息查询失败！");
			}
			//手机号修改成功后，去修改会话信息---------结束
			
			return map;
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("flag", "1");
			map.put("message", "未找到修改信息！");
			return map;
		}
	}

	/**
	 * 忘记证书密码申请
	 * 
	 * @param certSn
	 * @param subject
	 * @param p10
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> getMobileCertByForgetPass(CertVo certVo,String mobileApplyInfoId,String applyBusinessId,String isRecovery){

		// 获取移动端申请信息
		MobileApplyInfo mobileApplyInfo = mobileApplyInfoDao.get(mobileApplyInfoId);
		//证书申请实体
		CertapplyVo certapplyVo = new CertapplyVo();
		//证书申请对象
		CertapplyInfo certapplyInfo = certVo.getCertapplyInfo();
		//待跟新证书对象
		CertInfo certEntity = certVo.getCertInfo();

		if (certEntity != null && certapplyInfo != null) {
			// 获取项目id
			String projectId = certapplyInfo.getProjectId();
			
			if (StringUtils.isNotBlank(projectId)) {
					ProjectInfo projectInfo = projectInfoDao.get(projectId);
					boolean isRetainKey = false;
					if("0".equals(projectInfo.getIsHoldKey())){
						isRetainKey = true;
					}
					//证书申请对象
					String date = DateUtil.date2String(new Date(),"yyyyMMddHHmmss");
					certapplyInfo.setOptTime(new BigDecimal(Double.parseDouble(date + "000")));
					certapplyInfo.setId(null);
					certapplyInfo.setApplyBusinessId(applyBusinessId);
					certapplyInfo.setOptType(ApplyConstants.OPTTYPE_OTHER_FORGET_PIN);
					certapplyInfo.setReqStatus("1");
					certapplyInfo.setDoubleCertSn(null);
					certapplyInfo.setOldCertSn(certEntity.getCertSn());
					certapplyInfo.setOldDoubleCertSn(certEntity.getDoubleCertSn());

					certapplyVo.setCertapplyInfo(certapplyInfo);

					// 证书自定义扩展域信息
					List<CertSelfExt> selfList = certVo.getCertSelfExtList();
					if (selfList != null && selfList.size() > 0) {
						for (CertSelfExt self : selfList) {
							self.setCertId(null);
							self.setRegSn(null);
							self.setCertSn(null);
							self.setId(null);
						}
					}
					certapplyVo.setCertSelfExtList(selfList);

					// 证书标准扩展域信息
					List<CertStandardExt> standList = certVo
							.getCertStandardExtList();
					if (standList != null && standList.size() > 0) {
						for (CertStandardExt ext : standList) {
							ext.setCertId(null);
							ext.setReqSn(null);
							ext.setId(null);
							ext.setCertSn(null);
						}
					}
					certapplyVo.setCertStandardExtList(standList);

					//BTK证书申请信息封装类------装载
					Integer certValidity = certValitity(certVo);
					CertApplyBTK certApplyBTK = new CertApplyBTK(
							certEntity.getCertSn(),
							certEntity.getCertSubject(),
							certEntity.getCtmlName(), new BigDecimal(1),
							new BigDecimal(0), certValidity, isRetainKey);
					
					// btk接口 证书跟新申请
					CertUpdateApplyAddResponse response = btk
							.addCertUpdateOnlineApply(certApplyBTK);
					String message = "网络异常";
					String errCode = "000";
					if (response != null) {
						if (!response.getErr().equals("0")) {
							Map<String, Object> map = new HashMap<>();
							map.put("flag", "1");
							map.put("message", "证书申请失败！");
							
							if(response != null){
								message =response.getMsg();
								errCode = response.getErr();
							}
							
							mobileApplyInfo.setApplyResult("证书申请失败！");
							mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
							mobileApplyInfoDao.update(mobileApplyInfo);
							logger.warn("忘记密码：{} 证书更新申请失败！错误码：{}，错误原因：{}。",
									mobileApplyInfo.getCertSubject(), errCode, message);
							return map;
						}

						// 通过 项目id 取到模板id 通过模板id取到 模板 来判断模板是否是 自动审核
						String certTemplate = projectInfo.getCertTemplate();
						CertCtml certCtml = certCtmlDao.get(certTemplate);

						// Y为自动审核,N为手动审核
						if ("Y".equals(certCtml.getCertCtmlAudit())) {

							//证书跟新后，注销旧的证书实体，保存新的证书对象----------开始
							String newCertSn = saveUpdatecertVo(certEntity,response.getRefcode(),response.getReqSN(),certapplyVo,isRecovery);
							//证书跟新后，注销旧的证书实体，保存新的证书对象----------结束
							
							mobileApplyInfo.setApplyResult("审请,自动审核成功待下载");
							mobileApplyInfo.setErrorMessage("无");
							mobileApplyInfoDao.update(mobileApplyInfo);
							
							Map<String, Object> map = new HashMap<>();
							map.put("flag", "0");
							map.put("newCertSn", newCertSn);
							return map;

						} else if ("N".equals(certCtml.getCertCtmlAudit())) {
							// 手动审核 ，需要再去调用btk 手动审核---------开始
							CertUpdateApplyAuditResponse certUpdateApplyAuditResponse = btk.auditCertUpdateOnlineApply(response.getReqSN(), true, "");
							// 手动审核 ，需要再去调用btk 手动审核---------结束

							if (certUpdateApplyAuditResponse != null
									&& certUpdateApplyAuditResponse.getErr()
											.equals("0")) {

								//证书跟新后，注销旧的证书实体，保存新的证书对象----------开始
								String newCertSn = saveUpdatecertVo(certEntity,certUpdateApplyAuditResponse.getRefcode(),certUpdateApplyAuditResponse.getReqSN(),certapplyVo,isRecovery);
								//证书跟新后，注销旧的证书实体，保存新的证书对象----------结束
								
								mobileApplyInfo.setApplyResult("审请,自动审核成功待下载");
								mobileApplyInfo.setErrorMessage("无");
								mobileApplyInfoDao.update(mobileApplyInfo);
								
								Map<String, Object> map = new HashMap<>();
								map.put("flag", "0");
								map.put("newCertSn", newCertSn);
								return map;

							} else {
								Map<String, Object> map = new HashMap<>();
								map.put("flag", "1");
								map.put("p7b", "");
								map.put("message", "证书审核失败！");

								if(certUpdateApplyAuditResponse != null){
									message =certUpdateApplyAuditResponse.getMsg();
									errCode = certUpdateApplyAuditResponse.getErr();
								}
								
								mobileApplyInfo.setApplyResult("证书审核失败");
								mobileApplyInfo.setErrorMessage("错误码:"+errCode+",错误原因:"+message);
								mobileApplyInfoDao.update(mobileApplyInfo);
								logger.warn("忘记密码：{} 证书申请审核失败！错误码：{}，错误原因：{}。",
										mobileApplyInfo.getCertSubject(), errCode, message);
								return map;
							}

						}

					} else {
						return null;
					}
			} else {
				Map<String, Object> map = new HashMap<>();
				map.put("flag", "1");
				map.put("message", "忘记密码:项目信息查询失败！");
				return map;
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("flag", "1");
			map.put("message", "忘记密码:证书相关信息查询失败！");
			return map;
		}
		return null;
	
		
	}

	/**
	 * lihuilong 获取证书未下载状态下的最新的一条证书信息
	 * 
	 * @return
	 */
	public CertInfo getNodownCertinfo(String certSubject,Object userOrCorpWhitelist,String certType) {
		
		CertInfo entity = new CertInfo();
		entity.setCertSubject(certSubject);
		if (ApplyConstants.USERTYPE_USERINFO.equals(certType)) {
			WhiteList whiteList = (WhiteList) userOrCorpWhitelist;
			ProjectInfo projectInfo = new ProjectInfo();
			projectInfo.setId(whiteList.getProjectInfoId());
			entity.setProjectInfo(projectInfo);
		}
		if (ApplyConstants.USERTYPE_CORPORATION_INFO.equals(certType)) {
			CorporationRequestCode requestCode = (CorporationRequestCode) userOrCorpWhitelist;
			ProjectInfo projectInfo = new ProjectInfo();
			projectInfo.setId(requestCode.getProjectId());
			entity.setProjectInfo(projectInfo);
		}

		List<CertInfo> certInfoList = certInfoDao.getNodownCertinfo(entity);

		if (certInfoList != null && certInfoList.size() > 0) {
			return certInfoList.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * lihuilong 获取散户证书未下载状态下的最新的一条证书信息
	 * @param certSubject
	 * @return
	 */
	public CertInfo getSanhuNodownCertinfo(String certSubject) {
		
		CertInfo entity = new CertInfo();
		entity.setCertSubject(certSubject);
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setIsDefaultProject(ApplyConstants.ISDEAFULT_PRO_YES);
		List<ProjectInfo> projectInfos = projectInfoDao
				.findList(projectInfo);
		
		if(projectInfos != null && projectInfos.size() > 0){
			entity.setProjectInfo(projectInfos.get(0));
			
			List<CertInfo> certInfoList = certInfoDao.getNodownCertinfo(entity);
			
			if (certInfoList != null && certInfoList.size() > 0) {
				return certInfoList.get(0);
			} else {
				return null;
			}
		}else{
			return null;
		}
		
	}

	/**
	 * lihuilong 获取变更下 存在证书，切证书oldcertsn 是该序列号的，查找出最新的一条证书序列号
	 * 
	 * @param certSn
	 * @return
	 */
	public String getChangeNewCertinfo(String certSn) {

		CertInfo entity = new CertInfo();
		entity.setOldCertSn(certSn);
		CertapplyInfo certapplyInfo = new CertapplyInfo();
		entity.setCertapplyInfo(certapplyInfo);

		List<CertInfo> certInfoList = certInfoDao.findList(entity);
		if (certInfoList != null && certInfoList.size() > 0) {
			return getChangeNewCertinfo(certInfoList.get(0).getCertSn());
		} else {
			return certSn;
		}
	}
	
	public CertInfo getNewCertinfoByOldCertSn(CertInfo certInfo) {

		List<CertInfo> certInfoList = certInfoDao.findList(certInfo);
		if (certInfoList != null && certInfoList.size() > 0) {
			CertInfo info = new CertInfo();
			info.setOldCertSn(certInfoList.get(0).getCertSn());
			return getNewCertinfoByOldCertSn(info);
		} else {
			return certInfoDao.getByCertSnAndCertStatus(certInfo.getOldCertSn());
		}
	}
	@Override
	public Map<String,String> getAllowUpdate(String certSn) throws ParseException{
		
		Map<String,String> map = new HashMap<String, String>();
		
		// 通过cerSn获取证书相关信息
		CertVo certVo = getcertVoBycertsn(certSn);
		
		CertInfo certInfo = certVo.getCertInfo();
		
		CertapplyInfo certapplyInfo = certVo.getCertapplyInfo();
		// 获取原有证书生效日期与项目的 允许跟新时间做对比
		BigDecimal notBefore = certInfo.getNotBefore();
		
		// 获取项目id
		String projectId = certapplyInfo.getProjectId();
		
		ProjectInfo projectInfo = projectInfoDao.get(projectId);
		if(certapplyInfo != null && certInfo != null && projectInfo != null){
			
			// 项目允许跟新天数
			int days = projectInfo.getAllowUpdateDay();
			int betweenDays = (int) DateUtils.getDistanceOfTwoDate(
					DateUtil.typeToDate(notBefore), new Date());
			if (betweenDays - days >= 0) {
				map.put("flag", "0");
				map.put("message", "允许更新！");
			}else{
				map.put("flag", "1");
				map.put("message", "未到允许更新时间,不允许操作！");
			}
		}else{
			map.put("flag", "1");
			map.put("message", "不允许更新！");
		}
		return map;
	}
	
	/**
	 * 证书跟新后，注销旧的证书实体，保存新的证书对象 lhl
	 * @param certEntity
	 * @param refCode
	 * @param reqSn
	 * @param certapplyVo
	 * @return
	 */
	public String saveUpdatecertVo(CertInfo certEntity,String refCode,String reqSn,CertapplyVo certapplyVo,String isRecovery){
		
		// 将原证书注销
		certEntity.setCertStatus("Revoke");
		certEntity.setCertStatusMyb("Revoke");
		certEntity.setIsRecovery(isRecovery);
		certInfoDao.update(certEntity);

		// 通过证书序列号去ra查找,保存新证书实体---------开始
		CertInfo certInfoNew = certInfoService.getCertBycertsnFromra(refCode);
		certInfoNew.setIsRecovery(isRecovery);
		certInfoNew.setCertStatusMyb("Undown");
		certInfoNew.preInsert();
		certInfoDao.insert(certInfoNew);
		// 通过证书序列号去ra查找,保存新证书实体---------结束
		//修改证书应用日志------开始
		CertAppLog certAppLog = new CertAppLog();
		certAppLog.setCertSn(certEntity.getCertSn());
		List<CertAppLog> certAppLogList = certAppLogService.getCertAppLogs(certAppLog);
		if(certAppLogList != null && certAppLogList.size() > 0){
			for(CertAppLog entity : certAppLogList){
				entity.setCertSn(certInfoNew.getCertSn());
				certAppLogService.update(certAppLog);
			}
		}
		//修改证书应用日志------结束
		// 保存证书申请实体
		saveCertapplyVo(certapplyVo, certInfoNew.getId(),refCode, reqSn);
		
		return certInfoNew.getCertSn();
		
	}

	/**
	 * 证书更新时如果证书未到期,则在现有有效期上加上更新天数
	 * 如果已过期 则有效期为更新天数
	 * glq 2017-07-03
	 * @param certVo
	 * @return
     */
	public Integer certUpdateValidity(CertVo certVo,CertInfo certInfoOld) {
		Integer certValidity = 0;
		try {
			String projectId = certVo.getCertapplyInfo().getProjectId();
			ProjectInfo projectInfo = projectInfoDao.get(projectId);
			Date notafter= null;
			notafter = DateUtil.typeToDate(certInfoOld.getNotAfter());
			int accountValidity = DateUtil.getDiffDay(new Date(), notafter);
			if(accountValidity <= 0){
				accountValidity = 0;
			}
			certValidity = accountValidity + projectInfo.getCertValidity();
			return certValidity;
		} catch (ParseException e) {
			logger.warn("证书更新：{} 证书更新天数获取异常!" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 补发忘记密码，证书有效期，有限期减去已经使用天数
	 * @return
	 */
	public Integer certValitity(CertVo certVo){
		Integer certValidity = 0;
		try {
			CertInfo certInfo = certVo.getCertInfo();
			Date notBefore= null;
			notBefore = DateUtil.typeToDate(certInfo.getNotBefore());
			int accountValidity = DateUtil.getDiffDay(notBefore, new Date());
			if(accountValidity <= 0){
				accountValidity = 0;
			}
			certValidity = certInfo.getCertValidity()-accountValidity;
			return certValidity;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

}
