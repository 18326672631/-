/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boen.commonUtils.R;
import com.boen.order.Service.PayLogService;
import com.boen.order.pojo.PayLog;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author boen
 * @since 2022-06-01
 */
@RestController
@RequestMapping("/orderservice/paylog")
@CrossOrigin
public class PayLogController {


    @Autowired
    private PayLogService payLogService;

    //生成微信支付二维码
    @GetMapping("/createpaylog/{orderNo}")
    public R CreateNative(@PathVariable String orderNo){

        //返回信息，包含二维码和其他信息
        Map map  = payLogService.createNative(orderNo);
        return R.ok().data("map",map);
    }


    //获取支付状态
    @GetMapping("/queryOrderStatus/{OrderNo}")
    public R quetyOrderStatus(@PathVariable String OrderNo){

        //获取订单状态
        Map<String,String> map   = payLogService.getOrderStatus(OrderNo);
        System.out.println("查询订单状态"+map);

        if (map ==null)
        {
            return R.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS"))
        {
            //更新订单
            payLogService.updateOrder(map);
            return R.ok().message("支付成功");
        }


        return R.ok().message("支付中").code(25000);
    }










}
