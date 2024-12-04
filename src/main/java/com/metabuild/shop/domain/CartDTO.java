package com.metabuild.shop.domain;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class CartDTO {

    private int cnum;  //장바구니 번호(PK)
    private String userId; //회원 아이디(FK)
    private int pnum;  //상품번호(FK)
    private int pqty;  //상품수량
    private Date cdate;   //장바구니에 담은 날짜

    private int cartTotalPrice; //장바구니에 담긴 상품 총액
    private int cartTotalPoint; //총포인트

    //장바구니에 담긴 상품정보
    private List<ProductDTO> items;

//    private String pname;
//    private String pimage1;
}
