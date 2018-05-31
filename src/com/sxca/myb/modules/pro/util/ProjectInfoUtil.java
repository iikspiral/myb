package com.sxca.myb.modules.pro.util;

import java.util.List;

import com.sxca.myb.common.utils.SpringContextHolder;
import com.sxca.myb.modules.pro.dao.ProjectInfoDao;
import com.sxca.myb.modules.pro.entity.ProjectInfo;


/**
 * @author lihuilong 
 * @date : 2017年4月10日 下午5:04:45
 */
public class ProjectInfoUtil {
	
	private static ProjectInfoDao projectInfoDao = SpringContextHolder
			.getBean(ProjectInfoDao.class);
	/**
	 * 获取所有项目
	 * @return
	 */
	public static List<ProjectInfo> getProjectInfoList() {
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setIsDefaultProject("1");
		List<ProjectInfo> list =  projectInfoDao.findList(projectInfo);
		return list;
	}
	
	/**
	 * 获取单个项目名称
	 * @return
	 */
	public static String getProjectName(String id){
		
		ProjectInfo projectInfo = projectInfoDao.get(id);
		if(projectInfo != null){
			return projectInfo.getProjectName();
		}else{
			return "";
		}
		
	}

}
