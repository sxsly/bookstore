package com.sxs.bookstore.beans;

import lombok.Data;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/25/025
 */
@Data
public class Product {
    private String id;
    private String name;
    private double price;
    private String category;
    private Integer pnum;
    private String imgurl;
    private String description;
    private String salnum;
}
