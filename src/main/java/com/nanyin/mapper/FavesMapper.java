package com.nanyin.mapper;

import com.nanyin.model.Faves;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by NanYin on 2017-11-13 下午2:53.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@Mapper
public interface FavesMapper {

    @Insert("INSERT INTO social_blog.faves(user_id,paper_id) VALUES(#{userId},#{paperId})")
    int insertFavesItem(@Param("userId") int userId, @Param("paperId")int paperId);

    @Select("SELECT * FROM social_blog.faves WHERE user_id=#{userId} AND paper_id=#{paperId}")
    Faves findFavesItem(@Param("userId") int userId, @Param("paperId")int paperId);

    @Select("SELECT * FROM social_blog.faves WHERE user_id=#{userId} LIMIT #{page},#{limit}")
    List<Faves> findAllFavesItem(@Param("userId") int userId,@Param("page") int page,@Param("limit") int limit);

    @Select("SELECT * FROM social_blog.faves WHERE user_id=#{userId}")
    List<Faves> findAllFavesItemNoLimit(@Param("userId") int userId);

    @Delete("DELETE FROM social_blog.faves WHERE user_id=#{userId} AND paper_id=#{paperId}")
    int deleteFaverItem(@Param("userId") int userId, @Param("paperId")int paperId);


}
