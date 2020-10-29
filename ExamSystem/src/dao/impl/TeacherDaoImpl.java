package dao.impl;

import dao.TeacherDao;
import entity.ExamPage;
import entity.ExamPaper;
import entity.Student;
import entity.StudentScore;
import sql.StudentSql;
import sql.TeacherSql;
import util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    /**
     * 查看学生成绩及信息
     */
    @Override
    public List<StudentScore> getStudentScore() throws SQLException {
        List<StudentScore> studentScores = new ArrayList<>();
        Connection conn = C3P0Util.getConnection(); //从连接池中获取一个连接
        System.out.println("TeacherDaoImpl "+conn);
        ps = conn.prepareStatement(TeacherSql.checkStudentSql);   //执行sql语句
        rs = ps.executeQuery();            //得到sql结果集
        while (rs.next()){
            int number = rs.getInt(1);
            String username = rs.getString(2);
            String college = rs.getString(3);
            String major = rs.getString(4);
            String score = rs.getString(5);

            StudentScore studentScore = new StudentScore(number,username,college,major,score); //创建对象，并把把数据给StudentScore对象
            studentScores.add(studentScore);   //将该对象添加到列表里

        }
//        C3P0Util.close(conn,ps,rs);
        conn.close();
        return studentScores;
    }


    /**
     * 查询试卷(分页查询)
     */
    @Override
    public List<ExamPage> getExamPage() throws ClassNotFoundException, SQLException {

            List<ExamPage> examPageList = new ArrayList<>();
            Connection conn = C3P0Util.getConnection();
            System.out.println("TeacherDaoImpl "+conn);
            ps = conn.prepareStatement(TeacherSql.checkExamPageSql);   //执行sql语句
            rs = ps.executeQuery();            //得到sql结果集
            while (rs.next()){

                int id = rs.getInt(1);
                String name = rs.getString("name");
                String time = rs.getString("time");
                String teacher = rs.getString("teacher");

                ExamPage examPage = new ExamPage(id,name,time,teacher);
                examPageList.add(examPage);

            }
//            C3P0Util.close(conn,ps,rs);
            conn.close();
            return examPageList;
        }


    /**
     * 得到分页的信息
     */
    public List<ExamPage> getRecords(int begin,int over) throws SQLException {

        List<ExamPage> examPages = new ArrayList<>();
        Connection conn = C3P0Util.getConnection();
        System.out.println("TeacherDaoImpl "+conn);
        ps = conn.prepareStatement(TeacherSql.checkExamPageSql);   //执行sql语句
        ps.setString(1,Integer.toString(begin));
        ps.setString(1,Integer.toString(over));
        rs = ps.executeQuery();             //得到sql结果集
        while (rs.next()){

            int id = rs.getInt(1);
            String name = rs.getString("name");
            String time = rs.getString("time");
            String teacher = rs.getString("teacher");

            ExamPage examPage = new ExamPage(id,name,time,teacher);
            examPages.add(examPage);

        }
//        C3P0Util.close(conn,ps,rs);
        conn.close();
        return examPages;
    }


    /**
     * 增加试卷
     *
     * @param page
     */
    @Override
    public int addPage(ExamPage page) throws SQLException {
        conn = C3P0Util.getConnection();
        ps = conn.prepareStatement(TeacherSql.addExamPageSql);
        ps.setInt(1,page.getId());
        ps.setString(2,page.getName());
        ps.setString(3,page.getTime());
        ps.setString(4,page.getTeacher());
        int rows = ps.executeUpdate();
//        C3P0Util.close(conn,ps,rs);
        conn.close();
        return rows;
    }


    /**
     * 删除试卷
     *
     * @param page
     */
    @Override
    public int deletePage(ExamPage page) throws SQLException {
         conn = C3P0Util.getConnection();
         ps = conn.prepareStatement(TeacherSql.deleteExamPageSql);
         int rows = ps.executeUpdate();
         conn.close();
         return rows;
    }


    /**
     * 查询试题
     */
    @Override
    public List<ExamPaper> getExamPaper() throws SQLException {
        List<ExamPaper> examPapers = new ArrayList<>();
        conn = C3P0Util.getConnection();
        ps = conn.prepareStatement(TeacherSql.checkExamPaperSql);
        rs = ps.executeQuery();
        while (rs.next()){
           int qid = rs.getInt(1);
           String type = rs.getString(2);
           String content = rs.getString(3);
           int value = rs.getInt(4);

           ExamPaper examPaper = new ExamPaper(qid,type,content,value);
           examPapers.add(examPaper);
           conn.close();
        }
        return examPapers;
    }

    /**
     * 增加试题
     *
     * @param paper
     */
    @Override
    public int addPaper(ExamPaper paper) throws SQLException {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(TeacherSql.addExamPaperSql);
            ps.setInt(1,paper.getQid());
            ps.setString(2,paper.getType());
            ps.setString(3,paper.getContent());
            ps.setInt(4,paper.getValue());
            int rows = ps.executeUpdate();
            conn.close();
            return rows;
    }

    /**
     * 删除试题
     *
     * @param paper
     */
    @Override
    public int deletePaper(ExamPaper paper) throws SQLException {
        conn = C3P0Util.getConnection();
        ps = conn.prepareStatement(TeacherSql.deleteExamPaperSql);
        int rows = ps.executeUpdate();
        conn.close();
        return rows;
    }


    /**
     * 修改试题(也就是修改试卷)
     *
     * @param paper
     */
    @Override
    public int updatePaper(ExamPaper paper) throws SQLException {
         conn = C3P0Util.getConnection();
         ps = conn.prepareStatement(TeacherSql.updateExamPaperSql);
         int rows = ps.executeUpdate();
         conn.close();
         return rows;
    }


}
