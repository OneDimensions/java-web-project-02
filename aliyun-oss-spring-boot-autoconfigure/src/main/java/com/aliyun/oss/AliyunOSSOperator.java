package com.aliyun.oss;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 此类的实例化自动装配写在了AliyunOSSAutoConfiguration中
 */
public class AliyunOSSOperator {

    // // 配置信息写在了application.yml中
    // // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    // @Value("${aliyun.oss.endpoint}")
    // private String endpoint;
    // // 填写Bucket名称，例如examplebucket。
    // @Value("${aliyun.oss.bucketName}")
    // private String bucketName;
    // // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
    // @Value("${aliyun.oss.region}")
    // private String region;

    // 配置信息写在了application.yml中
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    private AliyunOSSProperties aliyunOSSProperties;

    public AliyunOSSOperator (@Autowired AliyunOSSProperties aliyunOSSProperties) {
        this.aliyunOSSProperties = aliyunOSSProperties;
    }

    private OSS createOssClient() throws ClientException {

        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建OSSClient实例。
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        return OSSClientBuilder.create()
                .endpoint(aliyunOSSProperties.getEndpoint())
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(aliyunOSSProperties.getRegion())
                .build();
    }

    public String upload(byte[] fileBytes, String originalFilename) throws ClientException {

        String endpoint = aliyunOSSProperties.getEndpoint();
        String bucketName = aliyunOSSProperties.getBucketName();

        // 上传文件夹
        String fileDir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = fileDir + "/" + UUID.randomUUID()
                + originalFilename.substring(originalFilename.lastIndexOf('.'));

        // 创建OSSClient实例。
        OSS ossClient = createOssClient();

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(fileBytes));
            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } finally {
            ossClient.shutdown();
        }

        String[] endpoints = endpoint.split("//");
        // 组装访问地址
        return endpoints[0] + "//" + bucketName + "." + endpoints[1] + "/" + objectName;
    }
}
