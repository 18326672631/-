/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.order.Service;

import com.boen.order.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author boen
 * @since 2022-06-01
 */
public interface OrderService extends IService<Order> {

    String cresteOrder(String courseId, HttpServletRequest request);
}
