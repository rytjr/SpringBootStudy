package com.metabuild.spring_mybatis.basic.ioc.ex3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   //현재 AppCinfig 클래스를 환경설ㅈㅇ으로 사용하겠단 의미
public class AppConfig {

    //OracleDao oracle = new OracleDao()와 동일한 동작
    @Bean(name = "oracle")
    public OracleDao getOracle() {
        return new OracleDao();
    }

    //Bean의 이름을 주지 않으면 메서드 명이 빈의 이름이 된다
    //MySQLDao getMySql = new MySQLDao();와 같은 동작
    @Bean
    public MySQLDao getMySql() {
        return new MySQLDao();
    }

}
