/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.certCtml.service;

import com.sxca.myb.common.BTK.btkinterface.BTK;
import com.sxca.myb.common.BTK.entity.CtmlBTK;
import com.sxca.myb.common.BTK.entity.CtmlSelfExtBTK;
import com.sxca.myb.common.BTK.entity.CtmlStandardExtBTK;
import com.sxca.myb.common.BTK.entity.CtmlVOBTK;
import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.certCtml.dao.CertCtmlDao;
import com.sxca.myb.modules.certCtml.dao.CertCtmlSelfExtDao;
import com.sxca.myb.modules.certCtml.dao.CertCtmlStandardExtDao;
import com.sxca.myb.modules.certCtml.entity.CertCtml;
import com.sxca.myb.modules.certCtml.entity.CertCtmlSelfExt;
import com.sxca.myb.modules.certCtml.entity.CertCtmlStandardExt;
import com.sxca.myb.modules.certCtml.entity.CertCtmlVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class CertCtmlService extends CrudService<CertCtmlDao, CertCtml> {
	@Autowired
	private BTK btk;
	@Autowired
	private CertCtmlDao certCtmlDao;
	@Autowired
	private CertCtmlSelfExtDao certCtmlSelfExtDao;
	@Autowired
	private CertCtmlStandardExtDao certCtmlStandardExtDao;

	public List<CertCtml> findAll(){
		return super.findList(new CertCtml());
	}

	@Transactional(readOnly = false)
	public void save(CertCtml certCtml) {
		super.save(certCtml);

	}
	
	@Transactional(readOnly = false)
	public void delete(CertCtml certCtml) {
		super.delete(certCtml);

	}
	@Transactional(readOnly = false)
	public CertCtmlVO findCtmlVo(String ctmlId){
		CertCtmlVO certCtmlVO = new CertCtmlVO();
		CertCtml certCtml = dao.get(ctmlId);
		certCtmlVO.setCtml(certCtml);
		List<CertCtmlSelfExt> selfExtList = certCtmlSelfExtDao.findSelfExtsByCtmlId(certCtml.getId());
		certCtmlVO.setSelfExt(selfExtList);
		List<CertCtmlStandardExt> standardExtList = certCtmlStandardExtDao.findStandardExtsByCtmlId(certCtml.getId());
		certCtmlVO.setStandardExt(standardExtList);
		if (standardExtList != null && standardExtList.size() > 0) {
			List<CertCtmlStandardExt> identify = new ArrayList<>();
			List<CertCtmlStandardExt> insurance = new ArrayList<>();
			List<CertCtmlStandardExt> icreg = new ArrayList<>();
			List<CertCtmlStandardExt> organ = new ArrayList<>();
			List<CertCtmlStandardExt> taxation = new ArrayList<>();
			List<CertCtmlStandardExt> subjectAlt = new ArrayList<>();
			for (int i = 0; i < standardExtList.size(); i++) {
				CertCtmlStandardExt ext = standardExtList.get(i);
				if (ext.getExtName().equals("IdentifyCodePolicy")) {
					identify.add(ext);
				}
				if (ext.getExtName().equals("InsuranceNumPolicy")) {
					insurance.add(ext);
				}
				if (ext.getExtName().equals("ICRegNumPolicy")) {
					icreg.add(ext);
				}
				if (ext.getExtName().equals("OrganCodePolicy")) {
					organ.add(ext);
				}
				if (ext.getExtName().equals("TaxationNumPolicy")) {
					taxation.add(ext);
				}
				if (ext.getExtName().equals("SubjectAltNameExtPolicy")) {
					subjectAlt.add(ext);
				}
			}
			List<List<CertCtmlStandardExt>> list = new ArrayList<List<CertCtmlStandardExt>>();
			list.add(0, identify);
			list.add(1, insurance);
			list.add(2, icreg);
			list.add(3, organ);
			list.add(4, taxation);
			list.add(5, subjectAlt);
			certCtmlVO.setStandardExtList(list);
		}
		return certCtmlVO;
	}
	@Transactional(readOnly = false)
	public boolean synchCtml() {
		try {
			List<CtmlVOBTK> list=btk.certCtmlQuery();
			if(list.size()>0){
				certCtmlDao.deleteData();
				certCtmlSelfExtDao.deleteData();
				certCtmlStandardExtDao.deleteData();
				for (CtmlVOBTK ctmlVO : list) {
					CtmlBTK ctmlBTK = ctmlVO.getCtmlBTK();
					CertCtml certCtml = new CertCtml();
					certCtml.setId(ctmlBTK.getCtmlId());
					certCtml.setCertCtmlName(ctmlBTK.getCtmlName());
					certCtml.setCertCtmlType(ctmlBTK.getCtmlCertType());
					certCtml.setCertCtmlBasedn(ctmlBTK.getCtmlBaseDn());
					certCtml.setCertCtmlStatus(ctmlBTK.getCtmlStatus());
					certCtml.setCertCtmlAudit(ctmlBTK.getCtmlAuditStatus());
					certCtml.setSecretType(ctmlBTK.getCtmlKeyType()+"--"+ctmlBTK.getCtmlKeyLength());
					certCtml.setCtmlKeyGenPlace(ctmlBTK.getCtmlKeyGenPlace());
					certCtmlDao.insert(certCtml);
					if (ctmlBTK != null) {
						List<CtmlSelfExtBTK> selfExtList = ctmlVO.getCtmlSelfExtBTKs();
						if (selfExtList != null && selfExtList.size() > 0) {
							for (int i = 0; i < selfExtList.size(); i++) {
								CtmlSelfExtBTK selfExt = selfExtList.get(i);
								if (selfExt != null) {
									CertCtmlSelfExt certCtmlSelfExt = new CertCtmlSelfExt();
									certCtmlSelfExt.setCertCtmlId(certCtml.getId());
									certCtmlSelfExt.setSelfExtName(selfExt.getCtmlSelfExtName());
									certCtmlSelfExt.setSelfExtOid(selfExt.getCtmlSelfExtOid());
									certCtmlSelfExt.preInsert();
									certCtmlSelfExtDao.insert(certCtmlSelfExt);
								}
							}
						}
						List<CtmlStandardExtBTK> standardExtList = ctmlVO.getCtmlStandardExtBTKs();
						if (standardExtList != null&& standardExtList.size() > 0) {
							for (int i = 0; i < standardExtList.size(); i++) {
								CtmlStandardExtBTK standardExt = standardExtList.get(i);
								if (standardExt != null) {
									CertCtmlStandardExt certCtmlStandardExt = new CertCtmlStandardExt();
									certCtmlStandardExt.setCertCtmlId(certCtml.getId());
									certCtmlStandardExt.setExtName(standardExt.getCtmlExtName());
									certCtmlStandardExt.setExtChildName(standardExt.getCtmlExtChildName());
									certCtmlStandardExt.preInsert();
									certCtmlStandardExtDao.insert(certCtmlStandardExt);
								}
							}
						}
					}
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
