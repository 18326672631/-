package com.boen.guli.Feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("order")
@Component
public interface OrderClient {


    @GetMapping("/orderservice/order/isPayCourse/{menberId}/{CourseId}")
    public Boolean isPayCourse(@PathVariable("menberId")String menberId, @PathVariable("CourseId")String CourseId);

}
