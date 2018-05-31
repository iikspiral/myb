package com.sxca.myb.modules.config.appsystem.web;

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
import com.sxca.myb.modules.config.appsystem.entity.ApplicationSystem;
import com.sxca.myb.modules.config.appsystem.service.ApplicationSystemService;
import com.sxca.myb.modules.config.appsystem.vo.ApplicationSystemVo;
import com.sxca.myb.modules.config.basearea.entity.BaseArea;
import com.sxca.myb.modules.config.basearea.service.BaseAreaService;


@Controller
@RequestMapping(value = "${adminPath}/application")
public class ApplicationSystemController extends BaseController {

	@Autowired
	private ApplicationSystemService applicationSystemService;
	
	@Autowired
	private BaseAreaService baseAreaService;

	@ModelAttribute
	public ApplicationSystem get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return applicationSystemService.get(id);
		} else {
			return new ApplicationSystem();
		}
	}

	@RequestMapping(value = "form")
	private String index(ApplicationSystem entity,HttpServletRequest request, HttpServletResponse response,Model model) {
		
		List<BaseArea> areaList = baseAreaService.getFirstArealist();
		if(StringUtils.isNoneBlank(entity.getId())){
			if(StringUtils.isNotBlank(entity.getProvince())){
				BaseArea baseArea = new BaseArea();
				baseArea.setParentid(entity.getProvince());
				List<BaseArea> cityArealist = baseAreaService.getAreaList(baseArea);
				model.addAttribute("cityArealist", cityArealist);
			}
			if(StringUtils.isNotBlank(entity.getCity())){
				BaseArea baseArea = new BaseArea();
				baseArea.setParentid(entity.getCity());
				List<BaseArea> countryArealist = baseAreaService.getAreaList(baseArea);
				model.addAttribute("countryArealist", countryArealist);
			}
			
		}
		
		
		model.addAttribute("areaList", areaList);
		
		return "modules/config/applicationsystem/form";
	}

	@RequestMapping(value = "list")
	public String applicationSystemList(ApplicationSystem entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		//通过clickType  来对查询实体entity 进行赋值  clickType = 1 代表界面查询操作，进行赋值
		if(StringUtils.isNotBlank(clickType) && ("1").equals(clickType)){
			ApplicationSystemVo applicationSystemVo = new ApplicationSystemVo();
			applicationSystemVo.setSystemName(entity.getSystemName());
			
			request.getSession().setAttribute("applicationSystemVo", applicationSystemVo);
			
			model.addAttribute("applicationSystemVo", applicationSystemVo);
		}else if(StringUtils.isNotBlank(clickType) && ("2").equals(clickType)){
			ApplicationSystemVo applicationSystemVo = (ApplicationSystemVo) request.getSession().getAttribute("applicationSystemVo");
			entity.setSystemName(applicationSystemVo.getSystemName());
			
			model.addAttribute("applicationSystemVo", applicationSystemVo);
		}else{
			request.getSession().setAttribute("applicationSystemVo", new ApplicationSystemVo());
		}
		
		Page<ApplicationSystem> page = applicationSystemService.findPage(
				new Page<ApplicationSystem>(request, response), entity);
		model.addAttribute("page", page);
		return "modules/config/applicationsystem/applicationList";

	}

	@RequestMapping(value = "save")
	public String applicationSystemSave(ApplicationSystem entity, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

			applicationSystemService.save(entity);
			addMessage(redirectAttributes, "保存成功");
			return "redirect:" + adminPath + "/application/list/?repage";
		
	}

	@RequestMapping(value = "delete")
	public String applicationSystemDelete(ApplicationSystem entity,
			RedirectAttributes redirectAttributes) {
		applicationSystemService.delete(entity);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/application/list/?repage";
	}
	
	/**
	 * 省市县获取下级菜单
	 * @param parentid
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="rhChange")
	@ResponseBody
	public String rhChange(String parentid,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		//获取下级菜单
		BaseArea baseArea = new BaseArea();
		baseArea.setParentid(parentid);
		List<BaseArea> baseArealist = baseAreaService.getAreaList(baseArea);
		
		String outText = "<option value=''>---请择---</option>";
		
		if(baseArealist != null && baseArealist.size() > 0){
			for (int i = 0; i < baseArealist.size(); i++) {
				outText += "<option value='"
						+ baseArealist.get(i).getBaseAreaid() + "'>"
						+ baseArealist.get(i).getName() + "</option>";
			}
		}
		
		return outText;
	}
	
	/**
	 * 验证记录唯一性
	 * @param strategy
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkUnique")
	public String checkUnique(String systemName,String id) {
		
		if(StringUtils.isNotBlank(id)){
			ApplicationSystem entity = applicationSystemService.get(id);
			if(systemName.equals(entity.getSystemName())){
				return "true";
			}else{
				ApplicationSystem entity1 = new ApplicationSystem();
				entity1.setSystemName(systemName);
				
				List<ApplicationSystem> pplicationSystemList = applicationSystemService.getListBysysname(entity1);
				
				if (pplicationSystemList != null && pplicationSystemList.size() > 0) {
					return "false";
				}else{
					return "true";
				}
			}
		}else{
			ApplicationSystem entity = new ApplicationSystem();
			entity.setSystemName(systemName);
			
			List<ApplicationSystem> pplicationSystemList = applicationSystemService.getListBysysname(entity);
			
			if (pplicationSystemList != null && pplicationSystemList.size() > 0) {
				return "false";
			}else{
				return "true";
			}
		}
		
	}

}
