package com.nanyin;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.nanyin.config.common.Paging;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import java.io.*;
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
    @Test
    public void test4(){
        // guava mutimap
        Multimap<String,String> multimap = ArrayListMultimap.create();
        multimap.putAll("a",new ArrayList<>(Arrays.asList("12","56")));
        System.out.println(multimap.asMap());
        System.out.println(multimap.get("a"));

        Map<String,Object> map = new HashMap<>();
        map.put("a",new ArrayList<>(Arrays.asList("12","56")));
        System.out.println(map);
    }
    @Test
    public void test5() {
        System.out.println(new Md5Hash("123", "MD5",1024));
        System.out.println();
    }
    public void test6(){
        System.out.println("hello world");
    }


}
