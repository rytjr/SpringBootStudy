package com.metabuild.user.controller;

import com.metabuild.user.domain.MemberDTO;
import com.metabuild.user.exception.NoMemberException;
import com.metabuild.user.service.MemberSercive;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor    //final이 붙은 필드들을 생성자 인젝션으로 주입해준다.
public class LoginController {

    private final MemberSercive memberService;

    @GetMapping(value = "/login")    //get방식만 지원
    public String ShowLogin() {

        return "member/login";
    }

    @PostMapping("/login")
    public String loginProcess(MemberDTO tmpUser, HttpSession session,
                               @RequestParam(name = "saveId", defaultValue = "false") boolean saveId,
                               HttpServletResponse response) throws NoMemberException {
        log.info("tmpUser ==={}", tmpUser);
        log.info("saveId === {}", saveId);

//        log.info("memeberService == {}", memberService);

        //1. 유효성 체크(userId,passwd) 빈 문자열 체크
        //2. memberService의 loginCheck(temUser)
        //회원이 아닌경우(아이디가 다르거나, 비밀번호가 다르거나) --> 사용자정의 예외를 발생시킬 예정 - NoMemberException
        //회원이 맞다면 --> DB에서 해당 회원정보를 가져와서 MemberDTO객체에 담아 반환할 예정
        MemberDTO authUser = memberService.loginCheck(tmpUser);

        if(authUser != null) {
            //회원으로 인증뱓은 경우라면
            log.info("회원 인증 성공!!");
            //로그인한 회원의 정보를 session에 저장
            //HttpSession session = request.getSession();
            //session.setAttribute(key값,value값)
            //하나의 브라우저를 사용하느 동안 또는 세션 타임아웃이 되기 전까지 "loginUser" 라는 키값으로 저장한 정보를 계속 유지된다.
            session.setAttribute("loginUser", authUser);     //세션은 서버에서 보관
            String sessionId = session.getId();   //jsessionId라고 함 --> 세션을 식별하는데 사용 쿠키로 저장되어 클라이언트가 보관하고 있음
            log.info("sessionId === {}", sessionId);

            Cookie cookie = new Cookie("uid", authUser.getUserId());
            //회원 아이디를 쿠키로 보관하자
            if(saveId) {     //아이디 저장에 체크한 경우
                cookie.setMaxAge(60*60*24*7);   //유효시간 설정 7일간 유효함
            }else {      //체크하지 않은 경우
                cookie.setMaxAge(0);  //쿠키 삭제
            }
            cookie.setPath("/");    //쿠키를 꺼낼 수 있는 경로 "/" 설정
            //응답 객체에 쿠키 추가  -> 응답데이터 헤더에 쿠키가 저장
            response.addCookie(cookie);
        }

        return "redirect:/main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //1. 방법 특정 세션 변수 삭제
//        session.removeAttribute("loginUser");
        //2. 방법 모든 세션 변수 삭제
        session.invalidate();
        return "redirect:/main";
    }

    /*   스프링에서 예외 처리하는 방법
    * 1. 방법 @ExceptionHandler 어노테이션을 붙인 예외 처리 메서드를 구성
    * 2. 방법 @ControllerAdvice 어노테이션을 붙인 예외 처리 클래스를 구성 --> 예외 처리 메서드 모음
    * */

//    @ExceptionHandler(NoMemberException.class)
//    public String exceptionHandler(Exception ex, Model model) {
//
//        model.addAttribute("message",ex.getMessage());
//        model.addAttribute("loc","/login");
//
//        return "message";
//    }
}
