package com.sxca.myb.modules.mobile.dao;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.mobile.entity.MobileVerify;

import java.util.List;

/**
 * @author glq
 * @date : 2017-04-27
 */
@MyBatisDao
public interface MobileVerifyDao extends CrudDao<MobileVerify> {

	/**
	 * 判断验证码的唯一性
	 * @param verifyCode
	 * @return
	 */
	public List<MobileVerify> unquieVerifyCode(String verifyCode);

}
