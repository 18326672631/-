package com.boen.guli.pojo.vo;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Teacher查询对象")
public class TeacherQuery {


    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "教师级别")
    private Integer level;

    @ApiModelProperty(value = "开始时间", example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "结束时间",example = "2019-12-01 10:10:10")
    private String end;


}
