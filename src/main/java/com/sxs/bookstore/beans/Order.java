package com.sxs.bookstore.beans;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/29/029
 */
@Data
public class Order {
    private String id;
    private double money;
    private String receiveraddress;
    private String receivername;
    private String receiverphone;
    private Integer paystatus;
    private Date ordertime;
    private User user;
    private List orderItems;

}
