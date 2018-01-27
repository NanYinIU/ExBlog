package com.nanyin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
