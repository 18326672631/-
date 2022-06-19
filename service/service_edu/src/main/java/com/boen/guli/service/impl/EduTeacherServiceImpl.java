/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boen.guli.mapper.EduTeacherMapper;
import com.boen.guli.pojo.EduTeacher;
import com.boen.guli.service.IEduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author boen
 * @since 2022-04-20
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements IEduTeacherService {


    //前端分页
    @Override
    public Map<String,Object> pageList(Integer curretpage, Integer pagesize) {

        Page<EduTeacher> page = new Page<>(curretpage,pagesize);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        Page<EduTeacher> teacherPage = this.baseMapper.selectPage(page, queryWrapper);
        //返回分页对象
        HashMap<String, Object> HashMap = new HashMap<>();
        HashMap.put("teachers",teacherPage.getRecords());
        HashMap.put("total",teacherPage.getTotal());
        HashMap.put("hasNext",teacherPage.hasNext());
        HashMap.put("hasPrevious",teacherPage.hasPrevious());
        HashMap.put("pages",teacherPage.getPages());
        HashMap.put("current",teacherPage.getCurrent());
        return HashMap;

    }
}
