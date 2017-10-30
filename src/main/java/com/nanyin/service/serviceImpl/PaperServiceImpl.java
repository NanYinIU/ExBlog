package com.nanyin.service.serviceImpl;

import com.nanyin.config.AllAttriOfPaper;
import com.nanyin.config.PaperAndComments;
import com.nanyin.mapper.PaperMapper;
import com.nanyin.mapper.UserMapper;
import com.nanyin.model.Column;
import com.nanyin.model.Comments;
import com.nanyin.model.Paper;
import com.nanyin.model.Users;
import com.nanyin.service.CommentsService;
import com.nanyin.service.PaperService;
import com.nanyin.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NanYin on 2017-10-02 下午2:06.
 * 包名： com.nanyin.service.serviceImpl
 * 类描述：
 */
@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    PaperMapper paperMapper;
    @Autowired
    CommentsService commentsService;
    @Autowired
    UserService userService;
    Logger logger = Logger.getLogger(this.getClass());
    @Override
    public Map<String, Object> findAllPapersByTime() {
        Map<String,Object> map = new HashMap<>();
        List<Paper> papers = paperMapper.findAllPapersByTime();
        List<PaperAndComments> paperAndComments = new ArrayList<>();
        for(int i = 0 ; i < papers.size() ; i ++){
          PaperAndComments paperAndComments1 = new PaperAndComments();
          Paper paper = papers.get(i);
          String title = paper.getTitle();
          int id = paper.getId();
          Users users1 = paperMapper.findUserByPaperTitle(id);
          int count = commentsService.findCommentCountByTitle(title);
          paperAndComments1.setTitle(title);
          paperAndComments1.setContent(paper.getContent());
          paperAndComments1.setSegment(paper.getSgement());
          paperAndComments1.setCount(count);
          paperAndComments1.setCreate_time(paper.getCreate_time());
          paperAndComments1.setPaper_image(paper.getPaper_image());
          paperAndComments1.setMark(paper.getMark());
          paperAndComments1.setLogin_name(users1.getLogin_name());
          paperAndComments1.setEmail(users1.getEmail());
          paperAndComments1.setHead(users1.getHead());
          paperAndComments.add(paperAndComments1);
        }
        map.put("paper",paperAndComments);


        return map;
    }

    @Override
    public Map<String, Object> findAllPapersByMark() {
        Map<String,Object> map = new HashMap<>();
        List<Paper> papers = paperMapper.findAllPapersByMark();
        List<PaperAndComments> paperAndComments = new ArrayList<>();
        for(int i = 0 ; i < papers.size() ; i ++){
            PaperAndComments paperAndCommentss = new PaperAndComments();
            Paper paper = papers.get(i);
            int id = paper.getId();
            String title = paper.getTitle();
            Users users1 = paperMapper.findUserByPaperTitle(id);
            int count = commentsService.findCommentCountByTitle(title);
            paperAndCommentss.setTitle(title);
            paperAndCommentss.setContent(paper.getContent());
            paperAndCommentss.setCount(count);
            paperAndCommentss.setCreate_time(paper.getCreate_time());
            paperAndCommentss.setPaper_image(paper.getPaper_image());
            paperAndCommentss.setMark(paper.getMark());
            paperAndCommentss.setLogin_name(users1.getLogin_name());
            paperAndCommentss.setEmail(users1.getEmail());
            paperAndCommentss.setHead(users1.getHead());
            paperAndCommentss.setId(paper.getId());
            paperAndComments.add(paperAndCommentss);

        }
        map.put("paper",paperAndComments);
        return map;

    }

    @Override
    public int updateMarkByTitle(int mark ,String title) {
        return paperMapper.updateMarkByTitle(mark, title);
    }

