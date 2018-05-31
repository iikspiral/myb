package com.sxca.myb.modules.cert.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sxca.myb.modules.cert.dao.CertapplyInfoDao;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.certCtml.dao.CertSelfExtDao;
import com.sxca.myb.modules.certCtml.dao.CertStandardExtDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.jit.pki.ra.cert.response.query.CertSingleQueryResponse;
import cn.com.jit.pki.ra.vo.RACertInfo;

import com.sxca.myb.common.BTK.btkinterface.BTK;
import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.cert.dao.CertInfoDao;
import com.sxca.myb.modules.cert.entity.CertInfo;

/**
 * @author lihuilong
 * @date : 2017年4月17日 下午3:29:02
 */
@Service
@Transactional(readOnly = true)
public class CertInfoService extends CrudService<CertInfoDao, CertInfo> {
	@Autowired
	private BTK btk;
	@Autowired
	private CertapplyInfoDao certapplyInfoDao;
	@Autowired
	private CertSelfExtDao certSelfExtDao;
	@Autowired
	private CertStandardExtDao certStandardExtDao;
	@Autowired
	private CertInfoDao certInfoDao;
	public List<CertInfo> fingByids(List<String> result) {
		List<CertInfo> list = dao.fingByids(result);
		return list;
	}
	
	public CertInfo getByCertSn(String cerSn){
		return dao.getByCertSn(cerSn);
	}

	/**
	 * 通过BTK查询证书信息并进行保存
	 * @param certSn
	 * @return
     */
	@Transactional(readOnly = false)
	public CertInfo createOrUpdateCert(String certSn,String isRecovery){
		//根据证书序列号去RA查询证书信息
		CertSingleQueryResponse certSingleQueryResponse = btk.certSingleQuery(certSn);
		if(certSingleQueryResponse != null && "0".equals(certSingleQueryResponse.getErr())){
			RACertInfo raCertInfo = certSingleQueryResponse.getRaCertInfo();
			if(raCertInfo == null){
				return null;
			}
			//获取本地证书信息,如果有则进行更新(除新制外的业务),没有进行新增(新制)
			CertInfo certInfo = dao.getByCertSn(certSn);
			if(certInfo != null){
				//对已有证书进行更新
				String certStatus = raCertInfo.getCertInfo().getCertStatus();
				certInfo.setCertStatus(certStatus);
				if("Hold".equals(certStatus) || "Revoke".equals(certStatus)){
					certInfo.setCertStatusMyb(certStatus);
				}
				certInfo.setNotBefore(new BigDecimal(raCertInfo.getCertInfo().getNotBefore()));
				certInfo.setNotAfter(new BigDecimal(raCertInfo.getCertInfo().getNotAfter()));
				certInfo.setCertValidity(raCertInfo.getCertInfo().getValidity());
				certInfo.setOptTime(new BigDecimal(raCertInfo.getOptTime()));
				certInfo.setIsRecovery(isRecovery);
				super.update(certInfo);
				//对申请信息进行更新
				CertapplyInfo certapplyInfo = new CertapplyInfo();
				certapplyInfo.setCertSn(certSn);
				List<CertapplyInfo> certApplyInfolist = certapplyInfoDao.findList(certapplyInfo);
				certApplyInfolist.get(0).setNotBefore(new BigDecimal(raCertInfo.getCertInfo().getNotBefore()));
				certApplyInfolist.get(0).setNotAfter(new BigDecimal(raCertInfo.getCertInfo().getNotAfter()));
				certApplyInfolist.get(0).setCertValidity(raCertInfo.getCertInfo().getValidity());
				certapplyInfoDao.update(certApplyInfolist.get(0));
			}else{ //新制证书进行保存
				certInfo = new CertInfo();
				certInfo.setCertSn(raCertInfo.getCertInfo().getCertSN());
				certInfo.setCertStatus(raCertInfo.getCertInfo().getCertStatus());
				certInfo.setNotBefore(new BigDecimal(raCertInfo.getCertInfo().getNotBefore()));
				certInfo.setNotAfter(new BigDecimal(raCertInfo.getCertInfo().getNotAfter()));
				certInfo.setCertValidity(raCertInfo.getCertInfo().getValidity());
				certInfo.setAuthCode(raCertInfo.getCertInfo().getAuthCode());
				certInfo.setCertSubject(raCertInfo.getCertInfo().getSubject());
				certInfo.setSubjectUppercase(raCertInfo.getCertInfo().getSubject().toUpperCase());
				certInfo.setCertType(raCertInfo.getCertType());
				certInfo.setCommonName(raCertInfo.getCertInfo().getCommonname());
				certInfo.setCtmlName(raCertInfo.getCertInfo().getCtmlName());
				certInfo.setIsAdmin(raCertInfo.getIsAdmin());
				certInfo.setUserinfoId(raCertInfo.getUserInfoId());
				certInfo.setOrganId(raCertInfo.getOrganId());
				certInfo.setApplicant(raCertInfo.getCertInfo().getApplicant());
				certInfo.setApplicantUppercase(raCertInfo.getCertInfo().getApplicant().toUpperCase());
				certInfo.setOptTime(new BigDecimal(raCertInfo.getOptTime()));
				certInfo.setOldCertSn(raCertInfo.getCertInfo().getOldSN());
				certInfo.setDoubleCertSn(raCertInfo.getCertInfo().getDoublecertsn());
				certInfo.setOldDoubleCertSn(raCertInfo.getCertInfo().getOlddoublecertsn());
				certInfo.setIsRetainKey(raCertInfo.getCertInfo().getIsRetainKey());
				certInfo.setIsWaiting(raCertInfo.getCertInfo().getIswaiting());
				certInfo.setSignServer(raCertInfo.getCertInfo().getSignServer());
				certInfo.setCertStatusMyb("Undown");
				certInfo.setIsRecovery(isRecovery);
				super.insert(certInfo);

				CertapplyInfo certapplyInfo = new CertapplyInfo();
				certapplyInfo.setCertSn(certSn);
				List<CertapplyInfo> certApplyInfolist = certapplyInfoDao.findList(certapplyInfo);
				certApplyInfolist.get(0).setCertId(certInfo.getId());
				certapplyInfoDao.update(certApplyInfolist.get(0));
			}
			return certInfo;
		}
		return null;
	}


