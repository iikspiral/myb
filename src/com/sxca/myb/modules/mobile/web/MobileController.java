package com.sxca.myb.modules.mobile.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxca.myb.modules.mobile.util.GetCertSubjectUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.common.mapper.JsonMapper;
import com.sxca.myb.common.utils.DateUtils;
import com.sxca.myb.common.utils.SMSUtil;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.cert.service.CertInfoService;
import com.sxca.myb.modules.cert.service.CertapplyBusinessService;
import com.sxca.myb.modules.cert.service.CertapplyInfoService;
import com.sxca.myb.modules.cert.vo.CertVo;
import com.sxca.myb.modules.certCtml.entity.CertCtml;
import com.sxca.myb.modules.certCtml.service.CertCtmlService;
import com.sxca.myb.modules.config.apkversion.entity.APKVersion;
import com.sxca.myb.modules.config.apkversion.service.APKVersionService;
import com.sxca.myb.modules.config.appsystem.entity.ApplicationSystem;
import com.sxca.myb.modules.config.appsystem.service.ApplicationSystemService;
import com.sxca.myb.modules.config.corporcode.entity.CorporationRequestCode;
import com.sxca.myb.modules.config.corporcode.service.CorporcodeService;
import com.sxca.myb.modules.config.logman.entity.CertAppLog;
import com.sxca.myb.modules.config.logman.service.CertAppLogService;
import com.sxca.myb.modules.config.problem.entity.Problem;
import com.sxca.myb.modules.config.problem.service.ProblemService;
import com.sxca.myb.modules.config.sysconfig.entity.SystemConfig;
import com.sxca.myb.modules.config.sysconfig.service.SystemConfigService;
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;
import com.sxca.myb.modules.config.whitelist.service.WhiteListService;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.idinfo.service.CorporationInfoService;
import com.sxca.myb.modules.idinfo.service.CorporationUserInfoService;
import com.sxca.myb.modules.idinfo.service.CorporationUserRelationnService;
import com.sxca.myb.modules.idinfo.service.UserInfoService;
import com.sxca.myb.modules.mobile.entity.FaceInfo;
import com.sxca.myb.modules.mobile.entity.UserConversation;
import com.sxca.myb.modules.mobile.service.FaceInfoService;
import com.sxca.myb.modules.mobile.service.FaceResultInfoService;
import com.sxca.myb.modules.mobile.service.MobileApplyInfoService;
import com.sxca.myb.modules.mobile.service.MobileService;
import com.sxca.myb.modules.mobile.service.MobileVerifyService;
import com.sxca.myb.modules.mobile.service.UserConversationService;
import com.sxca.myb.modules.mobile.util.Result;
import com.sxca.myb.modules.pro.entity.ProjectInfo;
import com.sxca.myb.modules.pro.service.ProjectInfoService;
import com.sxca.myb.modules.sms.entity.SMSPlatform;
import com.sxca.myb.modules.sms.service.SMSPlatformService;
import com.sxca.myb.modules.sms.service.SMSTemplateService;

/**
 * 移动端手机Http接口
 * 
 * @author lihuilong
 * @date : 2017年4月24日 下午3:19:57
 */
@Controller
@RequestMapping(value = "${frontPath}/mobile")
public class MobileController extends BaseController {

	@Autowired
	private SMSPlatformService smsPlatformService;
	@Autowired
	private SMSTemplateService smsTemplateService;
	@Autowired
	private CertInfoService certInfoService;
	@Autowired
	private MobileService mobileService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CorporationInfoService corporationInfoService;
	@Autowired
	private WhiteListService whiteListService;
	@Autowired
	private CorporcodeService corporcodeService;
	@Autowired
	private CertapplyInfoService certapplyInfoService;
	@Autowired
	private MobileApplyInfoService mobileApplyInfoService;
	@Autowired
	private MobileVerifyService mobileVerifyService;
	@Autowired
	private UserConversationService userConversationService;
	@Autowired
	private SystemConfigService systemConfigService;
	@Autowired
	private CertAppLogService certAppLogsService;
	@Autowired
	private CorporationUserRelationnService corporationUserRelationnService;
	@Autowired
	private ProjectInfoService projectInfoService;
	@Autowired
	private FaceResultInfoService faceResultInfoService;
	@Autowired
	private FaceInfoService faceInfoService;
	@Autowired
	private CertapplyBusinessService certapplyBusinessService;
	@Autowired
	private CorporationUserInfoService corporationUserInfoService;
	@Autowired
	private ProblemService problemService;
	@Autowired
	private CertCtmlService certCtmlService;
	@Autowired
	private APKVersionService apkVersionService;
	@Autowired
	private ApplicationSystemService applicationSystemService;

