package com.nanyin.mapper;

import com.nanyin.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * Created by NanYin on 2017-10-01 下午9:47.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@Mapper
public interface PermissionMapper {

    @Select("SELECT permission_name " +
            "FROM social_blog.permission p,social_blog.user_role_permission urp,social_blog.users u " +
            "WHERE p.id = urp.permisssion_id AND urp.user_id = u.id AND u.login_name= #{name}")
    Set<String> findPermissionByName(@Param("name") String name);
}
