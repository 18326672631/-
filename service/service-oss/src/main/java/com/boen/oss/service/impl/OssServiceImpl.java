package com.boen.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.boen.oss.service.OssService;
import com.boen.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;


@Service
public class OssServiceImpl implements OssService {




    @Override
    public String uploadFileAvatar(MultipartFile file) {

        String endpoint = ConstantPropertiesUtils.ENDPOINT;
        String accessKeyId =ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret=ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKETNAME;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //文件名


        try {
            // 创建PutObject请求。
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();

            String uuid = UUID.randomUUID().toString();
            //去掉uuid里的-
            String s = uuid.replaceAll("-", "");

            filename = s+filename;

            //使用joda工具类生成当前日期
            String date = new DateTime().toString("yyyy/MM/dd");

            filename = date+"/"+filename;
            /*
            第一个参数：bucket名称
            第二个参数：上传到oss的文件路径和文件名称  自动生成目录
            第三个参数：上传文件输入流
             */
            ossClient.putObject(bucketName, filename, inputStream);
            ossClient.shutdown();

            //返回上传到阿里云的路径地址拼接后返回
            //https://edu-guli-boen.oss-cn-shanghai.aliyuncs.com/a.png
            String url = "https://"+bucketName+"."+endpoint+"/"+filename;
            return url;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }



    }
}
