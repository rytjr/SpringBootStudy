package com.metabuild.spring_mybatis.controller;
//POJO (play Old Java Object)

import com.metabuild.user.service.MemberSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//컨트롤러 어노테이션
@Controller
public class HomeController {
    //field Injection
    @Autowired
    private MemberSercive memberService;

    //url-pattern : "/main"
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String ShowHome(Model model) {
        //회원수 가져오기
        int userCount = memberService.getMemberCount();
        model.addAttribute("userCount",userCount); //회원수

        model.addAttribute("message", "즐거운 한주가 되세요");
        //request.setAttribute() ==> 스프링에서는 Model객체에 저장

        return "home";  //view name
        //"/WEB-INF/views/home.jsp"를 찾아간다
    }

    @RequestMapping("/test")
    public String ShowTemplate() {

        return "template";
    }


}
