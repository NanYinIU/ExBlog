package com.nanyin.service;

import com.nanyin.model.Users;
import org.springframework.stereotype.Service;

/**
 * Created by NanYin on 2017-10-01 下午6:07.
 * 包名： com.nanyin.service
 * 类描述：
 */
@Service
public interface UserService {

    Users findUsersByName(String name);

    int findAuthorByName(String name);
}
