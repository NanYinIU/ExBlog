package com.nanyin.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * Created by NanYin on 2017-10-01 下午8:15.
 * 包名： com.nanyin.model
 * 类描述：评论类
 */
public class Comments {
    private int id;
    private int comments_user;
    private String comments_content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp comments_time;
    private int comments_paper;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComments_user() {
        return comments_user;
    }

    public void setComments_user(int comments_user) {
        this.comments_user = comments_user;
    }

    public String getComments_content() {
        return comments_content;
    }

    public void setComments_content(String comments_content) {
        this.comments_content = comments_content;
    }

    public Timestamp getComments_time() {
        return comments_time;
    }

    public void setComments_time(Timestamp comments_time) {
        this.comments_time = comments_time;
    }

    public int getComments_paper() {
        return comments_paper;
    }

    public void setComments_paper(int comments_paper) {
        this.comments_paper = comments_paper;
    }
}
