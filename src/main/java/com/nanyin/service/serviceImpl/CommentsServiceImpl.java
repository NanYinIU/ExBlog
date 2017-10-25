package com.nanyin.service.serviceImpl;

import com.nanyin.mapper.CommentsMapper;
import com.nanyin.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NanYin on 2017-10-02 下午8:07.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsMapper commentsMapper;
    @Override
    public int findCommentCountByTitle(String title) {
        return commentsMapper.findCommentCountByTitle(title);
    }

    @Override
    public int findCommentCountById(int id) {
        return commentsMapper.findCommentCountByid(id);
    }
}
