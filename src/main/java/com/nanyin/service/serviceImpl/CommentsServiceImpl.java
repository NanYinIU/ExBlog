package com.nanyin.service.serviceImpl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nanyin.config.ShowComments;
import com.nanyin.config.common.TimeUtil;
import com.nanyin.mapper.CommentsMapper;
import com.nanyin.model.Comments;
import com.nanyin.service.CommentsService;
import com.nanyin.service.PaperService;
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
    public int insertComments(String content, String paperId) {
        int id = Integer.parseInt(paperId);

        Timestamp timestamp = TimeUtil.setCurrentTime();
        Comments comments = new Comments();
        comments.setComments_content(content);
        comments.setComments_paper(id);
        comments.setComments_time(timestamp);
        return commentsMapper.insertComments(comments);
    }
}
