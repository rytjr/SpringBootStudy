package com.metabuild.spring_mybatis.common.Interceptor;

import com.metabuild.user.domain.MemberDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
* Interceptor
* 컨트롤러가 실해되기 전에 사전 처리할 일이 있으면 인터셉터에서 구현한다.
* 1. HandlerInterceptor 인터페이스를 상속
*   1. preHandle() : 사전 처리. 컨트롤러 실행하기 전에 호출
*   2. postHandle() : 사후 처리. 컨트롤러 실행한 후, 아직 뷰를 실행하기 전
*   3. afterCompletion() : 컨트롤러 실행하고, 뷰도 실행한 후에 호출
*
* 2. url pattern일 때 해당 인터셉터를 적용할지 설정한다 -> webConfig 클래스에서 설정
* */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.세선에 저장된 loginUser가 있는지 꺼내본다. -> 없다면 false반환(컨트롤러까지 진행하지 못함), 있으면 true반환
        HttpSession session = request.getSession();
        MemberDTO user = (MemberDTO)session.getAttribute("loginUser");
        if(user == null) {  //로그인 안 한 경우
            String str = "로그인해야 이용할 수 있어요";
            String loc = "/login";

            request.setAttribute("message", str);
            request.setAttribute("loc",loc);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/message.jsp");
            dispatcher.forward(request,response);
            return false;
        }

        return true;
    }

}
