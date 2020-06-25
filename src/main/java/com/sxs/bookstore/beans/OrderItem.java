package com.sxs.bookstore.beans;

import lombok.Data;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/29/029
 */
@Data
public class OrderItem {
    private Order order;
    private Product product;
    private Integer buynum;
}
