package com.nanyin.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nanyin.model.Comments;
import com.nanyin.model.Paper;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by NanYin on 2017-10-04 下午1:57.
 * 包名： com.nanyin.config
 * 类描述：完整的paper的属性集合
 */
public class AllAttriOfPaper {
//    private int id;
//    private String title;
//    private String content;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Timestamp create_time;
//    private int mark;
//    private String paper_image;
//
//    评论数
    private Paper paper;
    private int count;
    private String login_name;
    // 头像
    private String head;

    private String columnTitle;

//    private String comments_email;
//
//    private String comments_content;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Timestamp comments_time;

    private List<Comments> comments ;
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public void setColumnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
