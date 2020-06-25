package com.sxs.bookstore.controller;

import com.sxs.bookstore.beans.Order;
import com.sxs.bookstore.service.IOrderService;
import com.sxs.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/14/014
 */
@Controller
@RequestMapping("/orderManage")
public class OrderManaHandler {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/findAllOrders.do")
    public String findAllOrders(@RequestParam(defaultValue = "1") int pageIndex, Order order, Model model){
        model.addAttribute("order",order);
        PageModel orderModel = orderService.findAllOrders(pageIndex,order);
        model.addAttribute("orderModel",orderModel);
        return "/admin/orders/list.jsp";
    }
}
