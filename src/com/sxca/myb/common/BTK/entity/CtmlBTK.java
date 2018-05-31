package com.sxca.myb.common.BTK.entity;

/**
 * BTK5066模版信息封装类
 * Created by admin on 2017/3/23.
 */
public class CtmlBTK extends CertBaseBTK{
    private String ctmlId;//模版ID
    private String ctmlName;//模版名称
    private String ctmlDescription;//模版描述
    private long ctmlCreateTime;//模版创建时间
    private String ctmlStatus;//模版状态(使用中，未使用)
    private String ctmlBaseDn;//模版BaseDN
    private String ctmlAuditStatus;//模版审核状态(N:手动,Y:自动)
    private String ctmlCertType;//模版单双
    private String ctmlKeyGenPlace;//密钥位置
    private Integer ctmlKeyLength;//密钥长度
    private String ctmlKeyType;//模版类型

    public String getCtmlId() {
        return ctmlId;
    }

    public void setCtmlId(String ctmlId) {
        this.ctmlId = ctmlId;
    }

    public String getCtmlName() {
        return ctmlName;
    }

    public void setCtmlName(String ctmlName) {
        this.ctmlName = ctmlName;
    }

    public String getCtmlDescription() {
        return ctmlDescription;
    }

    public void setCtmlDescription(String ctmlDescription) {
        this.ctmlDescription = ctmlDescription;
    }

    public long getCtmlCreateTime() {
        return ctmlCreateTime;
    }

    public void setCtmlCreateTime(long ctmlCreateTime) {
        this.ctmlCreateTime = ctmlCreateTime;
    }

    public String getCtmlStatus() {
        return ctmlStatus;
    }

    public void setCtmlStatus(String ctmlStatus) {
        this.ctmlStatus = ctmlStatus;
    }

    public String getCtmlBaseDn() {
        return ctmlBaseDn;
    }

    public void setCtmlBaseDn(String ctmlBaseDn) {
        this.ctmlBaseDn = ctmlBaseDn;
    }

    public String getCtmlAuditStatus() {
        return ctmlAuditStatus;
    }

    public void setCtmlAuditStatus(String ctmlAuditStatus) {
        this.ctmlAuditStatus = ctmlAuditStatus;
    }

    public String getCtmlCertType() {
        return ctmlCertType;
    }

    public void setCtmlCertType(String ctmlCertType) {
        this.ctmlCertType = ctmlCertType;
    }

    public String getCtmlKeyGenPlace() {
        return ctmlKeyGenPlace;
    }

    public void setCtmlKeyGenPlace(String ctmlKeyGenPlace) {
        this.ctmlKeyGenPlace = ctmlKeyGenPlace;
    }

    public Integer getCtmlKeyLength() {
        return ctmlKeyLength;
    }

    public void setCtmlKeyLength(Integer ctmlKeyLength) {
        this.ctmlKeyLength = ctmlKeyLength;
    }

    public String getCtmlKeyType() {
        return ctmlKeyType;
    }

    public void setCtmlKeyType(String ctmlKeyType) {
        this.ctmlKeyType = ctmlKeyType;
    }
}
