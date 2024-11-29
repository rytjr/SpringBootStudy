package com.metabuild.spring_mybatis.basic.ioc.ex1;

public class MessageBeanEn implements MessageBean{

    public void sayHello(String name) {
        System.out.println("Hello" + name);
    }
}
