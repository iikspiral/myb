package com.sxca.myb.modules.config.sysconfig.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sxca.myb.common.utils.CacheUtils;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.config.sysconfig.entity.SystemConfig;
import com.sxca.myb.modules.config.sysconfig.service.SystemConfigService;
import com.sxca.myb.modules.sys.entity.User;
import com.sxca.myb.modules.sys.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/systemconfig")
public class SystemConfigController extends BaseController {

	@Autowired
	private SystemConfigService systemConfigService;

	@RequestMapping(value = "form")
	private String index(SystemConfig entity) {
		return "modules/config/systemconfig/form";
	}

	@RequestMapping(value = "configList")
	public String systemConfigList(SystemConfig entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		model.addAttribute("user", user);
		List<SystemConfig> systemConfigList = systemConfigService.findList(new SystemConfig());
		if(systemConfigList != null && systemConfigList.size() > 0){
			model.addAttribute("systemConfig", systemConfigList.get(0));
		}else{
			SystemConfig systemConfig = new SystemConfig();
			systemConfig.setIsFace("0");
			systemConfig.setLoginType("0");
			systemConfig.setIsuseDefaultProject("0");
			model.addAttribute("systemConfig", systemConfig);
		}
		
		return "modules/config/systemconfig/list";

	}

	@RequestMapping(value = "save")
	public String systemConfigSave(SystemConfig entity, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
			CacheUtils.put("loginType", entity.getLoginType());
			CacheUtils.put("systemName", entity.getSystemName());
			CacheUtils.put("copyRightInfo", entity.getCopyRightInfo());
			systemConfigService.save(entity);
			addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/systemconfig/configList/?repage";

	}

	@RequestMapping(value = "delete")
	public String systemConfigDelete(SystemConfig entity,
			RedirectAttributes redirectAttributes) {
		systemConfigService.delete(entity);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/systemconfig/configList/?repage";
	}

	@RequestMapping(value = "isused")
	private String isUsed(SystemConfig entity,String clickType) {
		entity.setIsuseDefaultProject(entity.getIsuseDefaultProject().trim());
		systemConfigService.save(entity);
		return "redirect:" + adminPath + "/systemconfig/configList/?IsuseDefaultProject="+entity.getIsuseDefaultProject()+"&clickType="+clickType;
	}
	
	@RequestMapping(value = "checkUnique")
	@ResponseBody
	public Boolean decide(String id,Model model, HttpServletRequest request,HttpServletResponse response) {
			if (StringUtils.isNotBlank(id)) {
				User user = UserUtils.getUser();
				User usered = UserUtils.getUserById(user.toString());
				if("1".equals(user.toString()) &&
						usered.getCertSubject()!=null && !"".equals(usered.getCertSubject()) ){
					return true;
				}else{
					return false;
				}
				
			}
			return true;
	
	}	
}
