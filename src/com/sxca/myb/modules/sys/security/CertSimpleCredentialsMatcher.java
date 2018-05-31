package com.sxca.myb.modules.sys.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CertSimpleCredentialsMatcher extends SimpleCredentialsMatcher {

	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		return true;
	}

}
