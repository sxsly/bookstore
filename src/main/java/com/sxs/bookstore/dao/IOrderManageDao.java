package com.sxs.bookstore.dao;

import com.sxs.bookstore.beans.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/14/014
 */
@Mapper
public interface IOrderManageDao {
    List findAllOrders(Map map);

    int findTotalRows(Order order);
}
