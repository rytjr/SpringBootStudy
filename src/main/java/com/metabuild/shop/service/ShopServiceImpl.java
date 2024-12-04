package com.metabuild.shop.service;

import com.metabuild.shop.domain.CartDTO;
import com.metabuild.shop.domain.ProductDTO;
import com.metabuild.shop.mapper.CartMapper;
import com.metabuild.shop.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("shopService")
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ProductMapper productMapper;
    private final CartMapper cartMapper;

    @Override
    public List<ProductDTO> findByPspec(String pspec) {
        return productMapper.findByPspec(pspec);
    }

    @Override
    public List<ProductDTO> findByCategory(int cg_num) {
        return List.of();
    }

    @Override
    public ProductDTO findByPnum(int pnum) {
        return productMapper.getProduct(pnum);
    }

    @Override
    public int addCart(CartDTO cartDTO) {
        //1. 회원 아이디와 상품번호로 cart 테이블에 담겨있는 상품 개수 가져오기
        int cnt = cartMapper.selectCartCountByPnum(cartDTO);

        //1.1 이미 담겨있는 상품이라면 ==> 수량만 수정
        int n = 0;
        if(cnt > 0) {
            n = cartMapper.updateCartQty(cartDTO);
        }else {
            //1.2 담겨있는 상품이 아니라면 ==> 장바구니에 상품을 추가(insert)
            n = cartMapper.addCart(cartDTO);
        }
        return n;
    }

    @Override
    public int updateCartQty(CartDTO cartDTO) {
        return 0;
    }

    @Override
    public int editCart(CartDTO cartDTO) {
        int qty = cartDTO.getPqty();
        //수량이 음수일 경우  ==> 예외발생
        if(qty < 0) {
            throw new NumberFormatException("수량은 양수 값을 입력해야 해요");
        } else if(qty == 0) {
            //수량이 0인 경우   ==> 삭제 처리
            return this.delCart(cartDTO.getCnum());
        }else {
            //수량이 양수인 경우  ==> 수정처리
            int n = cartMapper.editCart(cartDTO);
            return n;
        }
    }

    @Override
    public List<CartDTO> findCartView(String userid) {
        return cartMapper.selectCartView(userid);
    }

    @Override
    public int delCart(int cartNum) {
        return cartMapper.delCart(cartNum);
    }

    @Override
    public int delCartAll(CartDTO cartDTO) {
        return 0;
    }

    @Override
    public int delCartOrder(Map<String, Integer> map) {
        return 0;
    }

    @Override
    public CartDTO getCartTotal(String userid) {
        return cartMapper.getCartTotal(userid);
    }
}
