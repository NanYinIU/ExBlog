package com.nanyin.mapper;

import com.nanyin.model.Tag;
import org.apache.ibatis.annotations.*;

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

    @Select("SELECT COUNT(*) FROM social_blog.tag WHERE paper_id = #{paper_id}")
    int findHasTagByPaperId(int paper_id);

    /**
     * 删除文章的删除文章对应的tag
     * @param paper_id
     * @return
     */
    @Delete("DELETE FROM social_blog.tag WHERE paper_id = #{paper_id}")
    int delectTag(int paper_id);

    /**
     * tag 标签的增加
     */
    @Insert("<script>"+
            "insert into social_blog.tag(tag_name,paper_id) "
            + "values "
            + "<foreach collection =\"tag_name\" item=\"id\" index= \"index\" separator =\",\"> "
            + "(#{id},#{paper_id})"
            + "</foreach > "
            + "</script>")
    int insertTagByUserId(@Param("tag_name") List<String> tagName,@Param("paper_id") int paperId);

}
