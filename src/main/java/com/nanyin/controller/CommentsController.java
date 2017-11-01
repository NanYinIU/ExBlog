package com.nanyin.controller;

import com.nanyin.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by NanYin on 2017-11-01 下午5:15.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class CommentsController {
    @Autowired
    CommentsService commentsService;

    @RequestMapping("/comments/getComments")
    public String getComments(){
        return "InnerLayui/comMes";
    }
    @RequestMapping("/comments/deleteComment/{id}")
    public @ResponseBody int deleteComment(@PathVariable("id") int id){
        return commentsService.deleteCommentById(id);
    }
    @RequestMapping("/comments/ByPaperId/{id}")
    public @ResponseBody Map<String,Object> commentsByPaperId(@PathVariable("id") int id){
        return commentsService.findAllCommentsByPaperId(id);
    }


}
