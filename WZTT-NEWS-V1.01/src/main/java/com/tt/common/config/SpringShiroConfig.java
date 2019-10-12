package com.tt.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 注解描述的类为一个配置对象,
 * 此对象也会交给spring管理
 * @author TEDU
 *
 */
@Configuration
public class SpringShiroConfig {
	/**@Bean 描述的方法,其返回值会交给spring管理
	 * @Bean 一般应用在整合第三bean资源时*/
	@Bean
	public SecurityManager newSecurityManager(
			@Autowired Realm realm) {
		DefaultWebSecurityManager sManager = 
				new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		sManager.setRememberMeManager(newRememberMeManager());
		return sManager;
	}
	@Autowired
	@Bean("shiroFilterFactory")
	public ShiroFilterFactoryBean newShiroFilterFactoryBean(
			SecurityManager securityManager) {
		//构建bean对象,通过此对象创建bean工厂
		ShiroFilterFactoryBean sfBean = 
				new ShiroFilterFactoryBean();
		//注入SecurityManager
		sfBean.setSecurityManager(securityManager);
		//设置登录url
		sfBean.setLoginUrl("/doLoginUI");
		//设置过滤规则
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		//静态资源允许匿名访问"anon"
		map.put("/bower_components/**", "anon");
		map.put("/build/**", "anon");
		map.put("/dist/**", "anon");
		map.put("/plugins/**", "anon");
		map.put("/user/doLogin","anon");
		map.put("/doLogout","logout");
		//map.put("/doIndexUI","anon");
		//除了匿名访问的资源,其他都要认证("authc")后访问
		map.put("/**", "user");
		sfBean.setFilterChainDefinitionMap(map);
		return sfBean;
	}

	public SimpleCookie newCookie() {
		SimpleCookie c=new SimpleCookie("rememberMe");
		c.setMaxAge(10*60);
		return c;
	}
	public CookieRememberMeManager newRememberMeManager() {
		CookieRememberMeManager cManager=
				new CookieRememberMeManager();
		cManager.setCookie(newCookie());
		return cManager;
	}


}
