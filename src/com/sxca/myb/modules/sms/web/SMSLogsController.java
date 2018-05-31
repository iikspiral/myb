package com.sxca.myb.modules.sms.web;


import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.sms.entity.SMSLogs;
import com.sxca.myb.modules.sms.service.SMSLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 通知管理短信日志控制层
 * @author glq
 * @time   20170421
 */

@Controller
	@RequestMapping("${adminPath}/smsLogs")
public class SMSLogsController extends BaseController {
	@Autowired
	private SMSLogsService smsLogsService;

	@ModelAttribute("smsLogs")
	public SMSLogs get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return smsLogsService.get(id);
		}else{
			return new SMSLogs();
		}
	}

	/**
	 * 查询日志记录信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logsList")
	public String logsList(SMSLogs smsLogs, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SMSLogs> page = smsLogsService.findPage(new Page<SMSLogs>(request, response), smsLogs);
		model.addAttribute("page", page);// 页面数据，包括每页显示多少条和总条数
		return "modules/sms/logsList";
	}
	/**
	 * 跳转到添加或修改页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "logsForm")
	public String form(SMSLogs smsLogs, Model model) {
		if (smsLogs.getId() != null && !"".equals(smsLogs.getId())){
			model.addAttribute("smsPlatForm", smsLogsService.get(smsLogs.getId()));
		}
		return "modules/sms/logsForm";
	}
	/**
	 * 删除
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logsDel", method = RequestMethod.GET)
	public String del(SMSLogs smsLogs, HttpServletRequest request) throws Exception{
		smsLogsService.delete(smsLogs);
		return "redirect:" + adminPath + "smsLogs/logsList";
	}

}
