package com.metabuild.spring_mybatis;

import com.metabuild.spring_mybatis.common.Interceptor.AdminCheckInterceptor;
import com.metabuild.spring_mybatis.common.Interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* WebMvcConfigure 인터페이스를 구현 --> Spring MVC의 설정을 확장하고 커스터마이징 하도록 제공되는 인터페이스
*               이를 통해서 스프링부트의 기본설정은 그대로 유지하면서 특정 기능을 필요에 맞게 추가하거나 수정할 수 있다.
* */

@Configuration
@RequiredArgsConstructor  //생성자 인젝션. final 필드를 주입해준다.
public class WebConfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptro;
    private final AdminCheckInterceptor adminCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry에 LoginCheckInteceptor 등록
        registry.addInterceptor(loginCheckInterceptro)    //인터셉터 등록
                .addPathPatterns("/auth/**","/admin/**")   // 로그인에 필요한 url pattern지정
                .order(1);  //1순위로 설정

        registry.addInterceptor(adminCheckInterceptor)    //인터셉터 등록
                .addPathPatterns("/admin/**")   // 로그인에 필요한 url pattern지정
                .order(2);  //1순위로 설정
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")   //브라우저에서 접근할 경로 지정
                .addResourceLocations("file:///c:/Lecture/devSource/upload/");   //실제 파일 저장 경로
        //브라우저에서 접근할 떄는
        //http://localhost:8080/upload/파일명
    }
}
