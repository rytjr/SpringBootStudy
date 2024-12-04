package com.metabuild.shop.controller;

import com.metabuild.shop.domain.ProductDTO;
import com.metabuild.shop.service.ProductServie;
import com.metabuild.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor   //생성자 인젝션
@Slf4j
public class ProductController {

    private final ShopService shopService;
    private final ProductServie productService;

    //메서드가 뷰네임을 반환하지 않으면(void)
    //@GetMapping()에 등록된 url-pattern "/mall" 가 뷰네임이 된다
    @GetMapping("/mall")
    public void display(Model model){

        List<ProductDTO> hitList = shopService.findByPspec("HIT");
        List<ProductDTO> newList= shopService.findByPspec("NEW");

        model.addAttribute("hitItems", hitList);
        model.addAttribute("newItems", newList);

        //WEB-INF/views/mall.jsp를 보여준다
    }

    @GetMapping("/prodDetail")
    public String productDetail(@RequestParam(defaultValue = "0")int pnum, Model model){

        if(pnum == 0) {
            return "redirect:/mall";
        }

        ProductDTO item = shopService.findByPnum(pnum);

        model.addAttribute("item", item);

        return "shop/prodDetail";
    }

    @GetMapping("/admin/product")
    public String productRegsitration() {

        return "shop/productRegis";
    }

    @PostMapping("/admin/product")
    public String productPostRegsitration(ProductDTO pdto, Model model) {

        log.info("pdto == {}",pdto);

        if(pdto.getPname().trim().isEmpty()) {

            return "redirect:productRegis";
        }

        pdto.setPoint(pdto.getSalePrice());

        // 현재 날짜를 java.sql.Date로 변환
        LocalDate now = LocalDate.now(); // 현재 날짜
        Date sqlDate = Date.valueOf(now); // LocalDate -> java.sql.Date 변환

        // DTO에 날짜 설정
        pdto.setPdate(sqlDate);

        log.info("pdto2 == {}",pdto);

//        productService.insertProduct(pdto);

        return "message";
    }
}
