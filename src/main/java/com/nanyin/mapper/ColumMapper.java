package com.nanyin.mapper;

import com.nanyin.model.Column;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.swing.border.TitledBorder;
import java.util.List;
import java.util.Set;

/**
 * Created by NanYin on 2017-10-02 下午1:15.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@Mapper
public interface ColumMapper {

    // 专题内文章由多到少排序
    @Select("SELECT c.title,c.C_create_time,c.image " +
            "FROM social_blog.`Column` c,social_blog.Column_paper cp " +
            "WHERE c.id = cp.Column_id " +
            "GROUP BY c.title,c.C_create_time,c.image " +
            "ORDER BY COUNT(cp.paper_id) DESC LIMIT 0,5")
    List<Column> findColumByPaperCount();

//   查询所有专题信息
    @Select("SELECT * FROM social_blog.`Column`")
    List<Column> findAllColumn();

    @Select("SELECT COUNT(*) FROM social_blog.paper p, social_blog.`Column` c ,social_blog.Column_paper cp ,social_blog.users u " +
            "WHERE cp.Column_id = c.id AND cp.paper_id = p.id AND u.id = p.author AND c.title = #{title} AND u.login_name=#{name}")
    int findCountByTitle(@Param("title") String title,@Param("name") String name);

    @Select("SELECT c.title " +
            "FROM social_blog.paper p, social_blog.`Column` c ,social_blog.Column_paper cp ,social_blog.users u , social_blog.tag t " +
            "WHERE cp.Column_id = c.id AND cp.paper_id = p.id AND u.id = p.author AND u.login_name=#{name}")
    Set<String> findCoumnByUser(String name);



// 保证了专题和对应的paper的数量
}
