package com.sxca.myb.modules.mobile.util;

import java.util.Map;

/**
 * 获取验证码返回实体
 * 
 * @author lihuilong
 * @date : 2017年4月24日 下午3:27:41
 */
public class Result {

	private String flag;// 成功标识(0:成功,1:失败)

	private String verifyCode;// 验证码

	private String subjectOrSubjectRule;// 主题或主题规则

	private String isFace;// 是否人脸(0:是,1:否)

	private String isPublic;// 是否公共项目(0:是,1:否)

	private String message;// 提示信息

	private String p7b;

	private String userId;

	private Map<String, String> certInfoMap;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getSubjectOrSubjectRule() {
		return subjectOrSubjectRule;
	}

	public void setSubjectOrSubjectRule(String subjectOrSubjectRule) {
		this.subjectOrSubjectRule = subjectOrSubjectRule;
	}

	public String getIsFace() {
		return isFace;
	}

	public void setIsFace(String isFace) {
		this.isFace = isFace;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getP7b() {
		return p7b;
	}

	public void setP7b(String p7b) {
		this.p7b = p7b;
	}

	public Map<String, String> getCertInfoMap() {
		return certInfoMap;
	}

	public void setCertInfoMap(Map<String, String> certInfoMap) {
		this.certInfoMap = certInfoMap;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
