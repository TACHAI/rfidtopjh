package com.laishishui.rfidtopjh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@MapperScan(value = "com.laishishui.rfidtopjh.dao")
@SpringBootApplication
public class RfidtopjhApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RfidtopjhApplication.class, args);
    }


    /**
     * 打成war包使用
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        /**
         * 此处Application.class 替换为springboot默认启动类
         */
        return builder.sources(RfidtopjhApplication.class);
    }
}
