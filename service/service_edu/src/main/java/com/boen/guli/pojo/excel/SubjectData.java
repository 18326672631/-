package com.boen.guli.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ExampleProperty;
import lombok.Data;


@Data
public class SubjectData {


    //一级目录
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    //二级
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
