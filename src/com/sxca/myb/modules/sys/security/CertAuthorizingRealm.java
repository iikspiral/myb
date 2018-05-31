package com.sxca.myb.modules.sys.security;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sxca.myb.common.config.Global;
import com.sxca.myb.common.utils.Encodes;
import com.sxca.myb.common.utils.SpringContextHolder;
import com.sxca.myb.common.web.Servlets;
import com.sxca.myb.modules.sys.entity.Menu;
import com.sxca.myb.modules.sys.entity.Role;
import com.sxca.myb.modules.sys.entity.User;
import com.sxca.myb.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.sxca.myb.modules.sys.service.SystemService;
import com.sxca.myb.modules.sys.utils.LogUtils;
import com.sxca.myb.modules.sys.utils.UserUtils;


public class CertAuthorizingRealm  extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SystemService systemService1;
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String certsubject=token.getUsername();
		
		User user = getSystemService().getUserByCertSubject(certsubject);
		if (user != null) {
			if (Global.NO.equals(user.getLoginFlag())) {
				throw new AuthenticationException("msg:该已帐号已登录禁止登录.");
			}
			byte[] salt = Encodes
					.decodeHex(user.getPassword().substring(0, 16));
			return new SimpleAuthenticationInfo(new Principal(user,
					token.isMobileLogin()), user.getPassword().substring(16),
					ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);
		// 获取当前已登录的用户
		if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))){
			Collection<Session> sessions = getSystemService().getSessionDao().getActiveSessions(true, principal, UserUtils.getSession());
			if (sessions.size() > 0){
				// 如果是登录进来的，则踢出已在线用户
				if (UserUtils.getSubject().isAuthenticated()){
					for (Session session : sessions){
						getSystemService().getSessionDao().delete(session);
					}
				}
				// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
				else{
					UserUtils.getSubject().logout();
					throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
				}
			}
		}
		User user = getSystemService().getUserByLoginName(principal.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Menu> list = UserUtils.getMenuList();
			for (Menu menu : list){
				if (StringUtils.isNotBlank(menu.getPermission())){
					// 添加基于Permission的权限信息
					for (String permission : StringUtils.split(menu.getPermission(),",")){
						info.addStringPermission(permission);
					}
				}
			}
			// 添加用户权限
			info.addStringPermission("user");
			// 添加用户角色信息
			for (Role role : user.getRoleList()){
				info.addRole(role.getEnname());
			}
			// 更新登录IP和时间
			getSystemService().updateUserLoginInfo(user);
			// 记录登录日志
			LogUtils.saveLog(Servlets.getRequest(), "系统登录");
			return info;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取系统业务对象
	 */
	public SystemService getSystemService() {
		if (systemService1 == null){
			systemService1 = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService1;
	}
	
	
	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		CertSimpleCredentialsMatcher match=new CertSimpleCredentialsMatcher();
		setCredentialsMatcher(match);
	}
	

}
