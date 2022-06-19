package com.boen.commonUtils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class R {

    @ApiModelProperty(value = "是否成功")
    private Boolean sueecss;

    @ApiModelProperty(value = "返回状态码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();


    //私有化构造方法
    private R() {
    }


    //成功
    public static R ok() {
        R r = new R();
        r.setSueecss(true);
        r.setCode(Resultcode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    /**
     * 失败
     *
     * @return
     */
    public static R error() {
        R r = new R();
        r.setSueecss(false);
        r.setCode(Resultcode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean sueecss) {
        this.setSueecss(sueecss);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }


}
