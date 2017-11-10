package com.nanyin.service;

import com.nanyin.config.AllParamOfUser;
import com.nanyin.model.Friend;
import com.nanyin.model.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.ParseException;

/**
 * Created by NanYin on 2017-10-01 下午6:07.
 * 包名： com.nanyin.service
 * 类描述：
 */
@Service
public interface UserService {

    Users findUsersByName(String name);

    int findAuthorByName(String name);

    String findUserNameById(int id);

    AllParamOfUser getUserParam(String name);

    int updateUserMes1(Users users, String name);

    int updateUserMes(String imgMes,String userName, String realName, String position, String data, String email, String address, String sketch) throws ParseException;


}
