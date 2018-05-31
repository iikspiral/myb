package com.sxca.myb.common.BTK.entity;

import cn.com.jit.pki.ra.vo.ApplyInfo;

import java.util.List;

/**
 * Created by admin on 2017/3/23.
 * BTK批量申请所需实体类
 */
public class BatchApplyBTK extends CertBaseBTK{
    private List<ApplyInfo> applyinfoList; //新制证书集合
    private List<String> certSNs; //证书序列号集合
    private List<String> reqSNs; //申请序列号集合
    private Boolean auditSign; //有审核权限时，即：批量申请证书所使用的模版为自动审核，指定直接审核
    private String overwriteType;  //批量申请证书时，是否覆盖原有证书（主键相同时）1：是  ，0：否
    private long notBefore; //生效时间
    private long notAfter;  //失效时间
    private Integer certValidty; //有效期

    public List<ApplyInfo> getApplyinfoList() {
        return applyinfoList;
    }

    public void setApplyinfoList(List<ApplyInfo> applyinfoList) {
        this.applyinfoList = applyinfoList;
    }

    public List<String> getCertSNs() {
        return certSNs;
    }

    public void setCertSNs(List<String> certSNs) {
        this.certSNs = certSNs;
    }

    public List<String> getReqSNs() {
        return reqSNs;
    }

    public void setReqSNs(List<String> reqSNs) {
        this.reqSNs = reqSNs;
    }

    public Boolean getAuditSign() {
        return auditSign;
    }

    public void setAuditSign(Boolean auditSign) {
        this.auditSign = auditSign;
    }

    public String getOverwriteType() {
        return overwriteType;
    }

    public void setOverwriteType(String overwriteType) {
        this.overwriteType = overwriteType;
    }

    public long getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(long notBefore) {
        this.notBefore = notBefore;
    }

    public long getNotAfter() {
        return notAfter;
    }

    public void setNotAfter(long notAfter) {
        this.notAfter = notAfter;
    }

    public Integer getCertValidty() {
        return certValidty;
    }

    public void setCertValidty(Integer certValidty) {
        this.certValidty = certValidty;
    }
}
