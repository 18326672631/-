package com.boen.guli.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;

@Configuration
public class TeacherConfig {


    /**
     * 逻辑删除插件,新版本不需要配置
     * @return
     */
//    @Bean
//    public ISqlInjector injector(){
//        return new LogicSqlInjector();
//    }



    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //乐观锁插件
//        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }


}
