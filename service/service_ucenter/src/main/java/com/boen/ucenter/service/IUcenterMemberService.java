/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boen.ucenter.pojo.UcenterMember;
import com.boen.ucenter.pojo.VO.registVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author boen
 * @since 2022-05-03
 */
public interface IUcenterMemberService extends IService<UcenterMember> {

    String loginUser(UcenterMember ucenterMember);

    boolean registUser(registVo registVo);

    UcenterMember queryUser(String openid);

    int getResisterOftoday(String day);

}
