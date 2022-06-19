package com.boen.guli.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boen.commonUtils.R;
import com.boen.guli.pojo.EduCourse;
import com.boen.guli.pojo.EduTeacher;
import com.boen.guli.service.IEduCourseService;
import com.boen.guli.service.IEduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndecFrontController {

    @Autowired
    private IEduCourseService eduCourseService;

    @Autowired
    private IEduTeacherService eduTeacherService;


    /**
     * 查询钱8条热门视频，前四个热门讲师
     * @return
     */
    @GetMapping("/index")
    public R index(){
        //查询前8条热门视频
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();

        courseQueryWrapper.orderByDesc("view_count");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> eduCourses = eduCourseService.list(courseQueryWrapper);


        //查询前4条热门讲师
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> eduTeachers = eduTeacherService.list(teacherQueryWrapper);

        return R.ok().data("eduCourses",eduCourses).data("eduTeachers",eduTeachers);

    }


}
