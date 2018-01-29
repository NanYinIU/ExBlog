package com.nanyin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by NanYin on 18-1-26.
 * 包名： com.nanyin.controller
 * 类名： MainController
 * 类描述：
 */
@Controller
public class MainController {

    @RequestMapping("/main/index")
    public String mainIndex(){
        return "/main/index";
    }

    @RequestMapping("/main/content")
    public String maincontent(){
        return "/main/content";
    }

    @RequestMapping("/main/page")
    public String mainPage(){
        return "/main/page";
    }

    @RequestMapping("/main/pageList")
    public String pageList(){
        return "/main/pageList";
    }

    @RequestMapping("/main/columnList")
    public String columnList(){
        return "/main/columnList";
    }

    @RequestMapping(value = "/main/columnItem/{columnId}")
    public ModelAndView columnItem(@PathVariable("columnId") String columnId){
        ModelAndView modelAndView = new ModelAndView("/main/columnItem");
        modelAndView.addObject("columnId",columnId);
        return modelAndView;
    }

    @RequestMapping("/main/personal/{userName}")
    public ModelAndView personal(@PathVariable("userName") String userName){
        ModelAndView modelAndView = new ModelAndView("/main/personal");
        modelAndView.addObject("userName",userName);
        return modelAndView;
    }




}
