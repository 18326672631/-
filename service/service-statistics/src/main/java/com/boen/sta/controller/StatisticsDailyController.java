/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package com.boen.sta.controller;


import com.boen.commonUtils.R;
import com.boen.sta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author wangbowen
 * @since 2022-06-13
 */
@RestController
@RequestMapping("/staservice/sta")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;



    @GetMapping("/createRegister/{day}")
    private R createRegister(@PathVariable String day){

        statisticsDailyService.saveRegister(day);
        return R.ok();
    }



    @GetMapping("/getCounterShow/{begin}/{end}/{type}")
    public R getCounterShow(@PathVariable String begin,
                            @PathVariable String end,
                            @PathVariable String type){
        Map<String,Object> map = statisticsDailyService.getCounterShow(type,begin,end);
        return R.ok().data("chart",map);
    }

}

