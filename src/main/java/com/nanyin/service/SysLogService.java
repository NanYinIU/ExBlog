package com.nanyin.service;

import com.nanyin.model.SysLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NanYin on 2017-11-14 下午3:02.
 * 包名： com.nanyin.service
 * 类描述：
 */
@Service
public interface SysLogService {

    int insertSysLog(SysLog sysLog);

    List<SysLog>
    findLogByUserName(String userName);
}
