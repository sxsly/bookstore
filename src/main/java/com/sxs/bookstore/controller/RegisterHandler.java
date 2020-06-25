package com.sxs.bookstore.controller;

import com.sxs.bookstore.beans.User;
import com.sxs.bookstore.service.IUserService;
import com.sxs.bookstore.utils.ActiveCodeUtils;
import com.sxs.bookstore.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/4/13/013
 */
@Controller
@RequestMapping("/user")
public class RegisterHandler {

    @Autowired
    private IUserService service;

    @RequestMapping("/userRegister.do")
    public String userRegister(User user, HttpSession session, String checkWord, Model model) throws MessagingException {
        String word = (String) session.getAttribute("checkcode_session");
        if (checkWord.equals(word)){
            String email = user.getEmail();
            String emailMsg = ActiveCodeUtils.createActiveCode();
            MailUtils.sendMail(email,emailMsg);
            user.setActivecode(emailMsg);
            user.setRole("普通用户");
            int rows = service.addUser(user);
            if (rows >0){
                return "redirect:/client/registersuccess.jsp";
            }else {
                return "redirect:/client/error/registerFail.jsp";
            }
        }
        model.addAttribute("errorMsg","验证码输入错误，请核对重试！");
        return "/client/register.jsp";
    }

    //使用激活码激活账号
    @RequestMapping("/activeUser.do")
    public String activeUser(String activeCode,String activeUsername,Model model){
        model.addAttribute("activeCode",activeCode);
        model.addAttribute("activeUsername",activeUsername);
        String bStatus = service.findStatus(activeUsername);
        if (bStatus != null && bStatus.equals("0")){
            String actCode = service.findActiveCode(activeUsername);
            // System.out.println(activeCode+"......"+activeUsername+"...."+actCode);
            if (actCode != null && actCode.equals(activeCode)){
                int rows = service.updateStatus(activeUsername);
                if (rows > 0){
                    return "redirect:/client/activesuccess.jsp";
                }else {
                    return "redirect:/client/error/activeFail.jsp";
                }
            }
            model.addAttribute("errorMsg","激活码与激活账号不匹配，请重新输入！");
            return "/client/active.jsp";
        }else if (bStatus == null){
            model.addAttribute("errorMsg","该账号不存在，请确认后输入！");
            return "/client/active.jsp";
        }
        model.addAttribute("errorMsg","该账号已经激活过，无需重复激活！");
        return "/client/active.jsp";
    }

    // 检查邮箱是否存在
    @RequestMapping("/checkemail.do")
    @ResponseBody
    public String checkemail(String email){
          User user = service.findUserByEmail(email);
          if (user != null){
              return "exist";
          }else {
              return "null";
          }
    }

    // 检查用户名是否存在
    @RequestMapping("/checkusername.do")
    @ResponseBody
    public String checkusername(String username){
        User user = service.findUserByUsername(username);
        if (user != null){
            return "exist";
        }else {
            return "null";
        }
    }
}
