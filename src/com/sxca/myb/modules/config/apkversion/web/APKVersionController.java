package com.sxca.myb.modules.config.apkversion.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.config.apkversion.entity.APKVersion;
import com.sxca.myb.modules.config.apkversion.service.APKVersionService;

@Controller
@RequestMapping(value = "${adminPath}/apk")
public class APKVersionController extends BaseController{
	
	@Autowired
	private APKVersionService apkVersionService;
	
	@RequestMapping(value = "list")
	public String apkVersionList(@ModelAttribute("apkVersion")APKVersion entity,HttpServletRequest request, HttpServletResponse response,Model model) {
		
		List<APKVersion> apkVersionList = apkVersionService.findList(new APKVersion());
		if(apkVersionList != null && apkVersionList.size() > 0){
			model.addAttribute("apkVersion", apkVersionList.get(0));
		}
		
		return "modules/config/apkversion/list";

	}

	@RequestMapping(value = "save")
	public String apkVersionSave(APKVersion entity, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
			apkVersionService.save(entity);
			addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/apk/list/?repage";

	}

}
