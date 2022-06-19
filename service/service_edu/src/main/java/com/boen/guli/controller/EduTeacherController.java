/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boen.ExceptionHandler.GuliException;
import com.boen.commonUtils.R;
import com.boen.guli.pojo.EduTeacher;
import com.boen.guli.pojo.vo.TeacherQuery;
import com.boen.guli.service.IEduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author boen
 * @since 2022-04-20
 */
@RestController
@RequestMapping("/eduservice/eduteacher")
@Api(tags = "讲师管理")
@CrossOrigin
public class EduTeacherController {



    @Autowired
    private IEduTeacherService iEduTeacherService;


    /**
     * 查询所有
     * @return
     */
    @ApiOperation(value = "获取所有讲师列表")
    @GetMapping("/findall")
    public R findall(){
        //获取自定义异常
//        try {
//            int i = 10/0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new GuliException(500,"执行了自定义异常");
//        }

        List<EduTeacher> list = iEduTeacherService.list(null);
        return R.ok().data("itms",list);
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @ApiOperation(value = "删除某个讲师")
    @DeleteMapping("/{id}")
    public R deleted(
            @ApiParam(name = "id",value = "讲师id",required = true)
            @PathVariable String id){
        boolean flag = iEduTeacherService.removeById(id);
        if (flag)
        {
            return R.ok();
        }
        return R.error();
    }


    /**
     * 组合条件查询
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     */
    @ApiOperation("条件分页")
    @PostMapping("/page/{current}/{limit}")
    public R pageQueryCondition(
            @PathVariable int current,
            @PathVariable int limit,
            //查询条件非必要
            @RequestBody(required = false) TeacherQuery teacherQuery){

        Page<EduTeacher> page = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(teacherQuery.getName()))
        {
            wrapper.like("name",teacherQuery.getName());
        }
        if (!StringUtils.isEmpty(teacherQuery.getLevel())){
            wrapper.eq("level",teacherQuery.getLevel());
        }
        if (!StringUtils.isEmpty(teacherQuery.getBegin()))
        {
            wrapper.ge("gmt_create",teacherQuery.getBegin());
        }
        if (!StringUtils.isEmpty(teacherQuery.getEnd())){
            //小于end
            wrapper.le("gmt_modified",teacherQuery.getEnd());
        }
        //根据创建时间降序排列，最新创建的排在前面
        wrapper.orderByDesc("gmt_create");

        //调用方法实现分页查询
        Page<EduTeacher> pages = iEduTeacherService.page(page, wrapper);

        //总记录数
        long total = pages.getTotal();
        List<EduTeacher> records = pages.getRecords();
        return R.ok().data("total",total).data("rows",records);


    }


    /**
     * 新增讲师
     * @param eduTeacher
     * @return
     */
    @ApiOperation("新增讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){

        boolean flag = iEduTeacherService.save(eduTeacher);
        if (flag){
            return R.ok();
        }
        else return R.error();
    }


    /**
     * 修改讲师信息根据id
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value = "更新讲师信息")
    @PostMapping("/updateTeacher")                      //@RequestBody需要Post
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){

        boolean update = iEduTeacherService.updateById(eduTeacher);
        if (update){
            return R.ok();
        }
        else return R.error();
    }



    //
    @ApiOperation(value = "通过id查找讲师")
    @GetMapping("/getTeacher/{id}")
    public R getTeacher(@PathVariable String id){

        EduTeacher teacher = iEduTeacherService.getById(id);
        if (teacher!=null)
        {
            return R.ok().data("teacher",teacher);
        }
        else return R.error().message("找不到该讲师");
    }




}
