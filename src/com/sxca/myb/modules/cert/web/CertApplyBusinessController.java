package com.sxca.myb.modules.cert.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.entity.CertapplyBusinessInfo;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.cert.service.CertInfoService;
import com.sxca.myb.modules.cert.service.CertapplyBusinessService;
import com.sxca.myb.modules.cert.service.CertapplyInfoService;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.idinfo.service.CorporationInfoService;
import com.sxca.myb.modules.idinfo.service.CorporationUserInfoService;
import com.sxca.myb.modules.idinfo.service.UserInfoService;
import com.sxca.myb.modules.mobile.entity.MobileApplyInfo;
import com.sxca.myb.modules.mobile.service.MobileApplyInfoService;
import com.sxca.myb.modules.pro.entity.ProjectInfo;
import com.sxca.myb.modules.pro.service.ProjectInfoService;
/**  
* <p>Title: CertApplyBusinessController</p>  
* <p>Description: 手机业务查询页面控制层 </p>  
* <p>Company: 吉大正元</p>   
* @author wyf 
* @date 2017年6月1日下午3:23:30  
* @version V1.0 
*/ 
@Controller
@RequestMapping(value = "${adminPath}/certApplyBusiness")
public class CertApplyBusinessController extends BaseController{
	
	
	@Autowired
	private CertapplyBusinessService certapplyBusinessService;
	
	@Autowired
	private CertapplyInfoService certapplyInfoService;
	
	@Autowired
	private CertInfoService certInfoService;
	
	@Autowired
	private ProjectInfoService projectInfoService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private CorporationInfoService corporationInfoService;
	
	@Autowired
	private CorporationUserInfoService corporationUserInfoService;
	
	@Autowired
	private MobileApplyInfoService mobileApplyInfoService;
	