	/**
	 * 根据手机号获取验证码,并进行验证码的保存 glq 2017-04-27
	 * 
	 * @param deviceType
	 *            设备类型
	 * @param deviceNum
	 *            设备号
	 * @param phoneNum
	 *            手机号
	 * @return
	 */
	@RequestMapping(value = "getVerify", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getVerify(String deviceType, String deviceNum, String phoneNum) {
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(deviceType)
				|| StringUtils.isBlank(deviceNum)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		// 获取有效的短信平台
		SMSPlatform sms = new SMSPlatform();
		sms.setState("1");
		List<SMSPlatform> listSMS = smsPlatformService.findList(sms);
		String templateId = smsTemplateService.getTemplateId("myb_yzm");
		// 生成验证码
		String verifyCode = mobileService.generateVerifyCode();
		System.out.println("==============" + verifyCode + "===============");
		// 保存或更新验证码信息
		mobileVerifyService.save(deviceType, deviceNum, phoneNum, verifyCode);
		// 发送验证码
		boolean sendResult = SMSUtil.sendSMS(phoneNum, verifyCode + ",5分钟",
		listSMS,templateId);
		//boolean sendResult = true;
		if (sendResult) {
			result.put("flag", "0");
			result.put("verifyCode", verifyCode);
			result.put("message", "获取验证码成功");
		} else {
			result.put("flag", "1");
			result.put("verifyCode", verifyCode);
			result.put("message", "获取验证码失败");
		}
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * 验证验证码输入是否正确 glq 2017-04-27
	 * 
	 * @param phoneNum
	 * @param verify
	 * @return
	 */
	@RequestMapping(value = "/validateVerify", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String validateVerify(String phoneNum, String verify) {
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(verify)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		boolean verifyIsSucc = mobileService.validateVerify(phoneNum, verify);
		if (verifyIsSucc) {
			result.put("flag", "0");
			result.put("message", "验证码通过");
		} else {
			result.put("flag", "1");
			result.put("message", "验证码验证失败");
		}
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * 身份验证接口（其他:除新制,变更） glq 2017-05-03
	 * 
	 * @param idCard
	 * @param certSn
	 * @param phoneNum
	 * @param verify
	 * @return
	 */
	@RequestMapping(value = "/validateIdentity", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String validateIdentity(String idCard, String certSn,
			String phoneNum, String verify, String deviceNum) {
		Map<String, Object> result = new HashMap<>();
		boolean verifyIsSucc = true;
		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(idCard)
				|| StringUtils.isBlank(certSn)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		if (StringUtils.isNotBlank(verify)) {
			// 验证验证码是否正确
			verifyIsSucc = mobileService.validateVerify(phoneNum, verify);
		}
		if (verifyIsSucc) {
			// 用户身份认证是否正确
			boolean indetityIsSucc = mobileService.validateIdentity(idCard,
					certSn, phoneNum);
			if (indetityIsSucc) {
				// 更新会话时间
				userConversationService.createOrUpdate(phoneNum, deviceNum);
				result.put("flag", "0");
				result.put("message", "身份验证验证成功");
			} else {
				result.put("flag", "1");
				result.put("message", "身份验证验证失败");
			}
		} else {
			result.put("flag", "1");
			result.put("message", "验证码验证失败");
		}
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * 获取当前用户已有证书 glq 2017-05-02
	 * 
	 * @param phoneNum
	 * @param idCard
	 * @return
	 */
	@RequestMapping(value = "/getCert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCert(String phoneNum, String idCard) {
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(idCard)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		// 获取当前用户使用中的个人证书及企业证书
		List<Map<String, String>> certinfoMap = mobileService.getCert(phoneNum,
				idCard);
		if (certinfoMap != null && certinfoMap.size() > 0) {
			return JsonMapper.toJsonString(certinfoMap);
		}
		result.put("flag", "1");
		result.put("message", "获取已有证书异常");
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * 待申请证书申请下载接口 glq 2017-05-04
	 * 
	 * @param certType
	 *            证书类型
	 * @param id
	 *            白名单ID
	 * @param p10
	 *            p10下载码
	 * @param deviceType
	 *            设备类型
	 * @param deviceNum
	 *            设备号
	 * @return
	 */
	@RequestMapping(value = "/getMobileCert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMobileCert(String certType, String id, String p10,String deviceType, String deviceNum, String certSubject) {
		
		Map<String, Object> result = new HashMap<>();
		
		if (StringUtils.isBlank(certType) || StringUtils.isBlank(id)|| StringUtils.isBlank(p10) || StringUtils.isBlank(deviceType)|| StringUtils.isBlank(deviceNum)|| StringUtils.isBlank(certSubject)) {
			
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
			
		}

		Object userOrCorpWhitelist = null;
		// 个人用户，获取个人白名单信息
		if (ApplyConstants.USERTYPE_USERINFO.equals(certType)) {
			userOrCorpWhitelist = whiteListService.get(id);
		}
		// 企业用户，获取企业白名单信息
		if (ApplyConstants.USERTYPE_CORPORATION_INFO.equals(certType)) {
			userOrCorpWhitelist = corporcodeService.get(id);
		}

		if (userOrCorpWhitelist != null) {
			
			// 保存移动端申请业务信息------开始ggw
			String applyBusinessId = certapplyBusinessService.findByWhitListId(userOrCorpWhitelist, certType, certSubject);
			if (applyBusinessId == null) {
				applyBusinessId = certapplyBusinessService.saveCertapplyBusiness(certType, deviceNum, deviceType,userOrCorpWhitelist, certSubject);
			}
			// 保存移动端申请业务信息------结束ggw
			
			// 保存移动端申请信息------开始
			String mobileApplyInfoId = mobileApplyInfoService.saveMobileApplyInfo(certType, deviceNum, deviceType,userOrCorpWhitelist, certSubject, applyBusinessId);
			// 保存移动端申请信息------结束
			
			if (StringUtils.isNotBlank(mobileApplyInfoId)) {
				
				// 证书申请并审核-------开始
				String certSn = mobileService.mobileAddApply(certType,userOrCorpWhitelist, ApplyConstants.USERINFO_NORMAL,mobileApplyInfoId, deviceType, deviceNum,applyBusinessId);
				// 证书申请并审核-------结束
				
				if (StringUtils.isNotBlank(certSn)) {
					
					// 证书下载-------------开始
					String downResult = mobileService.mobileCertDown(certSn,p10, mobileApplyInfoId);
					// 证书下载-------------结束
					
					if (StringUtils.isNotBlank(downResult)) {
						result.put("flag", "0");
						result.put("p7b", downResult);
						return JsonMapper.getInstance().toJson(result);
					}
				}
			}
		}
		
		result.put("flag", "1");
		result.put("message", "证书申请下载失败");
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * 散户证书获取主题接口 glq 2017-05-08
	 * 
	 * @param phoneNum
	 * @param name
	 * @param idCard
	 * @param deviceType
	 * @param deviceNum
	 * @param verify
	 * @return
	 */
	@RequestMapping(value = "/getSubjectOther", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getSubjectOther(String phoneNum, String name, String idCard,
			String deviceType, String deviceNum, String verify) {
		Map<String, Object> result = new HashMap<>();
		boolean verifyIsSucc = true;
		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(name)
				|| StringUtils.isBlank(idCard)
				|| StringUtils.isBlank(deviceType)
				|| StringUtils.isBlank(deviceNum)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		if (StringUtils.isNotBlank(verify)) {
			// 验证验证码是否正确
			verifyIsSucc = mobileService.validateVerify(phoneNum, verify);
		}
		if (verifyIsSucc) {
			List<SystemConfig> systemConfigs = systemConfigService
					.findList(new SystemConfig());
			if (systemConfigs != null && systemConfigs.size() > 0) {
				if ("1".equals(systemConfigs.get(0).getIsuseDefaultProject())) {
					result.put("flag", "1");
					result.put("message", "您无权限使用,如有疑问请拨打电话联系"+systemConfigs.get(0).getPhone()+"");
					return JsonMapper.getInstance().toJson(result);
				}
				ProjectInfo projectInfo = new ProjectInfo();
				projectInfo
						.setIsDefaultProject(ApplyConstants.ISDEAFULT_PRO_YES);
				List<ProjectInfo> projectInfos = projectInfoService
						.findList(projectInfo);
				if (projectInfos != null && projectInfos.size() > 0) {
					// 验证该用户是否已有使用中的证书或者未审核的证书申请
					result = mobileService.getCertOther(phoneNum, name, idCard,
							projectInfos.get(0).getId());
					// 如果返回结果状态为1,则直接返回
					if (result != null && "1".equals(result.get("flag"))) {
						return JsonMapper.getInstance().toJson(result);
					}
					// 如果返回结果状态为0,并且map中证书主题为空(已存在使用中或者未下载的证书,但未成功下载到App),则进行拼接证书主题
					if (StringUtils.isBlank((String) result
							.get("subjectOrSubjectRule"))) {
						UserInfo userInfo = userInfoService.get((String) result
								.get("userId"));
						// String certSubject =
						// mobileService.getSubject(projectInfos.get(0).getId(),(String)result.get("userId"),ApplyConstants.USERTYPE_USERINFO);
						String certSubject = GetCertSubjectUtil.getSubject(
								projectInfos.get(0), userInfo,
								ApplyConstants.USERTYPE_USERINFO,"");
						CertCtml certCtml = certCtmlService.get(projectInfos
								.get(0).getCertTemplate());
						result.put("subjectOrSubjectRule", certSubject);
						result.put("secretType", certCtml.getSecretType());
					}
				}else {
					result.put("flag", "1");
					result.put("message", "未配置默认项目,请联系管理员!");
				}
			}
		} else {
			result.put("flag", "1");
			result.put("message", "验证码验证失败");
		}
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * 散户证书申请下载接口 glq 2017-05-08
	 * 
	 * @param userId
	 * @param p10
	 * @param deviceType
	 * @param deviceNum
	 * @return
	 */
	@RequestMapping(value = "/getMobileCertOther", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMobileCertOther(String userId, String p10,String deviceType, String deviceNum, String certSubject) {
		
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(userId) || StringUtils.isBlank(p10) || StringUtils.isBlank(deviceType) || StringUtils.isBlank(deviceNum) || StringUtils.isBlank(certSubject)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		
		UserInfo userInfoOther = userInfoService.get(userId);
		
		if (userInfoOther != null) {
			
			// 保存移动端申请业务信息------开始ggw
			String applyBusinessId = certapplyBusinessService.findByWhitListIdOther(userInfoOther, certSubject);
			if (applyBusinessId == null) {
				applyBusinessId = certapplyBusinessService.saveCertapplyBusinessOther(deviceNum, deviceType,userInfoOther, certSubject);
			}
			// 保存移动端申请业务信息------结束ggw
			
			// 保存移动端申请信息------开始
			String mobileApplyInfoId = mobileApplyInfoService.saveMobileApplyInfoOther(deviceNum, deviceType,userInfoOther, ApplyConstants.OPTTYPE_ADD, "",certSubject, applyBusinessId);
			// 保存移动端申请信息------结束
			
			if (StringUtils.isNotBlank(mobileApplyInfoId)) {
				
				// 证书申请并审核-------开始
				String certSn = mobileService.mobileAddApply(ApplyConstants.USERTYPE_USERINFO, userInfoOther,ApplyConstants.USERINFO_OTHER, mobileApplyInfoId,deviceType, deviceNum, applyBusinessId);
				// 证书申请并审核-------结束
				
				if (StringUtils.isNotBlank(certSn)) {
					
					// 证书下载-------------开始
					String downResult = mobileService.mobileCertDown(certSn,p10, mobileApplyInfoId);
					// 证书下载-------------结束
					if (StringUtils.isNotBlank(downResult)) {
						result.put("flag", "0");
						result.put("p7b", downResult);
						return JsonMapper.getInstance().toJson(result);
					}
					
				} 
			}
		}
		result.put("flag", "1");
		result.put("message", "证书申请下载失败");
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * 证书补发申请下载接口 glq 2017-05-09
	 * 
	 * @param certSn
	 *            证书序列号
	 * @param p10
	 *            证书下载p10码
	 * @param deviceNum
	 *            设备号
	 * @param deviceType
	 *            设备类型
	 * @return
	 */
	@RequestMapping(value = "/reissueMobileCert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String reissueMobileCert(String certSn, String p10,String deviceNum, String deviceType, String certSubject) {
		
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(certSn) || StringUtils.isBlank(p10) || StringUtils.isBlank(deviceType) || StringUtils.isBlank(deviceNum) || StringUtils.isBlank(certSubject)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		
		CertVo certVo = mobileService.getcertVoBycertsn(certSn);
		
		CertInfo certInfoSerch = new CertInfo();
		certInfoSerch.setOldCertSn(certSn);
		CertInfo certInfo = mobileService.getNewCertinfoByOldCertSn(certInfoSerch);
		if(certInfo!=null) {
			if("Undown".equals(certInfo.getCertStatus())) {
				String applyType = mobileApplyInfoService.getApplyType(certSn);
				if(!ApplyConstants.OPTTYPE_OTHER_REISSUE.equals(applyType)&&applyType!=null) {
					result.put("flag", "1");
					result.put("message", "已存在未下载的" + ApplyConstants.getApplyTypeName(applyType) +"证书!");
					return JsonMapper.getInstance().toJson(result);
				}else {
					// 保存移动端申请业务信息------开始ggw
					String applyBusinessId = certapplyBusinessService.findByCertSn(certSn, ApplyConstants.OPTTYPE_OTHER_REISSUE);
					if (applyBusinessId == null) {
						applyBusinessId = certapplyBusinessService.saveCertapplyBusiness(deviceNum, deviceType,ApplyConstants.OPTTYPE_OTHER_REISSUE, certVo);
					}
					// 保存移动端申请业务信息------结束ggw
					// 保存移动端申请信息------开始
					String mobileApplyInfoId = mobileApplyInfoService.saveMobileApplyInfo(deviceNum, deviceType,ApplyConstants.OPTTYPE_OTHER_REISSUE, certVo, "",applyBusinessId,"");
					// 保存移动端申请信息------结束
					String downResult = mobileService.mobileCertDown(certInfo.getCertSn(),p10, mobileApplyInfoId);
					// 证书下载-------------结束
					if(downResult!=null) {
						result.put("flag", "0");
						result.put("p7b", downResult);
						return JsonMapper.getInstance().toJson(result);
					}else {
						result.put("flag", "1");
						result.put("message", "下载失败!");
						return JsonMapper.getInstance().toJson(result);
					}
					
				}
			}
		}
		
		
		if (certVo != null) {
			
			// 保存移动端申请业务信息------开始ggw
			String applyBusinessId = certapplyBusinessService.findByCertSn(certSn, ApplyConstants.OPTTYPE_OTHER_REISSUE);
			if (applyBusinessId == null) {
				applyBusinessId = certapplyBusinessService.saveCertapplyBusiness(deviceNum, deviceType,ApplyConstants.OPTTYPE_OTHER_REISSUE, certVo);
			}
			// 保存移动端申请业务信息------结束ggw
			
			// 保存移动端申请信息------
			String mobileApplyInfoId = mobileApplyInfoService.saveMobileApplyInfo(deviceNum, deviceType,ApplyConstants.OPTTYPE_OTHER_REISSUE, certVo, "",applyBusinessId,"");
			// 保存移动端申请信息------结束
			
			if (StringUtils.isNotBlank(mobileApplyInfoId)) {
				
				// 证书申请并审核-------开始
				String newCertSn = mobileService.mobileUpdateApply(certSn,certVo, mobileApplyInfoId, applyBusinessId);
				// 证书申请并审核-------结束
				
				if (StringUtils.isNotBlank(newCertSn)) {
					// 证书下载-------------开始
					String downResult = mobileService.mobileCertDown(newCertSn,p10, mobileApplyInfoId);
					// 证书下载-------------结束
					result.put("flag", "0");
					result.put("p7b", downResult);
					return JsonMapper.getInstance().toJson(result);
				}
			}
		}
		result.put("flag", "1");
		result.put("message", "证书补发申请下载失败");
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * 人脸信息收集(保留) glq 2017-05-11
	 * 
	 * @param name
	 *            姓名
	 * @param phoneNum
	 *            手机号
	 * @param idCard
	 *            身份证号
	 * @param num
	 *            验证次数
	 * @param result
	 *            人脸结果
	 * @param picString
	 *            人脸图片字符串
	 * @return
	 */
	@RequestMapping(value = "/getCertFaceResultInfo", produces = "application/json;charset=UTF-8")
	public void getCertFaceResultInfo(String name, String phoneNum,
			String idCard, String num, String result, String picString,
			String deviceNum) {
		if (StringUtils.isBlank(name) || StringUtils.isBlank(phoneNum)
				|| StringUtils.isBlank(idCard) || StringUtils.isBlank(num)
				|| StringUtils.isBlank(result)
				|| StringUtils.isBlank(picString)
				|| StringUtils.isBlank(deviceNum)) {
			System.out.println("传入信息有误");
		} else {
			// 保存人脸结果信息
			faceResultInfoService.createFaceResultInfo(name, phoneNum, idCard,
					num, result, picString);
			System.out.println("保存人脸信息结果成功");
			// 更新或保存会话信息
			userConversationService.createOrUpdate(phoneNum, deviceNum);
			System.out.println("保存或更新会话信息成功");
		}
	}

	/**
	 * 人脸信息收集 glq 2017-05-11
	 * 
	 * @param name
	 *            姓名
	 * @param phoneNum
	 *            手机号
	 * @param idCard
	 *            身份证号
	 * @param picString
	 *            人脸图片字符串
	 * @return
	 */
	@RequestMapping(value = "/getFaceInfo", produces = "application/json;charset=UTF-8")
	public void getFaceInfo(String name, String phoneNum, String idCard,
			String picString) {
		if (StringUtils.isBlank(name) || StringUtils.isBlank(phoneNum)
				|| StringUtils.isBlank(idCard)
				|| StringUtils.isBlank(picString)) {
			System.out.println("传入信息有误");
		} else {
			FaceInfo faceInfo = new FaceInfo();
			faceInfo.setIdcard(idCard);
			faceInfo.setName(name);
			faceInfo.setPhoneNum(phoneNum);
			faceInfo.setPicString(picString);
			faceInfoService.insert(faceInfo);
			System.out.println("保存人脸信息成功");
		}
	}

	/**
	 * 证书申请回调接口 glq 2017-05-19
	 * 
	 * @param certSn
	 *            证书序列号
	 * @return
	 */
	@RequestMapping(value = "/getCertCallBack", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCertCallBack(String certSn, String oldCertSn,
			String applyType) {
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(certSn)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		boolean isSucc = mobileService.updateCertCallBack(certSn, oldCertSn,
				applyType);
		if (isSucc) {
			result.put("flag", "0");
			result.put("message", "证书申请回调成功");
			return JsonMapper.getInstance().toJson(result);
		}
		result.put("flag", "1");
		result.put("message", "证书申请回调失败");
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * 身份验证接口（新制待申请）lihuilong
	 * 
	 * @param certType
	 *            证书类型
	 * @param idCard
	 *            身份证号码
	 * @param whiteListId
	 *            白名单id
	 * @param phoneNum
	 *            手机号码
	 * @param verify
	 *            验证码
	 * @return
	 */
	@RequestMapping(value = "/validateIdentityNew", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String validateIdentityNew(String certType, String idCard,
			String whiteListId, String phoneNum, String verify,
			String deviceNum, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = Maps.newHashMap();
		// 判断验证码是否为空
		if (StringUtils.isNotBlank(verify)) {
			boolean flag = mobileService.validateVerify(phoneNum, verify);
			// 先判断验证码是否正确
			if (flag) {// 校验通过
				map = mobileService.validateIdentityNew(certType, idCard,
						whiteListId, phoneNum);
				// 如果验证码校验成功，并且身份认证也成功，去修改会话信息
				if ("0".equals(map.get("flag"))) {
					userConversationService.createOrUpdate(phoneNum, deviceNum);
				}
			} else {// 校验失败
				map.put("flag", "1");
				map.put("message", "验证码校验失败!");
			}

		} else {
			map = mobileService.validateIdentityNew(certType, idCard,
					whiteListId, phoneNum);
		}

		return JsonMapper.getInstance().toJson(map);

	}

	/**
	 * 是否可申请证书接口
	 * @author lhl 
	 * @param name
	 * @param phoneNum
	 * @param idCard
	 * @param deviceNum
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getIsMobileApply", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getIsMobileApply(String name, String phoneNum, String idCard,
			String deviceNum, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(phoneNum)
				&& StringUtils.isNotBlank(idCard)
				&& StringUtils.isNotBlank(deviceNum)) {
			Map<String, Object> MobileApplyResult = mobileService
					.getIsMobileApply(name, phoneNum, idCard, deviceNum);
			return JsonMapper.getInstance().toJson(MobileApplyResult);
		} else {
			Map<String, Object> map = Maps.newHashMap();
			map.put("flag", "1");
			map.put("message", "校验失败：参数不能为空!");
			return JsonMapper.getInstance().toJson(map);
		}

	}

	/**
	 * 取当前用户待申请证书
	 * @author lhl
	 * @param phoneNum
	 * @param idCard
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getWaitApplyCert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getWaitApplyCert(String phoneNum, String idCard,
			HttpServletRequest request, HttpServletResponse response) {
		
		if(StringUtils.isBlank(phoneNum) || StringUtils.isBlank(idCard)){
			Map<String, Object> map = Maps.newHashMap();
			map.put("flag", "1");
			map.put("message", "参数不能为空!");
			return JsonMapper.getInstance().toJson(map);	
		}else{
			List<Map<String, Object>> resultList = mobileService.getWaitApplyCert(
					phoneNum, idCard);
			return JsonMapper.getInstance().toJson(resultList);
		}

	}

	/**
	 * 获取当前用户可变更证书
	 * 
	 * @param phoneNum
	 *            手机号码
	 * @param idCard
	 *            身份证号码
	 * @return
	 */
	@RequestMapping(value = "/getChangeCert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getChangeCert(String phoneNum, String idCard,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		
		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(idCard)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		
		List<Map<String, Object>> resultList = mobileService.getChangeCert(
				phoneNum, idCard);

		return JsonMapper.getInstance().toJson(resultList);
	}

	/**
	 * 获取会话状态
	 * 
	 * @author ggw
	 * @param deviceNum
	 *            设备号
	 * @param phoneNum
	 *            手机号
	 * @return
	 */
	@RequestMapping(value = "validateConversation", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String validateConversation(String deviceNum, String phoneNum) {
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(deviceNum)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}

		List<UserConversation> userConversations = userConversationService
				.findListByPhoneNum(phoneNum);
		UserConversation userConversation = null;
		if (userConversations != null && userConversations.size() > 0) {
			userConversation = userConversations.get(0);
			List<SystemConfig> systemConfigs = systemConfigService
					.findList(new SystemConfig());
			SystemConfig systemConfig = null;
			if (systemConfigs != null && systemConfigs.size() > 0) {
				systemConfig = systemConfigs.get(0);
			}
			String conversationDate = systemConfig.getConversationDate();
			Date createConversationDate = userConversation
					.getConversationDate();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(createConversationDate);
			calendar.add(calendar.HOUR_OF_DAY,
					Integer.parseInt(conversationDate));
			createConversationDate = calendar.getTime();
			Boolean flag = createConversationDate.before(new Date());

			if (!deviceNum.equals(userConversation.getDeviceNum())) {
				result.put("flag", "1");
				result.put("message", "设备更换重新登录");
				return JsonMapper.getInstance().toJson(result);
			} else if (flag) {
				result.put("flag", "1");
				result.put("message", "超时重新登录");
				return JsonMapper.getInstance().toJson(result);
			} else {
				result.put("flag", "0");
				result.put("message", "不用重新登录");
				return JsonMapper.getInstance().toJson(result);
			}
		} else {
			result.put("flag", "1");
			result.put("message", "未登录重新登录");
			return JsonMapper.getInstance().toJson(result);
		}
	}

	/**
	 * 企业管理员获取企业所有证书
	 * 
	 * @author ggw
	 * @param phoneNum
	 *            手机号
	 * @param idCard
	 *            身份证号
	 * @return
	 */
	@RequestMapping(value = "/getCertByCorAdm", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCertByCorAdm(String phoneNum, String idCard) {
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(idCard)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}

		List<String> corporationIds = corporationUserRelationnService
				.getCorporationId(phoneNum, idCard);
		if (corporationIds != null && corporationIds.size() > 0) {
			List<Map<String, String>> certinfoList = mobileService
					.getCertByCorIds(corporationIds);
			if (certinfoList != null && certinfoList.size() > 0) {
				return JsonMapper.getInstance().toJson(certinfoList);
			}
		}
		return null;
	}

	/**
	 * description:保存证书应用日志 author:wyf
	 * 
	 * @param certSn
	 * @param systemFlag
	 * @param operationName
	 */

	@RequestMapping(value = "/saveCertappLogs", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveCertappLogs(String certSn, String systemFlag,String operationName,String busDes,String optType) {
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(certSn) || StringUtils.isBlank(systemFlag)|| StringUtils.isBlank(operationName)||StringUtils.isBlank(busDes)||StringUtils.isBlank(optType)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.toJsonString(result);
		} else {
			try {
				CertAppLog certappLogs = new CertAppLog();
				certappLogs.setOperationDate(new Date());
				certappLogs.setCertSn(certSn);
				certappLogs.setSystemFlag(systemFlag);
				certappLogs.setOperationName(operationName);
				certappLogs.setBusDes(busDes);
				certappLogs.setOptType(optType);
				certAppLogsService.save(certappLogs);
				result.put("flag", "0");
				result.put("message", "保存证书应用日志成功");
			} catch (Exception e) {
				result.put("flag", "1");
				result.put("message", "保存证书应用日志失败");
			}
		}
		return JsonMapper.toJsonString(result);
	}

	/**
	 * description:获取证书应用日志------------------------------------- author:wyf
	 * 
	 * @param certSn
	 */
	@RequestMapping(value = "/getCertappLogs", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCertappLogs(String certSn) {
		Result result = new Result();
		if (StringUtils.isBlank(certSn)) {
			result.setFlag("0");
			result.setMessage("传入信息有误");
			System.out.println("获取证书应用日志失败");
			return JsonMapper.toJsonString(result);
		} else {
			List<Map<String, Object>> resultList = new ArrayList<>();
			CertAppLog certappLogs = new CertAppLog();
			certappLogs.setCertSn(certSn);

			/** 查找操作人 */
			CertapplyInfo certapplyInfo = new CertapplyInfo();
			certapplyInfo.setCertSn(certSn);
			List<CertapplyInfo> certApplyInfolist = certapplyInfoService
					.findList(certapplyInfo);
			String optName = null;
			if (certApplyInfolist != null && certApplyInfolist.size() > 0) {
				CertapplyInfo certapply = certApplyInfolist.get(0);
				CorporationUserInfo corporationUserInfo = corporationUserInfoService
						.get(certapply.getCorpUserId());
				optName = corporationUserInfo.getUserName();
			}
			/** 查找操作人 */

			List<CertAppLog> logs = certAppLogsService
					.getCertAppLogs(certappLogs);

			if (logs != null && logs.size() > 0) {
				for (int i = 0; i < logs.size(); i++) {
					CertAppLog log = logs.get(i);
					Map<String, Object> certAppLogsMap = new HashMap<String, Object>();
					certAppLogsMap.put("optName", optName);
					certAppLogsMap.put("systemFlag", log.getSystemFlag());// log.getSystemFlag()
					certAppLogsMap.put("operationName", log.getOperationName());
					certAppLogsMap.put("optDate", DateUtils.formatDate(
							log.getOperationDate(), "yyyy/MM/dd/HH:mm:ss"));
					resultList.add(certAppLogsMap);
				}
				return JsonMapper.getInstance().toJson(resultList);
			}
		}
		return null;
	}

	/**
	 * description:修改手机号接口 
	 * @author:lhl
	 * @param idCard
	 * @param phoneNum
	 * @return
	 */
	@RequestMapping(value = "/changePhoneNum", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String changePhoneNum(String name, String idCard,String oldPhoneNum, String newPhoneNum, String deviceNum,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(name) || StringUtils.isBlank(idCard)
				|| StringUtils.isBlank(oldPhoneNum) || StringUtils.isBlank(newPhoneNum)
				|| StringUtils.isBlank(deviceNum)) {
			result.put("flag", "1");
			result.put("message", "传入信息误");
			return JsonMapper.getInstance().toJson(result);
		}
		
		result = mobileService.changePhoneNum(name, idCard,
				oldPhoneNum, newPhoneNum, deviceNum);

		return JsonMapper.getInstance().toJson(result);

	}

	
	/**
	 * 证书更新接口 lihuilong
	 * @param certSn
	 * @param certSubject
	 * @param p10
	 * @param deviceType
	 * @param deviceNum
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/updateMobileCert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateMobileCert(String certSn, String certSubject,
			String p10, String deviceType, String deviceNum,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(certSn) || StringUtils.isBlank(certSubject)
				|| StringUtils.isBlank(p10) || StringUtils.isBlank(deviceType)
				|| StringUtils.isBlank(deviceNum)) {
			result.put("flag", "1");
			result.put("message", "传入信息误");
			return JsonMapper.getInstance().toJson(result);
		}
		certSn = certSn.toUpperCase();
		//证书实体查询------------开始
		CertVo certVo = mobileService.getcertVoBycertsn(certSn);
		CertInfo certInfoOld = certVo.getCertInfo();
		
		//更新证书已注销，存在已经更新过的证书查询------------------开始
		CertInfo certInfoSerch = new CertInfo();
		certInfoSerch.setOldCertSn(certSn);
		CertInfo certInfo = mobileService.getNewCertinfoByOldCertSn(certInfoSerch);
		//跟新证书已注销，存在已经跟新过的证书查询------------------结束
		
		if(certInfo!=null) {
			//对未下载证书进行下载----------------开始
			if("Undown".equals(certInfo.getCertStatus())) {
				String applyType = mobileApplyInfoService.getApplyType(certSn);
				if(!ApplyConstants.OPTTYPE_OTHER_UPDATE.equals(applyType)&&applyType!=null) {
					result.put("flag", "1");
					result.put("message", "已存在未下载的" + ApplyConstants.getApplyTypeName(applyType) +"证书!");
					return JsonMapper.getInstance().toJson(result);
				}else {
					// 保存移动端申请业务信息------开始ggw
					String applyBusinessId = certapplyBusinessService.findByCertSn(certSn, ApplyConstants.OPTTYPE_OTHER_UPDATE);
					if (applyBusinessId == null) {
						applyBusinessId = certapplyBusinessService.saveCertapplyBusiness(deviceNum, deviceType,ApplyConstants.OPTTYPE_OTHER_UPDATE, certVo);
					}
					// 保存移动端申请业务信息------结束ggw
					// 保存移动端申请信息------开始
					String mobileApplyInfoId = mobileApplyInfoService.saveUpdateapplyEntiy(deviceNum,deviceType,applyBusinessId,ApplyConstants.OPTTYPE_OTHER_UPDATE,certSubject,certSn,certVo);
					// 保存移动端申请信息------结束
					String downResult = mobileService.mobileCertDown(certInfo.getCertSn(),p10, mobileApplyInfoId);
					// 证书下载-------------结束
					if(downResult!=null) {
						result.put("flag", "0");
						result.put("p7b", downResult);
						return JsonMapper.getInstance().toJson(result);
					}else {
						result.put("flag", "1");
						result.put("message", "下载失败!");
						return JsonMapper.getInstance().toJson(result);
					}
					
				}
			}
			//对未下载证书进行下载----------------结束
		}
		
		if (certVo != null) {
			
			// 保存移动端申请业务信息------开始ggw
			String applyBusinessId = certapplyBusinessService.findByCertSn(certSn, ApplyConstants.OPTTYPE_OTHER_UPDATE);
			if (applyBusinessId == null) {
				applyBusinessId = certapplyBusinessService.saveCertapplyBusiness(deviceNum, deviceType,ApplyConstants.OPTTYPE_OTHER_UPDATE, certVo);
			}
			// 保存移动端申请业务信息------结束ggw
			
			// 保存移动端申请信息------开始
			String mobileApplyInfoId = mobileApplyInfoService.saveUpdateapplyEntiy(deviceNum,deviceType,applyBusinessId,ApplyConstants.OPTTYPE_OTHER_UPDATE,certSubject,certSn,certVo);
			// 保存移动端申请信息------结束
			
			if (StringUtils.isNotBlank(mobileApplyInfoId)) {
				String isRecovery = "0";
				if(certInfo != null){
					isRecovery = "1";
					//传入的证书已注销，实际已经存在跟新下的新证书，但可能会有某种原因客户端未保留，故而传入原有已注销证书，对查询出的新证书进行跟新
					certVo.setCertInfo(certInfo);
				}
				// 证书申请并审核-------开始
				Map<String, Object> map = mobileService.updateMobileCert(certVo,mobileApplyInfoId,applyBusinessId,isRecovery,certInfoOld);
				// 证书申请并审核-------结束
				if("0".equals(map.get("flag"))){
					String newCertSn = (String) map.get("newCertSn");
					// 证书下载-------------开始
					String downResult = mobileService.mobileCertDown(newCertSn,p10, mobileApplyInfoId);
					// 证书下载-------------结束
					result.put("flag", "0");
					result.put("p7b", downResult);
					return JsonMapper.getInstance().toJson(result);
				}else{
					return JsonMapper.getInstance().toJson(map);
				}
			}
		}
		
		return null;
	}

	// 证书注销申请接口
	@RequestMapping(value = "/cancelMobileCert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String cancelMobileCert(String certSn, String cancelReason,
			String cancelDes, String deviceType, String deviceNum,
			String certSubject, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(certSn)) {
			result.put("flag", "1");
			result.put("message", "传入信息误");
			return JsonMapper.getInstance().toJson(result);
		}
		
		// 通过cerSn获取证书相关信息
		CertVo certVo = mobileService.getcertVoBycertsn(certSn);
		if(certVo != null){
			if("Revoke".equals(certVo.getCertInfo().getCertStatus())){
				result.put("flag", "0");
				result.put("message", "证书注销成功！");
				return JsonMapper.getInstance().toJson(result);
			}
		}
		
		//保存移动端申请业务信息------开始ggw
		String applyBusinessId = certapplyBusinessService.findByCertSn(certSn,ApplyConstants.OPTTYPE_OTHER_REVOKE);
		if(applyBusinessId==null) {
			applyBusinessId = certapplyBusinessService.saveCertapplyBusiness(deviceNum, deviceType, ApplyConstants.OPTTYPE_OTHER_REVOKE,certVo);
		}
		//保存移动端申请业务信息------结束ggw
		
		// 保存移动端申请信息------开始
		String mobileApplyInfoId = mobileApplyInfoService.saveUpdateapplyEntiy(deviceNum,deviceType,applyBusinessId,ApplyConstants.OPTTYPE_OTHER_REVOKE,certSubject,certSn,certVo);
		// 保存移动端申请信息------结束

		//证书注销------------开始
		Map<String, Object> MobileApplyResult = mobileService.cancelMobileCert(certVo,mobileApplyInfoId,applyBusinessId,cancelReason,cancelDes);
		//证书注销------------结束
		
		return JsonMapper.getInstance().toJson(MobileApplyResult);
	}

	// 证书变更申请下载接口
	@RequestMapping(value = "/changeMobileCert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String changeMobileCert(String certType, String certSn, String p10,
			String deviceType, String deviceNum, String certSubject,
			HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(certSn) || StringUtils.isBlank(certSubject) || StringUtils.isBlank(certType)
				|| StringUtils.isBlank(p10) || StringUtils.isBlank(deviceType)
				|| StringUtils.isBlank(deviceNum)) {
			result.put("flag", "1");
			result.put("message", "传入信息误");
			return JsonMapper.getInstance().toJson(result);
		}
		
		//判断白名单所在项目下是否有与新证书主题certSubject相关的使用中证书   ---------开始
		boolean isHasCert = mobileService.isHasCert(certType, certSn, certSubject);
		if(isHasCert){
			result.put("flag", "1");
			result.put("message", "无法变更,该用户已存在此项目证书,请联系管理员!");
			return JsonMapper.getInstance().toJson(result);
		}
		//判断白名单所在项目下是否有与新证书主题certSubject相关的使用中证书   ---------结束
		
		// 通过cerSn获取证书相关信息
		CertVo certVo = mobileService.getcertVoBycertsn(certSn);
		
		//保存移动端申请业务信息------开始ggw
		String applyBusinessId = certapplyBusinessService.findByCertSn(certSn,ApplyConstants.OPTTYPE_ALTER);
		if(applyBusinessId==null) {
			applyBusinessId = certapplyBusinessService.saveCertapplyBusiness(deviceNum, deviceType, ApplyConstants.OPTTYPE_ALTER,certVo);
		}
		//保存移动端申请业务信息------结束ggw
		
		// 保存移动端申请信息------开始
		String mobileApplyInfoId = mobileApplyInfoService.saveUpdateapplyEntiy(deviceNum,deviceType,applyBusinessId,ApplyConstants.OPTTYPE_ALTER,certSubject,certSn,certVo);
		// 保存移动端申请信息------结束
		
		
		//证书变更-------------开始
		Map<String, Object> map = mobileService.changeMobileCert(
				certType, certSn, deviceType, deviceNum, certSubject,applyBusinessId,mobileApplyInfoId);
		//证书变更-------------结束
		
		if("0".equals(map.get("flag"))){
			String newCertSn = (String) map.get("newCertSn");
			// 证书下载-------------开始
			String downResult = mobileService.mobileCertDown(newCertSn,p10, mobileApplyInfoId);
			// 证书下载-------------结束
			result.put("flag", "0");
			result.put("p7b", downResult);
			return JsonMapper.getInstance().toJson(result);
		}else{
			return JsonMapper.getInstance().toJson(map);
		}

	}
	
	// 忘记证书密码申请
	@RequestMapping(value = "/getMobileCertByForgetPass", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMobileCertByForgetPass(String certSn, String certSubject,
			String p10, String deviceType, String deviceNum,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {

		
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(certSn) || StringUtils.isBlank(certSubject)
				|| StringUtils.isBlank(p10) || StringUtils.isBlank(deviceType)
				|| StringUtils.isBlank(deviceNum)) {
			result.put("flag", "1");
			result.put("message", "传入信息误");
			return JsonMapper.getInstance().toJson(result);
		}
		certSn = certSn.toUpperCase();
		
		CertVo certVo = mobileService.getcertVoBycertsn(certSn);
		
		CertInfo certInfoSerch = new CertInfo();
		certInfoSerch.setOldCertSn(certSn);
		CertInfo certInfo = mobileService.getNewCertinfoByOldCertSn(certInfoSerch);
		
		if(certInfo!=null) {
			if("Undown".equals(certInfo.getCertStatus())) {
				String applyType = mobileApplyInfoService.getApplyType(certSn);
				if(!ApplyConstants.OPTTYPE_OTHER_FORGET_PIN.equals(applyType)&&applyType!=null) {
					result.put("flag", "1");
					result.put("message", "已存在未下载的" + ApplyConstants.getApplyTypeName(applyType) +"证书!");
					return JsonMapper.getInstance().toJson(result);
				}else {
					// 保存移动端申请业务信息------开始ggw
					String applyBusinessId = certapplyBusinessService.findByCertSn(certSn, ApplyConstants.OPTTYPE_OTHER_FORGET_PIN);
					if (applyBusinessId == null) {
						applyBusinessId = certapplyBusinessService.saveCertapplyBusiness(deviceNum, deviceType,ApplyConstants.OPTTYPE_OTHER_FORGET_PIN, certVo);
					}
					// 保存移动端申请业务信息------结束ggw
					// 保存移动端申请信息------开始
					String mobileApplyInfoId = mobileApplyInfoService.saveUpdateapplyEntiy(deviceNum,deviceType,applyBusinessId,ApplyConstants.OPTTYPE_OTHER_FORGET_PIN,certSubject,certSn,certVo);
					// 保存移动端申请信息------结束
					String downResult = mobileService.mobileCertDown(certInfo.getCertSn(),p10, mobileApplyInfoId);
					// 证书下载-------------结束
					if(downResult!=null) {
						result.put("flag", "0");
						result.put("p7b", downResult);
						return JsonMapper.getInstance().toJson(result);
					}else {
						result.put("flag", "1");
						result.put("message", "下载失败!");
						return JsonMapper.getInstance().toJson(result);
					}
					
				}
			}
		}
		
		if (certVo != null) {
			
			// 保存移动端申请业务信息------开始ggw
			String applyBusinessId = certapplyBusinessService.findByCertSn(certSn, ApplyConstants.OPTTYPE_OTHER_FORGET_PIN);
			if (applyBusinessId == null) {
				applyBusinessId = certapplyBusinessService.saveCertapplyBusiness(deviceNum, deviceType,ApplyConstants.OPTTYPE_OTHER_FORGET_PIN, certVo);
			}
			// 保存移动端申请业务信息------结束ggw
			
			// 保存移动端申请信息------开始
			String mobileApplyInfoId = mobileApplyInfoService.saveUpdateapplyEntiy(deviceNum,deviceType,applyBusinessId,ApplyConstants.OPTTYPE_OTHER_FORGET_PIN,certSubject,certSn,certVo);
			// 保存移动端申请信息------结束
			
			if (StringUtils.isNotBlank(mobileApplyInfoId)) {
				String isRecovery = "0";
				// 证书申请并审核-------开始
				if(certInfo != null){
					isRecovery = "1";
					certVo.setCertInfo(certInfo);
				}
				Map<String, Object> map = mobileService.getMobileCertByForgetPass(certVo,mobileApplyInfoId,applyBusinessId,isRecovery);
				// 证书申请并审核-------结束
				if("0".equals(map.get("flag"))){
					String newCertSn = (String) map.get("newCertSn");
					// 证书下载-------------开始
					String downResult = mobileService.mobileCertDown(newCertSn,p10, mobileApplyInfoId);
					// 证书下载-------------结束
					result.put("flag", "0");
					result.put("p7b", downResult);
					return JsonMapper.getInstance().toJson(result);
				}else{
					return JsonMapper.getInstance().toJson(map);
				}
			}
		}
		
		return null;
	
	}

	// 生成随机数
	@RequestMapping(value = "/getRdmnum", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getRdmnum(HttpServletRequest request,
			HttpServletResponse response) {
		return com.sxca.myb.common.utils.StringUtils.rdmNum();
	}

	/**
	 * description:判断证书是否注销 author:ggw
	 * 
	 * @param idCard
	 * @param phoneNum
	 * @return 返回已注销证书
	 */
	@RequestMapping(value = "/getCertsCancel", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCertsCancel(String certSns, HttpServletRequest request,
			HttpServletResponse response) {

		List<Map<String, String>> certCancels = null;

		if (certSns != null) {
			String[] certSnList = certSns.split(",");
			certCancels = certInfoService.getCertsIsCancel(certSnList);
		}

		return JsonMapper.getInstance().toJson(certCancels);

	}

	/**
	 * description:获取待补发证书 author:ggw
	 * 
	 * @param idCard
	 * @param phoneNum
	 * @return 返回证书
	 */
	@RequestMapping(value = "/getLoseCert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getLoseCert(String certSns, String phoneNum, String idCard,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(idCard)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}

		List<Map<String, String>> certinfoMap = mobileService.getCert(phoneNum,
				idCard);

		List<Map<String, Object>> whiteList = mobileService.getWaitApplyCert(
				phoneNum, idCard);
		if (whiteList != null && whiteList.size() > 0) {
			for (Map<String, Object> map : whiteList) {
				CertInfo certInfo = getCertByWhitList(map);
				if (certInfo != null) {
					for (Map<String, String> map1 : certinfoMap) {
						String certSn = map1.get("certSn");
						if (certInfo.getCertSn().equals(certSn)) {
							certinfoMap.remove(map1);
							break;
						}
					}
				}
			}
		}

		List<Map<String, String>> certinfoMap1 = new ArrayList<>();

		if (certSns != null && certinfoMap.size() > 0) {
			String[] cert = certSns.split(",");
			List<String> certSnList = certInfoService.getCertSns(cert);
			for (int i = 0; i < certinfoMap.size(); i++) {
				String certSn = certinfoMap.get(i).get("certSn");
				if (!certSnList.contains(certSn)) {
					// certinfoMap.remove(certinfoMap.get(i));
					certinfoMap1.add(certinfoMap.get(i));
				}

			}
			return JsonMapper.toJsonString(certinfoMap1);

		} else {

			if (certinfoMap != null && certinfoMap.size() > 0) {
				return JsonMapper.toJsonString(certinfoMap);
			}

		}

		result.put("flag", "1");
		result.put("message", "获取已有证书异常");
		return JsonMapper.getInstance().toJson(result);

	}

	// 意见反馈 wyf
	@RequestMapping(value = "/getProblem", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String getProblem(String deviceType, String deviceNum,
			String phoneNum, String idCard, String problem, String pic,
			String contact, String score, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(deviceType) || StringUtils.isBlank(deviceNum)
				|| StringUtils.isBlank(phoneNum) || StringUtils.isBlank(idCard)
				|| StringUtils.isBlank(problem)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		} else {
			Problem pro = new Problem();
			pro.setFeedbackProblem(problem);
			pro.setPic(pic);
			pro.setContact(contact);
			pro.setScore(score);
			String Proid = problemService.insert1(pro);
			result.put("flag", "0");
			result.put("message", "意见反馈回调成功");
			return JsonMapper.getInstance().toJson(result);
		}
	}

	// 获取客服电话
	@RequestMapping(value = "/getCusPhone", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCusPhone(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();

		List<SystemConfig> systemConfigList = systemConfigService
				.findList(new SystemConfig());

		if (systemConfigList != null && systemConfigList.size() > 0) {
			result.put("flag", "0");
			result.put("cusPhone", systemConfigList.get(0).getPhone());
			result.put("msg", "");
		} else {
			result.put("flag", "1");
			result.put("cusPhone", "");
			result.put("msg", "系统配置未开启");
		}

		return JsonMapper.getInstance().toJson(result);
	}

	// 获取白名单下是否存在已使用但手机未下载的证书
	public CertInfo getCertByWhitList(Map<String, Object> map) {
		CertInfo certInfo = null;
		String certSubject = (String) map.get("subject");
		String whitListId = (String) map.get("whiteId");
		String certType = (String) map.get("certType");
		String projectId = null;
		if (ApplyConstants.USERTYPE_USERINFO.equals(certType)) {
			WhiteList whiteList = whiteListService.get(whitListId);
			projectId = whiteList.getProjectInfoId();
		}
		if (ApplyConstants.USERTYPE_CORPORATION_INFO.equals(certType)) {
			CorporationRequestCode code = corporcodeService.get(whitListId);
			projectId = code.getProjectId();
		}
		CertInfo info = new CertInfo();
		info.setCertStatus("Use");
		info.setCertStatusMyb("Undown");
		info.setCertSubject(certSubject);
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setId(projectId);
		info.setProjectInfo(projectInfo);
		List<CertInfo> certInfos = certInfoService.findList(info);
		if (certInfos != null && certInfos.size() > 0) {
			certInfo = certInfos.get(0);
		}
		return certInfo;
	}
	
	//返回APK版本
	@RequestMapping(value = "/getAPKVersion", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAPKVersion() {
		Map<String, Object> result = new HashMap<>();
		List<APKVersion> apkVersions = apkVersionService.findList(new APKVersion());
		if(apkVersions!=null&&apkVersions.size()>0) {
			result.put("version", apkVersions.get(0).getVersion());
			result.put("url", apkVersions.get(0).getUrl());
		}
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * description:判断证书是否冻结 glq 2017-06-21
	 *
	 * @param certSn
	 * @return 返回是否冻结
	 */
	@RequestMapping(value = "/getCertIsHold", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCertIsHold(String certSn) {
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isBlank(certSn)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		CertInfo certInfo = certInfoService.getByCertSn(certSn);
		if(certInfo != null && StringUtils.isNotBlank(certInfo.getCertStatus())){
			if("Hold".equals(certInfo.getCertStatus())){
				result.put("flag", "0");
				result.put("message", "证书状态为冻结");
			} else {
				result.put("flag", "1");
				result.put("message", "证书状态非冻结");
			}
		}else{
			result.put("flag", "1");
			result.put("message", "证书查询异常");
		}
		return JsonMapper.getInstance().toJson(result);
	}
	/**
	 * 判断证书是否允许跟新
	 * @author lihuilong
	 * @param certSn
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/getAllowUpdate", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAllowUpdate(String certSn) throws ParseException{
		
		Map<String, String> result = new HashMap<>();
		if (StringUtils.isBlank(certSn)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		result = mobileService.getAllowUpdate(certSn);
		
		return JsonMapper.getInstance().toJson(result);
		
	}
	/**
	 * 返回应用是否存在
	 * @author lihuilong
	 * @param appName
	 * @return
	 */
	@RequestMapping(value = "/getIsUseByAppId", produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String getIsUseByAppId(String appName){
		
		Map<String, String> result = new HashMap<>();
		if (StringUtils.isBlank(appName)) {
			result.put("flag", "1");
			result.put("message", "传入信息有误");
			return JsonMapper.getInstance().toJson(result);
		}
		ApplicationSystem entity  = new ApplicationSystem();
		
		entity.setSystemName(appName);
		
		List<ApplicationSystem> sysList = applicationSystemService.findList(entity);
		
		if(sysList != null && sysList.size() > 0){
			result.put("flag", "0");
			result.put("message", "应用存在");
			return JsonMapper.getInstance().toJson(result);
		}else{
			result.put("flag", "1");
			result.put("message", "应用不存在");
			return JsonMapper.getInstance().toJson(result);		
		}
	}

}
