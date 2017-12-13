package com.nanyin.controller;

import com.google.common.collect.Maps;
import com.nanyin.service.PaperService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 编辑页面
     * @param content
     * @return
     */
    @RequestMapping("/newPage")
    public String markDown(
            @RequestParam(value = "content",required = false) String content){
        return  "markDown";
    }

    /**
     * 返回管理页面
     * @return
     */
    @RequestMapping("/cit")
    public String cit(){
        return "Manage";
    }

    /**
     * 返回管理页面 默认主页
     * @return
     */
    @RequestMapping("/mainPage")
    public String main(){
        return "InnerLayui/main";
    }

    /**
     * 返回新建文章的提交页面
     * @return
     */
    @RequestMapping("/newPage/submit")
    public String newPageSubmit(){
        return "InnerLayui/submit";
    }

    /**
     * 返回修改文章内容页面
     * @param id
     * @return
     */
    @RequestMapping("/updatePageContent/{id}")
    public ModelAndView updatePageContent(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paper",paperService.findPaperById(id));
        modelAndView.setViewName("InnerLayui/UpdatemarkDown");
        return modelAndView;
    }

    /**
     * springmvc 图片上传
     * @param userName
     * @param file
     * @param request
     * @return
     * @throws IOException
     */

    @RequestMapping("/uploadImg")
    public @ResponseBody Map<String,Object> uploadImg(
                                    String userName ,
                                    @RequestParam(value="file",required=false) MultipartFile file,
                                    HttpServletRequest request) throws IOException{
        int flag = 1 ;
        Map<String,Object> map = Maps.newHashMap();
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
        map.put("imagesPath",path);
        map.put("flag",flag);
        return map;
    }



}
