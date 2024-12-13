package com.alanlee.springbootmall.dao;

import com.alanlee.springbootmall.dto.ProductQueryParams;
import com.alanlee.springbootmall.dto.ProductRequest;
import com.alanlee.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
