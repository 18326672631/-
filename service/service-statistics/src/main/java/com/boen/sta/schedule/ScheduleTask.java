package com.boen.sta.schedule;


import com.boen.sta.service.StatisticsDailyService;
import com.boen.sta.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //定时任务，每天1点执行定时
    @Scheduled(cron = "0 0 1 * * ?")
    public void task(){
        //                                                           --上一天的日期
        statisticsDailyService.saveRegister(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }
}
