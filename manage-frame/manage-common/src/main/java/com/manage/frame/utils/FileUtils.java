package com.manage.frame.utils;

import com.manage.frame.entity.FileEntity;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/18
 * Time: 9:26
 */
@Component
public class FileUtils {
    @Value("${file.path.upload}")
    private String UPLOAD_PATH;

    //
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
    //    下载

    //    上传
    public void fileUpload(MultipartFile file, String path) throws Exception {
        FileEntity fileEntity = new FileEntity();
        if (file != null) {
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
            String filePath = UPLOAD_PATH + date + path + "/" + fileNewName + fileType;

            //根据拼接的附件存储地址，创建一个目标文件对象
            File targetFile = new File(filePath);
            File pFile = targetFile.getParentFile();
            if (!pFile.exists()) {
                pFile.mkdirs();
            }
            //将上传的文件写入目标文件
            file.transferTo(targetFile);
            Map<String, Object> map = new HashMap<>();

        } else {

        }

    }

    //    生成随机文件名。
    public String getNewFileName(int length) {
        char[] BASE62 = "0123456789".toCharArray();
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