	@ModelAttribute
	public CertapplyBusinessInfo get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return certapplyBusinessService.get(id);
		} else {
			return new CertapplyBusinessInfo();
		}
	}
	
	
	
	@RequestMapping(value = "list")
	public String CertApplyBusinessInfoList(CertapplyBusinessInfo entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CertapplyBusinessInfo> page = new Page<CertapplyBusinessInfo>(request,
                response);
		entity.setPage(page);
		page.setList(certapplyBusinessService.findbyList(entity));
		model.addAttribute("page", page);
		return "modules/certApplyBusiness/list";

	}
	
	
	
	/** 申请详情跳转方法
	 * @param entity
	 * @param clickType
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "apply")
	public String CertApplyapplyList(CertapplyBusinessInfo entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		CertapplyInfo certapplyInfo = new CertapplyInfo();
		certapplyInfo.setApplyBusinessId(entity.getId());
		Page<CertapplyInfo> page = new Page<CertapplyInfo>(request,
                response);
		certapplyInfo.setPage(page);
		page.setList(certapplyInfoService.findbyList(certapplyInfo));
		model.addAttribute("page", page);
		return "modules/certApplyBusiness/applyList";

	}
	
	@RequestMapping(value = "mobileApply")
	public String mobileApply(CertapplyBusinessInfo entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		MobileApplyInfo mobileApplyInfo = new MobileApplyInfo();
		mobileApplyInfo.setApplyBusinessId(entity.getId());
		Page<MobileApplyInfo> page = new Page<MobileApplyInfo>(request,
                response);
		mobileApplyInfo.setPage(page);
		page.setList(mobileApplyInfoService.findbyList(mobileApplyInfo));
		model.addAttribute("page", page);
		return "modules/certApplyBusiness/mobileApplyList";

	}
	
	@RequestMapping(value = "applyform")
	public String CertApplyapplyformList(String clickType,HttpServletRequest request,
			HttpServletResponse response,String id,Model model) {
		if(id!=null && !"".equals(id)){
			//拿到手机申请信息
			MobileApplyInfo mobileApplyInfo=mobileApplyInfoService.get(id);
			if(mobileApplyInfo !=null && !"".equals(mobileApplyInfo)){
				/*CertInfo certInfo = new CertInfo();
				certInfo.setCertSn(mobileApplyInfo.getCertSn());
				List<CertInfo> certlist=certInfoService.findbyList(certInfo);
				if(certlist!=null && certlist.size()>0){
					CertInfo cert=certlist.get(0);
					model.addAttribute("certlist", certlist);
					model.addAttribute("cert", cert);
					}*/
				CertapplyInfo certapplyInfo1 = new CertapplyInfo();
				certapplyInfo1.setCertSn(mobileApplyInfo.getCertSn());
				certapplyInfo1.setCertSubject(mobileApplyInfo.getCertSubject());
				certapplyInfo1.setCreateDate(mobileApplyInfo.getCreateDate());
				certapplyInfo1.setApplyBusinessId(mobileApplyInfo.getApplyBusinessId());
				List<CertapplyInfo> certapplyInfoList= certapplyInfoService.findbyList(certapplyInfo1);
				if(certapplyInfoList!=null && certapplyInfoList.size()>0){
					CertapplyInfo certapplyInfo=certapplyInfoList.get(0);
					model.addAttribute("certapplyInfo", certapplyInfo);
					ProjectInfo  project=projectInfoService.get(certapplyInfoList.get(0).getProjectId());
					model.addAttribute("project",project);
					
					if(certapplyInfo!=null && !"".equals(certapplyInfo)){
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
					
				}
				
			}
		}
			
		
		return "modules/certApplyBusiness/applyform";

	}
	
	
	/**证书详情 跳转方法
	 * @param entity
	 * @param clickType
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cert")
	public String CertList(CertapplyBusinessInfo entity,String clickType,String id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<String> certId = new ArrayList<String>();
		CertapplyInfo certapplyInfo = new CertapplyInfo();
		certapplyInfo.setApplyBusinessId(entity.getId());
		List<CertapplyInfo> certapplyInfos = certapplyInfoService.findbyList(certapplyInfo);
		if(certapplyInfos!=null&&certapplyInfos.size()>0) {
			for(CertapplyInfo certapplyInfo2:certapplyInfos) {
				certId.add(certapplyInfo2.getCertId());
			}
		}
		CertInfo certInfo = new CertInfo();
		certInfo.setSerchCertId(certId);
		Page<CertInfo> page = new Page<CertInfo>(request,
				response);
		certInfo.setPage(page);
		page.setList(certInfoService.findbyList(certInfo));
		model.addAttribute("page", page);
		return "modules/certApplyBusiness/certList";
		
	}
	
	
	/**description: 证书查询方法--------------------------------------------------------------------------
	 * @param certInfo
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "certform")
	public String form(Model model,String id,HttpServletRequest request,
			HttpServletResponse response) {
		if(id!=null && !"".equals(id)){
			//拿到证书信息
			CertInfo certInfo =null;
			certInfo=certInfoService.get(id);
			model.addAttribute("certInfo", certInfo);
			//通过证书申请表 拿到项目ID，查出项目实体
			List<CertapplyInfo> applyList = certapplyInfoService.findByCertSn(certInfo.getCertSn());
			if(applyList!=null && applyList.size()>0){
				ProjectInfo  project=projectInfoService.get(applyList.get(0).getProjectId());
				model.addAttribute("project",project);
			}
			//区分个人用户和企业用户
			UserInfo userInfo = new UserInfo();
			CorporationInfo corporationInfo = new CorporationInfo();
			//通过证书类型 区分 个人和企业 0是个人 
			if(certInfo.getUserinfoId()!=null && !"".equals(certInfo.getUserinfoId()) &&certInfo.getCertType().equals(ApplyConstants.USERTYPE_USERINFO)){
				userInfo.setRaUserId(certInfo.getUserinfoId()); //此时的Rauserid 是个人用户ID
				List<UserInfo> userinfoList =userInfoService.findList(userInfo);
				if(userinfoList!=null && userinfoList.size()>0){
					UserInfo user =userinfoList.get(0);
					model.addAttribute("userList", userinfoList);
					model.addAttribute("user", user);
				}
			}
			//1 是企业
			if(certInfo.getUserinfoId()!=null && !"".equals(certInfo.getUserinfoId()) &&certInfo.getCertType().equals(ApplyConstants.USERTYPE_CORPORATION_INFO)){
				corporationInfo.setRaUserId(certInfo.getUserinfoId());//此时的Rauserid 是企业ID
				List<CorporationInfo> corinfoList =corporationInfoService.findList(corporationInfo);
				if(corinfoList!=null &&corinfoList.size()>0){
					CorporationInfo corpo = corinfoList.get(0);
					CertapplyInfo  certapplyInfo = new CertapplyInfo();
					certapplyInfo.setUserInfoId(corpo.getId());
					//查询证书申请表 拿到企业用户信息
					List<CertapplyInfo> corpoUserList=certapplyInfoService.findList(certapplyInfo);
					if(corpoUserList!=null && corpoUserList.size()>0 ){
						String corpoUser=corpoUserList.get(0).getCorpUserId();
						CorporationUserInfo corUser=corporationUserInfoService.get(corpoUser);
						model.addAttribute("corpoUserList",corpoUserList);
						model.addAttribute("corUser", corUser);
					}
					//----------------------------------------------------------------------
					model.addAttribute("corpoList", corinfoList);
					model.addAttribute("corpo", corpo);
				}
			}
		}
		
		return "modules/certApplyBusiness/certform";
	}

	

}
