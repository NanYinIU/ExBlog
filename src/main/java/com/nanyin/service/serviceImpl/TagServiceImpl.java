package com.nanyin.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nanyin.mapper.TagMapper;
import com.nanyin.model.Paper;
import com.nanyin.service.PaperService;
import com.nanyin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by NanYin on 2017-10-08 下午2:03.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Autowired
    PaperService paperService;

    @Override
    public Set<String> findAllTagName() {
        return tagMapper.findAllTagName();
    }

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
    public int delectTag(int paperId) {
        return tagMapper.delectTag(paperId);
    }

    @Override
    public int findHasTagByPaperId(int paperId) {
        return tagMapper.findHasTagByPaperId(paperId);
    }

    @Override
    public Map<String,Object> findTagNamesByPaperId(int id) {
        Set<String> set =  tagMapper.findTagNamesByPaperId(id);
         List<Map<String,String>> list = setToList(set);
        Map<String,Object> map = Maps.newHashMap();
        map.put("data",list);
        map.put("code",0);
        map.put("mes","");
        return map;
    }

    private List<Map<String,String>> setToList(Set<String> set){
        List<Map<String,String>> list = Lists.newLinkedList();
        for (String tag: set
             ) {
            Map<String,String> objectMap = new HashMap<>();
            objectMap.put("tags",tag);
            list.add(objectMap);
        }
        return list;
    }

    @Override
    public int delectTagByPaperIdAndTagName(int id, String name) {
        return tagMapper.delectTagByPaperIdAndTagName(id,name);
    }

    @Override
    public int updateTagNameByPaperIdAndTagName(String newName, int id, String name) {
        return tagMapper.updateTagNameByPaperIdAndTagName(newName,id,name);
    }

    @Override
    public int insertTagNameByPaperId(String name, int id) {
        return tagMapper.insertTagNameByPaperId(name,id);
    }

    @Override
    public PageInfo<Paper> tagPage(String tagName, int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Paper> papers = tagMapper.findPaperByTagName(tagName);
        PageInfo<Paper> pageInfo = new PageInfo<>(papers);
        return pageInfo;
    }
}
