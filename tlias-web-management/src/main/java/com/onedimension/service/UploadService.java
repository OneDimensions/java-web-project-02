package com.onedimension.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    String upload(MultipartFile file) throws ClientException, IOException;

}
