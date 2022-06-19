/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.order.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boen.commonUtils.R;
import com.boen.order.Service.OrderService;
import com.boen.order.Utils.HttpClient;
import com.boen.order.pojo.Order;
import com.boen.order.pojo.PayLog;
import com.boen.order.mapper.PayLogMapper;
import com.boen.order.Service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import io.swagger.models.Xml;
import javafx.util.converter.LocalDateStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author boen
 * @since 2022-06-01
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private OrderService orderService;


    /**
     * 生成二维码接口
     * @param orderNo
     * @return
     */
    @Override
    public Map createNative(String orderNo) {


        //client设置参数
        try {

            //根据id获取订单信息
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_no",orderNo);
            Order order = orderService.getOne(queryWrapper);
            //使用map生成二维码需要参数
            Map m = new HashMap();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            m.put("trade_type", "NATIVE");
            //发送http请求，传递参数xml格式，微信支付提供的固定地址
            //2、HTTPClient来根据URL访问第三方接口并且传递参数
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));

            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            Map<String,Object> map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));

            return map;

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();

        }
    }

    /**
     * 获取订单支付状态
     * @return
     */
    @Override
    public Map<String, String> getOrderStatus(String orderNo) {


        Map m = new HashMap<>();
        m.put("appid", "wx74862e0dfcf69954");
        m.put("mch_id", "1558950191");
        m.put("out_trade_no", orderNo);
        m.put("nonce_str", WXPayUtil.generateNonceStr());

        //2、设置请求
        HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
        try {
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            String xml = client.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(xml);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 更新订单状态
     * @param map
     */
    @Override
    public void updateOrder(Map<String, String> map) {


        //从map中获取订单号
        String OrderNo = map.get("out_trade_no");
        System.out.println("订单号：  "+OrderNo);

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", OrderNo);

        Order one = orderService.getOne(queryWrapper);
        if (one.getStatus()==1)return;

        one.setStatus(1);
        one.setGmtModified(new Date());
        //更新状态信息
        orderService.updateById(one);

        //记录支付日志
        PayLog payLog = new PayLog();
        payLog.setOrderNo(one.getOrderNo());//支付订单号
        payLog.setPayTime(new Date());
        payLog.setPayType(1);//支付类型
        payLog.setTotalFee(one.getTotalFee());//总金额(分)
        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));   //其他属性
        payLog.setGmtCreate(new Date());
        payLog.setGmtModified(new Date());

        this.baseMapper.insert(payLog);


    }
}
