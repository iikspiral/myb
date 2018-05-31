package com.sxca.myb.modules.sms.service;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.sms.dao.SMSTemplateDao;
import com.sxca.myb.modules.sms.entity.SMSTemplate;
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
public class SMSTemplateService extends CrudService<SMSTemplateDao, SMSTemplate> {

    @Autowired
    private SMSTemplateDao SMSTemplateDao;


    public List<SMSTemplate> findAll(){
        return super.findList(new SMSTemplate());
    }

    @Transactional(readOnly = false)
    public void save(SMSTemplate SMSTemplate) {
        super.save(SMSTemplate);

    }

    @Transactional(readOnly = false)
    public void delete(SMSTemplate SMSTemplate) {
        super.delete(SMSTemplate);

    }
    
    public String getTemplateId(String templateName) {
    	return dao.getTemplateId(templateName);
    }
}
