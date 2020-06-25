package com.sxs.bookstore.service;

import com.sxs.bookstore.beans.Product;
import com.sxs.bookstore.utils.PageModel;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/11/011
 */
public interface IProductManaService {
    PageModel findAllProducts(Map map);

    List findAllCategories();

    int addProduct(Product product);

    int removeProduct(String id);

    int modifyProduct(Product product);

    List findProductSalList(int year, int month);
}
