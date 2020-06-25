// package com.sxs.bookstore.interceptor;
//
// import org.springframework.web.servlet.HandlerInterceptor;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
//
// /**
//  * company: www.abc.com
//  * Author: Administrator
//  * Create Data: 2019/5/20/020
//  */
// public class LoginInterceptor implements HandlerInterceptor {
//
//     public boolean preHandle(HttpServletRequest request,
//                              HttpServletResponse response, Object handler) throws Exception {
//         String [ ]login_formate = {"index.jsp","register.jsp","login.jsp","active.jsp","registersuccess.jsp","activesuccess.jsp","cart.jsp"};
//         String currentPath = request.getRequestURI();
//         for(String s:login_formate){
//             if(currentPath.endsWith(s)){
//                 return true;
//             }
//         }
//
//             HttpSession session = request.getSession();
//             if(session.getAttribute("login_user") != null){
//                 return true;
//             }else{
//                 response.sendRedirect("/bookstore/client/login.jsp");
//             }
//
//         return true;
//     }
// }
