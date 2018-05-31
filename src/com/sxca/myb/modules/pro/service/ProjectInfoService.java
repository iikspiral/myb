package com.sxca.myb.modules.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.pro.dao.ProjectInfoDao;
import com.sxca.myb.modules.pro.entity.ProjectInfo;

/**
 * @author lihuilong 
 * @date : 2017年4月10日 下午4:15:02
 */
@Service
@Transactional(readOnly = true)
public class ProjectInfoService extends CrudService<ProjectInfoDao,ProjectInfo>{
}
