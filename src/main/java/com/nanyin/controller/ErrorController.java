package com.nanyin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
    // error全局控制
    @RequestMapping("/error/404")
    public String get404Page(){
                return "errorPage/404";
    }
    @RequestMapping("/error/400")
    public String get400Page(){
        return "errorPage/400";
    }
    @RequestMapping("/error/500")
    public String get500Page(){
        return "errorPage/500";
    }
    @RequestMapping("/error/unAuthorized")
    public String unAuthorizedPage(){
        return "errorPage/unAuthorized";
    }
}
