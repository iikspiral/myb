package com.sxca.myb.modules.config.corporcode.web;

import java.util.Arrays;
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
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.cert.service.CertInfoService;
import com.sxca.myb.modules.cert.service.CertapplyInfoService;
import com.sxca.myb.modules.config.corporcode.entity.CorporationRequestCode;
import com.sxca.myb.modules.config.corporcode.service.CorporcodeService;
import com.sxca.myb.modules.config.corporcode.util.CorporcodeVo;
import com.sxca.myb.modules.config.corporcode.vo.CorporationRequestCodeVo;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserRelation;
import com.sxca.myb.modules.idinfo.service.CorporationInfoService;
import com.sxca.myb.modules.idinfo.service.CorporationUserRelationnService;
import com.sxca.myb.modules.mobile.service.MobileService;
import com.sxca.myb.modules.pro.entity.ProjectInfo;
import com.sxca.myb.modules.pro.service.ProjectInfoService;

/**
 * @author lihuilong
 * @date : 2017年4月14日 上午9:44:44
 */
@Controller
@RequestMapping(value = "${adminPath}/corporcode")
public class CorporcodeController extends BaseController {

	@Autowired
	private CorporcodeService corporcodeService;

	@Autowired
	private CorporationInfoService corporationInfoService;

	@Autowired
	private CertInfoService certInfoService;

	@Autowired
	private CertapplyInfoService certapplyInfoService;

	@Autowired
	private CorporationUserRelationnService corporationUserRelationnService;

	@Autowired
	private ProjectInfoService projectInfoService;
	
	@Autowired
	private MobileService mobileService;

	@RequestMapping(value = "list")
	public String corporCodeList(CorporationRequestCode entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		//通过clickType  来对查询实体entity 进行赋值  clickType = 1 代表界面查询操作，进行赋值
		if(StringUtils.isNotBlank(clickType) && ("1").equals(clickType)){
			CorporationRequestCodeVo corporationRequestCodeVo = new CorporationRequestCodeVo();
			corporationRequestCodeVo.setProjectId(entity.getProjectId());
			corporationRequestCodeVo.setCorporationId(entity.getCorporationId());
			corporationRequestCodeVo.setCorporUserId(entity.getCorporUserId());
			corporationRequestCodeVo.setCertSubject(entity.getCertSubject());
			corporationRequestCodeVo.setType(entity.getType());
			corporationRequestCodeVo.setStatus(entity.getStatus());
			
			request.getSession().setAttribute("corporationRequestCodeVo", corporationRequestCodeVo);
			
			model.addAttribute("corporationRequestCodeVo", corporationRequestCodeVo);
		}else if(StringUtils.isNotBlank(clickType) && ("2").equals(clickType)){
			CorporationRequestCodeVo corporationRequestCodeVo = (CorporationRequestCodeVo) request.getSession().getAttribute("corporationRequestCodeVo");
			entity.setProjectId(corporationRequestCodeVo.getProjectId());
			entity.setCorporationId(corporationRequestCodeVo.getCorporationId());
			entity.setCorporUserId(corporationRequestCodeVo.getCorporUserId());
			entity.setCertSubject(corporationRequestCodeVo.getCertSubject());
			entity.setType(corporationRequestCodeVo.getType());
			entity.setStatus(corporationRequestCodeVo.getStatus());
			
			model.addAttribute("corporationRequestCodeVo", corporationRequestCodeVo);
		}else{
			request.getSession().setAttribute("corporationRequestCodeVo", new CorporationRequestCodeVo());
		}
		
		Page<CorporationRequestCode> page = corporcodeService.findPage(
				new Page<CorporationRequestCode>(request, response), entity);
		model.addAttribute("page", page);

		return "modules/config/corporcode/corporcodeList";
	}

	@RequestMapping(value = "form") 
	private String index(CorporationRequestCode entity,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		entity = new CorporationRequestCode();

		String sessionid = request.getSession().getId();
		model.addAttribute("sessionid", sessionid);
		model.addAttribute("corporationRequestCode", entity);

		return "modules/config/corporcode/form";
	}

	@RequestMapping(value = "edit")
	private String edit(CorporationRequestCode entity,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		entity = corporcodeService.get(entity.getId());

		String sessionid = request.getSession().getId();
		model.addAttribute("sessionid", sessionid);
		model.addAttribute("corporationRequestCode", entity);

		return "modules/config/corporcode/edit";
	}

