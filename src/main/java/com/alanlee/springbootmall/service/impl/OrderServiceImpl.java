package com.alanlee.springbootmall.service.impl;

import com.alanlee.springbootmall.dao.OrderDao;
import com.alanlee.springbootmall.dao.ProductDao;
import com.alanlee.springbootmall.dto.BuyItem;
import com.alanlee.springbootmall.dto.CreateOrderRequest;
import com.alanlee.springbootmall.model.Order;
import com.alanlee.springbootmall.model.OrderItem;
import com.alanlee.springbootmall.model.Product;
import com.alanlee.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;

    }

    // 加上這個可以確保這兩個資料庫操作同時發生or同時不發生
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            // 計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;


            // 轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct_id(buyItem.getProductId() );
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

        }


        // 創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
