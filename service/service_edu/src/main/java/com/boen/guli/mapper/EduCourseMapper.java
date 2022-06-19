/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boen.guli.pojo.EduCourse;
import com.boen.guli.pojo.FrontVo.FrontCourseInfoVo;
import com.boen.guli.pojo.FrontVo.FrontCourseVo;
import com.boen.guli.pojo.vo.publishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author boen
 * @since 2022-04-27
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public publishVo getpublishCourseInfo(String courseId);

    /**
     * 前台接口，获取课程相关信息
     * @param coueseId
     * @return
     */
    public FrontCourseInfoVo getFrontAllCourseInfo(String coueseId);

}
