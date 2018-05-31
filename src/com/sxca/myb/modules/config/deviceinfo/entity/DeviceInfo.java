package com.sxca.myb.modules.config.deviceinfo.entity;

import com.sxca.myb.common.persistence.DataEntity;

/**
 * @author Yophw 系统配置实体
 *
 */
public class DeviceInfo extends DataEntity<DeviceInfo> {

	private static final long serialVersionUID = 1L;
	
	private String deviceNameeng;  //设备名称
	private String deviceIp;//设备ip
	private String devicePort;//设备端口
	//	-------------------------------------------------
	public String getDeviceNameeng() {
		return deviceNameeng;
	}
	public void setDeviceNameeng(String deviceNameeng) {
		this.deviceNameeng = deviceNameeng;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getDevicePort() {
		return devicePort;
	}
	public void setDevicePort(String devicePort) {
		this.devicePort = devicePort;
	}

	
	
	

}
