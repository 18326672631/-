/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boen.ExceptionHandler.GuliException;
import com.boen.commonUtils.JwtUtils;
import com.boen.commonUtils.MD5;
import com.boen.ucenter.mapper.UcenterMemberMapper;
import com.boen.ucenter.pojo.UcenterMember;
import com.boen.ucenter.pojo.VO.registVo;
import com.boen.ucenter.service.IUcenterMemberService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 * @author boen
 * @since 2022-05-03
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements IUcenterMemberService {



    @Autowired
    private UcenterMemberMapper ucenterMemberMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;



    @Override
    public String loginUser(UcenterMember ucenterMember) {


        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();


        //先进行手机号和密码的验证
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password))
        {
            throw new GuliException(20001,"手机号或密码为空");
        }

        //再差数据库啊，看是否有改手机号
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember Usermember = this.baseMapper.selectOne(queryWrapper);

        if (Usermember ==null){
            throw new GuliException(20002,"没有改手机号");
        }

        if (!MD5.encrypt(password).equals(Usermember.getPassword()))
        {
            System.out.println("加密密码"+MD5.encrypt(password));
            System.out.println("数据库密码"+Usermember.getPassword());
            throw new GuliException(20003,"密码错误");
        }


        //判断是都禁用
        if (Usermember.getIsDisabled()){
            throw new GuliException(20004,"账号不可用");
        }


        //通过id，和用户名生成token
        String jwtToken = JwtUtils.getJwtToken(Usermember.getId(), Usermember.getNickname());

        return jwtToken;
    }


    /**
     * 注册用户
     * @param registVo
     * @return
     */
    @Override
    public boolean registUser(registVo registVo) {

        String mobile = registVo.getMobile();
        String nickname = registVo.getNickname();
        String password = registVo.getPassword();
        String code = registVo.getCode();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)
                          || StringUtils.isEmpty(code))
        {
            throw new GuliException(20001,"信息不完整");
        }

        //判断验证码是否相等
        String redisCode = redisTemplate.opsForValue().get(mobile);
        System.out.println("redis:  "+redisCode);
        System.out.println("code:   "+code);
        if (!code.equals(redisCode))
        {
            throw new GuliException(20001,"验证码错误");
        }


        //判断手机号(邮箱)是否存在,存在的话注册失败
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);

        Integer count = baseMapper.selectCount(wrapper);

        if (count!=0) throw new GuliException(20001,"手机号或邮箱已注册");


        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);


        baseMapper.insert(member);
        return true;
    }

    //通过openid查询用户
    @Override
    public UcenterMember queryUser(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        UcenterMember ucenterMember = this.baseMapper.selectOne(queryWrapper);
        return ucenterMember;
    }

    @Override
    public int getResisterOftoday(String day) {

        int register = ucenterMemberMapper.getRegister(day);

        return register;
    }
}
