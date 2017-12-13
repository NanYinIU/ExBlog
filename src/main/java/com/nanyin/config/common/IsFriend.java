package com.nanyin.config.common;

public enum IsFriend {
    /**
     * NO_USERNAME : 3 用户名不存在的
     * NOT_FRIEND ： 2 不是好友
     * IS_FRIEND ： 1 是好友
     * HAS_EXCEPTION ： 0 发生错误 异常
     */
    NO_USERNAME(3),

    NOT_FRIEND(2),

    IS_FRIEND (1)  ,

    HAS_EXCEPTION(0) ;

    int value;

    IsFriend(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
