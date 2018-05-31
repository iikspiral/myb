package com.sxca.myb.common.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sxca.myb.common.utils.DateUtils;

@Service
@Lazy(false)
public class SchedulerService {
	/**
	 * 每分钟执行一次
	 */
	@Scheduled(cron = "0 0/1 15,* * * ?")
	private void deleteSendBack(){
		DateUtils.formatDateTime(DateUtils.now());
	}
}
