package com.sxca.myb.modules.cert.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.jit.pki.ra.cert.response.RABaseResponse;
import cn.com.jit.pki.ra.cert.response.addapply.CertHoldApplyAddResponse;
import cn.com.jit.pki.ra.cert.response.addapply.CertRevokeApplyAddResponse;
import cn.com.jit.pki.ra.cert.response.addapply.CertUnholdApplyAddResponse;
import cn.com.jit.pki.ra.cert.response.auditapply.CertHoldApplyAuditResponse;
import cn.com.jit.pki.ra.cert.response.auditapply.CertRevokeApplyAuditResponse;
import cn.com.jit.pki.ra.cert.response.auditapply.CertUnholdApplyAuditResponse;
import cn.com.jit.pki.ra.cert.response.deleteapply.DeleteApplyResponse;
import com.sxca.myb.common.BTK.btkinterface.BTK;
import com.sxca.myb.common.BTK.conversion.BussinessToBTKImpl;
import com.sxca.myb.common.BTK.entity.CertApplyBTK;
import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.common.utils.DateUtils;
import com.sxca.myb.modules.cert.vo.CertVo;
import com.sxca.myb.modules.certCtml.entity.CertCtml;
import com.sxca.myb.modules.certCtml.service.CertCtmlService;
import com.sxca.myb.modules.mobile.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.DateUtil;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.cert.service.CertInfoService;
import com.sxca.myb.modules.cert.service.CertapplyInfoService;
import com.sxca.myb.modules.config.deviceinfo.entity.DeviceInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.idinfo.service.CorporationInfoService;
import com.sxca.myb.modules.idinfo.service.CorporationUserInfoService;
import com.sxca.myb.modules.idinfo.service.UserInfoService;
import com.sxca.myb.modules.pro.entity.ProjectInfo;
import com.sxca.myb.modules.pro.service.ProjectInfoService;
import org.springframework.web.bind.annotation.ResponseBody;

/**  
* <p>Title: CertApplyInfoController</p>  
* <p>Description:  证书申请查询控制层</p>  
* <p>Company: 吉大正元</p>   
* @author wyf   
* @date 下午3:50:36  
*/ 
@Controller
@RequestMapping(value = "${adminPath}/certApplyInfo")
public class CertApplyInfoController extends BaseController {

	@Autowired
	private BTK btk;
	@Autowired
	private MobileService mobileService;
	@Autowired
	private CertCtmlService certCtmlService;
	@Autowired
	private CertInfoService certInfoService;
	@Autowired
	private CertapplyInfoService certapplyInfoService;
	@Autowired
	private ProjectInfoService projectInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CorporationInfoService corporationInfoService;
	@Autowired
	private CorporationUserInfoService corporationUserInfoService;

