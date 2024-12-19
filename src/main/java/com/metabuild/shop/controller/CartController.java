package com.metabuild.shop.controller;

import com.metabuild.shop.domain.CartDTO;
import com.metabuild.shop.domain.ProductDTO;
import com.metabuild.shop.domain.WishDTO;
import com.metabuild.shop.service.ShopService;
import com.metabuild.user.domain.MemberDTO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/auth")
public class CartController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/cartAdd")
    public String addCart(Model model, CartDTO cdto, HttpSession session){

        //로그인한 회원의 아디ㅣ 구하기
        MemberDTO logingUser = (MemberDTO)session.getAttribute("loginUser");
        String userId = logingUser.getUserId();
        cdto.setUserId(userId);

        log.info("cdto == {}", cdto);

        int n = shopService.addCart(cdto);
        //로그인한 회원의 장바구니 목록 가져오기
//        List<CartDTO> cartArr = shopService.findCartView(userId);
//        model.addAttribute("cartArr",cartArr);

        //return "shop/cartList";   //forward 이동 ==> 새로 고침할 경우 마지막에 추가한 상품의 수량이 계속 증가함
        //따라서 redirect로 장바구니 목록으로 이동해야 한다
        return "redirect:/auth/cart";
    }

    @GetMapping("/cart")
    public String cartList(Model model, HttpSession session) {

        String userId = ((MemberDTO)session.getAttribute("loginUser")).getUserId();

        //로그인한 회원의 장바구니 목록 가져오기
        List<CartDTO> cartArr = shopService.findCartView(userId);
        //장바구니 총액, 총포인트 가져오기
        CartDTO cartDTO = shopService.getCartTotal(userId);
        model.addAttribute("cartSum", cartDTO);

        model.addAttribute("cartArr",cartArr);

        return "shop/cartList";
    }

    @PostMapping("/cartDel")
    public String cartDelete(@RequestParam(name="cnum", defaultValue = "-1") int cnum){
        if(cnum == -1) return "redirect:cart";
        int n = shopService.delCart(cnum);
        return "redirect:cart";
    }

    @PostMapping("/cartEdit")
    public String cartEdit(CartDTO dto){
        log.info("dto  == - {}", dto);

        shopService.editCart(dto);

        return "redirect:cart";
    }

    @GetMapping("/wishList")
    public String wishList(Model model, HttpSession session) {

        int no = ((MemberDTO)session.getAttribute("loginUser")).getNo();

        List<WishDTO> wishList = shopService.listWish(no);

        model.addAttribute("wishList", wishList);

        return "/shop/wish";
    }

    @PostMapping("/wish")
    public String wish(ProductDTO productDTO, HttpSession session, Model model) {

        int pnum = productDTO.getPnum();
        int no = ((MemberDTO)session.getAttribute("loginUser")).getNo();

        log.info("no,pnum = {},{}",no,pnum);

        String str = "실패";
        String loc = "redirect:history.back()";

        int t = shopService.selectWish(no,pnum);
        if(t > 0) {
            str = "위시리스트에 이미 존재합니다.";
            loc = "/mall";
            model.addAttribute("message", str);
            model.addAttribute("loc",loc);
            return "message";
        }

        int n = shopService.insertWish(no, pnum);


        str = (n > 0) ? "위시리스트 추가 성공" : "위시리스트 추가 실패";
        loc = (n > 0) ? "/mall" : "redirect:history.back()";
        model.addAttribute("message", str);
        model.addAttribute("loc",loc);
        return "message";
    }
}
