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
}
