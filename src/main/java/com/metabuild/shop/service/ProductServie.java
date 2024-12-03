package com.metabuild.shop.service;

import com.metabuild.shop.domain.ProductDTO;

public interface ProductServie {

    int insertProduct(ProductDTO pdto);
    ProductDTO findByPnum(int pnum);
    int updateProduct(ProductDTO pdto);
    int deleteProduct(int pnum);
}
