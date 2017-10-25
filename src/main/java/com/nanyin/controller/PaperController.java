package com.nanyin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nanyin.config.AllAttriOfPaper;
import com.nanyin.model.Paper;
import com.nanyin.service.CommentsService;
import com.nanyin.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by NanYin on 2017-10-02 下午2:10.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class PaperController {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    PaperService paperService;
    @Autowired
    CommentsService commentsService;
    @RequestMapping("/returnHome")
    public ModelAndView returnHome(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
//    限制在5 篇
    @RequestMapping("/PapersByTime")
    public @ResponseBody
    Map<String,Object> PapersByTime(){
        return  paperService.findAllPapersByTime();
    }

    @RequestMapping("/markAdd")
    public @ResponseBody int markAdd(String mark,String title){
        System.out.println(mark);
        int marked = Integer.parseInt(mark);
        return paperService.updateMarkByTitle(marked+1, title);
    }

//    同样限制在5 篇
    @RequestMapping("/PapersByMark")
    public @ResponseBody
    Map<String,Object> PapersByMark(){
        return paperService.findAllPapersByMark();
    }
    @RequestMapping("/te")
    public String te(){
        return "test";
    }

    @RequestMapping("/personalPage/{name}/{pageNum}")
    public @ResponseBody
    ModelAndView  personalPage(
            @PathVariable(value = "pageNum") int pageNum,
            @PathVariable(value = "name") String name,
            @RequestParam(value = "search", required = false) String search){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("PersonalIndex");
        PageHelper.startPage(pageNum,10);

        List papers = paperService.findAllPaperByUser(name,search);

        PageInfo pageInfo = new PageInfo(papers);

        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }
    @RequestMapping("/HomePage/{pageNum}")
    public @ResponseBody
    ModelAndView  HomePage(
            @PathVariable(value = "pageNum") int pageNum,
            @RequestParam(value = "search", required = false) String search){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("SearchIndex");
        PageHelper.startPage(pageNum,8);

        List<Paper> papers = paperService.findAllPapers(search);

        PageInfo pageInfo = new PageInfo(papers);

        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }


    @RequestMapping("/ColumnPage2/{column}/{pageNum}")
    public @ResponseBody
    Map<String,Object>  ColumnPage2(
            @PathVariable(value = "pageNum") int pageNum,
            @PathVariable(value = "column") String column
    ) {
        Map<String,Object> map = new HashMap<>();
        map.put("list",paperService.findPaperInColumn(column));
        ModelAndView modelAndView = new ModelAndView();
        PageHelper.startPage(pageNum,3);
        PageInfo pageInfo = new PageInfo(paperService.findPaperInColumn(column));
        modelAndView.setViewName("Column");
        map.put("pageInfo",pageInfo);
        map.put("column",column);
        return map;
    }


    @RequestMapping("/page/{id}")
    public @ResponseBody
    ModelAndView page(@PathVariable("id") int id){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("page");
                Map<String,Object> map = new HashMap<>();

                map.put("page",paperService.findAllAttriOfPapaer(id));

//                modelAndView.addObject("page",paperService);
                modelAndView.addAllObjects(map);
                return modelAndView;
    }

    @RequestMapping("/ColumnPage/{column}/{pageNum}")
    public @ResponseBody
    ModelAndView  ColumnPage(
            @PathVariable(value = "pageNum") int pageNum,
            @PathVariable(value = "column") String column
            ) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Column");
        PageHelper.startPage(pageNum,10);
        PageInfo pageInfo = new PageInfo(paperService.findPaperInColumn(column));
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("column",column);
        return modelAndView;
    }

    @RequestMapping("/PersonalManager/{name}/{pageNum}")
    public @ResponseBody
    ModelAndView PersonalManager(
            @PathVariable("name") String name,
            @PathVariable("pageNum")int pageNum,
            @RequestParam(value = "search", required = false) String search)
    {
        Map<String,Object> map = new HashMap<>();
        List list =  paperService.findPaperByUserName(name,search);
        PageHelper.startPage(pageNum,10);
        PageInfo pageInfo = new PageInfo(list);
        map.put("pageInfo",pageInfo);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("PersonalManager");
        modelAndView.addAllObjects(map);
        return modelAndView;
    }

    @RequestMapping("/paper/delect/{id}")
    public @ResponseBody
    int deletePaperByPaperId(@PathVariable("id") int id){
        int A = paperService.deletePaperByPaperId(id);
        logger.info("返回值："+A);
        return A;
    }


}
