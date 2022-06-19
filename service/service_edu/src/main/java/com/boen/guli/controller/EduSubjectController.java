/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.controller;


import com.boen.commonUtils.R;
import com.boen.guli.pojo.subject.OneSubject;
import com.boen.guli.service.IEduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author boen
 * @since 2022-04-25
 */
@Api(tags = "科目管理")
@RestController
@RequestMapping(value = "/eduservice/edusubject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private IEduSubjectService eduSubjectService;


    @PostMapping("/save")
    public R save(MultipartFile file){
        eduSubjectService.saveFile(file);
        return R.ok();
    }


    @GetMapping("/getsubject")
    public R getSubject(){
        List<OneSubject> subjectList = eduSubjectService.getallsubject();

        return R.ok().data("list",subjectList);
    }

}
