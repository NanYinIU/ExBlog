package com.nanyin.service.serviceImpl;

import com.nanyin.mapper.ColumMapper;
import com.nanyin.model.Column;
import com.nanyin.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
}
