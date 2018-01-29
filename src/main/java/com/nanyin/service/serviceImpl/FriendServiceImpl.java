package com.nanyin.service.serviceImpl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nanyin.config.ExFriends;
import com.nanyin.config.common.Paging;
import com.nanyin.mapper.FriendMapper;
import com.nanyin.model.Friend;
import com.nanyin.model.Users;
import com.nanyin.service.FriendService;
import com.nanyin.service.PaperService;
import com.nanyin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    /**
     *  friends -> exFriends
     * @param friends
     * @return
     */
    private List<ExFriends> setValue(List<Friend> friends){
        List<ExFriends> exFriendsList = Lists.newLinkedList();
        for (Friend friend:friends
             ) {
            ExFriends exFriends = new ExFriends();
            String friendName = userService.findUserNameById(friend.getFriend_id());

            exFriends.setId(friend.getId());
            exFriends.setFriendId(friend.getFriend_id());
            exFriends.setFriendName(friendName);
            exFriends.setEmail(userService.findUsersByName(friendName).getEmail());
            exFriends.setSex(userService.findUsersByName(friendName).getSex());
            exFriends.setPaperCount(paperService.findCountOfPaperByUser(friendName));
            exFriendsList.add(exFriends);
        }
        return exFriendsList;
    }

    @Override
    public List<ExFriends> findAllFriedns(String userName,int pageNum) {
        int userId = getAuthorId(userName);
        int limit= Paging.LIMIT.getValue();
        List<Friend> friends = friendMapper.findAllFriend(userId,(pageNum-1) * limit,limit);
        return setValue(friends);
    }

    @Override
    public int findCountOfFriend(String userName) {
        int userId = getAuthorId(userName);
        return friendMapper.findCountOfFriend(userId);
    }

    @Override
    public Map<String, Object> friendsWithUserId(String userName) {
        Map<String,Object> map = Maps.newHashMap();
        int userId = userService.findAuthorByName(userName);
        List<Users> list = friendMapper.findFriendsWithUserId(userId,0,Paging.LIMIT.getValue()-5);
        map.put("list",list);
        return map;
    }
}
