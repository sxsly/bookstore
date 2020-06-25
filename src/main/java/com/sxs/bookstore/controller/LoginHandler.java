package com.sxs.bookstore.controller;

import com.sxs.bookstore.beans.User;
import com.sxs.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/11/011
 */
@Controller
@RequestMapping("/user")
public class LoginHandler {

    @Autowired
    private IUserService service;

    // @RequestMapping("/findUsers.do")
    // public String doLoginHandle(){
    //     User user = service.findUsers();
    //     return user.toString();
    // }

    // 实现用户登录
    @RequestMapping("/login.do")
    public String loginHandle(User user, HttpSession session, Model model, HttpServletResponse response,String[] checkbox){
        User login_user = service.findUser(user);
        // System.out.println(checkbox[0]+".....");
        Cookie remecookie = null;
        Cookie cookie = null;
        if (login_user != null && login_user.getStatus() == 1){
            for (int i=0;checkbox != null && i<checkbox.length;i++){
                if (checkbox[i] != null && checkbox[i].equals("remember")){
                    remecookie = new Cookie("username",user.getUsername());
                    remecookie.setMaxAge(30*60*60*24);
                    remecookie.setPath(session.getServletContext().getContextPath());
                    response.addCookie(remecookie);
                }else if (checkbox[i] != null && checkbox[i].equals("autologin")){
                    cookie = new Cookie("autologin",user.getUsername()+"-"+user.getPassword());
                    cookie.setMaxAge(30*60*60*24);
                    cookie.setPath(session.getServletContext().getContextPath());
                    response.addCookie(cookie);
                }
            }
            //如果一个也没选的情况，将cookie设置为空
            if (checkbox == null){
                remecookie = new Cookie("username",null);
                cookie = new Cookie("autologin",null);
                remecookie.setMaxAge(0);
                cookie.setMaxAge(0);
                remecookie.setPath(session.getServletContext().getContextPath());
                cookie.setPath(session.getServletContext().getContextPath());
                response.addCookie(remecookie);
                response.addCookie(cookie);
            }else if (checkbox != null && checkbox.length == 1 && checkbox[0].equals("remember")){
                cookie = new Cookie("autologin",null);
                cookie.setMaxAge(0);
                cookie.setPath(session.getServletContext().getContextPath());
                response.addCookie(cookie);
            }else if (checkbox != null && checkbox.length == 1 && checkbox[0].equals("autologin")){
                remecookie = new Cookie("username",null);
                remecookie.setMaxAge(0);
                remecookie.setPath(session.getServletContext().getContextPath());
                response.addCookie(remecookie);
            }

            session.setAttribute("login_user",login_user);
            return "redirect:/index.jsp";
        }else if (login_user != null && login_user.getStatus() == 0){
            model.addAttribute("errorMsg","该账号尚未激活，无法进行登录！");
            return "/client/login.jsp";
        }else {
            model.addAttribute("errorMsg","您输入的账号或密码不正确，请确认后登录！");
            return "/client/login.jsp";
        }
        // System.out.println(checkbox[0]+"..."+checkbox[1]);
        // return null;
    }

    /**
     * *******************************************
     */
    //实现网站后台登录
    @RequestMapping("/adminLogin.do")
    public String adminLoginHandle(User user,HttpSession session,Model model){
         User admin_user = service.findUser(user);
         if (admin_user != null && admin_user.getRole().equals("管理员")){
             session.setAttribute("admin_user",admin_user);
             return "redirect:/admin/login/home.jsp";
         } else {
             model.addAttribute("errMsg","您输入的账号或密码不正确");
             return "/admin/login/login.jsp";
         }
    }
}
