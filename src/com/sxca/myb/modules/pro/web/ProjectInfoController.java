package com.sxca.myb.modules.pro.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxca.myb.common.config.ApplyConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.config.corporcode.entity.CorporationRequestCode;
import com.sxca.myb.modules.config.corporcode.service.CorporcodeService;
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;
import com.sxca.myb.modules.config.whitelist.service.WhiteListService;
import com.sxca.myb.modules.pro.entity.ProjectInfo;
import com.sxca.myb.modules.pro.service.ProjectInfoService;
import com.sxca.myb.modules.pro.vo.ProjectInfoVo;

/**
 * 项目信息
 * 
 * @author lihuilong
 * @date : 2017年4月20日 上午8:47:45
 */
@Controller
@RequestMapping(value = "${adminPath}/projectinfo")
public class ProjectInfoController extends BaseController {

	@Autowired
	private ProjectInfoService projectInfoService;
	
	@Autowired
	private WhiteListService whiteListService;
	
	@Autowired
	private CorporcodeService corporcodeService;

	@RequestMapping(value = "list")
	public String projectinfoList(ProjectInfo entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		//通过clickType  来对查询实体entity 进行赋值  clickType = 1 代表界面查询操作，进行赋值
		if(StringUtils.isNotBlank(clickType) && ("1").equals(clickType)){
			ProjectInfoVo projectInfoVo = new ProjectInfoVo();
			projectInfoVo.setProjectName(entity.getProjectName());
			projectInfoVo.setCertTemplate(entity.getCertTemplate());
			projectInfoVo.setIsDefaultProject(entity.getIsDefaultProject());
			projectInfoVo.setIsHoldKey(entity.getIsHoldKey());
			request.getSession().setAttribute("projectInfoVo", projectInfoVo);
			
			model.addAttribute("projectInfoVo", projectInfoVo);
		}else if(StringUtils.isNotBlank(clickType) && ("2").equals(clickType)){
			ProjectInfoVo projectInfoVo = (ProjectInfoVo) request.getSession().getAttribute("projectInfoVo");
			entity.setProjectName(projectInfoVo.getProjectName());
			entity.setCertTemplate(projectInfoVo.getCertTemplate());
			entity.setIsDefaultProject(projectInfoVo.getIsDefaultProject());
			entity.setIsHoldKey(projectInfoVo.getIsHoldKey());
			
			model.addAttribute("projectInfoVo", projectInfoVo);
		}else{
			request.getSession().setAttribute("projectInfoVo", new ProjectInfoVo());
		}
		

		Page<ProjectInfo> page = projectInfoService.findPage(
				new Page<ProjectInfo>(request, response), entity);
		model.addAttribute("page", page);

		return "modules/pro/proinfoList";
	}

	@RequestMapping(value = "form")
	private String index(ProjectInfo entity, HttpServletRequest request,
			Model model) {

		if (StringUtils.isBlank(entity.getId())) {
			entity = new ProjectInfo();
			entity.setIsDefaultProject(ApplyConstants.ISDEAFULT_PRO_YES);
			entity.setIsHoldKey("1");
//			entity.setIsuseFace("0");
		} else {
			entity = projectInfoService.get(entity.getId());
		}

		String sessionid = request.getSession().getId();
		model.addAttribute("sessionid", sessionid);
		model.addAttribute("projectInfo", entity);
		return "modules/pro/form";
	}

	@RequestMapping(value = "save")
	public String proinfoSave(ProjectInfo entity, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		projectInfoService.save(entity);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/projectinfo/list/?repage";

	}

