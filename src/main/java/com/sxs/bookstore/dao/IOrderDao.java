package com.sxs.bookstore.dao;

import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.beans.OrderItem;
import com.sxs.bookstore.beans.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/2/002
 */
@Mapper
public interface IOrderDao {

    List findOrderByUser(Map map);

    Order findOrderById(String id);

    List findOrderItemById(String id);

    int findTotalRows(User login_user);

    int removeOrderById(String id);

    int removeOrderItemById(String id);

    void updatePayStatus(String id);

    int updateProductsPnum(OrderItem item);
}
