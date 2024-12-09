package com.metabuild.shop.controller;

import com.metabuild.shop.domain.ProductDTO;
import com.metabuild.shop.service.ProductServie;
import com.metabuild.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor   //생성자 인젝션
@Slf4j
public class ProductController {

    private final ShopService shopService;
    private final ProductServie productService;

    @Value("${file.product_upload_dir}")
    private String product_upload;

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

        log.info("dirloard == {}", product_upload);

        return "shop/productRegis";
    }

    @PostMapping("/admin/product")
    public String productPostRegsitration(ProductDTO pdto, Model model, @RequestParam("fi1") MultipartFile pimage1,
                                          @RequestParam("fi2") MultipartFile pimage2, @RequestParam("fi3") MultipartFile pimage3) {

        log.info("pdto == {}",pdto);

        //유효성 검사
        if(pdto.getPname().trim().isEmpty()) {

            return "redirect:productRegis";
        }

        File dir = new File(product_upload);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        if(!pimage1.isEmpty()){
            String origin = pimage1.getOriginalFilename();
            UUID uid = UUID.randomUUID();
            String fileName1 = uid + "_" + origin;
            log.info("origin,fileName1 =={},{}",origin,fileName1);

            pdto.setPimage1(origin);
            pdto.setPimage1Fn(fileName1);

            try{
                pimage1.transferTo(new File(product_upload,fileName1));
            }catch (IOException ex) {
                log.info("파일 업로드오류 == {}", ex.getMessage());
            }
        }
        if(!pimage2.isEmpty()){
            String origin = pimage2.getOriginalFilename();
            UUID uid = UUID.randomUUID();
            String fileName1 = uid + "_" + origin;
            log.info("origin,fileName1 =={},{}",origin,fileName1);

            pdto.setPimage2(origin);
            pdto.setPimage2Fn(fileName1);

            try{
                pimage2.transferTo(new File(product_upload,fileName1));
            }catch (IOException ex) {
                log.info("파일 업로드오류 == {}", ex.getMessage());
            }
        }
        if(!pimage3.isEmpty()){
            String origin = pimage3.getOriginalFilename();
            UUID uid = UUID.randomUUID();
            String fileName1 = uid + "_" + origin;
            log.info("origin,fileName1 =={},{}",origin,fileName1);

            pdto.setPimage3(origin);
            pdto.setPimage3Fn(fileName1);

            try{
                pimage3.transferTo(new File(product_upload,fileName1));
            }catch (IOException ex) {
                log.info("파일 업로드오류 == {}", ex.getMessage());
            }
        }

        pdto.setPoint(pdto.getSalePrice());

        // 현재 날짜를 java.sql.Date로 변환
        LocalDate now = LocalDate.now(); // 현재 날짜
        Date sqlDate = Date.valueOf(now); // LocalDate -> java.sql.Date 변환

        // DTO에 날짜 설정
        pdto.setPdate(sqlDate);

        //예전에 업로드했던 파일이 있따면 서버에서 삭제 처리
        if("edit".equals(pdto.getMode())) {   //수정 모드라면
            if(pdto.getFile1() != null) {
                File tmp = new File(product_upload, pdto.getFile1());
                if(tmp.exists()) {
                    boolean b = tmp.delete();   //파일 삭제 처리
                    log.info("예전 첨부파일 삭제 여부 == {}" , b);
                }
            }
        }
        if("edit".equals(pdto.getMode())) {   //수정 모드라면
            if(pdto.getFile2() != null) {
                File tmp = new File(product_upload, pdto.getFile2());
                if(tmp.exists()) {
                    boolean b = tmp.delete();   //파일 삭제 처리
                    log.info("예전 첨부파일 삭제 여부 == {}" , b);
                }
            }
        }
        if("edit".equals(pdto.getMode())) {   //수정 모드라면
            if(pdto.getFile3() != null) {
                File tmp = new File(product_upload, pdto.getFile3());
                if(tmp.exists()) {
                    boolean b = tmp.delete();   //파일 삭제 처리
                    log.info("예전 첨부파일 삭제 여부 == {}" , b);
                }
            }
        }

        log.info("pdto2 == {}",pdto);

        String str = "실패";
        String loc = "javascript:history.back()";
        int n = 0;

        if(pdto.getMode().equals("write")) {
            n = productService.insertProduct(pdto);

            str = (n > 0) ? "상품 등록 성공" : "상품 등록 실패";
            loc = (n > 0) ? "/admin/productList" : "redirect:productRegis";
        } else if(pdto.getMode().equals("edit")) {
            n = productService.updateProduct(pdto);

            str = (n > 0) ? "상품 수정 성공" : "상품 수정 실패";
            loc = (n > 0) ? "/admin/productList" : "redirect:productEdit";
        }

        model.addAttribute("message", str);
        model.addAttribute("loc",loc);

        return "message";
    }

    @GetMapping("/admin/productList")
    public String productList(Model model, ProductDTO pdto) {

        List<ProductDTO> prodcutList = productService.findProduct(pdto);

        model.addAttribute("prodcutList",prodcutList);

        return "shop/productList";
    }

    @GetMapping("/admin/prodDelete")
    public String productEdit(@RequestParam(defaultValue = "0") int pnum, Model model){

        log.info("pnum== {}",pnum);

        ProductDTO pdto = productService.findByPnum(pnum);

        int n = productService.deleteProduct(pdto.getPnum());

        if(n > 0) {
            String filename1 = pdto.getPimage1Fn();
            String filename2 = pdto.getPimage2Fn();
            String filename3 = pdto.getPimage3Fn();

            if(filename1 != null) {
                File dir = new File(product_upload, filename1);
                if(dir.exists()){
                    boolean b = dir.delete();
                    log.info("deletefiel == {}",b);
                }
            }
            if(filename2 != null) {
                File dir = new File(product_upload, filename2);
                if(dir.exists()){
                    boolean b = dir.delete();
                    log.info("deletefiel == {}",b);
                }
            }
            if(filename3 != null) {
                File dir = new File(product_upload, filename3);
                if(dir.exists()){
                    boolean b = dir.delete();
                    log.info("deletefiel == {}",b);
                }
            }
        }

        String str = (n > 0) ? "삭제 성공" : "삭제 실패";
        String loc = "/admin/productList";

        model.addAttribute("message" , str);
        model.addAttribute("loc", loc);

        return "message";
    }

    @GetMapping("/admin/prodEdit")
    public String productEdit(Model model, @RequestParam(defaultValue = "0") int pnum) {

        if(pnum == 0) {
            return "redirect:/admin/productList";
        }



        ProductDTO n = productService.findByPnum(pnum);

        log.info("수정 == {}", n);

        model.addAttribute("productList", n);


        return "/shop/productEdit";
    }

    @GetMapping("/admin/search") // PROJECT
    public String search(Model m, String keyword) // PROJECT
    {
        log.info("keyword==={}", keyword); // PROJECT
        List<ProductDTO> searchList = shopService.findByPname(keyword);
        m.addAttribute("prodcutList", searchList);
        return "shop/productList"; // PROJECT
    } // PROJECT


}
