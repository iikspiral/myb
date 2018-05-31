package com.sxca.myb.common.BTK.impl;

import cn.com.jit.pki.core.Response;
import cn.com.jit.pki.core.entity.Ctml;
import cn.com.jit.pki.core.entity.extension.ExtenSet;
import cn.com.jit.pki.core.entity.extension.IExtension;
import cn.com.jit.pki.core.entity.extension.x509impl.*;
import cn.com.jit.pki.core.entity.policy.IPolicy;
import cn.com.jit.pki.core.entity.policy.PolicySet;
import cn.com.jit.pki.core.entity.policy.basepolicy.CtmlAttributePolicy;
import cn.com.jit.pki.core.entity.policy.basepolicy.KeyPolicy;
import cn.com.jit.pki.core.entity.policy.extenpolicy.*;
import cn.com.jit.pki.ra.btk.Business;
import cn.com.jit.pki.ra.btk.ConnConfig;
import cn.com.jit.pki.ra.cert.request.addapply.*;
import cn.com.jit.pki.ra.cert.request.auditapply.*;
import cn.com.jit.pki.ra.cert.request.certmake.CertMakeRequest;
import cn.com.jit.pki.ra.cert.request.certmake.ExtendValidAndDownRequest;
import cn.com.jit.pki.ra.cert.request.deleteapply.BatchDeleteApplyRequest;
import cn.com.jit.pki.ra.cert.request.deleteapply.DeleteApplyRequest;
import cn.com.jit.pki.ra.cert.request.modifyapply.CertApplyModifyRequest;
import cn.com.jit.pki.ra.cert.request.modifyapply.CertRevokeApplyModifyRequest;
import cn.com.jit.pki.ra.cert.request.modifyapply.CertUpdateApplyModifyRequest;
import cn.com.jit.pki.ra.cert.request.query.CertApplyQueryRequest;
import cn.com.jit.pki.ra.cert.request.query.CertApplySingleQueryRequest;
import cn.com.jit.pki.ra.cert.request.query.CertQueryRequest;
import cn.com.jit.pki.ra.cert.request.query.CertSingleQueryRequest;
import cn.com.jit.pki.ra.cert.response.addapply.*;
import cn.com.jit.pki.ra.cert.response.auditapply.*;
import cn.com.jit.pki.ra.cert.response.certmake.CertMakeResponse;
import cn.com.jit.pki.ra.cert.response.certmake.ExtendValidAndDownResponse;
import cn.com.jit.pki.ra.cert.response.deleteapply.BatchDeleteApplyResponse;
import cn.com.jit.pki.ra.cert.response.deleteapply.DeleteApplyResponse;
import cn.com.jit.pki.ra.cert.response.modifyapply.CertApplyModifyResponse;
import cn.com.jit.pki.ra.cert.response.modifyapply.CertRevokeApplyModifyResponse;
import cn.com.jit.pki.ra.cert.response.modifyapply.CertUpdateApplyModifyResponse;
import cn.com.jit.pki.ra.cert.response.query.CertApplyQueryResponse;
import cn.com.jit.pki.ra.cert.response.query.CertApplySingleQueryResponse;
import cn.com.jit.pki.ra.cert.response.query.CertQueryResponse;
import cn.com.jit.pki.ra.cert.response.query.CertSingleQueryResponse;
import cn.com.jit.pki.ra.ctml.request.CtmlQueryFromRaRequest;
import cn.com.jit.pki.ra.ctml.response.CtmlQueryFromRaResponse;
import cn.com.jit.pki.ra.ctml.vo.CtmlInfoBean;
import cn.com.jit.pki.ra.user.request.*;
import cn.com.jit.pki.ra.user.response.*;

import com.sxca.myb.common.BTK.btkinterface.BTK;
import com.sxca.myb.common.BTK.entity.*;
import com.sxca.myb.common.config.Global;
import com.sxca.myb.common.utils.SystemPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author Wang Jiyu
 * BTK工具类包装，与RA通信操作与证书有关的所有请求
 * BTK5066版本实现类
 * Created by admin on 2017/2/28.
 * */

@Service("btk")
public class BTKImpl5066 implements BTK {
	protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 获取btk配置
     * @return
     * @throws Exception
     */
    private Business getBusiness() throws Exception{
        ConnConfig connConfig = new ConnConfig();
        connConfig.setServer(Global.getConfig("raIp"),Integer.valueOf(Global.getConfig("raPort")));
        //connConfig.setUserKeyFile_JKS("E:/IdeaSpaces/dcismp/src/main/resources/btk/dcims.jks","111111".toCharArray());
        String commPwd = Global.getConfig("commPwd");
        connConfig.setUserKeyFile_DAT(SystemPath.getPath()+"SM2ServerKey.dat",commPwd.toCharArray());
        logger.warn(SystemPath.getPath()+"SM2ServerKey.dat");
        connConfig.setDeviceID("JSOFT_LIB");
        //Business business = new Business(connConfig);
        Business business = new Business(connConfig,"0");
        //business.setLicenseFileName("E:/IdeaSpaces/dcismp/src/main/resources/btk/BTK-20131126.lce");
        business.setLicenseFileName(SystemPath.getPath()+"license3.lce");
        logger.warn(SystemPath.getPath()+"license3.lce");
        System.setProperty("commType", "3DES");
        return business;
    }

