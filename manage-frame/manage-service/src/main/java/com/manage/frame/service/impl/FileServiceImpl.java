package com.manage.frame.service.impl;

import com.manage.frame.base.BaseServiceImpl;
import com.manage.frame.entity.FileEntity;
import com.manage.frame.service.FileService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/19
 * Time: 15:51
 */
@Service
public class FileServiceImpl extends BaseServiceImpl<FileEntity, String> implements FileService {

    /**
     * 保存对象
     *
     * @param fileEntity
     */
    @Override
    public int insert(FileEntity fileEntity) {
        fileEntity.setId(UUID.randomUUID().toString());
        fileEntity.setUploadTime(new Date());
        fileEntity.setDelFlag("0");

        return super.insert(fileEntity);
    }
}
