package com.nanyin.controller;

import com.nanyin.mapper.ColumMapper;
import com.nanyin.model.Column;
import com.nanyin.service.ColumnService;
import javafx.scene.effect.SepiaTone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by NanYin on 2017-10-02 下午11:09.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
@RequestMapping("/column")
public class ColumnController {
    @Autowired
    ColumnService columnService;

    @RequestMapping("/findColumByCount")
    public @ResponseBody
    Map<String,Object> findColumByPaperCount(){
        List<Column> list = columnService.findColumByPaperCount();
        Map<String,Object> map = new HashMap<>();
        map.put("colum",list);
        return map;
    }

    @RequestMapping("/findAllColumn2")
    public @ResponseBody
    Map<String,Object> findAllColumn2(){
        List<Column> list = columnService.findAllColumn();
        Map<String ,Object> map = new HashMap<>();
        map.put("list",list);
        return map;
    }

    @RequestMapping("/findAllColumn")
    public @ResponseBody
    ModelAndView findAllColumn(){
        List<Column> list = columnService.findAllColumn();
        Map<String ,Object> map = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ColumnIndex");
        map.put("list",list);
        modelAndView.addAllObjects(map);
        return modelAndView;
    }

    @RequestMapping("/PersonalColumn/{name}")
    public @ResponseBody Map<String,Object> PersonalColumn(@PathVariable("name") String name){
//        column 的 title 集合
        Map<String,Object> map = new HashMap<>();
        List<Integer> countList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
//        List<String> list = (List<String>) columnService.findCoumnByUser(name);
        Set<String> set = columnService.findCoumnByUser(name);
        List List = new ArrayList<>();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Map<String,Object> map1 = new HashMap<>();
            String title = (String) iterator.next();
            int count = columnService.findCountByTitle(title,name);
            map1.put("count",count);
            map1.put("title",title);
            List.add(map1);
        }
//        map.put("count",countList);
//        map.put("title",titleList);
        Map<String,Object> map1 = new HashMap<>();
        map.put("columnList",List);
        return map;
    }

}
