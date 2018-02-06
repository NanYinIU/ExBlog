package com.nanyin.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.nanyin.model.Paper;
import com.nanyin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by NanYin on 2017-10-08 下午2:04.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class TagController {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    TagService tagService;

    @RequestMapping(value = {"/main/tag/tags","/tag/tags"})
    public @ResponseBody Map<String,Set<String>> AllTags(){
        Map<String,Set<String>> map = Maps.newHashMap();
        map.put("tags",tagService.findAllTagName());
        return map;
    }

    @RequestMapping(value = "/tag/PersonalTag/{name}",method = RequestMethod.POST)
    public @ResponseBody
    Multimap<String,Map<String,Object>> PersonalTag(@PathVariable("name") String name){
        Set<String> tagname = tagService.findTagNameByUser(name);
        Multimap<String,Map<String,Object>> map = HashMultimap.create();
        List<Map<String,Object>> list = Lists.newArrayList();
        loopTagNameSet(name,tagname,list);
        map.putAll("tagList",list);
        return map;
    }

    private void loopTagNameSet(String name,Set<String> tagname,List<Map<String,Object>> list){
        for (String s:tagname
             ) {
            Map<String,Object> map = Maps.newHashMap();
            int count = tagService.findTagCountByTagName(name,s);
            map.put("tag_name", s);
            map.put("count",count);
            list.add(map);
        }
    }


    /**
     * 查找对应用户的所有tag
     * @param name
     * @return
     */
    @RequestMapping(value = "/tag/findAllTagsByName/{name}")
    public @ResponseBody Map<String,Object> findAllTagsByName(@PathVariable("name") String name){
            Set<String> list = tagService.findTagNameByUser(name);
            Map<String ,Object> map = Maps.newHashMap();
            boolean flag = true ;

            if(list.size() == 0){
                flag = false;
            }

            map.put("flag",flag);
            map.put("list",list);
            return map;
    }

    /**
     * 返回后台的tag管理页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/tag/tagPage/{id}")
    public String tagPage(@PathVariable("id") int id){
        return "InnerLayui/tagMes";
    }

    /**
     * 查询对应文章的所有tag
     * @param id
     * @return
     */
    @RequestMapping(value = "/tag/tagsByPaperId/{id}")
    public @ResponseBody Map<String, Object> tagsByPaperId(@PathVariable("id") int id){
        return tagService.findTagNamesByPaperId(id);
    }

    /**
     * 返回修改tag的页面
     * @return
     */
    @RequestMapping(value = "/tag/updateTag")
    public String updateTag(){
        return "InnerLayui/updateTag";
    }

    /**
     * 删除tag
     * @param id
     * @param tags
     * @return
     */
    @RequestMapping(value = "/tag/delectTags/{id}/{tags}",method = RequestMethod.POST)
    public @ResponseBody int delectTags(@PathVariable("id") int id,@PathVariable("tags") String tags){
        return tagService.delectTagByPaperIdAndTagName(id,tags);
    }

    /**
     * 后台修改tag
     * @param id
     * @param oldTag
     * @param newTag
     * @return
     */
    @RequestMapping(value = "/tag/updateTags/{id}/{oldTag}",method = RequestMethod.POST)
    public @ResponseBody int updateTags(@PathVariable("id") int id,@PathVariable("oldTag") String oldTag,@RequestParam("title") String newTag){
        return tagService.updateTagNameByPaperIdAndTagName(newTag,id,oldTag);
    }

    /**
     * 插入tag
     * @param name
     * @param id
     * @return
     */
    @RequestMapping(value = "/tag/insertTags/{id}",method = RequestMethod.POST)
    public @ResponseBody int insertTags(@RequestParam("newTag") String name,@PathVariable("id") int id){
        return tagService.insertTagNameByPaperId(name, id);
    }

    /**
     * 返回前台的tagList页面
     * @param tagName
     * @return
     */
    @RequestMapping(value = "/main/tag/returnTagPage/{tagName}")
    public @ResponseBody ModelAndView returnTagPage(@PathVariable("tagName") String tagName){
        ModelAndView modelAndView = new ModelAndView("/main/tagList");
        modelAndView.addObject("tagName",tagName);
        return modelAndView;

    }
    /**
     * 根据tagname查找文章 并进行分页
     * @param tagName
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/main/tag/tagPages/{pageNum}",method = RequestMethod.POST)
    public @ResponseBody PageInfo<Paper> tagPages(@RequestParam("tagName") String tagName, @PathVariable("pageNum") int pageNum){
        return tagService.tagPage(tagName,pageNum);
    }
}