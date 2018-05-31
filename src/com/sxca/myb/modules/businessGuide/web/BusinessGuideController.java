package com.sxca.myb.modules.businessGuide.web;


import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.config.corporcode.entity.CorporationRequestCode;
import com.sxca.myb.modules.config.corporcode.service.CorporcodeService;
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;
import com.sxca.myb.modules.config.whitelist.service.WhiteListService;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserRelation;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.idinfo.service.CorporationInfoService;
import com.sxca.myb.modules.idinfo.service.CorporationUserInfoService;
import com.sxca.myb.modules.idinfo.service.CorporationUserRelationnService;
import com.sxca.myb.modules.idinfo.service.UserInfoService;
import com.sxca.myb.modules.mobile.util.GetCertSubjectUtil;
import com.sxca.myb.modules.pro.entity.ProjectInfo;
import com.sxca.myb.modules.pro.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sxca.myb.common.web.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/businessGuide")
public class BusinessGuideController extends BaseController{

	@Autowired
	private ProjectInfoService projectInfoService;
	@Autowired
	private CorporationInfoService corporationInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CorporationUserInfoService corporationUserInfoService;
	@Autowired
	private CorporationUserRelationnService corporationUserRelationnService;
	@Autowired
	private WhiteListService whiteListService;
	@Autowired
	private CorporcodeService corporcodeService;

	/**
	 * 业务向导2
	 * @param model
	 * @return
     */
	@RequestMapping(value = "list")
	public String list(Model model) {
		List<CorporationInfo> corporationInfos = corporationInfoService.findList(new CorporationInfo());
		model.addAttribute("corporationInfos",corporationInfos);
		List<UserInfo> userInfos = userInfoService.findList(new UserInfo());
		model.addAttribute("userInfos",userInfos);
		return "modules/businessGuide/list";
	}

	/**
	 * 业务向导1
	 * @return
     */
	@RequestMapping(value = "form")
	public String form() {
		return "modules/businessGuide/form";
	}

	/**
	 * 业务向导1
	 * @return
	 */
	@RequestMapping(value = "getWhitelistByProjectId")
	@ResponseBody
	public String getWhitelistByProjectId(String projectId) {
		String result = "null";
		List<WhiteList> whiteLists = whiteListService.findbyprojectInfoId(projectId);
		if(whiteLists != null && whiteLists.size() > 0){
			String userIdStr = "";
			for (WhiteList wl : whiteLists) {
				userIdStr += wl.getUserInfoId() + ",";
			}
			result = userIdStr.substring(0,userIdStr.length()-1);
		}
		return result;
	}

	/**
	 * 业务向导 项目信息保存 成功返回项目ID 项目名称
	 * glq 2017-06-29
	 * @param entity
	 * @param request
	 * @param newProId 项目ID 若不为空则为更新  若为空则为新增
     * @return
     */
	@RequestMapping(value = "projectinfoSave")
	@ResponseBody
	public String projectinfoSave(ProjectInfo entity, HttpServletRequest request,String newProId) {
		String result = "false";
		String projectId = "";
		if(StringUtils.isNotBlank(newProId)){
			entity.setId(newProId);
			projectInfoService.update(entity);
			result = "true:"+newProId+":"+entity.getProjectName();
		}else{
			projectId = projectInfoService.insert1(entity);
			if(StringUtils.isNotBlank(projectId)){
				result = "true:"+projectId+":"+entity.getProjectName();
			}
		}
		return result;
	}

	@RequestMapping(value = "projectinfoDelete")
	@ResponseBody
	public String proinfoDelete(String id) {
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setId(id);
		projectInfoService.delete(projectInfo);
		return "true";
	}
	/**
	 * 业务向导 个人用户保存 成功返回个人用户ID
	 * glq 2017-06-29
	 * @param entity
	 * @param request
     * @return
     */
	@RequestMapping(value = "userinfoSave")
	@ResponseBody
	public String userinfoSave(UserInfo entity, String userId, HttpServletRequest request) {
		String result = "false";
		if(StringUtils.isNotBlank(userId)){
			entity.setId(userId);
		}
		entity.setUserType("0");
		if(userInfoService.saveAndRasave(entity)){
			List<UserInfo> userInfos = userInfoService.findList(entity);
			if(userInfos != null && userInfos.size() > 0){
				UserInfo userInfo = userInfos.get(0);
				if(StringUtils.isNotBlank(entity.getId())){
					CorporationUserInfo corporationUserInfo = corporationUserInfoService.getByIdCard(entity.getUseridcardnum());
					if(corporationUserInfo != null){
						corporationUserInfo.setUserName(entity.getUsername());
						corporationUserInfoService.update(corporationUserInfo);
					}
				}
				result = "true:" + userInfo.getId();
			}
		}
		return result;
	}

