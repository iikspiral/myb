package com.sxca.myb.modules.config.logman.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.config.logman.entity.CertAppLog;
import com.sxca.myb.modules.config.logman.service.CertAppLogService;


/**  
* <p>Title: CertAppLogController</p>  
* <p>Description: </p>  
* <p>Company: 吉大正元</p>   
* @author Yophy  
* @date 上午8:31:03  
*/ 
@Controller
@RequestMapping(value = "${adminPath}/logman")
public class CertAppLogController extends BaseController {

	@Autowired
	private CertAppLogService certAppLogService;

	@ModelAttribute
	public CertAppLog get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return certAppLogService.get(id);
		} else {
			return new CertAppLog();
		}
	}


	@RequestMapping(value = "list")
	public String deviceInfoList(CertAppLog entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CertAppLog> page = certAppLogService.findPage(
				new Page<CertAppLog>(request, response), entity);
		model.addAttribute("page", page);
		return "modules/config/logman/list";

	}



}
