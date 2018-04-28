package com.bootdemo.controller;

import com.bootdemo.common.CookieUtils;
import com.bootdemo.dao.UserDao;
import com.bootdemo.model.User;
import com.bootdemo.service.imp.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserDao dao;
    @Autowired
    private RedisService service;

    @RequestMapping(value = "/login")
    public String Loginview() {
        return "login";
    }

    //登录验证
    @RequestMapping(value = "/loginVerify")
    public String loginverifys(HttpServletResponse response,
                               Model model, String username, String password, String returnurl,
                               @RequestParam(required = false) String auto) throws Exception {
        if ((username == null || username.isEmpty()) || (password == null || password.isEmpty())) {
            model.addAttribute("msg", "用户名密码不能为空！");
        } else {
            User user = dao.findByUsername(username);
            if (user == null) {
                model.addAttribute("msg", "用户名或密码错误");
            } else {
                if (auto != null) {
                    CookieUtils.Login(response, 3000, user.getUserName(), user.getId() + "", new Date());
                } else {
                    CookieUtils.Login(response, 300, user.getUserName(), user.getId() + "", new Date());
                }

                if (returnurl == null || returnurl.isEmpty())
                    response.sendRedirect("index");
                else
                    response.sendRedirect(returnurl);

            }
        }
        return "login";
    }

    @RequestMapping("/index")
    public String indexs(Model model) {
        User user;

        if (service.get("user") == null) {
            user = dao.findByUsername("123");
            service.set("user",user);
        } else {
            user = (User)service.get("user");
        }
        model.addAttribute("user", user);
        return "index";
    }
}
