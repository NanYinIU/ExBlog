package com.nanyin.controller;

import com.nanyin.service.FavesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by NanYin on 2017-11-13 下午2:54.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class FavesController {
    @Autowired
    FavesService favesService;

    @RequestMapping("/faves/addItem")
    public@ResponseBody int addFavesItem(@RequestParam("userName") String userName,@RequestParam("pageId") String pageId){
        return favesService.insertFavesItem(userName, pageId);
    }
    @RequestMapping("/faves/checkIsFaves")
    public @ResponseBody Map<String,Boolean> checkIsFaves(@RequestParam("userName") String userName,@RequestParam("pageId") String pageId){
        return favesService.checkIsFaves(userName, pageId);
    }
    @RequestMapping("/faves/favesTable")
    public @ResponseBody ModelAndView favesTable(@RequestParam(value = "url",required = false) String url){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("InnerLayui/myFaves");
        modelAndView.addObject("url",url);
        return modelAndView;
    }


    @RequestMapping("/faves/findFaves/{pageNum}")
    public @ResponseBody Map<String,Object> findFaves(HttpServletRequest request,
                                        @RequestParam(value = "search",required = false) String search,@PathVariable("pageNum") int pageNum){
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("user");
        return favesService.findFaves(userName,search,pageNum);
    }
    @RequestMapping("/faves/subItem")
    public @ResponseBody int subItem(@RequestParam("userName") String userName,@RequestParam("pageId")int pageId){
        return favesService.deleteFaverItem(userName, pageId);
    }


}
