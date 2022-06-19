/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boen.ExceptionHandler.GuliException;
import com.boen.guli.mapper.EduCourseMapper;
import com.boen.guli.pojo.EduCourse;
import com.boen.guli.pojo.EduCourseDescription;
import com.boen.guli.pojo.FrontVo.FrontCourseInfoVo;
import com.boen.guli.pojo.FrontVo.FrontCourseVo;
import com.boen.guli.pojo.vo.CourseInfoVo;
import com.boen.guli.pojo.vo.publishVo;
import com.boen.guli.service.IEduCourseDescriptionService;
import com.boen.guli.service.IEduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author boen
 * @since 2022-04-27
 */
@Slf4j
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements IEduCourseService {


    @Autowired
    private IEduCourseDescriptionService service;

    @Autowired
    private EduCourseMapper eduCourseMapper;




    /**
     * 添加课程
     * @param courseInfoVo
     */
    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,course);

        int insert = this.baseMapper.insert(course);

        String cid = course.getId();

        EduCourseDescription description = new EduCourseDescription();

        //id和课程相同
        description.setId(cid);
        description.setDescription(courseInfoVo.getDescription());
        service.save(description);

        return cid;

    }

    /**
     * 获得课程信息
     * @param id
     * @return
     */
    @Override
    public CourseInfoVo getCourseInfo(String id) {
        EduCourse course = this.baseMapper.selectById(id);

        CourseInfoVo courseInfoVo = new CourseInfoVo();

        BeanUtils.copyProperties(course,courseInfoVo);

        //获得课程简介
        EduCourseDescription description = service.getById(id);

        courseInfoVo.setDescription(description.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {

        try {
            EduCourse eduCourse = new EduCourse();

            //更新课程信息
            BeanUtils.copyProperties(courseInfoVo,eduCourse);
            int i = baseMapper.updateById(eduCourse);
            if (i<=0)throw new GuliException(500, "添加课程信息失败");

            //更新课程描述信息
            EduCourseDescription eduCourseDescription = new EduCourseDescription();

            BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);

            boolean b = service.updateById(eduCourseDescription);
            if (!b) throw new GuliException(500,"添加课程描述信息失败");
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取课程确认信息
     * @param courseId
     * @return
     */
    @Override
    public publishVo getPublishInfo(String courseId) {

        publishVo publishVo = eduCourseMapper.getpublishCourseInfo(courseId);
        return publishVo;

    }

    //发布课程
    @Override
    public boolean publishCourse(String courseId) {

        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        int i = baseMapper.updateById(eduCourse);
        return i>0;
    }


    /**
     * 前端课程查询带分页
     * @param page
     * @param frontCourseVo
     * @return
     */
    @Override
    public Map<String, Object> queryPage(Page<EduCourse> page, FrontCourseVo frontCourseVo) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(frontCourseVo.getSubjectParentId()))
        {
            queryWrapper.eq("subject_parent_id",frontCourseVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(frontCourseVo.getSubjectId())){
            queryWrapper.eq("subject_id",frontCourseVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(frontCourseVo.getBuyCountSort()))
        {
            queryWrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(frontCourseVo.getGmtCreateSort()))
        {
            queryWrapper.orderByDesc("gmt_create");
        }
        if(!StringUtils.isEmpty(frontCourseVo.getPriceSort()))
        {
            queryWrapper.orderByDesc("price");
        }

        Page<EduCourse> pages = this.baseMapper.selectPage(page, queryWrapper);

        HashMap<String, Object> HashMap = new HashMap<>();
        HashMap.put("courses",pages.getRecords());
        HashMap.put("total",pages.getTotal());
        HashMap.put("hasNext",pages.hasNext());
        HashMap.put("hasPrevious",pages.hasPrevious());
        HashMap.put("pages",pages.getPages());
        HashMap.put("current",pages.getCurrent());

        return HashMap;


    }

    @Override
    public FrontCourseInfoVo getAllCourseInfo(String id) {

        FrontCourseInfoVo frontAllCourseInfo = eduCourseMapper.getFrontAllCourseInfo(id);
        return frontAllCourseInfo;

    }


}
