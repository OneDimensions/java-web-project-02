package com.onedimension.utils;

// 用于配置阿里云OSS的属性, 具体的值在application.xml中配置, 这里将其封装成一个JavaBean, 使用时直接@Autowired注入即可

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
// 此注解用于说明配置文件中的前缀
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}
