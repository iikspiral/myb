package com.sxca.myb.common.config;

public class ApplyConstants extends Global{

	/**
     * 支付方式 (微信支付、银联支付、支付宝)
     */
    public static String PAY_METHOD_1 = "1";        // 1 微信支付
    public static String PAY_METHOD_2 = "2";        // 2 银联在线(企业)
    public static String PAY_METHOD_3 = "3";        // 3 银联在线(个人)
    public static String PAY_METHOD_4 = "4";        // 4 支付宝
    
    /**
     * 支付状态(1待支付、2支付成功、3支付失败)
     */
    public static String APPLY_PAY_STATUS_1 = "1";        // 1 待支付
    public static String APPLY_PAY_STATUS_2 = "2";        // 2 支付成功
    public static String APPLY_PAY_STATUS_3 = "3";        // 3 支付失败

    /**
     * 用户类型
     */
    public static String USERTYPE_USERINFO = "userinfo";        // 个人用户
    public static String USERTYPE_CORPORATION_INFO = "corporation_info";        // 企业用户

    /**
     * 个人用户类型
     */
    public static String USERINFO_NORMAL = "0";        // 正常个人用户
    public static String USERINFO_OTHER = "1";        // 散户

    /**
     * 密钥宝服务平台操作类型
     */
    public static String OPTTYPE_ADD = "0";        // 新制申请
    public static String OPTTYPE_ALTER = "1";        // 变更申请
    public static String OPTTYPE_OTHER_UPDATE = "2";        // 更新申请
    public static String OPTTYPE_OTHER_REVOKE = "3";        // 注销申请
    public static String OPTTYPE_OTHER_REISSUE = "4";        // 补发申请
    public static String OPTTYPE_OTHER_FORGET_PIN = "5";        // 忘记密码申请
    public static String OPTTYPE_OTHER_HOLD = "6";        // 冻结申请
    public static String OPTTYPE_OTHER_UNHOLD = "7";        // 解冻申请
    
    /**
     * 密钥宝服务平台RA操作类型
     */
    public static String RA_OPTTYPE_ADD = "1";        		// 新制申请
    public static String RA_OPTTYPE_REVOKE = "2";        	// 注销申请
    public static String RA_OPTTYPE_HOLD = "3";        		// 冻结申请
    public static String RA_OPTTYPE_UNHOLD = "4";        	// 解冻申请
    public static String RA_OPTTYPE_UPDATE = "5";        	// 更新申请
    
    /**
     * 密钥宝服务平台证书申请业务结果
     */
    public static String BUSINESS_OK = "0";        // 完成
    public static String BUSINESS_FAIL = "1";      // 未完成

    /**
     * 密钥宝服务平台是否默认项目
     */
    public static String ISDEAFULT_PRO_YES = "0";        // 是默认项目
    public static String ISDEAFULT_PRO_NO = "1";      // 不是默认项目
    
    public static String getApplyTypeName(String applyType) {
    	
    	String applyTypeName = null;
    	if(applyType.equals(OPTTYPE_ADD)) {
    		applyTypeName = "新制申请";
    	}else if(applyType.equals(OPTTYPE_ALTER)) {
    		applyTypeName = "变更申请";
    	}else if(applyType.equals(OPTTYPE_OTHER_UPDATE)) {
    		applyTypeName = "更新申请";
    	}else if(applyType.equals(OPTTYPE_OTHER_REVOKE)) {
    		applyTypeName = "注销申请";
    	}else if(applyType.equals(OPTTYPE_OTHER_REISSUE)) {
    		applyTypeName = "补发申请";
    	}else if(applyType.equals(OPTTYPE_OTHER_FORGET_PIN)) {
    		applyTypeName = "忘记密码申请";
    	}
		return applyTypeName;
    	
    }
}
