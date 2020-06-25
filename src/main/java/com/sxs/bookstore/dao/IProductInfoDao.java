package com.sxs.bookstore.dao;

import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.beans.OrderItem;
import com.sxs.bookstore.beans.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/25/025
 */
@Mapper
public interface IProductInfoDao {
    List findProducts(@Param("firstLimitParam") int firstLimitParam, @Param("pageSize") int pageSize, @Param("category") String category);

    int findProductsCount(@Param("category") String category);

    int findBooksCount(@Param("bookname") String bookname);

    List findBooksByName(@Param("firstLimitParam")int firstLimitParam, @Param("pageSize")int pageSize, @Param("bookname")String bookname);

    Product findProductById(@Param("id") String id);

    void updateProductPnum(OrderItem item);

    void insertOrderItem(OrderItem item);

    void insertOrder(Order order);
}
