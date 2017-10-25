package com.nanyin.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * Created by NanYin on 2017-10-01 下午10:12.
 * 包名： com.nanyin.model
 * 类描述： 专栏 主题
 */
public class Column {
    private int id;
    private String title;
    private String image;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp c_create_time;

    public Timestamp getC_create_time() {
        return c_create_time;
    }

    public void setC_create_time(Timestamp c_create_time) {
        this.c_create_time = c_create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
