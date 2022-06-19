/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.controller;


import com.boen.commonUtils.R;
import com.boen.guli.Feign.VodClinet;
import com.boen.guli.pojo.EduVideo;
import com.boen.guli.service.IEduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author boen
 * @since 2022-04-27
 */
@Api(tags = "小结管理")
@RestController
@RequestMapping("/eduservice/eduvideo")
@CrossOrigin
public class EduVideoController {


    @Autowired
    private IEduVideoService eduVideoService;


    //删除小结
    @Autowired
    private VodClinet vodClinet;


    /**
     * 添加小节
     * @param eduVideo
     * @return
     */
    @PostMapping("/addvideo")
    public R addVide(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    /**
     * 修改小结
     * @param eduVideo
     * @return
     */
    @PostMapping("/updatevideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }

    /**
     * 删除小节
     * @param id
     * @return
     */
    @DeleteMapping("/deletevideo/{id}")
    public R deleteVideo(@PathVariable String id){


        //通过小结查找视频的id
        EduVideo video = eduVideoService.getById(id);

        String videoSourceId = video.getVideoSourceId();

        //删除小结中的视频,调用vod中的方法
        vodClinet.deleteVideo(videoSourceId);

        //先删视频，再删小结，否则删掉小结就查不到数据
        boolean b = eduVideoService.removeById(id);
        if (b){
            return R.ok();
        }
        return R.error();
    }

    //查找小结信息
    @GetMapping("/getvideo/{id}")
    public R getVideo(@PathVariable String id){
        EduVideo video = eduVideoService.getById(id);
        return R.ok().data("video",video);
    }


}
