package com.nanyin;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.Buffer;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,2);
        System.out.println(calendar.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = simpleDateFormat.format(date);
        Timestamp timestamp = Timestamp.valueOf(string);
        System.out.println(timestamp);
        System.out.println(new Timestamp(System.currentTimeMillis()));
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
    @Test
    public void test3(){

        Set<Integer> hashset = new HashSet<>();
        hashset.add(1);hashset.add(3);hashset.add(4);hashset.add(3);hashset.add(6);hashset.add(7);
        int id = 2 ;
        Iterator iterator =  hashset.iterator();
        while(iterator.hasNext()) {
            id = (int) iterator.next();
        }
        System.out.println(id+1);
    }

}
