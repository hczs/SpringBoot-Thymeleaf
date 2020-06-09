package com.hou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

//登录控制controller
@Controller
public class LoginController {

    //登录验证
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session){
        //检查用户名和密码是否正确
        if("admin".equals(username) && "123456".equals(password)){
            //当用户名为admin且密码是123456的时候可以进入主页面
            session.setAttribute("LoginUser",username);
            return "redirect:/main.html";
        }else{
            //用户名或者密码错误的时候给出相应的错误提示,并且返回主页面
            model.addAttribute("msg","用户名或者密码输入错误");
            return "index";
        }
    }
}
