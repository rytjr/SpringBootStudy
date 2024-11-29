package com.metabuild.spring_mybatis.basic.ioc.ex1;

public class HelloApp {

    public static void main(String[] args) {


        //MessageBeanKo mb = new MessageBeanKo();
        MessageBeanEn mb = new MessageBeanEn();
        //HelloApp uses MessageNeanKo
        //의존성을 갖는다  ==> 결합력이 강함(객체를 교체하기 어렵다)
        mb.sayHello("Guest");


        //약한 결합력
        //부모 인터페이스타입 변수 = new 구현한 자식객체()
        MessageBean mb2 = new MessageBeanKo();
        mb2.sayHello("VIP");
    }
}
