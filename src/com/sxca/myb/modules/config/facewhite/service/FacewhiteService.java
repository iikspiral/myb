package com.sxca.myb.modules.config.facewhite.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.config.facewhite.dao.FacewhiteDao;
import com.sxca.myb.modules.config.facewhite.entity.Facewhite;

/**
 * @author lihuilong 
 * @date : 2017年5月2日 下午5:54:02
 */
@Service
@Transactional(readOnly = true)
public class FacewhiteService extends CrudService<FacewhiteDao,Facewhite>{

}
