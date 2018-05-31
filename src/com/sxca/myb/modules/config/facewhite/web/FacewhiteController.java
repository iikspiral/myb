package com.sxca.myb.modules.config.facewhite.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.config.facewhite.entity.Facewhite;
import com.sxca.myb.modules.config.facewhite.service.FacewhiteService;
import com.sxca.myb.modules.config.facewhite.vo.FacewhiteVo;

/**
 * @author lihuilong 
 * @date : 2017年5月2日 下午6:04:33
 */
@Controller
@RequestMapping(value = "${adminPath}/facewhite")
public class FacewhiteController extends BaseController{

	@Autowired
	private FacewhiteService facewhiteService;
	
	
	@RequestMapping(value = "list")
	public String facewhiteList(Facewhite entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		//通过clickType  来对查询实体entity 进行赋值  clickType = 1 代表界面查询操作，进行赋值
		if(StringUtils.isNotBlank(clickType) && ("1").equals(clickType)){
			FacewhiteVo facewhiteVo = new FacewhiteVo();
			facewhiteVo.setIdCardNum(entity.getIdCardNum());
			facewhiteVo.setIsEffect(entity.getIsEffect());
			facewhiteVo.setUserName(entity.getUserName());
			
			request.getSession().setAttribute("facewhiteVo", facewhiteVo);
			
			model.addAttribute("facewhiteVo", facewhiteVo);
		}else if(StringUtils.isNotBlank(clickType) && ("2").equals(clickType)){
			FacewhiteVo facewhiteVo = (FacewhiteVo) request.getSession().getAttribute("facewhiteVo");
			entity.setIdCardNum(facewhiteVo.getIdCardNum());
			entity.setIsEffect(facewhiteVo.getIsEffect());
			entity.setUserName(facewhiteVo.getUserName());
			
			model.addAttribute("facewhiteVo", facewhiteVo);
		}else{
			request.getSession().setAttribute("facewhiteVo", new FacewhiteVo());
		}

		Page<Facewhite> page = facewhiteService.findPage(
				new Page<Facewhite>(request, response), entity);
		model.addAttribute("page", page);

		return "modules/config/facewhite/facewhiteList";
	}
	
	
	@RequestMapping(value = "form")
	private String index(Facewhite entity, HttpServletRequest request,
			Model model) {

		if (StringUtils.isBlank(entity.getId())) {
			entity = new Facewhite();
		} else {
			entity = facewhiteService.get(entity.getId());
		}

		String sessionid = request.getSession().getId();
		model.addAttribute("sessionid", sessionid);
		model.addAttribute("facewhite", entity);
		return "modules/config/facewhite/form";
	}
	
	
	@RequestMapping(value = "save")
	public String facewhiteSave(Facewhite entity, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		entity.setIsEffect("0");
		facewhiteService.save(entity);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/facewhite/list/?repage";
	}
	
	
	@RequestMapping(value = "delete")
	public String facewhiteDelete(Facewhite entity,
			RedirectAttributes redirectAttributes) {
		facewhiteService.delete(entity);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/facewhite/list/?repage";
	}
	
	
	/**
	 * 验证记录唯一性
	 * @param strategy
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkUnique")
	public String checkUnique(String idCardNum,String id) {
		
		if(StringUtils.isNotBlank(id)){
			Facewhite entity = facewhiteService.get(id);
			if(idCardNum.equals(entity.getIdCardNum())){
				return "true";
			}else{
				Facewhite entity1 = new Facewhite();
				entity1.setIdCardNum(idCardNum);
				
				List<Facewhite> facewhiteInfo = facewhiteService.findList(entity1);
				
				if (facewhiteInfo != null && facewhiteInfo.size() > 0) {
					return "false";
				}else{
					return "true";
				}
			}
		}else{
			Facewhite entity = new Facewhite();
			entity.setIdCardNum(idCardNum);
			
			List<Facewhite> facewhiteInfo = facewhiteService.findList(entity);
			
			if (facewhiteInfo != null && facewhiteInfo.size() > 0) {
				return "false";
			}else{
				return "true";
			}
		}
		
	}
	
}
