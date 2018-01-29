package com.nanyin.service.serviceImpl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nanyin.config.ShowComments;
import com.nanyin.config.common.Paging;
import com.nanyin.config.common.TimeUtil;
import com.nanyin.mapper.CommentsMapper;
import com.nanyin.model.Comments;
import com.nanyin.model.Ex.CommentsWithPaperMes;
import com.nanyin.model.Paper;
import com.nanyin.model.Users;
import com.nanyin.service.CommentsService;
import com.nanyin.service.PaperService;
import com.nanyin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.*;

/**
 * Created by NanYin on 2017-10-02 下午8:07.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    PaperService paperService;

    @Autowired
    UserService userService;
    @Override
    public int findCommentCountByTitle(int title) {
        return commentsMapper.findCommentCountByTitle(title);
    }

    @Override
    public int findCommentCountById(int id) {
        return commentsMapper.findCommentCountByid(id);
    }

    @Override
    public int deleteCommentById(int id) {
        return commentsMapper.deleteCommentById(id);
    }

    /**
     *
     * @param list
     * @return
     */
    private List<ShowComments> setValue(List<Comments> list){
        List<ShowComments> showCommentsList = Lists.newLinkedList();
        for (Comments comment : list) {
            ShowComments showComments = new ShowComments();
            showComments.setId(comment.getId());
            showComments.setContent(comment.getComments_content());
            showComments.setTime(comment.getComments_time());
            showComments.setTitle(paperService.findPaperTitleById(comment.getComments_paper()));
            showCommentsList.add(showComments);
        }
        return showCommentsList;
    }

    @Override
    public Map<String,Object> findAllCommentsByPaperId(int id) {
        Map<String,Object> map = Maps.newHashMap();
        List<Comments> comments = commentsMapper.findAllCommentsByPaperId(id);
        List<ShowComments> list= setValue(comments);
        map.put("code",0);
        map.put("count",30);
        map.put("mes","");
        map.put("data",list);
        return map;
    }

    @Override
    public int insertComments(String content, int paperId,String userName) {

        Timestamp timestamp = TimeUtil.setCurrentTime();
        Comments comments = new Comments();
        comments.setComments_content(content);
        comments.setComments_paper(paperId);
        comments.setComments_user(userService.findAuthorByName(userName));
        comments.setComments_time(timestamp);
        return commentsMapper.insertComments(comments);
    }

    @Override
    public Map<String, Object> findAllCommentsOrderByTime() {
        Map<String,Object> map = Maps.newHashMap();
//      这里应该统一控制页面
        List<Comments> list = commentsMapper.findAllCommentsOrderByTime(0, Paging.LIMIT.getValue()-5);
        List<CommentsWithPaperMes> commentsWithPaperMesList = setCommentsWithPaperAndUserMes(list);
        map.put("comments",commentsWithPaperMesList);
        return map;
    }

    @Override
    public Map<String, Object> findCommentsByUserId(String userName) {
        int userId = userService.findAuthorByName(userName);
        List<Comments> list = commentsMapper.findCommentsByUserId(userId,0,Paging.LIMIT.getValue());
        List<CommentsWithPaperMes> commentsWithPaperMesList = setCommentsWithPaperAndUserMes(list);
        Map<String,Object> map = Maps.newHashMap();
        map.put("list",commentsWithPaperMesList);
        return map;
    }

    private List<CommentsWithPaperMes> setCommentsWithPaperAndUserMes(List<Comments> list){
        List<CommentsWithPaperMes> commentsWithPaperMesList = Lists.newLinkedList();
        for (Comments comments: list
             ) {
            CommentsWithPaperMes commentsWithPaperMes = new CommentsWithPaperMes();

           Paper paper = paperService.findPaperById(comments.getComments_paper());

            Users users = userService.findUsersById(comments.getComments_user());

            commentsWithPaperMes.setComment_id(comments.getId());
            commentsWithPaperMes.setComments_content(comments.getComments_content());
            commentsWithPaperMes.setComments_time(comments.getComments_time());
            commentsWithPaperMes.setComments_user_id(users.getId());
            commentsWithPaperMes.setComments_user_name(users.getLogin_name());
            commentsWithPaperMes.setPaper_id(paper.getId());
            commentsWithPaperMes.setPaper_image(paper.getPaper_image());
            commentsWithPaperMes.setTitle(paper.getTitle());
            commentsWithPaperMesList.add(commentsWithPaperMes);
        }
        return commentsWithPaperMesList;
    }
}
