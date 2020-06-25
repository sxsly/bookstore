package com.sxs.bookstore.beans;

import lombok.Data;

import java.util.Date;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/6/006
 */
@Data
public class Notice {
    private Integer n_id;
    private String title;
    private String details;
    private Date n_time;
}
