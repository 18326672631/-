/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package com.boen.cms.controller;


import com.boen.cms.pojo.CrmBanner;
import com.boen.cms.service.CrmBannerService;
import com.boen.commonUtils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-05-04
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class CrmBannerFrontController {

    @Autowired
    private CrmBannerService bannerService;


    //前端页面显示,查询所有banner

    @Cacheable(key = "banner",value = "'selectBanner'")
    @GetMapping("/getallbanner")
    public R getAllBanner(){

        List<CrmBanner> list= bannerService.getall();


        return R.ok().data("list",list);
    }

}

