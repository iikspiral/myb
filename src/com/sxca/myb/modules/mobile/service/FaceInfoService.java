/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.mobile.service;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.mobile.dao.FaceInfoDao;
import com.sxca.myb.modules.mobile.entity.FaceInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 人脸信息Service
 * @author glq
 * @version 2017-05-11
 */
@Service
public class FaceInfoService extends CrudService<FaceInfoDao, FaceInfo> {

}
