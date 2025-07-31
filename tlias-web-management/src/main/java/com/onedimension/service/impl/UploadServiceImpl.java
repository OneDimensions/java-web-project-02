package com.onedimension.service.impl;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import com.onedimension.service.UploadService;
import com.onedimension.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @Override
    public String upload(MultipartFile file) throws ClientException, IOException {
        byte[] bytes = file.getBytes();
        String originalFilename = file.getOriginalFilename();
        String filePath = aliyunOSSOperator.upload(bytes, originalFilename);
        return filePath;
    }
}
