package com.nanyin.mapper;

import com.nanyin.model.Friend;
import com.nanyin.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by NanYin on 2017-10-01 下午8:35.
 * 包名： com.nanyin.mapper
 * 类描述：用户mapper类
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 根据昵称查询基本信息
     * @param name
     * @return
     */
    @Select("SELECT * FROM social_blog.users WHERE login_name=#{name} ")
    Users findUsersByName(String name);

    @Select("SELECT * FROM social_blog.users WHERE login_name=#{name} LIMIT #{page},#{limit}")
    Users findUsersByNameLimit(@Param("name") String name,@Param("page") int page,@Param("limit")  int limit );


    /**
     * 根据name查询paper表的author信息
     * @param name
     * @return
     */
    @Select("SELECT u.id FROM social_blog.users u WHERE u.login_name=#{name}")
    int findAuthorByName(String name);

    @Select("SELECT u.login_name FROM social_blog.users u WHERE u.id=#{id}")
    String findUserNameById(int id);

    @Update("UPDATE social_blog.users SET head=#{users.head} , real_name = #{users.real_name},email=#{users.email} WHERE login_name=#{name}")
    int updateUserMes(@Param("users") Users users,@Param("name") String name);

    /**
     * 改密码
     * @param userName 用户名
     * @param newPassWord 新密码
     * @param oldPassWord 旧密码
     * @return 1
     */
    @Update("UPDATE social_blog.users SET password = #{newPassWord} WHERE login_name = #{userName} AND password = #{oldPassWord}")
    int updateUserPass(@Param("userName") String userName,@Param("newPassWord") String newPassWord,@Param("oldPassWord") String oldPassWord);

    @Select({
            "<script>",
            "SELECT * FROM social_blog.users",
            "<if test=\"search!=null and search!=''\">",
            "WHERE login_name LIKE concat(concat('%',#{search}),'%') ",
            "</if>",
            "ORDER BY id ASC",
            "</script>"
    })
    List<Users> findAllUsers(@Param("search")String search);

    @Select({
            "<script>",
            "SELECT * FROM social_blog.users",
            "ORDER BY id ASC",
            "</script>"
    })
    List<Users> findAllUsersList();

    @Select({
            "<script>",
            "SELECT * FROM social_blog.users",
            "ORDER BY id ASC",
            " LIMIT #{page},#{limit}",
            "</script>"
    })
    List<Users> findAllUsersListLimit(@Param("page") int page,@Param("limit")  int limit );

    @Select({
            "<script>",
            "SELECT * FROM social_blog.users",
            "<if test=\"search!=null and search!=''\">",
            "WHERE login_name LIKE concat(concat('%',#{search}),'%') ",
            "</if>",
            "ORDER BY id ASC",
            "LIMIT #{page},#{limit}",
            "</script>"
    })
    List<Users> findAllUsersLimit(@Param("search") String search,@Param("page") int page,@Param("limit") int limit);

}
