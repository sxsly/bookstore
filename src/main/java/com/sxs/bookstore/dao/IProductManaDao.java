package com.sxs.bookstore.dao;

import com.sxs.bookstore.beans.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/11/011
 */
@Mapper
public interface IProductManaDao {
    List findAllProducts(Map map);

    List findAllCategories();

    int addProduct(Product product);

    int removeProduct(String id);

    int modifyProduct(Product product);

    int findTotalRows(Map map);

    List findProductSalList(@Param("year") int year, @Param("month") int month);
}
