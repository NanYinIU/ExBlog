package com.nanyin.service.serviceImpl;

import com.nanyin.mapper.RoleMapper;
import com.nanyin.model.Role;
import com.nanyin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


/**
 * Created by NanYin on 2017-10-01 下午10:33.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Set<String> findRoleByName(String name) {
        return roleMapper.findRoleByName(name);
    }
}
