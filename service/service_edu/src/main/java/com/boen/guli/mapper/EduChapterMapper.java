/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boen.guli.pojo.EduChapter;
import com.boen.guli.pojo.chapter.ChapterVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author boen
 * @since 2022-04-27
 */
@Mapper
public interface EduChapterMapper extends BaseMapper<EduChapter> {

    public List<ChapterVo> getChapterofCourseId(String courseId);

}
