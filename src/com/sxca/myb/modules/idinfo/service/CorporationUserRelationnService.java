package com.sxca.myb.modules.idinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.idinfo.dao.CorporationnUserRelationDao;
import com.sxca.myb.modules.idinfo.entity.CorporationUserRelation;

@Service
@Transactional(readOnly = true)
public class CorporationUserRelationnService extends CrudService<CorporationnUserRelationDao,CorporationUserRelation>{

	
	public List<CorporationUserRelation> getByIds(List strList){
		return dao.fingByids(strList);
	};
	public CorporationUserRelation getByCorUserId(String CorUserId) {
		CorporationUserRelation corporationUserRelation = null;
		List<CorporationUserRelation> corporationUserRelations = dao.getByCorUserId(CorUserId);
		if(corporationUserRelations!=null&&corporationUserRelations.size()>0) {
			corporationUserRelation = corporationUserRelations.get(0);
		}
		return corporationUserRelation;
	}
	
	public List<String> getCorporationId(String phoneNum,String idCard) {
		return dao.getCorporationId(phoneNum,idCard);
	}
	@Transactional(readOnly=false)
	public void setAdmin(CorporationUserRelation corporationUserRelation) {
		dao.setAdmin(corporationUserRelation);
	}
	@Transactional(readOnly=false)
	public void cancelAdmin(CorporationUserRelation corporationUserRelation) {
		dao.cancelAdmin(corporationUserRelation);
	}
	
	public CorporationUserRelation getByCorUserIdAndCorId(CorporationUserRelation corporationUserRelation) {
		return dao.getByCorUserIdAndCorId(corporationUserRelation);
	}
	
	public List<String> getUserIds(CorporationUserRelation corporationUserRelation) {
		return dao.getUserIds(corporationUserRelation);
	}
}
