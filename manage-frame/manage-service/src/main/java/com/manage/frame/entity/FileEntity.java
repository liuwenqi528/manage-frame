package com.manage.frame.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/18
 * Time: 11:23
 */
@Data
public class FileEntity {
    private String id;
    private String fileNewName;
    private String fileOldName;
    private String filePath;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;
    private String module;//
}
