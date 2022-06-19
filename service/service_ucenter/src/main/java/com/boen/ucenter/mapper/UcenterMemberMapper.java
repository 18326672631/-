/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boen.ucenter.pojo.UcenterMember;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author boen
 * @since 2022-05-03
 */
@Mapper
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    public int getRegister(String day);

}
