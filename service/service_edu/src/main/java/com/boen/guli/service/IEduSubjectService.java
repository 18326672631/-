/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boen.guli.pojo.EduSubject;
import com.boen.guli.pojo.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author boen
 * @since 2022-04-25
 */
public interface IEduSubjectService extends IService<EduSubject> {

    void saveFile(MultipartFile file);

    List<OneSubject> getallsubject();

}
