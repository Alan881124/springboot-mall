package com.alanlee.springbootmall.dao;

import com.alanlee.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
