package com.nanyin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.nanyin.config.AllAttriOfPaper;
import com.nanyin.config.InsertPojo;
import com.nanyin.config.PaperAndComments;
import com.nanyin.config.common.Paging;
import com.nanyin.model.Paper;
import com.nanyin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.concurrent.ExecutionException;
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

    /**
     * 返回文章和评论的信息 按时间排序
     * @return
     */
    @RequestMapping("/paper/PapersByTime")
    public @ResponseBody
    Map<String,List<PaperAndComments>>PapersByTime(){
        return paperService.findAllPapersByTime();
    }


    /**
     * 返回文章和评论信息 按热度排序
     * @return
     */
    @RequestMapping("/paper/PapersByMark")
    public @ResponseBody
    Map<String,List<PaperAndComments>> papersByMark(){
        return paperService.findAllPapersByMark();
    }

    /**
     * 后台管理页面
     * @param url
     * @return
     */
    @RequestMapping("/manage/pageMes")
    public ModelAndView pageManage(String url){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("InnerLayui/pageMes");
        Map<String,Object> map = Maps.newHashMap();
        map.put("url",url);
        modelAndView.addAllObjects(map);
        return modelAndView;
    }

//    /**
//     * 前台显示所有页面
//     * @param pageNum
//     * @param search
//     * @return
//     */
//    @RequestMapping("/main/pageList/{pageNum}")
//    public @ResponseBody
//    ModelAndView  HomePage(
//            @PathVariable(value = "pageNum") int pageNum,
//            @RequestParam(value = "search", required = false) String search){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/main/pageList");
//        PageHelper.startPage(pageNum, Paging.LIMIT.getValue()-2);
//        PageInfo pageInfo = paperService.findAllPapers(search,pageNum);
//        modelAndView.addObject("pageInfo",pageInfo);
//        return modelAndView;
//    }

    /**
     *
     * @param pageNum
     * @param search
     * @return
     */
    @RequestMapping("/paper/test/{pageNum}")
    public @ResponseBody
    PageInfo pageTest(
            @PathVariable(value = "pageNum") int pageNum,
            @RequestParam(value = "search", required = false) String search){
        Map<String,Object> map = Maps.newHashMap();
        PageHelper.startPage(pageNum, Paging.LIMIT.getValue()-2);
        PageInfo pageInfo = paperService.findAllPapers(search,pageNum);
        map.put("pageInfo",pageInfo);
        return pageInfo;
    }




    @RequestMapping("/paper/{id}")
    public @ResponseBody
    ModelAndView page(@PathVariable("id") int id){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/main/page");
                Map<String,Object> map = Maps.newHashMap();
                //根据id进行mark的增加
                Paper paper = paperService.findPaperById(id);
                try{
                    map.put("page",paperService.findAllAttriOfPapaer(id));
                }catch (Exception e){

                }
                modelAndView.addAllObjects(map);
                return modelAndView;
    }


    /**
     *
     * @param id 文章的id
     * @return
     */
    @RequestMapping("/paper/delect/{id}")
    public @ResponseBody
    int deletePaperByPaperId(@PathVariable("id") int id){
        int hasTag = tagService.findHasTagByPaperId(id);
        checkPaperHasTag(hasTag,id);
        return paperService.deletePaperByPaperId(id);
    }

    /**
     * 检查文章中是否有tag 有就删除
     * @param hasTag
     * @param id
     */
    private void checkPaperHasTag(int hasTag,int id){
        if(hasTag!=0){
            logger.info("文章里面有Tag 准备删除tag");
            try{
                tagService.delectTag(id);
            }catch (Exception e){
                logger.info("删除过程出错");
            }
            logger.info("删除文章的tag 成功");
        }
        else {
            logger.info("这个paper没有tag");
        }
    }

    /**
     * 根据
     * @param name
     * @param pageNum
     * @return
     */
    @RequestMapping("/paper/findPaperByName/{name}/{pageNum}")
    public @ResponseBody Map<String,Object> findPaperByName(
            @PathVariable("name") String name, @PathVariable("pageNum") String pageNum){
        Map<String ,Object> map = paperService.findPaperByUser(name,pageNum);
        map.put("pageNum",pageNum);
        return map;
    }

    /**
     * 更新文章内容
     * @param id
     * @param content
     * @return
     */
    @RequestMapping("/paper/updateContent/{id}")
    public @ResponseBody int updateContent(@PathVariable("id") String id,String content){
        return paperService.updatePaperContentById(content,id);
    }

    /**
     * 新建文章
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

        List<String> tags = Lists.newLinkedList();
        List<String> tag = insertPojo.getTag();

        addTags(tags,tag,newTag);

        String theme = insertPojo.getTheme();
        // 插入里一条paper的记录 自动生成一个id值
        int result = paperService.insertPaper(title,content,segment,user);
        // 根据信息查询paper的id
        int paperId = paperService.findPaperId(title,segment,user);
        // 插入tag和主题
        if(tags.size()!=0){
            tagService.insertTagByUserId(tags,paperId);
        }
        if(theme != null && !"".equals(theme)){
            int columnId = columnService.findColumnId(theme);
            int result1 = columnService.insertColumnPaper(columnId,paperId);
        }

        return result ;
    }

    /**
     * 根据条件添加tags
     * @param tags
     * @param tag
     * @param newTag
     */
        private void addTags(List<String> tags,List<String> tag ,String newTag){
            if(tag!=null){
                tags.addAll(tag);
            }
            tags.add(newTag);
        }

    /**
     * 得到前一篇和下一片的内容
     * @param paperId
     * @return
     */
    @RequestMapping("/paper/PreAndNextPage/{paperId}")
    public @ResponseBody Map<String ,Object> findPreAndNextPage(@PathVariable("paperId") int paperId){
         Map<String,Object> map = paperService.findPreAndNextPage(paperId);
         return map;
    }

    /**
     * 后台文章管理的数据
     * @return 所有文章的数据
     */
    @RequestMapping(value = {"/paper/adminPapersData/{pageNum}","/paper/adminPapersData/{pageNum}/{timePick}"})
    public @ResponseBody Map<String,Object> adminPapers(
            @PathVariable(value = "pageNum") int pageNum,
            @PathVariable(value = "timePick",required = false) String timePick,
            @RequestParam(value = "title",required = false) String search){
        return paperService.findPapers(pageNum,search,timePick);
    }

    /**
     * 返回admin文章审核页面
     *
     */
    @RequestMapping("/paper/adminPapers")
    public ModelAndView returnAdminPapers(@RequestParam(value = "url",required = false) String url){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",url);
        modelAndView.setViewName("InnerLayui/Admin/pageReview");
        return modelAndView;
    }

    /**
     * 返回修改文章状态页面
     * @param id
     * @return
     */
    @RequestMapping("/paper/FixPaperStatues/{id}")
    public ModelAndView returnFixPaperStatues(@PathVariable(value = "id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paperId",id);
        modelAndView.setViewName("InnerLayui/Admin/updatePageStatus");
        return modelAndView;
    }

    /**
     * 更改文章状态执行方法
     * @param review 输入状态 string类型
     * @param id 文章id
     * @return
     */
    @RequestMapping("/paper/updatePaperStatus/{id}")
    public @ResponseBody int updatePaperStatus(
            @RequestParam(value = "review") String review,
            @PathVariable(value = "id") int id){
        return paperService.updatePaperStatus(id, review);
    }

    /**
     * 获得一月内热门文章的json
     * @return
     */
    @RequestMapping("/paper/hotPapersInMonth")
    public @ResponseBody PageInfo<Paper> hotPapersInMonth(){
        PageInfo pageInfo = paperService.findPaperInMonth();

        List<Paper> list = pageInfo.getList();
        List<Paper> list2 = Lists.newLinkedList();
        for (Paper p: list
             ) {
            list2.add(0,p);
        }
        pageInfo.setList(list2);
        return pageInfo;
    }

    /**
     * 个人页面所有文章
     * @param pageNum
     * @param search
     * @param name
     * @return
     */
    @RequestMapping("/paper/personalPaperList/{name}/{pageNum}")
    public @ResponseBody PageInfo<Paper> personalPaperList(@PathVariable("pageNum") int pageNum
            ,@RequestParam(value = "search",required = false) String search
            ,@PathVariable("name")String name){
        return paperService.findPaperByUserName(name,pageNum,search);
    }


}
