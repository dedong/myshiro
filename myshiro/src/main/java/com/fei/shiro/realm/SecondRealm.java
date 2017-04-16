package com.fei.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author fei
 * @Time：2017年4月9日 下午3:38:10
 * @version 1.0
 */
public class SecondRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//System.out.println("doGetAuthenticationInfo" + token.hashCode());
		System.out.println("[second realm doGetAuthenticationInfo]");
		
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		
		String username = usernamePasswordToken.getUsername();
		
		System.out.println("调用service 从数据库中查询"+username+"的用户信息");
		
		if("unknown".equals(username)){
			throw new UnknownAccountException("用户不存在");
		}
		
		if("locked".equals(username)){
			throw new LockedAccountException("用户已经锁定");
		}
		
		Object credentials = null;//"fc1709d0a95a6be30bc5926fdb7f22f4";
		Object principal = username;
		
		if("admin".equals(username)){
			credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06---";
		}
		
		if("user".equals(username)){
			credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718---";
		}
		
		String realmName = getName();
		
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		
		SimpleAuthenticationInfo authenticationInfo = 
				//new SimpleAuthenticationInfo(principal, credentials, realmName);
				new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
				
		return authenticationInfo;
	}
	
	public static void main(String[] args) {
		
		String algorithmName = "SHA1";
		
		String source = "123456";
		
		Object salt = ByteSource.Util.bytes("admin");;
		
		int hashIterations = 1024;
		
		SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
		
		System.out.println(simpleHash);
	}

}
