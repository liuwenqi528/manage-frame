package com.manage.frame.utils;

import com.manage.frame.entity.FileEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
@Slf4j
public class FileUtils {
    //    @Value("${file.path.upload}")
    //    读取文件时的路径
    private static final String FILE_PATH = "/fileEcho/";
    //    存储地址
    private static final String UPLOAD_PATH = "e://file/manage/";
    //    编码使用的字符
    private static final char[] BASE62 = "0123456789".toCharArray();


    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(UUID.randomUUID().toString());
        int machineId = 2;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().replaceAll("-", "").hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        System.out.println(machineId + String.format("%015d", hashCodeV));
        System.out.println(new Md5Hash("" + hashCodeV, "123456", 12).toHex());
        System.out.println("-----------------------");
        char[] BASE62 = "0123456789".toCharArray();
        byte[] input = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(input);
        char[] chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
        }
        System.out.println(new String(chars));

    }

    /**
     * 回显图片
     * @param req
     * @param resp
     */
    public static void fileEcho(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String filepath = req.getRequestURI();
        int index = filepath.indexOf(FILE_PATH);
        if(index >= 0) {
            filepath = filepath.substring(index +FILE_PATH.length());
        }
            filepath = UriUtils.decode(filepath, "UTF-8");
        File file = new File(UPLOAD_PATH+ filepath);
        try {
            FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            resp.setHeader("Content-Type", "application/octet-stream");
        } catch (FileNotFoundException e) {
//            req.setAttribute("exception", new FileNotFoundException("请求的文件不存在"));
//            req.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(req, resp);
        }
    }
    //    下载
    public static void fileDownload(String filepath, HttpServletResponse resp) throws  ServletException ,IOException{
        int index = filepath.indexOf(FILE_PATH);
        if(index >= 0) {
            filepath = filepath.substring(index +FILE_PATH.length());
        }
        File file = new File(UPLOAD_PATH +filepath);
        try {
            FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
            resp.setHeader("Content-Type", "application/octet-stream");
        } catch (FileNotFoundException e) {
//            req.setAttribute("exception", new FileNotFoundException("请求的文件不存在"));
//            req.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(req, resp);
        }
    }
    //    上传
    public static FileEntity fileUpload(MultipartFile file, String path) throws Exception {
        FileEntity fileEntity = null;
        if (file != null) {
            fileEntity = new FileEntity();
            fileEntity.setModule(path);
            //上传附件
            //获取上传附件的文件名称
            String fileName = file.getOriginalFilename();
            fileEntity.setFileOldName(fileName);
            String fileNewName = getNewFileName(16);
            //获取上传附件的文件类型
            String type = file.getContentType();
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            fileEntity.setFileNewName(fileNewName + fileType);
            //获取当前年/月
            SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy/MM/");
            String date = dateFormat.format(new Date());
            //拼接附件存储的地址/
            String filePath =  path + date + fileNewName + fileType;

            //根据拼接的附件存储地址，创建一个目标文件对象
            File targetFile = new File(UPLOAD_PATH +filePath);
            File pFile = targetFile.getParentFile();
            if (!pFile.exists()) {
                pFile.mkdirs();
            }
            //将上传的文件写入目标文件
            file.transferTo(targetFile);
            fileEntity.setFilePath(FILE_PATH+filePath);
        }
        return fileEntity;
    }

    //    生成随机文件名。
    private static String getNewFileName(int length) {

        byte[] input = new byte[length];
        SecureRandom random = new SecureRandom();
        random.nextBytes(input);
        char[] chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
        }
        return new String(chars);
    }


}