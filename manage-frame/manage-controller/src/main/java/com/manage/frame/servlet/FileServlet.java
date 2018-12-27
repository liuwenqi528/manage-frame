package com.manage.frame.servlet;

import com.manage.frame.entity.FileEntity;
import com.manage.frame.service.FileService;
import com.manage.frame.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/20
 * Time: 10:37
 */

@WebServlet(urlPatterns = {"/fileEcho/*"},loadOnStartup = 1,name = "fileEcho")
public class FileServlet extends HttpServlet {

    @Autowired
    private FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("初始化servlet");
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

//            FileEntity fileEntity = fileService.getOne(id);
            FileUtils.fileEcho(req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
