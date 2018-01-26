package com.nanyin.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by NanYin on 2017-10-01 下午6:09.
 * 包名： com.nanyin.model
 * 类描述：user 实体类
 */
public class Users implements Serializable{
    private int id;
    private String login_name;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp create_time;

    private String email;

    private String real_name;

    private String sex;
    // 头像
    private String head;

    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login_name='" + login_name + '\'' +
                ", password='" + password + '\'' +
                ", create_time=" + create_time +
                ", email='" + email + '\'' +
                ", real_name='" + real_name + '\'' +
                ", sex='" + sex + '\'' +
                ", head='" + head + '\'' +
                ", status=" + status +
                '}';
    }
}
