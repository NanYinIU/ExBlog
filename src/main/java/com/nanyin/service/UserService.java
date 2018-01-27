package com.nanyin.service;

import com.github.pagehelper.PageInfo;
import com.nanyin.config.AllParamOfUser;
import com.nanyin.config.Author;
import com.nanyin.model.Friend;
import com.nanyin.model.To.UserAndRoles;
import com.nanyin.model.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by NanYin on 2017-10-01 下午6:07.
 * 包名： com.nanyin.service
 * 类描述：
 */
@Service
public interface UserService {

    Users findUsersByName(String name);

    Users findUsersById(int id);

    Users findUsersByName(String name,int page,int pageNum);

    int findAuthorByName(String name);

    String findUserNameById(int id);

    AllParamOfUser getUserParam(String name);

    int updateUserMes1(Users users, String name);

    int updateUserMes(String imgMes,String userName, String realName, String position, String data, String email, String address, String sketch) throws ParseException;

    int updateUserPass(String userName,String newPassWord,String oldPassWord);

    Map<String,Object> userAndAuthor(String search, int pageNum);

    List<UserAndRoles> userAndRole(int pageNum);

    List<Users> findAllUsers(int page, int limit );

    List<Users> findAllUsers( );

    Users checkUserMes(int id);
}