//    这里会有分页
    @Override
    public List findAllPaperByUser(String name,String search) {

        List<PaperAndComments> paperAndComments = new ArrayList<>();
        List<Paper> papers =  paperMapper.findAllPaperByUser(name,search);
        for(int i = 0 ; i < papers.size() ; i ++){
            PaperAndComments paperAndCommentss = new PaperAndComments();
            Paper paper = papers.get(i);
            String title = paper.getTitle();
            int id = paper.getId();
            Users users1 = paperMapper.findUserByPaperTitle(id);
            int count = commentsService.findCommentCountByTitle(title);
            paperAndCommentss.setTitle(title);
            paperAndCommentss.setContent(paper.getContent());
            paperAndCommentss.setCount(count);
            paperAndCommentss.setCreate_time(paper.getCreate_time());
            paperAndCommentss.setPaper_image(paper.getPaper_image());
            paperAndCommentss.setMark(paper.getMark());
            paperAndCommentss.setLogin_name(users1.getLogin_name());
            paperAndCommentss.setEmail(users1.getEmail());
            paperAndCommentss.setHead(users1.getHead());
            paperAndCommentss.setId(paper.getId());
            paperAndComments.add(paperAndCommentss);

        }
        return paperAndComments;
    }

    @Override
    public List findAllPapers(String search) {
        List<PaperAndComments> paperAndComments = new ArrayList<>();
        List<Paper> papers =  paperMapper.findAllPapers(search);
        for(int i = 0 ; i < papers.size() ; i ++){
            PaperAndComments paperAndCommentss = new PaperAndComments();
            Paper paper = papers.get(i);
            String title = paper.getTitle();
            int id = paper.getId();
            Users users1 = paperMapper.findUserByPaperTitle(id);
            int count = commentsService.findCommentCountByTitle(title);
            paperAndCommentss.setTitle(title);
            paperAndCommentss.setContent(paper.getContent());
            paperAndCommentss.setCount(count);
            paperAndCommentss.setSegment(paper.getSgement());
            logger.info("paper segment:"+ paper.getSgement());
            paperAndCommentss.setCreate_time(paper.getCreate_time());
            paperAndCommentss.setPaper_image(paper.getPaper_image());
            paperAndCommentss.setMark(paper.getMark());
            paperAndCommentss.setLogin_name(users1.getLogin_name());
            paperAndCommentss.setEmail(users1.getEmail());
            paperAndCommentss.setHead(users1.getHead());
            paperAndCommentss.setId(paper.getId());
            paperAndComments.add(paperAndCommentss);

        }
        return paperAndComments;
    }

    @Override
    public AllAttriOfPaper findAllAttriOfPapaer(int id) {
        AllAttriOfPaper allAttriOfPaper = new AllAttriOfPaper();
        Users users = paperMapper.findUserByPaperId(id);
        String columns = paperMapper.findColumnsById(id);
        List<Comments> comments = paperMapper.findCommentsById(id);
        Paper paper = paperMapper.findPaperById(id);
        allAttriOfPaper.setColumnTitle(columns);
        allAttriOfPaper.setComments(comments);
        allAttriOfPaper.setPaper(paper);
        allAttriOfPaper.setCount(commentsService.findCommentCountById(id));
        allAttriOfPaper.setLogin_name(users.getLogin_name());
        allAttriOfPaper.setHead(users.getHead());
        allAttriOfPaper.setTags(paperMapper.findTagsById(id));
        return allAttriOfPaper;
    }

    @Override
    public List<String> findPaperInColumn(String column) {
        return paperMapper.findPaperInColumn(column);
    }

    @Override
    public List<Paper> findPaperByUserName(String name,String search) {
        return paperMapper.findPaperByUserName(name,search);
    }

    @Override
    public int deletePaperByPaperId(int id) {

        return paperMapper.deletePaperByPaperId(id);
    }

    @Override
    public Map<String,Object> findPaperByUser(String name,String pageNum) {
        int limit = 10 ;
        logger.info(pageNum);
        int pageNum1 = Integer.parseInt(pageNum);
        Map<String,Object> map = new HashMap<>();
        int count = paperMapper.findCountOfPaperByUser(name);
        List<Paper> papers = paperMapper.findPaperByUser(name,(pageNum1-1) * limit,limit);
//        logger.info(papers);
        map.put("code",0);
        map.put("mes","");
        map.put("count",count);
        map.put("data",papers);
        return map;
    }

    @Override
    public Paper findPaperById(String id) {
        int id1 = Integer.parseInt(id);
        return paperMapper.findPaperById(id1);
    }

    @Override
    public int updatePaperContentById(String content, String id) {
        int id1 = Integer.parseInt(id);
        return paperMapper.updatePaperContentById(content,id1);
    }

    @Override
    public int insertPaper(String title, String content, String segment, String name) {
//        需要把name通过usermapper转为id插入
        int author = userService.findAuthorByName(name);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        int mark = 1 ;
        String is_pass = "正在审核";
        return paperMapper.insertPaper(title,content,createTime,author,segment,mark,is_pass);
    }

    @Override
    public int findPaperId(String title, String segment,String name) {
        int author = userService.findAuthorByName(name);
        return paperMapper.findPaperId(title,segment,author);
    }


}
