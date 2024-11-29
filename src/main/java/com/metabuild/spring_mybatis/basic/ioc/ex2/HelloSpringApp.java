package com.metabuild.spring_mybatis.basic.ioc.ex2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

//설정파일
//1. xml로 설정하는 경우 + 어노테이션
//2. java로 설정하는 경우 + 어노테이션

public class HelloSpringApp {
    public static void main(String[] args) {
        //설정 파일 : applicationContext.xml
        String resource = "src/main/java/com/metabuild/spring_mybatis/basic/ioc/ex2/applicationContext.xml";

        //스프링컨테이너
        ApplicationContext ctx = new FileSystemXmlApplicationContext(resource);

        //DL (Dependency Lookup) : 스프링이 알아서 객체를 메모리에 올리면 우리는 이름으로 찾아서 사용
        MessageBean mb = (MessageBean)ctx.getBean("mbEn");
        mb.sayHello("홍길동");

    }
}
