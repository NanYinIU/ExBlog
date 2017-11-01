package com.nanyin.service.serviceImpl;

import com.nanyin.mapper.TagMapper;
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

    @Override
    public Map<String,Object> findTagNamesByPaperId(int id) {
        List<Object> list = new LinkedList<>();
        Set<String> set =  tagMapper.findTagNamesByPaperId(id);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            String tag = (String) iterator.next();
            Map<String,String> objectMap = new HashMap<>();
            objectMap.put("tags",tag);
            list.add(objectMap);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        map.put("code",0);
        map.put("mes","");
        return map;
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
}
