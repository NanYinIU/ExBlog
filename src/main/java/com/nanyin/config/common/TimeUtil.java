package com.nanyin.config.common;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Demo class
 *
 * @author NanYinIU
 * @date
 */
@Component
public class TimeUtil {
    // 分会当前时间

    @org.jetbrains.annotations.NotNull
    public static Timestamp setCurrentTime(){
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     *
     * @return
     */
    public static SimpleDateFormat formatToYMD(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }

}
