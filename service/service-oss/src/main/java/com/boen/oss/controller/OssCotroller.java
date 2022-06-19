package com.boen.oss.controller;

import com.boen.commonUtils.R;
import com.boen.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssCotroller {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    public R uploadOssFile(MultipartFile file){
        //获取上传文件
        String url = ossService.uploadFileAvatar(file);
        //返回上传文件的路径
        return R.ok().data("url",url);
    }


}
