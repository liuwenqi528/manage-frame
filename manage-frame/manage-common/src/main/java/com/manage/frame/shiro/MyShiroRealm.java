package com.manage.frame.shiro;


import com.manage.frame.entity.SysPermission;
import com.manage.frame.entity.SysRole;
import com.manage.frame.entity.UserEntity;
import com.manage.frame.entity.UserInfo;
import com.manage.frame.service.SysPermissionService;
import com.manage.frame.service.SysRoleService;
import com.manage.frame.service.UserService;
import org.apache.shiro.authc.AccountException;
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
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        try {
            List<SysRole> roles = sysRoleService.selectRoleByUser(userInfo);
            for (SysRole role : roles) {
                authorizationInfo.addRole(role.getRole());
            }
            List<SysPermission> sysPermissions = sysPermissionService.selectPermByUser(userInfo);
            for (SysPermission perm : sysPermissions) {
                authorizationInfo.addStringPermission(perm.getPermission());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取用户的输入的账号.
        String username = usernamePasswordToken.getUsername();
        System.out.println(usernamePasswordToken.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserEntity ew = new UserEntity();
        ew.setUsername(username);
        UserEntity userInfo = userService.login(ew);
//        System.out.println("----->>userInfo="+userInfo);
        if (userInfo == null) {
            throw new UnknownAccountException("账号不存在");
        }
        if (userInfo.getState() == 1) { //账户冻结
            throw new LockedAccountException();
        }
        UserInfo ui = new UserInfo();
        ui.setUsername(userInfo.getUsername());
        ui.setName(userInfo.getTruename());
        ui.setPassword(userInfo.getPassword());
        ui.setUid(userInfo.getId());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户信息
                userInfo.getPassword().toCharArray(), //密码
//                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

}
