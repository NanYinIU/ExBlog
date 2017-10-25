package com.nanyin.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by NanYin on 2017-10-08 下午2:02.
 * 包名： com.nanyin.service
 * 类描述：
 */
@Service
public interface TagService {


    Set<String> findTagNameByUser(String name);

    int findTagCountByTagName(String name,String tag_name);
}
