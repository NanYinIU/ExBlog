package com.nanyin.service;

import com.nanyin.model.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by NanYin on 2017-10-01 下午10:33.
 * 包名： com.nanyin.service
 * 类描述：
 */
@Service
public interface RoleService {
    Set<String> findRoleByName(String name);
}
