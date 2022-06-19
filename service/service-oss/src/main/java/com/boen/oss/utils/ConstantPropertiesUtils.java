package com.boen.oss.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtils implements InitializingBean {


    @Value("${aliyun.oss.file.endpoint}")
    private String  endpoint;
    @Value("${aliyun.oss.file.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.file.KeySecret}")
    private String keySecret;
    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;

    public static String ENDPOINT;
    public static String ACCESS_KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT=endpoint;
        ACCESS_KEY_ID=accessKeyId;
        KEY_SECRET=keySecret;
        BUCKETNAME=bucketName;

    }
}
