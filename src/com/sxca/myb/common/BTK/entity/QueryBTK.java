package com.sxca.myb.common.BTK.entity;

/**
 * BTK查询类实体
 * Created by admin on 2017/3/23.
 */
public class QueryBTK extends CertBaseBTK{
    private String certSN;  //证书序列号
    private String reqSN;   //证书申请ID
    private String userInfoId;  //证书用户ID
    private Boolean isFindWay;  //是否根据时间查询
    private long optStartTime; //操作时间范围（开始）
    private long optEndTime;    //操作时间范围（结束）
    private String certType;     //证书用户类型
    private String orgId;       //组织机构ID
    private boolean isContainChildOrg;//是否查询子机构
    private String exactQuery;  //针对证书主题是否精确查询
    private String certSubject; //证书主题
    private String certCtmlName;//证书模版名称
    private String optType;     //操作类型
    private String reqStatus;   //申请状态
    private String certStatus;  //证书当前状态
    private String commonName;  //CN项内容
    private String isAdmin;     //是否管理员证书
    private String max;//查询最大数量
    private String first;//查询起始位置（证书/申请）

    public String getCertSN() {
        return certSN;
    }

    public void setCertSN(String certSN) {
        this.certSN = certSN;
    }

    public String getReqSN() {
        return reqSN;
    }

    public void setReqSN(String reqSN) {
        this.reqSN = reqSN;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Boolean getFindWay() {
        return isFindWay;
    }

    public void setFindWay(Boolean findWay) {
        isFindWay = findWay;
    }

    public long getOptStartTime() {
        return optStartTime;
    }

    public void setOptStartTime(long optStartTime) {
        this.optStartTime = optStartTime;
    }

    public long getOptEndTime() {
        return optEndTime;
    }

    public void setOptEndTime(long optEndTime) {
        this.optEndTime = optEndTime;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public boolean isContainChildOrg() {
        return isContainChildOrg;
    }

    public void setContainChildOrg(boolean containChildOrg) {
        isContainChildOrg = containChildOrg;
    }

    public String getExactQuery() {
        return exactQuery;
    }

    public void setExactQuery(String exactQuery) {
        this.exactQuery = exactQuery;
    }

    public String getCertSubject() {
        return certSubject;
    }

    public void setCertSubject(String certSubject) {
        this.certSubject = certSubject;
    }

    public String getCertCtmlName() {
        return certCtmlName;
    }

    public void setCertCtmlName(String certCtmlName) {
        this.certCtmlName = certCtmlName;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(String certStatus) {
        this.certStatus = certStatus;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
}
