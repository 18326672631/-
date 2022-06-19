package com.boen.ExceptionHandler;


import com.boen.commonUtils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobelException {


    /**
     * 统一异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了统一异常处理");
    }


    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();

        log.error(e.getMessage());

        return R.error().code(e.getCode()).message(e.getMsg());
    }



}
