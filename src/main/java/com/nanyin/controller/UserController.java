package com.nanyin.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.nanyin.config.common.ModifiPass;
import com.nanyin.config.logConfig.Log;
import com.nanyin.config.shiroConfig.ShiroUtil;
import com.nanyin.model.To.UserAndRoles;
import com.nanyin.model.Users;
import com.nanyin.service.UserDetailService;
import com.nanyin.service.UserService;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.List;
import java.util.Map;


/**
 * Created by NanYin on 2017-10-01 下午11:25.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class UserController {

    private static final int SUCCESS_FOR_NOW = 1 ;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    ModifiPass modifiPass;
    @Autowired
    UserService userService;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    PaperController paperController;
//     主页名称
//    @RequestMapping("/index")
//    public String index(){
//        return "redirect:/user/login";
//    }

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
        try{
            request.getSession().removeAttribute("user");
            request.getSession().invalidate();
        }catch (Exception e){

        }
        return "redirect:/user/login";
    }

    @Log(operationName = "用户登录" )
    @RequestMapping("/user/gotoIndex")
    public String gotoIndex(@RequestParam("username") String username
            ,@RequestParam("password") String password
            ,@RequestParam(value = "rememberMe",required = false)String rememberMe
            ,HttpServletRequest request
            ,@RequestParam(value = "url",required = false) String url){

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        //设置rememberMe
        if("on".equals(rememberMe)){
            usernamePasswordToken.setRememberMe(true);
            logger.info("开启 rememberMe 功能");
        }else{
            usernamePasswordToken.setRememberMe(false);
            logger.info("未开启 rememberMe 功能");
        }
        try {
            subject.login(usernamePasswordToken);
            session.setAttribute("user",username);
            return "redirect:/HomePage/1";
        }catch (Exception r){
            r.printStackTrace();
            return "login";
        }

    }



    @RequestMapping("/userMes/{name}")
    public @ResponseBody Users userMes(
            @PathVariable("name") String name){
        return userService.findUsersByName(name);
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

        if(modifiPass.checkOldPassWordIsRight(userName,oldPassword) != SUCCESS_FOR_NOW){
                return modifiPass.checkOldPassWordIsRight(userName,oldPassword);
        }
        else if(modifiPass.checkNewPassIsNotNull(newPassword,newPassword1) != SUCCESS_FOR_NOW ){
            return modifiPass.checkNewPassIsNotNull(newPassword,newPassword1);

        }else if(modifiPass.checkIsEquals(newPassword, newPassword1) != SUCCESS_FOR_NOW){
            return modifiPass.checkIsEquals(newPassword, newPassword1);
        }
        else {
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
    @RequestMapping("/user/userManage/{pageNum}")
    public @ResponseBody Map<String,Object> userManage(@PathVariable("pageNum") int pageNum){
        Map<String,Object> map = Maps.newHashMap();

        List<UserAndRoles>  list = userService.userAndRole(pageNum);
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");
        map.put("count",userService.findAllUsers().size());
        return map;
    }

    @RequestMapping("/user/returnUserManage")
    public ModelAndView returnUserManage(@RequestParam(value = "url",required = false) String url){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("InnerLayui/userManage");
        modelAndView.addObject("",url);
        return modelAndView;
    }

    @RequestMapping("/user/admin/CheckUserMesPage")
    public @ResponseBody ModelAndView returnCheckUserMesPage(@RequestParam(value = "id",required = false) int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",userService.checkUserMes(id));
        modelAndView.setViewName("InnerLayui/Admin/checkUserMes");
        return modelAndView;
    }
}
