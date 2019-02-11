package com.manage.frame.config.shiro;


import com.manage.frame.entity.UserEntity;
import com.manage.frame.service.LoginService;
import com.manage.frame.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 18:44
 * 自定义权限匹配和账号密码匹配
 */

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
//        try {
//            List<SysRole> roles = sysRoleService.selectRoleByUser(userInfo);
//            for (SysRole role : roles) {
//                authorizationInfo.addRole(role.getRole());
//            }
//            List<SysPermission> sysPermissions = sysPermissionService.selectPermByUser(userInfo);
//            for (SysPermission perm : sysPermissions) {
//                authorizationInfo.addStringPermission(perm.getPermission());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取用户的输入的账号.
        String username = usernamePasswordToken.getUsername();
        //获取token中的密码
//        String password = new String((char[]) usernamePasswordToken.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserEntity ew = new UserEntity();
        ew.setUsername(username);
        UserEntity userInfo = loginService.login(ew);
        if (userInfo == null) {
            throw new UnknownAccountException("账号不存在");
        }
        if (userInfo.getState() == 1) { //账户冻结
            throw new LockedAccountException("账户冻结");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户信息
                //数据库中存储的密码
                userInfo.getPassword().toCharArray(), //密码
                //如果shiro未配置加密方式，则此处传入加密后的密码
//                new Md5Hash(password,userInfo.getUsername()).toHex(),
                //传入盐值
                ByteSource.Util.bytes(userInfo.getSalt()),//salt=username
                getName()  //realm name
        );
        return authenticationInfo;
    }

    /***************缓存清理****************/
    /**
     * 重写方法,清除当前用户的的 授权缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }


}
