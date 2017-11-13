package com.nanyin.model;

/**
 * Created by NanYin on 2017-11-13 下午2:51.
 * 包名： com.nanyin.model
 * 类描述： 收藏文章的表
 */
public class Faves {
    private int id;
    private int user_id;
    private int paper_id;

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

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }
}
