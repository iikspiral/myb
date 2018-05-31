package com.sxca.myb.modules.idinfo.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;
import com.sxca.myb.modules.config.whitelist.service.WhiteListService;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.idinfo.entity.ExtraInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.idinfo.service.CorporationUserInfoService;
import com.sxca.myb.modules.idinfo.service.ExtraInfoService;
import com.sxca.myb.modules.idinfo.service.UserInfoService;
import com.sxca.myb.modules.idinfo.vo.UserInfoVo;

/**
 * @author lihuilong
 * @date : 2017年4月11日 上午11:25:21
 */
@Controller
@RequestMapping(value = "${adminPath}/userinfo")
public class UserInfoController extends BaseController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private ExtraInfoService extraInfoService;

	@Autowired
	private CorporationUserInfoService corporationUserInfoService;
	
	@Autowired
	private WhiteListService whiteListService;

	@RequestMapping(value = "list")
	public String userinfoList(UserInfo entity,String clickType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		//通过clickType  来对查询实体entity 进行赋值  clickType = 1 代表界面查询操作，进行赋值
		if(StringUtils.isNotBlank(clickType) && ("1").equals(clickType)){
			UserInfoVo userInfoVo = new UserInfoVo();
			userInfoVo.setUsername(entity.getUsername());
			userInfoVo.setUsercontactaddr(entity.getUsercontactaddr());
			userInfoVo.setOrg(entity.getOrg());
			userInfoVo.setIdtype(entity.getIdtype());
			
			request.getSession().setAttribute("userInfoVo", userInfoVo);
			
			model.addAttribute("userInfoVo", userInfoVo);
		}else if(StringUtils.isNotBlank(clickType) && ("2").equals(clickType)){
			UserInfoVo userInfoVo = (UserInfoVo) request.getSession().getAttribute("userInfoVo");
			entity.setUsername(userInfoVo.getUsername());
			entity.setUsercontactaddr(userInfoVo.getUsercontactaddr());
			entity.setOrg(userInfoVo.getOrg());
			entity.setIdtype(entity.getIdtype());
			
			model.addAttribute("userInfoVo", userInfoVo);
		}else{
			request.getSession().setAttribute("userInfoVo", new UserInfoVo());
		}

		Page<UserInfo> page = userInfoService.findPage(new Page<UserInfo>(
				request, response), entity);
		model.addAttribute("page", page);

		return "modules/idinfo/userinfo/userinfoList";
	}

	@RequestMapping(value = "form")
	private String index(UserInfo entity, HttpServletRequest request,
			Model model) {

		if (StringUtils.isBlank(entity.getId())) {
			entity = new UserInfo();
			entity.setIsFace("0");
		} else {
			entity = userInfoService.get(entity.getId());
		}
		entity.setIdtype("01");
		// 查询对应的扩展属性是否已经添加
		ExtraInfo extraInfoVo1 = new ExtraInfo();
		extraInfoVo1.setAttribute("extra1");
		extraInfoVo1.setUserInfoType("userinfo");
		List<ExtraInfo> extraInfo1List = extraInfoService
				.findList(extraInfoVo1);
		if (extraInfo1List != null && extraInfo1List.size() > 0) {
			ExtraInfo extraInfo1 = extraInfo1List.get(0);
			if (entity.getExtra1() == null || entity.getExtra1() == "") {
				entity.setExtra1(extraInfo1.getDefaultValue());
			}
			model.addAttribute("extraInfo1", extraInfo1);
		}
		ExtraInfo extraInfoVo2 = new ExtraInfo();
		extraInfoVo2.setAttribute("extra2");
		extraInfoVo2.setUserInfoType("userinfo");
		List<ExtraInfo> extraInfo2List = extraInfoService
				.findList(extraInfoVo2);
		if (extraInfo2List != null && extraInfo2List.size() > 0) {
			ExtraInfo extraInfo2 = extraInfo2List.get(0);
			if (entity.getExtra2() == null || entity.getExtra2() == "") {
				entity.setExtra2(extraInfo2.getDefaultValue());
			}
			model.addAttribute("extraInfo2", extraInfo2);
		}
		ExtraInfo extraInfoVo3 = new ExtraInfo();
		extraInfoVo3.setAttribute("extra3");
		extraInfoVo3.setUserInfoType("userinfo");
		List<ExtraInfo> extraInfo3List = extraInfoService
				.findList(extraInfoVo3);
		if (extraInfo3List != null && extraInfo3List.size() > 0) {
			ExtraInfo extraInfo3 = extraInfo3List.get(0);
			if (entity.getExtra3() == null || entity.getExtra3() == "") {
				entity.setExtra3(extraInfo3.getDefaultValue());
			}
			model.addAttribute("extraInfo3", extraInfo3);
		}
		ExtraInfo extraInfoVo4 = new ExtraInfo();
		extraInfoVo4.setAttribute("extra4");
		extraInfoVo4.setUserInfoType("userinfo");
		List<ExtraInfo> extraInfo4List = extraInfoService
				.findList(extraInfoVo4);
		if (extraInfo4List != null && extraInfo4List.size() > 0) {
			ExtraInfo extraInfo4 = extraInfo4List.get(0);
			if (entity.getExtra4() == null || entity.getExtra4() == "") {
				entity.setExtra4(extraInfo4.getDefaultValue());
			}
			model.addAttribute("extraInfo4", extraInfo4);
		}
		ExtraInfo extraInfoVo5 = new ExtraInfo();
		extraInfoVo5.setAttribute("extra5");
		extraInfoVo5.setUserInfoType("userinfo");
		List<ExtraInfo> extraInfo5List = extraInfoService
				.findList(extraInfoVo5);
		if (extraInfo5List != null && extraInfo5List.size() > 0) {
			ExtraInfo extraInfo5 = extraInfo5List.get(0);
			if (entity.getExtra5() == null || entity.getExtra5() == "") {
				entity.setExtra5(extraInfo5.getDefaultValue());
			}
			model.addAttribute("extraInfo5", extraInfo5);
		}
		ExtraInfo extraInfoVo6 = new ExtraInfo();
		extraInfoVo6.setAttribute("extra6");
		extraInfoVo6.setUserInfoType("userinfo");
		List<ExtraInfo> extraInfo6List = extraInfoService
				.findList(extraInfoVo6);
		if (extraInfo6List != null && extraInfo6List.size() > 0) {
			ExtraInfo extraInfo6 = extraInfo6List.get(0);
			if (entity.getExtra6() == null || entity.getExtra6() == "") {
				entity.setExtra6(extraInfo6.getDefaultValue());
			}
			model.addAttribute("extraInfo6", extraInfo6);
		}
		ExtraInfo extraInfoVo7 = new ExtraInfo();
		extraInfoVo7.setAttribute("extra7");
		extraInfoVo7.setUserInfoType("userinfo");
		List<ExtraInfo> extraInfo7List = extraInfoService
				.findList(extraInfoVo7);
		if (extraInfo7List != null && extraInfo7List.size() > 0) {
			ExtraInfo extraInfo7 = extraInfo7List.get(0);
			if (entity.getExtra7() == null || entity.getExtra7() == "") {
				entity.setExtra7(extraInfo7.getDefaultValue());
			}
			model.addAttribute("extraInfo7", extraInfo7);
		}
		ExtraInfo extraInfoVo8 = new ExtraInfo();
		extraInfoVo8.setAttribute("extra8");
		extraInfoVo8.setUserInfoType("userinfo");
		List<ExtraInfo> extraInfo8List = extraInfoService
				.findList(extraInfoVo8);
		if (extraInfo8List != null && extraInfo8List.size() > 0) {
			ExtraInfo extraInfo8 = extraInfo8List.get(0);
			if (entity.getExtra8() == null || entity.getExtra8() == "") {
				entity.setExtra8(extraInfo8.getDefaultValue());
			}
			model.addAttribute("extraInfo8", extraInfo8);
		}
		ExtraInfo extraInfoVo9 = new ExtraInfo();
		extraInfoVo9.setAttribute("extra9");
		extraInfoVo9.setUserInfoType("userinfo");
		List<ExtraInfo> extraInfo9List = extraInfoService
				.findList(extraInfoVo9);
		if (extraInfo9List != null && extraInfo9List.size() > 0) {
			ExtraInfo extraInfo9 = extraInfo9List.get(0);
			if (entity.getExtra9() == null || entity.getExtra9() == "") {
				entity.setExtra9(extraInfo9.getDefaultValue());
			}
			model.addAttribute("extraInfo9", extraInfo9);
		}
		ExtraInfo extraInfoVo10 = new ExtraInfo();
		extraInfoVo10.setAttribute("extra10");
		extraInfoVo10.setUserInfoType("userinfo");
		List<ExtraInfo> extraInfo10List = extraInfoService
				.findList(extraInfoVo10);
		if (extraInfo10List != null && extraInfo10List.size() > 0) {
			ExtraInfo extraInfo10 = extraInfo10List.get(0);
			if (entity.getExtra10() == null || entity.getExtra10() == "") {
				entity.setExtra10(extraInfo10.getDefaultValue());
			}
			model.addAttribute("extraInfo10", extraInfo10);
		}

		String sessionid = request.getSession().getId();
		model.addAttribute("sessionid", sessionid);
		model.addAttribute("userInfo", entity);
		return "modules/idinfo/userinfo/form";
	}

	@RequestMapping(value = "save")
	public String userinfoSave(UserInfo entity, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(StringUtils.isBlank(entity.getId())){
			entity.setUserType("0");
		}
		if(userInfoService.saveAndRasave(entity)){
			addMessage(redirectAttributes, "保存成功");
			if(StringUtils.isNotBlank(entity.getId())){
				CorporationUserInfo corporationUserInfo = corporationUserInfoService.getByIdCard(entity.getUseridcardnum());
				if(corporationUserInfo != null){
					corporationUserInfo.setUserName(entity.getUsername());
					corporationUserInfoService.update(corporationUserInfo);
				}
				
			}
		}else{
			addMessage(redirectAttributes, "保存失败");
		}
		return "redirect:" + adminPath + "/userinfo/list/?repage";

	}

	@RequestMapping(value = "delete")
	public String userinfoDelete(UserInfo entity,
			RedirectAttributes redirectAttributes) {
		userInfoService.delete(entity);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/userinfo/list/?repage";
	}
	
	
	/**
	 * 验证记录唯一性
	 * @param strategy
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkUnique")
	public String checkUnique(String useridcardnum,String id) {
		
		if(StringUtils.isNotBlank(id)){
			UserInfo entity = userInfoService.get(id);
			if(useridcardnum.equals(entity.getUseridcardnum())){
				return "true";
			}else{
				UserInfo entity1 = new UserInfo();
				entity1.setUseridcardnum(useridcardnum);
				
				List<UserInfo> userInfo = userInfoService.findList(entity1);
				
				if (userInfo != null && userInfo.size() > 0) {
					return "false";
				}else{
					return "true";
				}
			}
		}else{
			UserInfo entity = new UserInfo();
			entity.setUseridcardnum(useridcardnum);
			
			List<UserInfo> userInfoList = userInfoService.findList(entity);
			
			if (userInfoList != null && userInfoList.size() > 0) {
				return "false";
			}else{
				return "true";
			}
		}
		
	}
	
	
	
	@RequestMapping(value = "updateCorpname")
	@ResponseBody
	public Boolean updateCorpname(String id,String idCard,String name, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		String userName = URLDecoder.decode(name, "UTF-8");
		
		if(StringUtils.isNotBlank(id)){
			return true;
		}else{
			//新增的同时 也根据身份证号去查询    
			CorporationUserInfo corporationUserInfo = corporationUserInfoService.getByIdCard(idCard);
			
			if(corporationUserInfo != null){
				//如果name 一样  校验失败
				if(userName.equals(corporationUserInfo.getUserName())){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
			
		}
		
	}

	
	
	@RequestMapping(value = "whiteIs")
	@ResponseBody
	public String decide(String id,Model model, HttpServletRequest request,HttpServletResponse response) {
			String isOK = null;
			if (StringUtils.isNotBlank(id)) {
				WhiteList whiteList = new WhiteList();
				whiteList.setUserInfoId(id);
				List<WhiteList> whitebyList=whiteListService.findList(whiteList);
				if(whitebyList!=null && whitebyList.size()>0){
					isOK="OK"; //不能改
					return isOK;
				}else{
					isOK=""; //可以改
				}
				
			}
			return isOK;
	
	}	
			
	
	
}
