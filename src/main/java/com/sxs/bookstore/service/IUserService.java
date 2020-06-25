package com.sxs.bookstore.service;

import com.sxs.bookstore.beans.User;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/11/011
 */
public interface IUserService {
    // User findUsers();

    int addUser(User user);

    String findActiveCode(String activeUsername);

    int updateStatus(String activeUsername);

    String findStatus(String activeUsername);

    User findUser(User user);

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    //修改用户信息
    int modifyUserInfo(User user);
}