	@ModelAttribute
	public CertapplyInfo get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return certapplyInfoService.get(id);
		} else {
			return new CertapplyInfo();
		}
	}


	@RequestMapping(value = "list")
	public String certapplyInfoList(CertapplyInfo entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if(entity.getBeforeMin()!=null && !"".equals(entity.getBeforeMin()) && entity.getBeforeMax()!=null && !"".equals(entity.getBeforeMax()) ){
			String aa=DateUtil.date2String(entity.getBeforeMin(), "yyyyMMddHHmmss")+"000";
			BigDecimal bd=new BigDecimal(aa);
			entity.setBeforeMin1(bd);
//			------------------------
			String aa1=DateUtil.date2String(entity.getBeforeMax(), "yyyyMMddHHmmss")+"000";
			BigDecimal bd1=new BigDecimal(aa1);
			entity.setBeforeMax1(bd1);
		}else if(entity.getBeforeMin()!=null && !"".equals(entity.getBeforeMin())){
			String aa=DateUtil.date2String(entity.getBeforeMin(), "yyyyMMddHHmmss")+"000";
			BigDecimal bd=new BigDecimal(aa);
			entity.setBeforeMin1(bd);
		}else if(entity.getBeforeMax()!=null && !"".equals(entity.getBeforeMax())){
			String aa=DateUtil.date2String(entity.getBeforeMax(), "yyyyMMddHHmmss")+"000";
			BigDecimal bd=new BigDecimal(aa);
			entity.setBeforeMax1(bd);
		}
//		-------------------------------------------------
		/*CertapplyInfo certapplyInfo = new CertapplyInfo();*/
		Page<CertapplyInfo> page = new Page<CertapplyInfo>(request,
                response);
		entity.setPage(page);
		entity.setCertCtmlName(entity.getCtmlName());
		page.setList(certapplyInfoService.findbyList(entity));
		model.addAttribute("page", page);
		return "modules/certApplyInfo/list";

	}
	
	/**description: 证书查询方法
	 * @param certapplyInfo
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(CertapplyInfo certapplyInfo, Model model,String id) {
		if(id!=null && !"".equals(id)){
			//通过证书申请表 拿到项目ID，查出项目实体
			ProjectInfo  project=projectInfoService.get(certapplyInfo.getProjectId());
			model.addAttribute("project",project);
			//区分个人用户和企业用户
			UserInfo userInfo = new UserInfo();
			CorporationInfo corporationInfo = new CorporationInfo();
			//通过证书类型 区分 个人和企业 0是个人 
			if(certapplyInfo.getUserInfoId()!=null && !"".equals(certapplyInfo.getUserInfoId()) &&certapplyInfo.getCertType().equals(ApplyConstants.USERTYPE_USERINFO)){
				userInfo.setId(certapplyInfo.getUserInfoId()); //此时的Rauserid 是个人用户ID
				List<UserInfo> userinfoList =userInfoService.findList(userInfo);
				if(userinfoList!=null && userinfoList.size()>0){
					UserInfo user =userinfoList.get(0);
					model.addAttribute("userList", userinfoList);
					model.addAttribute("user", user);
				}
			}
			//1 是企业
			if(certapplyInfo.getUserInfoId()!=null && !"".equals(certapplyInfo.getUserInfoId()) &&certapplyInfo.getCertType().equals(ApplyConstants.USERTYPE_CORPORATION_INFO)){
				corporationInfo.setId(certapplyInfo.getUserInfoId());//此时的Rauserid 是企业ID
				List<CorporationInfo> corinfoList =corporationInfoService.findList(corporationInfo);
				if(corinfoList!=null &&corinfoList.size()>0){
					CorporationInfo corpo = corinfoList.get(0);
					CorporationUserInfo corporationUserInfo = new CorporationUserInfo();
					corporationUserInfo.setId(certapplyInfo.getCorpUserId());
					List<CorporationUserInfo> coruserList = corporationUserInfoService.findList(corporationUserInfo);
					if(coruserList!=null && coruserList.size()>0){
						CorporationUserInfo corUser=coruserList.get(0);
						model.addAttribute("corUser", corUser);
						model.addAttribute("coruserList", coruserList);
						
					}
						
						model.addAttribute("corpoList", corinfoList);
						model.addAttribute("corpo", corpo);
				}
			}
		}
		
		return "modules/certApplyInfo/form";
	}
	/**
	 * 注销、冻结、解冻申请
	 * glq 	2017-05-08
	 * @param optType
	 * @param certSn
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/revokeHoldApply")
	public String revokeHoldApply(String optType, String certSn, Model model){
		if(StringUtils.isBlank(optType) || StringUtils.isBlank(certSn)){
			model.addAttribute("errCode", "000");
			model.addAttribute("errMsg", "数据交互异常");
			return "modules/certApplyInfo/codeerror";
		}
		CertVo certVo = mobileService.getcertVoBycertsn(certSn);
		certVo.getCertapplyInfo().setId(null);
		certVo.getCertapplyInfo().setReqSn(null);
		certVo.getCertapplyInfo().setRevokeDesc(null);
		model.addAttribute("certVo",certVo);
		model.addAttribute("optType",optType);
		return "modules/certApplyInfo/revokeHoldApply";
	}

	/**
	 * 保存注销、冻结、解冻申请
	 * 如果模板是自动审核,则进行审核,否则跳转到下一步页面
	 * glq 	2017-05-08
	 * @param optType 		操作类型 注销:2,冻结:3,解冻:4
	 * @param newCertVo	页面提交的相关申请信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveRevokeHoldApply")
	@ResponseBody
	public String saveRevokeHoldApply(String optType, CertVo newCertVo, Model model){
		if(newCertVo == null || StringUtils.isBlank(newCertVo.getCertapplyInfo().getCertSn())){
			return "false:数据交互异常";
		}
		//根据证书序列号获取CertVo信息
		CertVo oldCertVo = mobileService.getcertVoBycertsn(newCertVo.getCertapplyInfo().getCertSn());
		CertapplyInfo certapplyInfo = oldCertVo.getCertapplyInfo();
		//封装此次申请
		certapplyInfo.setOptType(optType);
		certapplyInfo.setRevokeReason(newCertVo.getCertapplyInfo().getRevokeReason());
		certapplyInfo.setRevokeDesc(newCertVo.getCertapplyInfo().getRevokeDesc());
		certapplyInfo.setRefuseReason(null);
		certapplyInfo.setReqStatus("1");
		String date= DateUtils.formatPayTime(new Date());
		certapplyInfo.setOptTime(new BigDecimal(Double.parseDouble(date+"000")));
		//获取项目信息下的模板信息
		ProjectInfo projectInfo = projectInfoService.get(certapplyInfo.getProjectId());
		CertCtml certCtml = null;
		if (projectInfo != null) {
			certCtml = certCtmlService.get(projectInfo.getCertTemplate());
		}
		//业务实体转换为BTK实体,进行BTK请求
		BussinessToBTKImpl bussToBTK = new BussinessToBTKImpl();
		CertApplyBTK certApplyBTK = (CertApplyBTK)bussToBTK.mapToEntityBTK(certapplyInfo,new CertApplyBTK());
		certApplyBTK.setNotNow(false);
		certApplyBTK.setDelayDays(0);
		RABaseResponse response = null;
		//如果是注销,BTK提交注销申请
		if(ApplyConstants.RA_OPTTYPE_REVOKE.equals(optType)) {
			response = (CertRevokeApplyAddResponse)btk.certRevokeApplyAdd(certApplyBTK);
		}
		//如果是冻结,BTK提交冻结申请
		if(ApplyConstants.RA_OPTTYPE_HOLD.equals(optType)) {
			certApplyBTK.setHoldDesc(certapplyInfo.getRevokeDesc());
			response = (CertHoldApplyAddResponse)btk.certHoldApplyAdd(certApplyBTK);
		}
		//如果是解冻,BTK提交解冻申请
		if(ApplyConstants.RA_OPTTYPE_UNHOLD.equals(optType)) {
			response = (CertUnholdApplyAddResponse)btk.certUnholdApplyAdd(certApplyBTK);
		}
		//判断BTK申请响应
		if (response != null && "0".equals(response.getErr())) {
			//如果模板是自动审核,则进行申请信息和证书信息的更新保存
			if("Y".equals(certCtml.getCertCtmlAudit())){
				certapplyInfo.setReqStatus("2");
				certapplyInfo.setReqSn(response.getReqSN());
				certapplyInfoService.insert(certapplyInfo);
				// 关联修改相关证书实体状态
				certInfoService.createOrUpdateCert(oldCertVo.getCertInfo().getCertSn(),oldCertVo.getCertInfo().getIsRecovery());
				return "true:";
			}else{//如果模板是手动审核则跳转到下一步页面
				certapplyInfo.setReqSn(response.getReqSN());
				String certApplyId = certapplyInfoService.insert1(certapplyInfo);
				return "true:"+certApplyId;
			}
		} else {
			String message = "网络异常!";
			if(response != null){
				message = response.getMsg();
			}
			return "false:" + message;
		}
	}

	/**
	 * 保存注销、冻结、解冻申请
	 * 如果模板是自动审核,则进行审核,否则跳转到下一步页面
	 * glq 	2017-05-08
	 * @param reqId 		请求ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delRevokeHoldApply")
	public String delRevokeHoldApply(String reqId, Model model){
		if(StringUtils.isBlank(reqId)){
			model.addAttribute("errCode", "000");
			model.addAttribute("errMsg", "数据交互异常");
			return "modules/certApplyInfo/codeerror";
		}
		CertapplyInfo certapplyInfo = certapplyInfoService.get(reqId);
		DeleteApplyResponse deleteApplyResponse = btk.deleteApply(certapplyInfo.getReqSn());
		if(deleteApplyResponse != null && "0".equals(deleteApplyResponse.getErr())){
			//删除本地申请信息
			certapplyInfoService.deleteData(certapplyInfo);
			return "redirect:" + adminPath + "/certApplyInfo/list/?repage";
		}
		model.addAttribute("errCode", "000");
		model.addAttribute("errMsg", "数据交互异常");
		return "modules/certApplyInfo/codeerror";
	}

	/**
	 * 注销、冻结、解冻审核申请
	 * glq 	2017-05-08
	 * @param reqId	请求Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/RevokeHoldAudit")
	public String RevokeHoldAudit(String reqId, Model model){
		//跳转申请审核页面
		if (StringUtils.isNotBlank(reqId)) {
			CertapplyInfo certapplyInfo = certapplyInfoService.get(reqId);
			CertInfo certInfo = certInfoService.getByCertSn(certapplyInfo.getCertSn());
			CertVo certVo = new CertVo();
			certVo.setCertapplyInfo(certapplyInfo);
			certVo.setCertInfo(certInfo);
			model.addAttribute("certVo", certVo);
			return "modules/certApplyInfo/revokeHoldAudit";
		}
		model.addAttribute("errCode", "000");
		model.addAttribute("errMsg", "数据交互异常");
		return "modules/certApplyInfo/codeerror";
	}

	/**
	 * 保存注销、冻结、解冻审核申请
	 * glq 	2017-05-08
	 * @param auditStatus		审核状态 通过:0,拒绝:1
	 * @param newCertVo		页面提交的相关申请信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveRevokeHoldAudit")
	public String saveRevokeHoldAudit(String auditStatus, CertVo newCertVo, Model model){
		if(newCertVo == null || StringUtils.isBlank(newCertVo.getCertapplyInfo().getCertSn())){
			model.addAttribute("errCode", "000");
			model.addAttribute("errMsg", "数据交互异常");
			return "modules/certApplyInfo/codeerror";
		}
		//审核是否通过
		boolean pass = true;
		if("1".equals(auditStatus)){
			pass = false;
		}
		//找到审核的证书申请
		CertVo certVo = mobileService.getcertVoBycertsn(newCertVo.getCertapplyInfo().getCertSn());
		CertapplyInfo certapplyInfo = certVo.getCertapplyInfo();
		certapplyInfo.setRefuseReason(newCertVo.getCertapplyInfo().getRefuseReason());
		String date= DateUtils.formatPayTime(new Date());
		certapplyInfo.setOptTime(new BigDecimal(Double.parseDouble(date+"000")));
		//BTK业务请求
		String optType = certapplyInfo.getOptType();
		RABaseResponse response = null;
		//如果是注销,BTK提交注销审核申请
		if(ApplyConstants.RA_OPTTYPE_REVOKE.equals(optType)) {
			response = (CertRevokeApplyAuditResponse)btk.certRevokeApplyAudit(certapplyInfo.getReqSn(),pass,certapplyInfo.getRefuseReason());
		}
		//如果是冻结,BTK提交冻结审核申请
		if(ApplyConstants.RA_OPTTYPE_HOLD.equals(optType)) {
			response = (CertHoldApplyAuditResponse)btk.certHoldApplyAudit(certapplyInfo.getReqSn(),pass,certapplyInfo.getRefuseReason());
		}
		//如果是解冻,BTK提交解冻审核申请
		if(ApplyConstants.RA_OPTTYPE_UNHOLD.equals(optType)) {
			response = (CertUnholdApplyAuditResponse)btk.certUnholdApplyAudit(certapplyInfo.getReqSn(),pass,certapplyInfo.getRefuseReason());
		}

		//判断BTK审核申请响应
		if (response != null && "0".equals(response.getErr())) {
			if(pass){
				certapplyInfo.setReqStatus("2");//申请状态设置为已审核
			}else{
				certapplyInfo.setReqStatus("3");//申请状态设置为审核未通过
			}
			//更新申请状态
			certapplyInfoService.update(certapplyInfo);
			// 关联修改相关证书实体状态
			certInfoService.createOrUpdateCert(certVo.getCertInfo().getCertSn(),certVo.getCertInfo().getIsRecovery());
			return "redirect:" + adminPath + "/certInfo/list/?repage";
		} else {
			model.addAttribute("errCode", response.getErr());
			model.addAttribute("errMsg", response.getMsg());
			return "modules/certApplyInfo/codeerror";
		}
	}

	/**
	 * 未下载 , 使用中 , 冻结 证书进行状态同步
	 * //状态同步成功返回true 否则返回false
	 * glq 	2017-05-18
	 * @param certSn	证书序列号
	 * @return
	 */
	@RequestMapping(value = "/syncCertInfo")
	@ResponseBody
	public boolean syncCertInfo(String certSn,String isRecovery){
		if (StringUtils.isNotBlank(certSn)) {
			//同步并保存证书信息
			CertInfo certInfo = certInfoService.createOrUpdateCert(certSn,isRecovery);
			if(certInfo != null){
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * 未下载 证书进行状态同步(当天)
	 * glq 	2017-05-18
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/syncCertInfoToday")
	@ResponseBody
	public String syncCertInfoToday(Model model){
		BigDecimal optTimeMin = new BigDecimal(DateUtil.date2String(new Date(),"yyyyMMdd")+"000000000");
		BigDecimal optTimeMax = new BigDecimal(DateUtil.date2String(new Date(),"yyyyMMddHHmmss")+"000");
		CertInfo certInfo = new CertInfo();
		if(optTimeMin != null && optTimeMin.compareTo(new BigDecimal(0))==1){
			certInfo.setOptTimeMin(optTimeMin);
		}
		if(optTimeMax!=null && optTimeMax.compareTo(new BigDecimal(0))==1){
			certInfo.setOptTimeMax(optTimeMax);
		}
		certInfo.setCertStatus("Undown");
		List<CertInfo> certInfos = certInfoService.findbyList(certInfo);//本地条件查询得到的证书信息
		if(certInfos != null && certInfos.size() > 0){
			for(CertInfo cert: certInfos){
				cert = certInfoService.createOrUpdateCert(cert.getCertSn(),cert.getIsRecovery());
				if(cert == null){
					return "false";
				}
			}
			return "true";
		}else{
			return "null";
		}
	}
}
