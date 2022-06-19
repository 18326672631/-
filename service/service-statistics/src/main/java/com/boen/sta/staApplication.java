package com.boen.sta;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.boen.sta.mapper")
@ComponentScan("com.boen")
public class staApplication {

    public static void main(String[] args) {
        SpringApplication.run(staApplication.class,args);
    }
}
