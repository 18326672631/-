/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.boen.commonUtils.R;
import com.boen.order.Service.OrderService;
import com.boen.order.pojo.Order;
import com.boen.order.pojo.PayLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author boen
 * @since 2022-06-01
 */
@RestController
@RequestMapping("/orderservice/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/create/{courseId}")
    public R CreateOrder(@PathVariable String courseId, HttpServletRequest request){

        String orderId = orderService.cresteOrder(courseId,request);
        if (orderId==null)
        {
            return R.error().message("请登录后再访问");
        }
        return R.ok().data("orderId",orderId);

    }


    @GetMapping("/getorderinfo/{orderid}")
    public R getOrderInfo(@PathVariable String orderid){

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderid);
        Order order = orderService.getOne(queryWrapper);
        return R.ok().data("order",order);

    }

    @GetMapping("/isPayCourse/{menberId}/{CourseId}")
    public Boolean isPayCourse(@PathVariable String menberId,@PathVariable String CourseId){

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",menberId);
        queryWrapper.eq("course_id",CourseId);

        List<Order> list = orderService.list(queryWrapper);

        int flag=0;

        for (Order order : list) {
            if (order.getStatus()==1)
            {
                flag=1;
            }
        }
        return flag == 1;
    }



}
