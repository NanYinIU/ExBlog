package com.nanyin.controller;

import com.nanyin.model.Users;
import com.nanyin.service.UserService;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by NanYin on 2017-10-01 下午11:25.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class UserController {
    Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    UserService userService;

    @Autowired
    PaperController paperController;

    @RequestMapping("/user/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/user/signUp")
    public String signUp(){
        return null;
    }

    @RequestMapping("/user/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
//        request.getSession().invalidate();
        return "login";
    }
    @RequestMapping("/user/gotoIndex")
    public String gotoIndex( String username, String password,HttpServletRequest request){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);

        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();

        try {
            subject.login(usernamePasswordToken);
            session.setAttribute("user",username);
            return "index";
        }catch (Exception r){
            return "login";

        }

    }



    @RequestMapping("/userMes/{name}")
    public @ResponseBody Users userMes(
            @PathVariable("name") String name){
        Users users = userService.findUsersByName(name);

        return users;
    }
}
