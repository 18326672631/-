package excelTest;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;

import java.util.Map;

public class excelListener extends AnalysisEventListener<pojo> {



    //一行一行读取内容,data为每行的内容
    @Override
    public void invoke(pojo pojo, AnalysisContext analysisContext) {

        System.out.println("***"+pojo);
    }

    //读取表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头：  "+headMap);
    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
