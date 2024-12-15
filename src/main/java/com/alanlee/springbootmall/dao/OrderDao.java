package com.alanlee.springbootmall.dao;

import com.alanlee.springbootmall.model.Order;
import com.alanlee.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Order getOrderById(int id);

    List<OrderItem> getOrderItemsByOrderId(int id);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

}
