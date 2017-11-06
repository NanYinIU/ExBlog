package com.nanyin.service;

import com.nanyin.model.UserDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by NanYin on 2017-11-02 下午5:43.
 * 包名： com.nanyin.service
 * 类描述：
 */
@Service
public interface UserDetailService {

    UserDetail findUserDetailByUserName( String userName);

    int updateUserDetailByUserId(UserDetail userDetail);

    int insertUserDetailByUserId(UserDetail userDetail);
}
