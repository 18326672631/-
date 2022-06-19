package com.boen.order.Feign;

import com.boen.order.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "ucenter")
public interface UcenterClient {

    @GetMapping("/educenter/apimember/getUserInfo/{userId}")
    public UcenterMemberOrder getUserInfo(@PathVariable(value = "userId") String userId);

}
