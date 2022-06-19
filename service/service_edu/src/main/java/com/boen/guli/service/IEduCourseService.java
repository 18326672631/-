/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boen.guli.pojo.EduCourse;
import com.boen.guli.pojo.FrontVo.FrontCourseInfoVo;
import com.boen.guli.pojo.FrontVo.FrontCourseVo;
import com.boen.guli.pojo.vo.CourseInfoVo;
import com.boen.guli.pojo.vo.publishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author boen
 * @since 2022-04-27
 */
public interface IEduCourseService extends IService<EduCourse> {

    //新增课程信息
    String addCourseInfo(CourseInfoVo courseInfoVo);

    //根据id获取课程信息
    CourseInfoVo getCourseInfo(String id);

    //更新课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    publishVo getPublishInfo(String courseId);

    boolean publishCourse(String courseId);

    Map<String, Object> queryPage(Page<EduCourse> page, FrontCourseVo frontCourseVo);

    FrontCourseInfoVo getAllCourseInfo(String id);

}