	@RequestMapping(value = "corList")
	private String corList(CorporcodeVo corporcodeVo,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String ids="";
		//证书新制，个人白名单 选则用户时，需要对未添加过的企业用户过滤，如该企业用户已存在证书，证书状态为正在使用，也需要对该类型用户进行过滤
		//获取到企业与企业用户中间表id，企业白名单中已添加，取到现在生效的白名单
		String checkIds = corporcodeService.fingCheckIds(corporcodeVo
				.getProjectId());
		//去查询一下 当前项目下，正在使用的证书
		
		String resulIds = corporcodeService.getCorpuseridsByProid(corporcodeVo
				.getProjectId());
		if(StringUtils.isNotBlank(checkIds)){
			ids += checkIds;
		}
		if(StringUtils.isNotBlank(resulIds)){
			ids += resulIds;
		}
		
		List<String> result = null;
		if (StringUtils.isNotBlank(ids)) {
			result = Arrays.asList(ids.split(","));
		}

		CorporationInfo corporationInfo = new CorporationInfo();
		if(StringUtils.isNotBlank(corporcodeVo.getCorpname())){
			corporationInfo.setCorpname(corporcodeVo.getCorpname());
		}

		corporationInfo.setStrList(result);

		Page<CorporationInfo> page = new Page<CorporationInfo>(request,
				response);
		corporationInfo.setPage(page);
		page.setList(corporationInfoService.findProUserlist(corporationInfo));

		model.addAttribute("page", page);
		model.addAttribute("corporcodeVo", corporcodeVo);

		return "modules/config/corporcode/corList";
	}

	@RequestMapping(value = "certList")
	private String certList(CorporcodeVo corporcodeVo,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<String> result = null;
		String checkCerts = corporcodeService.fingCheckCerts();
		if (checkCerts != null) {
			result = Arrays.asList(checkCerts.split(","));
		}

		CertInfo certInfo = new CertInfo();

		certInfo.setCertList(result);
		certInfo.setCertStatus("Use");
		certInfo.setCertType("corporation_info");
		
		CertapplyInfo certapplyInfo = new CertapplyInfo();

		certInfo.setCertapplyInfo(certapplyInfo);
		
		if(StringUtils.isNotBlank(corporcodeVo.getCertSubject())){
			certInfo.setCertSubject(corporcodeVo.getCertSubject());
		}

		Page<CertInfo> page = certInfoService.findPage(new Page<CertInfo>(
				request, response), certInfo);

		model.addAttribute("page", page);
		model.addAttribute("corporcodeVo", corporcodeVo);

		return "modules/config/corporcode/certList";
	}

	@RequestMapping(value = "save")
	public String corporCodeSave(CorporationRequestCode entity, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (corporcodeService.saveCorpor(entity)) {
			addMessage(redirectAttributes, "保存成功");
		} else {
			addMessage(redirectAttributes, "保存失败");
		}
		return "redirect:" + adminPath + "/corporcode/list/?repage";

	}

	@RequestMapping(value = "updateSave")
	public String updateSave(CorporationRequestCode entity, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if ("1".equals(entity.getType())) {
			// 判断是否为变更，如果为变更，需从申请信息表中查询相关用户id 插入到表中
			CertapplyInfo certapplyInfo = new CertapplyInfo();
			certapplyInfo.setCertSn(entity.getCertSn());
			List<CertapplyInfo> certApplyList = certapplyInfoService
					.findList(certapplyInfo);
			if (certApplyList != null && certApplyList.size() > 0) {
				entity.setCorporationId(certApplyList.get(0).getUserInfoId());
				entity.setCorporUserId(certApplyList.get(0).getCorpUserId());
				// 判断项目是否为空，如果为空，去证书申请表中通过证书序列号查询项目id
				if (StringUtils.isBlank(entity.getProjectId())) {
					entity.setProjectId(certApplyList.get(0).getProjectId());
				} else {
					entity.setProjectId(entity.getProjectId());
				}
			}
		} else {
			if (StringUtils.isNotBlank(entity.getCorporUserRelaId())) {
				// 企业与企业用户中间表
				CorporationUserRelation corporationUserRelation = corporationUserRelationnService
						.get(entity.getCorporUserRelaId());
				entity.setCorporationId(corporationUserRelation
						.getCorporationId());
				entity.setCorporUserId(corporationUserRelation
						.getCorporationUserId());
			}
		}
		corporcodeService.update(entity);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/corporcode/list/?repage";

	}

