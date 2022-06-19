package com.boen.guli.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boen.ExceptionHandler.GuliException;
import com.boen.guli.pojo.EduSubject;
import com.boen.guli.pojo.EduTeacher;
import com.boen.guli.pojo.excel.SubjectData;
import com.boen.guli.service.IEduSubjectService;


public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {




    public IEduSubjectService service;

    public SubjectExcelListener(IEduSubjectService service) {
        this.service = service;
    }


    //读取excel内容 ,一行一行读取
    @Override
    public void invoke(SubjectData subject, AnalysisContext analysisContext) {

        if(subject == null){
            throw  new GuliException(20000,"文件数据为空");
        }
        //一行一行读取，每次读取两个值，第一个值为以及分类，第二个值为二级分类
        EduSubject oneSubject = this.existOneSubject(subject.getOneSubjectName(), service);

        if (oneSubject == null){
            //不存在相同的一级分类
            oneSubject = new EduSubject();
            oneSubject.setTitle(subject.getOneSubjectName());
            oneSubject.setParentId("0");
            service.save(oneSubject);
        }

        //如果oneSubject为空，调用mp保存方法时，会自动生成id
        EduSubject twoSubject = this.existTwoSubject(subject.getTwoSubjectName(), service, oneSubject.getId());

        if (twoSubject == null){
            //不存在相同的二级分类
            twoSubject = new EduSubject();
            twoSubject.setTitle(subject.getTwoSubjectName());
            twoSubject.setParentId(oneSubject.getId());
            service.save(twoSubject);
        }



    }

    //判断一级分类能不能重复添加
    private EduSubject existOneSubject(String name,IEduSubjectService service){

        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");

        EduSubject one = service.getOne(queryWrapper);
        return one;
    }

    //判断二级分类能不能重复添加
    private EduSubject existTwoSubject(String name,IEduSubjectService service,String pid){

        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);

        EduSubject two = service.getOne(queryWrapper);
        return two;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
