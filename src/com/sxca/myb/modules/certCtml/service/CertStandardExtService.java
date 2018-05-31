package com.sxca.myb.modules.certCtml.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.certCtml.dao.CertCtmlStandardExtDao;
import com.sxca.myb.modules.certCtml.dao.CertStandardExtDao;
import com.sxca.myb.modules.certCtml.entity.CertCtmlStandardExt;
import com.sxca.myb.modules.certCtml.entity.CertStandardExt;
import com.sxca.myb.modules.idinfo.dao.CorporationInfoDao;
import com.sxca.myb.modules.idinfo.dao.UserInfoDao;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.mobile.util.GetCertSubjectUtil;
import com.sxca.myb.modules.pro.dao.ProjectInfoDao;
import com.sxca.myb.modules.pro.entity.ProjectInfo;

/**
 * @author lihuilong 
 * @date : 2017年5月5日 下午2:33:46
 */
@Service
@Transactional(readOnly = true)
public class CertStandardExtService extends CrudService<CertStandardExtDao,CertStandardExt> {
	
	
	@Autowired
	private ProjectInfoDao projectInfoDao;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private CertCtmlStandardExtDao certCtmlStandardExtDao;
	
	@Autowired
	private CorporationInfoDao corporationInfoDao;
	
	/**
	 * 获取标准域扩展域信息集合
	 * lihuilong
	 * @param proId
	 * @param userId
	 * @param userType
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<CertStandardExt> getCertStandtextList(String proId,String userId,String userType){
		
		ProjectInfo projectInfo = projectInfoDao.get(proId);
		
		List<CertStandardExt> certStandardExt = new ArrayList<CertStandardExt>();
		if(projectInfo != null){
			//证书自定义扩展域规则
			String selfExtRules = projectInfo.getStandardExtRules();
			if(StringUtils.isNotBlank(selfExtRules)){
				if("userinfo".equals(userType)){
					UserInfo userInfo = userInfoDao.get(userId);
					if(userInfo != null){
						if (selfExtRules.indexOf("个人用户") != -1) {
							String corporSub = selfExtRules
									.substring(selfExtRules.indexOf("个人用户") + 5);
							corporSub = corporSub.substring(0,
									corporSub.indexOf(";"));
							try {
								String[] strarry = corporSub.split(",");
								for (int i = 0; i < strarry.length; i++) {
									String str = strarry[i];
									if (str.indexOf("$") != -1) {
										String[] subarr = str.split("}");
										for (int j = 0; j < subarr.length; j++) {
											String subStr = subarr[j];
											if (subStr.indexOf("$") != -1) {
												String sub = subStr.substring(0,
														subStr.indexOf("="));
												String name = subStr
														.substring(subStr.indexOf("{") + 1);
												//去自定义扩展域模板中，去看是否存在该条扩展信息
												CertCtmlStandardExt certCtmlStandardExt = new CertCtmlStandardExt();
												certCtmlStandardExt.setCertCtmlId(projectInfo.getCertTemplate());
												certCtmlStandardExt.setExtChildName(sub);
												List<CertCtmlStandardExt> ctmlList = certCtmlStandardExtDao.findList(certCtmlStandardExt);
												if(ctmlList != null && ctmlList.size() > 0){
													CertStandardExt entity = new CertStandardExt();
													entity.setExtName(sub);
													entity.setExtValue(GetCertSubjectUtil.getSxname(userInfo, name));
													certStandardExt.add(entity);
												}
											} 
										}
									}
								}
								return certStandardExt;
							} catch (Exception e) {
								e.printStackTrace();
							}
							return null;
						}
					}else{
						return null;
					}
				} else if ("corporation_info".equals(userType)) {
					try {
						CorporationInfo corporationInfo = corporationInfoDao
								.get(userId);
						if (corporationInfo != null) {
							try {
								if (selfExtRules.indexOf("企业用户") != -1) {
									String corporSub = selfExtRules
											.substring(selfExtRules
													.indexOf("企业用户") + 5);
									corporSub = corporSub.substring(0,
											corporSub.indexOf(";"));
									
									try {
										String[] strarry = corporSub.split(",");
										for (int i = 0; i < strarry.length; i++) {
											String str = strarry[i];
											if (str.indexOf("$") != -1) {
												String[] subarr = str.split("}");
												for (int j = 0; j < subarr.length; j++) {
													String subStr = subarr[j];
													if (subStr.indexOf("$") != -1) {
														String sub = subStr.substring(0,
																subStr.indexOf("="));
														String name = subStr
																.substring(subStr.indexOf("{") + 1);
														//去自定义扩展域模板中，去看是否存在该条扩展信息
														CertCtmlStandardExt certCtmlStandardExt = new CertCtmlStandardExt();
														certCtmlStandardExt.setCertCtmlId(projectInfo.getCertTemplate());
														certCtmlStandardExt.setExtChildName(sub);
														List<CertCtmlStandardExt> ctmlList = certCtmlStandardExtDao.findList(certCtmlStandardExt);
														if(ctmlList != null && ctmlList.size() > 0){
															CertStandardExt entity = new CertStandardExt();
															entity.setExtName(sub);
															entity.setExtValue(GetCertSubjectUtil.getSxname(corporationInfo, name));
															certStandardExt.add(entity);
														}
													} 
												}
											}
										}
										return certStandardExt;
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				return null;
			}
			
		}
		
		
		return null;
	}

}
