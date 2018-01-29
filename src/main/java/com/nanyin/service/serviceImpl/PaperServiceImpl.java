package com.nanyin.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.nanyin.config.AllAttriOfPaper;
import com.nanyin.config.PaperAndColumn;
import com.nanyin.config.PaperAndComments;
import com.nanyin.config.common.Paging;
import com.nanyin.config.common.TimeUtil;
import com.nanyin.mapper.PaperMapper;
import com.nanyin.model.Column;
import com.nanyin.model.Comments;
import com.nanyin.model.Paper;
import com.nanyin.model.Users;
import com.nanyin.service.ColumnService;
import com.nanyin.service.CommentsService;
import com.nanyin.service.PaperService;
import com.nanyin.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    private  static final String IS_PASS = "审核通过";

    private static final int INIT_MARK = 1 ;

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
    public Map<String, Object> findPapers(int pageNum,String search,String interval) throws NumberFormatException {
        int limit = Paging.LIMIT.getValue();
        // 时间间隔天数
        int inter = 0;
        logger.info("search:" +search);
        List<Paper> list = null;
        Map<String, Object> map = new HashMap<>();
        if(interval != null && !"".equals(interval)) {
            inter = Integer.parseInt(interval);
            //获得当前时间
            Timestamp now = new Timestamp(System.currentTimeMillis());
            //转换成data类型进项时间的加减
            Date nowDate = new Date();
            try {
                nowDate = now;
            } catch (Exception e) {
                e.printStackTrace();
            }
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(nowDate);
            calendar.add(calendar.DATE, -inter);
            Date afterAdd = calendar.getTime();
            // 转化为timestamps类型
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String afterAddString = simpleDateFormat.format(afterAdd);
            Timestamp end = Timestamp.valueOf(afterAddString);
            // 几天前的时间 end
            list = paperMapper.findPapers((pageNum - 1) * limit, limit, search, now, end);
        } else {
            list = paperMapper.findPapers((pageNum - 1) * limit, limit, search, null, null);
        }
        int count = list.size();
        map.put("code",0);
        map.put("mes","");
        map.put("count",count);
        map.put("data",list);
        return map;
    }

    @Override
    public  Map<String,List<PaperAndComments>> findAllPapersByTime() {
        Map<String,List<PaperAndComments>> map = Maps.newHashMap();
        List<Paper> papers = paperMapper.findAllPapersByTime();
        List<PaperAndComments> paperAndComments = paperAndCommentsList(papers);
        map.put("paper",paperAndComments);
        return map;
    }

    @Override
    public  Map<String,List<PaperAndComments>> findAllPapersByMark() {
        Map<String,List<PaperAndComments>> map = Maps.newHashMap();
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

    private PaperAndComments initPaperAndConmments(String name) {
        PaperAndComments initPaperAndComment = new PaperAndComments();
        initPaperAndComment.setLogin_name(name);
        Users users = userService.findUsersByName(name);
        initPaperAndComment.setHead(users.getHead());
        initPaperAndComment.setTitle("266fb5d2-b97b-41dd-999e-1143c0963fd4");
        initPaperAndComment.setCreate_time(TimeUtil.setCurrentTime());
        return initPaperAndComment;
    }


    @Override
    public PageInfo<PaperAndComments> findAllPaperByUser(String name,String search,int pageNum) {

        List<Paper> papers =  paperMapper.findAllPaperByUser(name,search);
        List<PaperAndComments> paperAndComments = paperAndCommentsList(papers);
        PageHelper.startPage(pageNum,Paging.LIMIT.getValue());
        PageInfo<PaperAndComments> pageInfo = new PageInfo<>(paperAndComments);

        if(paperAndComments.size() == 0){
         //初始化一个paperAndComments
            PaperAndComments paperAndComment = initPaperAndConmments(name);
            paperAndComments.add(paperAndComment);
            return new PageInfo<>(paperAndComments);
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
        PageHelper.startPage(pageNum,Paging.LIMIT.getValue()-2);
        return new PageInfo<>(papers);
    }

    @Override
    public List findAllPapers(String search) {
        List<Paper> papers =  paperMapper.findAllPapers(search);
        return paperAndCommentsList(papers);
    }

    private AllAttriOfPaper setValue(Users users,Paper paper,List<Comments> comments,String columns){
        AllAttriOfPaper allAttriOfPaper = new AllAttriOfPaper();

        allAttriOfPaper.setColumnTitle(columns);
        allAttriOfPaper.setComments(comments);
        List<Users> list = Lists.newLinkedList();
        for (Comments c: comments
             ) {
            Users users1 =userService.findUsersById(c.getComments_user()) ;
            list.add(users1);
        }
        allAttriOfPaper.setComments_UserMes(list);
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

    /**
     * 查找个人文章
     * @param name
     * @param search
     * @return
     */
    @Override
    public PageInfo<Paper> findPaperByUserName(String name,int pageNum,String search) {
        PageHelper.startPage(pageNum,Paging.LIMIT.getValue());
        PageInfo<Paper> pageInfo = new PageInfo<>(paperMapper.findPaperByUserName(name,search));
        return pageInfo;
    }

    @Override
    public int deletePaperByPaperId(int id) {

        return paperMapper.deletePaperByPaperId(id);
    }

    private Boolean columnIsNull(Column column,PaperAndColumn paperAndColumn){
        if(column == null){
            paperAndColumn.setTheme("暂无");
        }
        else {
            paperAndColumn.setTheme(column.getTitle());
        }
        return true;
    }

    private List<PaperAndColumn> setValueToPaperAndColumn(List<Paper> papers){
        List<PaperAndColumn> li1 = Lists.newLinkedList();
        for (Paper p:papers
             ) {
            Column column = columnService.findColumnByPaperId(p.getId());
            PaperAndColumn paperAndColumn = new PaperAndColumn();
            paperAndColumn.setPaperId(p.getId());
            paperAndColumn.setCreateTime(p.getCreate_time());
            paperAndColumn.setIs_pass(p.getIs_pass());
            paperAndColumn.setPaperTitle(p.getTitle());
            columnIsNull(column,paperAndColumn);
        li1.add(paperAndColumn);
        }
        return li1;
    }


    @Override
    public Map<String,Object> findPaperByUser(String name,String pageNum) {
        int limit = Paging.LIMIT.getValue() ;
        int pageNum1 = Integer.parseInt(pageNum);
        Map<String,Object> map = Maps.newHashMap();

        int count = paperMapper.findCountOfPaperByUser(name);
        List<Paper> papers = paperMapper.findPaperByUser(name,(pageNum1-1) * limit,limit);
        List<PaperAndColumn> list = setValueToPaperAndColumn(papers);
        map.put("code",0);
        map.put("mes","");
        map.put("count",count);
        map.put("data",list);
        return map;
    }

    @Override
    public Paper findPaperById(String id) {
        int id1 = Integer.parseInt(id);
        return paperMapper.findPaperById(id1);
    }

    @Override
    public Paper findPaperById(int id) {
        return paperMapper.findPaperById(id);
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
        Timestamp createTime = TimeUtil.setCurrentTime();
        return paperMapper.insertPaper(title,content,createTime,author,segment,INIT_MARK,IS_PASS);
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

        int currentPaperNum = Paging.INIT.getValue() ;

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
        String segment;
        int prePaperId;
        if(prePaperNum < 0){
            prePaperTitle="已经到头了";
            prePaperId = -1 ;
            segment="";
            isPrePage = true ;
        }else {
            prePaperTitle = "上一篇："+paperList.get(prePaperNum).getTitle();
            prePaperId = paperList.get(prePaperNum).getId();
            segment = paperList.get(prePaperNum).getSgement();
        }
        Paper prePaper = new Paper();
        prePaper.setId(prePaperId);
        prePaper.setTitle(prePaperTitle);
        prePaper.setSgement(segment);

        // 下一页
        String nextPaperTitle;
        String nextSegment;
        int nextPaperId;
        if(nextPaperNum >= paperList.size()){
            nextPaperTitle = "已经到末尾了";
            nextPaperId = -2;
            nextSegment = "";
            isNextPage = true;
        }else {
            nextPaperTitle = "下一篇："+paperList.get(nextPaperNum).getTitle();
            nextSegment=paperList.get(nextPaperNum).getSgement();
            nextPaperId = paperList.get(nextPaperNum).getId();
        }
        Paper nextPaper = new Paper();
        nextPaper.setId(nextPaperId);
        nextPaper.setTitle(nextPaperTitle);
        nextPaper.setSgement(nextSegment);

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



    @Override
    public PageInfo<Paper> findPaperInMonth() {
        PageHelper.startPage(1,Paging.LIMIT.getValue()-5);
        //获得当前时间
        Timestamp now = new Timestamp(System.currentTimeMillis());
        //转换成data类型进项时间的加减
        Date nowDate = new Date();
        try {
            nowDate = now;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(nowDate);
        //获得30天之前的日期
        calendar.add(calendar.DATE, -30);
        Date afterAdd = calendar.getTime();
        // 转化为timestamps类型
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String afterAddString = simpleDateFormat.format(afterAdd);
        Timestamp end = Timestamp.valueOf(afterAddString);
        List<Paper> list = paperMapper.findPaperInMonth(end);
        return new PageInfo<>(list);
    }


}
