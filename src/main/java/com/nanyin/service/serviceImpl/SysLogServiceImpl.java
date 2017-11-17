package com.nanyin.service.serviceImpl;

import com.nanyin.mapper.SysLogMapper;
import com.nanyin.model.SysLog;
import com.nanyin.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NanYin on 2017-11-14 下午3:02.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    public int insertSysLog(SysLog sysLog) {
        return sysLogMapper.insertSysLog(sysLog);
    }

    @Override
    public List<SysLog> findLogByUserName(String userName) {
        return sysLogMapper.findLogByUserName(userName);
    }
}
