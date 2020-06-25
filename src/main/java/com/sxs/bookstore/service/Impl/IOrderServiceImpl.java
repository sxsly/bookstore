package com.sxs.bookstore.service.Impl;

import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.dao.IOrderDao;
import com.sxs.bookstore.dao.IOrderManageDao;
import com.sxs.bookstore.service.IOrderService;
import com.sxs.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/14/014
 */
@Service
public class IOrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderManageDao dao;

    @Override
    public PageModel findAllOrders(int pageIndex,Order order) {
        int rows = dao.findTotalRows(order);
        PageModel pageModel = new PageModel();
        pageModel.setRecordCount(rows);
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(4);
        Map map = new HashMap();
        map.put("order",order);
        // map.put("pageModel",pageModel);
        // map.put("id",order.getId());
        // map.put("receivername",order.getReceivername());
        map.put("firstLimitParam",pageModel.getFirstLimitParam());
        map.put("pageSize",pageModel.getPageSize());
        List list = dao.findAllOrders(map);
        pageModel.setData(list);
        return pageModel;
    }
}
