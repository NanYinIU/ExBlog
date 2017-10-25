package com.nanyin.mapper;

import com.nanyin.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * Created by NanYin on 2017-10-01 下午10:14.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@Mapper
public interface RoleMapper {
//    需要注意的是 使用user_role_permission 这个表查出来的关于role 和permission的都需要去重 否则大量重复的数据
    @Select("SELECT r.role_name " +
            "FROM social_blog.`role` r,social_blog.user_role_permission urp,social_blog.users u " +
            "WHERE r.id = urp.role_id AND urp.user_id = u.id AND u.login_name=#{name}")
    Set<String> findRoleByName(String name);
}
