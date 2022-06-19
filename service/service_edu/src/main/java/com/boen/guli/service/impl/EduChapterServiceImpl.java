/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boen.guli.mapper.EduChapterMapper;
import com.boen.guli.mapper.EduVideoMapper;
import com.boen.guli.pojo.EduChapter;
import com.boen.guli.pojo.EduVideo;
import com.boen.guli.pojo.chapter.ChapterVo;
import com.boen.guli.service.IEduChapterService;
import com.boen.guli.service.IEduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author boen
 * @since 2022-04-27
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements IEduChapterService {

    @Autowired
    private EduChapterMapper eduChapterMapper;

    @Autowired
    private IEduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoofCourse(String id) {

        List<ChapterVo> list = eduChapterMapper.getChapterofCourseId(id);
        return list;
    }

    @Override
    public boolean deleteChapter(String id) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);

        //现根据章节id查找小结信息
        int count = eduVideoService.count(wrapper);

        if (count>0){
            //有小结，不能删除
            return false;
        }
        int i = baseMapper.deleteById(id);
        return i>0;
    }
}
