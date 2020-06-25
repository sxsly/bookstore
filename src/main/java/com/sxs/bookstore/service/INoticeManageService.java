package com.sxs.bookstore.service;

import com.sxs.bookstore.beans.Notice;
import com.sxs.bookstore.utils.PageModel;


/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/14/014
 */
public interface INoticeManageService {
    PageModel findNotices(int pageIndex);

    int addNotice(Notice notice);

    Notice findNoticeById(String id);

    int modifyNotice(Notice notice);

    int removeNoticeById(String id);
}
