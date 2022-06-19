package com.boen.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


@Component
public class MyMetaObejectHandler implements MetaObjectHandler {


    /**
     * 使用mp添加操作，这个方法执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //属性名称，非字段名称
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    /**
     * 使用mp更新操作
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

//    @Override
//    public void insertFill(MetaObject metaObject) {
////        log.info("start insert fill ....");
//        this.strictInsertFill(metaObject, "gmtCreate", Date.class, new Date());
//        this.strictInsertFill(metaObject, "gmtModified", Date.class, new Date());
//
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
////        log.info("start update fill ....");
//        this.strictUpdateFill(metaObject, "gmtModified", Date.class, new Date()); // 起始版本 3.3.0(推荐)
//    }




}
