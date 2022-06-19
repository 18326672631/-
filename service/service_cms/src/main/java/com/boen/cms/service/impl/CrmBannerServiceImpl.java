/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package com.boen.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boen.cms.pojo.CrmBanner;
import com.boen.cms.mapper.CrmBannerMapper;
import com.boen.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-05-04
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    @Cacheable(value = "banner",key = "'bannerList'")
    public List<CrmBanner> getall() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");  //拼接字符串
        List<CrmBanner> list = baseMapper.selectList(wrapper);
        System.out.println("-------从数据库中查找数据----------");
        return list;
    }

}
