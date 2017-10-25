package com.nanyin.service.serviceImpl;

import com.nanyin.mapper.PermissionMapper;
import com.nanyin.model.Permission;
import com.nanyin.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by NanYin on 2017-10-01 下午10:35.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Set<String> findPermissionByName(String name) {
        return permissionMapper.findPermissionByName(name);
    }
}
