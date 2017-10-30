package com.nanyin.service.serviceImpl;

import com.nanyin.mapper.TagMapper;
import com.nanyin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by NanYin on 2017-10-08 下午2:03.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Override
    public Set<String> findTagNameByUser(String name) {
        return tagMapper.findTagNameByUser(name);
    }

    @Override
    public int findTagCountByTagName(String name, String tag_name) {
        return tagMapper.findTagCountByTagName(name, tag_name);
    }

    @Override
    public int insertTagByUserId(List<String> tagName, int paperId) {
        return tagMapper.insertTagByUserId(tagName,paperId);
    }

    @Override
    public int delectTag(int paper_id) {
        return tagMapper.delectTag(paper_id);
    }

    @Override
    public int findHasTagByPaperId(int paper_id) {
        return tagMapper.findHasTagByPaperId(paper_id);
    }
}
