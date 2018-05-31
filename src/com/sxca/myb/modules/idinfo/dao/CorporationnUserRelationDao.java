package com.sxca.myb.modules.idinfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.idinfo.entity.CorporationUserRelation;

@MyBatisDao
public interface CorporationnUserRelationDao extends CrudDao<CorporationUserRelation>{
	
	//企业与企业用户表 通过id List 来查询
	public List<CorporationUserRelation> fingByids(List strList);

	public List<CorporationUserRelation> getByCorUserId(String CorUserId);
	
	public void setAdmin(CorporationUserRelation corporationUserRelation);
	public void cancelAdmin(CorporationUserRelation corporationUserRelation);
	public CorporationUserRelation getByCorUserIdAndCorId(CorporationUserRelation corporationUserRelation);
	
	public List<String> getCorporationId(@Param("phoneNum")String phoneNum,@Param("idCard")String idCard);
	
	public String getIsAdmin(@Param("userInfoId")String userInfoId,@Param("corpUserId")String corpUserId);
	
	public String getAdminCorpUserId(@Param("userInfoId")String userInfoId);
	
	public List<String> getUserIds(CorporationUserRelation corporationUserRelation);
}
