package com.sxca.myb.common.BTK.btkinterface;

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
import cn.com.jit.pki.ra.user.response.UserInfoDeleteResponse;
import cn.com.jit.pki.ra.user.response.UserInfoInsertResponse;
import cn.com.jit.pki.ra.user.response.UserInfoQueryResponse;
import cn.com.jit.pki.ra.user.response.UserInfoUpdateResponse;
import com.sxca.myb.common.BTK.entity.*;

import java.util.List;

/**
 * @Author Wang Jiyu
 * BTK工具类包装，与RA通信操作与证书有关的所有请求接口
 * Created by admin on 2017/3/23.
 */
public interface BTK {
    /**
     * 判断与RA是否连通
     * @param userinfoId 用户ID
     * @param userType  用户类型（userinfo:个人用户，corporation_info：企业用户）
     * @return
     */
    public boolean userinfoQuery(String userinfoId, String userType);

    /**
     * 证书模版查询
     * @return
     */
    public List<CtmlVOBTK> certCtmlQuery();

    /**
     *新制申请
     * @param certVOBTK BTK申请证书信息封装类
     * @return
     */
    public CertApplyAddResponse certApplyAdd(CertVOBTK certVOBTK);

    /**
     * 新制审核
     * @param reqSN 申请序列号
     * @param pass  是否通过审核
     * @param reason  拒绝原因
     * @return
     */
    public CertApplyAuditResponse certApplyAudit(String reqSN, Boolean pass, String reason);

    /**
     * 新制申请修改
     * @param certVOBTK
     * @return
     */
    public CertApplyModifyResponse certApplyModify(CertVOBTK certVOBTK);

    /**
     * 证书下载
     * @param certMakeBTK
     * @return
     */
    public CertMakeResponse downloadCert(CertMakeBTK certMakeBTK);

    /**
     * 证书更新申请
     * @param certApplyBTK
     * @return
     */
    public CertUpdateApplyAddResponse addCertUpdateOnlineApply(CertApplyBTK certApplyBTK);

    /**
     * 证书更新审核
     * @param reqSN
     * @param pass
     * @param reason
     * @return
     */
    public CertUpdateApplyAuditResponse auditCertUpdateOnlineApply(String reqSN, boolean pass, String reason);

    /**
     * 证书更新申请修改
     * @param certApplyBTK
     * @return
     */
    public CertUpdateApplyModifyResponse certUpdateApplyModify(CertApplyBTK certApplyBTK);

    /**
     * 证书冻结申请
     * @param certApplyBTK
     * @return
     */
    public CertHoldApplyAddResponse certHoldApplyAdd(CertApplyBTK certApplyBTK);

    /**
     * 证书冻结审核
     * @param reqSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    public CertHoldApplyAuditResponse certHoldApplyAudit(String reqSN, Boolean auditPass, String refuseReason);

    /**
     * 证书解冻申请
     * @param certApplyBTK
     * @return
     */
    public CertUnholdApplyAddResponse certUnholdApplyAdd(CertApplyBTK certApplyBTK);

    /**
     * 证书解冻审核
     * @param reqSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    public CertUnholdApplyAuditResponse certUnholdApplyAudit(String reqSN, Boolean auditPass, String refuseReason);

    /**
     * 证书注销申请
     * @param certApplyBTK
     * @return
     */
    public CertRevokeApplyAddResponse certRevokeApplyAdd(CertApplyBTK certApplyBTK);

    /**
     * 证书注销审核
     * @param reqSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    public CertRevokeApplyAuditResponse certRevokeApplyAudit(String reqSN, Boolean auditPass, String refuseReason);

    /**
     * 证书注销申请修改
     * @param certApplyBTK
     * @return
     */
    public CertRevokeApplyModifyResponse certRevokeApplyModify(CertApplyBTK certApplyBTK);

    /**
     * 证书延期申请
     * @param certSN
     * @param applyType
     * @return
     */
    public ExtendCertValidApplyAddResponse extendCertValidApplyAdd(String certSN,String applyType);

