package com.sxs.bookstore.service.Impl;

import com.sxs.bookstore.beans.Notice;
import com.sxs.bookstore.dao.INoticeDao;
import com.sxs.bookstore.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/6/006
 */
@Service
public class INoticeServiceImpl implements INoticeService {
    @Autowired
    private INoticeDao dao;

    @Override
    public Notice findNotice() {
        Notice notice = dao.findNotice();
        return notice;
    }

    @Override
    public List findWeekHotProduct() {
        List list = dao.findWeekHotProduct();
        return list;
    }
}
