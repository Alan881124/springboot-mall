package com.alanlee.springbootmall.dao;

import com.alanlee.springbootmall.dto.OrderQueryParams;
import com.alanlee.springbootmall.model.Order;
import com.alanlee.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer id);

    List<OrderItem> getOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);

}
