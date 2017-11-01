package com.nanyin.controller;

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
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    TagService tagService;

    @RequestMapping("/PersonalTag/{name}")
    public @ResponseBody Map<String,Object> PersonalTag(@PathVariable("name") String name){
        Set<String> tagname = tagService.findTagNameByUser(name);

        Map<String,Object> map = new HashMap<>();
        List List = new ArrayList<>();

        Iterator iterator = tagname.iterator();
        while (iterator.hasNext()){
            Map<String,Object> map1 = new HashMap<>();

            String tag_name = (String) iterator.next();
            logger.info(tag_name);
            int count = tagService.findTagCountByTagName(name,tag_name);
            map1.put("tag_name", tag_name);
            map1.put("count",count);
            List.add(map1);
        }

        map.put("tagList",List);
        return map;
    }
    @RequestMapping("/findAllTagsByName/{name}")
    public @ResponseBody Map<String,Object> findAllTagsByName(@PathVariable("name") String name){
            Set<String> list = tagService.findTagNameByUser(name);
            logger.info("set:"+list);
            Map<String ,Object> map = new HashMap<>();
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
        logger.info(name);
        int insert = tagService.insertTagNameByPaperId(name, id);
        logger.info("返回值:"+insert);
        return insert;
    }

}