package com.sxs.bookstore.service;

import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/14/014
 */
public interface IOrderService {
    PageModel findAllOrders(int pageIndex,Order order);
}
