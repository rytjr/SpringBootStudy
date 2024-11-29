package com.metabuild.spring_mybatis.common.exception;

import com.metabuild.user.exception.NoMemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

//예외 처리하는 메서드를 모아두는 곳
@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

    @ExceptionHandler(NoMemberException.class)
    public String authException(Exception ex, Model model){
        model.addAttribute("message",ex.getMessage());
        model.addAttribute("loc","/login");
        return "message";
    }

    @ExceptionHandler({SQLException.class, NumberFormatException.class})
    public String exceptionHandler(Exception ex, Model model) {
        log.error("Error : " + ex.getMessage());
        model.addAttribute("message","Error : " + ex.getMessage());
        return "erroeMessage";
    }
}
