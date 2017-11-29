package com.nanyin.model.To;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * Created by NanYin on 17-11-29
 * 包名：com.nanyin.model.To
 * 类描述：包含用户的基本信息 和角色信息
 */
public class UserAndRoles {

    private int userId;
    private String userName;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTIme;

    /***
     * 状态信息 数据库里面的是int类型 在server里面进行修改为 【正在使用 和 已停用】
     */  
    private String status;

    private String roleName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateTIme() {
        return createTIme;
    }

    public void setCreateTIme(Timestamp createTIme) {
        this.createTIme = createTIme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
