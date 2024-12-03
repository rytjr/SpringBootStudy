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
        return 0;
    }

    @Override
    public int updateCartQty(CartDTO cartDTO) {
        return 0;
    }

    @Override
    public int editCart(CartDTO cartDTO) {
        return 0;
    }

    @Override
    public List<CartDTO> findCartView(String userid) {
        return List.of();
    }

    @Override
    public int delCart(int cartNum) {
        return 0;
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
        return null;
    }
}