	@RequestMapping(value = "delete")
	public String corporCodeDelete(CorporationRequestCode entity,
			RedirectAttributes redirectAttributes) {
		corporcodeService.delete(entity);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/corporcode/list/?repage";
	}

	@RequestMapping(value = "getCor")
	@ResponseBody
	public CorporationInfo getCor(String corId, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		CorporationInfo corporationInfo = corporationInfoService.get(corId);

		return corporationInfo;
	}

	@RequestMapping(value = "getCert")
	@ResponseBody
	public CertInfo getCert(String certSn, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		CertInfo certInfo = certInfoService.get(certSn);

		return certInfo;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param corporationId
	 *            前台选中ids
	 * @return
	 */
	@RequestMapping(value = "corporlist")
	public String certlist(HttpServletRequest request,
			HttpServletResponse response, Model model, String corporationId) {

		if (StringUtils.isNoneBlank(corporationId)) {
			List<String> result = null;
			result = Arrays.asList(corporationId.split(","));
			List<CorporationInfo> corporList = corporationInfoService
					.fingByids(result);
			model.addAttribute("corporList", corporList);
		} else {
			model.addAttribute("corporList", null);
		}

		return "modules/config/corporcode/corselectList";

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param corporationId
	 *            前台选中ids
	 * @return
	 */
	@RequestMapping(value = "certSelectlist")
	public String certSelectlist(HttpServletRequest request,
			HttpServletResponse response, Model model, String certSn) {

		if (StringUtils.isNoneBlank(certSn)) {
			List<String> result = null;
			result = Arrays.asList(certSn.split(","));
			List<CertInfo> certList = certInfoService.fingByids(result);
			model.addAttribute("certList", certList);
		} else {
			model.addAttribute("certList", null);
		}

		return "modules/config/corporcode/certselectList";

	}

	@RequestMapping(value = "checkUniqueCorpuser")
	@ResponseBody
	public Boolean checkUniqueCorpuser(String id, String corporUserRelaId,
			String projectId, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		CorporationRequestCode corporationRequestCode = corporcodeService
				.get(id);
		if (corporationRequestCode != null) {
			if (corporUserRelaId.equals(corporationRequestCode.getCorporUserRelaId())
					&& projectId.equals(corporationRequestCode.getProjectId())) {
				return true;
			} else {
				CorporationRequestCode entity = new CorporationRequestCode();
				entity.setCorporUserRelaId(corporUserRelaId);
				entity.setProjectId(projectId);
				entity.setStatus("0");
				List<CorporationRequestCode> list = corporcodeService.findList(entity);
				
				List<CertInfo> certInfos = null;
				CorporationUserRelation corporationUserRelation = corporationUserRelationnService.get(corporUserRelaId);
				if(corporationUserRelation != null) {
					certInfos = corporcodeService.getStatus(projectId, corporationUserRelation.getCorporationUserId());
				}
				
				if (list != null && list.size() > 0) {
					return false;
				} else {
					if(certInfos!=null && certInfos.size()>0) {
						return false;
					}else {
						return true;
					}
				}
			}
		} else {
			return true;
		}

	}

	@RequestMapping(value = "checkUniqueCert")
	@ResponseBody
	public Boolean checkUniqueCert(String id, String certSn, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		CorporationRequestCode corporationRequestCode = corporcodeService
				.get(id);
		if (corporationRequestCode != null) {
			if (certSn.equals(corporationRequestCode.getCertSn())) {
				return true;
			} else {
				CorporationRequestCode entity = new CorporationRequestCode();
				entity.setStatus("0");
				entity.setCertSn(certSn);
				List<CorporationRequestCode> list = corporcodeService
						.findList(entity);
				if (list != null && list.size() > 0) {
					return false;
				} else {
					return true;
				}
			}
		} else {
			return true;
		}

	}
	
	/**author:wyf
	 * description:页面提示信息校验。
	 * @param userInfoId
	 * @param entity
	 * @param model
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "Judgement")
	@ResponseBody
	public String showUser(String userInfoId,String projectInfoId,Model model ,String certSn,
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
							if(Pid!=null && !"".equals(Pid) &&
									projectInfoId!=null && !"".equals(projectInfoId) && 
									!Pid.equals(projectInfoId)  ){
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
