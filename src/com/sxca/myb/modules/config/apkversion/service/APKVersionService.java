package com.sxca.myb.modules.config.apkversion.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.config.apkversion.dao.APKVersionDao;
import com.sxca.myb.modules.config.apkversion.entity.APKVersion;

@Service
@Transactional(readOnly = true)
public class APKVersionService extends CrudService<APKVersionDao, APKVersion>{

}
