package com.sxca.myb.modules.config.appsystem.dao;

import java.util.List;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.config.appsystem.entity.ApplicationSystem;

@MyBatisDao
public interface ApplicationSystemDao extends CrudDao<ApplicationSystem> {
	
	public List<ApplicationSystem> getListBysysname(ApplicationSystem applicationSystem); 
	

}
