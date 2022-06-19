package com.boen.guli;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.boen")
@MapperScan("com.boen.guli.mapper")
@EnableDiscoveryClient //nacos注册
@EnableFeignClients    //在调用端添加注解
public class eduTeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(eduTeacherApplication.class,args);
    }

    static {
        System.setProperty("druid.mysql.usePingMethod","false");
    }
}
