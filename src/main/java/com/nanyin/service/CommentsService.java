package com.nanyin.service;

/**
 * Created by NanYin on 2017-10-02 下午8:06.
 * 包名： com.nanyin.service
 * 类描述：
 */
public interface CommentsService {

    int findCommentCountByTitle(String title);

    int findCommentCountById(int id);

}
