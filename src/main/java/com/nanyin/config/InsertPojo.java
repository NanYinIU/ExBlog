package com.nanyin.config;

import java.util.List;

/**
 * Created by NanYin on 2017-10-30 上午10:08.
 * 包名： com.nanyin.config
 * 类描述：插入式数据绑定的pojo
 */
public class InsertPojo {
    private String title;
    private String segment;
    private String newTag;
    private String main1;
    private List<String> tag;
    private String theme;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getNewTag() {
        return newTag;
    }

    public void setNewTag(String newTag) {
        this.newTag = newTag;
    }

    public String getMain1() {
        return main1;
    }

    public void setMain1(String main1) {
        this.main1 = main1;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "InsertPojo{" +
                "title='" + title + '\'' +
                ", segment='" + segment + '\'' +
                ", newTag='" + newTag + '\'' +
                ", main1='" + main1 + '\'' +
                ", tag=" + tag +
                ", theme='" + theme + '\'' +
                '}';
    }
}
