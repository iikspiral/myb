package com.sxca.myb.modules.sms.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.sms.entity.SMSTemplate;
import com.sxca.myb.modules.sms.service.SMSTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * 通知管理短息模版控制层
 * @author glq
 * @time   20170421
 */

@Controller
@RequestMapping("${adminPath}/smsTemplate")
public class SMSTemplateController extends BaseController {
	@Autowired
	private SMSTemplateService SMSTemplateService;

	@ModelAttribute("smsTemplate")
	public SMSTemplate get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return SMSTemplateService.get(id);
		}else{
			return new SMSTemplate();
		}
	}

	/**
	 * 初始化列表信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/temList")
	public String temList(SMSTemplate smsTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SMSTemplate> page = SMSTemplateService.findPage(new Page<SMSTemplate>(request, response), smsTemplate);
		model.addAttribute("page", page);// 页面数据，包括每页显示多少条和总条数
		return "modules/sms/temList";

	}
	/**
	 * 跳转到添加或修改页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "temForm")
	public String temForm(SMSTemplate smsTemplate, Model model) {
		if (smsTemplate.getId() != null && !"".equals(smsTemplate.getId())){
			model.addAttribute("smsTemplate", SMSTemplateService.get(smsTemplate.getId()));
		}
		return "modules/sms/temForm";
	}
	/**
	 * 保存或者修改
	 * @param smsTemplate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "temSave")
	public String temSave(SMSTemplate smsTemplate, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		SMSTemplateService.save(smsTemplate);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/smsTemplate/temList";
	}
	/**
	 * 删除
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String del(SMSTemplate smsTemplate, HttpServletRequest request) throws Exception{
		SMSTemplateService.delete(smsTemplate);
		return "redirect:" + adminPath + "/smsTemplate/temList";
	}
}
