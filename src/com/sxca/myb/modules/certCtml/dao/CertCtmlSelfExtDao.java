/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.certCtml.dao;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.certCtml.entity.CertCtmlSelfExt;

import java.util.List;


/**
 * 模板DAO接口
 * @author ThinkGem
 * @version 2014-07-10
 */
@MyBatisDao
public interface CertCtmlSelfExtDao extends CrudDao<CertCtmlSelfExt> {
    /**
     *模版同步时删除之前的模板自定义扩展信息
     */
    public void deleteData();

    /**
     *根据模板Id获取该模板的自定义扩展域
     */
    public List<CertCtmlSelfExt> findSelfExtsByCtmlId(String ctmlId);
}
