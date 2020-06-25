package com.sxs.bookstore.service.Impl;

import com.sxs.bookstore.beans.Notice;
import com.sxs.bookstore.dao.INoticeManageDao;
import com.sxs.bookstore.service.INoticeManageService;
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
public class INoticeManageServceImpl implements INoticeManageService {

    @Autowired
    private INoticeManageDao dao;

    @Override
    public PageModel findNotices(int pageIndex) {
        int totalRows = dao.findTotalRows();
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(totalRows);
        pageModel.setPageSize(4);
        Map map = new HashMap();
        map.put("firstLimitParam",pageModel.getFirstLimitParam());
        map.put("pageSize",pageModel.getPageSize());
        // map.put("pageModel",pageModel);
        List list = dao.findNotices(map);
        pageModel.setData(list);
        return pageModel;
    }

    @Override
    public int addNotice(Notice notice) {
        int rows = dao.addNotice(notice);
        return rows;
    }

    @Override
    public Notice findNoticeById(String id) {
        Notice notice = dao.findNoticeById(id);
        return notice;
    }

    @Override
    public int modifyNotice(Notice notice) {
        int rows = dao.modifyNotice(notice);
        return rows;
    }

    @Override
    public int removeNoticeById(String id) {
        int rows = dao.removeNoticeById(id);
        return rows;
    }
}
