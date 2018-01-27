package com.nanyin.service;

import com.nanyin.model.Comments;

import java.util.List;
import java.util.Map;

/**
 * Created by NanYin on 2017-10-02 下午8:06.
 * 包名： com.nanyin.service
 * 类描述：
 */
public interface CommentsService {

    int findCommentCountByTitle(int title);

    int findCommentCountById(int id);

    int deleteCommentById(int id);



    Map<String,Object> findAllCommentsByPaperId(int id);

    int insertComments(String content,String paperId);

    Map<String,Object> findAllCommentsOrderByTime();
}
