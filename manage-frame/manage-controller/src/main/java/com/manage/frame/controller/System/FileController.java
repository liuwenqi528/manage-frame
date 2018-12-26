package com.manage.frame.controller.System;

import com.manage.frame.entity.FileEntity;
import com.manage.frame.service.FileService;
import com.manage.frame.utils.FileUtils;
import com.manage.frame.utils.ResponseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
    private FileService fileService;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam fileUpload(MultipartFile file,String module) {
        try {

            FileEntity fileEntity = FileUtils.fileUpload(file, module);
            fileService.insert(fileEntity);
            return ResponseParam.success().data(fileEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail();
        }
    }

    /**
     * 图片回显
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/fileDownload/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseParam fileDownload(@PathVariable String id , HttpServletResponse response) {
        try {
            FileEntity fileEntity = fileService.getOne(id);
            FileUtils.fileDownload(fileEntity.getFilePath(),response);
            return ResponseParam.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail();
        }
    }

}
