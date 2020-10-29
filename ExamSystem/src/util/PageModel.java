package util;

import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import entity.ExamPage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PageModel {


    //查询记录数
    private int totalRecords;

    //当前页数
    private int pageNo;

    //每一页有多少条记录
    private int pageSize;

    //总页数
    private int totalPages;

    static TeacherDaoImpl teacherDaoimpl = new TeacherDaoImpl();
    List<ExamPage> examPages = new ArrayList<>();
    static List<ExamPage> examPageList;

    static {
        try {
            examPageList = teacherDaoimpl.getExamPage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PageModel() throws SQLException, ClassNotFoundException {
        pageSize = 4;
        pageNo = 0;
        totalRecords = teacherDaoimpl.getExamPage().size();
        totalPages = (totalRecords + pageSize - 1) / pageSize ;
    }

    //总页数
    public int getTotalPages(){
        return totalPages ;
    }

    public int getPageNo() { return pageNo;}

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<ExamPage> getRecords_1(){
        examPages.clear();
        for (int i=0;i<pageSize;i++){
           examPages.add(examPageList.get(pageNo * pageSize + i));
        }
        return examPages;
    }

    public List<ExamPage> getRecords_2() throws SQLException, ClassNotFoundException {
        return teacherDaoimpl.getRecords(pageNo * pageSize + 1,pageNo * pageSize + pageSize);
    }


    //首页
    public int getToPage(){
        return 1;
    }

    //上一页
    public int getPreviousPage(){
        if (pageNo <= 1){
            return 1;
        }
        return pageNo - 1;
    }

    //下一页
    public int getNextPage(){
        if (pageNo >= getBottomPage()){
            return getBottomPage();
        }
        return pageNo + 1;
    }

    //尾页
    public int getBottomPage(){
        return  getBottomPage();
    }


//    public void setTotalRecords(int totalRecords){
//        this.totalRecords = totalRecords;
//    }
//
//
//    public void setPageSize(int pageSize){
//        this.pageSize = pageSize;
//    }


}
