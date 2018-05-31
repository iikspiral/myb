package com.sxca.myb.modules.config.sysconfig.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.config.sysconfig.dao.SystemConfigDao;
import com.sxca.myb.modules.config.sysconfig.entity.SystemConfig;


@Service
@Transactional(readOnly = true)
public class SystemConfigService extends CrudService<SystemConfigDao, SystemConfig> {

}
