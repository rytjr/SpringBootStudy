package com.metabuild.shop.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class ProductDTO {

    private int cg_code;  //카테고리 코드

    private int pnum;
    private String pname;
    private String pimage1;
    private String pimage2;
    private String pimage3;
    private String pimage1Fn;
    private String pimage2Fn;
    private String pimage3Fn;

    private int price;  //정가
    private int salePrice; // 판매가
    private int pqty;  //재고 수량  or 장바구니에 담긴 상품 수량
    private int point; //적립 포인트

    private String pspec;  //HIT, NEW, BEST
    private String pcontents;   //상품설명
    private String pcompany; //제조사
    private Date pdate; //입고일

    private int totalPrice; //총판매가 = 판매가 * 수량
    private int totalPoint; //총 포인트 = 포인트 * 수량

    private String mode;

    private String file1;
    private String file2;
    private String file3;

    //포인트 계산 판매가의 1%
    public void setPoint(int salePrice) {
        this.point = salePrice / 100;
    }

    //주문 수량이 정햐지면 총판매가, 총포인트를 연산하자
    public void setPqty(int qty){
        this.pqty = qty;
        this.totalPrice = this.pqty * this.salePrice;
        this.totalPoint = this.pqty * this.point;
    }

    //할인율을 반환하는 메서드
    public int getPercent(){
        //(정가-판매가) / 정가 * 100
        int percent = (price - salePrice) * 100 / price;
        if(percent < 0) throw new NumberFormatException("상품 판매가가 상품 정가보다 클 수 없어요");
        return percent;
    }

}
