package com.sxca.myb.modules.mobile.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.vo.CertVo;

/**
 * @author lihuilong 
 * @date : 2017年4月24日 下午3:24:04
 */
public interface MobileDao {

	//身份验证接口（新制待申请）
	public Map<String,Object> validateIdentityNew(String certType, String idCard,String whiteListId, String phoneNum);
	//是否可申请证书接口
	public Map<String, Object> getIsMobileApply(String name,String phoneNum, String idCard,String deviceNum);
	//获取当前用户待申请证书
	public List<Map<String,Object>> getWaitApplyCert(String phoneNum, String idCard);
	//获取当前用户可变更证书
	public List<Map<String, Object>> getChangeCert(String phoneNum, String idCard);
	//证书更新申请下载接口
	public Map<String,Object> updateMobileCert(CertVo certVo,String mobileApplyInfoId,String applyBusinessId,String isRecovery,CertInfo certInfoOld);
	//证书注销申请接口
	public Map<String,Object> cancelMobileCert(CertVo certVo,String mobileApplyInfoId,String applyBusinessId,String cancelReason,String cancelDes);
	//证书变更申请下载接口
	public Map<String,Object> changeMobileCert(String certType, String certSn,String deviceType, String deviceNum,String certSubject,String applyBusinessId,String mobileApplyInfoId);
	//修改手机号接口
	public Map<String,Object> changePhoneNum(String name,String idCard,String oldPhoneNum,String newPhoneNum,String deviceNum);
	//忘记证书密码申请
	public Map<String,Object> getMobileCertByForgetPass(CertVo certVo,String mobileApplyInfoId,String applyBusinessId,String isRecovery);
	//查询证书是否 跟新
	public Map<String,String> getAllowUpdate(String certSn) throws ParseException;
}
