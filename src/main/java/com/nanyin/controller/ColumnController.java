package com.nanyin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.nanyin.model.Column;
import com.nanyin.service.ColumnService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("/findColumByCount")
    public @ResponseBody
    Map<String, List<Column>> findColumByPaperCount(){
        List<Column> list = columnService.findColumByPaperCount();
        Map<String, List<Column>> map = Maps.newHashMap();
        map.put("colum",list);
        return map;
    }

    @RequestMapping("/findAllColumn2")
    public @ResponseBody
    Map<String, List<Column>> findAllColumn2(){
        List<Column> list = columnService.findAllColumn();
        Map<String, List<Column>> map = Maps.newHashMap();
        map.put("list",list);
        return map;
    }

    @RequestMapping("/findAllColumn/{pageNum}")
    public @ResponseBody
    ModelAndView findAllColumn( @RequestParam(value = "search", required = false) String search,
                                @PathVariable("pageNum") int pageNum){
        Map<String,Object> map = columnService.findAllColumnSearch(search, pageNum);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ColumnIndex");

        modelAndView.addAllObjects(map);
        return modelAndView;
    }

    @RequestMapping("/PersonalColumn/{name}")
    public @ResponseBody Map<String, List<Map<String,Object>>> personalColumn(@PathVariable("name") String name){
//        column 的 title 集合
        Map<String, List<Map<String,Object>>> map = Maps.newHashMap();
        Set<String> set = columnService.findCoumnByUser(name);
        List<Map<String,Object>> list = Lists.newLinkedList();
        loopSetOfCoumn(list,set,name);
        map.put("columnList",list);
        return map;
    }

    private void loopSetOfCoumn(List<Map<String,Object>> list,Set<String> set,String name){
        for (String title: set
             ) {
            Map<String,Object> map = Maps.newHashMap();
            int count = columnService.findCountByTitle(title,name);
            map.put("count",count);
            map.put("title",title);
            list.add(map);
        }
    }

    @RequestMapping("/updateTheme")
    public String updateTheme(){
        return "InnerLayui/colMes";
    }

    @RequestMapping("/updateColumnName/{id}")
    public @ResponseBody int updateColumnName(@PathVariable(name = "id") int paperId,@RequestParam("theme") String title){
        return columnService.updateColumnByPaperId(paperId,title);
    }

    @RequestMapping("/addColumn")
    public String addColumn(String url){
        return "InnerLayui/addColumn";
    }

    @RequestMapping("/columnPage")
    public ModelAndView columnPage(@RequestParam(value = "url",required = false) String url){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("InnerLayui/columnPage");
        modelAndView.addObject("url",url);
        return modelAndView;
    }
    @RequestMapping("/allColumn/{pageNum}")
    public @ResponseBody Map<String,Object> allColumn(@PathVariable("pageNum") String pageNum){
        Map<String,Object> map = columnService.allColumn(pageNum);
        map.put("pageNum",pageNum);
        return map;
    }

    @RequestMapping("/insertInlet")
    public @ResponseBody int insertInlet(@RequestParam("userName") String name,@RequestParam("btn") String image){
    return columnService.insertInlet(name,image);
    }

    @RequestMapping("/deleteColumn/{id}")
    public @ResponseBody int deleteColumn(@PathVariable("id") int id){
        return columnService.deleteColumnById(id);
    }

    @RequestMapping("/editColumn/{id}")
    public ModelAndView editColumn(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("InnerLayui/editColumn");
        Column column = columnService.selectColumnById(id);
        modelAndView.addObject("column",column);
        return modelAndView;
    }
    @RequestMapping("/updateInlet/{id}")
    public @ResponseBody int updateInlet(@RequestParam("sTitle") String name,@RequestParam("imgMes")String image,@PathVariable("id") String id){
        return columnService.updateInlet(name, image, id);
    }

}
