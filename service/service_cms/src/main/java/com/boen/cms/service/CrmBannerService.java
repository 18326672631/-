/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package com.boen.cms.service;

import com.boen.cms.pojo.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-05-04
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getall();

}
