package com.nanyin.controller;

import com.nanyin.service.PaperService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NanYin on 2017-10-04 下午1:21.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class OtherController {
    @Autowired
    PaperService paperService;

    Logger logger = Logger.getLogger(this.getClass());
    @RequestMapping("/c")
    public String c(){
        return "page";
    }
    @RequestMapping("/ci")
    public String ci(@RequestParam(value = "content",required = false) String content){
        return  "markDown";
    }
    @RequestMapping("/cit")
    public String cit(){
        return "Manage";
    }
    @RequestMapping("/cite")
    public String cite(){
        return "InnerLayui/main";
    }

    @RequestMapping("/cte")
    public ModelAndView cte(String url){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("InnerLayui/pageMes");
        Map<String,Object> map = new HashMap<>();
        map.put("url",url);
        modelAndView.addAllObjects(map);

        return modelAndView;
    }
    @RequestMapping("/M1")
    public String M1(){
        return "InnerLayui/submit";
    }

    @RequestMapping("/model")
    public ModelAndView model(@RequestParam(value = "content",required = false) String content, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        logger.info("content ="+content);
        Map<String,Object> map = new HashMap<>();
        logger.info("map="+map);
        modelAndView.setViewName("InnerLayui/submit");
        modelAndView.addAllObjects(map);
        modelAndView.addObject("content",content);
        logger.info(modelAndView.getModelMap());
        return modelAndView;
    }
    @RequestMapping("/newMD/{id}")
    public ModelAndView newMD(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paper",paperService.findPaperById(id));
        modelAndView.setViewName("InnerLayui/UpdatemarkDown");
        return modelAndView;
    }
}
