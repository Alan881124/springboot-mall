package com.alanlee.springbootmall.dao.Impl;

import com.alanlee.springbootmall.dao.OrderDao;
import com.alanlee.springbootmall.dto.OrderQueryParams;
import com.alanlee.springbootmall.model.Order;
import com.alanlee.springbootmall.model.OrderItem;
import com.alanlee.springbootmall.rowmapper.OrderItemRowMapper;
import com.alanlee.springbootmall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer id) {
        return List.of();
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "select count(*) from `order` where 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "select order_id, user_id, total_amount, created_date, last_modified_date" +
                " from `order` where 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);

        // 排序
        sql = sql + " order by created_date desc";

        // 分頁
        sql = sql + " Limit :limit Offset :offset";
        map.put("limit", orderQueryParams.getLimit());
        map.put("offser", orderQueryParams.getOffset());

        List<Order> orders = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        return orders;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "select order_id, user_id, total_amount, created_date," +
                " last_modified_date from `order` where order_id = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        if (orderList.size() > 0) {
            return orderList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderId(Integer orderId) {
        String sql = "select oi.order_item_id, oi.order_id, oi.quantity, oi.amount, p.product_name, p.image_url" +
                "from `order_item` as oi left join `product` as p on oi.product_id = p.product_id where" +
                " oi.order_id = :oi.orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());

        if (orderItemList.size() > 0) {
            return orderItemList;
        }else {
            return null;
        }
    }

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = "insert into `order`(user_id,total_amount," +
                " created_date, last_modified_date) values(:userId," +
                " :totalAmount, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    // 使用 batchUpdate 一次性加入數據,效率更高
    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "insert into `order_item`(order_id, product_id, quantity, amount)" +
                " values (:orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] parameterSource = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);

            parameterSource[i] = new MapSqlParameterSource();
            parameterSource[i].addValue("orderId", orderId);
            parameterSource[i].addValue("productId", orderItem.getProductId());
            parameterSource[i].addValue("quantity", orderItem.getQuantity());
            parameterSource[i].addValue("amount", orderItem.getAmount());
        }
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSource);
    }
    private String addFilteringSql(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams) {

        if (orderQueryParams.getUserId() != null) {
            sql = sql + " and user_id = :userId ";
            map.put("userId", orderQueryParams.getUserId());
        }

        return sql;

    }
}
