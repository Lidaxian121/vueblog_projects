package com.lidaxian.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShrioFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /*
            anon：无需认证就可以访问
            authc：必须认证了才能访问
            user：必须拥有 记住我 功能才能用
            perms：拥有对某个资源的权限才能访问

           role：拥有对某个角色的权限才能访问
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
         */

        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();




        //授权,正常情况下，没有授权会跳转到未授权页面
//        filterMap.put("/blog/toSerch","perms[user:blogserch]");
        filterMap.put("/blog/toAdd","perms[user:edit]");
        filterMap.put("/blog/toDelete","perms[user:edit]");

        filterMap.put("/blog/*","authc");


        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录的请求
        bean.setLoginUrl("/toLogin");
        //未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }
    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象,需要自定义类1
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
