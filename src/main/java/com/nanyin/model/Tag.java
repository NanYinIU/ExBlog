package com.nanyin.model;

/**
 * Created by NanYin on 2017-10-01 下午8:33.
 * 包名： com.nanyin.model
 * 类描述：标签实体类
 */
public class Tag {
    private int id;
    private String tag_name;
    private int papper_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public int getPapper_id() {
        return papper_id;
    }

    public void setPapper_id(int papper_id) {
        this.papper_id = papper_id;
    }
}
