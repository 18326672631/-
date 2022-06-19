package com.boen.sta.FeignClient;

import com.boen.commonUtils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("ucenter")
public interface ucenterClient {

    @GetMapping("/educenter/apimember/getResigterCounter/{day}")
    public R getResisteroftoDay(@PathVariable(value = "day") String day );

}
