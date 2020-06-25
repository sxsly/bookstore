package com.sxs.bookstore.controller;

import com.sxs.bookstore.beans.Notice;
import com.sxs.bookstore.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/6/006
 */
@Controller
@RequestMapping("/notice")
public class NoticeHandler {
    @Autowired
    private INoticeService service;

    @RequestMapping("/showNotice.do")
    public String showNotice(Model model){
        Notice notice = service.findNotice();
        model.addAttribute("notice",notice);
        List pList = service.findWeekHotProduct();
        model.addAttribute("pList",pList);
        return "/client/index.jsp";
    }
}
