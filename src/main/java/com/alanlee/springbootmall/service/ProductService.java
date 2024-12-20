package com.alanlee.springbootmall.service;

import com.alanlee.springbootmall.dto.ProductQueryParams;
import com.alanlee.springbootmall.dto.ProductRequest;
import com.alanlee.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productRequest);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    //沒有返回值
    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
