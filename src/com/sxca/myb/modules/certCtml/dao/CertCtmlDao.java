/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.certCtml.dao;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.certCtml.entity.CertCtml;


/**
 * 模板DAO接口
 * @author ThinkGem
 * @version 2014-07-10
 */
@MyBatisDao
public interface CertCtmlDao extends CrudDao<CertCtml> {

    /**
     * 模板同步时删除之前的所有模板信息
     */
    public void deleteData();
    
}
