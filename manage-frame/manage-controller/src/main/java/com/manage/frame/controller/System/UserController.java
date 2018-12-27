package com.manage.frame.controller.System;

import com.manage.frame.entity.UserEntity;
import com.manage.frame.service.UserService;
import com.manage.frame.utils.ResponseParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据ID获取用户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam get(@PathVariable("id") String id) {
        try {
            return ResponseParam.success().data(userService.get(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail();
        }
    }

    /**
     * 根据条件查询
     * @param entity
     * @return
     */
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

    /**
     * 全查
     * @return
     */
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

    /**
     * 保存--添加保存和修改保存
     * @param entity
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam save(@RequestBody UserEntity entity) {
        try {
            return ResponseParam.success("保存成功").data(userService.save(entity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail("保存失败");
        }
    }


//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseParam update(@RequestBody UserEntity entity) {
//        try {
//            return ResponseParam.success("修改成功").data(userService.update(entity));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseParam.fail("修改失败");
//        }
//    }

    /**
     * 根据ID单一删除
     * @param id
     * @return
     */
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
