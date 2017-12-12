package com.nanyin.controller;

import com.google.common.collect.Maps;
import com.nanyin.config.ExFriends;
import com.nanyin.model.Friend;
import com.nanyin.model.Users;
import com.nanyin.service.FriendService;
import com.nanyin.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NanYin on 2017-11-10 上午9:18.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class FriendController {

    @Autowired
    FriendService friendService;
    @Autowired
    UserService userService;


    @RequestMapping("/user/checkFriends")
    public @ResponseBody
    Map<String,Boolean> findFriends(@RequestParam("userId") String userId, @RequestParam("friendId") String friendId){
        Map<String,Boolean> map = Maps.newHashMap();
        Friend friend = friendService.findFriendById(userId, friendId);
        return checkHasFriends(friend,map);
    }

    private  Map<String,Boolean> checkHasFriends(Friend friend,Map<String,Boolean> map){
        boolean flag = false;
        if(friend != null){
            //有值 返回true
            flag = true;
            map.put("flag",flag);
            return map;
        }
        else{
            //没有值返回false
            map.put("flag",flag);
            return map;
        }
    }

    @RequestMapping("/user/addFriends")
    public @ResponseBody int addFriends(@RequestParam("userId") String userId, @RequestParam("friendId") String friendId){
        return friendService.insertFriendRelation(userId, friendId);
    }

    @RequestMapping("/user/deleteFriends")
    public @ResponseBody int deleteFriends(@RequestParam("userId") String userId, @RequestParam("friendId") String friendId){
        return friendService.deleteFriendRelation(userId, friendId);
    }
    @RequestMapping("/user/friendTable/{pageNum}")
    public @ResponseBody Map<String,Object> friendTable(HttpServletRequest request,@PathVariable("pageNum") int pageNum){
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("user");
        List<ExFriends> list = friendService.findAllFriedns(userName,pageNum);
        Map<String,Object> map = new HashMap<>();
        int count = friendService.findCountOfFriend(userName);
        map.put("data",list);
        map.put("code",0);
        map.put("mes","");
        map.put("pageNum",pageNum);
        map.put("count",count);
        return map;
    }

    @RequestMapping("/user/friendPage")
    public ModelAndView friendPage(@Param("url") String url){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("InnerLayui/friendPage");
        modelAndView.addObject("url",url);
        return modelAndView;
    }

    /**
     * 添加用户之前先进行检查 昵称不存在返回3 已经是好友了返回2 出现错误返回 0 正常情况下返回1
     * @param userId
     * @param friendId
     * @return
     */
    @RequestMapping("/user/checkFriendName")
    public @ResponseBody int checkFriendName(@RequestParam("userId") String userId, @RequestParam("friendId") String friendId){
        //需要两部分 昵称是否存在？ 是否已经成为好友？
        try {
            Users users = userService.findUsersByName(friendId);

            if(users == null){
                return 3;
            }else if (friendService.findFriendById(userId, friendId) != null){
                return 2;
            }else {
                return 1;
            }
        }catch (Exception e){
            return 0;
        }
    }

}

