package excelTest;

import com.alibaba.excel.EasyExcel;

public class readTest {
    public static void main(String[] args) {


        String filename ="D:/write.xlsx";

        EasyExcel.read(filename,pojo.class,new excelListener()).sheet().doRead();

        
    }
}
