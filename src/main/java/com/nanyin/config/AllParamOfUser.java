package com.nanyin.config;

import com.nanyin.model.UserDetail;
import com.nanyin.model.Users;

/**
 * Created by NanYin on 2017-11-02 下午5:37.
 * 包名： com.nanyin.config
 * 类描述：user的全部信息
 */
public class AllParamOfUser {
    private Users users;
    private UserDetail userDetail;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

}
