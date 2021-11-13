package com.lidaxian.config;

import com.lidaxian.pojo.User;
import com.lidaxian.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的  UserRealm extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=> 授权doGetAuthorizationInfo");
//        //SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//
//        info.addStringPermission("user:add");

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();//拿到user对象

        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());
        //
        return info;
//        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        //连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());

        if (user==null) {//没有这个人
            return null;//UnknownAccountException
        }
        //可以加密：MD5加密：e10adc3949ba59abbe56e057f20f883e  MD5盐值加密
        //密码认证，shiro做~,加密了
        return new  SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
