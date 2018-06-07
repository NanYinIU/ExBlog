package com.nanyin.mapper;

import com.nanyin.model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by NanYin on 2017-10-02 下午1:10.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@CacheConfig(cacheNames = "demo")
@Mapper
public interface PaperMapper {

    /**
     * 查询所有文章用于管理员的审查 物理分页
     * @param page 页数
     * @param limit 每个项数
     * @param search 查询内容
     * @param startTime 现在时间
     * @param endTime 前n天 比如现在时间 。。。 nowday的几天前就是endtime
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM social_blog.paper p WHERE 1=1",
            "<if test=\"search!=null and search!=''\">",
            "AND p.title LIKE concat(concat('%',#{search}),'%')",
            "</if>",
            "<if test=\"endTime!=null\">",
            "AND p.create_time > #{endTime}",
            "</if>",
            "LIMIT #{page},#{limit}",
            "</script>"})
    List<Paper> findPapers(@Param("page") int page,
                           @Param("limit") int limit,
                           @Param("search")String search,
                           @Param("startTime") Timestamp startTime,
                           @Param("endTime") Timestamp endTime);

    /**
     * 一个月内热门文章
     * @param endTime
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM social_blog.paper p WHERE 1=1",
            "<if test=\"endTime!=null\">",
            "AND p.create_time > #{endTime}",
            "</if>",
            " ORDER BY p.mark DESC",
            "</script>"})
    List<Paper> findPaperInMonth(@Param("endTime") Timestamp endTime);
    /**
    * 按最新时间排序
    */

    @Select("SELECT * FROM social_blog.paper WHERE is_pass = \"审核通过\" ORDER BY create_time DESC LIMIT 0,5 ")
    List<Paper> findAllPapersByTime();

    @Select("SELECT * FROM social_blog.paper WHERE is_pass = \"审核通过\" ORDER BY create_time DESC")
    List<Paper> findAllPapersByTimeNoLimit();

    /**
    * 按照热度排序
    */
    @Select("SELECT * FROM  social_blog.paper p WHERE p.is_pass = \"审核通过\"  ORDER BY p.mark DESC LIMIT 0,3")
    List<Paper> findAllPapersByMark();

    /**
     * 根据文章title 查作者信息
     */
    @Select("SELECT u.* FROM social_blog.users u , social_blog.paper p WHERE p.author = u.id AND p.id=#{id}")
    Users findUserByPaperTitle(int id);

    @Select("SELECT u.* FROM social_blog.users u , social_blog.paper p WHERE p.author = u.id AND p.id=#{id}")
    Users findUserByPaperId(int id);

    @Update("UPDATE social_blog.paper SET mark = #{mark} WHERE id = #{id}")
    int updateMarkByTitle(@Param("mark") int mark, @Param("id")int id);

    @Select({"<script>",
            "SELECT p.* FROM social_blog.paper p , social_blog.users s WHERE s.id = p .author AND s.login_name = #{name} AND p.is_pass = \"审核通过\"",
            "<if test=\"search!=null and search!=''\">",
            "AND  p.title LIKE concat(concat('%',#{search}),'%') ",
            "</if>",
            "ORDER BY  p.create_time DESC",
            "</script>" })
    List<Paper> findAllPaperByUser(@Param("name") String name,@Param("search") String search);


    @Select({"<script>",
            "SELECT p.* FROM social_blog.paper p , social_blog.users s WHERE s.id = p .author AND  p.is_pass = \"审核通过\"",
            "<if test=\"search!=null and search!=''\">",
            "AND  p.title LIKE concat(concat('%',#{search}),'%') ",
            "</if>",
            "ORDER BY  p.create_time DESC",
            "</script>" })

    List<Paper> findAllPapers(@Param("search") String search);

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

    /**
     * 根据id查询paper
     */
    @Select("SELECT * FROM social_blog.paper p WHERE p.id = #{id}")
    Paper findPaperById(int id);

    /**
     * 根据id查询Title
     * @param id
     * @return
     */
    @Select("SELECT p.title FROM social_blog.paper p WHERE p.id = #{id}")
    String findPaperTitleById(int id);

    @Select("SELECT p.* " +
            "FROM social_blog.paper p, social_blog.`Column` c ,social_blog.Column_paper cp " +
            "WHERE cp.Column_id = c.id AND cp.paper_id = p.id AND c.title = #{column}")
    List<Paper> findPaperInColumn(String column);

    @Select({
            "<script>",
            "SELECT p.* FROM social_blog.paper p , social_blog.users u WHERE p.author = u.id AND u.login_name = #{name}",
            "<if test=\"search!=null and search!=''\">",
            "AND  p.title LIKE concat(concat('%',#{search}),'%') ",
            "</if>",
            "ORDER BY p.create_time DESC",
            "</script>"
    })
    List<Paper> findPaperByUserName(@Param("name") String name,@Param("search") String search);

    /**
     * 根据id删除paper信息
     * @param id paperid
     * @return
     */
    @Delete("DELETE FROM social_blog.paper WHERE id=#{id}")
    int deletePaperByPaperId(int id);

    @Delete("delete from social_blog.column_paper  where paper_id=#{id}")
    int deletePaperInCoulmn(int id);
    /**
     * 查到所有的文章信息
     *  @param name user's name
     *  @param page = pageNum*limit-1
     *  @param limit             
     */
    @Select("SELECT p.* FROM social_blog.paper p LEFT JOIN social_blog.users u ON u.id = p.author WHERE u.login_name=#{name} LIMIT #{page},#{limit}")
    List<Paper> findPaperByUser(@Param("name") String name,@Param("page") int page,@Param("limit") int limit);

    /**
     * 查询总条数
     */
    @Select("SELECT COUNT(*) FROM social_blog.paper p LEFT JOIN social_blog.users u ON u.id = p.author WHERE u.login_name=#{name}")
    int findCountOfPaperByUser(String name);

    /**
     * 更新文章内容
     * @param content
     * @param id
     * @return
     */
    @Update("UPDATE social_blog.paper p SET p.content = #{content} WHERE p.id = #{id} ")
    int updatePaperContentById(@Param("content") String content,@Param("id") int id);


    /**
     *
     * @param title 标题
     * @param content 文章正文
     * @param createTime 创建时间
     * @param author 作者
     * @param segment 片段
     * @param mark 初始设为一
     * @param is_pass 初始设置 正在审核
     * @return
     */
    @Insert("INSERT INTO social_blog.paper(title,content,create_time,mark,author,segment,is_pass) " +
            "VALUES(#{title},#{content},#{createTime},#{mark},#{author},#{segment},#{is_pass})")
    int insertPaper(
            @Param("title") String title,  @Param("content")String content,
            @Param("createTime") Timestamp createTime, @Param("author")int author,
            @Param("segment") String segment,@Param("mark") int mark,@Param("is_pass") String is_pass);

    /**
     * 根据信息查id
     * @param title
     * @param segment
     * @param author
     * @return
     */
    @Select("SELECT id FROM social_blog.paper WHERE title=#{title} AND segment=#{segment} AND author = #{author}")
    int findPaperId(@Param("title") String title,@Param("segment") String segment,@Param("author") int author);

    @Update("UPDATE social_blog.paper SET is_pass = #{is_pass} WHERE id = #{id}")
    int updataPaperStatus(@Param("id") int id,@Param("is_pass") String is_pass);


}
