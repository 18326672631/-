package com.boen.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@MapperScan("com.boen.ucenter.mapper")
@ComponentScan("com.boen")
@EnableSwagger2
public class UcenterAplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterAplication.class,args);
    }
}
