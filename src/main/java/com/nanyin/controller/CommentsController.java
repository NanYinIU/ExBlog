package com.nanyin.controller;

import com.nanyin.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping( value = "/comments/getComments")
    public String getComments(){
        return "InnerLayui/comMes";
    }
    @RequestMapping(value = "/comments/deleteComment/{id}")
    public @ResponseBody int deleteComment(@PathVariable("id") int id){
        return commentsService.deleteCommentById(id);
    }
    @RequestMapping(value = "/comments/ByPaperId/{id}",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> commentsByPaperId(@PathVariable("id") int id){
        return commentsService.findAllCommentsByPaperId(id);
    }

    @RequestMapping(value = "/main/comments/insertComment")
    public @ResponseBody int insertComment(@RequestParam("userName") String userName,@RequestParam("pageId") int pageId,@RequestParam("text") String text){
        return commentsService.insertComments(text,pageId,userName);
    }


    /**
     * 主页的评论信息 不需要权限控制 而上面的需要权限控制 和request.post控制
     * @return
     */
    @RequestMapping(value = "/main/comments/commentsWithPaperAndUserMes")
    public @ResponseBody Map<String,Object> commentsWithPaperAndUserMes(){
        return commentsService.findAllCommentsOrderByTime();
    }

    @RequestMapping(value = "/main/comments/PersonalComments")
    public @ResponseBody Map<String,Object> PersonalComments(@RequestParam("userName") String userName){
        return commentsService.findCommentsByUserId(userName);
    }

}
