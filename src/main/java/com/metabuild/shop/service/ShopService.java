package com.metabuild.shop.service;

import com.metabuild.shop.domain.CartDTO;
import com.metabuild.shop.domain.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ShopService {

    /*Pspec 별로 상품 정보 가져오기*/
    public List<ProductDTO> findByPspec(String pspec);
    /*카테고리별 상품정보 가져오기*/
    public List<ProductDTO> findByCategory(int cg_num);
    /**상품번호로 특정 상품 정보 가져오기*/
    public ProductDTO findByPnum(int pnum);

    /**장바구니 관련 메소드===============*/
    int addCart(CartDTO cartDTO);//장바구니 추가하기
    int updateCartQty(CartDTO cartDTO);//장바구니 추가 관련=>기존에 담긴 상품이면 수량만 수정하기
    int editCart(CartDTO cartDTO);// 장바구니 수정하기
    List<CartDTO> findCartView(String userid);//특정 회원의 장바구니 목록보기

    int delCart(int cartNum);
    int delCartAll(CartDTO cartDTO);
    int delCartOrder(Map<String,Integer> map);

    CartDTO getCartTotal(String userid);//특정 회원의 장바구니 총액,총포인트 구하기

}
