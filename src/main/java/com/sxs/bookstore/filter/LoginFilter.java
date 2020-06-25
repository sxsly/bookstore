package com.sxs.bookstore.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/16/016
 */
@WebFilter(filterName = "LoginFilter",urlPatterns = "/client/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String [ ]login_formate = {"index.jsp","register.jsp","login.jsp","active.jsp","registersuccess.jsp","activesuccess.jsp","cart.jsp",".jpg",".js",".css",".gif",".png"};
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String currentPath = request.getRequestURI();
        boolean flag = true;
        for(String s:login_formate){
            if(currentPath.endsWith(s)){
                chain.doFilter(request,response);
                flag = false;
            }
        }
        if (flag){
            HttpSession session = request.getSession();
            if(session.getAttribute("login_user") != null){
                chain.doFilter(request,response);
            }else{
                response.sendRedirect("/bookstore/client/login.jsp");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
