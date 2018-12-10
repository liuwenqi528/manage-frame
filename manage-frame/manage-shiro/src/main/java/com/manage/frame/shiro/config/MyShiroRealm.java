package com.manage.frame.shiro.config;



import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.manage.frame.shiro.entity.SysPermission;
import com.manage.frame.shiro.entity.SysRole;
import com.manage.frame.shiro.entity.UserInfo;
import com.manage.frame.shiro.service.SysPermissionService;
import com.manage.frame.shiro.service.SysRoleService;
import com.manage.frame.shiro.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 18:44
 * 自定义权限匹配和账号密码匹配
 */

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
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
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
//        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        EntityWrapper<UserInfo> ew = new EntityWrapper<>();
        ew.eq("username",username);
        System.out.println(ew.getSqlSegment());
        UserInfo userInfo = userInfoService.selectOne(ew);
//        System.out.println("----->>userInfo="+userInfo);
        if (userInfo == null) {
            return null;
        }
        if (userInfo.getState() == 1) { //账户冻结
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

}
