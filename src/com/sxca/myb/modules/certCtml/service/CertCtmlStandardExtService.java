/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.certCtml.service;

import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.certCtml.dao.CertCtmlStandardExtDao;
import com.sxca.myb.modules.certCtml.entity.CertCtmlStandardExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 模板DAO接口
 * @author ThinkGem
 * @version 2014-07-10
 */
@Service
@Transactional(readOnly = true)
public class CertCtmlStandardExtService extends CrudService<CertCtmlStandardExtDao, CertCtmlStandardExt> {

    @Autowired
    private CertCtmlStandardExtDao certCtmlStandardExtDao;
    /**
     *模版同步时删除之前的模板标准扩展信息
     */
    @Transactional(readOnly = false)
    public void deleteData(){
        certCtmlStandardExtDao.deleteData();
    }
    /**
     *根据模板Id获取该模板的标准扩展域
     */
    public List<CertCtmlStandardExt> findStandardExtsByCtmlId(String ctmlId){
        return certCtmlStandardExtDao.findStandardExtsByCtmlId(ctmlId);
    }
}
