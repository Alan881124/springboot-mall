package com.alanlee.springbootmall.rowmapper;

import com.alanlee.springbootmall.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {

        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setTotal_amount(rs.getInt("total_amount"));
        order.setCreated_date(rs.getTimestamp("created_date"));
        order.setLast_modified_date(rs.getTimestamp("last_modified_date"));

        return order;
    }
}
