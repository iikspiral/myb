/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sxca.myb.modules.sms.dao;

import com.sxca.myb.common.persistence.CrudDao;
import com.sxca.myb.common.persistence.annotation.MyBatisDao;
import com.sxca.myb.modules.sms.entity.SMSLogs;


/**
 * 短信日志DAO接口
 *  @author glq
 * @version 2017-04-21
 */
@MyBatisDao
public interface SMSLogsDao extends CrudDao<SMSLogs> {

}
