package com.nanyin.service.serviceImpl;

import com.nanyin.mapper.UserMapper;
import com.nanyin.model.Users;
import com.nanyin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NanYin on 2017-10-01 下午9:33.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Users findUsersByName(String name) {
        return userMapper.findUsersByName(name);
    }

    @Override
    public int findAuthorByName(String name) {
        return userMapper.findAuthorByName(name);
    }
}
