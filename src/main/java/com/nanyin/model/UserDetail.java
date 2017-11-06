package com.nanyin.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;


/**
 * Created by NanYin on 2017-11-02 下午2:27.
 * 包名： com.nanyin.model
 * 类描述：user的拓展类
 */
public class UserDetail {

    /**
     * 自增主键
     */
    private int detail_id;

    /**
     * 职位 如:学生
     */
    private String position;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp birthday;
    /**
     * 住址
     */
    private String address;
    /**
     * 简述
     */
    private String sketch;
    /**
     * 外键
     */
    private int user_id;
    /**
     * setter和getter
     */
    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
