package com.metabuild.shop.service;

import com.metabuild.shop.domain.ProductDTO;

import java.util.List;

public interface ProductServie {

    int insertProduct(ProductDTO pdto);
    ProductDTO findByPnum(int pnum);
    int updateProduct(ProductDTO pdto);
    int deleteProduct(int pnum);
    List<ProductDTO> findProduct(ProductDTO pdto);
    int selectFile(int pnum);
}
