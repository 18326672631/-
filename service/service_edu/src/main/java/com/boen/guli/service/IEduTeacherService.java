/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boen.guli.pojo.EduTeacher;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author boen
 * @since 2022-04-20
 */
public interface IEduTeacherService extends IService<EduTeacher> {

    Map<String,Object> pageList(Integer currentpage, Integer pagesize);


}
