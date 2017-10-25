package com.nanyin.mapper;

import com.nanyin.config.AllAttriOfPaper;
import com.nanyin.model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NanYin on 2017-10-02 下午1:10.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@Mapper
public interface PaperMapper {

//    按最新时间排序
    @Select("SELECT * FROM social_blog.paper ORDER BY create_time DESC LIMIT 0,5")
    List<Paper> findAllPapersByTime();

//  按照热度排序
    @Select("SELECT * FROM social_blog.paper p ORDER BY p.mark DESC ")
    List<Paper> findAllPapersByMark();
    // 根据文章title 查作者信息
    @Select("SELECT u.* FROM social_blog.users u , social_blog.paper p WHERE p.author = u.id AND p.id=#{id}")
    Users findUserByPaperTitle(int id);

    @Select("SELECT u.* FROM social_blog.users u , social_blog.paper p WHERE p.author = u.id AND p.id=#{id}")
    Users findUserByPaperId(int id);

    @Update("UPDATE social_blog.paper SET mark = #{mark} WHERE title = #{title}")
    int updateMarkByTitle(@Param("mark") int mark, @Param("title")String title);

    @Select({"<script>",
            "SELECT p.* FROM social_blog.paper p , social_blog.users s WHERE s.id = p .author AND s.login_name = #{name}",
            "<if test=\"search!=null and search!=''\">",
            "AND  p.title LIKE concat(concat('%',#{search}),'%') ",
            "</if>",
            "ORDER BY  p.create_time DESC",
            "</script>" })
    List<Paper> findAllPaperByUser(@Param("name") String name,@Param("search") String search);


    @Select({"<script>",
            "SELECT p.* FROM social_blog.paper p , social_blog.users s WHERE s.id = p .author",
            "<if test=\"search!=null and search!=''\">",
            "AND  p.title LIKE concat(concat('%',#{search}),'%') ",
            "</if>",
            "ORDER BY  p.create_time DESC",
            "</script>" })

    List<Paper> findAllPapers(String search);

    @Select("SELECT t.tag_name " +
            "FROM social_blog.paper p , social_blog.tag t " +
            "WHERE t.paper_id = p.id AND p.id = #{id} ")
    List<String> findTagsById(int id);

    @Select("SELECT c.* " +
            "FROM social_blog.paper p, social_blog.comments c " +
            "WHERE p.id = c.comments_paper AND p.id =#{id}")
    List<Comments> findCommentsById(int id);

    @Select("SELECT c.title FROM social_blog.paper p,social_blog.`Column` c ,social_blog.Column_paper cp WHERE cp.Column_id = c.id AND cp.paper_id = p.id AND p.id = #{id}")
    String findColumnsById(int id);

    @Select("SELECT * FROM social_blog.paper p WHERE p.id = #{id}")
    Paper findPaperById(int id);

    @Select("SELECT p.title " +
            "FROM social_blog.paper p, social_blog.`Column` c ,social_blog.Column_paper cp " +
            "WHERE cp.Column_id = c.id AND cp.paper_id = p.id AND c.title = #{column}")
    List<String> findPaperInColumn(String column);

    @Select({
            "<script>",
            "SELECT p.* FROM social_blog.paper p , social_blog.users u WHERE p.author = u.id AND u.login_name = #{name}",
            "<if test=\"search!=null and search!=''\">",
            "AND  p.title LIKE concat(concat('%',#{search}),'%') ",
            "</if>",
            "ORDER BY  p.create_time DESC",
            "</script>"
    })
    List<Paper> findPaperByUserName(@Param("name") String name,@Param("search") String search);

    @Delete("DELETE FROM social_blog.paper WHERE id=#{id}")
    int deletePaperByPaperId(int id);

}