	public List<CertInfo> findbyList(CertInfo certInfo) {
		List<CertInfo> list = dao.findbyList(certInfo);
		return list;
	}
	
	public CertInfo getbyid(String id){
		return dao.getbyid(id);
	}
	
	public CertInfo getByOldCertSn(String certSn){
		return dao.getByOldCertSn(certSn);
	}
	
	
	
	public List<Map<String,String>> getCertsIsCancel(String[] certSns){
		
		List<Map<String,String>> certCancels = new ArrayList<Map<String,String>>();
		
		for(String certSn:certSns) {
			CertInfo certInfo = getByOldCertSn(certSn);
			if(certInfo==null) {
				certInfo = getByCertSn(certSn);
				if(certInfo!=null) {
					if("Revoke".equals(certInfo.getCertStatus())||(("Use".equals(certInfo.getCertStatus())||"Undown".equals(certInfo.getCertStatus()))&&"Undown".equals(certInfo.getCertStatusMyb()))) {
						Map<String,String> map = new HashMap<String,String>();
						map.put("certSn", certSn);
						certCancels.add(map);
					}
				}
			}else {
				certInfo = getByCertSn(certSn);
				if(certInfo!=null) {
					if("Revoke".equals(certInfo.getCertStatus())&&"Undown".equals(certInfo.getCertStatusMyb())) {
						Map<String,String> map = new HashMap<String,String>();
						map.put("certSn", certSn);
						certCancels.add(map);
					}else {
						if(getCertStatus(certSn)==null) {
							Map<String,String> map = new HashMap<String,String>();
							map.put("certSn", certSn);
							certCancels.add(map);
						}
					}
				}
			}
		}
		return certCancels;
	}
	
	public String getCertStatus(String certSn) {
		
		CertInfo certInfo = getByOldCertSn(certSn);
		
		if(certInfo == null) {
			certInfo = getByCertSn(certSn);
			if(certInfo != null){
				if(("Use".equals(certInfo.getCertStatus()) || "Undown".equals(certInfo.getCertStatus()))&&"Undown".equals(certInfo.getCertStatusMyb())) {
					return certInfo.getCertSn();
				}else{
					return null;
				}
			}else{
				return null;
			}
		}else {
			return getCertStatus(certInfo.getCertSn());
		}
	}
	
	public List<String> getCertSns(String[] certSns) {
		
		List<String> list = new ArrayList<String>();
		
		for(String oldCertSn:certSns) {
			
			String certSn = getCertSn(oldCertSn);
			
			if(certSn!=null) {
				list.add(certSn);
			}
		}
		return list;
	}
	
	public String getCertSn(String certSn) {
		
		CertInfo certInfo = getByOldCertSn(certSn);
		
		if(certInfo == null) {
			
			certInfo = getByCertSn(certSn);
			if(certInfo!=null) {
				return certInfo.getCertSn();
			}else {
				return null;
			}
			
		}else {
			return getCertSn(certInfo.getCertSn());
		}
	}
	
	
	/**
	 * 创建一条信息证书信息 lihuilong
	 * 
	 * @param certSn
	 * @return
	 */
	@Transactional(readOnly = false)
	public CertInfo getCertBycertsnFromra(String certSn) {
		// 根据证书序列号去RA查询证书信息
		CertSingleQueryResponse certSingleQueryResponse = btk
				.certSingleQuery(certSn);

		if (certSingleQueryResponse != null
				&& "0".equals(certSingleQueryResponse.getErr())) {
			RACertInfo raCertInfo = certSingleQueryResponse.getRaCertInfo();

			CertInfo certInfoNew = new CertInfo(certSn, raCertInfo
					.getCertInfo().getSubject().toUpperCase(), raCertInfo
					.getCertInfo().getSubject(), raCertInfo.getCertInfo()
					.getCommonname(), raCertInfo.getCertInfo().getCtmlName(),
					raCertInfo.getCertInfo().getAuthCode(),
					raCertInfo.getIsAdmin(), raCertInfo.getCertInfo()
							.getCertStatus(), raCertInfo.getCertInfo()
							.getValidity(), new BigDecimal(raCertInfo
							.getCertInfo().getNotBefore()), new BigDecimal(
							raCertInfo.getCertInfo().getNotAfter()),
					raCertInfo.getUserInfoId(), raCertInfo.getCertType(),
					raCertInfo.getCertInfo().getApplicant().toUpperCase(),
					raCertInfo.getCertInfo().getApplicant(), new BigDecimal(
							raCertInfo.getOptTime()), raCertInfo.getOrganId(),
					raCertInfo.getCertInfo().getIsRetainKey(), raCertInfo
							.getCertInfo().getOldSN(), raCertInfo.getCertInfo()
							.getDoublecertsn(), raCertInfo.getCertInfo()
							.getOlddoublecertsn(), raCertInfo.getCertInfo()
							.getIswaiting(), raCertInfo.getCertInfo()
							.getSignServer(),"Undown");
			return certInfoNew;

		} else {
			return null;
		}
	}
}
 