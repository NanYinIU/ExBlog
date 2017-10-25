package com.nanyin.config.shiroConfig;


import com.nanyin.model.Users;
import com.nanyin.service.PermissionService;
import com.nanyin.service.RoleService;
import com.nanyin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by NanYin on 2017-09-23 下午10:28.
 * 包名： com.nanyin.config.shiroConfig
 * 类描述：
 */
public class MyRaalm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username =(String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissionService.findPermissionByName(username));
        simpleAuthorizationInfo.setRoles(roleService.findRoleByName(username));
        return simpleAuthorizationInfo;
    }
    /*
    登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        Users user = userService.findUsersByName(name);
        String password = user.getPassword();

        return new SimpleAuthenticationInfo(user,password,this.getClass().getName());
    }
}
