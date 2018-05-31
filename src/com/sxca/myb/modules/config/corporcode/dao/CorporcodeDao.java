package com.sxca.myb.modules.config.corporcode.dao;

import java.util.List;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.config.corporcode.entity.CorporationRequestCode;

/**
 * @author lihuilong 
 * @date : 2017年4月14日 上午9:22:39
 */
@MyBatisDao
public interface CorporcodeDao extends CrudDao<CorporationRequestCode>{
	
	public boolean saveCorpor(CorporationRequestCode entity);
	//查询新制下，同意项目 企业 企业用户 已存在的白名单信息集合
	public List<CorporationRequestCode> findUseCorpor(String projectId);
	//查询变更下，已存在的证书变更集合
	public List<CorporationRequestCode> findCertCorpor(CorporationRequestCode entity);
	//通过id 查询出企业白名单单条信息，以及关联的企业用户信息
	public CorporationRequestCode getCorporuserByid(String id);
	
	public List<CorporationRequestCode> getWaitcorporWhitelist(CorporationRequestCode entity);
	
	public List<CorporationRequestCode> getByCorUserId(String corUserId);

	public List<CorporationRequestCode> getNotInvalidCorpcode(CorporationRequestCode entity);

}
