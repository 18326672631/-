package com.boen.mail.service.impl;


import com.boen.commonUtils.RandomUtil;
import com.boen.mail.service.mailservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

@Service
public class mailServiceImpl implements mailservice {




    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void senmail(String mail) {

        String sixBitRandom = RandomUtil.getSixBitRandom();


        MimeMessage Message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(Message, true);//支持附件
            helper.setFrom("wang_boen@163.com");
            helper.setTo(mail);
            helper.setText("【验证码:】  "+sixBitRandom+"  [有效期5分钟]",true);   //支持html格式

            //添加附件
//            File file = new File("D:\\java\\Springboot\\Springboot_mail\\te.html");
//            helper.addAttachment("附件1",file);

            javaMailSender.send(Message);
            redisTemplate.opsForValue().set(mail,sixBitRandom,5, TimeUnit.MINUTES);

            String code = redisTemplate.opsForValue().get(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}
