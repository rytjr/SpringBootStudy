package com.metabuild.shop.controller;

import com.metabuild.shop.domain.CartDTO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/auth")
public class CartController {

    @PostMapping("/cartAdd")
    public String addCart(Model model, CartDTO cdto, HttpSession session){

        //상품번호 수량이 넘어온다
        log.info("cdto == {}", cdto);

        model.addAttribute("message", "test");
        model.addAttribute("loc","/mall");

        return "message";
    }
}
