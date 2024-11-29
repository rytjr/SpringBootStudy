package com.metabuild.spring_mybatis.basic.ioc.ex3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloSpingApp {
    public static void main(String[] args) {

        //스프링 컨테이너
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        //DL(이름으로 찾아 쓰는거)
        Dao dao = ctx.getBean("oracle", Dao.class);
        dao.daoInfo();

        //DL(이름을 안 줬을 경우)
        Dao dao2 = ctx.getBean("getMySql", Dao.class);
        dao2.daoInfo();


    }
}
