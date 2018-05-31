package com.sxca.myb.modules.mobile.dao;

import java.util.List;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.mobile.entity.MobileApplyInfo;


/**
 * @author glq
 * @date : 2017-05-04
 */
@MyBatisDao
public interface MobileApplyInfoDao extends CrudDao<MobileApplyInfo> {
	
	public List<MobileApplyInfo> findbyList(MobileApplyInfo mobileApplyInfo);
	
	public List<MobileApplyInfo> findbyCertSn(MobileApplyInfo mobileApplyInfo);

	public List<MobileApplyInfo> findbyOldCertSn(MobileApplyInfo mobileApplyInfo);
}
