package com.metabuild.shop.mapper;

import com.metabuild.shop.domain.CategoryDTO;
import com.metabuild.shop.domain.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductDTO> findByPspec(String pspec);
    ProductDTO getProduct(int pnum);
    List<CategoryDTO> getCategory();
    List<ProductDTO> findByCategory(int cg_code);
    int insertProduct(ProductDTO pdto);
}