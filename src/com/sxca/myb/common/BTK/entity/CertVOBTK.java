package com.sxca.myb.common.BTK.entity;

import java.util.List;

/**
 * BTK证书申请信息集合
 * Created by admin on 2017/3/23.
 */
public class CertVOBTK extends CertBaseBTK{
    private CertApplyBTK certApplyBTK;//证书申请信息
    private UserBTK userBTK;//证书个人用户信息
    private CorporationBTK corporationBTK;//证书企业用户信息
    private List<CertStandardExtBTK> certStandardExtBTKList;//证书标准扩展域
    private List<CertSelfExtBTK> certSelfExtBTKList;//证书自定义扩展域


    public CertApplyBTK getCertApplyBTK() {
        return certApplyBTK;
    }

    public void setCertApplyBTK(CertApplyBTK certApplyBTK) {
        this.certApplyBTK = certApplyBTK;
    }

    public UserBTK getUserBTK() {
        return userBTK;
    }

    public void setUserBTK(UserBTK userBTK) {
        this.userBTK = userBTK;
    }

    public CorporationBTK getCorporationBTK() {
        return corporationBTK;
    }

    public void setCorporationBTK(CorporationBTK corporationBTK) {
        this.corporationBTK = corporationBTK;
    }

    public List<CertStandardExtBTK> getCertStandardExtBTKList() {
        return certStandardExtBTKList;
    }

    public void setCertStandardExtBTKList(List<CertStandardExtBTK> certStandardExtBTKList) {
        this.certStandardExtBTKList = certStandardExtBTKList;
    }

    public List<CertSelfExtBTK> getCertSelfExtBTKList() {
        return certSelfExtBTKList;
    }

    public void setCertSelfExtBTKList(List<CertSelfExtBTK> certSelfExtBTKList) {
        this.certSelfExtBTKList = certSelfExtBTKList;
    }
}
