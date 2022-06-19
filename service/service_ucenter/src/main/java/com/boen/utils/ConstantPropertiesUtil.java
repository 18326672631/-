package com.boen.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class ConstantPropertiesUtil implements InitializingBean {


    @Value("${wx.open.app_id}")
    private String app_id;
    @Value("${wx.open.app_secret}")
    private String app_secret;

    @Value("${wx.open.redirecturl}")
    private String redirectUrl;


    public static String WX_OPEN_APP_ID;

    public static String WX_OPEN_APP_SECRET;

    public static String WX_OPEN_REDIRECTURL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID=this.app_id;
        WX_OPEN_APP_SECRET=this.app_secret;
        WX_OPEN_REDIRECTURL=this.redirectUrl;
    }
}
