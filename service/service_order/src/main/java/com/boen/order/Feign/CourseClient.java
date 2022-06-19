package com.boen.order.Feign;

import com.boen.order.FrontCourseInfoVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "edu")
public interface CourseClient {

    @GetMapping("/eduservice/course/getCourseInfo/{courseId}")
    public FrontCourseInfoVoOrder getCourseInfofromId(@PathVariable(value = "courseId") String courseId);
}
