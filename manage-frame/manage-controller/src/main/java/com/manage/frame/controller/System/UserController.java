package com.manage.frame.controller.System;

import com.manage.frame.entity.UserEntity;
import com.manage.frame.service.UserService;
import com.manage.frame.utils.ResponseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制层
 * Created by
 * User: Liuwq
 * Date: 2018/12/7
 * Time: 13:50
 */
@Controller
@RequestMapping("/user")
//@CrossOrigin(
//        origins = {"http://localhost:8081", "null"},
//        maxAge = 18000,
//        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE},
//        allowedHeaders = "*",
//        allowCredentials = "true"
//)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam get(@PathVariable("id") String id) {
        try {
            return ResponseParam.fail("系统错误").data(userService.get(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail();
        }
    }

    @RequestMapping(value = "/findByQuery", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam findByQuery(@RequestBody UserEntity entity) {
        try {
            return ResponseParam.success().data(userService.findByQuery(entity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail();
        }
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam findAll() {
        try {
            return ResponseParam.success().data(userService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail();
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam insert(@RequestBody UserEntity entity) {
        try {
            return ResponseParam.success("添加成功").data(userService.insert(entity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail("添加失败");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam update(@RequestBody UserEntity entity) {
        try {
            return ResponseParam.success("修改成功").data(userService.update(entity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail("修改失败");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam delete(@PathVariable("id") String id) {
        try {
            return ResponseParam.success("删除成功").data(userService.delete(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail("删除失败");
        }
    }
}
