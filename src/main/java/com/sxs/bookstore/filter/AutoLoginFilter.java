package com.sxs.bookstore.filter;

import com.sxs.bookstore.beans.User;
import com.sxs.bookstore.service.IUserService;
import com.sxs.bookstore.service.Impl.IUserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/18/018
 */
@WebFilter(filterName = "AutoLoginFilter",urlPatterns = "*.jsp")
public class AutoLoginFilter implements Filter {

    private IUserService service;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        boolean flag = true;
        String currentpath = request.getRequestURI();

        if (currentpath.endsWith("login.jsp")){
            // 获取所有的Cookies
            Cookie[] cookies = request.getCookies();
            String autologin = null;
            for (int i=0;cookies != null && i < cookies.length;i++){
                if ("autologin".equals(cookies[i].getName())){
                    autologin = cookies[i].getValue();
                    break;
                }
            }

            if (autologin != null){
                request.setAttribute("autologin",autologin);
            }
        }

        if (session.getAttribute("login_user") != null){
            chain.doFilter(request,response);
            flag = false;
        }

        if (flag){
            ServletContext context = request.getServletContext();
            ApplicationContext ap = WebApplicationContextUtils.getWebApplicationContext(context);
            service = ap.getBean(IUserServiceImpl.class);
            // service = new IUserServiceImpl();

            // 获取所有的Cookies
            Cookie[] cookies = request.getCookies();
            String autologin = null;
            for (int i=0;cookies != null && i < cookies.length;i++){
                if ("autologin".equals(cookies[i].getName())){
                    autologin = cookies[i].getValue();
                    break;
                }
            }

            if (autologin != null){
                //自动登录
                String[] parts = autologin.split("-");
                String loginname = parts[0];
                String password = parts[1];
                User user = new User();
                user.setUsername(loginname);
                user.setPassword(password);
                //检查用户名和密码是否正确
                User login_user = service.findUser(user);
                if (login_user != null){
                    session.setAttribute("login_user",login_user);
                }
            }
            chain.doFilter(request,response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
