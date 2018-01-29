package com.nanyin.service;

import com.github.pagehelper.PageInfo;
import com.nanyin.model.Paper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by NanYin on 2017-10-08 下午2:02.
 * 包名： com.nanyin.service
 * 类描述：
 */
@Service
public interface TagService {

    Set<String> findAllTagName();

    Set<String> findTagNameByUser(String name);

    int findTagCountByTagName(String name,String tag_name);

    /**
     * 为paper添加tag标签
     * @param tagName
     * @param paperId
     * @return
     */
    int insertTagByUserId(List<String> tagName,int paperId);

    int delectTag(int paper_id);

    int findHasTagByPaperId(int paper_id);

    Map<String,Object> findTagNamesByPaperId(int id);

    /**
     *  删除标签
     * @param id
     * @param name
     * @return
     */
    int delectTagByPaperIdAndTagName(int id,String name);

    /**
     * 修改
     * @param newName
     * @param id
     * @param name
     * @return
     */

    int updateTagNameByPaperIdAndTagName(String newName,int id,String name);

    /**
     *  插入
     * @param name
     * @param id
     * @return
     */
    int insertTagNameByPaperId(String name,int id);

    PageInfo<Paper> tagPage(String tagName,int pageNum);
}
