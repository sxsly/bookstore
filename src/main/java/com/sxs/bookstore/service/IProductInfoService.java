package com.sxs.bookstore.service;

import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.beans.Product;
import com.sxs.bookstore.utils.PageModel;

import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/25/025
 */
public interface IProductInfoService {

    PageModel findProductsInfo(String category, int pageIndex);

    PageModel findBooksByName(String bookname, int pageIndex);

    Product findProductById(String id);

    void createOrder(Order order, Map<Product, Integer> cart);
}
