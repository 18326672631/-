package com.boen.guli.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boen.commonUtils.R;
import com.boen.guli.pojo.EduCourse;
import com.boen.guli.pojo.EduTeacher;
import com.boen.guli.service.IEduCourseService;
import com.boen.guli.service.IEduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherFront")
@CrossOrigin
public class TeacherFrontController {


    @Autowired
    private IEduTeacherService eduTeacherService;

    @Autowired
    private IEduCourseService eduCourseService;





    /**
     * 讲师分页
     * @param currentPage
     * @param pagesize
     * @return
     */
    @GetMapping("/getTeacherList/{currentPage}/{pagesize}")
    public R getFrontTeacherList(@PathVariable Integer currentPage,@PathVariable Integer  pagesize){

        Map<String, Object> map = eduTeacherService.pageList(currentPage, pagesize);
        return R.ok().data("itms",map);

    }

    /**
     * 通过id查找教师信息和对应课程信息
     * @return
     */
    @GetMapping("/getTeacherFrontInfo/{id}")
    public R getTeacherFront(@PathVariable String id){

        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);

        EduTeacher teacherInfo = eduTeacherService.getOne(queryWrapper);


        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id", id);
        List<EduCourse> courses = eduCourseService.list(courseQueryWrapper);
        return R.ok().data("teacherInfo",teacherInfo).data("courses",courses);


    }

}
