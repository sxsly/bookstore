package com.sxs.bookstore.service.Impl;

import com.sxs.bookstore.beans.User;
import com.sxs.bookstore.dao.IUserDao;
import com.sxs.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/11/011
 */
@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao dao;

    // @Override
    // public User findUsers() {
    //    User user = dao.selectUsers();
    //    return user;
    // }

    @Override
    public int addUser(User user) {
        int rows = dao.insertUser(user);
        return rows;
    }

    @Override
    public String findActiveCode(String activeUsername) {
        String activeCode = dao.findCode(activeUsername);
        return activeCode;
    }

    @Override
    public int updateStatus(String activeUsername) {
        int rows = dao.modifyStatus(activeUsername);
        return rows;
    }

    @Override
    public String findStatus(String activeUsername) {
        String bStatus = dao.selectStatus(activeUsername);
        return bStatus;
    }

    @Override
    public User findUser(User user) {
        User login_user = dao.selectUser(user);
        return login_user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = dao.findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = dao.findUserByUsername(username);
        return user;
    }

    @Override
    public int modifyUserInfo(User user) {
        int rows = dao.modifyUserInfo(user);
        return rows;
    }
}
