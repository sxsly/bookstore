package com.sxs.bookstore.beans;

import lombok.Data;

import java.util.Date;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/11/011
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String email;
    private String telphone;
    private String introduce;
    private String activecode;
    private int status;
    private String role;
    private Date registtime;

}
