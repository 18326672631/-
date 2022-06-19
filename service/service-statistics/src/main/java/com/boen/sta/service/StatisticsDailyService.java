/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package com.boen.sta.service;

import com.boen.sta.pojo.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author wangbowen
 * @since 2022-06-13
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void saveRegister(String day);

    Map<String, Object> getCounterShow(String type, String begin, String end);

}
