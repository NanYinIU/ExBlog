package com.nanyin.controller;

import com.nanyin.service.PaperService;
import javafx.application.Application;
import org.apache.catalina.core.ApplicationContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by NanYin on 2017-10-04 下午1:21.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class OtherController {
    @Autowired
    PaperService paperService;

    Logger logger = Logger.getLogger(this.getClass());
    @RequestMapping("/c")
    public String c(){
        return "page";
    }
    @RequestMapping("/ci")
    public String ci(@RequestParam(value = "content",required = false) String content){
        return  "markDown";
    }
    @RequestMapping("/cit")
    public String cit(){
        return "Manage";
    }
    @RequestMapping("/cite")
    public String cite(){
        return "InnerLayui/main";
    }

    @RequestMapping("/cte")
    public ModelAndView cte(String url){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("InnerLayui/pageMes");
        Map<String,Object> map = new HashMap<>();
        map.put("url",url);
        modelAndView.addAllObjects(map);

        return modelAndView;
    }
    @RequestMapping("/M1")
    public String M1(){
        return "InnerLayui/submit";
    }

    @RequestMapping("/model")
    public ModelAndView model(@RequestParam(value = "content",required = false) String content, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        logger.info("content ="+content);
        Map<String,Object> map = new HashMap<>();
        logger.info("map="+map);
        modelAndView.setViewName("InnerLayui/submit");
        modelAndView.addAllObjects(map);
        modelAndView.addObject("content",content);
        logger.info(modelAndView.getModelMap());
        return modelAndView;
    }
    @RequestMapping("/newMD/{id}")
    public ModelAndView newMD(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paper",paperService.findPaperById(id));
        modelAndView.setViewName("InnerLayui/UpdatemarkDown");
        return modelAndView;
    }

    @RequestMapping("/uploadImg")
    public @ResponseBody Map<String,Object> uploadImg(String userName ,@RequestParam(value="file",required=false) MultipartFile file,
                                  HttpServletRequest request) throws IOException{
        int flag = 1 ;
        Map<String,Object> map = new HashMap<>();
        //物理路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        logger.info("物理路径:"+pathRoot);
        String path="";
        if(!file.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            path="/images/"+uuid+"."+imageName;
            // 文件写入文件系统
            try {
                String s = System.getProperty("user.dir");
                file.transferTo(new File(s+"/src/main/resources/static"+path));
            }catch (FileNotFoundException e){
                e.printStackTrace();
                flag = 0;
            }finally {
                System.out.println("上传结束");
            }

        }
        System.out.println(path);
        map.put("imagesPath",path);
        map.put("flag",flag);
        return map;
    }

}
