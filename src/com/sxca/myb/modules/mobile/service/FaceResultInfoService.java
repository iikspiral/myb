/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.mobile.service;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.mobile.dao.FaceInfoDao;
import com.sxca.myb.modules.mobile.dao.FaceResultInfoDao;
import com.sxca.myb.modules.mobile.entity.FaceInfo;
import com.sxca.myb.modules.mobile.entity.FaceResultInfo;
import org.springframework.stereotype.Service;


/**
 * 人脸信息Service
 * @author glq
 * @version 2017-05-11
 */
@Service
public class FaceResultInfoService extends CrudService<FaceResultInfoDao, FaceResultInfo> {

    public void createFaceResultInfo(String name, String phoneNum, String idCard, String num, String result, String picString) {
        FaceResultInfo faceResultInfo = new FaceResultInfo();
        faceResultInfo.setIdcard(idCard);
        faceResultInfo.setName(name);
        faceResultInfo.setPhoneNum(phoneNum);
        faceResultInfo.setNum(num);
        faceResultInfo.setResult(result);
        faceResultInfo.setPicString(picString);
        super.insert(faceResultInfo);
    }


}
