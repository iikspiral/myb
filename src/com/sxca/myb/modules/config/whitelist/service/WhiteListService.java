package com.sxca.myb.modules.config.whitelist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.common.utils.StringUtils;
import com.sxca.myb.modules.cert.dao.CertInfoDao;
import com.sxca.myb.modules.cert.entity.CertInfo;
import com.sxca.myb.modules.cert.entity.CertapplyInfo;
import com.sxca.myb.modules.config.whitelist.dao.WhiteListDao;
import com.sxca.myb.modules.config.whitelist.entity.WhiteList;


@Service
@Transactional(readOnly = true)
public class WhiteListService extends CrudService<WhiteListDao, WhiteList> {
	
	@Autowired
	private CertInfoDao certInfoDao;
	
   
	public  WhiteList findUserList (String UserInfoId){
		 return dao.findUserList (UserInfoId);
	 }
	
	public List<WhiteList> findNameList(String projectInfoName){
		return dao.findNameList(projectInfoName);
	}
	public List<WhiteList> findCertList(String certSn){
		return dao.findCertList(certSn);
	}
	
	public  WhiteList getView (String Id){
		 return dao.getView(Id);
	 }
	
	public List<WhiteList> findbyprojectInfoId(String projectInfoId){
		return dao.findbyprojectInfoId(projectInfoId);
	}
	
	
	
	//对已经加入白名单的数据进行甄选，如果项目id存在就禁止再次加入，保证一个项目下只有，一条数据，一条记录
	public boolean findbyprojectInfoId(WhiteList entity ){
		boolean result =true;// 设定一个boolean 类型的开关 目的是将返回值用于判断
		//用拿到的项目id在白名单里进行查询表：有可能一个项目下有多条信息
		List<WhiteList> whitebyList =dao.findbyprojectInfoId(entity.getProjectInfoId());
				if(whitebyList!=null && whitebyList.size()>0){
					for(WhiteList white:whitebyList){
						if(StringUtils.isNotBlank(white.getUserInfoId()) &&
								StringUtils.isNotBlank(white.getCertSn()) &&
								entity.getProjectInfoId().equals(white.getProjectInfoId()) &&
								entity.getCertSn().equals(white.getCertSn()) &&
								entity.getOptType().equals(white.getOptType()) &&
								entity.getIsUsed().equals(white.getIsUsed()) &&
								entity.getIsuseFace().equals(white.getIsuseFace()) &&
								entity.getRemarks().equals(white.getRemarks())
								){ //如果查不到就让他true返回往下走
								result=false;
						}
					}
					return result;
					
				}
		
		return result;
	}
	
	
	public List<WhiteList> findWaitwhiteList(WhiteList whiteList){
		return dao.findWaitwhiteList(whiteList);
	}
	
	
	/**获取已新制的ids
	 * @param projectId
	 * @return
	 */
	public String findCheckIds(String projectId){
		
		List<WhiteList> list = dao.getbyProId(projectId);
		if(list != null && list.size() >0){
			String ids ="";
			for(int i = 0;i < list.size(); i++){
				ids += list.get(i).getUserInfoId()+",";
			}
			return ids;
		}else{
			return null;
		}
	}
	
	
	//获取已变更的certs
		public String findCerts(){
			List<WhiteList> list = dao.getWhiteList();
			if(list != null && list.size() >0){
				String ids ="";
				for(int i = 0;i < list.size(); i++){
					String certSn=list.get(i).getCertSn();
					if(certSn!=null && !"".equals(certSn)){
						ids += certSn+",";
					}
				}
				return ids;
			}else{
				return null;
			}
		}
		
		
		//获取个人证书  个人用户ids，通过正在使用的证书来进行获取
		public String getCorpuseridsByProid(String projectId){
			
			List<CertInfo> certList = new ArrayList<CertInfo>();

			String ids = "";
			//查询使用中
			CertInfo entity = new CertInfo();

			entity.setCertType("userinfo");
			CertapplyInfo certApplyInfo = new CertapplyInfo();
			certApplyInfo.setProjectId(projectId);
			entity.setCertapplyInfo(certApplyInfo);

			List<CertInfo> certInfoList = certInfoDao.findListBySP(entity);
			
			if(certInfoList != null && certInfoList.size() > 0){
				for(CertInfo certInfoOld : certInfoList){
					certList.add(certInfoOld);
				}
			}
			
			
			if(certList!= null && certList.size() > 0){
				for(CertInfo certInfo : certList){
					ids += certInfo.getCertapplyInfo().getUserInfoId()+",";
				}
				if(StringUtils.isNotBlank(ids)){
					return ids;
				}else{
					return null;
				}
			}else{
				return null;
			}
		}
		
		public List<CertInfo> getStatus(String projectId,String userId) {
			
			CertInfo entity = new CertInfo();

			entity.setCertType("userinfo");
			CertapplyInfo certApplyInfo = new CertapplyInfo();
			certApplyInfo.setProjectId(projectId);
			certApplyInfo.setUserInfoId(userId);
			entity.setCertapplyInfo(certApplyInfo);

			List<CertInfo> certInfoList = certInfoDao.findListBySP(entity);
			return certInfoList;
			
		}
	
	
}
