package com.sxs.bookstore.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/18/018
 */
@WebFilter(filterName = "RemeUsernameFilter",urlPatterns = "*.jsp")
public class RemeUsernameFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String currentpath = request.getRequestURI();
        if (currentpath.endsWith("login.jsp")){
            String username = null;
            Cookie[] cookies = request.getCookies();
            for (int i=0;cookies != null && i < cookies.length;i++){
                if (cookies[i] != null && "username".equals(cookies[i].getName())){
                    username = cookies[i].getValue();
                    break;
                }
            }
            // System.out.println("测试过滤器有没有被调用！");
            request.setAttribute("username",username);
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
