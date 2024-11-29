package com.metabuild.spring_mybatis.basic.ioc.ex4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Random;

@Configuration
public class MyConfig {

    @Bean
    public Date getDate() {
        return new Date();
    }

    @Bean
    public Member getMember() {
        return new Member();
    }

    @Bean
    public Random getRandom() {
        return new Random();
    }

    @Bean
    public ServiceImpl service() {
        Random r = this.getRandom();
        return new ServiceImpl(r);
    }
}
