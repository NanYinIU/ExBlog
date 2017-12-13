package com.nanyin.controller;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.nanyin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by NanYin on 2017-10-08 下午2:04.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
@RequestMapping("/tag")
public class TagController {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    TagService tagService;

    @RequestMapping("/PersonalTag/{name}")
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

    @RequestMapping("/findAllTagsByName/{name}")
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

    @RequestMapping("/tagPage/{id}")
    public String tagPage(@PathVariable("id") int id){
        return "InnerLayui/tagMes";
    }

    @RequestMapping("/tagsByPaperId/{id}")
    public @ResponseBody Map<String, Object> tagsByPaperId(@PathVariable("id") int id){
        return tagService.findTagNamesByPaperId(id);
    }
    @RequestMapping("/updateTag")
    public String updateTag(){
        return "InnerLayui/updateTag";
    }

    @RequestMapping("/delectTags/{id}/{tags}")
    public @ResponseBody int delectTags(@PathVariable("id") int id,@PathVariable("tags") String tags){
        return tagService.delectTagByPaperIdAndTagName(id,tags);
    }

    @RequestMapping("/updateTags/{id}/{oldTag}")
    public @ResponseBody int updateTags(@PathVariable("id") int id,@PathVariable("oldTag") String oldTag,@RequestParam("title") String newTag){
        return tagService.updateTagNameByPaperIdAndTagName(newTag,id,oldTag);
    }
    @RequestMapping("/insertTags/{id}")
    public @ResponseBody int insertTags(@RequestParam("newTag") String name,@PathVariable("id") int id){
        return tagService.insertTagNameByPaperId(name, id);
    }

}