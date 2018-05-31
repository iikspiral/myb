package com.sxca.myb.common.BTK.entity;

/**
 * BTK证书自定义扩展域信息封装类
 * Created by admin on 2017/3/24.
 */
public class CertSelfExtBTK extends CertBaseBTK{
    private String reqSN;
    private String certSn;
    private String selfExtOid;
    private String selfExtName;
    private String selfExtValue;
    
    public CertSelfExtBTK(){
    	
    }
    public CertSelfExtBTK(String reqSN,String certSn,String selfExtOid,String selfExtName,String selfExtValue){
    	this.reqSN = reqSN;
    	this.certSn = certSn;
    	this.selfExtOid = selfExtOid;
    	this.selfExtName = selfExtName;
    	this.selfExtValue = selfExtValue;
    }

    public String getReqSN() {
        return reqSN;
    }

    public void setReqSN(String reqSN) {
        this.reqSN = reqSN;
    }

    public String getCertSn() {
        return certSn;
    }

    public void setCertSn(String certSn) {
        this.certSn = certSn;
    }

    public String getSelfExtOid() {
        return selfExtOid;
    }

    public void setSelfExtOid(String selfExtOid) {
        this.selfExtOid = selfExtOid;
    }

    public String getSelfExtName() {
        return selfExtName;
    }

    public void setSelfExtName(String selfExtName) {
        this.selfExtName = selfExtName;
    }

    public String getSelfExtValue() {
        return selfExtValue;
    }

    public void setSelfExtValue(String selfExtValue) {
        this.selfExtValue = selfExtValue;
    }
}
