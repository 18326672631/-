/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.order.Service;

import com.boen.order.pojo.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author boen
 * @since 2022-06-01
 */
public interface PayLogService extends IService<PayLog> {

    //获取微信支付二维码
    Map createNative(String orderNo);

    //获取订单状态
    Map<String, String> getOrderStatus(String OrderNo);

    //更新订单
    void updateOrder(Map<String, String> map);
}
