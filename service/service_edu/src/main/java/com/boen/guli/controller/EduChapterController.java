/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.controller;


import com.boen.commonUtils.R;
import com.boen.guli.pojo.EduChapter;
import com.boen.guli.pojo.chapter.ChapterVo;
import com.boen.guli.service.IEduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author boen
 * @since 2022-04-27
 */
@Api(tags = "章节管理")
@RestController
@RequestMapping("/eduservice/educhapter")
@CrossOrigin
public class EduChapterController {


    @Autowired
    private IEduChapterService eduChapterService;

    /**
     * 查找课程大纲
     * @param courseid
     * @return
     */
    @GetMapping("/getchaptervideo/{courseid}")
    @ApiOperation(value = "查找课程大纲列表")
    public R getChapterVideo(@PathVariable String courseid){

        List<ChapterVo> chapterVoList = eduChapterService.getChapterVideoofCourse(courseid);

        return R.ok().data("chapterlist",chapterVoList);
    }

    /**
     * 添加章节
     * @param chapter
     * @return
     */
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter chapter){

        eduChapterService.save(chapter);
        return R.ok();
    }

    /**
     * 修改章节信息
     * @param chapter
     * @return
     */
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter){

        eduChapterService.updateById(chapter);
        return R.ok();
    }

    /**
     * 删除章节
     * @return
     */
    @DeleteMapping("/deleteChapter/{id}")
    public R deleteChapter(@PathVariable String id){

        boolean flag = eduChapterService.deleteChapter(id);

        if (flag){
            return R.ok();
        }
        else {
            return R.error().message("请先删除小结");
        }

    }


    /**
     * 通过id获取章节信息
     * @param id
     * @return
     */
    @GetMapping("/getChapterInfo/{id}")
     public R getChapterInfo(@PathVariable String id){


        EduChapter chapter = eduChapterService.getById(id);

        return R.ok().data("chapter",chapter);
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public R deleteCourse(@PathVariable String courseId)
    {
        return R.ok();
    }


}
