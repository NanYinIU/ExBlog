package com.nanyin.mapper;

import com.nanyin.model.UserDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by NanYin on 2017-11-02 下午2:34.
 * 包名： com.nanyin.mapper
 * 类描述：用户的个人完善详细信息
 */
@Mapper
@Component
public interface UserDetailMapper {
    /**
     * 根据用户的昵称查询详细信息
     * @param userName
     * @return
     */
    @Select("SELECT ud.* FROM social_blog.userDetail ud " +
            "LEFT JOIN social_blog.users u " +
            "ON u.id = ud.user_id " +
            "WHERE u.login_name=#{login_name}")
    UserDetail findUserDetailByUserName(@Param("login_name") String userName);

    @Update("UPDATE social_blog.userDetail u " +
            "SET u.`position`= #{userDetail.position}," +
            "u.birthday=#{userDetail.birthday}," +
            "u.address=#{userDetail.address}," +
            "u.sketch=#{userDetail.sketch} " +
            "WHERE user_id=#{userDetail.user_id}")
    int updateUserDetailByUserId(UserDetail userDetail);

    @Insert("INSERT INTO " +
            "social_blog.userDetail(`position`,birthday,address,sketch,user_id)" +
            "VALUES (#{userDetail.position},#{userDetail.birthday},#{userDetail.address},#{userDetail.sketch},#{userDetail.user_id})")
    int insertUserDetailByUserId(UserDetail userDetail);



}
