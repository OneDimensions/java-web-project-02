package com.aliyun.oss;

// 用于配置阿里云OSS的属性, 具体的值在application.xml中配置, 这里将其封装成一个JavaBean, 使用时直接@Autowired注入即可

import org.springframework.boot.context.properties.ConfigurationProperties;

// 此注解用于说明配置文件中的前缀
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;

    @Override
    public String toString() {
        return "AliyunOSSProperties{" +
                "endpoint='" + endpoint + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
