package com.metabuild.spring_mybatis.basic.ioc.ex4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloSpringApp {
    public static void main(String[] args) {

        /*
        Member m1= new Member("아무개","");
        m1.setName("홍길동");
        m1.setTel("010-2938-3934");
        */

        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
        Member m1 = ctx.getBean("getMember",Member.class);
        m1.showInfo();

        //ServiceImpl 객체를 lookup해서 info1(), info2()를 호출하세요
        Service svc = ctx.getBean("service", Service.class);
        svc.info1();
        svc.info2();
    }
}
