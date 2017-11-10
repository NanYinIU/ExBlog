package com.nanyin.mapper;

import com.nanyin.model.Comments;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by NanYin on 2017-10-02 下午8:01.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@Mapper
public interface CommentsMapper {
    @Select("SELECT COUNT(*) " +
            "FROM social_blog.comments c ,social_blog.paper p " +
            "WHERE p.id = c.comments_paper AND p.id=#{title}")
    int findCommentCountByTitle(int title);

    @Select("SELECT COUNT(*) " +
            "FROM social_blog.comments c ,social_blog.paper p " +
            "WHERE p.id = c.comments_paper AND p.id=#{id}")
    int findCommentCountByid(int id);

    /**
     * 删除评论
     * @param id
     * @return
     */
    @Delete("DELETE FROM social_blog.comments WHERE id=#{id}")
    int deleteCommentById(int id);

    /**
     * 查询对应paper的所有comments
     * @param id
     * @return
     */
    @Select("SELECT c.* FROM social_blog.comments c " +
            "LEFT JOIN social_blog.paper p ON c.comments_paper = p.id " +
            "WHERE comments_paper=#{id}")
    List<Comments> findAllCommentsByPaperId(int id);

    @Insert("INSERT INTO social_blog.comments(comments_content,comments_time,comments_paper)" +
            "VALUES(#{comments.comments_content},#{comments.comments_time},#{comments.comments_paper})")
    int insertComments(@Param("comments") Comments comments);
}
