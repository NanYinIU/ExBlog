package com.nanyin.model;

/**
 * Created by NanYin on 2017-11-09 下午5:13.
 * 包名： com.nanyin.model
 * 类描述：
 */
public class Friend {
    private int id;
    private int user_id;
    private int friend_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }
}
