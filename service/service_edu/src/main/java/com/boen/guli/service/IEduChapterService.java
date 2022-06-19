/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boen.guli.pojo.EduChapter;
import com.boen.guli.pojo.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author boen
 * @since 2022-04-27
 */
public interface IEduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoofCourse(String id);

    boolean deleteChapter(String id);



}
