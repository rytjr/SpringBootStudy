package com.metabuild.spring_mybatis.basic.ioc.ex1;

public class MessageBeanKo implements MessageBean{

    public void sayHello(String name) {
        System.out.println("안녕하세요" + name + "님");
    }
}
