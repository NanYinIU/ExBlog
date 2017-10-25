package com.nanyin.model;

/**
 * Created by NanYin on 2017-10-01 下午8:20.
 * 包名： com.nanyin.model
 * 类描述：权限实体类
 */
public class Permission {
    private int id;
    private String permission_name;
    private String describe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
