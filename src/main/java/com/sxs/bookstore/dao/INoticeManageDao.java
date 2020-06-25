package com.sxs.bookstore.dao;

import com.sxs.bookstore.beans.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/14/014
 */
@Mapper
public interface INoticeManageDao {
    List findNotices(Map map);

    int addNotice(Notice notice);

    Notice findNoticeById(String id);

    int modifyNotice(Notice notice);

    int removeNoticeById(String id);

    int findTotalRows();
}
