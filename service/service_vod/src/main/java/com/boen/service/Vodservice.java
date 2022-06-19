package com.boen.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boen.commonUtils.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Vodservice {
    //上传视频的方法
    public String upload(MultipartFile file);

    void deleteVideo(String videoid);

    void deleteVideoList(List videoList);
}
