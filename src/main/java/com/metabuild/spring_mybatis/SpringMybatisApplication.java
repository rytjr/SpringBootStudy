package com.metabuild.spring_mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//해당 패키지를 스캔해서 @Controller, @Service, @Repository 어노테이션 붙은
// 클래스의 객체를 스프링이 자동으로 메모리에 올려줌
@ComponentScan(basePackages = {"com.metabuild", "com.metabuild.spring_mybatis"})
//XXXXMapper 인터페이스가 있는 패키지를 @MapperScan에 추가하자
@MapperScan(basePackages = {"com.metabuild.user.mapper","com.metabuild.board.mapper","com.metabuild.shop.mapper"})
public class SpringMybatisApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringMybatisApplication.class, args);
	}

}
