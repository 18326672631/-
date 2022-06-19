package excelTest;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class writeTest {
    public static void main(String[] args) {

        //设置写入文件的地址
        String filename ="D:/write.xlsx";

        EasyExcel.write(filename,pojo.class).sheet("学生列表").doWrite(getList());

    }


    public static List<pojo> getList(){
        List<pojo> list =new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            pojo pojo = new pojo();
            pojo.setSno(i);
            pojo.setName("lucy"+i);
            list.add(pojo);
        }
        return list;

    }
}
