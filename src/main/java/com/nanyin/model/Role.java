package com.nanyin.model;

/**
 * Created by NanYin on 2017-10-01 下午8:21.
 * 包名： com.nanyin.model
 * 类描述：角色实体类
 */
public class Role {
    private int id;
    private String role_name;
    private String describe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
