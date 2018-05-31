package com.sxca.myb.modules.sms.service;

import java.util.List;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.sms.dao.SMSPlatformDao;
import com.sxca.myb.modules.sms.entity.SMSPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 通知管理服务实现层
 * @author glq
  * @version 2017-04-21
 */

@Service
public class SMSPlatformService extends CrudService<SMSPlatformDao, SMSPlatform> {

    @Autowired
    private SMSPlatformDao smsPlatformDao;


    public List<SMSPlatform> findAll(){
        SMSPlatform smsPlatform = new SMSPlatform();
        smsPlatform.setState("1");
        return super.findList(smsPlatform);
    }

    @Transactional(readOnly = false)
    public void save(SMSPlatform smsPlatform) {
        super.save(smsPlatform);

    }

    @Transactional(readOnly = false)
    public void delete(SMSPlatform smsPlatform) {
        super.delete(smsPlatform);

    }
    @Transactional(readOnly = true)
    public List<SMSPlatform> findList(SMSPlatform smsPlatform) {
        return super.findList(smsPlatform);
    }
}
