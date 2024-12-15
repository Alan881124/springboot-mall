package com.alanlee.springbootmall.service;

import com.alanlee.springbootmall.dto.CreateOrderRequest;
import com.alanlee.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);


}
