package com.sxs.bookstore.controller;

import com.sxs.bookstore.beans.Notice;
import com.sxs.bookstore.service.INoticeManageService;
import com.sxs.bookstore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/5/14/014
 */
@Controller
@RequestMapping("/noticeManage")
public class NoticeManaHandler {

    @Autowired
    private INoticeManageService service;

    @RequestMapping("/findNotices.do")
    public String findNotices(@RequestParam(defaultValue = "1") int pageIndex, Model model){
        PageModel noticeModel = service.findNotices(pageIndex);
        model.addAttribute("noticeModel",noticeModel);
        return "/admin/notices/list.jsp";
    }

    // 添加公告
    @RequestMapping("/addNotice.do")
    @ResponseBody
    public String addNotice(Notice notice){
        int rows = service.addNotice(notice);
        if (rows > 0){
            return "OK";
        }else {
            return "FAIL";
        }
    }

    // 根据ID查询公告信息
    @RequestMapping("/findNoticeById.do")
    public String findNoticeById(String id,Model model){
        Notice notice = service.findNoticeById(id);
        model.addAttribute("notice",notice);
        return "/admin/notices/edit.jsp";
    }

    // 修改公告信息
    @RequestMapping("/modifyNotice.do")
    @ResponseBody
    public String modifyNotice(Notice notice){
        int rows = service.modifyNotice(notice);
        if (rows > 0){
            return "OK";
        }
        return "FAIL";
    }

    // 删除公告信息
    @RequestMapping("/removeNoticeById.do")
    @ResponseBody
    public String removeNoticeById(String id){
        int rows = service.removeNoticeById(id);
        if (rows > 0){
            return "OK";
        }
        return "FAIL";
    }
}
