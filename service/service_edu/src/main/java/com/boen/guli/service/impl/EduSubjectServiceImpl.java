/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boen.guli.Listener.SubjectExcelListener;
import com.boen.guli.mapper.EduSubjectMapper;
import com.boen.guli.pojo.EduSubject;
import com.boen.guli.pojo.excel.SubjectData;
import com.boen.guli.pojo.subject.OneSubject;
import com.boen.guli.pojo.subject.TwoSubject;
import com.boen.guli.service.IEduSubjectService;
import com.boen.guli.service.IEduTeacherService;
import org.apache.ibatis.annotations.One;
import org.jcp.xml.dsig.internal.dom.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author boen
 * @since 2022-04-25
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements IEduSubjectService {


    @Autowired
    private IEduSubjectService service;

    @Autowired
    private EduSubjectMapper mapper;

    /**
     * 添加课程分类
     * @param file
     */
    @Override
    public void saveFile(MultipartFile file) {


        try {
            InputStream inputStream = file.getInputStream();
                                                             //讲service传入到监听器
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(service)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 课程分类列表（树形）
     * @return
     */
    @Override
    public List<OneSubject> getallsubject() {

        //获取所有的一级分类和二级分类的集合
        //一级
        QueryWrapper<EduSubject> onewrapper = new QueryWrapper<>();
        onewrapper.eq("parent_id","0");
        List<EduSubject> onesubject = mapper.selectList(onewrapper);


        QueryWrapper<EduSubject> twowrapper = new QueryWrapper<>();
        twowrapper.ne("parent_id","0");    //parent_id不等于0
        List<EduSubject> twosubject = mapper.selectList(twowrapper);

        List<OneSubject> finnalSubject = new ArrayList<>();

        for (int i = 0; i <onesubject.size(); i++) {

            EduSubject eduSubject = onesubject.get(i);

            OneSubject subject = new OneSubject();
            //把取到的一级分类封装
            BeanUtils.copyProperties(eduSubject,subject);

            ArrayList<TwoSubject> twoSubjects = new ArrayList<>();

            for (int j = 0; j < twosubject.size(); j++) {

                EduSubject eduSubject1 = twosubject.get(j);

                TwoSubject twoSubject = new TwoSubject();


                if (eduSubject1.getParentId().equals(subject.getId())){
                    BeanUtils.copyProperties(eduSubject1,twoSubject);
                    twoSubjects.add(twoSubject);
                }

            }
            subject.setChildren(twoSubjects);

            finnalSubject.add(subject);
        }


        return finnalSubject;
    }
}
