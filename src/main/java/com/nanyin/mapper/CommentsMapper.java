package com.nanyin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by NanYin on 2017-10-02 下午8:01.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@Mapper
public interface CommentsMapper {
    @Select("SELECT COUNT(*) " +
            "FROM social_blog.comments c ,social_blog.paper p " +
            "WHERE p.id = c.comments_paper AND p.title=#{title}")
    int findCommentCountByTitle(String title);

    @Select("SELECT COUNT(*) " +
            "FROM social_blog.comments c ,social_blog.paper p " +
            "WHERE p.id = c.comments_paper AND p.id=#{id}")
    int findCommentCountByid(int id);
}
