package com.sxca.myb.modules.config.whitelist.dao;

import java.util.List;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;

@MyBatisDao
public interface WhiteListDao extends CrudDao<WhiteList> {
	
	public WhiteList findUserList(String UserInfoId);
	

	public List<WhiteList> findbyprojectInfoId(String projectInfoId);
	
	public List<WhiteList> findNameList(String projectInfoName);
	
	public List<WhiteList> findCertList(String certSn);
	
	public WhiteList getView (String id);
	
	public List<WhiteList> findWaitwhiteList(WhiteList whiteList);
	//查询新制下，相同 企业 企业用户 已存在的白名单信息集合
	public List<WhiteList> getbyProId(String projectId);
	//查询变更下，已存在的证书变更集合
	public List<WhiteList> findCert(WhiteList entity);
	
	public List<WhiteList> getbyCertSn(WhiteList entity);
	
	public List<WhiteList> getWhiteList();
	
}
