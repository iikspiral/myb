/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.certCtml.web;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.certCtml.entity.CertCtml;
import com.sxca.myb.modules.certCtml.entity.CertCtmlSelfExt;
import com.sxca.myb.modules.certCtml.entity.CertCtmlStandardExt;
import com.sxca.myb.modules.certCtml.service.CertCtmlSelfExtService;
import com.sxca.myb.modules.certCtml.service.CertCtmlService;
import com.sxca.myb.modules.certCtml.service.CertCtmlStandardExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/certCtml")
public class CertCtmlController extends BaseController {

	@Autowired
	private CertCtmlService certCtmlService;
	@Autowired
	private CertCtmlSelfExtService certCtmlSelfExtService;
	@Autowired
	private CertCtmlStandardExtService certCtmlStandardExtService;
	
	@ModelAttribute("certCtml")
	public CertCtml get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return certCtmlService.get(id);
		}else{
			return new CertCtml();
		}
	}

	@RequestMapping(value = {"list", ""})
	public String list(CertCtml certCtml, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CertCtml> page = certCtmlService.findPage(new Page<CertCtml>(request, response), certCtml);
		model.addAttribute("page", page);
		return "modules/certCtml/certCtmlList";
	}

	@RequestMapping(value = "form")
	public String form(CertCtml certCtml, Model model) {
		if (certCtml.getId() != null && !"".equals(certCtml.getId())){
			model.addAttribute("certCtml", certCtmlService.get(certCtml.getId()));
			List<CertCtmlSelfExt> selfExtList = certCtmlSelfExtService.findSelfExtsByCtmlId(certCtml.getId());
			model.addAttribute("selfExtList",selfExtList);
			List<CertCtmlStandardExt> standardExtList = certCtmlStandardExtService.findStandardExtsByCtmlId(certCtml.getId());
			model.addAttribute("standardExtList",standardExtList);
		}
		return "modules/certCtml/certCtmlForm";
	}

	/**
	 * 模板同步
	 */
	@RequestMapping(value="/synchCtml",method= RequestMethod.POST)
	@ResponseBody
	public boolean synchCtml(HttpServletRequest request){
		boolean flag = certCtmlService.synchCtml();
		return flag;
	}

}