	@RequestMapping(value = "delete")
	public String proinfoDelete(ProjectInfo entity,
			RedirectAttributes redirectAttributes) {
		projectInfoService.delete(entity);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/projectinfo/list/?repage";
	}
	
	
	@RequestMapping(value = "checkUnique")
	@ResponseBody
	public Boolean checkUnique(String id,String isDefaultProject, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		if(id != null && id != "" && id.length() > 0){
			ProjectInfo entity = projectInfoService.get(id);
			if(entity!=null){
				if(isDefaultProject.equals(entity.getIsDefaultProject())){
					return true;
				}else{
					ProjectInfo entity1 = new ProjectInfo();
					entity1.setIsDefaultProject(isDefaultProject);
					
					List<ProjectInfo> projectInfo = projectInfoService.findList(entity1);
					if(projectInfo != null && projectInfo.size()>0){
						return false;
					}else{
						return true;
					}
				}
			}else{
				return true;
			}
			
		}else{
			ProjectInfo entity = new ProjectInfo();
			entity.setIsDefaultProject(isDefaultProject);
			
			List<ProjectInfo> projectInfo = projectInfoService.findList(entity);
			if(projectInfo != null && projectInfo.size()>0){
				return false;
			}else{
				return true;
			}
		}
		
	}
	
	/**
	 * 验证记录唯一性
	 * @param strategy
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkUniquePronum")
	public String checkUniquePronum(String projectNum,String id) {
		
		if(StringUtils.isNotBlank(id)){
			ProjectInfo entity = projectInfoService.get(id);
			if(projectNum.equals(entity.getProjectNum())){
				return "true";
			}else{
				ProjectInfo entity1 = new ProjectInfo();
				entity1.setProjectNum(projectNum);
				
				List<ProjectInfo> projectInfo = projectInfoService.findList(entity1);
				
				if (projectInfo != null && projectInfo.size() > 0) {
					return "false";
				}else{
					return "true";
				}
			}
		}else{
			ProjectInfo entity = new ProjectInfo();
			entity.setProjectNum(projectNum);
			
			List<ProjectInfo> projectInfo = projectInfoService.findList(entity);
			
			if (projectInfo != null && projectInfo.size() > 0) {
				return "false";
			}else{
				return "true";
			}
		}
		
	}
	
	/**
	 * 项目修改时  去白名单去查询 是否该项目是否使用，如果使用  制证规则不允许修改
	 * @param makeCertRules
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkMakecertRules")	
	public String checkMakecertRules(String makeCertRules,String id){
		
		if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(makeCertRules)){
			ProjectInfo projectInfo = projectInfoService.get(id);
			if(makeCertRules.equals(projectInfo.getMakeCertRules())){
				return "true";
			}else{
				boolean flag = true;
				//个人白名单去查
				List<WhiteList> whiteList = whiteListService.findbyprojectInfoId(id);
				if(whiteList.size() > 0 && whiteList != null){
					flag = false;
				}
				//企业白名单去查
				CorporationRequestCode corporationRequestCode = new CorporationRequestCode();
				corporationRequestCode.setStatus("0");
				corporationRequestCode.setProjectId(id);
				List<CorporationRequestCode>  codeList = corporcodeService.findList(corporationRequestCode);
				if(codeList != null && codeList.size() > 0){
					flag = false;
				}
				if(flag){
					return "true";
				}else{
					return "false";
				}
				
			}
		}else{
			return "true";
		}
	}
	/**
	 * 判断项目是否正在被白名单使用
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="checkProisInwhite")
	public Boolean checkProisInwhite(String id){
		
		boolean flag = true;
		//个人白名单去查
		List<WhiteList> whiteList = whiteListService.findbyprojectInfoId(id);
		if(whiteList.size() > 0 && whiteList != null){
			flag = false;
		}
		//企业白名单去查
		CorporationRequestCode corporationRequestCode = new CorporationRequestCode();
		corporationRequestCode.setStatus("0");
		corporationRequestCode.setProjectId(id);
		List<CorporationRequestCode>  codeList = corporcodeService.findList(corporationRequestCode);
		if(codeList != null && codeList.size() > 0){
			flag = false;
		}
		return flag;
	}

}
