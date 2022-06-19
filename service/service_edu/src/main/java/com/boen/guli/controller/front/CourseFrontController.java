package com.boen.guli.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boen.commonUtils.JwtUtils;
import com.boen.commonUtils.R;
import com.boen.guli.Feign.OrderClient;
import com.boen.guli.pojo.EduCourse;
import com.boen.guli.pojo.FrontVo.FrontCourseInfoVo;
import com.boen.guli.pojo.FrontVo.FrontCourseVo;
import com.boen.guli.pojo.chapter.ChapterVo;
import com.boen.guli.service.IEduChapterService;
import com.boen.guli.service.IEduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RequestMapping("/eduservice/frontcourse")
@RestController
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private IEduCourseService eduCourseService;


    @Autowired
    private IEduChapterService eduChapterService;


    @Autowired
    private OrderClient orderClient;


    @PostMapping("/pageQuery/{currentpage}/{pagesize}")
    public R PageCourseQuery(@PathVariable Integer currentpage,@PathVariable Integer pagesize,
                             @RequestBody(required = false) FrontCourseVo FrontCourseVo){
        Page<EduCourse> page = new Page<>(currentpage,pagesize);

        Map<String,Object> map  = eduCourseService.queryPage(page,FrontCourseVo);

        return R.ok().data("map",map);

    }


    /**
     * 查询课程详情信息
     * @param id
     * @return
     */
    @GetMapping("/getFrontCourseInfo/{id}")
    public R getAllCourseInfo(@PathVariable String id, HttpServletRequest request){

        //查找课程相关信息
        FrontCourseInfoVo courseVo= eduCourseService.getAllCourseInfo(id);

        //查找章节小结信息
        List<ChapterVo> chapterVideoofCourse = eduChapterService.getChapterVideoofCourse(id);

        Boolean ispayCourse = orderClient.isPayCourse(JwtUtils.getMemberIdByJwtToken(request), id);

        return R.ok().data("courseWebVo",courseVo).data("ChapterVideoList",chapterVideoofCourse).data("idBuyCourse",ispayCourse);
    }
}
