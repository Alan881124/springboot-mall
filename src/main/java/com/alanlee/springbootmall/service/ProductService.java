package com.alanlee.springbootmall.service;

import com.alanlee.springbootmall.dto.ProductRequest;
import com.alanlee.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    //沒有返回值
    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
