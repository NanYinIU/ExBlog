package com.nanyin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by NanYin on 2017-10-04 下午1:21.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class OtherController {
    @RequestMapping("/c")
    public String c(){
        return "page";
    }
    @RequestMapping("/ci")
    public String ci(){
        return  "markDown";
    }
}
