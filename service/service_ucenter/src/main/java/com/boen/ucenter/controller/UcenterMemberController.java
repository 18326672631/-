/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.ucenter.controller;


import com.boen.commonUtils.JwtUtils;
import com.boen.commonUtils.R;
import com.boen.order.UcenterMemberOrder;
import com.boen.ucenter.pojo.UcenterMember;
import com.boen.ucenter.pojo.VO.registVo;
import com.boen.ucenter.service.IUcenterMemberService;
import com.netflix.client.http.HttpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author boen
 * @since 2022-05-03
 */
@Api(tags = "注册登录服务")
@RestController
@RequestMapping("/educenter/apimember")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private IUcenterMemberService ucenterMemberService;


    @ApiOperation("登录接口")
    @PostMapping("/login")
    public R loginUser(@RequestBody UcenterMember ucenterMember){

        String token = ucenterMemberService.loginUser(ucenterMember);

        return R.ok().data("token",token);

    }


    @ApiOperation("注册接口")
    @PostMapping("/regist")
    public R registUser(@RequestBody registVo registVo){

        boolean isRegist = ucenterMemberService.registUser(registVo);


        if (isRegist) return R.ok();
        else return R.error().message("注册失败");

    }

    //根绝token获取用户信息
    @GetMapping("/getMemberInfo")
    public R getUserInfo(HttpServletRequest request){

        String id = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember menber = ucenterMemberService.getById(id);


        return  R.ok().data("userInfo",menber);
    }



    //根据id获取用户信息
    @GetMapping("/getUserInfofromId/{id}")
    public R getUserInfofromId(@PathVariable String id){

        UcenterMember byId = ucenterMemberService.getById(id);
        return R.ok().data("userInfo",byId);

    }


    //根据用户id获取用户信息
    @GetMapping("/getUserInfo/{userId}")
    public UcenterMemberOrder getUserInfo(@PathVariable String userId){
        UcenterMember ucenterMember = ucenterMemberService.getById(userId);
        UcenterMemberOrder order = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember,order);
        return order;
    }

    @GetMapping("/getResigterCounter/{day}")
    public R getResisteroftoDay(@PathVariable String day ){
        int register = ucenterMemberService.getResisterOftoday(day);
        return R.ok().data("register",register);
    }

}
