package com.onedimension.controller;

import com.aliyuncs.exceptions.ClientException;
import com.onedimension.pojo.Result;
import com.onedimension.service.UploadService;
import com.onedimension.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;
    // 上传本地
    // @PostMapping("/upload")
    // public Result upload(MultipartFile file) throws IOException {
    //     log.info("上传文件: {}", file);
    //
    //     // 这个file默认会先存在临时目录中
    //
    //     // 获取项目根路径
    //     String rootPath = System.getProperty("user.dir");
    //     log.info("项目根路径: {}", rootPath);
    //
    //     // 获取原始文件名
    //     String originalFilename = file.getOriginalFilename();
    //
    //     // 创建上传目录
    //     String uploadDirPath = rootPath + "/tlias-web-management/src/main/resources/static/upload/";
    //     File uploadDir = new File(uploadDirPath);
    //     if (!uploadDir.exists()) {
    //         uploadDir.mkdirs();
    //     }
    //
    //     // 生成唯一文件名
    //     String fileExt = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf("."));
    //     String newFileName = UUID.randomUUID().toString() + fileExt;
    //
    //     // 保存文件到本地
    //     file.transferTo(new File(uploadDirPath + newFileName));
    //
    //     return ResultUtil.success();
    // }


    // 上传阿里云
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException, ClientException {
        log.info("上传文件: {}", file);
        String filePath = uploadService.upload(file);
        return ResultUtil.success(filePath, "上传成功");
    }
}
