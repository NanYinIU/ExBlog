package com.nanyin;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.Buffer;
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
        System.out.println(System.getProperty("user.dir"));
    }
    @Test
    public void test2() throws IOException{
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(""));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(""));
        byte [] bt = new byte[1024];
        while ((inputStream.read(bt)) != 0){
            outputStream.write(bt);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

}
