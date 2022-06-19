package com.boen;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testupload{


    //获取凭证
        public static void main(String[] args) {
            DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai",
                    "LTAI5tK2Fp4LyFQMJPdQtSQM", "a0THp2Nd0dWtG9YEznmZmbtT09BsXA");


            /** use STS Token
             DefaultProfile profile = DefaultProfile.getProfile(
             "<your-region-id>",           // The region ID
             "<your-access-key-id>",       // The AccessKey ID of the RAM account
             "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
             "<your-sts-token>");          // STS Token
             **/
            IAcsClient client = new DefaultAcsClient(profile);

            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId("878d0a4f4e274dfa883c3d9ef9d41a99");

            try {
                GetVideoPlayAuthResponse response = client.getAcsResponse(request);
//                System.out.println(new Gson().toJson(response));
                System.out.println(response.getPlayAuth());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



        //获取视频播放地址
    @Test
    public void testGetPlayInfo() throws ClientException {

        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai",
                "LTAI5tK2Fp4LyFQMJPdQtSQM", "a0THp2Nd0dWtG9YEznmZmbtT09BsXA");



        /** use STS Token
         DefaultProfile profile = DefaultProfile.getProfile(
         "<your-region-id>",           // The region ID
         "<your-access-key-id>",       // The AccessKey ID of the RAM account
         "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
         "<your-sts-token>");          // STS Token
         **/
        IAcsClient client = new DefaultAcsClient(profile);

        GetPlayInfoRequest request = new GetPlayInfoRequest();

        request.setVideoId("a4a6bf9650bc44b68e4732e5ff3fd8ba");

        try {
            GetPlayInfoResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void deleVideoList(){
        List<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);



        String join = StringUtils.join(arrayList, ",");
        System.out.println(join);
    }


}
