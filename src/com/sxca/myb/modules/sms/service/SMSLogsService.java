package com.sxca.myb.modules.sms.service;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.sms.dao.SMSLogsDao;
import com.sxca.myb.modules.sms.dao.SMSTemplateDao;
import com.sxca.myb.modules.sms.entity.SMSLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 通知管理服务实现层
 *  @author glq
 * @version 2017-04-21
 */

@Service
public class SMSLogsService extends CrudService<SMSLogsDao, SMSLogs> {

    @Autowired
    private SMSTemplateDao SMSTemplateDao;


    public List<SMSLogs> findAll(){
        return super.findList(new SMSLogs());
    }

    @Transactional(readOnly = false)
    public void save(SMSLogs smsLogs) {
        super.save(smsLogs);

    }

    @Transactional(readOnly = false)
    public void delete(SMSLogs smsLogs) {
        super.delete(smsLogs);

    }
}
