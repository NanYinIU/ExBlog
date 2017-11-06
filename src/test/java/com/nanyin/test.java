package com.nanyin;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by NanYin on 2017-10-01 下午9:40.
 * 包名： com.nanyin
 * 类描述：
 */
public class test {
    @Test
    public void test1(){
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date());
        System.out.println(new Timestamp(System.currentTimeMillis()));
        System.out.println(36/10);
    }
}
