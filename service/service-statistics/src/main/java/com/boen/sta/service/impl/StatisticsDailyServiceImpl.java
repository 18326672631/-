/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package com.boen.sta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boen.commonUtils.R;
import org.springframework.beans.factory.annotation.Autowired;
import com.boen.sta.FeignClient.ucenterClient;
import com.boen.sta.pojo.StatisticsDaily;
import com.boen.sta.mapper.StatisticsDailyMapper;
import com.boen.sta.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author wangbowen
 * @since 2022-06-13
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {


    @Autowired
    private ucenterClient ucenterClient;



    @Override
    public void saveRegister(String day) {

        //先删掉这一天的结果再次添加新的查询结果，省去更新的步骤
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        this.baseMapper.delete(queryWrapper.eq("date_calculated",day));


        R resisteroftoDay = ucenterClient.getResisteroftoDay(day);
        Map<String, Object> data = resisteroftoDay.getData();
        int  register = (int)data.get("register");

       StatisticsDaily statisticsDaily = new StatisticsDaily();

       statisticsDaily.setRegisterNum(register);


        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setRegisterNum(register);


        Random random = new Random();
        statisticsDaily.setLoginNum(random.nextInt(200));
        statisticsDaily.setCourseNum(random.nextInt(100));
        statisticsDaily.setVideoViewNum(random.nextInt(1000));


        this.baseMapper.insert(statisticsDaily);

    }

    @Override
    public Map<String, Object> getCounterShow(String type, String begin, String end) {

        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated",begin,end);
        List<StatisticsDaily> statisticsDailies = this.baseMapper.selectList(queryWrapper);
        //前端需要的是两个数组，一个x轴日期，一个y轴数量
        ArrayList<String> days = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();

        for (StatisticsDaily statisticsDaily : statisticsDailies) {
            days.add(statisticsDaily.getDateCalculated());
            switch (type) {
                case "register_num":
                    counts.add(statisticsDaily.getRegisterNum());break;
                case "login_num":
                    counts.add(statisticsDaily.getLoginNum());break;
                case "video_view_num":
                    counts.add(statisticsDaily.getVideoViewNum());break;
                case "course_num":
                    counts.add(statisticsDaily.getCourseNum());break;
                default:
                    break;
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("days",days);
        map.put("counts",counts);
        return map;

    }
}
