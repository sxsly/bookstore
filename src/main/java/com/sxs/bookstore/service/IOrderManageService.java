package com.sxs.bookstore.service;

import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.beans.OrderItem;
import com.sxs.bookstore.beans.User;
import com.sxs.bookstore.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/2/002
 */
public interface IOrderManageService {
    PageModel findOrderByUser(int pageIndex, User login_user);

    Order findOrderById(String id);

    int removeOrderById(String id);

    int removeOrderItemById(String id);

    void modifyPayStatus(String id);

    List findOrderItemById(String id);

    int updateProductsPnum(OrderItem item);
}
