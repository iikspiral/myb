package com.sxca.myb.modules.config.deviceinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.config.deviceinfo.entity.DeviceInfo;
import com.sxca.myb.modules.config.deviceinfo.service.DeviceInfoService;
import com.sxca.myb.modules.config.deviceinfo.vo.DeviceInfoVo;


@Controller
@RequestMapping(value = "${adminPath}/device")
public class DeviceInfoController extends BaseController {

	@Autowired
	private DeviceInfoService deviceInfoService;

	@ModelAttribute
	public DeviceInfo get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return deviceInfoService.get(id);
		} else {
			return new DeviceInfo();
		}
	}

	@RequestMapping(value = "form")
	private String index(DeviceInfo entity) {
		return "modules/config/deviceinfo/form";
	}

	@RequestMapping(value = "list")
	public String deviceInfoList(DeviceInfo entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		//通过clickType  来对查询实体entity 进行赋值  clickType = 1 代表界面查询操作，进行赋值
		if(StringUtils.isNotBlank(clickType) && ("1").equals(clickType)){
			DeviceInfoVo deviceInfoVo = new DeviceInfoVo();
			deviceInfoVo.setDeviceIp(entity.getDeviceIp());
			deviceInfoVo.setDeviceNameeng(entity.getDeviceNameeng());
			deviceInfoVo.setDevicePort(entity.getDevicePort());
			
			request.getSession().setAttribute("deviceInfoVo", deviceInfoVo);
			
			model.addAttribute("deviceInfoVo", deviceInfoVo);
		}else if(StringUtils.isNotBlank(clickType) && ("2").equals(clickType)){
			DeviceInfoVo deviceInfoVo = (DeviceInfoVo) request.getSession().getAttribute("deviceInfoVo");
			entity.setDeviceIp(deviceInfoVo.getDeviceIp());
			entity.setDeviceNameeng(deviceInfoVo.getDeviceNameeng());
			entity.setDevicePort(deviceInfoVo.getDevicePort());
			
			model.addAttribute("deviceInfoVo", deviceInfoVo);
		}else{
			request.getSession().setAttribute("deviceInfoVo", new DeviceInfoVo());
		}
		
		Page<DeviceInfo> page = deviceInfoService.findPage(
				new Page<DeviceInfo>(request, response), entity);
		model.addAttribute("page", page);
		return "modules/config/deviceinfo/deviceList";

	}

	@RequestMapping(value = "save")
	public String applicationSystemSave(DeviceInfo entity, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
			
			deviceInfoService.save(entity);
			addMessage(redirectAttributes, "保存成功");
			return "redirect:" + adminPath + "/device/list/?repage";
		
		
	}

	@RequestMapping(value = "delete")
	public String applicationSystemDelete(DeviceInfo entity,
			RedirectAttributes redirectAttributes) {
		deviceInfoService.delete(entity);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/device/list/?repage";
	}
}
