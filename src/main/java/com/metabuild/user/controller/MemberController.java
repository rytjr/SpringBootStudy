package com.metabuild.user.controller;

import com.metabuild.user.domain.MemberDTO;
import com.metabuild.user.service.MemberSercive;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@Controller
@Slf4j //log객체를 사용할 수 있다.
public class MemberController {

//    @Autowired   //by Tyape으로 주입 동일한 객체가 여러 개 있을 경우에는 에러 발생함 여러개일 경우 밑처럼 하나면 없어도 됨
//    @Qualifier("memberServiceImpl")
    //@Resource : by Name으로 주입. 객체 이름으로 주입
    @Resource(name = "memberServiceImpl")
    private MemberSercive memberSercive;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String ShowSignUp() {

        return "member/signup";
    }
    @RequestMapping(value = "/signupProc", method = RequestMethod.POST)
    public String signupProc(MemberDTO user, Model model) {
        //html의 input name과 DTO의 property명이 같으면 자동으로 DTO객체에 담아준다
        log.info("MemberDTO user======{}", user);

        //유효성 체크(userId,name,passwd)
        if(user.getUserId().trim().isEmpty() || user.getPasswd().trim().isEmpty()
                || user.getName().trim().isEmpty()) {

            //스프링의 default 이동방식은 : forward방식이다
            //만약 redirect방식으로 이동하고 싶다면 "redirect:" 접두어를 붙인다.
            return "redirect:signup";
        }

        //memberService의 insertMember(user) 호출
        int n = memberSercive.insertMember(user);

        //실행 경과 메시지,이동할 경로 지정
        String str = (n > 0) ? "회원가입 완료!! 로그인 페이지로 이동합니다." : "회원가입 실패 - 아이디 중복체크 확인하세요";
        String loc = (n > 0) ? "login" : "javascript:history.back()";

        model.addAttribute("message", str);
        model.addAttribute("loc",loc);
        return "message";
    }



    @RequestMapping("/admin/users")
    public String memberlist(Model model) {
        List<MemberDTO> userlist = memberSercive.listMember();

        model.addAttribute("userList",userlist);

        return "member/list";
    }

    // /admin/userDelete?no=10
    @RequestMapping("/admin/userDelete")
    // 파라미터명 : no, 파라미터가 넘어오지 않을 경우 no의 값을 0으로 설정하겠다는 의미
    public String memberDelete(Model model, @RequestParam(value = "no", defaultValue = "0") int no) {    //-parameters
        log.info("no====={}",no);

        if(no == 0) {
            model.addAttribute("message","잘못 들어온 경로입니다.");
            model.addAttribute("loc","users");
            return "message";
        }

        int n = memberSercive.deleteMember(no);

        return "redirect:/admin/users";
    }

    /*
    * GET /idCheck ==> 아이디 입력 form을 보여준다
    * POST /idCheck ==> 사용자가 입력한 userId값이 사용가능한지 여부를 알려준다
    * */
    @RequestMapping(value = "/idCheck", method = RequestMethod.GET)
    public String idCheck() {
        return "member/idCheck";
    }

    @RequestMapping(value = "/idCheck", method = RequestMethod.POST)
    public String idCheckResult(Model model, @RequestParam(defaultValue = "") String userId) {
        log.info("userId======={}", userId);
        if(userId.trim().isEmpty()) {
            model.addAttribute("message","아이디 입력하세여");
            model.addAttribute("loc","javascript:history.back()");
            return "message";
        }

        boolean isUse = memberSercive.idCheck(userId.trim());
        String str = (isUse) ? userId + "는 사용 가능합니다" : userId + "는 이미 사용 중 입니다";
        String result = (isUse) ? "ok" : "fail";

        model.addAttribute("message", str);
        model.addAttribute("result",result);
        model.addAttribute("userId", userId);
        return "member/idCheckResult";
    }


    @GetMapping("/auth/myPage")
    public String getMyPage(HttpSession session, Model model) {

        String key = "loginUser";
        MemberDTO value = (MemberDTO) session.getAttribute(key);

        log.info("value == {}", value.getName());

        MemberDTO user = memberSercive.findByUserId(value.getUserId());

        log.info("user == {}", user);

        model.addAttribute("user", user);

        return "/auth/myPage";
    }

    @PostMapping("/auth/myPage")
    public String postMyPage(){
        return "message";
    }

}
