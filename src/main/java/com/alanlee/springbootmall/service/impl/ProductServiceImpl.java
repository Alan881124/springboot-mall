package com.alanlee.springbootmall.service.impl;

import com.alanlee.springbootmall.dao.ProductDao;
import com.alanlee.springbootmall.model.Product;
import com.alanlee.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);

    }
}