    /**
     * 证书延期审核
     * @param certSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    public ExtendCertValidApplyAuditResponse extendCertValidApplyAudit(String certSN,Boolean auditPass,String refuseReason);

    /**
     * 证书延期下载
     * @param certMakeBTK
     * @return
     */
    public ExtendValidAndDownResponse extendValidAndDown(CertMakeBTK certMakeBTK);

    /**
     * 证书授权码更新申请
     * @param certSN
     * @return
     */
    public CertAuthCodeUpdateApplyAddResponse certAuthCodeUpdateApplyAdd(String certSN);

    /**
     * 证书授权码更新审核
     * @param reqSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    public CertAuthCodeUpdateApplyAuditResponse certAuthCodeUpdateApplyAudit(String reqSN,boolean auditPass,String refuseReason);

    /**
     * 删除证书申请
     * @param reqSN
     * @return
     */
    public DeleteApplyResponse deleteApply(String reqSN);

    /**
     * 批量新制
     * @param batchApplyBTK
     * @return
     */
    public BatchCertApplyResponse batchCertApply(BatchApplyBTK batchApplyBTK);

    /**
     * 批量更新
     * @param batchApplyBTK
     * @return
     */
    public BatchCertUpdateResponse batchCertUpdate(BatchApplyBTK batchApplyBTK);

    /**
     * 批量冻结
     * @param certSNs
     * @return
     */
    public BatchCertHoldResponse batchCertHold(List<String> certSNs);

    /**
     * 批量解冻
     * @param certSNs
     * @return
     */
    public BatchCertUnHoldResponse batchCertUnHold(List<String> certSNs);

    /**
     * 批量注销
     * @param certSNs
     * @return
     */
    public BatchCertRevokeResponse batchCertRevoke(List<String> certSNs);

    /**
     * 批量审核申请
     * @param reqSNs
     * @param batchAuditType
     * @param batchAuditResult
     * @param batchAuditReason
     * @return
     */
    public BatchAuditApplyResponse batchAuditApply(List<String> reqSNs,String batchAuditType,Boolean batchAuditResult,String batchAuditReason);

    /**
     * 批量删除申请
     * @param reqSNs
     * @return
     */
    public BatchDeleteApplyResponse batchDeleteApply(List<String> reqSNs);

    /**
     * 证书查询
     * @param queryBTK
     * @return
     */
    public CertQueryResponse certQuery(QueryBTK queryBTK);

    /**
     * 证书单个查询
     * @param certSN
     * @return
     */
    public CertSingleQueryResponse certSingleQuery(String certSN);

    /**
     * 证书申请查询
     * @param queryBTK
     * @return
     */
    public CertApplyQueryResponse certApplyQuery(QueryBTK queryBTK);

    /**
     * 单个申请查询（BTK暂不支持）
     * @param reqSN
     * @return
     */
    public CertApplySingleQueryResponse certApplySingleQuery(String reqSN);

    /**
     * 用户添加
     * @param certType 证书用户类型
     * @param certVOBTK
     * @return
     */
    public UserInfoInsertResponse userInfoInsert(String certType, CertVOBTK certVOBTK);

    /**
     * 用户更新
     * @param certType
     * @param certVOBTK
     * @return
     */
    public UserInfoUpdateResponse userInfoUpdate(String certType, CertVOBTK certVOBTK);

    /**
     * 用户单个查询
     * @param userInfoId
     * @param certType
     * @return
     */
    public CertVOBTK UserInfoSingleQuery(String userInfoId, String certType);

    /**
     * 用户查询
     * @param certType
     * @param orgId
     * @param isContainChildOrg
     * @return
     */
    public UserInfoQueryResponse userInfoQuery(String certType, String orgId, boolean isContainChildOrg);

    /**
     * 用户删除
     * @param userInfoId
     * @return
     */
    public UserInfoDeleteResponse userInfoDelete(String userInfoId);

    /**
     * 密钥恢复申请
     * @param certSN
     * @return
     */
    public RecoverKeyApplyAddResponse recoverKeyApplyAdd(String certSN);
    /**
     * 密钥恢复申请审核
     * @param reqSN
     * @param auditPass
     * @param refuseReason
     * @return
     */
    public RecoverKeyApplyAuditResponse recoverKeyApplyAudit(String reqSN,Boolean auditPass,String refuseReason);

}
