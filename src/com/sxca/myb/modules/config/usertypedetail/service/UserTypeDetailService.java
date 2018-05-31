package com.sxca.myb.modules.config.usertypedetail.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxca.myb.common.service.CrudService;
import com.sxca.myb.modules.config.usertypedetail.dao.UsertypeDetailDao;
import com.sxca.myb.modules.config.usertypedetail.entity.UserTypeDetail;

/**
 * @author lihuilong
 * @date : 2017年6月21日 下午3:37:39
 */

@Service
@Transactional(readOnly = true)
public class UserTypeDetailService extends
		CrudService<UsertypeDetailDao, UserTypeDetail> {

}
