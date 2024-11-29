package com.metabuild.spring_mybatis.basic.ioc.ex4;

import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Random;

@Setter
@Getter
@NoArgsConstructor    //기본 생성자(default constructor)
@AllArgsConstructor   //인자 생성자
public class Member {

    @Value("김철수")
    private String name;

    @Value("010-2394-2932")
    private String tel;

    //bt type으로 주입한다. 같은 자료형의 객체가 있으면 주입
    @Autowired     //Date 유형의 객체가 있으면 자동으로 주입한다.
    private Date tody;

    @Inject   // by type으로 주입  ==> build.gradle에 라이브러리 등록해야 함
    private Random random;

    public void showInfo() {
        System.out.printf("Name : %s\nTel : %s\njoinDate : %s\n", name,tel,tody.toString());
        System.out.println("랜덤한 행운의 수 : " + random.nextInt(100));
    }
}
