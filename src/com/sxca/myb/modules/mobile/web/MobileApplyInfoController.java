package com.sxca.myb.modules.mobile.web;

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
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.cert.service.CertInfoService;
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
* <p>Title: MobileApplyInfoController</p>  
* <p>Description: 手机申请查询 页面控制层</p>  
* <p>Company: 吉大正元</p>   
* @author wyf  
* @date 2017年5月12日下午4:57:23  
* @version V1.0 
*/ 
@Controller
@RequestMapping(value = "${adminPath}/mobileApplyInfo")
public class MobileApplyInfoController extends BaseController {

	@Autowired
	private CertInfoService certInfoService;
	@Autowired
	private MobileApplyInfoService mobileApplyInfoService;
	@Autowired
	private ProjectInfoService projectInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CorporationInfoService corporationInfoService;
	@Autowired
	private CorporationUserInfoService corporationUserInfoService;

	@ModelAttribute
	public MobileApplyInfo get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return mobileApplyInfoService.get(id);
		} else {
			return new MobileApplyInfo();
		}
	}


	@RequestMapping(value = "list")
	public String certInfoList(MobileApplyInfo entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
//	  ------------------------------------------------------	
		/*MobileApplyInfo mobileApplyInfo = new MobileApplyInfo();*/
		Page<MobileApplyInfo> page = new Page<MobileApplyInfo>(request,
                response);
		entity.setPage(page);
		page.setList(mobileApplyInfoService.findbyList(entity));
//		
		model.addAttribute("page", page);
		return "modules/mobileApplyInfo/list";

	}
	
	/**description: 证书查询方法
	 * @param certInfo
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(MobileApplyInfo mobileApplyInfo, Model model,String id) {
		if(id!=null && !"".equals(id)){
			// 查出证书实体
			CertInfo certInfo = new CertInfo();
			certInfo.setCertSn(mobileApplyInfo.getCertSn());
			if(mobileApplyInfo.getCertSn()!=null &&!"".equals(mobileApplyInfo.getCertSn())){
				List<CertInfo> certlist=certInfoService.findbyList(certInfo);
				if(certlist!=null && certlist.size()>0){
					CertInfo cert=certlist.get(0);
					model.addAttribute("certlist", certlist);
					model.addAttribute("cert", cert);
					}
			}
			//查出项目实体
			ProjectInfo  project=projectInfoService.get(mobileApplyInfo.getProjectId());
			model.addAttribute("project",project);
			//区分个人用户和企业用户
			UserInfo userInfo = new UserInfo();
			CorporationInfo corporationInfo = new CorporationInfo();
			//通过证书类型 区分 个人和企业 0是个人 
			if(mobileApplyInfo.getUserId()!=null && !"".equals(mobileApplyInfo.getUserId()) &&mobileApplyInfo.getUserType().equals(ApplyConstants.USERTYPE_USERINFO)){
				userInfo.setId(mobileApplyInfo.getUserId()); //此时的Rauserid 是个人用户ID
				List<UserInfo> userinfoList =userInfoService.findList(userInfo);
				if(userinfoList!=null && userinfoList.size()>0){
					UserInfo user =userinfoList.get(0);
					model.addAttribute("userList", userinfoList);
					model.addAttribute("user", user);
				}
			}
			//1 是企业
			if(mobileApplyInfo.getUserId()!=null && !"".equals(mobileApplyInfo.getUserId()) &&mobileApplyInfo.getUserType().equals(ApplyConstants.USERTYPE_CORPORATION_INFO)){
				corporationInfo.setId(mobileApplyInfo.getUserId());//此时的Rauserid 是企业ID
				List<CorporationInfo> corinfoList =corporationInfoService.findList(corporationInfo);
				if(corinfoList!=null &&corinfoList.size()>0){  //查企业表实体
					CorporationInfo corpo = corinfoList.get(0);
					CertapplyInfo  certapplyInfo = new CertapplyInfo();
					certapplyInfo.setUserInfoId(corpo.getId());
					//查询证书申请表 拿到企业用户信息
					CorporationUserInfo corporationUserInfo = new CorporationUserInfo();
					corporationUserInfo.setId(mobileApplyInfo.getCorpUserId());
					List<CorporationUserInfo> coruserList = corporationUserInfoService.findList(corporationUserInfo);
					if(coruserList!=null && coruserList.size()>0 ){
						CorporationUserInfo corUser=corporationUserInfoService.get(mobileApplyInfo.getCorpUserId());
						model.addAttribute("coruserList",coruserList);
						model.addAttribute("corUser", corUser);
					}
					//----------------------------------------------------------------------
					model.addAttribute("corpoList", corinfoList);
					model.addAttribute("corpo", corpo);
				}
			}
		}
		
		return "modules/mobileApplyInfo/form";
	}

}
