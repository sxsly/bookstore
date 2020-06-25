package com.sxs.bookstore.service;

import com.sxs.bookstore.beans.Notice;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/6/006
 */
public interface INoticeService {
    Notice findNotice();

    List findWeekHotProduct();
}
