package com.nanyin.service.serviceImpl;

import com.nanyin.config.ExFriends;
import com.nanyin.mapper.FriendMapper;
import com.nanyin.mapper.UserMapper;
import com.nanyin.model.Friend;
import com.nanyin.service.FriendService;
import com.nanyin.service.PaperService;
import com.nanyin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by NanYin on 2017-11-10 上午9:12.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    UserService userService;
    @Autowired
    PaperService paperService;

    private int getAuthorId(String name){
        return userService.findAuthorByName(name);
    };

    @Override
    public Friend findFriendById(String userId, String friendId) {
        int user_id = getAuthorId(userId);
        int friend_id = getAuthorId(friendId);
        return friendMapper.findFriendById(user_id,friend_id);
    }

    @Override
    public int insertFriendRelation(String userName, String friendName) {
        int userId = getAuthorId(userName);
        int friendId = getAuthorId(friendName);
        return friendMapper.insertFriendRelation(userId,friendId);
    }

    @Override
    public int deleteFriendRelation(String userName, String friendName) {
        int userId = getAuthorId(userName);
        int friendId = getAuthorId(friendName);
        return friendMapper.deleteFriendRelation(userId,friendId);
    }

    @Override
    public List<ExFriends> findAllFriedns(String userName,int pageNum) {
        int userId = getAuthorId(userName);
        int limit=10;
        List<Friend> friends = friendMapper.findAllFriend(userId,(pageNum-1) * limit,limit);
        List<ExFriends> exFriendsList = new LinkedList<>();
        Iterator iterator = friends.iterator();
        while(iterator.hasNext()){
            Friend friend = (Friend) iterator.next();
            ExFriends exFriends = new ExFriends();
            exFriends.setId(friend.getId());
            exFriends.setFriendId(friend.getFriend_id());
            String friendName = userService.findUserNameById(friend.getFriend_id());
            exFriends.setFriendName(friendName);

            exFriends.setEmail(userService.findUsersByName(friendName).getEmail());
            exFriends.setSex(userService.findUsersByName(friendName).getSex());
            exFriends.setPaperCount(paperService.findCountOfPaperByUser(friendName));
            exFriendsList.add(exFriends);
        }
        return exFriendsList;
    }

    @Override
    public int findCountOfFriend(String userName) {
        int userId = getAuthorId(userName);
        return friendMapper.findCountOfFriend(userId);
    }
}
