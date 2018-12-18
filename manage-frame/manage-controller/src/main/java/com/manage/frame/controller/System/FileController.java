package com.manage.frame.controller.System;

import com.manage.frame.utils.FileUtils;
import com.manage.frame.utils.ResponseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/18
 * Time: 13:23
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileUtils fileUtils;
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam fileUpload(MultipartFile file){
        try {
            fileUtils.fileUpload(file,"userPhoto");
            return ResponseParam.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseParam.fail();
        }
    }
}
