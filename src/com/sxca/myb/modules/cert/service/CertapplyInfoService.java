package com.sxca.myb.modules.cert.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.cert.dao.CertapplyInfoDao;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;

/**
 * @author lihuilong 
 * @date : 2017年4月18日 上午8:37:35
 */
@Service
@Transactional(readOnly = true)
public class CertapplyInfoService  extends CrudService<CertapplyInfoDao,CertapplyInfo>{

	public  List<CertapplyInfo> findByCertSn (String certSn){
		 return dao.findByCertSn(certSn);
	 }
	
	
	public List<CertapplyInfo> findbyList(CertapplyInfo certapplyInfo) {
		List<CertapplyInfo> list = dao.findbyList(certapplyInfo);
		return list;
	}
	@Transactional(readOnly = false)
	public void deleteData(CertapplyInfo certapplyInfo) {
		dao.deleteData(certapplyInfo);
	}
}
