package com.nanyin.config;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * Created by NanYin on 2017-11-01 下午1:26.
 * 包名： com.nanyin.config
 * 类描述： paper和对应的column信息
 */
public class PaperAndColumn {
    private int paperId;
    private String paperTitle;
    private String theme;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    private String is_pass;

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getIs_pass() {
        return is_pass;
    }

    public void setIs_pass(String is_pass) {
        this.is_pass = is_pass;
    }
}
