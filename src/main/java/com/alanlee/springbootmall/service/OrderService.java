package com.alanlee.springbootmall.service;

import com.alanlee.springbootmall.dto.CreateOrderRequest;
import com.alanlee.springbootmall.dto.OrderQueryParams;
import com.alanlee.springbootmall.model.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);


}
