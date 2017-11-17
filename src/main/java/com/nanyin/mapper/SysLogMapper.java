package com.nanyin.mapper;

import com.nanyin.model.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by NanYin on 2017-11-14 下午3:03.
 * 包名： com.nanyin.mapper
 * 类描述：
 */
@Mapper
public interface SysLogMapper {

    @Insert("INSERT INTO social_blog.sysLog (descript, logIp, createBy, createDate)" +
            "VALUES(#{sysLog.descript},#{sysLog.logIp},#{sysLog.createBy},#{sysLog.createDate})")
    int insertSysLog(@Param("sysLog") SysLog sysLog);

    @Select("SELECT * FROM social_blog.sysLog WHERE createBy=#{userName} order by createDate desc LIMIT 0,10")
    List<SysLog> findLogByUserName(@Param("userName") String userName);

}