	/**
	 * 业务向导 保存企业信息 成功返回企业ID
	 * glq 2017-06-29
	 * @param entity
     * @return
     */
	@RequestMapping(value = "corporationinfoSave")
	@ResponseBody
	public String corporationInfoSave(CorporationInfo entity, String corporationId) {
		String result = "false";
		entity.setId(corporationId);
		if(corporationInfoService.saveAndRasave(entity)){
			result = "true:" + entity.getId();
		}
		return result;
	}

	/**
	 * 业务向导 保存个人白名单
	 * glq 2017-06-29
	 * @param userInfoIds
	 * @param entity
     * @return
     */
	@RequestMapping(value = "whitelistSave")
	@ResponseBody
	public String whitelistSave(String userInfoIds, WhiteList entity) {
		String result = "false";
		if (userInfoIds != null && !"".equals(userInfoIds)) {
			String[] userInfoIdArray = userInfoIds.split(",");
			WhiteList whiteList = null;
			for (String userinfoId : userInfoIdArray) {
				whiteList = new WhiteList();
				if (StringUtils.isNotBlank(userinfoId) && userinfoId != null) {
					UserInfo userInfo = userInfoService.get(userinfoId);
					ProjectInfo projectInfo = projectInfoService.get(entity.getProjectInfoId());
					String certSubject = GetCertSubjectUtil.getSubject(projectInfo, userInfo, ApplyConstants.USERTYPE_USERINFO,"");
					whiteList.setUserInfoId(userinfoId);
					whiteList.setProjectInfoId(entity.getProjectInfoId());
					whiteList.setOptType(entity.getOptType());
					whiteList.setCertSubject(certSubject);
					whiteList.setIsUsed("0");
					whiteList.setIsuseFace("0");
					whiteListService.save(whiteList);
				}
			}
			result = "true";
		}
		return result;
	}

	/**
	 * 业务向导 保存企业白名单
	 * glq 2017-06-29
	 * @param entity
	 * @param model
	 * @param request
     * @return
     */
	@RequestMapping(value = "corporcodeSave")
	@ResponseBody
	public String corporCodeSave(CorporationRequestCode entity, Model model, HttpServletRequest request) {
		String result = "false";
		boolean flag = corporcodeService.saveCorpor(entity);
		if (flag) {
			result = "true";
		}
		return result;
	}

	/**
	 * 加载企业用户关系表,如果userId不为空,则先进行保存企业用户关系,后进行加载
	 * 若如果userId为空,则直接按照企业Id加载现有关系
	 * glq 2017-06-29
	 * @param entity
	 * @param userId
	 * @param model
     * @return
     */
	@RequestMapping(value = "listByCorpId")
	public String corpUserListByCorpId(CorporationUserRelation entity,String userId, String projectId, Model model) {
		List<String> result = null;
		if(StringUtils.isNotBlank(userId)){
			result = Arrays.asList(userId.split(","));
			for(String id:result) {
				CorporationUserRelation corporationUserRelation = corporationUserRelationnService.getByCorUserIdAndCorId(entity);
				if(corporationUserRelation==null) {
					entity.setIsAdmin("1");
					entity.setCorporationUserId(id);
					corporationUserRelationnService.save(entity);
				}else {
					entity.setCorporationUserId(id);
					corporationUserRelationnService.insert(entity);
				}
			}
		}

		boolean hasAdmin = false;
		List<CorporationUserRelation> corporationUserRelations = corporationUserRelationnService.findList(entity);
		if(corporationUserRelations.size()>0) {
			CorporationRequestCode corporationRequestCode = new CorporationRequestCode();
			corporationRequestCode.setStatus("0");
			for(CorporationUserRelation corporationUserRelation:corporationUserRelations) {
				corporationUserRelation.setIsChoose("false");
				if(corporationUserRelation.getIsAdmin().equals("0")) {
					hasAdmin = true;
				}
				corporationRequestCode.setCorporUserRelaId(corporationUserRelation.getId());
				corporationRequestCode.setProjectId(projectId);
				List<CorporationRequestCode> corporationRequestCodes = corporcodeService.getNotInvalidCorpcode(corporationRequestCode);
				if(corporationRequestCodes != null && corporationRequestCodes.size() > 0){
					corporationUserRelation.setIsChoose("true");
				}
			}
		}
		model.addAttribute("corporationUserRelations", corporationUserRelations);
		model.addAttribute("hasAdmin", hasAdmin);
		return "modules/businessGuide/corpUserList";
	}

	/**
	 * 删除企业用户关系,如果白名单中存在未失效的,则不允许删除
	 * glq 2017-06-29
	 * @param entity
	 * @return
     */
	@RequestMapping(value = "deleteCorpUserRel")
	@ResponseBody
	public String advertiseDelete(CorporationUserRelation entity) {
		if(corporcodeService.getByCorUserId(entity.getId())!=null) {
			return "false:无法删除,该用户已存在于企业白名单!";
		}else {
			corporationUserRelationnService.delete(entity);
			return "true";
		}
	}
}
