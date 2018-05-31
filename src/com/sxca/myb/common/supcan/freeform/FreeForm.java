/**
 * Copyright &copy; 2016-2016 <a href="http://www.sxca.com.cn">山西CA</a> All rights reserved.
 */
package com.sxca.myb.common.supcan.freeform;

import com.sxca.myb.common.supcan.common.Common;
import com.sxca.myb.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 硕正FreeForm
 * @author WangZhen
 * @version 2013-11-04
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}
