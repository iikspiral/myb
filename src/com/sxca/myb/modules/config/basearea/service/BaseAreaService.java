package com.sxca.myb.modules.config.basearea.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.config.basearea.dao.BaseAreaDao;
import com.sxca.myb.modules.config.basearea.entity.BaseArea;

/**
 * @author lihuilong 
 * @date : 2017年6月29日 下午2:50:01
 */
@Service
@Transactional(readOnly = true)
public class BaseAreaService extends CrudService<BaseAreaDao, BaseArea>{
	
	/**
	 * 获取第一级省级目录列表
	 * @return
	 */
	public List<BaseArea> getFirstArealist(){
		return dao.getFirstArealist();
	}; 
	
	/**
	 * 获取市县一级的地区列表
	 * @return
	 */
	public List<BaseArea> getAreaList(BaseArea baseArea){
		return dao.getAreaList(baseArea);
	};
	
	public BaseArea getAreabyAreaid(BaseArea baseArea){
		return dao.getAreabyAreaid(baseArea);
	}

}
