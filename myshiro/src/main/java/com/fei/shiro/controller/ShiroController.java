package com.fei.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fei
 * @Time：2017年4月15日 下午3:34:27
 * @version 1.0  
 * Function: TODO
 */
@Controller
@RequestMapping("shiro")
public class ShiroController {
	
	@RequestMapping("login")
	public String login(String username,String password){
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if(!currentUser.isAuthenticated()){
			// 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //remember
            token.setRememberMe(true);
            try {
            	System.out.println("1. " + token.hashCode());
            	// 执行登录. 
                currentUser.login(token);
            } 
            // 所有认证时异常的父类. 
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            	System.out.println("登录失败: " + ae.getMessage());
            }
		}
		
		
		return "redirect:/list.jsp";
	}

}
