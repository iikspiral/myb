/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.sys.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.sys.entity.Log;
import com.sxca.myb.modules.sys.service.LogService;

/**
 * 日志Controller
 * @author ThinkGem
 * @version 2013-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;
	
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = {"list", ""})
	public String list(Log log, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
		Date endDate = log.getEndDate();
		if(endDate!=null) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			String str=sdf.format(endDate); 
			str = str + " 23:59:59";
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			endDate = sdf1.parse(str);
			log.setEndDate(endDate);
		}
        Page<Log> page = logService.findPage(new Page<Log>(request, response), log); 
        model.addAttribute("page", page);
		return "modules/sys/logList";
	}

}
