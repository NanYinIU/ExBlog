package com.nanyin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * Created by NanYin on 2017-10-08 上午10:15.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@Mapper
public interface TagMapper {

    @Select("SELECT t.tag_name " +
            "FROM social_blog.tag t,social_blog.paper p ,social_blog.users u " +
            "WHERE t.paper_id = p.id AND u.id  = p.author AND u.login_name = #{name}")
    Set<String> findTagNameByUser(String name);

//    查数量
    @Select("SELECT COUNT(*) FROM social_blog.tag t ,social_blog.paper p , social_blog.users u WHERE t.paper_id = p.id AND u.id = p.author AND tag_name = #{tag_name} AND u.login_name=#{name}")
    int findTagCountByTagName(@Param("name") String name,@Param("tag_name") String tag_name);
}
