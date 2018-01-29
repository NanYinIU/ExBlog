package com.nanyin.mapper;

import com.nanyin.model.Friend;
import com.nanyin.model.Users;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by NanYin on 2017-11-10 上午9:14.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@Mapper
public interface FriendMapper {

    @Select("SELECT * FROM social_blog.friends WHERE user_id=#{userId} AND friend_id=#{friendId}")
    Friend findFriendById(@Param("userId") int userId, @Param("friendId") int friendId);

    @Insert("INSERT INTO social_blog.friends(user_id,friend_id) VALUES(#{userId},#{friendId})")
    int insertFriendRelation(@Param("userId") int userId, @Param("friendId") int friendId);

    @Delete("DELETE FROM social_blog.friends WHERE user_id=#{userId} and friend_id=#{friendId}")
    int deleteFriendRelation(@Param("userId") int userId, @Param("friendId") int friendId);

    @Select("SELECT * FROM social_blog.friends WHERE user_id=#{userId} LIMIT #{page},#{limit}")
    List<Friend> findAllFriend(@Param("userId") int userId ,@Param("page")int page ,@Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM social_blog.friends WHERE user_id=#{userId}")
    int findCountOfFriend(@Param("userId") int userId);

    @Select("SELECT u.* FROM social_blog.users u,social_blog.friends f " +
            "WHERE f.friend_id = u.id AND f.user_id=#{userId} LIMIT #{PageNum},#{pageSize}")
    List<Users> findFriendsWithUserId(@Param("userId") int userId, @Param("PageNum")int PageNum, @Param("pageSize")int pageSize);
}