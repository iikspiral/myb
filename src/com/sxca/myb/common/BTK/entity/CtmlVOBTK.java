package com.sxca.myb.common.BTK.entity;

import java.util.List;

/**
 * BTK模版信息集合
 * Created by admin on 2017/3/28.
 */
public class CtmlVOBTK {
    private CtmlBTK ctmlBTK;
    private List<CtmlSelfExtBTK> ctmlSelfExtBTKs;
    private List<CtmlStandardExtBTK> ctmlStandardExtBTKs;
    private List<List<CtmlStandardExtBTK>> ctmlStandardExtBTKLists;

    public CtmlBTK getCtmlBTK() {
        return ctmlBTK;
    }

    public void setCtmlBTK(CtmlBTK ctmlBTK) {
        this.ctmlBTK = ctmlBTK;
    }

    public List<CtmlSelfExtBTK> getCtmlSelfExtBTKs() {
        return ctmlSelfExtBTKs;
    }

    public void setCtmlSelfExtBTKs(List<CtmlSelfExtBTK> ctmlSelfExtBTKs) {
        this.ctmlSelfExtBTKs = ctmlSelfExtBTKs;
    }

    public List<CtmlStandardExtBTK> getCtmlStandardExtBTKs() {
        return ctmlStandardExtBTKs;
    }

    public void setCtmlStandardExtBTKs(List<CtmlStandardExtBTK> ctmlStandardExtBTKs) {
        this.ctmlStandardExtBTKs = ctmlStandardExtBTKs;
    }

    public List<List<CtmlStandardExtBTK>> getCtmlStandardExtBTKLists() {
        return ctmlStandardExtBTKLists;
    }

    public void setCtmlStandardExtBTKLists(List<List<CtmlStandardExtBTK>> ctmlStandardExtBTKLists) {
        this.ctmlStandardExtBTKLists = ctmlStandardExtBTKLists;
    }
}
