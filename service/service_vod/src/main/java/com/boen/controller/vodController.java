package com.boen.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.ecs.model.v20140526.ReInitVolumeRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.boen.ExceptionHandler.GuliException;
import com.boen.commonUtils.R;
import com.boen.service.Vodservice;
import com.boen.utils.AliyunUtils;
import com.boen.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduvod/vod")
public class vodController {

    @Autowired
    private Vodservice vodservice;


    /**
     * 上传视频
     * @param file
     * @return
     */
    @PostMapping("/uploadvideo")
    public R uploadVideo(MultipartFile file){
        String VideoId = vodservice.upload(file);
        String originalFilename = file.getOriginalFilename();

        return R.ok().data("videoid",VideoId).data("originalfilename",originalFilename);

    }

    /**
     * 删除视频
     * @param videoid
     * @return
     */
    @DeleteMapping("/deletevideo/{videoid}")
    public R deleteVideo(@PathVariable String videoid){
        vodservice.deleteVideo(videoid);
        return R.ok();
    }


    /**
     * 批量删除视频
     * @param VideoList
     * @return
     */
    @DeleteMapping("/deletevideoList")
    public R deletebatch(@RequestParam("/VideoList")List VideoList){
        vodservice.deleteVideoList(VideoList);
        return R.ok();
    }


    /**
     * 通过视频id获取播放凭证
     * @param VideoId
     * @return
     */
    @GetMapping("/getplayauth/{VideoId}")
    public R getVideoPlayAuth(@PathVariable String VideoId) {

        try {
            DefaultAcsClient Client =
                    AliyunUtils.initVodClient(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);

            System.out.println(ConstantUtils.ACCESS_KEY_ID);
            System.out.println(ConstantUtils.ACCESS_KEY_SECRET);

            GetVideoPlayAuthRequest Request = new GetVideoPlayAuthRequest();

            Request.setVideoId(VideoId);
            GetVideoPlayAuthResponse acsResponse = Client.getAcsResponse(Request);
            String playAuth = acsResponse.getPlayAuth();
            return R.ok().data("palyAuth", playAuth);

        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001,"获取凭证失败");
        }

    }



}
