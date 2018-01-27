package com.nanyin.model.Ex;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * Created by NanYin on 18-1-27.
 * 包名： com.nanyin.model.Ex
 * 类名： CommentsWithPaperMes
 * 类描述：首页最新评论位置
 */
public class CommentsWithPaperMes {
    //评论的文章信息
    private int paper_id;
    private String title;
    private String paper_image;
    //  评论信息
    private int comment_id;
    private String comments_content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp comments_time;
    // 评论员信息
    private int comments_user_id;
    private String comments_user_name;

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaper_image() {
        return paper_image;
    }

    public void setPaper_image(String paper_image) {
        this.paper_image = paper_image;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public int getComments_user_id() {
        return comments_user_id;
    }

    public void setComments_user_id(int comments_user_id) {
        this.comments_user_id = comments_user_id;
    }

    public String getComments_user_name() {
        return comments_user_name;
    }

    public void setComments_user_name(String comments_user_name) {
        this.comments_user_name = comments_user_name;
    }
}
