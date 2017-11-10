package com.nanyin.service;

import com.nanyin.config.ExFriends;
import com.nanyin.model.Friend;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NanYin on 2017-11-10 上午9:12.
 * 包名： com.nanyin.service
 * 类描述：
 */
@Service
public interface FriendService {
    Friend findFriendById(String userName, String friendName);

    int insertFriendRelation(String userName,String friendName);

    int deleteFriendRelation(String userName,String friendName);

    List<ExFriends> findAllFriedns(String userName,int pageNum);

    int findCountOfFriend (String userName);
}
