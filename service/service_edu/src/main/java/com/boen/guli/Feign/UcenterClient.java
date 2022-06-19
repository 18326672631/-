package com.boen.guli.Feign;


import com.boen.commonUtils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Component
@FeignClient("ucenter")
public interface UcenterClient {


    @GetMapping("/educenter/apimember/getUserInfofromId/{id}")
    public R getUserInfofromId(@PathVariable("id") String id);
}
