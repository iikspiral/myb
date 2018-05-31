package com.sxca.myb.modules.idinfo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.idinfo.service.CorporationUserInfoService;
import com.sxca.myb.modules.idinfo.service.CorporationUserRelationnService;
import com.sxca.myb.modules.idinfo.service.UserInfoService;
import com.sxca.myb.modules.mobile.service.UserConversationService;


@Controller
@RequestMapping(value = "${adminPath}/corpUserInfo")
public class CorporationUserInfoController extends BaseController{

	@Autowired
	private CorporationUserInfoService corporationUserInfoService;
	
	@Autowired
	private CorporationUserRelationnService corporationUserRelationnService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserConversationService userConversationService;
	
	@ModelAttribute
	public CorporationUserInfo get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return corporationUserInfoService.get(id);
		} else {
			return new CorporationUserInfo();
		}
	}
	
	@RequestMapping(value = "form")
	private String index(CorporationUserInfo entity) {
		return "modules/idinfo/corporationUserInfo/form";
	}
	
	@RequestMapping(value = "form1")
	private String index1(String corporationId,Model model) {
		model.addAttribute("corporationId", corporationId);
		return "modules/idinfo/corporationUserInfo/form1";
	}
	
	@RequestMapping(value = "list")
	public String corporationInfoList(CorporationUserInfo entity,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		Page<CorporationUserInfo> page = corporationUserInfoService.findPage(
				new Page<CorporationUserInfo>(request, response), entity);
		model.addAttribute("page", page);

		return "modules/idinfo/corporationUserInfo/corpUserList";
	}
	
	@RequestMapping(value = "save")
	public String platformStrategySave(CorporationUserInfo entity, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes){
		
		if(StringUtils.isNotBlank(entity.getId())) {
			UserInfo userInfoSerch = new UserInfo();
			userInfoSerch.setUseridcardnum(entity.getIdCard());
			List<UserInfo> userInfos = userInfoService.findList(userInfoSerch);
			if(userInfos!=null&&userInfos.size()>0) {
				UserInfo userInfo = userInfos.get(0);
				userInfo.setUsername(entity.getUserName());
				userInfoService.saveAndRasave(userInfo);
			}
			
			//判断手机号是否修改，如果修改，删除 user_conversation 表中，该用户的会话信息
			CorporationUserInfo corporationUserInfo = corporationUserInfoService.get(entity.getId());
			if(!corporationUserInfo.getPhoneNum().equals(entity.getPhoneNum())){
				userConversationService.deleteByPhonenum(corporationUserInfo.getPhoneNum());
			}
		}else {
			entity.setRdmNum(StringUtils.rdmNum());
		}
		corporationUserInfoService.save(entity);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/corpUserInfo/list/?repage";
		
	}
	
	@RequestMapping(value = "save1")
	public String save1(CorporationUserInfo entity,String corporationId, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes){
		
		if(!StringUtils.isNotBlank(entity.getId())) {
			entity.setRdmNum(StringUtils.rdmNum());
		}
		corporationUserInfoService.save(entity);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/corpUserRel/corpUserList?corporationId="+corporationId;
		
	}
	
	@RequestMapping(value = "delete")
	public String advertiseDelete(CorporationUserInfo entity,
			RedirectAttributes redirectAttributes) {
		
		if(corporationUserRelationnService.getByCorUserId(entity.getId())!=null) {
			addMessage(redirectAttributes, "无法删除,该用户已被企业使用!");
		}else {
			corporationUserInfoService.delete(entity);
			addMessage(redirectAttributes, "删除成功");
		}
		return "redirect:" + adminPath + "/corpUserInfo/list/?repage";
	}
	
	/**
	 * 验证记录唯一性
	 * @param strategy
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkUnique")
	public String checkUnique(String oldIdCard, String idCard) {
		if(idCard != null && idCard.equals(oldIdCard)) {
			return "true";
		}else if (idCard != null && corporationUserInfoService.getByIdCard(idCard) == null) {
			return "true";
		}
		return "false";
	}
	
	/**
	 * 验证记录唯一性
	 * @param strategy
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkUserName")
	public String checkUserName(String userName, String idCard) throws Exception{
		UserInfo userInfoSerch = new UserInfo();
		userInfoSerch.setUseridcardnum(idCard);
		List<UserInfo> userInfos = userInfoService.findList(userInfoSerch);
		if(userInfos!=null&&userInfos.size()>0) {
			UserInfo userInfo = userInfos.get(0);
			if(userName.equals(userInfo.getUsername())) {
				return "true";
			}else {
				return "false";
			}
		}else {
			return "true";
		}
	}
}
