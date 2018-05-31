/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.certCtml.service;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.certCtml.dao.CertCtmlSelfExtDao;
import com.sxca.myb.modules.certCtml.entity.CertCtmlSelfExt;
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
public class CertCtmlSelfExtService extends CrudService<CertCtmlSelfExtDao, CertCtmlSelfExt> {

    @Autowired
    private CertCtmlSelfExtDao certCtmlSelfExtDao;
    /**
     *模版同步时删除之前的模板自定义扩展信息
     */
    @Transactional(readOnly = false)
    public void deleteData(){
        certCtmlSelfExtDao.deleteData();
    }
    /**
     *根据模板Id获取该模板的自定义扩展域
     */
    public List<CertCtmlSelfExt> findSelfExtsByCtmlId(String ctmlId){
        return certCtmlSelfExtDao.findSelfExtsByCtmlId(ctmlId);
    }
}
