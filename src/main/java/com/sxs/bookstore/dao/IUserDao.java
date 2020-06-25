package com.sxs.bookstore.dao;

import com.sxs.bookstore.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/11/011
 */
@Mapper
public interface IUserDao {
    // User selectUsers();

    int insertUser(User user);

    String findCode(String activeUsername);

    int modifyStatus(String activeUsername);

    String selectStatus(String activeUsername);

    User selectUser(User user);

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    int modifyUserInfo(User user);
}
