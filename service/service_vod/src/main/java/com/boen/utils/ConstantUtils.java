package com.boen.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ConstantUtils implements InitializingBean {

    @Value("${aliyun.vod.file.accessKeyId}")
    private String keyId;


    @Value("${aliyun.vod.file.accessKeySecret}")
    private String keySecret;


    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {

        ACCESS_KEY_ID = keyId;

        ACCESS_KEY_SECRET = keySecret;

    }

}
