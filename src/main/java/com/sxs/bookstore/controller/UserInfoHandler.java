package com.sxs.bookstore.controller;

import com.sxs.bookstore.beans.User;
import com.sxs.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/24/024
 */
@Controller
@RequestMapping("/user")
public class UserInfoHandler {
    @Autowired
    private IUserService service;

    @RequestMapping(value = "/modifyUserInfo.do")
    @ResponseBody
    public String modifyUserInfo(User user, HttpSession session){
        User login_user = (User) session.getAttribute("login_user");
        user.setUsername(login_user.getUsername());
        // System.out.print(user);
        int rows = service.modifyUserInfo(user);
        if (rows > 0){
            return "success";
        }else {
            return "fail";
        }
    }

    // 退出登录
    @RequestMapping("/logout.do")
    public String logout(HttpSession session, HttpServletResponse response){
        session.removeAttribute("login_user");
        Cookie cookie = new Cookie("autologin",null);
        cookie.setMaxAge(0);
        cookie.setPath(session.getServletContext().getContextPath());
        response.addCookie(cookie);
        return "redirect:/client/login.jsp";
    }

    // 管理员退出登录
    @RequestMapping("/adminLogout.do")
    public String adminLogout(HttpSession session){
        session.removeAttribute("admin_user");
        return "redirect:/admin/login/login.jsp";
    }
}
