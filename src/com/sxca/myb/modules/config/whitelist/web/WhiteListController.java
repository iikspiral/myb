package com.sxca.myb.modules.config.whitelist.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.cert.service.CertInfoService;
import com.sxca.myb.modules.cert.service.CertapplyInfoService;
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;
import com.sxca.myb.modules.config.whitelist.service.WhiteListService;
import com.sxca.myb.modules.config.whitelist.vo.WhiteListVo;
import com.sxca.myb.modules.config.whitelist.vo.WhiteListselectVo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.idinfo.service.UserInfoService;
import com.sxca.myb.modules.mobile.service.MobileService;
import com.sxca.myb.modules.mobile.util.GetCertSubjectUtil;
import com.sxca.myb.modules.pro.entity.ProjectInfo;
import com.sxca.myb.modules.pro.service.ProjectInfoService;


/**
* <p>Title: WhiteListController</p>
* <p>Description: 个人白名单控制层 </p>
* <p>Company: 吉大正元</p>
* @author wfy
* @date 上午8:41:08
*/
@Controller
@RequestMapping(value = "${adminPath}/white")
public class WhiteListController extends BaseController {

	@Autowired
	private WhiteListService whiteListService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private CertInfoService certInfoService;

	@Autowired
	private ProjectInfoService projectInfoService;

	@Autowired
	private CertapplyInfoService certapplyInfoService;

	@Autowired
	private MobileService mobileService;

