package com.nanyin.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nanyin.config.AllAttriOfPaper;
import com.nanyin.config.PaperAndColumn;
import com.nanyin.config.PaperAndComments;
import com.nanyin.mapper.PaperMapper;
import com.nanyin.mapper.UserMapper;
import com.nanyin.model.Column;
import com.nanyin.model.Comments;
import com.nanyin.model.Paper;
import com.nanyin.model.Users;
import com.nanyin.service.ColumnService;
import com.nanyin.service.CommentsService;
import com.nanyin.service.PaperService;
import com.nanyin.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.ec.SunEC;

import java.sql.Timestamp;
import java.util.*;

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
    @Autowired
    ColumnService columnService;
    Logger logger = Logger.getLogger(this.getClass());

//    赋值
    private PaperAndComments setValue(Paper paper,Users users){
        PaperAndComments paperAndComments = new PaperAndComments();
        int count = commentsService.findCommentCountByTitle(paper.getId());
        paperAndComments.setTitle(paper.getTitle());
        paperAndComments.setContent(paper.getContent());
        paperAndComments.setSegment(paper.getSgement());
        paperAndComments.setCount(count);
        paperAndComments.setCreate_time(paper.getCreate_time());
        paperAndComments.setPaper_image(paper.getPaper_image());
        paperAndComments.setMark(paper.getMark());
        paperAndComments.setLogin_name(users.getLogin_name());
        paperAndComments.setEmail(users.getEmail());
        paperAndComments.setHead(users.getHead());
        paperAndComments.setId(paper.getId());
        return paperAndComments;
    }
    private List<PaperAndComments> paperAndCommentsList(List<Paper> papers){
        List<PaperAndComments> paperAndComments = new ArrayList<>();
        for (Paper paper: papers) {
            int id = paper.getId();
            Users users = paperMapper.findUserByPaperTitle(id);
            PaperAndComments paperAndComment = setValue(paper,users);
            paperAndComments.add(paperAndComment);
        }
        return paperAndComments;
    }

    /**
     * 完成所有文章的查询和分页功能
     * @return map
     */
    @Override
    public Map<String, Object> findPapers(int pageNum) {
        int limit = 10 ;
        Map<String,Object> map = new HashMap<>();
        List<Paper> list = paperMapper.findPapers((pageNum-1) * limit,limit);
        int count = list.size();
        map.put("code",0);
        map.put("mes","");
        map.put("count",count);
        map.put("data",list);
        return map;
    }

    @Override
    public Map<String, Object> findAllPapersByTime() {
        Map<String,Object> map = new HashMap<>();
        List<Paper> papers = paperMapper.findAllPapersByTime();
        List<PaperAndComments> paperAndComments = paperAndCommentsList(papers);
        map.put("paper",paperAndComments);
        return map;
    }

    @Override
    public Map<String, Object> findAllPapersByMark() {
        Map<String,Object> map = new HashMap<>();
        List<Paper> papers = paperMapper.findAllPapersByMark();
        List<PaperAndComments> paperAndComments = paperAndCommentsList(papers);
        map.put("paper",paperAndComments);
        return map;

    }

    @Override
    public int updateMarkByTitle(int mark ,String id) {
        int id1 = Integer.parseInt(id);
        return paperMapper.updateMarkByTitle(mark, id1);
    }

    private PaperAndComments initPaperAndConmments(String name){
        PaperAndComments initPaperAndComment = new PaperAndComments();
        initPaperAndComment.setLogin_name(name);
        Users users = userService.findUsersByName(name);
        initPaperAndComment.setHead(users.getHead());
        initPaperAndComment.setTitle("266fb5d2-b97b-41dd-999e-1143c0963fd4");
        initPaperAndComment.setCreate_time(new Timestamp(System.currentTimeMillis()));
        return initPaperAndComment;
    }



    @Override
    public PageInfo<PaperAndComments> findAllPaperByUser(String name,String search,int pageNum) {

        List<Paper> papers =  paperMapper.findAllPaperByUser(name,search);
        List<PaperAndComments> paperAndComments = paperAndCommentsList(papers);
        PageHelper.startPage(pageNum,10);
        PageInfo<PaperAndComments> pageInfo = new PageInfo<>(paperAndComments);

        if(paperAndComments.size() == 0){
         //初始化一个paperAndComments
            PaperAndComments paperAndComment = initPaperAndConmments(name);
            paperAndComments.add(paperAndComment);
            PageInfo<PaperAndComments> pageInfo2 = new PageInfo<>(paperAndComments);
            return pageInfo2;
        }
       else {
            return pageInfo;
        }
    }

    @Override
    public int getTotal(String search,String username) {
        return paperMapper.findAllPaperByUser(username,search).size();
    }

    @Override
    public int getAllTotal(String search) {
        return paperMapper.findAllPapers(search).size();
    }

    @Override
    public PageInfo findAllPapers(String search,int pageNum) {

        List<Paper> papers =  paperMapper.findAllPapers(search);
        List<PaperAndComments> paperAndComments = paperAndCommentsList(papers);
        PageHelper.startPage(pageNum,8);
        PageInfo pageInfo = new PageInfo(paperAndComments);
        return pageInfo;
    }

    @Override
    public List findAllPapers(String search) {
        List<Paper> papers =  paperMapper.findAllPapers(search);
        List<PaperAndComments> paperAndComments = paperAndCommentsList(papers);
        return paperAndComments;
    }

    private AllAttriOfPaper setValue(Users users,Paper paper,List<Comments> comments,String columns){
        AllAttriOfPaper allAttriOfPaper = new AllAttriOfPaper();

        allAttriOfPaper.setColumnTitle(columns);
        allAttriOfPaper.setComments(comments);
        allAttriOfPaper.setPaper(paper);
        allAttriOfPaper.setCount(commentsService.findCommentCountById(paper.getId()));
        allAttriOfPaper.setLogin_name(users.getLogin_name());
        allAttriOfPaper.setHead(users.getHead());
        allAttriOfPaper.setTags(paperMapper.findTagsById(paper.getId()));
        return allAttriOfPaper;
    }

    @Override
    public AllAttriOfPaper findAllAttriOfPapaer(int id) {
        Users users = paperMapper.findUserByPaperId(id);
        String columns = paperMapper.findColumnsById(id);
        List<Comments> comments = paperMapper.findCommentsById(id);
        Paper paper = paperMapper.findPaperById(id);

       return setValue(users,paper,comments,columns);
    }

    @Override
    public List<Paper> findPaperInColumn(String column) {
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
        // 固定 limit 
        int limit = 10 ;
        int pageNum1 = Integer.parseInt(pageNum);
        Map<String,Object> map = new HashMap<>();
        int count = paperMapper.findCountOfPaperByUser(name);
        List<Paper> papers = paperMapper.findPaperByUser(name,(pageNum1-1) * limit,limit);
        List<PaperAndColumn> li1 = new ArrayList<>();
        Iterator iterator = papers.iterator();
        while (iterator.hasNext()){
            PaperAndColumn paperAndColumn = new PaperAndColumn();
            Paper paper = (Paper) iterator.next();
            paperAndColumn.setPaperId(paper.getId());
            paperAndColumn.setCreateTime(paper.getCreate_time());
            paperAndColumn.setIs_pass(paper.getIs_pass());
            paperAndColumn.setPaperTitle(paper.getTitle());
            Column column = columnService.findColumnByPaperId(paper.getId());
            if(column == null){
                paperAndColumn.setTheme("暂无");
            }
            else {
                paperAndColumn.setTheme(column.getTitle());
            }
            li1.add(paperAndColumn);
        }
        map.put("code",0);
        map.put("mes","");
        map.put("count",count);
        map.put("data",li1);
        return map;
    }

    @Override
    public Paper findPaperById(String id) {
        int id1 = Integer.parseInt(id);
        return paperMapper.findPaperById(id1);
    }

    @Override
    public String findPaperTitleById(int id) {
        return paperMapper.findPaperTitleById(id);
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
        String is_pass = "审核通过";
        return paperMapper.insertPaper(title,content,createTime,author,segment,mark,is_pass);
    }

    @Override
    public int findPaperId(String title, String segment,String name) {
        int author = userService.findAuthorByName(name);
        return paperMapper.findPaperId(title,segment,author);
    }

    @Override
    public int findCountOfPaperByUser(String name) {
        return paperMapper.findCountOfPaperByUser(name);
    }

    /**
     * 查询到当前paperId的前一页和后一页的标题和id值
     * @param paperId
     * @return
     */
    @Override
    public Map<String,Object> findPreAndNextPage(int paperId) {

        List<Paper> paperList = paperMapper.findAllPapersByTimeNoLimit();
        Paper paper = paperMapper.findPaperById(paperId);

        int currentPaperNum = 0 ;

        for (int i = 0 ; i < paperList.size() ; i ++){
            if ( paperList.get(i).getId() == paper.getId() ){
                currentPaperNum = i ;
                break;
            }
        }

        logger.info("当前页面是第"+currentPaperNum);
        boolean isPrePage = false;
        boolean isNextPage = false;
        // 前一页
        int prePaperNum = currentPaperNum - 1;
        int nextPaperNum = currentPaperNum + 1;
        String prePaperTitle;
        int prePaperId;
        if(prePaperNum < 0){
            prePaperTitle="已经到头了";
            prePaperId = -1 ;
            isPrePage = true ;
        }else {
            prePaperTitle = "上一篇："+paperList.get(prePaperNum).getTitle();
            prePaperId = paperList.get(prePaperNum).getId();
        }
        Paper prePaper = new Paper();
        prePaper.setId(prePaperId);
        prePaper.setTitle(prePaperTitle);
        // 下一页
        String nextPaperTitle;
        int nextPaperId;
        if(nextPaperNum >= paperList.size()){
            nextPaperTitle = "已经到末尾了";
            nextPaperId = -2;
            isNextPage = true;
        }else {
            nextPaperTitle = "下一篇："+paperList.get(nextPaperNum).getTitle();
            nextPaperId = paperList.get(nextPaperNum).getId();
        }
        Paper nextPaper = new Paper();
        nextPaper.setId(nextPaperId);
        nextPaper.setTitle(nextPaperTitle);


        Map<String,Object> map = new HashMap<>();

        map.put("prePaper",prePaper);
        map.put("nextPaper",nextPaper);
        map.put("isPrePage",isPrePage);
        map.put("isNextPage",isNextPage);
        return map;
    }

    @Override
    public int updatePaperStatus(int id, String review) {
        return paperMapper.updataPaperStatus(id,review);
    }


}
