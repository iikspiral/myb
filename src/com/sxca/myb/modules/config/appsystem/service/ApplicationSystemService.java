package com.sxca.myb.modules.config.appsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.config.appsystem.dao.ApplicationSystemDao;
import com.sxca.myb.modules.config.appsystem.entity.ApplicationSystem;


@Service
@Transactional(readOnly = true)
public class ApplicationSystemService extends CrudService<ApplicationSystemDao, ApplicationSystem> {

	public List<ApplicationSystem> getListBysysname(ApplicationSystem applicationSystem){
		return dao.getListBysysname(applicationSystem);
	}
}
