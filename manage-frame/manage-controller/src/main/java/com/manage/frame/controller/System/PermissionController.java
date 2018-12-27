package com.manage.frame.controller.System;

import com.manage.frame.config.shiro.MyShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/17
 * Time: 15:34
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    /**
     * 给admin用户添加 userInfo:del 权限
     * @param model
     * @return
     */
    @RequestMapping(value = "/addPermission",method = RequestMethod.GET)
    @ResponseBody
    public String addPermission(Model model) {

        //在sys_role_permission 表中  将 删除的权限 关联到admin用户所在的角色
//        roleMapper.addPermission(1,3);

        //添加成功之后 清除缓存
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
        MyShiroRealm shiroRealm = (MyShiroRealm) securityManager.getRealms().iterator().next();
        //清除权限 相关的缓存
        shiroRealm.clearAllCache();
        return "给admin用户添加 userInfo:del 权限成功";

    }

    /**
     * 删除admin用户 userInfo:del 权限
     * @param model
     * @return
     */
    @RequestMapping(value = "/delPermission",method = RequestMethod.GET)
    @ResponseBody
    public String delPermission(Model model) {

        //在sys_role_permission 表中  将 删除的权限 关联到admin用户所在的角色
//        roleMapper.delPermission(1,3);
        //添加成功之后 清除缓存
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
        MyShiroRealm shiroRealm = (MyShiroRealm) securityManager.getRealms().iterator().next();
        //清除权限 相关的缓存
        shiroRealm.clearAllCache();

        return "删除admin用户userInfo:del 权限成功";

    }
}
