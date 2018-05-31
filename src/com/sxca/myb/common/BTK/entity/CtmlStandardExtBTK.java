package com.sxca.myb.common.BTK.entity;

/**
 * BTK模版标准扩展域信息封装类
 * Created by admin on 2017/3/28.
 */
public class CtmlStandardExtBTK {
    private String ctmlStandardExtId;
    private String ctmlId;
    private String ctmlExtName;
    private String ctmlExtChildName;

    public CtmlStandardExtBTK(String ctmlId, String ctmlExtName, String ctmlExtChildName) {
        this.ctmlId = ctmlId;
        this.ctmlExtName = ctmlExtName;
        this.ctmlExtChildName = ctmlExtChildName;
    }

    public String getCtmlStandardExtId() {
        return ctmlStandardExtId;
    }

    public void setCtmlStandardExtId(String ctmlStandardExtId) {
        this.ctmlStandardExtId = ctmlStandardExtId;
    }

    public String getCtmlId() {
        return ctmlId;
    }

    public void setCtmlId(String ctmlId) {
        this.ctmlId = ctmlId;
    }

    public String getCtmlExtName() {
        return ctmlExtName;
    }

    public void setCtmlExtName(String ctmlExtName) {
        this.ctmlExtName = ctmlExtName;
    }

    public String getCtmlExtChildName() {
        return ctmlExtChildName;
    }

    public void setCtmlExtChildName(String ctmlExtChildName) {
        this.ctmlExtChildName = ctmlExtChildName;
    }
}
