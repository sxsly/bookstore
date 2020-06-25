package com.sxs.bookstore.exception;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/16/016
 */
@Configuration
public class Global500and404ExceptionHandler implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[3];
        errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
        errorPages[1] = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED,"/405.html");
        errorPages[2] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html");

        registry.addErrorPages(errorPages);
    }
}
