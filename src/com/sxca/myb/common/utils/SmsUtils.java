package com.sxca.myb.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.sxca.myb.common.client.SmsService.SmsPortType;

public class SmsUtils {
	
	public static final String IN0 = "103904"; 		// 企业编号
	public static final String IN1 = "gd_qhcwl"; 	// 用户名称
	public static final String IN2 = "544402"; 		// 用户密码
	
	private static SmsPortType smsPortType = SpringContextHolder.getBean(SmsPortType.class);
	
	public static Map<String, String> sendSms(String mobile, String verificationCode) {
		
		String content = "您的验证码为:" + verificationCode;
//		String content = "你有一项编号为123456789的事务需要处理！";
		
		String lsh = DateUtils.formatDate(DateUtils.now(), "yyyyyyyMMddHHmmssS");
		
		String smsResponse = smsPortType.sms(IN0, IN1, IN2, content, mobile, lsh, DateUtils.formatDate(DateUtils.now(), "yyyyMMddHHmmss"), null, null, null, null);
		
		Map<String, String> responseMap = new HashMap<String, String>();
		for (String r : smsResponse.split("&")) {
			String[] tempArr = r.split("=");
			responseMap.put(tempArr[0], tempArr.length > 1 ? tempArr[1] : "");
		}
		System.out.println("responseMap : " + responseMap);
		return responseMap;
	}
	
	/**
     * 随机生成6位随机验证码
     */
    public static String createRandomVcode(){
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }
	
}
