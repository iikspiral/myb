package com.sxca.myb.common.BTK.entity;

/**
 * BTK证书标准扩展域信息封装类
 * Created by admin on 2017/3/24.
 */
public class CertStandardExtBTK extends CertBaseBTK{
    private String reqSN;
    private String certSN;
    private String extName;
    private String childName;
    private String othernameOid;
    private String extValue;
    
    public CertStandardExtBTK(String reqSN,String certSN,String extName,String childName,String othernameOid,String extValue){
    	this.reqSN = reqSN;
    	this.certSN = certSN;
    	this.extName = extName;
    	this.childName = childName;
    	this.othernameOid = othernameOid;
    	this.extValue = extValue;
    }

    public CertStandardExtBTK(){
    	
    }
    
    public String getReqSN() {
        return reqSN;
    }

    public void setReqSN(String reqSN) {
        this.reqSN = reqSN;
    }

    public String getCertSN() {
        return certSN;
    }

    public void setCertSN(String certSN) {
        this.certSN = certSN;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getOthernameOid() {
        return othernameOid;
    }

    public void setOthernameOid(String othernameOid) {
        this.othernameOid = othernameOid;
    }

    public String getExtValue() {
        return extValue;
    }

    public void setExtValue(String extValue) {
        this.extValue = extValue;
    }
}
