package com.nanyin.controller;

import com.nanyin.config.common.ModifiPass;
import com.nanyin.config.logConfig.Log;
import com.nanyin.model.Users;
import com.nanyin.service.UserDetailService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by NanYin on 2017-10-01 下午11:25.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class UserController {

    private static final int SUCCESS_FOR_NOW = 1 ;
    Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    ModifiPass modifiPass;
    @Autowired
    UserService userService;
    @Autowired
    UserDetailService userDetailService;
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

    @Log(operationName = "用户登录" )
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
    @RequestMapping("/user/detailPage2/{name}")
    public @ResponseBody Map<String,Object> getDetail2(@PathVariable("name") String name){
        Map<String,Object> map = new HashMap<>();
        map.put("user",userService.getUserParam(name));
        return map;
    }
    @RequestMapping("/user/detailPage")
    public @ResponseBody
    ModelAndView getDetail(HttpServletRequest request){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("user");
        logger.info("在session中获得username:"+name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("InnerLayui/userDetail");
        modelAndView.addObject("user",userService.getUserParam(name));
        return modelAndView;
    }
    @RequestMapping("/user/updateDetail")
    public @ResponseBody int updateUserDetail(@RequestParam("imgMes") String imgMes,
                                              @RequestParam("userName") String userName,
                                              @RequestParam("realName") String realName,
                                              @RequestParam("position") String position,
                                              @RequestParam("date") String data,
                                              @RequestParam("email") String email,
                                              @RequestParam("address") String address,
                                              @RequestParam("sketch") String sketch) throws ParseException {
        return userService.updateUserMes(imgMes, userName, realName, position, data, email, address, sketch);
    }

    @RequestMapping("/user/headPic")
    public String headPic(){
        return "InnerLayui/pic";
    }

    @RequestMapping("/user/updateUserPass")
    public @ResponseBody int updateUserPass(@RequestParam("oldPassword") String oldPassword,
                              @RequestParam("newPassword")String newPassword,
                              @RequestParam("newPassword1")String newPassword1,HttpServletRequest request){
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("user");
        logger.info("newPass:"+newPassword);
        if(modifiPass.checkOldPassWordIsRight(userName,oldPassword) != SUCCESS_FOR_NOW){
//  4
                return modifiPass.checkOldPassWordIsRight(userName,oldPassword);
        }
        else if(modifiPass.checkNewPassIsNotNull(newPassword,newPassword1) != SUCCESS_FOR_NOW ){
            return modifiPass.checkNewPassIsNotNull(newPassword,newPassword1);

        }else if(modifiPass.checkIsEquals(newPassword, newPassword1) != SUCCESS_FOR_NOW){
            return modifiPass.checkIsEquals(newPassword, newPassword1);
        }
        else {
            // 1
            return userService.updateUserPass(userName,newPassword,oldPassword);
        }
    }

    @RequestMapping("/user/userAndAuthor/{pageNum}")
    public @ResponseBody Map<String,Object> userAndAuthor(@PathVariable("pageNum") int pageNum,
                                                          @RequestParam(value = "search",required = false) String search){
        Map<String,Object> map = userService.userAndAuthor(search, pageNum);
        map.put("pageNum",pageNum);
        return map;
    }

    @RequestMapping("/user/returnAuthor")
    public @ResponseBody ModelAndView returnAuthor(@RequestParam(value = "url",required = false) String url){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("InnerLayui/authorPage");
        modelAndView.addObject("url",url);
        return modelAndView;
    }
}
