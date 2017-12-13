package com.nanyin.controller;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.nanyin.model.SysLog;
import com.nanyin.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NanYin on 2017-11-14 下午5:01.
 * 包名： com.nanyin.controller
 * 类描述：
 */
@Controller
public class SysLogController {
    @Autowired
    SysLogService sysLogService;

    @RequestMapping("/sysLog/userSecurity")
    public String userSecurity(){
        return "InnerLayui/userSecurity";
    }

    @RequestMapping("/sysLog/findSysLog")
    public @ResponseBody
    Multimap<String,SysLog> findSysLog(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("user");
        Multimap<String,SysLog> map = HashMultimap.create();
        List<SysLog> list = sysLogService.findLogByUserName(userName);
        map.putAll("list",list);
        return map;
    }
}
