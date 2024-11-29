package com.metabuild.spring_mybatis.basic.ioc.ex4;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Random;

public class ServiceImpl  implements Service{

    @Autowired   // Field Injection
    private Member member;

    private Date date;

    private Random random;

    @Autowired   //setter Injection
    public void setDate(Date date) {
        this.date = date;
    }

    @Autowired  // constructor injection
    public ServiceImpl(Random ran) {
        this.random = ran;
    }

    @Override
    public void info1() {
        member.showInfo();
    }



    @Override
    public void info2() {
        System.out.println(date.toString());
        int n = random.nextInt(200) + 50;
        //nextint(범위) + 시작수
        System.out.println(n);
    }
}
