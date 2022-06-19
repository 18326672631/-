/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.order.Service.impl;

import com.boen.ExceptionHandler.GuliException;
import com.boen.commonUtils.JwtUtils;
import com.boen.commonUtils.OrderNoUtil;
import com.boen.order.Feign.CourseClient;
import com.boen.order.Feign.UcenterClient;
import com.boen.order.FrontCourseInfoVoOrder;
import com.boen.order.UcenterMemberOrder;
import com.boen.order.pojo.Order;
import com.boen.order.mapper.OrderMapper;
import com.boen.order.Service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author boen
 * @since 2022-06-01
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String cresteOrder(String courseId, HttpServletRequest request) {


        FrontCourseInfoVoOrder courseInfo = courseClient.getCourseInfofromId(courseId);

        if (request.getHeader("token")==null)
        {

            return null;
        }

        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        UcenterMemberOrder userInfo = ucenterClient.getUserInfo(memberId);

        Order order = new Order();

        order.setCourseCover(courseInfo.getCover());
        order.setCourseId(courseInfo.getId());  //课程ID
        order.setCourseTitle(courseInfo.getTitle());
        order.setTeacherName(courseInfo.getTeacherName());
        order.setTotalFee(courseInfo.getPrice());
        order.setMemberId(userInfo.getId());
        order.setMobile(userInfo.getMobile());
        order.setNickname(userInfo.getNickname());
        order.setGmtCreate(new Date());
        order.setGmtModified(new Date());

//        '订单状态（0：未支付 1：已支付）',
        order.setStatus(0);

//        '支付类型（1：微信 2：支付宝）',
        order.setPayType(1);
        order.setOrderNo(OrderNoUtil.getOrderNo());
        this.baseMapper.insert(order);


        return order.getOrderNo();
    }
}
