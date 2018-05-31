package com.sxca.myb.modules.config.basearea.dao;

import java.util.List;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.config.basearea.entity.BaseArea;

/**
 * @author lihuilong 
 * @date : 2017年6月29日 下午2:48:15
 */
@MyBatisDao
public interface BaseAreaDao extends CrudDao<BaseArea>{

	/**
	 * 获取第一级省级目录列表
	 * @return
	 */
	public List<BaseArea> getFirstArealist(); 
	
	/**
	 * 获取市县一级的地区列表
	 * @return
	 */
	public List<BaseArea> getAreaList(BaseArea baseArea);
	
	
	public BaseArea getAreabyAreaid(BaseArea baseArea);
	
}
