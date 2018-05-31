package com.sxca.myb.modules.cert.web;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.utils.DateUtil;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.common.web.BaseController;
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.cert.service.CertInfoService;
import com.sxca.myb.modules.cert.service.CertapplyInfoService;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.idinfo.service.CorporationInfoService;
import com.sxca.myb.modules.idinfo.service.CorporationUserInfoService;
import com.sxca.myb.modules.idinfo.service.UserInfoService;
import com.sxca.myb.modules.pro.entity.ProjectInfo;
import com.sxca.myb.modules.pro.service.ProjectInfoService;


/**  
* <p>Title: CertInfoController</p>  
* <p>Description: </p>  
* <p>Company: 吉大正元</p>   
* @author wyf 
* @date 2017年5月23日上午10:47:28  
* @version V1.0 
*/ 
@Controller
@RequestMapping(value = "${adminPath}/certInfo")
public class CertInfoController extends BaseController {

	@Autowired
	private CertInfoService certInfoService;
	@Autowired
	private CertapplyInfoService certapplyInfoService;
	@Autowired
	private ProjectInfoService projectInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CorporationInfoService corporationInfoService;
	@Autowired
	private CorporationUserInfoService corporationUserInfoService;

	@ModelAttribute
	public CertInfo get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return certInfoService.getbyid(id);
		} else {
			return new CertInfo();
		}
	}


	/** sql中可以to_date ,to_char 或是formate 处理一下 但前两个是oracle 后面这个是mysql
	 * 还没有找到好的兼容 方式 ，所以后台处理代码这么长。等找到好的方法就改。
	 * @param entity
	 * @param clickType
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String certInfoList(CertInfo entity,String clickType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if(entity.getBeforeMin()!=null && !"".equals(entity.getBeforeMin()) &&entity.getBeforeMax()!=null && !"".equals(entity.getBeforeMax())){
			String aa=DateUtil.date2String(entity.getBeforeMin(), "yyyyMMddHHmmss")+"000";
			BigDecimal bd=new BigDecimal(aa);
			entity.setBeforeMin1(bd);
//			--------------------------
			String aa1=DateUtil.date2String(entity.getBeforeMax(), "yyyyMMddHHmmss")+"000";
			BigDecimal bd1=new BigDecimal(aa1);
			entity.setBeforeMax1(bd1);
		}else if(entity.getBeforeMin()!=null && !"".equals(entity.getBeforeMin())){
			String aa=DateUtil.date2String(entity.getBeforeMin(), "yyyyMMddHHmmss")+"000";
			BigDecimal bd=new BigDecimal(aa);
			entity.setBeforeMin1(bd);
		}else if(entity.getBeforeMax()!=null && !"".equals(entity.getBeforeMax())){
			String aa=DateUtil.date2String(entity.getBeforeMax(), "yyyyMMddHHmmss")+"000";
			BigDecimal bd=new BigDecimal(aa);
			entity.setBeforeMax1(bd);
		}
		if(entity.getAfterMin()!=null && !"".equals(entity.getAfterMin()) && entity.getAfterMax()!=null && !"".equals(entity.getAfterMax())){
			String aaa=DateUtil.date2String(entity.getAfterMin(), "yyyyMMddHHmmss")+"000";
			BigDecimal bdb=new BigDecimal(aaa);
			entity.setAfterMin1(bdb);	
//			-----------------------------
			String aaaa=DateUtil.date2String(entity.getAfterMax(), "yyyyMMddHHmmss")+"000";
			BigDecimal bdbd=new BigDecimal(aaaa);
			entity.setAfterMax1(bdbd);
		}else if(entity.getAfterMin()!=null && !"".equals(entity.getAfterMin())){
			String aa1=DateUtil.date2String(entity.getAfterMin(), "yyyyMMddHHmmss")+"000";
			BigDecimal bd1=new BigDecimal(aa1);
			entity.setAfterMin1(bd1);
		}else if(entity.getAfterMax()!=null && !"".equals(entity.getAfterMax())){
			String aa2=DateUtil.date2String(entity.getAfterMax(), "yyyyMMddHHmmss")+"000";
			BigDecimal bd2=new BigDecimal(aa2);
			entity.setAfterMax1(bd2);
		}
//		-------------------------------------------------
		Page<CertInfo> page = new Page<CertInfo>(request,
                response);
		entity.setPage(page);
		page.setList(certInfoService.findbyList(entity));
//		
		model.addAttribute("page", page);
		model.addAttribute("certInfo", entity);
		return "modules/certInfo/list";

	}
	
	/**description: 证书查询方法
	 * @param certInfo
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(CertInfo certInfo, Model model,String id) {
		if(id!=null && !"".equals(id)){
			//通过证书申请表 拿到项目ID，查出项目实体
			List<CertapplyInfo> applyList = certapplyInfoService.findByCertSn(certInfo.getCertSn());
			if(applyList!=null && applyList.size()>0){
				ProjectInfo  project=projectInfoService.get(applyList.get(0).getProjectId());
				model.addAttribute("project",project);
			}
			//区分个人用户和企业用户
			UserInfo userInfo = new UserInfo();
			CorporationInfo corporationInfo = new CorporationInfo();
			//通过证书类型 区分 个人和企业 0是个人 
			if(certInfo.getUserinfoId()!=null && !"".equals(certInfo.getUserinfoId()) &&certInfo.getCertType().equals(ApplyConstants.USERTYPE_USERINFO)){
				userInfo.setRaUserId(certInfo.getUserinfoId()); //此时的Rauserid 是个人用户ID
				List<UserInfo> userinfoList =userInfoService.findList(userInfo);
				if(userinfoList!=null && userinfoList.size()>0){
					UserInfo user =userinfoList.get(0);
					model.addAttribute("userList", userinfoList);
					model.addAttribute("user", user);
				}
			}
			//1 是企业
			if(certInfo.getUserinfoId()!=null && !"".equals(certInfo.getUserinfoId()) &&certInfo.getCertType().equals(ApplyConstants.USERTYPE_CORPORATION_INFO)){
				corporationInfo.setRaUserId(certInfo.getUserinfoId());//此时的Rauserid 是企业ID
				List<CorporationInfo> corinfoList =corporationInfoService.findList(corporationInfo);
				if(corinfoList!=null &&corinfoList.size()>0){
					CorporationInfo corpo = corinfoList.get(0);
					CertapplyInfo  certapplyInfo = new CertapplyInfo();
					certapplyInfo.setUserInfoId(corpo.getId());
					//查询证书申请表 拿到企业用户信息
					List<CertapplyInfo> corpoUserList=certapplyInfoService.findList(certapplyInfo);
					if(corpoUserList!=null && corpoUserList.size()>0 ){
						String corpoUser=corpoUserList.get(0).getCorpUserId();
						CorporationUserInfo corUser=corporationUserInfoService.get(corpoUser);
						model.addAttribute("corpoUserList",corpoUserList);
						model.addAttribute("corUser", corUser);
					}
					//----------------------------------------------------------------------
					model.addAttribute("corpoList", corinfoList);
					model.addAttribute("corpo", corpo);
				}
			}
		}
		
		return "modules/certInfo/form";
	}

}
