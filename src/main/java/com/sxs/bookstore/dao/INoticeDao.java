package com.sxs.bookstore.dao;

import com.sxs.bookstore.beans.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/6/006
 */
@Mapper
public interface INoticeDao {
    Notice findNotice();

    List findWeekHotProduct();
}
