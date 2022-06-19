package com.boen.ucenter.controller;

import com.alibaba.fastjson.JSON;
import com.boen.commonUtils.JwtUtils;
import com.boen.ucenter.pojo.UcenterMember;
import com.boen.ucenter.service.IUcenterMemberService;
import com.boen.utils.ConstantPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@Controller
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
public class wxApiController {

    @Autowired
    private IUcenterMemberService ucenterMemberService;


    @GetMapping("/callback")
    public String callback(String code,String state)
    {

        //通过回调地址得到code
//        System.out.println("code: "+code);
//        System.out.println("state: "+state);
        //固定地址    2通过得到的code请求这个地址得到access_token和open_id
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                code
        );

        RestTemplate restTemplate = new RestTemplate();
        //使用restTemplete发送一个get请求  第一个参数：请求地址 第二个参数：返回消息体的数据类型
        ResponseEntity<String> forEntity = restTemplate.getForEntity(accessTokenUrl, String.class);
        //得到返回结果，是一个key-value结构
        String result = forEntity.getBody();
        //使用FastJson将返回的的字符串转换成map集合
        HashMap hashMap = JSON.parseObject(result, HashMap.class);
        //得到map中的access_token和open_id
        String access_token = (String) hashMap.get("access_token");
        String openid = (String) hashMap.get("openid");




        //通过openid查找数据库看是否有该用户
        UcenterMember ucenterMember = ucenterMemberService.queryUser(openid);
        //数据库中不存在此用户
        if (ucenterMember == null)
        {

            //3.通过得到的access_token和openid再去请求微信提供固定地址，得到扫码人的身份信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl,
                    access_token,
                    openid);
            String userInfo = restTemplate.getForObject(userInfoUrl, String.class);

            HashMap userInfoMap = JSON.parseObject(userInfo, HashMap.class);

            String nickname = (String) userInfoMap.get("nickname");//昵称
//          Integer sex = (String) userInfoMap.get("sex");
            String headimgurl = (String) userInfoMap.get("headimgurl");//头像

            ucenterMember=new UcenterMember();
            ucenterMember.setNickname(nickname);
            ucenterMember.setOpenid(openid);
            ucenterMember.setAvatar(headimgurl);
            ucenterMemberService.save(ucenterMember);

            String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), nickname);
            return "redirect:http://localhost:3000?token="+jwtToken;
        }
        else {
            String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
            return "redirect:http://localhost:3000?token="+jwtToken;
        }

//        System.out.println("access_token:  "+access_token);
//        System.out.println("openid:  "+openid);


//        return "redirect:http://localhost:3000";

    }

    @GetMapping("/login")
    public String login(){

        //1.请求固定地址 返回code
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        String redirecturl = ConstantPropertiesUtil.WX_OPEN_REDIRECTURL;

        try {
            redirecturl = URLEncoder.encode(redirecturl, "UTF-8");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        String state = "imhelen";
        //字符串拼接
        String url = String.format(baseUrl, ConstantPropertiesUtil.WX_OPEN_APP_ID
                , redirecturl,  state
        );

        return "redirect:"+url;


    }
}
