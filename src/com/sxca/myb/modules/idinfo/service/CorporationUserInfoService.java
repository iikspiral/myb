package com.sxca.myb.modules.idinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.persistence.Page;
import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.idinfo.dao.CorporationUserInfoDao;
import com.sxca.myb.modules.idinfo.entity.CorporationUserInfo;

@Service
@Transactional(readOnly = true)
public class CorporationUserInfoService extends CrudService<CorporationUserInfoDao,CorporationUserInfo>{

	
	public CorporationUserInfo getByIdCard(String idCard) {
		return dao.getByIdCard(idCard);
		
	}
	
	public List<CorporationUserInfo> findListNotUsers(Map<String, Object> map){
		return dao.findListNotUsers(map);
	}
	
	public Page<CorporationUserInfo> findPage(Page<CorporationUserInfo> page, Map map) {
		map.put("page", page);
		page.setList(dao.findListNotUsers(map));
		return page;
	}
	
}