	/**author:wyf
	 * descripiton:跳转form方法
	 * @param entity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	private String index(WhiteList entity, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "modules/config/whitelist/form";
	}


	/**author:wyf
	 * descripiton:编辑单个用户方法的独立跳转页面
	 * @param entity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "editor")
	private String editor(WhiteList entity,String id, HttpServletRequest request, HttpServletResponse response,Model model) {
		if (StringUtils.isNotBlank(id)) {
			WhiteList whiteList=whiteListService.getView(id);
			String sessionid = request.getSession().getId();
			model.addAttribute("whiteList", whiteList);
			model.addAttribute("sessionid", sessionid);
		}
		return "modules/config/whitelist/editor";
	}


	/**author:wyf
	 * description:
	 *          校验保存的时候数据唯一
	 * @param userInfoId
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "decide")
	@ResponseBody
	public Boolean decide(String userInfoId,String projectInfoId,String optType,String id,String remarks,String certSn,String certSubject,
			Model model,WhiteList whiteList,
			HttpServletRequest request,HttpServletResponse response) {
			if (StringUtils.isNotBlank(id)) {
			  WhiteList whiteList1=whiteListService.get(id);
			  if("0".equals(optType)){
				  if(whiteList1 !=null){
					   if(projectInfoId.equals(whiteList1.getProjectInfoId()) &&
							   userInfoId.equals(whiteList1.getUserInfoId())){
						   return true;
					   }else{
						   WhiteList wList = new WhiteList();
						   wList.setProjectInfoId(projectInfoId);
						   wList.setUserInfoId(userInfoId);
						   List<WhiteList> wl=whiteListService.findList(wList);

						   List<CertInfo> certInfos = whiteListService.getStatus(projectInfoId, userInfoId);

						   if(wl!=null && wl.size()>0){
							   return false;
						   }else{
							   if(certInfos!=null && certInfos.size()>0) {
								   return false;
							   }else {
								   return true;
							   }
						   }

					}

				   }
			  }else if("1".equals(optType)){
				  if(whiteList1 !=null){
					   if(certSn.equals(whiteList1.getCertSn())){
						   return true;
					   }else{
						   WhiteList wList = new WhiteList();
						   wList.setCertSn(certSn);
						   wList.setIsUsed("0");
						   List<WhiteList> wl=whiteListService.findList(wList);
						   if(wl!=null && wl.size()>0){
							   return false;
						   }else{
							   return true;
						   }

					}

				   }

			  }

	       }
			return true;
	}
	/**author:Yophy.w
	 * descripiton:最外层显示的表单list
	 * @param entity
	 * @param clickType
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String whiteListList(WhiteList entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		//通过clickType  来对查询实体entity 进行赋值  clickType = 1 代表界面查询操作，进行赋值
		if(StringUtils.isNotBlank(clickType) && ("1").equals(clickType)){
			WhiteListVo whiteListVo = new WhiteListVo();
			whiteListVo.setProjectName(entity.getProjectName());
			whiteListVo.setUserInfoName(entity.getUserInfoName());
			whiteListVo.setUserPhoneNum(entity.getUserPhoneNum());
			whiteListVo.setUserInfoId(entity.getUserInfoId());
			whiteListVo.setCertSn(entity.getCertSn());
			whiteListVo.setIsUsed(entity.getIsUsed());
			whiteListVo.setOptType(entity.getOptType());
			whiteListVo.setSearchCertSubject(entity.getSearchCertSubject());
			request.getSession().setAttribute("whiteListVo", whiteListVo);
			model.addAttribute("whiteListVo", whiteListVo);
		}else if(StringUtils.isNotBlank(clickType) && ("2").equals(clickType)){
			WhiteListVo whiteListVo = (WhiteListVo) request.getSession().getAttribute("whiteListVo");
			entity.setProjectName(whiteListVo.getProjectName());
			entity.setUserInfoName(whiteListVo.getUserInfoName());
			entity.setUserPhoneNum(whiteListVo.getUserPhoneNum());
			entity.setUserInfoId(whiteListVo.getUserInfoId());
			entity.setCertSn(whiteListVo.getCertSn());
			entity.setIsUsed(whiteListVo.getIsUsed());
			entity.setOptType(whiteListVo.getOptType());
			entity.setSearchCertSubject(whiteListVo.getSearchCertSubject());
			model.addAttribute("whiteListVo", whiteListVo);
		}else{
			request.getSession().setAttribute("whiteListVo", new WhiteListVo());
		}
		Page<WhiteList> page = whiteListService.findPage(
				new Page<WhiteList>(request, response), entity);
		model.addAttribute("page", page);
		return "modules/config/whitelist/list";

	}

	/**author:Yophy.w
	 * descripiton:公用的保存方法
	 * @param userInfoId
	 * @param entity
	 * @param model
	 * @param type
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String whiteListServiceSave(String userInfoId, String certSn, WhiteList entity, Model model, String type,
									   HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		//难道先要每次首先处理人脸？
		ProjectInfo PI = projectInfoService.get(entity.getProjectInfoId());
		if (StringUtils.isEmpty(type) && PI != null) {
			entity.setIsuseFace(PI.getIsuseFace());
		}
		/*if (whiteListService.findbyprojectInfoId(entity)) {*/ //判断是不是已经有了
			if (StringUtils.isNotBlank(type)) {
				PI.setIsuseFace(entity.getIsuseFace());
				projectInfoService.save(PI);
				String opttype = whiteListService.getView(entity.getId()).getOptType();
				entity.setOptType(opttype);
				whiteListService.save(entity);
			} else if (userInfoId != null && !"".equals(userInfoId)) {
				WhiteList whiteList = null;
				String[] userInfoIds = userInfoId.split(",");
				System.out.println(userInfoIds);
				for (String ids : userInfoIds) {
					if (StringUtils.isNotBlank(ids) && ids != null) {
						UserInfo userInfo = userInfoService.get(ids);
//							String certSubject = mobileService.getSubject(entity.getProjectInfoId(),ids,ApplyConstants.USERTYPE_USERINFO);
						//根据项目信息和个人用户信息生成新制证书的证书主题
						String certSubject = GetCertSubjectUtil.getSubject(PI, userInfo, ApplyConstants.USERTYPE_USERINFO,"");
						whiteList = new WhiteList();
						whiteList.setOptType(entity.getOptType());
						whiteList.setProjectInfoId(entity.getProjectInfoId());
						whiteList.setIsUsed(entity.getIsUsed());
						whiteList.setCertSn(entity.getCertSn());
						whiteList.setUserInfoId(ids);
						whiteList.setIsuseFace(entity.getIsuseFace());
						whiteList.setCertSubject(certSubject);
						whiteListService.save(whiteList);
					}
				}
			} else if (certSn != null && !"".equals(certSn)) {
				WhiteList whiteList = null;
				String[] certSns = certSn.split(",");
				System.out.println(certSns);
				for (String certs : certSns) {
					//这里保存的时候意思是要先处理一下userInfoId？ 证书表和用户表进行关联，关联后拿到用户表id
//						CertInfo certInfo=certInfoService.get(certs);
					UserInfo userInfo = userInfoService.findUserByCertSn(certs);
					List<CertapplyInfo> certapplyInfoList = certapplyInfoService.findByCertSn(certs);
					if (certapplyInfoList != null && certapplyInfoList.size() > 0) {
						CertapplyInfo certapplyInfo = certapplyInfoList.get(0);

						if (userInfo != null && !"".equals(userInfo) && StringUtils.isNotBlank(certs) && certs != null && certapplyInfo != null && !"".equals(certapplyInfo)) {
							whiteList = new WhiteList();
							//根据项目信息和个人用户信息生成证书变更申请生成的证书主题
							String certSubject = GetCertSubjectUtil.getSubject(PI, userInfo, ApplyConstants.USERTYPE_USERINFO,"");
							whiteList.setCertSubject(certSubject);
							whiteList.setOptType(entity.getOptType());
							whiteList.setProjectInfoId(entity.getProjectInfoId());
							whiteList.setIsUsed(entity.getIsUsed());
							whiteList.setUserInfoId(userInfo.getId());
							whiteList.setCertSn(certs);
							whiteList.setIsuseFace(entity.getIsuseFace());
							whiteListService.save(whiteList);
						} else {
							addMessage(redirectAttributes, "数据异常保存失败");
							return "redirect:" + adminPath + "/white/list/?repage";
						}
					}
				}

			}
			addMessage(redirectAttributes, "保存成功");
	/*	} else {

			addMessage(redirectAttributes, "保存失败,提交信息已存在!");
		}*/
		return "redirect:" + adminPath + "/white/list/?repage";
	}



	@RequestMapping(value = "delete")
	public String whiteListServiceDelete(WhiteList entity,
			RedirectAttributes redirectAttributes) {
		whiteListService.delete(entity);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/white/list/?repage";
	}


	/**author:Yophy.w
	 * descripiton:用户弹出框的显示方法
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "formUser")
	private String index(WhiteListselectVo userselectVo,HttpServletRequest request,HttpServletResponse response,Model model) {

		String ids = "";

		String checkIds = whiteListService.findCheckIds(userselectVo.getProjectId());
		//获取正在使用的用户的ids
		String checkCertIds =  whiteListService.getCorpuseridsByProid(userselectVo.getProjectId());


		List<String> result = null;
		if (StringUtils.isNotBlank(checkIds)) {
			ids += checkIds;
		}
		if(StringUtils.isNotBlank(checkCertIds)){
			ids += checkCertIds;
		}
		if(StringUtils.isNotBlank(ids)){
			result = Arrays.asList(ids.split(","));
		}

		UserInfo userInfo = new UserInfo();
		userInfo.setStrList(result);
		Page<UserInfo> page = new Page<UserInfo>(request,
				response);
		userInfo.setPage(page);
		if(userselectVo.getUsername()!=null && !"".equals(userselectVo.getUsername())){
			userInfo.setUsername(userselectVo.getUsername());
			/*page.setList(userInfoService.findProUserlist(userInfo));*/
			page.setList(userInfoService.findList(userInfo));
		}else{
			//-----------------------------------------------
			page.setList(userInfoService.findList(userInfo));
		}

		model.addAttribute("page", page);
		model.addAttribute("userInfoId", userselectVo.getUserInfoId());//回选时候需要的参数
		model.addAttribute("userselectVo", userselectVo);

		return "modules/config/whitelist/userselect";
	}



	/**author:Yophy.w
	 * descripiton:证书弹出框的显示方法
	 * @param entity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "formCert")
	private String index(WhiteListselectVo certselectVo,CertInfo entity,HttpServletRequest request,String certSn,
			HttpServletResponse response,Model model) {
		List<String> result = null;
		String checkCerts = whiteListService.findCerts();
		if (checkCerts != null &&  !"".equals(checkCerts)) {
			result = Arrays.asList(checkCerts.split(","));
		}
		CertInfo certInfo = new CertInfo();
		certInfo.setCertList(result);
		certInfo.setCertStatus("Use");
		certInfo.setCertType("userinfo");

		Page<CertInfo> page = new Page<CertInfo>(request,response);
		certInfo.setPage(page);
		if(certselectVo.getCertSubject()!=null && !"".equals(certselectVo.getCertSubject())
				|| certselectVo.getCommonName()!=null && !"".equals(certselectVo.getCommonName()) ){
			certInfo.setCertSubject(certselectVo.getCertSubject());
			certInfo.setCommonName(certselectVo.getCommonName());
			page.setList(certInfoService.findbyList(certInfo));

		}else{
			page.setList(certInfoService.findbyList(certInfo));//过滤是这一步
		}

		model.addAttribute("page", page);
		model.addAttribute("certSn", certSn);
		model.addAttribute("certselectVo", certselectVo);
		return "modules/config/whitelist/certselect";
	}


	/**author:Yophy.w
	 * descripiton:在form父页面下动态显示选择用户的表单查询方法
	 *             这个写法和下面写法的区别是，将累赘的后台代码一sql的方式实现从而优化了性能。
	 * @param request
	 * @param response
	 * @param model
	 * @param userInfoId
	 * @return
	 */
	@RequestMapping(value = "listUser")
	public String listUser(HttpServletRequest request, HttpServletResponse response,
			Model model,String userInfoId,UserInfo userInfo) {
		//第一种包装方法可以实现，最冗长 但是提供了一种普通和标准写法
		/*String[] userInfoIds = null;
		userInfoIds = userInfoId.split(",");
		List<String> userIds = new ArrayList<String>();
		for(String id:userInfoIds) {
			userIds.add(id);
		}
		List<UserInfo> userInfos = userInfoService.findUserids(userIds);*/
		/*--------------------------------------*/
		//第二种方法非常简洁，但是这个Arrays.asList 方法不是太好 而且传递的时候没有用到实体
		List<String> result = null;
		result = Arrays.asList(userInfoId.split(","));
		List<UserInfo> userInfos = userInfoService.findUserids(result);
		//传递参数时候采用实体包装一下
		/*List<String> result = null;
		result = Arrays.asList(userInfoId.split(","));
		userInfo.setStrList(result);
		List<UserInfo> userInfos = userInfoService.findUserids(userInfo);*/
		model.addAttribute("userInfos", userInfos);
		model.addAttribute("userInfoId",userInfoId);
		return "modules/config/whitelist/listUser";

	}




	/**author:Yophy.W
	 * descripiton:在form父页面下动态显示选择用户的表单查询方法
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "listCert")
	public String listCert(HttpServletRequest request, HttpServletResponse response,Model model,String certSn) {
		String[] certInfoIds = null;
		List<CertInfo> certInfos = new ArrayList<CertInfo>();
		if(certSn!=null&&!"".equals(certSn)) {
			certInfoIds = certSn.split(",");
			CertInfo certInfo = null;
			CertInfo certInfo1 = new CertInfo();
			for(String id:certInfoIds) {
				certInfo1.setCertSn(id);
				certInfo = certInfoService.findbyList(certInfo1).get(0);
				certInfos.add(certInfo);
			}
		}
		model.addAttribute("certInfos", certInfos);
		model.addAttribute("certSn",certSn);
		return "modules/config/whitelist/listCert";

	}

	/**author:Yophy.W
	 * description:使用ajax 方法  每次在子页面选择后，通过循环遍历提交到父页面在保存之前都对他进行判断是否已有
	 *             保证一个用户 一个项目 一条记录，和save方法开始的那段校验一样，都是后台对数据唯一性的校验。
	 * @param userInfoId
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "Judgement")
	@ResponseBody
	public String showUser(String userInfoId,String projectInfoId,Model model,String certSn,
			HttpServletRequest request,HttpServletResponse response) {

		String isOK = null;
		try {
			if(userInfoId!=null && !"".equals(userInfoId)){
				isOK="OK";
			}else if(StringUtils.isNotBlank(certSn)){
				String[] certsn=certSn.split(",");
				for(String cert : certsn){
					if(StringUtils.isNotBlank(cert)){
						CertapplyInfo certapplyInfo = new CertapplyInfo();
						certapplyInfo.setCertSn(cert);
						List<CertapplyInfo> certapplyInfolist=certapplyInfoService.findbyList(certapplyInfo);
						if(certapplyInfolist!=null && certapplyInfolist.size()>0){
							String Pid=certapplyInfolist.get(0).getProjectId();
							if(Pid!=null && Pid!="" && !Pid.equals(projectInfoId)){
								isOK = "fail";
								return isOK;
							}
						}
					}
					isOK="OK";
				}

			}
//			--------------------------
		} catch (Exception e) {
			isOK = "fail";
			e.getStackTrace();
		}
		return isOK;
	}


}



