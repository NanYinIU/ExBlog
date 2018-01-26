package com.nanyin.config.shiroConfig;


import com.nanyin.model.Users;
import com.nanyin.service.PermissionService;
import com.nanyin.service.RoleService;
import com.nanyin.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
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

    Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Users users = (Users) SecurityUtils.getSubject().getPrincipal();

//        String username1=(String) principalCollection.getPrimaryPrincipal();
//        logger.info(username1);
        String username = users.getLogin_name();

        logger.info(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissionService.findPermissionByName(username));
        logger.info("permission"+permissionService.findPermissionByName(username));
        simpleAuthorizationInfo.setRoles(roleService.findRoleByName(username));
        logger.info("roles"+roleService.findRoleByName(username));
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
//        if(user.getStatus()==0){
//            logger.info("该用户帐号以冻结");
//        }else{
//            logger.info("用户正常登录");
//        }

        String password = user.getPassword();
        //password 使用md5加载
        return new SimpleAuthenticationInfo(user,password,this.getClass().getName());
    }

    /**
     * 使用md5算法加密
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroUtil.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }
}
