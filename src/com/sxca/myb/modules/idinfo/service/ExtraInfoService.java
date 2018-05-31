package com.sxca.myb.modules.idinfo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.idinfo.dao.ExtraInfoDao;
import com.sxca.myb.modules.idinfo.entity.ExtraInfo;

/**
 * @author lihuilong
 * @date : 2017年4月10日 下午2:26:30
 */
@Service
@Transactional(readOnly = true)
public class ExtraInfoService extends CrudService<ExtraInfoDao, ExtraInfo> {

}
