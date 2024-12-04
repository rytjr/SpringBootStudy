package com.metabuild.shop.service;

import com.metabuild.shop.domain.ProductDTO;
import com.metabuild.shop.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return productMapper.findByPnum(pnum);
    }

    @Override
    public int updateProduct(ProductDTO pdto) {
        return 0;
    }

    @Override
    public int deleteProduct(int pnum) {
        return productMapper.deleteProduct(pnum);
    }

    @Override
    public List<ProductDTO> findProduct(ProductDTO pdto) {
        return productMapper.findProduct(pdto);
    }
}
