package com.sxca.myb.modules.config.logman.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.config.logman.dao.CertAppLogDao;
import com.sxca.myb.modules.config.logman.entity.CertAppLog;


/**  
* <p>Title: CertAppLogService</p>  
* <p>Description: </p>  
* <p>Company: 吉大正元</p>   
* @author Yophy  
* @date 上午8:30:48  
*/ 
@Service
public class CertAppLogService extends CrudService<CertAppLogDao,CertAppLog> {

	
	public List<CertAppLog> getCertAppLogs(CertAppLog CertAppLog){
		return dao.getCertAppLogs(CertAppLog);
	}

	
	
	
}
