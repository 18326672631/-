package com.boen.guli.pojo.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {

    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "讲师ID")
    @TableId(type = IdType.ID_WORKER_STR)
    private String teacherId;

    @ApiModelProperty(value = "科目ID")
    private String subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    private String subjectParentId;


    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "课程描述")
    private String description;
}
