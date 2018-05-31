/**
 * Copyright &copy; 2016-2016 <a href="http://www.sxca.com.cn">山西CA</a> All rights reserved.
 */
package com.sxca.myb.modules.mobile.util;

import com.sxca.myb.common.config.ApplyConstants;
import com.sxca.myb.common.utils.SpringContextHolder;
import com.sxca.myb.modules.config.usertypedetail.dao.UsertypeDetailDao;
import com.sxca.myb.modules.config.usertypedetail.entity.UserTypeDetail;
import com.sxca.myb.modules.idinfo.entity.CorporationInfo;
import com.sxca.myb.modules.idinfo.entity.UserInfo;
import com.sxca.myb.modules.pro.entity.ProjectInfo;


import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取证书主题工具类
 * @author glq
 * @version 2017-06-14
 */
public class GetCertSubjectUtil {
	
	
	private static UsertypeDetailDao usertypeDetailDao = SpringContextHolder
			.getBean(UsertypeDetailDao.class);

	/**
	 * glq
	 *
	 * @param projectInfo
	 *            项目信息
	 * @param userinfo
	 *            用户信息
	 * @param userType
	 *            用户类型 userinfo(个人用户类型) corporation_info(企业用户类型)
	 * @return
	 */
	public static String getSubject(ProjectInfo projectInfo, Object userinfo, String userType,String rdmNum) {

		if (projectInfo != null) {
			// 取到制证规则
			String makeCertRules = projectInfo.getMakeCertRules();
			return getProsubject(makeCertRules, userType, userinfo,rdmNum);
		} else {
			return null;
		}
	}
	
	public static Map<String,String> getUsertypedetail(String userType){
		Map<String,String> mapList = new HashMap<>();
		
		//user_type_detail 
		UserTypeDetail userTypeDetail = new UserTypeDetail();
		
		userTypeDetail.setUserType(userType);
		
		List<UserTypeDetail> usertypeDetailList = usertypeDetailDao.findList(userTypeDetail);
		
		if(usertypeDetailList != null && usertypeDetailList.size() > 0){
			for(UserTypeDetail entity : usertypeDetailList){
				mapList.put(entity.getDisplayName(), entity.getFailName());
			}
		}
		return mapList;
	}

	/**
	 * 项目制证规则 lihuilong
	 *
	 * @param makeCertRules
	 *            [企业用户:CN=${企业名称},OU=${经办人有效证件号码},C=CN;个人用户:CN=${姓名},OU=${
	 *            有效证件号码},C=CN;] 大概格式 ${}里面特殊情况 “序号” 特殊判断拼一个随机六位字符串
	 * @return
	 */
	public static String getProsubject(String makeCertRules, String userType, Object userinfo,String rdmNum) {

		if (StringUtils.isNotBlank(makeCertRules)) {
			// 判断用户类型 userinfo 个人用户 corporation_info 企业用户
			if (ApplyConstants.USERTYPE_USERINFO.equals(userType)) {
				// 获取用户信息
				UserInfo userInfo = (UserInfo)userinfo;
				if (userInfo != null) {
					try {
						if (makeCertRules.indexOf("个人用户") != -1) {
							String corporSub = makeCertRules
									.substring(makeCertRules.indexOf("个人用户") + 5);
							corporSub = corporSub.substring(0,
									corporSub.indexOf(";"));
							String subject = getSubvalue(corporSub, userInfo,
									userType,rdmNum);
							return subject;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					return null;
				}

			} else if (ApplyConstants.USERTYPE_CORPORATION_INFO.equals(userType)) {
				try {
					CorporationInfo corporationInfo = (CorporationInfo)userinfo;
					if (corporationInfo != null) {
						try {
							if (makeCertRules.indexOf("企业用户") != -1) {
								String corporSub = makeCertRules
										.substring(makeCertRules
												.indexOf("企业用户") + 5);
								corporSub = corporSub.substring(0,
										corporSub.indexOf(";"));
								String subject = getSubvalue(corporSub,
										corporationInfo, userType,rdmNum);
								return subject;
							} else {
								return null;
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

		} else {
			return null;
		}

		return null;
	}

	// lihuilong
	/**
	 * 对截取到的证书主题规则数据替换
	 *
	 * @param corporSub
	 * @param object
	 * @return
	 */
	public static String getSubvalue(String corporSub, Object object, String userType,String rdmNum) {
		Map<String,String> mapList = getUsertypedetail(userType);
		try {
			boolean isAddRdmNum = true;// 是否要添加随机数UID (避免多次添加)
			String[] strarry = corporSub.split(",");
			String subject = "";
			for (int i = 0; i < strarry.length; i++) {
				String str = strarry[i];
				if (str.indexOf("$") != -1) {
					String[] subarr = str.split("}");
					for (int j = 0; j < subarr.length; j++) {
						String subStr = subarr[j];
						if (subStr.indexOf("$") != -1) {
							String sub = subStr.substring(0,
									subStr.indexOf("$"));
							String name = subStr
									.substring(subStr.indexOf("{") + 1);
							subject += sub + getSxname(object, mapList.get(name));
						} else {
							subject += subStr;
						}
						subject += ",";
					}
				} else {
					if (ApplyConstants.USERTYPE_CORPORATION_INFO.equals(userType) && isAddRdmNum) {
						subject += "UID="
								+ rdmNum + ",";
						isAddRdmNum = false;
					}
					subject += str + ",";
				}
			}
			subject = subject.substring(0, subject.length() - 1);
			return subject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param userInfo
	 *            用户实体
	 * @param name
	 *            配置文件属性
	 * @return
	 */
	public static String getSxname(Object userInfo, String name) {
		try {

			Class userCla = (Class) userInfo.getClass();

			Field[] fs = userCla.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true);
				try {
					Object val = f.get(userInfo);
					if (name.equals(f.getName())) {
						return (String) val;
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			System.out.println("subject.properties获取值错误");
			e.printStackTrace();
		}
		return null;

	}
}
