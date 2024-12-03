package com.metabuild.shop.service;

import com.metabuild.shop.domain.ProductDTO;
import com.metabuild.shop.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServie {

    private final ProductMapper productMapper;

    @Override
    public int insertProduct(ProductDTO pdto) {
        return productMapper.insertProduct(pdto);
    }

    @Override
    public ProductDTO findByPnum(int pnum) {
        return null;
    }

    @Override
    public int updateProduct(ProductDTO pdto) {
        return 0;
    }

    @Override
    public int deleteProduct(int pnum) {
        return 0;
    }
}