    /**
     * 判断与RA是否连通
     * @param userinfoId
     * @param userType
     * @return
     */
    @Override
    public boolean userinfoQuery(String userinfoId, String userType) {
        UserInfoSingleQueryRequest userInfoSingleQueryRequest = new UserInfoSingleQueryRequest();
        userInfoSingleQueryRequest.setUserinfoId(userinfoId);
        userInfoSingleQueryRequest.setTableName(userType);
        try {
            Response response;
            response = new BTKImpl5066().getBusiness().doBusiness(userInfoSingleQueryRequest);
            UserInfoSingleQueryResponse userInfoSingleQueryResponse = (UserInfoSingleQueryResponse)response;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取模版信息
     */
    @Override
    public List<CtmlVOBTK> certCtmlQuery(){
        List<CtmlVOBTK> ctmlVOBTKs = new ArrayList<CtmlVOBTK>();
        CtmlQueryFromRaRequest ctmlQueryFromRaRequest = new CtmlQueryFromRaRequest();
        Response response;
        try {
            response = getBusiness().doBusiness(ctmlQueryFromRaRequest);
            CtmlQueryFromRaResponse ctmlQueryFromRaResponse = (CtmlQueryFromRaResponse)response;
            if(ctmlQueryFromRaResponse!=null){
                if("0".equals(ctmlQueryFromRaResponse.getErr())){
                    List<CtmlInfoBean> beans = ctmlQueryFromRaResponse.getCtmlInfoBeanList();
                    if(beans!=null&&beans.size()>0){
                        for (CtmlInfoBean ctmlInfoBean : beans) {
                            CtmlVOBTK ctmlVOBTK = new CtmlVOBTK();
                            //模版基本信息
                            CtmlBTK ctmlBTK = new CtmlBTK();
                            Ctml ctml = ctmlInfoBean.getCtml();
                            ctmlBTK.setCtmlId(ctml.getId());
                            ctmlBTK.setCtmlName(ctml.getName());
                            ctmlBTK.setCtmlDescription(ctml.getDescription());
                            ctmlBTK.setCtmlCreateTime(ctml.getCreateTime());
                            ctmlBTK.setCtmlStatus(ctml.getStatus());
                            ctmlBTK.setCtmlBaseDn(ctmlInfoBean.getBaseDnList().get(0));
                            ctmlBTK.setCtmlAuditStatus(ctmlInfoBean.getAuditPolicy());
                            PolicySet set=ctmlInfoBean.getCtml().getPolicySet();
                            CtmlAttributePolicy ctmlAttribute=(CtmlAttributePolicy) set.getPolicy("CtmlAttributePolicy");
                            ctmlBTK.setCtmlCertType(ctmlAttribute.getCertType());
                            KeyPolicy keypolicy=(KeyPolicy) set.getPolicy("KeyPolicy");
                            ctmlBTK.setCtmlKeyGenPlace(keypolicy.getKeyGenPlace());
                            ctmlBTK.setCtmlKeyLength(keypolicy.getKeyLength());
                            ctmlBTK.setCtmlKeyType(keypolicy.getKeyType());
                            ctmlVOBTK.setCtmlBTK(ctmlBTK);

                            //自定义扩展域信息
                            IPolicy[] self=set.getSelfExtenPolicy();
                            if(self!=null&&self.length>0){
                                List<CtmlSelfExtBTK> ctmlSelfExtBTKs=new ArrayList<CtmlSelfExtBTK>();
                                for(IPolicy policy:self){
                                    SelfDefPolicy selfPolicy=(SelfDefPolicy) policy;
                                    CtmlSelfExtBTK selfExt=new CtmlSelfExtBTK(ctml.getId(), selfPolicy.getName(), selfPolicy.getOID());
                                    ctmlSelfExtBTKs.add(selfExt);
                                }
                                ctmlVOBTK.setCtmlSelfExtBTKs(ctmlSelfExtBTKs);
                            }
                            //标准扩展域信息
                            IPolicy[] standard=set.getStdExtenPolicy();
                            if(standard!=null&&standard.length>0){
                                List<CtmlStandardExtBTK> ctmlStandardExtBTKs=new ArrayList<CtmlStandardExtBTK>();
                                for(IPolicy policy:standard){
                                    if(policy instanceof IdentifyCodePolicy){
                                        IdentifyCodePolicy identify=(IdentifyCodePolicy) policy;
                                        Hashtable ht=identify.getNumTable();
                                        for(Iterator itr = ht.keySet().iterator(); itr.hasNext();){
                                            String key = (String) itr.next();
                                            CtmlStandardExtBTK ctmlStandardExtBTK=new CtmlStandardExtBTK(ctml.getId(),policy.getName(), key);
                                            ctmlStandardExtBTKs.add(ctmlStandardExtBTK);
                                        }
                                    }
                                    if(policy instanceof InsuranceNumPolicy){
                                        CtmlStandardExtBTK ctmlStandardExtBTK=new CtmlStandardExtBTK(ctml.getId(),policy.getName(), policy.getName());
                                        ctmlStandardExtBTKs.add(ctmlStandardExtBTK);
                                    }
                                    if(policy instanceof ICRegNumPolicy){
                                        CtmlStandardExtBTK ctmlStandardExtBTK=new CtmlStandardExtBTK(ctml.getId(),policy.getName(), policy.getName());
                                        ctmlStandardExtBTKs.add(ctmlStandardExtBTK);
                                    }
                                    if(policy instanceof OrganCodePolicy){
                                        CtmlStandardExtBTK ctmlStandardExtBTK=new CtmlStandardExtBTK(ctml.getId(),policy.getName(), policy.getName());
                                        ctmlStandardExtBTKs.add(ctmlStandardExtBTK);
                                    }
                                    if(policy instanceof TaxationNumPolicy){
                                        CtmlStandardExtBTK ctmlStandardExtBTK=new CtmlStandardExtBTK(ctml.getId(),policy.getName(), policy.getName());
                                        ctmlStandardExtBTKs.add(ctmlStandardExtBTK);
                                    }
                                    if(policy instanceof SubjectAltNameExtPolicy){
                                        SubjectAltNameExtPolicy identify=(SubjectAltNameExtPolicy) policy;
                                        Hashtable ht=identify.getNumTable();
                                        for(Iterator itr = ht.keySet().iterator(); itr.hasNext();){
                                            String key = (String) itr.next();
                                            CtmlStandardExtBTK ctmlStandardExtBTK=new CtmlStandardExtBTK(ctml.getId(),policy.getName(), key);
                                            ctmlStandardExtBTKs.add(ctmlStandardExtBTK);
                                        }
                                    }
                                }
                                ctmlVOBTK.setCtmlStandardExtBTKs(ctmlStandardExtBTKs);
                            }
                            ctmlVOBTKs.add(ctmlVOBTK);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ctmlVOBTKs;
    }

    /**
     * 证书新制申请
     * @param certVOBTK
     * @return
     */
    @Override
    public CertApplyAddResponse certApplyAdd(CertVOBTK certVOBTK){
        CertApplyAddRequest certApplyAddRequest = new CertApplyAddRequest();
        //是否自主申请（是：移动证书申请，否：普通证书申请）
        //certApplyAddRequest.setSelfApply(isSelfApply);
        //证书申请基本信息
        CertApplyBTK certApplyBTK = certVOBTK.getCertApplyBTK();
        certApplyAddRequest.setSubject(certApplyBTK.getCertSubject());
        certApplyAddRequest.setCtmlName(certApplyBTK.getCtmlName());
        certApplyAddRequest.setIsAdmin(certApplyBTK.getIsAdmin());
        certApplyAddRequest.setCertType(certApplyBTK.getCertType());
        certApplyAddRequest.setNotBefore(certApplyBTK.getNotBefore().longValue());
        certApplyAddRequest.setNotAfter(certApplyBTK.getNotAfter().longValue());
        certApplyAddRequest.setValidity(certApplyBTK.getCertValidity());
        certApplyAddRequest.setUserinfoId(certApplyBTK.getUserInfoId());
        certApplyAddRequest.setOrganId(certApplyBTK.getOrganId());
        //证书用户信息
        Map<String, String> props_temp = getUserOrCorporation(certApplyBTK.getCertType(),certVOBTK);
        certApplyAddRequest.setUserMap(props_temp);
        certApplyAddRequest.setTableName(certApplyBTK.getCertType());
        //证书扩展域信息
        ExtenSet extenSet = new ExtenSet();
        SelfDefExtImpl selfExtServ = new SelfDefExtImpl();
        if(certVOBTK.getCertSelfExtBTKList()!=null&&certVOBTK.getCertSelfExtBTKList().size()>0){
            for(CertSelfExtBTK certSelfExtBTK:certVOBTK.getCertSelfExtBTKList()){
                selfExtServ.setCritical(false);
                selfExtServ.setName(certSelfExtBTK.getSelfExtName());
                selfExtServ.setExtensionValue(certSelfExtBTK.getSelfExtValue());
                selfExtServ.setOID(certSelfExtBTK.getSelfExtOid());
                selfExtServ.setType(IExtension.TYPE_SELF_EXTEN);
                extenSet.addExten(selfExtServ);
            }
        }
        IdentifyCodeExtImpl identify=new IdentifyCodeExtImpl();
        SubjectAltNameExtImpl subject=new SubjectAltNameExtImpl();
        if(certVOBTK.getCertStandardExtBTKList()!=null&&certVOBTK.getCertStandardExtBTKList().size()>0){
            for(CertStandardExtBTK certStandardExtBTK:certVOBTK.getCertStandardExtBTKList()){
                if(certStandardExtBTK.getExtName().equals("IdentifyCodePolicy")){
                    if(certStandardExtBTK.getChildName().equals("ResidenterCard")){
                        identify.setIdCardNum(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("Passport")){
                        identify.setPassportNum(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("MilitaryOfficerCard")){
                        identify.setMiliOfficerCardNum(certStandardExtBTK.getExtValue());
                    }
                }
                if(certStandardExtBTK.getExtName().equals("InsuranceNumPolicy")){
                    InsuranceNumExtImpl insurance=new InsuranceNumExtImpl();
                    insurance.setInsuranceNum(certStandardExtBTK.getExtValue());
                    extenSet.addExten(insurance);
                }
                if(certStandardExtBTK.getExtName().equals("ICRegNumPolicy")){
                    ICRegNumberExtImpl icreg=new ICRegNumberExtImpl();
                    icreg.setIcRegNum(certStandardExtBTK.getExtValue());
                    extenSet.addExten(icreg);
                }
                if(certStandardExtBTK.getExtName().equals("OrganCodePolicy")){
                    OrganCodeExtImpl organ=new OrganCodeExtImpl();
                    organ.setOrganCode(certStandardExtBTK.getExtValue());
                    extenSet.addExten(organ);
                }
                if(certStandardExtBTK.getExtName().equals("TaxationNumPolicy")){
                    TaxationNumExtImpl taxation=new TaxationNumExtImpl();
                    taxation.setTaxationNum(certStandardExtBTK.getExtValue());
                    extenSet.addExten(taxation);
                }
                if(certStandardExtBTK.getExtName().equals("SubjectAltNameExtPolicy")){
                    if(certStandardExtBTK.getChildName().equals("dnName")){
                        subject.setDnName(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("dnsName")){
                        subject.setDnsName(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("email")){
                        subject.setEmail(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("ipAddress")){
                        subject.setIpAddress(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("uriName")){
                        subject.setUriName(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("otherName")){
                        subject.setOtherName(certStandardExtBTK.getExtValue());
                        subject.setOtherNameOid(certStandardExtBTK.getOthernameOid());
                    }
                }
            }
            extenSet.addExten(identify);
            extenSet.addExten(subject);
        }
        certApplyAddRequest.getApplyInfo().setEncExtenSet(extenSet);
        certApplyAddRequest.getApplyInfo().setSigExtenSet(extenSet);
        Response response;
        CertApplyAddResponse certApplyAddResponse = null;


        try {
            response = getBusiness().doBusiness(certApplyAddRequest);
            certApplyAddResponse = (CertApplyAddResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certApplyAddResponse;
    }



    /**
     * 审核证书新制申请
     * @param reqSN //申请序列号
     * @param reason //拒绝原因
     * @param pass   //是否通过审核
     * @return
     */
    @Override
    public CertApplyAuditResponse certApplyAudit(String reqSN,Boolean pass,String reason){
        CertApplyAuditRequest certApplyAuditRequest = new CertApplyAuditRequest();
        certApplyAuditRequest.setReqSN(reqSN);
        certApplyAuditRequest.setRefuseReason(reason);
        certApplyAuditRequest.setAuditPass(pass);
        CertApplyAuditResponse certApplyAuditResponse = null;
        try {
            Response response;
            response = getBusiness().doBusiness(certApplyAuditRequest);
            certApplyAuditResponse = (CertApplyAuditResponse) response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certApplyAuditResponse;
    }

    /**
     * 修改证书新制申请
     * @param certVOBTK
     * @return
     */
    @Override
    public CertApplyModifyResponse certApplyModify(CertVOBTK certVOBTK){
        CertApplyModifyRequest certApplyModifyRequest = new CertApplyModifyRequest();
        CertApplyBTK certApplyBTK = certVOBTK.getCertApplyBTK();
        certApplyModifyRequest.setReqSN(certApplyBTK.getReqSn());
        certApplyModifyRequest.setSubject(certApplyBTK.getCertSubject());
        certApplyModifyRequest.setTableName(certApplyBTK.getCertType());
        certApplyModifyRequest.setOrganId(certApplyBTK.getOrganId());
        certApplyModifyRequest.setCtmlName(certApplyBTK.getCtmlName());
        certApplyModifyRequest.setNotBefore(certApplyBTK.getNotBefore().longValue());
        certApplyModifyRequest.setNotAfter(certApplyBTK.getNotAfter().longValue());
        certApplyModifyRequest.setValidity(certApplyBTK.getCertValidity());
        //是否变更证书主题
        certApplyModifyRequest.setIsupdateDN(certApplyBTK.getUpdateDN());
        //修改用户信息
        certApplyModifyRequest.setUserInfoId(certApplyBTK.getUserInfoId());
        Map<String, String> props_temp = getUserOrCorporation(certApplyBTK.getCertType(),certVOBTK);
        certApplyModifyRequest.setUserMap(props_temp);
        //证书扩展信息
        ExtenSet extenSet = new ExtenSet();
        if(certVOBTK.getCertSelfExtBTKList()!=null&&certVOBTK.getCertSelfExtBTKList().size()>0){
            SelfDefExtImpl selfExtServ = new SelfDefExtImpl();
            for(CertSelfExtBTK certSelfExtBTK:certVOBTK.getCertSelfExtBTKList()){
                selfExtServ.setCritical(false);
                selfExtServ.setName(certSelfExtBTK.getSelfExtName());
                selfExtServ.setExtensionValue(certSelfExtBTK.getSelfExtValue());
                selfExtServ.setOID(certSelfExtBTK.getSelfExtOid());
                selfExtServ.setType(IExtension.TYPE_SELF_EXTEN);
                extenSet.addExten(selfExtServ);
            }
        }
        if(certVOBTK.getCertStandardExtBTKList()!=null&&certVOBTK.getCertStandardExtBTKList().size()>0){
            IdentifyCodeExtImpl identify=new IdentifyCodeExtImpl();
            SubjectAltNameExtImpl subject=new SubjectAltNameExtImpl();
            for(CertStandardExtBTK certStandardExtBTK:certVOBTK.getCertStandardExtBTKList()){
                if(certStandardExtBTK.getExtName().equals("IdentifyCodePolicy")){
                    if(certStandardExtBTK.getChildName().equals("ResidenterCard")){
                        identify.setIdCardNum(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("Passport")){
                        identify.setPassportNum(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("MilitaryOfficerCard")){
                        identify.setMiliOfficerCardNum(certStandardExtBTK.getExtValue());
                    }
                }
                if(certStandardExtBTK.getExtName().equals("InsuranceNumPolicy")){
                    InsuranceNumExtImpl insurance=new InsuranceNumExtImpl();
                    insurance.setInsuranceNum(certStandardExtBTK.getExtValue());
                    extenSet.addExten(insurance);
                }
                if(certStandardExtBTK.getExtName().equals("ICRegNumPolicy")){
                    ICRegNumberExtImpl icreg=new ICRegNumberExtImpl();
                    icreg.setIcRegNum(certStandardExtBTK.getExtValue());
                    extenSet.addExten(icreg);
                }
                if(certStandardExtBTK.getExtName().equals("OrganCodePolicy")){
                    OrganCodeExtImpl organ=new OrganCodeExtImpl();
                    organ.setOrganCode(certStandardExtBTK.getExtValue());
                    extenSet.addExten(organ);
                }
                if(certStandardExtBTK.getExtName().equals("TaxationNumPolicy")){
                    TaxationNumExtImpl taxation=new TaxationNumExtImpl();
                    taxation.setTaxationNum(certStandardExtBTK.getExtValue());
                    extenSet.addExten(taxation);
                }
                if(certStandardExtBTK.getExtName().equals("SubjectAltNameExtPolicy")){
                    if(certStandardExtBTK.getChildName().equals("dnName")){
                        subject.setDnName(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("dnsName")){
                        subject.setDnsName(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("email")){
                        subject.setEmail(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("ipAddress")){
                        subject.setIpAddress(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("uriName")){
                        subject.setUriName(certStandardExtBTK.getExtValue());
                    }
                    if(certStandardExtBTK.getChildName().equals("otherName")){
                        subject.setOtherName(certStandardExtBTK.getExtValue());
                        subject.setOtherNameOid(certStandardExtBTK.getOthernameOid());
                    }
                }
            }
            extenSet.addExten(identify);
            extenSet.addExten(subject);
        }
        certApplyModifyRequest.getApplyInfo().setEncExtenSet(extenSet);
        certApplyModifyRequest.getApplyInfo().setSigExtenSet(extenSet);
        CertApplyModifyResponse certApplyModifyResponse = null;
        try {
            Response response = getBusiness().doBusiness(certApplyModifyRequest);
            certApplyModifyResponse = (CertApplyModifyResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certApplyModifyResponse;
    }

    /**
     * 证书下载
     * @param certMakeBTK
     * @return
     */
    @Override
    public CertMakeResponse downloadCert(CertMakeBTK certMakeBTK){
        CertMakeRequest certMakeRequest = new CertMakeRequest();
        certMakeRequest.setCheckAuthcode(certMakeBTK.getCheckAuthcode());
        certMakeRequest.setAuthCode(certMakeBTK.getAuthCode());
        certMakeRequest.setRefCode(certMakeBTK.getRefCode());
        certMakeRequest.setP10(certMakeBTK.getP10());
        certMakeRequest.setIsRetainKey(certMakeBTK.getIsRetainKey());
        certMakeRequest.setDoubleP10(certMakeBTK.getDoubleP10());
        CertMakeResponse certMakeResponse = null;
        try {
            Response response = getBusiness().doBusiness(certMakeRequest);
            certMakeResponse = (CertMakeResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certMakeResponse;
    }

    /**
     * 证书更新申请
     * @param certApplyBTK
     * @return
     */
    @Override
    public CertUpdateApplyAddResponse addCertUpdateOnlineApply(CertApplyBTK certApplyBTK){
        CertUpdateApplyAddRequest certUpdateApplyAddRequest = new CertUpdateApplyAddRequest();
        certUpdateApplyAddRequest.setCertSN(certApplyBTK.getCertSn());
        certUpdateApplyAddRequest.setSubject(certApplyBTK.getCertSubject());
        certUpdateApplyAddRequest.setCtmlName(certApplyBTK.getCtmlName());
        certUpdateApplyAddRequest.setNotBefore(certApplyBTK.getNotBefore().longValue());
        certUpdateApplyAddRequest.setNotAfter(certApplyBTK.getNotAfter().longValue());
        certUpdateApplyAddRequest.setValidity(certApplyBTK.getCertValidity());
        //是否更新管理员证书
        certUpdateApplyAddRequest.setUpdateCert(true);
        //是否保持密钥
        certUpdateApplyAddRequest.setUpdateRetainKey(false);
        CertUpdateApplyAddResponse certUpdateApplyAddResponse = null;
        try {
            Response response = new BTKImpl5066().getBusiness().doBusiness(certUpdateApplyAddRequest);
            certUpdateApplyAddResponse = (CertUpdateApplyAddResponse) response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certUpdateApplyAddResponse;
    }

    /**
     * 证书更新审核
     * @param reqSN
     * @param pass
     * @param reason
     * @return
     */
    @Override
    public CertUpdateApplyAuditResponse auditCertUpdateOnlineApply(String reqSN, boolean pass, String reason){
        CertUpdateApplyAuditRequest certUpdateApplyAuditRequest = new CertUpdateApplyAuditRequest();
        certUpdateApplyAuditRequest.setReqSN(reqSN);
        certUpdateApplyAuditRequest.setAuditPass(pass);
        certUpdateApplyAuditRequest.setRefuseReason(reason);
        CertUpdateApplyAuditResponse certUpdateApplyAuditResponse = null;
        try {
            Response response = getBusiness().doBusiness(certUpdateApplyAuditRequest);
            certUpdateApplyAuditResponse= (CertUpdateApplyAuditResponse) response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certUpdateApplyAuditResponse;
    }

    /**
     * 证书更新申请修改
     * @param certApplyBTK
     * @return
     */
    @Override
    public CertUpdateApplyModifyResponse certUpdateApplyModify(CertApplyBTK certApplyBTK){
        CertUpdateApplyModifyRequest certUpdateApplyModifyRequest = new CertUpdateApplyModifyRequest();
        certUpdateApplyModifyRequest.setReqSN(certApplyBTK.getReqSn());
        certUpdateApplyModifyRequest.setSubject(certApplyBTK.getCertSubject());
        certUpdateApplyModifyRequest.setNotBefore(certApplyBTK.getNotBefore().longValue());
        certUpdateApplyModifyRequest.setNotAfter(certApplyBTK.getNotAfter().longValue());
        certUpdateApplyModifyRequest.setValidity(certApplyBTK.getCertValidity());

        CertUpdateApplyModifyResponse certUpdateApplyModifyResponse = null;
        try {
            Response response = getBusiness().doBusiness(certUpdateApplyModifyRequest);
            certUpdateApplyModifyResponse = (CertUpdateApplyModifyResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certUpdateApplyModifyResponse;
    }

    /**
     * 证书冻结申请
     * @param certApplyBTK
     * @return
     */
    @Override
    public CertHoldApplyAddResponse certHoldApplyAdd(CertApplyBTK certApplyBTK){
        CertHoldApplyAddRequest certHoldApplyAddRequest = new CertHoldApplyAddRequest();
        certHoldApplyAddRequest.setCertSN(certApplyBTK.getCertSn());
        //是否延迟操作
        certHoldApplyAddRequest.setNotNow(certApplyBTK.getNotNow());
        //延迟天数
        certHoldApplyAddRequest.setDays(certApplyBTK.getDelayDays());
        certHoldApplyAddRequest.setHoldDESC(certApplyBTK.getHoldDesc());
        CertHoldApplyAddResponse certHoldApplyAddResponse = null;
        try {
            Response response = getBusiness().doBusiness(certHoldApplyAddRequest);
            certHoldApplyAddResponse = (CertHoldApplyAddResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certHoldApplyAddResponse;
    }

    /**
     * 证书冻结审核
     * @param reqSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    @Override
    public CertHoldApplyAuditResponse certHoldApplyAudit(String reqSN,Boolean auditPass,String refuseReason){
        CertHoldApplyAuditRequest certHoldApplyAuditRequest = new CertHoldApplyAuditRequest();
        certHoldApplyAuditRequest.setReqSN(reqSN);
        certHoldApplyAuditRequest.setAuditPass(auditPass);
        certHoldApplyAuditRequest.setRefuseReason(refuseReason);
        CertHoldApplyAuditResponse certHoldApplyAuditResponse = null;
        try {
            Response response = getBusiness().doBusiness(certHoldApplyAuditRequest);
            certHoldApplyAuditResponse = (CertHoldApplyAuditResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certHoldApplyAuditResponse;
    }

    /**
     * 证书解冻申请
     * @param certApplyBTK
     * @return
     */
    @Override
    public CertUnholdApplyAddResponse certUnholdApplyAdd(CertApplyBTK certApplyBTK){
        CertUnholdApplyAddRequest certUnholdApplyAddRequest = new CertUnholdApplyAddRequest();
        certUnholdApplyAddRequest.setCertSN(certApplyBTK.getCertSn());
        certUnholdApplyAddRequest.setNotNow(certApplyBTK.getNotNow());
        certUnholdApplyAddRequest.setDays(certApplyBTK.getDelayDays());
        CertUnholdApplyAddResponse certUnholdApplyAddResponse = null;
        try {
            Response response = getBusiness().doBusiness(certUnholdApplyAddRequest);
            certUnholdApplyAddResponse = (CertUnholdApplyAddResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certUnholdApplyAddResponse;
    }

    /**
     * 证书解冻审核
     * @param reqSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    @Override
    public CertUnholdApplyAuditResponse certUnholdApplyAudit(String reqSN, Boolean auditPass, String refuseReason){
        CertUnholdApplyAuditRequest certUnholdApplyAuditRequest = new CertUnholdApplyAuditRequest();
        certUnholdApplyAuditRequest.setReqSN(reqSN);
        certUnholdApplyAuditRequest.setAuditPass(auditPass);
        certUnholdApplyAuditRequest.setRefuseReason(refuseReason);
        CertUnholdApplyAuditResponse certUnholdApplyAuditResponse = null;
        try {
            Response response = getBusiness().doBusiness(certUnholdApplyAuditRequest);
            certUnholdApplyAuditResponse = (CertUnholdApplyAuditResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certUnholdApplyAuditResponse;
    }

    /**
     * 证书注销申请
     * @param certApplyBTK
     * @return
     */
    @Override
    public CertRevokeApplyAddResponse certRevokeApplyAdd(CertApplyBTK certApplyBTK){
        CertRevokeApplyAddRequest certRevokeApplyAddRequest = new CertRevokeApplyAddRequest();
        certRevokeApplyAddRequest.setCertSN(certApplyBTK.getCertSn());
        certRevokeApplyAddRequest.setRevokeReason(Integer.valueOf(certApplyBTK.getRevokeReason()));
        certRevokeApplyAddRequest.setRevokeDesc(certApplyBTK.getRevokeDesc());
        certRevokeApplyAddRequest.setNotNow(certApplyBTK.getNotNow());
        certRevokeApplyAddRequest.setDays(certApplyBTK.getDelayDays());
        CertRevokeApplyAddResponse certRevokeApplyAddResponse = null;
        try {
            Response response = getBusiness().doBusiness(certRevokeApplyAddRequest);
            certRevokeApplyAddResponse = (CertRevokeApplyAddResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certRevokeApplyAddResponse;
    }

    /**
     * 证书注销审核
     * @param reqSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    @Override
    public CertRevokeApplyAuditResponse certRevokeApplyAudit(String reqSN,Boolean auditPass,String refuseReason){
        CertRevokeApplyAuditRequest certRevokeApplyAuditRequest = new CertRevokeApplyAuditRequest();
        certRevokeApplyAuditRequest.setReqSN(reqSN);
        certRevokeApplyAuditRequest.setAuditPass(auditPass);
        certRevokeApplyAuditRequest.setRefuseReason(refuseReason);

        CertRevokeApplyAuditResponse certRevokeApplyAuditResponse = null;
        try {
            Response response = getBusiness().doBusiness(certRevokeApplyAuditRequest);
            certRevokeApplyAuditResponse = (CertRevokeApplyAuditResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certRevokeApplyAuditResponse;
    }

    /**
     * 证书注销申请修改
     * @param certApplyBTK
     * @return
     */
    @Override
    public CertRevokeApplyModifyResponse certRevokeApplyModify(CertApplyBTK certApplyBTK){
        CertRevokeApplyModifyRequest certRevokeApplyModifyRequest = new CertRevokeApplyModifyRequest();
        certRevokeApplyModifyRequest.setReqSN(certApplyBTK.getReqSn());
        //是否延迟操作
        certRevokeApplyModifyRequest.setNotNow(certApplyBTK.getNotNow());
        certRevokeApplyModifyRequest.setDays(certApplyBTK.getDelayDays());
        certRevokeApplyModifyRequest.setRevokeReason(Integer.valueOf(certApplyBTK.getRevokeReason()));
        certRevokeApplyModifyRequest.setRevokeDesc(certApplyBTK.getRevokeDesc());
        CertRevokeApplyModifyResponse certRevokeApplyModifyResponse = null;
        try {
            Response response = getBusiness().doBusiness(certRevokeApplyModifyRequest);
            certRevokeApplyModifyResponse = (CertRevokeApplyModifyResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certRevokeApplyModifyResponse;
    }

    /**
     * 证书延期申请
     * @param certSN
     * @param applyType
     * @return
     */
    @Override
    public ExtendCertValidApplyAddResponse extendCertValidApplyAdd(String certSN,String applyType){
        ExtendCertValidApplyAddRequest extendCertValidApplyAddRequest = new ExtendCertValidApplyAddRequest();
        extendCertValidApplyAddRequest.setCertSN(certSN);
        //是否自动审核(1:自动，0：手动)
        extendCertValidApplyAddRequest.setApplyType(applyType);
        ExtendCertValidApplyAddResponse extendCertValidApplyAddResponse = null;
        try {
            Response response = getBusiness().doBusiness(extendCertValidApplyAddRequest);
            extendCertValidApplyAddResponse = (ExtendCertValidApplyAddResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return extendCertValidApplyAddResponse;
    }

    /**
     * 证书延期审核
     * @param certSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    @Override
    public ExtendCertValidApplyAuditResponse extendCertValidApplyAudit(String certSN,Boolean auditPass,String refuseReason){
        ExtendCertValidApplyAuditRequest extendCertValidApplyAuditRequest = new ExtendCertValidApplyAuditRequest();
        extendCertValidApplyAuditRequest.setCertSN(certSN);
        extendCertValidApplyAuditRequest.setAuditPass(auditPass);
        extendCertValidApplyAuditRequest.setRefuseReason(refuseReason);
        ExtendCertValidApplyAuditResponse extendCertValidApplyAuditResponse = null;
        try {
            Response response = getBusiness().doBusiness(extendCertValidApplyAuditRequest);
            extendCertValidApplyAuditResponse = (ExtendCertValidApplyAuditResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return extendCertValidApplyAuditResponse;
    }

    /**
     * 证书延期下载
     * @param certMakeBTK
     * @return
     */
    @Override
    public ExtendValidAndDownResponse extendValidAndDown(CertMakeBTK certMakeBTK){
        ExtendValidAndDownRequest extendValidAndDownRequest = new ExtendValidAndDownRequest();
        extendValidAndDownRequest.setNotBefore(certMakeBTK.getNotBefore());
        extendValidAndDownRequest.setNotAfter(certMakeBTK.getNotAftere());
        extendValidAndDownRequest.setValidity(certMakeBTK.getValidaty());
        extendValidAndDownRequest.setP10(certMakeBTK.getP10());
        extendValidAndDownRequest.setDoubleP10(certMakeBTK.getDoubleP10());

        ExtendValidAndDownResponse extendValidAndDownResponse = null;
        try {
            Response response = getBusiness().doBusiness(extendValidAndDownRequest);
            extendValidAndDownResponse = (ExtendValidAndDownResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return extendValidAndDownResponse;
    }

    /**
     * 授权码更新申请
     * @param certSN
     * @return
     */
    @Override
    public CertAuthCodeUpdateApplyAddResponse certAuthCodeUpdateApplyAdd(String certSN){
        CertAuthCodeUpdateApplyAddRequest certAuthCodeUpdateApplyAddRequest = new CertAuthCodeUpdateApplyAddRequest();
        certAuthCodeUpdateApplyAddRequest.setCertSN(certSN);
        CertAuthCodeUpdateApplyAddResponse certAuthCodeUpdateApplyAddResponse = null;
        try {
            Response response = getBusiness().doBusiness(certAuthCodeUpdateApplyAddRequest);
            certAuthCodeUpdateApplyAddResponse = (CertAuthCodeUpdateApplyAddResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certAuthCodeUpdateApplyAddResponse;
    }

    /**
     * 授权码更新审核
     * @param reqSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    @Override
    public CertAuthCodeUpdateApplyAuditResponse certAuthCodeUpdateApplyAudit(String reqSN,boolean auditPass,String refuseReason){
        CertAuthCodeUpdateApplyAuditRequest certAuthCodeUpdateApplyAuditRequest = new CertAuthCodeUpdateApplyAuditRequest();
        certAuthCodeUpdateApplyAuditRequest.setReqSN(reqSN);
        certAuthCodeUpdateApplyAuditRequest.setAuditPass(auditPass);
        certAuthCodeUpdateApplyAuditRequest.setRefuseReason(refuseReason);

        CertAuthCodeUpdateApplyAuditResponse certAuthCodeUpdateApplyAuditResponse = null;
        try {
            Response response = getBusiness().doBusiness(certAuthCodeUpdateApplyAuditRequest);
            certAuthCodeUpdateApplyAuditResponse = (CertAuthCodeUpdateApplyAuditResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certAuthCodeUpdateApplyAuditResponse;
    }

    /**
     * 删除申请
     * @param reqSN
     * @return
     */
    @Override
    public DeleteApplyResponse deleteApply(String reqSN){
        DeleteApplyRequest deleteApplyRequest = new DeleteApplyRequest();
        deleteApplyRequest.setReqSN(reqSN);

        DeleteApplyResponse deleteApplyResponse = null;
        try {
            Response response = getBusiness().doBusiness(deleteApplyRequest);
            deleteApplyResponse = (DeleteApplyResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteApplyResponse;
    }

    /**
     * 批量申请证书
     * @param batchApplyBTK
     * @return
     */
    @Override
    public BatchCertApplyResponse batchCertApply(BatchApplyBTK batchApplyBTK){
        BatchCertApplyRequest batchCertApplyRequest = new BatchCertApplyRequest();
        //证书信息
        batchCertApplyRequest.setApplyInfoList(batchApplyBTK.getApplyinfoList());
        //有审核权限时，即：批量申请证书所使用的模版为自动审核，指定直接审核
        batchCertApplyRequest.setAuditSign(batchApplyBTK.getAuditSign());
        //批量申请证书时，是否覆盖原有证书（主键相同时）1：是  ，0：否
        batchCertApplyRequest.setOverwriteType(batchApplyBTK.getOverwriteType());

        BatchCertApplyResponse batchCertApplyResponse = null;
        try {
            Response response = getBusiness().doBusiness(batchCertApplyRequest);
            batchCertApplyResponse = (BatchCertApplyResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batchCertApplyResponse;
    }


    /**
     * 批量更新证书
     * @param batchApplyBTK
     * @return
     */
    @Override
    public BatchCertUpdateResponse batchCertUpdate(BatchApplyBTK batchApplyBTK){
        BatchCertUpdateRequest batchCertUpdateRequest = new BatchCertUpdateRequest();
        batchCertUpdateRequest.setSnList(batchApplyBTK.getCertSNs());
        batchCertUpdateRequest.setNotBefore(batchApplyBTK.getNotBefore());
        batchCertUpdateRequest.setNotAfter(batchApplyBTK.getNotAfter());
        batchCertUpdateRequest.setValidty(batchApplyBTK.getCertValidty());
        BatchCertUpdateResponse batchCertUpdateResponse = null;
        try {
            Response response = getBusiness().doBusiness(batchCertUpdateRequest);
            batchCertUpdateResponse = (BatchCertUpdateResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batchCertUpdateResponse;
    }

    /**
     * 批量冻结证书
     * @param certSNs
     * @return
     */
    @Override
    public BatchCertHoldResponse batchCertHold(List<String> certSNs){
        BatchCertHoldRequest batchCertHoldRequest = new BatchCertHoldRequest();
        batchCertHoldRequest.setSnList(certSNs);
        BatchCertHoldResponse batchCertHoldResponse = null;
        try {
            Response response = getBusiness().doBusiness(batchCertHoldRequest);
            batchCertHoldResponse = (BatchCertHoldResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batchCertHoldResponse;
    }

    /**
     * 批量解冻证书
     * @param certSNs
     * @return
     */
    @Override
    public BatchCertUnHoldResponse batchCertUnHold(List<String> certSNs){
        BatchCertUnHoldRequest batchCertUnHoldRequest = new BatchCertUnHoldRequest();
        batchCertUnHoldRequest.setSnList(certSNs);
        BatchCertUnHoldResponse batchCertUnHoldResponse = null;
        try {
            Response response = getBusiness().doBusiness(batchCertUnHoldRequest);
            batchCertUnHoldResponse = (BatchCertUnHoldResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batchCertUnHoldResponse;
    }

    /**
     * 批量注销证书
     * @param certSNs
     * @return
     */
    @Override
    public BatchCertRevokeResponse batchCertRevoke(List<String> certSNs){
        BatchCertRevokeRequest batchCertRevokeRequest = new BatchCertRevokeRequest();
        batchCertRevokeRequest.setSnList(certSNs);

        BatchCertRevokeResponse batchCertRevokeResponse = null;
        try {
            Response response = getBusiness().doBusiness(batchCertRevokeRequest);
            batchCertRevokeResponse = (BatchCertRevokeResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batchCertRevokeResponse;
    }
    /**
     * 批量审核申请信息(新制，更新，冻结，解冻，注销)
     * @param reqSNs
     * @param batchAuditType
     * @param batchAuditResult
     * @param batchAuditReason
     * @return
     */
    @Override
    public BatchAuditApplyResponse batchAuditApply(List<String> reqSNs,String batchAuditType,Boolean batchAuditResult,String batchAuditReason){
        BatchAuditApplyRequest batchAuditApplyRequest = new BatchAuditApplyRequest();
        /*
            AuditType代表的是审核操作的类型:
                批量审核证书申请：BatchAuditApplyRequest.BATCH_AUDIT_APPLY，
                批量审核证书冻结申请：BatchAuditApplyRequest.BATCH_AUDIT_HOLD，
                批量审核证书解冻申请：BatchAuditApplyRequest.BATCH_AUDIT_UNHOLD，
                批量审核证书注销申请： BatchAuditApplyRequest.BATCH_AUDIT_REVOKED，
                批量审核证书更新申请：BatchAuditApplyRequest.BATCH_AUDIT_UPDATE
         */
        if("1".equals(batchAuditType)){
            batchAuditType = BatchAuditApplyRequest.BATCH_AUDIT_APPLY;
        }else if("2".equals(batchAuditType)){
            batchAuditType = BatchAuditApplyRequest.BATCH_AUDIT_REVOKED;
        }else if("3".equals(batchAuditType)){
            batchAuditType = BatchAuditApplyRequest.BATCH_AUDIT_HOLD;
        }else if("4".equals(batchAuditType)){
            batchAuditType = BatchAuditApplyRequest.BATCH_AUDIT_UNHOLD;
        }else if("5".equals(batchAuditType)){
            batchAuditType = BatchAuditApplyRequest.BATCH_AUDIT_UPDATE;
        }
        batchAuditApplyRequest.setSnList(reqSNs);
        batchAuditApplyRequest.setAuditType(batchAuditType);
        batchAuditApplyRequest.setAuditResult(batchAuditResult);
        batchAuditApplyRequest.setAuditReason(batchAuditReason);

        BatchAuditApplyResponse batchAuditApplyResponse = null;
        try {
            Response response = getBusiness().doBusiness(batchAuditApplyRequest);
            batchAuditApplyResponse = (BatchAuditApplyResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batchAuditApplyResponse;
    }

    /**
     * 批量删除证书申请信息
     * @param reqSNs
     * @return
     */
    @Override
    public BatchDeleteApplyResponse batchDeleteApply(List<String> reqSNs){
        BatchDeleteApplyRequest batchDeleteApplyRequest = new BatchDeleteApplyRequest();
        batchDeleteApplyRequest.setSnList(reqSNs);

        BatchDeleteApplyResponse batchDeleteApplyResponse = null;
        try {
            Response response = getBusiness().doBusiness(batchDeleteApplyRequest);
            batchDeleteApplyResponse = (BatchDeleteApplyResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batchDeleteApplyResponse;
    }

    /**
     * RA证书查询
     * @return queryBTK
     */
    @Override
    public CertQueryResponse certQuery(QueryBTK queryBTK){
        CertQueryRequest certQueryRequest = new CertQueryRequest();
        //是否按照时间查询（true：是，false：否）
        certQueryRequest.setFindWay(queryBTK.getFindWay());
        certQueryRequest.setOptStartTime(queryBTK.getOptStartTime());
        certQueryRequest.setOptEndTime(queryBTK.getOptEndTime());
        certQueryRequest.setCertSN(queryBTK.getCertSN());
        //按照指定机构查询
        certQueryRequest.setOrgId(queryBTK.getOrgId());
        //是否包含指定机构（true：包含，false：不包含）
        certQueryRequest.setContainChildOrg(queryBTK.isContainChildOrg());
        //针对证书主题是否精确查询
        certQueryRequest.setExactQuery(queryBTK.getExactQuery());
        certQueryRequest.setSubject(queryBTK.getCertSubject());
        certQueryRequest.setCtmlName(queryBTK.getCertCtmlName());
        //证书当前状态
        certQueryRequest.setCertstatus(queryBTK.getCertStatus());
        //证书CN项
        certQueryRequest.setCommonName(queryBTK.getCommonName());
        //是否管理员（1：否，2：是）
        certQueryRequest.setIsAdmin(queryBTK.getIsAdmin());
        certQueryRequest.setFirst(queryBTK.getFirst());
        certQueryRequest.setMax(queryBTK.getMax());
        CertQueryResponse certQueryResponse = null;
        try {
            Response response = getBusiness().doBusiness(certQueryRequest);
            certQueryResponse = (CertQueryResponse)response;
            System.out.println("证书数量"+certQueryResponse.getTotalRowCount());
            for(int i=0;i<certQueryResponse.getResultList().size();i++){
                System.out.println("certSN"+certQueryResponse.getResultList().get(i).getCertInfo().getCertSN());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certQueryResponse;
    }

    /**
     * 单个证书查询
     * @param certSN
     * @return
     */
    @Override
    public CertSingleQueryResponse certSingleQuery(String certSN){
        CertSingleQueryRequest certSingleQueryRequest = new CertSingleQueryRequest();
        certSingleQueryRequest.setCertSN(certSN);

        CertSingleQueryResponse certSingleQueryResponse = null;
        try {
            Response response = getBusiness().doBusiness(certSingleQueryRequest);
            certSingleQueryResponse = (CertSingleQueryResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certSingleQueryResponse;
    }

    /**
     * 证书申请查询
     * @return
     */
    @Override
    public CertApplyQueryResponse certApplyQuery(QueryBTK queryBTK){
        CertApplyQueryRequest certApplyQueryRequest = new CertApplyQueryRequest();
        //是否根据时间查询
        certApplyQueryRequest.setFindWay1(queryBTK.getFindWay());
        certApplyQueryRequest.setReqSN(queryBTK.getReqSN());
        //是否精确查询(针对证书主题:0,模糊：1，精确)
        certApplyQueryRequest.setExactQuery(queryBTK.getExactQuery());
        certApplyQueryRequest.setSubject(queryBTK.getCertSubject());
        certApplyQueryRequest.setCtmlName(queryBTK.getCertCtmlName());
        certApplyQueryRequest.setReqStatus(queryBTK.getReqStatus());
        certApplyQueryRequest.setOptStartTime(queryBTK.getOptStartTime());
        certApplyQueryRequest.setOptEndTime(queryBTK.getOptEndTime());
        certApplyQueryRequest.setOptType(queryBTK.getOptType());
        certApplyQueryRequest.setOrgId(queryBTK.getOrgId());
        //是否包含子机构
        certApplyQueryRequest.setContainChildOrg(queryBTK.isContainChildOrg());
        certApplyQueryRequest.setFirst(queryBTK.getFirst());
        certApplyQueryRequest.setMax(queryBTK.getMax());

        CertApplyQueryResponse certApplyQueryResponse = null;
        try {
            Response response = getBusiness().doBusiness(certApplyQueryRequest);
            certApplyQueryResponse = (CertApplyQueryResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certApplyQueryResponse;
    }

    /**
     * 证书单个申请查询（BTK未支持）
     * @param reqSN
     * @return
     */
    @Override
    public CertApplySingleQueryResponse certApplySingleQuery(String reqSN){
        CertApplySingleQueryRequest certApplySingleQueryRequest = new CertApplySingleQueryRequest();
        certApplySingleQueryRequest.setReqSN(reqSN);

        CertApplySingleQueryResponse certApplySingleQueryResponse = null;
        try {
            Response response = getBusiness().doBusiness(certApplySingleQueryRequest);
            certApplySingleQueryResponse = (CertApplySingleQueryResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certApplySingleQueryResponse;
    }
    
    /**
     * 封装用户信息
     * @param certType
     * @param certVOBTK
     * @return
     */
    private static Map<String, String> getUserOrCorporation(String certType,CertVOBTK certVOBTK){
        Map<String, String> props_temp = new HashMap<String, String>();
        if("userinfo".equals(certType)){
            UserBTK userBTK = certVOBTK.getUserBTK();
            props_temp.put("username",userBTK.getUsername() == null ?"" : userBTK.getUsername());//用户姓名
            props_temp.put("organ_id",userBTK.getOrganId() == null ?"" : userBTK.getOrganId());
            props_temp.put("useridcardnum",userBTK.getUseridcardnum() == null ?"" : userBTK.getUseridcardnum());//证件号码
            props_temp.put("phonenum",userBTK.getPhonenum() == null ?"" : userBTK.getPhonenum());//电话
            props_temp.put("idtype",userBTK.getIdtype() == null ?"" : userBTK.getIdtype());//证件类型
            props_temp.put("usercontactaddr",userBTK.getUsercontactaddr() == null ?"" : userBTK.getUsercontactaddr());//联系地址
            props_temp.put("org",userBTK.getOrg() == null ?"" : userBTK.getOrg());//单位名称
            props_temp.put("fax",userBTK.getFax() == null ?"" : userBTK.getFax());//传真
            props_temp.put("userzipcode",userBTK.getUserzipcode() == null ?"" : userBTK.getUserzipcode());//邮编
            props_temp.put("usercountry",userBTK.getUsercountry() == null ?"" : userBTK.getUsercountry());//国家
            props_temp.put("email",userBTK.getEmail() == null ?"" : userBTK.getEmail());//用户邮箱
        }else if("corporation_info".equals(certType)){
            CorporationBTK corporationBTK = certVOBTK.getCorporationBTK();
            props_temp.put("corpname",corporationBTK.getCorpname() == null ?"" : corporationBTK.getCorpname());//企业名称
            props_temp.put("registeredaddr",corporationBTK.getRegisteredaddr() == null ?"" : corporationBTK.getRegisteredaddr());//注册地址
            props_temp.put("legalpersonname",corporationBTK.getLegalpersonname() == null ?"" : corporationBTK.getLegalpersonname());//法人代表
            props_temp.put("corpcontactaddr",corporationBTK.getCorpcontactaddr() == null ?"" : corporationBTK.getCorpcontactaddr());//联系地址
            props_temp.put("corpzipcode",corporationBTK.getCorpzipcode() == null ?"" : corporationBTK.getCorpzipcode());//邮编
            props_temp.put("contactperson",corporationBTK.getContactperson() == null ?"" : corporationBTK.getContactperson());//联系人姓名
            props_temp.put("contactphone",corporationBTK.getContactphone() == null ?"" : corporationBTK.getContactphone());//联系电话
            props_temp.put("corpidtype",corporationBTK.getCorpidtype() == null ?"" : corporationBTK.getCorpidtype());//企业有效证件类型
            props_temp.put("corpidcardnum",corporationBTK.getCorpidcardnum() == null ?"" : corporationBTK.getCorpidcardnum());//企业有效证件号码
            props_temp.put("agentname",corporationBTK.getAgentname() == null ?"" : corporationBTK.getAgentname());//经办人姓名
            props_temp.put("agentphone",corporationBTK.getAgentphone() == null ?"" : corporationBTK.getAgentphone());//经办人联系电话
            props_temp.put("agentcontactaddr",corporationBTK.getAgentcontactaddr() == null ?"" : corporationBTK.getAgentcontactaddr());//经办人联系地址
            props_temp.put("agentidtype",corporationBTK.getAgentidtype() == null ?"" : corporationBTK.getAgentidtype());//经办人有效证件类型
            props_temp.put("agentidcardnum",corporationBTK.getAgentidcardnum() == null ?"" : corporationBTK.getAgentidcardnum());//经办人有效证件号码
            props_temp.put("corpcountry",corporationBTK.getCorpcountry() == null ?"" : corporationBTK.getCorpcountry());//国家
            props_temp.put("email",corporationBTK.getEmail() == null ?"" : corporationBTK.getEmail());//用户邮箱
        }
        return props_temp;
    }

    /**
     * 添加用户
     * @param certType
     * @param certType
     * @param certVOBTK
     * @return
     */
    @Override
    public UserInfoInsertResponse userInfoInsert(String certType,CertVOBTK certVOBTK){

        UserInfoInsertRequest userInfoInsertRequest = new UserInfoInsertRequest();
        userInfoInsertRequest.setTableName(certType);
        Map<String, String> props_temp = getUserOrCorporation(certType,certVOBTK);
//        props_temp.put("organ_id", "2c908def200371b601200371c3250030");
        userInfoInsertRequest.setUserMap(props_temp);

        UserInfoInsertResponse userInfoInsertResponse = null;
        try {
            Response response = getBusiness().doBusiness(userInfoInsertRequest);
            userInfoInsertResponse = (UserInfoInsertResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfoInsertResponse;
    }

    /**
     * 更新用户信息
     * @param certVOBTK
     * @param certType
     * @return
     */
    @Override
    public UserInfoUpdateResponse userInfoUpdate(String certType,CertVOBTK certVOBTK){
        UserInfoUpdateRequest userInfoUpdateRequest = new UserInfoUpdateRequest();
        if("userinfo".equals(certType)){
        	userInfoUpdateRequest.setUserid(certVOBTK.getUserBTK().getUserInfoId());
        }else if("corporation_info".equals(certType)){
        	userInfoUpdateRequest.setUserid(certVOBTK.getCorporationBTK().getCorporationId());
        }
        userInfoUpdateRequest.setTableName(certType);
        Map<String, String> props_temp = getUserOrCorporation(certType,certVOBTK);
        userInfoUpdateRequest.setUserMap(props_temp);

        UserInfoUpdateResponse userInfoUpdateResponse = null;
        try {
            Response response = getBusiness().doBusiness(userInfoUpdateRequest);
            userInfoUpdateResponse = (UserInfoUpdateResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfoUpdateResponse;
    }

    /**
     * 单个用户查询
     * @param userInfoId
     * @param certType
     * @return
     */
    @Override
    public CertVOBTK UserInfoSingleQuery(String userInfoId,String certType){
        UserInfoSingleQueryRequest 	userInfoSingleQueryRequest 	= 	new UserInfoSingleQueryRequest();
        userInfoSingleQueryRequest.setUserinfoId(userInfoId);
        userInfoSingleQueryRequest.setTableName(certType);
        //是否查询归档用户 (是：true，否：false)
        userInfoSingleQueryRequest.setQueryArc(false);
        CertVOBTK certVOBTK = new CertVOBTK();
        UserInfoSingleQueryResponse userInfoSingleQueryResponse = null;
        try {
            Response response = getBusiness().doBusiness(userInfoSingleQueryRequest);
            userInfoSingleQueryResponse = (UserInfoSingleQueryResponse)response;
            if(userInfoSingleQueryResponse!=null){
                if("0".equals(userInfoSingleQueryResponse.getErr())){
                    if(userInfoSingleQueryResponse.getUserMap()!=null){
                        Map map=userInfoSingleQueryResponse.getUserMap();
                        if("userinfo".equals(certType)){
                            UserBTK userBTK = new UserBTK();
                            userBTK.setUserInfoId((String)map.get("userInfoId"));
                            userBTK.setOrganId((String)map.get("organId"));
                            userBTK.setCertType((String)map.get("certType"));
                            userBTK.setUsername((String)map.get("username"));
                            userBTK.setIdtype((String)map.get("idtype"));
                            userBTK.setUseridcardnum((String)map.get("useridcardnum"));
                            userBTK.setPhonenum((String)map.get("phonenum"));
                            userBTK.setUsercontactaddr((String)map.get("usercontactaddr"));
                            userBTK.setOrg((String)map.get("org"));
                            userBTK.setFax((String)map.get("fax"));
                            userBTK.setUserzipcode((String)map.get("userzipcode"));
                            userBTK.setUsercountry((String)map.get("usercountry"));
                            userBTK.setColumn_WD9Z((String)map.get("column_WD9Zv"));
                            userBTK.setEmail((String)map.get("email"));
                            certVOBTK.setUserBTK(userBTK);
                        }else if("corporation_info".equals(certType)){
                            CorporationBTK corporationBTK = new CorporationBTK();
                            corporationBTK.setCorporationId((String)map.get("corporationId"));
                            corporationBTK.setCorpname((String)map.get("corpname"));
                            corporationBTK.setRegisteredaddr((String)map.get("registeredaddr"));
                            corporationBTK.setLegalpersonname((String)map.get("legalpersonname"));
                            corporationBTK.setCorpcontactaddr((String)map.get("corpcontactaddr"));
                            corporationBTK.setCorpzipcode((String)map.get("corpzipcode"));
                            corporationBTK.setContactperson((String)map.get("contactperson"));
                            corporationBTK.setContactphone((String)map.get("contactphone"));
                            corporationBTK.setCorpidtype((String)map.get("corpidtype"));
                            corporationBTK.setCorpidcardnum((String)map.get("corpidcardnum"));
                            corporationBTK.setAgentname((String)map.get("agentname"));
                            corporationBTK.setAgentphone((String)map.get("agentphone"));
                            corporationBTK.setAgentcontactaddr((String)map.get("agentcontactaddr"));
                            corporationBTK.setAgentidtype((String)map.get("agentidtype"));
                            corporationBTK.setAgentidcardnum((String)map.get("agentidcardnum"));
                            corporationBTK.setCorpcountry((String)map.get("corpcountry"));
                            corporationBTK.setEmail((String)map.get("email"));
                            corporationBTK.setColumn_GK9O((String)map.get("column_GK9O"));
                            corporationBTK.setColumn_V98M((String)map.get("column_V98M"));
                            corporationBTK.setColumn_SJVP((String)map.get("column_SJVP"));
                            corporationBTK.setColumn_SLJQ((String)map.get("column_SLJQ"));
                            corporationBTK.setColumn_EJYX((String)map.get("column_EJYX"));
                            certVOBTK.setCorporationBTK(corporationBTK);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certVOBTK;
    }

    /**
     * 用户查询（BTK未支持）
     * @param certType
     * @param orgId
     * @param isContainChildOrg
     * @return
     */
    @Override
    public  UserInfoQueryResponse userInfoQuery(String certType,String orgId,boolean isContainChildOrg){
        UserInfoQueryRequest userInfoQueryRequest =new UserInfoQueryRequest();
        userInfoQueryRequest.setTableName(certType);
        userInfoQueryRequest.setOrgId(orgId);
        userInfoQueryRequest.setContainChildOrg(isContainChildOrg);
        UserInfoQueryResponse userInfoQueryResponse = null;
        try {
            Response response = getBusiness().doBusiness(userInfoQueryRequest);
            userInfoQueryResponse = (UserInfoQueryResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfoQueryResponse;
    }

    /**
     * 删除用户(BTK未支持)
     * @param userInfoId
     * @return
     */
    @Override
    public UserInfoDeleteResponse userInfoDelete(String userInfoId){
        UserInfoDeleteRequest userInfoDeleteRequest = new UserInfoDeleteRequest();
        userInfoDeleteRequest.setUserID(userInfoId);
        userInfoDeleteRequest.setTableName("userinfo");
        UserInfoDeleteResponse userInfoDeleteResponse = null;
        try {
            Response response = getBusiness().doBusiness(userInfoDeleteRequest);
            userInfoDeleteResponse = (UserInfoDeleteResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfoDeleteResponse;
    }

    /**
     * 密钥恢复申请
     * @param certSN
     * @return
     */
    @Override
    public RecoverKeyApplyAddResponse recoverKeyApplyAdd(String certSN){
        RecoverKeyApplyAddRequest recoverKeyApplyAddRequest = new RecoverKeyApplyAddRequest();
        recoverKeyApplyAddRequest.setCertSN(certSN);
        recoverKeyApplyAddRequest.setApplyType("0");
        RecoverKeyApplyAddResponse recoverKeyApplyAddResponse = null;
        try {
            Response response = getBusiness().doBusiness(recoverKeyApplyAddRequest);
            recoverKeyApplyAddResponse = (RecoverKeyApplyAddResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recoverKeyApplyAddResponse;
    }

    /**
     * 密钥恢复申请审核
     * @param reqSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    @Override
    public RecoverKeyApplyAuditResponse recoverKeyApplyAudit(String reqSN,Boolean auditPass,String refuseReason){
        RecoverKeyApplyAuditRequest recoverKeyApplyAuditRequest = new RecoverKeyApplyAuditRequest();
        recoverKeyApplyAuditRequest.setReqSN(reqSN);
        recoverKeyApplyAuditRequest.setAuditPass(auditPass);
        recoverKeyApplyAuditRequest.setRefuseReason(refuseReason);
        RecoverKeyApplyAuditResponse recoverKeyApplyAuditResponse = null;
        try {
            Response response = getBusiness().doBusiness(recoverKeyApplyAuditRequest);
            recoverKeyApplyAuditResponse = (RecoverKeyApplyAuditResponse)response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recoverKeyApplyAuditResponse;
    }
}
