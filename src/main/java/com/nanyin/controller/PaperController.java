package com.nanyin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nanyin.config.AllAttriOfPaper;
import com.nanyin.config.InsertPojo;
import com.nanyin.config.PaperAndComments;
import com.nanyin.model.Paper;
import com.nanyin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
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
    @Autowired
    UserService userService;
    @Autowired
    TagService tagService;
    @Autowired
    ColumnService columnService;
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
//        logger.info("格式"+ paperService.findAllPapersByTime().get("paper").toString());
        Map<String,Object> map = paperService.findAllPapersByTime();
        return map;
    }

    @RequestMapping("/markAdd")
    public @ResponseBody int markAdd(String mark,String id){
        System.out.println(mark);
        int marked = Integer.parseInt(mark);
        return paperService.updateMarkByTitle(marked+1, id);
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

        List<PaperAndComments> papers = paperService.findAllPaperByUser(name,search);

        PageInfo<PaperAndComments> pageInfo = new PageInfo<>(papers);
        int total = paperService.getTotal(name,search);
        pageInfo.setTotal(total);
        pageInfo.setFirstPage(0);
        pageInfo.setLastPage((total/8)+1);
        pageInfo.setPrePage(pageNum-1 >= 0 ? pageNum-1:0);
        pageInfo.setNextPage(pageNum+1 > ((total/8)+1) ? ((total/8)+1):pageNum+1);
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

        List<PaperAndComments> papers = paperService.findAllPapers(search);

        PageInfo pageInfo = new PageInfo(papers);

        int total = paperService.getAllTotal(search);
        pageInfo.setTotal(total);
        pageInfo.setFirstPage(0);
        pageInfo.setLastPage((total/8)+1);
        pageInfo.setPrePage(pageNum-1 >= 0 ? pageNum-1:0);
        pageInfo.setNextPage(pageNum+1 > ((total/8)+1) ? ((total/8)+1):pageNum+1);
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
        //这里手动把对应的tag删除
        //先查对应的paperid里有没有tag
        int hasTag = tagService.findHasTagByPaperId(id);
        if(hasTag!=0){
            tagService.delectTag(id);
        }
        else {
            logger.info("这个paper没有tag");
        }
        logger.info("返回值："+A);
        return A;
    }

    @RequestMapping("/paper/findPaperByName/{name}/{pageNum}")
    public @ResponseBody Map<String,Object> findPaperByName(
            @PathVariable("name") String name, @PathVariable("pageNum") String pageNum){
        Map<String ,Object> map = paperService.findPaperByUser(name,pageNum);
        map.put("pageNum",pageNum);
        return map;
    }

    @RequestMapping("/paper/updateContent/{id}")
    public @ResponseBody int updateContent(@PathVariable("id") String id,String content){
        return paperService.updatePaperContentById(content,id);
    }

    /**
     * @param insertPojo 插入的数据类型
     * @return 返回值 根据返回值判断是否返回成功
     */
    @RequestMapping(value = "/paper/insertAll/{user}",method = RequestMethod.POST)
    public @ResponseBody int insertAll(
              InsertPojo insertPojo
            , @PathVariable("user") String user){
        String title = insertPojo.getTitle();
        String content = insertPojo.getMain1();
        String newTag = insertPojo.getNewTag();
        String segment = insertPojo.getSegment();
        List<String> tags = new LinkedList<>();
        List<String> tag = insertPojo.getTag();

        if(tag!=null){
            tags.addAll(tag);
        }
        tags.add(newTag);
        String theme = insertPojo.getTheme();
        logger.info("theme:"+theme+"is null?"+theme.equals(null));
        logger.info("表单内容"+insertPojo);
        logger.info(insertPojo.getSegment());
        // 插入里一条paper的记录 自动生成一个id值
        int result = paperService.insertPaper(title,content,segment,user);
        // 根据信息查询paper的id
        int paperid = paperService.findPaperId(title,segment,user);
        // 插入tag和主题
        if(tags.size()!=0){
            tagService.insertTagByUserId(tags,paperid);
        }
        if(theme != null && !"".equals(theme)){
            int columnId = columnService.findColumnId(theme);
            int result1 = columnService.insertColumnPaper(columnId,paperid);
        }

        return result ;
    }

}
