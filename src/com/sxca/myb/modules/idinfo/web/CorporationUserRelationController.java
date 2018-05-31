package com.sxca.myb.modules.idinfo.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sxca.myb.modules.config.corporcode.service.CorporcodeService;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserRelation;
import com.sxca.myb.modules.idinfo.service.CorporationUserInfoService;
import com.sxca.myb.modules.idinfo.service.CorporationUserRelationnService;


@Controller
@RequestMapping(value = "${adminPath}/corpUserRel")
public class CorporationUserRelationController extends BaseController{

	
	@Autowired
	private CorporationUserRelationnService corporationUserRelationnService;
	
	@Autowired
	private CorporationUserInfoService corporationUserInfoService;
	
	@Autowired
	private CorporcodeService corporcodeService;
	
	
	@ModelAttribute
	public CorporationUserRelation get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return corporationUserRelationnService.get(id);
		} else {
			return new CorporationUserRelation();
		}
	}
	
	@RequestMapping(value = "listByCorpId")
	public String corpUserListByCorpId(CorporationUserRelation entity,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		boolean hasAdmin = false;
		Page<CorporationUserRelation> page = corporationUserRelationnService.findPage(
				new Page<CorporationUserRelation>(request, response), entity);
		
		CorporationUserRelation corporationUserRelationSerch = new CorporationUserRelation();
		corporationUserRelationSerch.setCorporationId(entity.getCorporationId());
		List<CorporationUserRelation> corporationUserRelations = corporationUserRelationnService.findList(corporationUserRelationSerch);
		
		if(page.getList().size()>0) {
			for(CorporationUserRelation corporationUserRelation:corporationUserRelations) {
				if(corporationUserRelation.getIsAdmin().equals("0")) {
					hasAdmin = true;
				}
			}
		}
		
		model.addAttribute("page", page);
		model.addAttribute("corporationUserRelation", entity);
		model.addAttribute("hasAdmin", hasAdmin);
		return "modules/idinfo/corporationUserRelation/corpUserList";
	}
	
	@RequestMapping(value = "corpUserList")
	public String corpUserList(CorporationUserInfo entity,String corporationId,
			HttpServletRequest request, HttpServletResponse response,String corporationUserId,
			Model model) {
		CorporationUserRelation corporationUserRelation = new CorporationUserRelation();
		corporationUserRelation.setCorporationId(corporationId);
		List<String> userid=corporationUserRelationnService.getUserIds(corporationUserRelation);
		entity.setUserid(userid);
		Page<CorporationUserInfo> page = corporationUserInfoService.findPage(new Page<CorporationUserInfo>(request, response), entity);
		model.addAttribute("page", page);
		model.addAttribute("corporationId", corporationId);

		return "modules/idinfo/corporationUserRelation/corpUserSelect";
	}
	
	@RequestMapping(value = "save")
	public String platformStrategySave(CorporationUserRelation entity, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes,String userId,String corporationId){
		List<String> result = null;
		if(userId!=null && !"".equals(userId)){
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
			addMessage(redirectAttributes, "保存成功");
		}else{
			addMessage(redirectAttributes, "未选择用户保存失败");
		}
		return "redirect:" + adminPath + "/corpUserRel/listByCorpId/?corporationId="+corporationId;
	}
	
	@RequestMapping(value = "delete")
	public String advertiseDelete(CorporationUserRelation entity,
			RedirectAttributes redirectAttributes) {
		
		if(corporcodeService.getByCorUserId(entity.getId())!=null) {
			addMessage(redirectAttributes, "无法删除,该用户已存在于企业白名单!");
		}else {
			corporationUserRelationnService.delete(entity);
			addMessage(redirectAttributes, "删除成功");
		}
		return "redirect:" + adminPath + "/corpUserRel/listByCorpId/?corporationId="+entity.getCorporationId();
	}
	
	@RequestMapping(value = "setAdmin")
	@ResponseBody
	public String setAdmin(CorporationUserRelation entity,
			RedirectAttributes redirectAttributes) {
		
		String isOK = null;
		try {
			entity.setIsAdmin("0");
			corporationUserRelationnService.setAdmin(entity);
			addMessage(redirectAttributes, "保存成功");
			isOK = "OK";
		} catch (Exception e) {
			isOK = "fail";
			e.getStackTrace();
		}
		return isOK;
		
	}
	
	@RequestMapping(value = "cancelAdmin")
	@ResponseBody
	public String cancelAdmin(CorporationUserRelation entity,
			RedirectAttributes redirectAttributes) {
		
		String isOK = null;
		try {
			entity.setIsAdmin("1");
			corporationUserRelationnService.cancelAdmin(entity);
			addMessage(redirectAttributes, "保存成功");
			isOK = "OK";
		} catch (Exception e) {
			isOK = "fail";
			e.getStackTrace();
		}
		return isOK;
	}
}
