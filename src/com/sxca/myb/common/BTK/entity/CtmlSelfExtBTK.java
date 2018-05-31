package com.sxca.myb.common.BTK.entity;

/**
 * BTK模版自定义扩展域信息封装类
 * Created by admin on 2017/3/28.
 */
public class CtmlSelfExtBTK {
    private String ctmlSelfExtId;
    private String ctmlId;
    private String ctmlSelfExtName;
    private String ctmlSelfExtOid;

    public CtmlSelfExtBTK(String ctmlId, String ctmlSelfExtName, String ctmlSelfExtOid) {
        this.ctmlId = ctmlId;
        this.ctmlSelfExtName = ctmlSelfExtName;
        this.ctmlSelfExtOid = ctmlSelfExtOid;
    }

    public String getCtmlSelfExtId() {
        return ctmlSelfExtId;
    }

    public void setCtmlSelfExtId(String ctmlSelfExtId) {
        this.ctmlSelfExtId = ctmlSelfExtId;
    }

    public String getCtmlId() {
        return ctmlId;
    }

    public void setCtmlId(String ctmlId) {
        this.ctmlId = ctmlId;
    }

    public String getCtmlSelfExtName() {
        return ctmlSelfExtName;
    }

    public void setCtmlSelfExtName(String ctmlSelfExtName) {
        this.ctmlSelfExtName = ctmlSelfExtName;
    }

    public String getCtmlSelfExtOid() {
        return ctmlSelfExtOid;
    }

    public void setCtmlSelfExtOid(String ctmlSelfExtOid) {
        this.ctmlSelfExtOid = ctmlSelfExtOid;
    }
}
