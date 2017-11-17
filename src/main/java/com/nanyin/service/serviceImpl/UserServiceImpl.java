package com.nanyin.service.serviceImpl;

import com.nanyin.config.AllParamOfUser;
import com.nanyin.mapper.UserMapper;
import com.nanyin.model.Friend;
import com.nanyin.model.UserDetail;
import com.nanyin.model.Users;
import com.nanyin.service.UserDetailService;
import com.nanyin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by NanYin on 2017-10-01 下午9:33.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserDetailService userDetailService;

    @Autowired
    UserService userService;

    @Override
    public Users findUsersByName(String name) {
        return userMapper.findUsersByName(name);
    }

    @Override
    public int findAuthorByName(String name) {
        return userMapper.findAuthorByName(name);
    }

    @Override
    public String findUserNameById(int id) {
        return userMapper.findUserNameById(id);
    }

    @Override
    public AllParamOfUser getUserParam(String name) {
        AllParamOfUser allParamOfUser = new AllParamOfUser();

        Users users = userMapper.findUsersByName(name);

        UserDetail userDetail = userDetailService.findUserDetailByUserName(name);

        allParamOfUser.setUsers(users);
        allParamOfUser.setUserDetail(userDetail);

        return allParamOfUser;
    }

    @Override
    public int updateUserMes1(Users users, String name) {
        return userMapper.updateUserMes(users,name);
    }

    private UserDetail setDetail(int userId,
                                String position,
                                java.util.Date data,
                                String address,
                                String sketch){
        UserDetail u1 = new UserDetail();
        u1.setUser_id(userId);
        u1.setAddress(address);
        u1.setBirthday(data);
        u1.setPosition(position);
        u1.setSketch(sketch);
        return u1;
    }

    private Users setUserDetail(String imgMes,
                                String realName,
                                String email
                                ){
        Users users = new Users();
        users.setEmail(email);
        users.setHead(imgMes);
        users.setReal_name(realName);
        return users;
    }

    @Override
    public int updateUserMes(String imgMes,
                             String userName,
                             String realName,
                             String position,
                             String data,
                             String email,
                             String address,
                             String sketch) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        java.util.Date date=sdf.parse(data);
        UserDetail userDetail = userDetailService.findUserDetailByUserName(userName);
        int userId = userService.findAuthorByName(userName);
        if(userDetail == null){
            // 查不到userdetail 说明还没有信息 执行insert操作
            UserDetail u1 = setDetail(userId,position,date,address,sketch);
            userDetailService.insertUserDetailByUserId(u1);
        }else {
            //可以查询到userdetail的信息站执行update操作
            UserDetail u2 = setDetail(userId,position,date,address,sketch);
            userDetailService.updateUserDetailByUserId(u2);
        }

        Users users2 = setUserDetail(imgMes,realName,email);

        return userService.updateUserMes1(users2,userName);
    }

    @Override
    public int updateUserPass(String userName, String newPassWord, String oldPassWord) {
        return userMapper.updateUserPass(userName, newPassWord, oldPassWord);
    }


}
