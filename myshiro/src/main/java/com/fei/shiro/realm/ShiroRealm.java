package com.fei.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

/**
 * @author fei
 * @Time：2017年4月9日 下午3:38:10
 * @version 1.0
 */
public class ShiroRealm implements Realm {

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(AuthenticationToken token) {
		// TODO Auto-generated method stub
		return false;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}
