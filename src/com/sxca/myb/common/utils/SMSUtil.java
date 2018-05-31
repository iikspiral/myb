package com.sxca.myb.common.utils;

import com.sxca.myb.modules.sms.entity.SMSPlatform;
import com.sxca.myb.common.ucpaas.restDemo.RestTest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SMSUtil {
	public static boolean sendSMS(String to, String context, List<SMSPlatform> smsPlatformList,String templateId) {
		for(SMSPlatform smsPlatform : smsPlatformList){
//			if("CCP".equals(smsPlatform.getSmsCode())){
//				if(SMSUtil.CCPSMS(to, context, smsPlatform, null)){
//					return true;
//				}
//			}
			if("UCPaaS".equals(smsPlatform.getSmsCode())){
				if(SMSUtil.UCPAASSMS(to, context, smsPlatform,templateId)){
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Response content is:
	 * {"resp":{"respCode":"000000","templateSMS":{"createDate"
	 * :"20150324104823","smsId":"973d6e19ed191797869a3637cbd00b92"}}}
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String json = "{\"resp\":{\"respCode\":\"000000\",\"templateSMS\":{\"createDate\":\"201507161103\",\"smsId\":\"f96f79240e372587e9284cd580d8f953\"}}}";
		if (getFlag(json)) {
			System.out.println("发送成功");
		}
		
	}
//    /**
//     * 第三方短信平台=容联 云资讯（CCP）
//     * 网址                                 http://www.yuntongxun.com
//     * @param to       电话号码
//     * @param context  内容
//     * @return
//     */
//	public static boolean CCPSMS(String to, String context, SMSPlatform smsPlatform, String templateId) {
//		if(smsPlatform ==null){
//			return false;
//		}
//
//		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
//		//restAPI.init("sandboxapp.cloopen.com", "8883");
//		//String accountSid = "aaf98f894e91da24014e94cd222e01ac";
//		//String token = "d73c964f53a44986b5c473f7b3fa073f";
//		//String appId = "8a48b5514e8a7522014e94cd4ef10c2c";
//
//		restAPI.init(smsPlatform.getCom(), smsPlatform.getPort());
//		//开发者信息id
//		String accountSid = smsPlatform.getAccountSid();
//		//开发者信息token
//		String token = smsPlatform.getTokenId();
//		//应用id
//		String appId = smsPlatform.getAppId();
//
//		if(templateId==null || "".equals(templateId)){
//			templateId = smsPlatform.getTemplateId();
//		}
//
//		restAPI.setAccount(accountSid, token);// 初始化主帐号名称和主帐号令牌
//		restAPI.setAppId(appId);// 初始化应用ID
//
//		HashMap<String, Object> result = restAPI.sendTemplateSMS(to, templateId, new String[] { context ,"5"});
//		if ("000000".equals(result.get("statusCode"))) {
//			return true;
//
//		} else {
//			// 异常返回输出错误码和错误信息
//			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
//			return false;
//		}
//	}
    /**
     * 第三方短信平台=云之讯（UCPaaS）
     * 网址                                    http://www.ucpaas.com/
     * @param to        电话号码
     * @param context   内容
     * @return
     */
	public static boolean UCPAASSMS(String to, String context, SMSPlatform smsPlatform, String templateId) {
		if(smsPlatform ==null){
			return false;
		}
		//开发者信息id
		String accountSid = smsPlatform.getAccountId();
		//开发者信息token
		String token = smsPlatform.getTokenId();
		//应用id
		String appId = smsPlatform.getAppId();

		return getFlag(RestTest.testTemplateSMS(true, accountSid, token, appId, templateId, to, context));
	}

	private static boolean getFlag(String res) {
		JSONObject obj;
		String code = "";
		try {
			obj = new JSONObject(res);
			code = obj.getString("resp");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		try {
			obj = new JSONObject(code);
			code = obj.getString("respCode");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		if (code.equals("000000")) {
			return true;
		}else{
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + code);
			return false;
		}
	}
}
