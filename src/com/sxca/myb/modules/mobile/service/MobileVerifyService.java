package com.sxca.myb.modules.mobile.service;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.mobile.dao.MobileVerifyDao;
import com.sxca.myb.modules.mobile.entity.MobileVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author glq
 * @date : 2017-04-27
 */
@Service
public class MobileVerifyService extends CrudService<MobileVerifyDao, MobileVerify> {

	@Autowired
	private MobileVerifyDao mobileVerifyDao;

	public List<MobileVerify> findAll(){
		return super.findList(new MobileVerify());
	}

	/**
	 * 保存移动端验证码信息
	 * @param deviceType
	 * @param deviceNum
	 * @param phoneNum
	 * @param verifyCode
     */
	@Transactional(readOnly = false)
	public void save(String deviceType, String deviceNum, String phoneNum, String verifyCode) {
		MobileVerify verify = new MobileVerify();
		verify.setPhoneNum(phoneNum);
		List<MobileVerify> verifies = super.findList(verify);
		//如果已存在该手机号的验证信息则进行更新,没有则新增
		if(verifies != null && verifies.size() > 0){
			verifies.get(0).setDeviceNum(deviceNum);
			verifies.get(0).setDeviceType(deviceType);
			verifies.get(0).setVerifyCode(verifyCode);
			verifies.get(0).setSendDate(new Date());
			super.update(verifies.get(0));
		}else{
			verify.setDeviceNum(deviceNum);
			verify.setDeviceType(deviceType);
			verify.setVerifyCode(verifyCode);
			verify.setSendDate(new Date());
			super.insert(verify);
		}
	}

	@Transactional(readOnly = false)
	public void delete(MobileVerify mobileVerify) {
		super.delete(mobileVerify);

	}

	/**
	 * 判断验证码的唯一性
	 * @param verifyCode
	 * @return
     */
	@Transactional(readOnly = false)
	public boolean unquieVerifyCode(String verifyCode) {
		List<MobileVerify> lists  = mobileVerifyDao.unquieVerifyCode(verifyCode);
		if (lists != null && lists.size() > 0) {
			return false;
		}else{
			return true;
		}
	}
}
