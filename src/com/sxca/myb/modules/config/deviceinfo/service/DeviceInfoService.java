package com.sxca.myb.modules.config.deviceinfo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.config.deviceinfo.dao.DeviceInfoDao;
import com.sxca.myb.modules.config.deviceinfo.entity.DeviceInfo;


@Service
@Transactional(readOnly = true)
public class DeviceInfoService extends CrudService<DeviceInfoDao, DeviceInfo> {

}
