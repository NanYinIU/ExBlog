package com.nanyin.service;


import com.nanyin.model.Column;

import java.util.List;
import java.util.Set;

/**
 * Created by NanYin on 2017-10-02 下午11:03.
 * 包名： com.nanyin.service
 * 类描述：
 */
public interface ColumnService {

    List<Column> findColumByPaperCount();


    List<Column> findAllColumn();

    Set<String> findCoumnByUser(String name);

    int findCountByTitle(String title,String name);


}
