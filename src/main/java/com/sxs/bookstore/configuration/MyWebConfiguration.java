// package com.sxs.bookstore.configuration;
//
// import com.sxs.bookstore.interceptor.LoginInterceptor;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
// /**
//  * company: www.abc.com
//  * Author: Administrator
//  * Create Data: 2019/5/20/020
//  */
// @Configuration
// public class MyWebConfiguration extends WebMvcConfigurationSupport {
//     protected void addInterceptors(InterceptorRegistry registry){
//         LoginInterceptor interceptor = new LoginInterceptor();
//         registry.addInterceptor(interceptor)
//                 .addPathPatterns("/client/*")
//                 .excludePathPatterns("*.jpg");
//     }
//
// }
