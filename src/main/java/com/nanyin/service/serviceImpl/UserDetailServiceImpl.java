package com.nanyin.service.serviceImpl;

import com.nanyin.mapper.UserDetailMapper;
import com.nanyin.model.UserDetail;
import com.nanyin.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NanYin on 2017-11-02 下午5:43.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    UserDetailMapper userDetailMapper;
    @Override
    public UserDetail findUserDetailByUserName(String userName) {
        return userDetailMapper.findUserDetailByUserName(userName);
    }

    @Override
    public int updateUserDetailByUserId(UserDetail userDetail) {
        return userDetailMapper.updateUserDetailByUserId(userDetail);
    }

    @Override
    public int insertUserDetailByUserId(UserDetail userDetail) {
        return userDetailMapper.insertUserDetailByUserId(userDetail);
    }
}
