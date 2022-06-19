package com.boen.guli.Feign;


import com.boen.commonUtils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("vod")
public interface VodClinet {



    /**
     * 定义要调用的方法路径   mapping里面要写完整
     * @PathVariable注解一定要指定参数名称，否则出错
     * @param videoid
     * @return
     */
    @DeleteMapping("/eduvod/vod/deletevideo/{videoid}")
    public R deleteVideo(@PathVariable("videoid") String videoid);


}
