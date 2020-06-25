package com.sxs.bookstore.service.Impl;

import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.beans.OrderItem;
import com.sxs.bookstore.beans.Product;
import com.sxs.bookstore.dao.IProductInfoDao;
import com.sxs.bookstore.service.IProductInfoService;
import com.sxs.bookstore.utils.PageModel;
import org.apache.taglibs.standard.tei.ImportTEI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/25/025
 */
@Service
public class IProductInfoServiceImpl implements IProductInfoService {
    @Autowired
    private IProductInfoDao dao;

    @Override
    public PageModel findProductsInfo(String category, int pageIndex) {
        int totalRows = dao.findProductsCount(category);
        PageModel pageModel = new PageModel();
        pageModel.setRecordCount(totalRows);
        pageModel.setPageIndex(pageIndex);
        List list = dao.findProducts(pageModel.getFirstLimitParam(),pageModel.getPageSize(),category);
        pageModel.setData(list);
        return pageModel;
    }

    @Override
    public PageModel findBooksByName(String bookname, int pageIndex) {
        int totalRows = dao.findBooksCount(bookname);
        PageModel booksModel = new PageModel();
        booksModel.setRecordCount(totalRows);
        booksModel.setPageIndex(pageIndex);
        List list = dao.findBooksByName(booksModel.getFirstLimitParam(),booksModel.getPageSize(),bookname);
        booksModel.setData(list);
        return booksModel;
    }

    @Override
    public Product findProductById(String id) {
        Product product = dao.findProductById(id);
        return product;
    }

    @Override
    public void createOrder(Order order, Map<Product, Integer> cart) {
        for (Product product:cart.keySet()){
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setBuynum(cart.get(product));

            //修改产品的库存
            dao.updateProductPnum(item);
            //插入订单项OrderItem表
            dao.insertOrderItem(item);

        }
        dao.insertOrder(order);
    }
}
