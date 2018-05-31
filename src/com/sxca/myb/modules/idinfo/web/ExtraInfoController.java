package com.sxca.myb.modules.idinfo.web;

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
import com.sxca.myb.modules.config.usertypedetail.entity.UserTypeDetail;
import com.sxca.myb.modules.config.usertypedetail.service.UserTypeDetailService;
import com.sxca.myb.modules.idinfo.entity.ExtraInfo;
import com.sxca.myb.modules.idinfo.service.ExtraInfoService;
import com.sxca.myb.modules.idinfo.vo.ExtraInfoVo;
import com.sxca.myb.modules.sys.entity.Dict;
import com.sxca.myb.modules.sys.service.DictService;

/**
 * @author lihuilong
 * @date : 2017年4月10日 下午3:06:41
 */
@Controller
@RequestMapping(value = "${adminPath}/extrainfo")
public class ExtraInfoController extends BaseController {

	@Autowired
	private ExtraInfoService extraInfoService;
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private UserTypeDetailService userTypeDetailService;

	@RequestMapping(value = "list")
	public String extrainfoList(ExtraInfo entity,String clickType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		//通过clickType  来对查询实体entity 进行赋值  clickType = 1 代表界面查询操作，进行赋值
		if(StringUtils.isNotBlank(clickType) && ("1").equals(clickType)){
			ExtraInfoVo extraInfoVo = new ExtraInfoVo();
			
			extraInfoVo.setDataType(entity.getDataType());
			extraInfoVo.setUserInfoType(entity.getUserInfoType());
			
			request.getSession().setAttribute("extraInfoVo", extraInfoVo);
			
			model.addAttribute("extraInfoVo", extraInfoVo);
		}else if(StringUtils.isNotBlank(clickType) && ("2").equals(clickType)){
			ExtraInfoVo extraInfoVo = (ExtraInfoVo) request.getSession().getAttribute("extraInfoVo");
			
			entity.setDataType(extraInfoVo.getDataType());
			entity.setUserInfoType(extraInfoVo.getUserInfoType());
			
			model.addAttribute("extraInfoVo", extraInfoVo);
		}else{
			request.getSession().setAttribute("extraInfoVo", new ExtraInfoVo());
		}

		Page<ExtraInfo> page = extraInfoService.findPage(new Page<ExtraInfo>(
				request, response), entity);
		model.addAttribute("page", page);

		return "modules/idinfo/extrainfo/extrainfoList";
	}

	@RequestMapping(value = "form")
	private String index(ExtraInfo entity, HttpServletRequest request,
			Model model) {

		if (StringUtils.isBlank(entity.getId())) {
			entity = new ExtraInfo();
			entity.setIsRequired("0");
			entity.setFiledLength(99);
		} else {
			entity = extraInfoService.get(entity.getId());
		}
		
		List<Dict> typeList = dictService.findDicExaList();
		
		String sessionid = request.getSession().getId();
		model.addAttribute("sessionid", sessionid);
		model.addAttribute("extraInfo", entity);
		model.addAttribute("typeList", typeList);
		return "modules/idinfo/extrainfo/form";
	}

	@RequestMapping(value = "save")
	public String extrainfoSave(ExtraInfo entity, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		extraInfoService.save(entity);
		
		//user_type_detail 扩展域 对应修改
		UserTypeDetail userTypeDetail = new UserTypeDetail();
		
		userTypeDetail.setUserType(entity.getUserInfoType());
		userTypeDetail.setFailName(entity.getAttribute());
		
		List<UserTypeDetail> usertypeDetailList = userTypeDetailService.findList(userTypeDetail);
		
		if(usertypeDetailList != null && usertypeDetailList.size() > 0){
			UserTypeDetail userTypeDetailEntity = usertypeDetailList.get(0);
			userTypeDetailEntity.setDisplayName(entity.getExtraMean());
			userTypeDetailService.update(userTypeDetailEntity);
		}
		
		
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/extrainfo/list/?repage";

	}

	@RequestMapping(value = "delete")
	public String extrainfoDelete(ExtraInfo entity,
			RedirectAttributes redirectAttributes) {
		extraInfoService.delete(entity);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/extrainfo/list/?repage";
	}
	
	/**
	 * 校验该项目下该用户类型是否已经添加
	 * @param projectId
	 * @param userInfoType
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "checkUnique")
	@ResponseBody
	public Boolean checkUnique(String id,String userInfoType,String attribute, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		if(id != null && id != "" && id.length() > 0){
			ExtraInfo entity = extraInfoService.get(id);
			if(entity!=null){
				if(userInfoType.equals(entity.getUserInfoType()) && attribute.equals(entity.getAttribute())){
					return true;
				}else{
					ExtraInfo entity1 = new ExtraInfo();
					entity1.setUserInfoType(userInfoType);
					entity1.setAttribute(attribute);
					
					List<ExtraInfo> extraInfo = extraInfoService.findList(entity1);
					if(extraInfo != null && extraInfo.size()>0){
						return false;
					}else{
						return true;
					}
				}
			}else{
				return true;
			}
			
		}else{
			ExtraInfo entity = new ExtraInfo();
			entity.setUserInfoType(userInfoType);
			entity.setAttribute(attribute);
			
			List<ExtraInfo> extraInfo = extraInfoService.findList(entity);
			if(extraInfo != null && extraInfo.size()>0){
				return false;
			}else{
				return true;
			}
		}
		
	}
}
