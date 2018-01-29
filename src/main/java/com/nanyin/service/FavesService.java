package com.nanyin.service;

import com.nanyin.model.Paper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by NanYin on 2017-11-13 下午2:54.
 * 包名： com.nanyin.service
 * 类描述：
 */
@Service
public interface FavesService {

    int insertFavesItem(String userName,String pageId);

    Map<String,Boolean> checkIsFaves(String userName, String pageId);

    Map<String,Object> findFaves(String userName,String search,int pageNum);

    int deleteFaverItem(String userName,int paperId);

    Map<String,List> findFavesItem(String userName);

}
