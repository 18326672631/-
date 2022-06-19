/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.controller;


import com.boen.commonUtils.R;
import com.boen.guli.pojo.FrontVo.FrontCourseInfoVo;
import com.boen.guli.pojo.vo.CourseInfoVo;
import com.boen.guli.pojo.vo.publishVo;
import com.boen.guli.service.IEduCourseService;
import com.boen.order.FrontCourseInfoVoOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author boen
 * @since 2022-04-27
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private IEduCourseService courseService;


    /**
     * 添加课程基本信息方法
     * @param courseInfoVo
     * @return
     */
    @ApiOperation("新增课程信息")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        String cid = courseService.addCourseInfo(courseInfoVo);
        return R.ok().data("id",cid);
    }



    @ApiOperation("获取课程信息")
    @GetMapping("/getCourseforId/{id}")
    public R getCourseInfo(@PathVariable String id){

        CourseInfoVo courseInfoVo = courseService.getCourseInfo(id);

        return R.ok().data("courseinfo",courseInfoVo);
    }


    @ApiOperation("更新课程信息")
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        courseService.updateCourseInfo(courseInfoVo);

        return R.ok();
    }


    //获取课程信息
    @GetMapping("/getpublishInfo/{courseId}")
    public R getPublishInfo(@PathVariable String courseId){

        publishVo publishVo = courseService.getPublishInfo(courseId);

        return R.ok().data("publishinfo",publishVo);

    }


    //发布课程
    @PutMapping("/publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId){

        boolean flag = courseService.publishCourse(courseId);

        if (flag) return R.ok();
        return R.error();
    }

    @GetMapping("/getCourseInfo/{courseId}")
    public FrontCourseInfoVoOrder getCourseInfofromId(@PathVariable String courseId){

        FrontCourseInfoVo allCourseInfo = courseService.getAllCourseInfo(courseId);
        FrontCourseInfoVoOrder infoVoOrder = new FrontCourseInfoVoOrder();
        BeanUtils.copyProperties(allCourseInfo,infoVoOrder);
        return infoVoOrder;

    }






}
