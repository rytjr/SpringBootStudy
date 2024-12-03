package com.metabuild.shop.mapper;

import com.metabuild.shop.domain.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    Integer selectCartNum(CartDTO cvo);//pnum,userid로 장바구니번호(cnum)가져노기
    int updateCartQty(CartDTO cvo);
    int addCart(CartDTO cvo);

    List<CartDTO> selectCartView(String userId);
    CartDTO getCartTotal(String userId);

    int delCart(int cartNum);
    int editCart(CartDTO cvo);
    int delCartAll(String userId);
}
