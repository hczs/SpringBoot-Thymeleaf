package com.hou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @RequestMapping("/user/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        //清除session，会清除session
//        session.invalidate();
        //也可以把用户登录的属性删除 也可以达到注销用户的功能
        session.removeAttribute("LoginUser");
        return "index";
    }
}
