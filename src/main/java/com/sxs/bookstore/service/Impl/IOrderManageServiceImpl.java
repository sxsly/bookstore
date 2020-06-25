package com.sxs.bookstore.service.Impl;

import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.beans.OrderItem;
import com.sxs.bookstore.beans.User;
import com.sxs.bookstore.dao.IOrderDao;
import com.sxs.bookstore.service.IOrderManageService;
import com.sxs.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/2/002
 */
@Service
public class IOrderManageServiceImpl implements IOrderManageService {
    @Autowired
    private IOrderDao dao;

    @Override
    public PageModel findOrderByUser(int pageIndex, User login_user) {
        int rows = dao.findTotalRows(login_user);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(rows);
        Map map = new HashMap();
        map.put("pageModel",pageModel);
        map.put("user",login_user);
        List list = dao.findOrderByUser(map);
        pageModel.setData(list);
        return pageModel;
    }

    @Override
    public Order findOrderById(String id) {
        Order order = dao.findOrderById(id);
        List list = dao.findOrderItemById(id);
        order.setOrderItems(list);
        return order;
    }

    @Override
    public int removeOrderById(String id) {
        int rows = dao.removeOrderById(id);
        return rows;
    }

    @Override
    public int removeOrderItemById(String id) {
        int rows = dao.removeOrderItemById(id);
        return rows;
    }

    @Override
    public void modifyPayStatus(String id) {
        dao.updatePayStatus(id);
    }

    @Override
    public List findOrderItemById(String id) {
        List list = dao.findOrderItemById(id);
        return list;
    }

    @Override
    public int updateProductsPnum(OrderItem item) {
        int rows = dao.updateProductsPnum(item);
        return rows;
    }
}
