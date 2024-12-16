package com.alanlee.springbootmall.rowmapper;

import com.alanlee.springbootmall.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_item_id(rs.getInt("order_item_id"));
        orderItem.setOrder_id(rs.getInt("order_id"));
        orderItem.setProduct_id(rs.getInt("product_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setAmount(rs.getInt("amount"));

        orderItem.setProduct_name(rs.getString("product_name"));
        orderItem.setImage_url(rs.getString("image_url"));

        return orderItem;
    }
}
