package com.nanyin.config.common;

public enum Paging {
    /**
     * 分页 每页有 LIMIT 10项
     *     初始化 INIT 0
     */
    LIMIT(10),

    INIT(0);
    private int value;

    Paging(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
