package com.nanyin.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nanyin.mapper.ColumMapper;
import com.nanyin.model.Column;
import com.nanyin.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by NanYin on 2017-10-02 下午11:03.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class ColumnServiceImpl implements ColumnService {
    @Autowired
    ColumMapper columMapper;
    @Override
    public List<Column> findColumByPaperCount() {
        return columMapper.findColumByPaperCount();
    }

    @Override
    public List<Column> findAllColumn() {
        return columMapper.findAllColumn();
    }

    @Override
    public Map<String,Object> findAllColumnSearch(String search,int pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Column> list = columMapper.findAllColumnSearch(search);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("pageInfo",pageInfo);
        return map;
    }

    @Override
    public Set<String> findCoumnByUser(String name) {
        return columMapper.findCoumnByUser(name);
    }

    @Override
    public int findCountByTitle(String title, String name) {
        return columMapper.findCountByTitle(title, name);
    }

    @Override
    public int insertColumnPaper(int clumnId, int paperId) {
        return columMapper.insertColumnPaper(clumnId,paperId);
    }

    @Override
    public int findColumnId(String title) {
        return columMapper.findColumnId(title);
    }

    @Override
    public Column findColumnByPaperId(int id) {
        return columMapper.findColumnByPaperId(id);
    }

    @Override
    public int updateColumnByPaperId(int paperId, String columnName) {
        int columnId = columMapper.findColumnId(columnName);
//      根据paperId 查看存在不存在匹配的关系  先看存在不存在

        Column column = columMapper.findColumnByPaperId(paperId);
        if(column == null){
            // 是空的那就插入 columnName
            return columMapper.insertColumnPaper(columnId,paperId);
        }else{
            // 不为空才是修改
            return columMapper.updateColumnByPaperId(paperId,columnId);
        }


    }

    @Override
    public Map<String, Object> allColumn(String page) {
        int pageNum = Integer.parseInt(page);
        int limit = 10 ;
        List<Column> columns = columMapper.findAllColumnLimit((pageNum-1) * limit,limit);

        List<Column> columns1 = columMapper.findAllColumn();
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("mes","");
        map.put("count",columns1.size());
        map.put("data",columns);
        return map;
    }

    @Override
    public int insertInlet(String name, String image) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Column column = new Column();
        column.setImage(image);
        column.setTitle(name);
        column.setC_create_time(timestamp);
        return columMapper.insertColumn(column);
    }

    @Override
    public int updateInlet(String name, String image, String id) {
        int id1 = Integer.parseInt(id);
        return columMapper.updateColumnById(name,image,id1);
    }

    @Override
    public int deleteColumnById(int id) {
        return columMapper.deleteColumnById(id);
    }

    @Override
    public Column selectColumnById(int id) {
        return columMapper.selectColumnById(id);
    }
}
