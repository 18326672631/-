package excelTest;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class pojo {

    //设置表头
    @ExcelProperty(value = "学生编号",index = 0)  //index=0 表示在读的时候第一列
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String name;
}
